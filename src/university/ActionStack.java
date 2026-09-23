package university;

public class ActionStack {

    private String[] stackArray;
    private int maxSize;
    private int top;

    public ActionStack(int size) {

        maxSize = size;
        stackArray = new String[maxSize];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    // Push action
    public boolean push(String value) {

        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        if (isFull()) {
            System.out.println("Action history is full.");
            return false;
        }

        stackArray[++top] = value;

        return true;
    }

    // Pop action
    public String pop() {

        if (isEmpty()) {
            return null;
        }

        String value = stackArray[top];
        stackArray[top] = null;
        top--;

        return value;
    }

    // View latest action
    public String peek() {

        if (isEmpty()) {
            return null;
        }

        return stackArray[top];
    }

    // Display latest actions first
    public void displayRecentActions() {

        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }

        System.out.println("----- Recent Actions -----");

        for (int i = top; i >= 0; i--) {
            System.out.println(stackArray[i]);
        }
    }
}