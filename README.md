# University Student Record and Campus Route Management System

The **University Student Record & Campus Route System** is a Java console-based application designed to manage student records, university service requests, recent system actions, and campus locations and routes.

The project demonstrates the practical use of several fundamental data structures and algorithms:

- Singly linked list
- Hash table with linear probing
- Binary search tree
- Circular queue
- Stack
- Graph using an adjacency matrix
- Breadth-First Search (BFS)
- Depth-First Search (DFS)

The application is operated through a menu-driven command-line interface.

---
## Main Features

### 1. Student Record Management

The system allows users to:

- Add student records
- Update student records
- Delete student records
- Display all student records
- Search for students by ID

Each student record contains:

- Student ID
- Student name
- Programme
- Marks

Student records are maintained in a linked list and are also stored in a hash table for efficient searching.

---
### 2. Hash-Based Student Search

The `StudentHashTable` class stores student records using:

- Hashing based on the student ID
- Linear probing for collision resolution
- Rehashing after deletion

This allows students to be searched efficiently by their student ID.

---

### 3. Binary Search Tree Display

The system rebuilds a binary search tree from the linked-list student records and displays students in ascending order of their IDs using in-order traversal.

### 4. Service Request Queue

The `ServiceQueue` class manages university service requests using a circular queue.

Users can:

- Add service requests
- Process the next pending request
- View all pending requests

Requests are processed according to the First-In, First-Out (FIFO) principle.

---
### 5. Recent Actions Stack

The `ActionStack` class records recent system operations using a stack.

The system records actions such as:

- Adding a student
- Updating a student
- Deleting a student
- Adding or processing a service request
- Adding or removing campus locations
- Adding or removing campus roads

Recent actions are displayed according to the Last-In, First-Out (LIFO) principle.

---
### 6. Campus Graph

The `CampusGraph` class represents campus locations and connections as an undirected graph.

Users can:

- Add campus locations
- Remove campus locations
- Add roads between locations
- Remove roads between locations
- Display campus connections
- Traverse the graph using BFS or DFS

The graph is implemented using an adjacency matrix.

---
## Data Structures Used

|Data Structure            |  Class                    |   Purpose                                 |  
|--------------------------|---------------------------|-------------------------------------------|
|Singly Linked List        | StudentLinkedList         | Stores student records in insertion order | 
|Hash Table                | StudentHashTable          | Provides fast student searching by ID     | 
|Binary Search Tree        | StudentTree               | Displays students in ascending ID order   | 
|Queue                     | ServiceQueue              | Manages service requests in FIFO order    | 
|Stack                     | ActionStack               | Stores recent actions in LIFO order       | 
|Graph                     | CampusGraph               | Represents campus locations and roads     | 

---

## Project Structure

All Java source files belong to the `university` package.

```text
src/
└── university/
    ├── ActionStack.java
    ├── CampusGraph.java
    ├── Main.java
    ├── ServiceQueue.java
    ├── Student.java
    ├── StudentHashTable.java
    ├── StudentLinkedList.java
    └── StudentTree.java
```

### Class Descriptions

#### `Main.java`

Contains the main program, menu system, input validation, and operations for students, service requests, actions, and campus routes.

#### `Student.java`

Represents a student record and contains references used by the linked list and binary search tree.

#### `StudentLinkedList.java`

Stores student records in a singly linked list and provides insertion, searching, updating, deletion, and display operations.

#### `StudentHashTable.java`

Stores students using a fixed-size hash table with linear probing.

#### `StudentTree.java`

Implements a binary search tree based on student IDs.

#### `ServiceQueue.java`

Implements a circular queue for university service requests.

#### `ActionStack.java`

Implements a fixed-size stack for storing recent actions.

#### `CampusGraph.java`

Implements an undirected campus graph using an adjacency matrix and supports BFS and DFS traversal.

---

## Menu Options

```text
1.  Add Student Record
2.  Update Student Record
3.  Delete Student Record
4.  Display All Records using Linked List
5.  Search Student using Hashing
6.  Display Students using BST/AVL
7.  Add Service Request to Queue
8.  Process Next Service Request
9.  Display Pending Service Requests
10. Display Recent Actions (Stack)
11. Add Campus Location
12. Remove Campus Location
13. Add Campus Connection/Road
14. Remove Campus Connection/Road
15. Display Campus Connections
16. Traverse Campus (BFS / DFS)
17. Exit
```
## Team Members and Individual Contributions

|Member's Name        |  Student ID      |   Assigned Responsibility        | Individual Contribution                          |
|---------------------|------------------|----------------------------------|--------------------------------------------------|
|W.A.T.K. Chandrasiri | 23DA2-0214       |Linked List and Student Record    |Option 1: Add Student Record                      |                                        
|                     |                  |                                  |Option 2: Update Student Record                   |
|                     |                  |                                  |Option 3: Delete Student Record                   |
|                     |                  |                                  |Option 4: Display All Records using Linked List   |
|R.M.T.R. Rathnayaka  | 23DA2-0253       |Stack and Queue                   |Option 7: Add Service Request to Queue            |                                        
|                     |                  |                                  |Option 8: Process Next Service Request            |
|                     |                  |                                  |Option 9: Display Pending Service Requests        |
|                     |                  |                                  |Option 10: Display Recent Actions using Stack     |
| N.B.A. Nethmi  Navodya Nishanka| 23DA2-0182       |BST and Hashing        |Option 5: Search Student using Hashing            |                                        
|                     |                  |                                  |Option 6: Display Students using BST/AVL          |
| P.H. Senevirathna   | 23DA2-0071       |  Graph and Main.java             |Option 11: Add Campus Location                    |                                        
|                     |                  |                                  |Option 12: Remove Campus Location                 |
|                     |                  |                                  |Option 13: Add Campus Connection/Road             |
|                     |                  |                                  |Option 14: Remove Campus Connection/Road          |
|                     |                  |                                  |Option 15: Display Campus Connections             |
|                     |                  |                                  |Option 16: Traverse Campus using BFS/DFS          |
|                     |                  |                                  |Option 17: Exit                                   |


  

                                                                            
                                                                             


                                                                             
/*
## Academic Information

- **Project Title:** University Student Record & Campus Route System
- **Programming Language:** Java
- **Academic Institution:** `Sri Lanka Technology Campus`
- **Course/Module:** `CIT300-Data Structures and Algorithms`
