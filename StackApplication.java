import java.util.Scanner;

// PART 1: STACK IMPLEMENTATION USING ARRAY
class ArrayStack<T> {
    private Object[] items;
    private int top;

    public ArrayStack() {
        items = new Object[10];
        top = -1;
    }

    public void push(T value) {
        if (top == items.length - 1) {
            Object[] newItems = new Object[items.length * 2];
            for (int i = 0; i < items.length; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        items[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        T value = (T) items[top];
        items[top] = null;
        top--;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return (T) items[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}

// PART 2: STACK IMPLEMENTATION USING LINKED LIST
class LinkedListStack<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public LinkedListStack() {
        top = null;
        size = 0;
    }

    public void push(T value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        T value = top.data;
        top = top.next;
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}

// PART 3: REAL-WORLD APPLICATION - BROWSER BACK HISTORY
public class StackApplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayStack<String> history = new ArrayStack<>();
        String currentPage = "Home";

        System.out.println("====================================");
        System.out.println("     STACK BROWSER HISTORY SYSTEM");
        System.out.println("====================================");
        System.out.println("Student Index: PS/ITC/24/0290");
        System.out.println("Current page: " + currentPage);

        while (true) {
            System.out.println("\\nMENU");
            System.out.println("1. Visit a new page");
            System.out.println("2. Go back");
            System.out.println("3. View current page");
            System.out.println("4. View history size");
            System.out.println("5. Demonstrate Array Stack");
            System.out.println("6. Demonstrate Linked List Stack");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter page name or URL: ");
                    String page = input.nextLine().trim();
                    if (page.isEmpty()) {
                        System.out.println("Page name cannot be empty.");
                    } else {
                        history.push(currentPage);
                        currentPage = page;
                        System.out.println("You are now visiting: " + currentPage);
                    }
                    break;
                case 2:
                    if (history.isEmpty()) {
                        System.out.println("No previous page available.");
                    } else {
                        currentPage = history.pop();
                        System.out.println("Returned to: " + currentPage);
                    }
                    break;
                case 3:
                    System.out.println("Current page: " + currentPage);
                    break;
                case 4:
                    System.out.println("Pages in history stack: " + history.size());
                    break;
                case 5:
                    demonstrateArrayStack();
                    break;
                case 6:
                    demonstrateLinkedListStack();
                    break;
                case 7:
                    System.out.println("Thank you for using the application!");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice. Choose from 1 to 7.");
            }
        }
    }

    public static void demonstrateArrayStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        System.out.println("\\nARRAY STACK DEMONSTRATION");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Pushed: 10, 20, 30");
        System.out.println("Top element: " + stack.peek());
        System.out.println("Removed element: " + stack.pop());
        System.out.println("New top element: " + stack.peek());
        System.out.println("Stack size: " + stack.size());
    }

    public static void demonstrateLinkedListStack() {
        LinkedListStack<String> stack = new LinkedListStack<>();
        System.out.println("\\nLINKED LIST STACK DEMONSTRATION");
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        System.out.println("Pushed: First, Second, Third");
        System.out.println("Top element: " + stack.peek());
        System.out.println("Removed element: " + stack.pop());
        System.out.println("New top element: " + stack.peek());
        System.out.println("Stack size: " + stack.size());
    }
}
