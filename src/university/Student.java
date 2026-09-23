package university;

public class Student {

    public int id;
    public String name;
    public String programme;
    public double marks;

    // Linked list reference
    public Student next;

    // BST references
    public Student leftChild;
    public Student rightChild;

    public Student(int id, String name, String programme, double marks) {
        this.id = id;
        this.name = name;
        this.programme = programme;
        this.marks = marks;

        this.next = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void displayStudent() {
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Programme    : " + programme);
        System.out.println("Marks        : " + marks);
    }
}