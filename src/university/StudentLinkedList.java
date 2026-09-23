package university;

public class StudentLinkedList {

    public Student first;

    public StudentLinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    // Add student at the end
    public boolean insertLast(int id, String name,
                              String programme, double marks) {

        if (find(id) != null) {
            return false;
        }

        Student newStudent = new Student(id, name, programme, marks);

        if (isEmpty()) {
            first = newStudent;
        } else {
            Student current = first;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newStudent;
        }

        return true;
    }

    // Search student by ID
    public Student find(int id) {

        Student current = first;

        while (current != null) {
            if (current.id == id) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    // Update student
    public boolean update(int id, String name,
                          String programme, double marks) {

        Student student = find(id);

        if (student == null) {
            return false;
        }

        student.name = name;
        student.programme = programme;
        student.marks = marks;

        return true;
    }

    // Delete student
    public Student delete(int id) {

        Student current = first;
        Student previous = null;

        while (current != null && current.id != id) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return null;
        }

        if (previous == null) {
            first = current.next;
        } else {
            previous.next = current.next;
        }

        current.next = null;

        return current;
    }

    // Display all students
    public void displayList() {

        if (isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        Student current = first;

        while (current != null) {
            current.displayStudent();
            System.out.println("-----------------------------");

            current = current.next;
        }
    }
}