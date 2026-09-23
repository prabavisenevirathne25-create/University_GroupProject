package university;

public class StudentHashTable {

    private Student[] table;
    private int maxSize;
    private int size;

    public StudentHashTable(int capacity) {

        maxSize = capacity;
        table = new Student[maxSize];
        size = 0;
    }

    private int hash(int id) {
        return Math.floorMod(id, maxSize);
    }

    // Insert student
    public boolean insert(Student student) {

        if (student == null || search(student.id) != null) {
            return false;
        }

        if (size == maxSize) {
            System.out.println("Hash table is full.");
            return false;
        }

        int index = hash(student.id);

        while (table[index] != null) {
            index = (index + 1) % maxSize;
        }

        table[index] = student;
        size++;

        return true;
    }

    // Search student by ID
    public Student search(int id) {

        int index = hash(id);
        int checked = 0;

        while (checked < maxSize && table[index] != null) {

            if (table[index].id == id) {
                return table[index];
            }

            index = (index + 1) % maxSize;
            checked++;
        }

        return null;
    }

    // Delete student by ID
    public Student delete(int id) {

        int index = hash(id);
        int checked = 0;

        while (checked < maxSize && table[index] != null) {

            if (table[index].id == id) {

                Student removed = table[index];

                table[index] = null;
                size--;

                // Rehash records following the deleted position
                int next = (index + 1) % maxSize;

                while (table[next] != null) {

                    Student temp = table[next];

                    table[next] = null;
                    size--;

                    insert(temp);

                    next = (next + 1) % maxSize;
                }

                return removed;
            }

            index = (index + 1) % maxSize;
            checked++;
        }

        return null;
    }

    // Display hash table
    public void displayTable() {

        System.out.println("----- Student Hash Table -----");

        for (int i = 0; i < maxSize; i++) {

            if (table[i] == null) {
                System.out.println("[" + i + "] Empty");
            } else {
                System.out.println(
                    "[" + i + "] " +
                    table[i].id + " - " + table[i].name
                );
            }
        }
    }
}