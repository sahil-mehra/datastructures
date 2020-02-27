package project20280;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {
    private Node head;
    private Node tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node(null, null, null);
        tail = new Node(null, head, null);
        head.next = tail;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }

    private void addBetween(E e, Node predecessor, Node successor) {
        Node newNode = new Node(e, predecessor, successor);
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    } // Returns the size

    @Override
    public boolean isEmpty() {
        return size == 0; // If the list is empty, it has 0 elements
    }

    @Override
    public E get(int i) {
        Node current = head.next; // New Node for traversing

        for (int j = 0; j < i; j++) { // Until you reach the ith Node
            current = current.next; // Traverse list
        }

        return current.data; // Return the current (ith) Nodes data
    }

    @Override
    public void add(int i, E e) {
        // 0 -> 1 -> 2 -> 3
        // add(2, e) -> 0
        // 0 -> 1 -> E -> 2 -> 3
        // Take 2, addBetween(e, 2.prev, 2)

        Node current = head.next; // New Node for traversing

        for (int j = 0; j < i; j++) { // Until you reach the ith Node
            current = current.next; // Traverse list
        }

        addBetween(e, current.prev, current); // Add to list
    }

    @Override
    public E remove(int i) {
        Node current = head.next; // Set to first node

        for (int j = 0; j < i; j++) { // Until you get to the ith Node
            current = current.next; // Traverse list
        }
        return remove(current); // Remove the current node, and return its data
    }

    public E remove(Node n) {
        Node prev = n.prev; // Predecessor
        Node next = n.next; // Successor

        prev.next = next; // Set next.prev to point to prev node
        next.prev = prev; // Set prev.next to point to next node

        size--; // Decrease size
        return n.data; // Return the data held in n
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) { // If empty
            return null; // Nothing to remove
        } else {
            return remove(head.next); // Remove first element
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty()) { // If empty
            return null; // Nothing to remove
        } else {
            return remove(tail.prev); // Remove last element
        }
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.next);
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.prev, tail);
    }

    @Override
    public String toString() {
        Node current = head.next; // Node for iterating through list
        StringBuilder returnString = new StringBuilder("["); // String to be returned

        while (current.next != null) {// Iterate through list
            returnString.append(current.data).append(", "); // Add data + comma
            current = current.next; // Move to next node
        }

        return returnString + "null]"; // Close the list
    }

    private class Node {
        // Instance variables
        public E data;
        public Node prev;
        public Node next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            if (this != null) {
                return data.toString();
            } else {
                return "NULL";
            }
        }
    }

    private class DoublyLinkedListIterator implements Iterator<E> {
        Node current = head.next;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}
