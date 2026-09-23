package university;

public class ServiceQueue {

    private String[] queue;
    private int front;
    private int rear;
    private int maxSize;
    private int count;

    public ServiceQueue(int capacity) {

        maxSize = capacity;
        queue = new String[maxSize];

        front = 0;
        rear = -1;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == maxSize;
    }

    // Add request
    public boolean insert(String request) {

        if (request == null || request.trim().isEmpty()) {
            System.out.println("Request cannot be empty.");
            return false;
        }

        if (isFull()) {
            System.out.println("Queue is full.");
            return false;
        }

        rear = (rear + 1) % maxSize;
        queue[rear] = request;
        count++;

        return true;
    }

    // Process next request
    public String remove() {

        if (isEmpty()) {
            return null;
        }

        String request = queue[front];

        queue[front] = null;
        front = (front + 1) % maxSize;
        count--;

        return request;
    }

    // View next request
    public String peekFront() {

        if (isEmpty()) {
            return null;
        }

        return queue[front];
    }

    // Display pending requests
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("----- Pending Service Requests -----");

        int index = front;

        for (int i = 0; i < count; i++) {

            System.out.println((i + 1) + ". " + queue[index]);

            index = (index + 1) % maxSize;
        }
    }
}