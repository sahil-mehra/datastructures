package project20280;

import java.util.Iterator;
import java.util.Stack;

public class SinglyLinkedList<E> implements List<E> {
    // Instance variables
    Node root;
    int size;

    public static void main(String[] args) {
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        for (String s : alphabet) {
            sll.addFirst(s);
            sll.addLast(s);
        }
        System.out.println(sll.toString());

        sll.removeFirst();
        System.out.println(sll.toString());

        sll.removeLast();
        System.out.println(sll.toString());

        sll.remove(2);
        System.out.println(sll.toString());

        for (String s : sll) {
            System.out.print(s + ", ");
        }

        SinglyLinkedList<Integer> ll = new SinglyLinkedList<>(); //LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);
        ll.add(3, 2);
        System.out.println(ll);

        ll.addFirst(-100);
        ll.addLast(+100);
        System.out.println(ll);
        ll.removeFirst();
        ll.removeLast();
        System.out.println(ll);

        // Removes the item in the specified index
        ll.remove(2);
        System.out.println(ll);
        ll.removeFirst();
        System.out.println(ll);
        ll.removeLast();
        System.out.println(ll);
        ll.removeFirst();
        System.out.println(ll);
        ll.addFirst(9999);
        ll.addFirst(8888);
        ll.addFirst(7777);
        System.out.println("\n\n\n");
        System.out.println(ll);
        ll.reverse();
        System.out.println(ll);
    }

    public void reverse() {
        // Reverse the order of the list using only a stack. Do not create a new list!
        Stack<E> stack = new Stack<>();
        Node ptr = this.root;
        // Push the elements of the list to the stack
        while (ptr != null) {
            stack.push(ptr.data);
            ptr = ptr.next;
        }

        for (E e : stack) {
            System.out.println(e);
        }
        // Pop from stack and replace
        // current nodes value
        while (!stack.isEmpty()) {
            if (ptr == null) {
                root = new Node(stack.peek());
                ptr = root;
            } else {
                ptr.next = new Node(stack.peek());
                ptr = ptr.next;
            }
            stack.pop();
        }
        ptr.next = null;
    }

    // TODO
    public SinglyLinkedList<E> recursiveCopy(){
        SinglyLinkedList<E> temp = new SinglyLinkedList<>();


        return temp;
    }

    // Done
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i >= size) {
            throw new IllegalArgumentException("Invalid index of list, must be less than size.");
        } else {// Otherwise valid index
            Node temp = root; // Temp Node for traversing list

            for (int j = 0; j < i; j++) {// Move forward i times
                temp = temp.next; // Move forward
            }
            return temp.data; // Return data
        }
    }

    @Override
    public void add(int i, E e) {
        if (i >= size) {
            throw new IllegalArgumentException("Invalid index of list, must be less than size.");
        } else {// Otherwise valid index
            Node temp = root; // Temp Node for traversing list
            for (int j = 0; j < i; j++) {// Move forward i times
                temp = temp.next; // Move forward
            }
            Node newNode = new Node(e); // Create new Node
            newNode.next = temp.next; // Set next pointer
            temp.next = newNode; // Set temp pointer
            size++;
        }
    }

    @Override
    public E remove(int i) {
        if (i >= size) {
            throw new IllegalArgumentException("Invalid index of list, must be less than size.");
        } else {// Otherwise valid index
            Node temp = root; // Temp Node for traversing list

            for (int j = 0; j < i - 1; j++) {// Move forward i times
                temp = temp.next; // Move forward
            }

            Node deletedNode = temp.next;
            temp.next = temp.next.next;

            size--;
            return deletedNode.data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (root != null) {// If there is anything to remove
            root = root.next; // Remove the first node
            size--; // Decrement size
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (root != null) {
            Node temp = root; // Temp node for traversing list

            while (temp.next != null) {// While the next pointer of this node is not null
                temp = temp.next; // Move along the list
            }
            size--; // Decrement size
        }
        return null;
    }

    @Override
    public void addFirst(E e) {
        linkFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkLast(e);
    }

    public void linkFirst(E o) {
        Node first = root;
        Node newHead = new Node(o);

        if (first != null) {
            newHead.next = first;
        }
        root = newHead;

        size++; // Increment size counter
    }

    public void linkLast(E o) {
        if (root == null) {// If empty
            root = new Node(o); // Root becomes a new Node holding value o
        } else {
            // Go to end
            // Add this node
            Node temp = root;

            while (temp.next != null) { // While not at the last valid node
                temp = temp.next; // Move along the list
            }
            temp.next = new Node(o); // Add new node
        }

        size++; // Increment size counter
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("[");
        Node temp = root;

        while (temp != null) {
            returnString.append(temp.toString()).append(", ");
            temp = temp.next;
        }

        return returnString + "]";
    }

    private class Node {
        // Instance variables
        public E data;
        public Node next;

        public Node(E data) {
            this.data = data;
        }

        // Getters and Setters
        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private class SinglyLinkedListIterator implements Iterator<E> {
        Node curr = root;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E data = curr.data;
            curr = curr.next;
            return data;
        }
    }

}
