package university;

public class StudentTree {

    private Student root;

    public StudentTree() {
        root = null;
    }

    // Insert student
    public boolean insert(int id, String name,
                          String programme, double marks) {

        if (find(id) != null) {
            return false;
        }

        Student newNode = new Student(id, name, programme, marks);

        if (root == null) {
            root = newNode;
            return true;
        }

        Student current = root;

        while (true) {

            if (id < current.id) {

                if (current.leftChild == null) {
                    current.leftChild = newNode;
                    return true;
                }

                current = current.leftChild;

            } else {

                if (current.rightChild == null) {
                    current.rightChild = newNode;
                    return true;
                }

                current = current.rightChild;
            }
        }
    }

    // Search BST
    public Student find(int id) {

        Student current = root;

        while (current != null) {

            if (id == current.id) {
                return current;
            }

            if (id < current.id) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }

        return null;
    }

    // Display records in ascending ID order
    public void inOrder() {

        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }

        inOrder(root);
    }

    private void inOrder(Student node) {

        if (node != null) {

            inOrder(node.leftChild);

            node.displayStudent();
            System.out.println("-----------------------------");

            inOrder(node.rightChild);
        }
    }

    // Clear the tree before rebuilding it
    public void deleteAll() {
        root = null;
    }
}