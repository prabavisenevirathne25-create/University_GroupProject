package university;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Data structures
    static StudentLinkedList studentList = new StudentLinkedList();

    static StudentHashTable hashTable = new StudentHashTable(23);

    static ServiceQueue serviceQueue = new ServiceQueue(10);

    static ActionStack actionStack = new ActionStack(30);

    static CampusGraph campus = new CampusGraph(20);

    public static void main(String[] args) {

        int choice;

        do {

            printMenu();

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    deleteStudent();
                    break;

                case 4:
                    displayStudents();
                    break;

                case 5:
                    searchStudent();
                    break;

                case 6:
                    displayStudentsUsingBST();
                    break;

                case 7:
                    addServiceRequest();
                    break;

                case 8:
                    processServiceRequest();
                    break;

                case 9:
                    serviceQueue.displayQueue();
                    break;

                case 10:
                    actionStack.displayRecentActions();
                    break;

                case 11:
                    addCampusLocation();
                    break;

                case 12:
                    removeCampusLocation();
                    break;

                case 13:
                    addCampusRoad();
                    break;

                case 14:
                    removeCampusRoad();
                    break;

                case 15:
                    campus.displayConnections();
                    break;

                case 16:
                    traverseCampus();
                    break;

                case 17:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 17);

        input.close();
    }

    // ---------------- MENU ----------------

    static void printMenu() {

        System.out.println("==============================================");
        System.out.println(" UNIVERSITY STUDENT RECORD & CAMPUS ROUTE SYSTEM");
        System.out.println("==============================================");

        System.out.println(" 1. Add Student Record");
        System.out.println(" 2. Update Student Record");
        System.out.println(" 3. Delete Student Record");
        System.out.println(" 4. Display All Records using Linked List");
        System.out.println(" 5. Search Student using Hashing");
        System.out.println(" 6. Display Students using BST/AVL");
        System.out.println(" 7. Add Service Request to Queue");
        System.out.println(" 8. Process Next Service Request");
        System.out.println(" 9. Display Pending Service Requests");
        System.out.println("10. Display Recent Actions (Stack)");
        System.out.println("11. Add Campus Location");
        System.out.println("12. Remove Campus Location");
        System.out.println("13. Add Campus Connection/Road ");
        System.out.println("14. Remove Campus Connection/Road");
        System.out.println("15. Display Campus Connections");
        System.out.println("16. Traverse Campus (BFS / DFS)");
        System.out.println("17. Exit");

        System.out.println("==============================================");
    }

    // ---------------- STUDENT OPERATIONS ----------------

    // Add student
    static void addStudent() {

        int id = readInt("Enter Student ID: ");

        if (id <= 0) {
            System.out.println("Student ID must be positive.");
            return;
        }

        if (studentList.find(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        String name = readLine("Enter Student Name: ").trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        String programme = readLine("Enter Programme: ").trim();

        if (programme.isEmpty()) {
            System.out.println("Programme cannot be empty.");
            return;
        }

        double marks = readDouble("Enter Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        Student student = new Student(id, name, programme, marks);

        // Add to linked list
        boolean added = studentList.insertLast(
            id, name, programme, marks
        );

        if (!added) {
            System.out.println("Unable to add student.");
            return;
        }

        // Add to hash table
        if (!hashTable.insert(studentList.find(id))) {

            studentList.delete(id);

            System.out.println("Unable to add student to hash table.");
            return;
        }

        actionStack.push("Added student: " + id + " - " + name);

        System.out.println("Student record added successfully.");
    }

    // Update student
    static void updateStudent() {

        int id = readInt("Enter Student ID to update: ");

        Student student = studentList.find(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        String name = readLine("Enter new Student Name: ").trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        String programme = readLine("Enter new Programme: ").trim();

        if (programme.isEmpty()) {
            System.out.println("Programme cannot be empty.");
            return;
        }

        double marks = readDouble("Enter new Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        studentList.update(id, name, programme, marks);

        // Hash table stores a reference to the same Student object.
        // Therefore, the updated values are reflected there as well.

        actionStack.push("Updated student: " + id);

        System.out.println("Student record updated successfully.");
    }

    // Delete student
    static void deleteStudent() {

        int id = readInt("Enter Student ID to delete: ");

        Student removed = studentList.delete(id);

        if (removed == null) {
            System.out.println("Student not found.");
            return;
        }

        hashTable.delete(id);

        actionStack.push("Deleted student: " + id);

        System.out.println("Student record deleted successfully.");
    }

    // Display linked list
    static void displayStudents() {

        System.out.println("----- All Student Records -----");

        studentList.displayList();
    }

    // Search using hashing
    static void searchStudent() {

        int id = readInt("Enter Student ID to search: ");

        Student student = hashTable.search(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found:");
            student.displayStudent();
        }
    }

    // Display students using BST
    static void displayStudentsUsingBST() {

        if (studentList.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        StudentTree tree = new StudentTree();

        Student current = studentList.first;

        // Rebuild BST using current linked-list records
        while (current != null) {

            tree.insert(
                current.id,
                current.name,
                current.programme,
                current.marks
            );

            current = current.next;
        }

        System.out.println("----- Students in BST (Ascending ID) -----");

        tree.inOrder();
    }

    // ---------------- SERVICE QUEUE ----------------

    // Add service request
    static void addServiceRequest() {

        String request = readLine("Enter service request: ").trim();

        if (request.isEmpty()) {
            System.out.println("Service request cannot be empty.");
            return;
        }

        if (serviceQueue.insert(request)) {

            actionStack.push("Added service request: " + request);

            System.out.println("Service request added successfully.");
        }
    }

    // Process next service request
    static void processServiceRequest() {

        String request = serviceQueue.remove();

        if (request == null) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("Processing request: " + request);

        actionStack.push("Processed service request: " + request);
    }

    // ---------------- CAMPUS GRAPH ----------------

    // Add location
    static void addCampusLocation() {

        String name = readLine("Enter campus location name: ").trim();

        if (campus.addLocation(name)) {

            actionStack.push("Added campus location: " + name);

            System.out.println("Campus location added successfully.");
        }
    }

    // Remove location
    static void removeCampusLocation() {

        if (campus.isEmpty()) {
            System.out.println("No campus locations available.");
            return;
        }

        String name = readLine("Enter location name to remove: ").trim();

        if (campus.removeLocation(name)) {

            actionStack.push("Removed campus location: " + name);

            System.out.println("Campus location removed successfully.");
        } else {
            System.out.println("Location not found.");
        }
    }

    // Add road
    static void addCampusRoad() {

        String from = readLine("Enter first location: ").trim();

        String to = readLine("Enter second location: ").trim();

        if (campus.addRoad(from, to)) {

            actionStack.push("Added road: " + from + " <-> " + to);

            System.out.println("Campus road added successfully.");
        }
    }

    // Remove road
    static void removeCampusRoad() {

        String from = readLine("Enter first location: ").trim();

        String to = readLine("Enter second location: ").trim();

        if (campus.removeRoad(from, to)) {

            actionStack.push("Removed road: " + from + " <-> " + to);

            System.out.println("Campus road removed successfully.");
        }
    }

    // BFS / DFS traversal
    static void traverseCampus() {

        if (campus.isEmpty()) {
            System.out.println("No campus locations available.");
            return;
        }

        String start = readLine("Enter starting location: ").trim();

        System.out.println("1. Breadth First Search (BFS)");
        System.out.println("2. Depth First Search (DFS)");

        int option = readInt("Choose traversal method: ");

        if (option == 1) {

            campus.bfs(start);

        } else if (option == 2) {

            campus.dfs(start);

        } else {

            System.out.println("Invalid traversal option.");
        }
    }

    // ---------------- INPUT VALIDATION ----------------

    // Read integer safely
    static int readInt(String prompt) {

        while (true) {

            System.out.print(prompt);

            if (input.hasNextInt()) {

                int value = input.nextInt();
                input.nextLine();

                return value;

            } else {

                System.out.println("Invalid input. Please enter an integer.");

                input.nextLine();
            }
        }
    }

    // Read double safely
    static double readDouble(String prompt) {

        while (true) {

            System.out.print(prompt);

            if (input.hasNextDouble()) {

                double value = input.nextDouble();
                input.nextLine();

                if (Double.isFinite(value)) {
                    return value;
                }

                System.out.println("Please enter a valid finite number.");

            } else {

                System.out.println("Invalid input. Please enter a number.");

                input.nextLine();
            }
        }
    }

    // Read text
    static String readLine(String prompt) {

        System.out.print(prompt);

        return input.nextLine();
    }
}