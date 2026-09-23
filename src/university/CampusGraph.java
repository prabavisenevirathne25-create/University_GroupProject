package university;

public class CampusGraph {

    private String[] locations;
    private int[][] roads;
    private int maxSize;
    private int count;

    // Constructor
    public CampusGraph(int capacity) {

        maxSize = capacity;
        locations = new String[maxSize];
        roads = new int[maxSize][maxSize];
        count = 0;
    }

    // Check whether graph is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Return number of locations
    public int getCount() {
        return count;
    }

    // Find location index
    private int indexOf(String name) {

        if (name == null) {
            return -1;
        }

        for (int i = 0; i < count; i++) {

            if (locations[i].equalsIgnoreCase(name.trim())) {
                return i;
            }
        }

        return -1;
    }

    // Add campus location
    public boolean addLocation(String name) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Location name cannot be empty.");
            return false;
        }

        name = name.trim();

        if (indexOf(name) != -1) {
            System.out.println("Location already exists.");
            return false;
        }

        if (count == maxSize) {
            System.out.println("Campus location list is full.");
            return false;
        }

        locations[count] = name;
        count++;

        return true;
    }

    // Remove campus location and connected roads
    public boolean removeLocation(String name) {

        int index = indexOf(name);

        if (index == -1) {
            return false;
        }

        // Shift rows upward
        for (int i = index; i < count - 1; i++) {

            for (int j = 0; j < count; j++) {
                roads[i][j] = roads[i + 1][j];
            }
        }

        // Shift columns left
        for (int i = 0; i < count - 1; i++) {

            for (int j = index; j < count - 1; j++) {
                roads[i][j] = roads[i][j + 1];
            }
        }

        // Shift location names
        for (int i = index; i < count - 1; i++) {
            locations[i] = locations[i + 1];
        }

        count--;

        // Clear unused row and column
        locations[count] = null;

        for (int i = 0; i < maxSize; i++) {
            roads[count][i] = 0;
            roads[i][count] = 0;
        }

        return true;
    }

    // Add road between two locations
    public boolean addRoad(String from, String to) {

        int a = indexOf(from);
        int b = indexOf(to);

        if (a == -1 || b == -1) {
            System.out.println("One or both locations were not found.");
            return false;
        }

        if (a == b) {
            System.out.println("A location cannot connect to itself.");
            return false;
        }

        if (roads[a][b] == 1) {
            System.out.println("Road already exists.");
            return false;
        }

        // Undirected graph
        roads[a][b] = 1;
        roads[b][a] = 1;

        return true;
    }

    // Remove road between two locations
    public boolean removeRoad(String from, String to) {

        int a = indexOf(from);
        int b = indexOf(to);

        if (a == -1 || b == -1) {
            System.out.println("One or both locations were not found.");
            return false;
        }

        if (a == b) {
            System.out.println("Invalid connection.");
            return false;
        }

        if (roads[a][b] == 0) {
            System.out.println("Road does not exist.");
            return false;
        }

        roads[a][b] = 0;
        roads[b][a] = 0;

        return true;
    }

    // Display all campus connections
    public void displayConnections() {

        if (isEmpty()) {
            System.out.println("No campus locations added.");
            return;
        }

        System.out.println("----- Campus Connections -----");

        for (int i = 0; i < count; i++) {

            System.out.print(locations[i] + " -> ");

            boolean hasRoad = false;

            for (int j = 0; j < count; j++) {

                if (roads[i][j] == 1) {
                    System.out.print(locations[j] + "  ");
                    hasRoad = true;
                }
            }

            if (!hasRoad) {
                System.out.print("(no connections)");
            }

            System.out.println();
        }
    }

    // Breadth First Search
    public void bfs(String start) {

        int s = indexOf(start);

        if (s == -1) {
            System.out.println("Starting location not found.");
            return;
        }

        boolean[] visited = new boolean[count];
        int[] queue = new int[count];

        int front = 0;
        int rear = 0;

        visited[s] = true;
        queue[rear++] = s;

        System.out.print("BFS Traversal: ");

        while (front < rear) {

            int current = queue[front++];

            System.out.print(locations[current] + " ");

            for (int i = 0; i < count; i++) {

                if (roads[current][i] == 1 && !visited[i]) {

                    visited[i] = true;
                    queue[rear++] = i;
                }
            }
        }

        System.out.println();
    }

    // Depth First Search
    public void dfs(String start) {

        int s = indexOf(start);

        if (s == -1) {
            System.out.println("Starting location not found.");
            return;
        }

        boolean[] visited = new boolean[count];

        System.out.print("DFS Traversal: ");

        dfsRecursive(s, visited);

        System.out.println();
    }

    // Recursive DFS helper
    private void dfsRecursive(int current, boolean[] visited) {

        visited[current] = true;

        System.out.print(locations[current] + " ");

        for (int i = 0; i < count; i++) {

            if (roads[current][i] == 1 && !visited[i]) {
                dfsRecursive(i, visited);
            }
        }
    }
}