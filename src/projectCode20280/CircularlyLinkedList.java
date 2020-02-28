package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {
  private Node tail = null;
  private int size = 0;

  public static void main(String[] args) {
    CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
    for(int i = 10; i < 20; ++i) {
      ll.addLast(i);
    }

    System.out.println("1: " + ll);

    ll.removeFirst();
    System.out.println("2: " + ll);

    ll.removeLast();

    ll.rotate();
    System.out.println("3: " + ll);

    ll.removeFirst();
    ll.rotate();
    System.out.println("4: " + ll);

    ll.removeLast();
    ll.rotate();
    System.out.println("5: " + ll);

    for(Integer e : ll) {
      System.out.println("value: " + e);
    }

  }

  /**
   * @return The current size of the the list.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * @return If the list is empty, return true. ELse return false.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * @param i
   * @return
   */
  @Override
  public E get(int i) {
    Node current = tail.next; // Node for iterating through list

    for(int j = 0; j < i; j++) {// Iterate until the ith Node
      current = current.next; // Move to next Node
    }

    return current.data; // Return ith Nodes data
  }

  /**
   * @param i
   * @param e
   */
  @Override
  public void add(int i, E e) {
    Node prev = tail;
    Node current = tail.next; // Node for iterating through list

    for(int j = 0; j < i; j++) {// Iterate until the ith Node
      prev = prev.next; // Move prev to next Node
      current = current.next; // Move to next Node
    }

    prev.next = new Node(e, current); // Update previous Node's next pointer to point to the new Node
    size++; // Increment size by one
  }

  /**
   * @param i
   * @return
   */
  @Override
  public E remove(int i) {
    if(size != 0) {
      Node prev = tail;
      Node current = tail.next; // Node for iterating through list

      for(int j = 0; j < i; j++) {// Iterate until the ith Node
        prev = prev.next; // Move prev to next Node
        current = current.next; // Move to next Node
      }

      prev.next = current.next; // Prev now points to the node after current
      size--; // Reduce size by one

      return current.data; // Return the data in current
    } else {
      return null; // Nothing to remove
    }
  }


  /**
   * @return
   */
  @Override
  public E removeFirst() {
    if(size != 0) {// If not empty
      Node current = tail.next;
      tail.next = current.next; // Remove link to current Node
      size--; // Decrement size by one
      return current.data;
    } else {// Empty list, nothing to remove
      return null;
    }
  }

  /**
   * @return
   */
  @Override
  public E removeLast() {
    if(size != 0) { // If not empty
      Node current = tail.next;

      while(current.next != tail) {// While not at the Node before the tail
        current = current.next;
      }

      E data = tail.data;
      current.next = tail.next; // Set the current's next
      tail = current; // Update the tail

      size--; // Decrement size
      return data;
    } else { // Empty list, nothing to remove
      return null;
    }
  }

  /**
   * @return
   */
  @Override
  public Iterator<E> iterator() {
    return new CircularlyLinkedListIterator();
  }

  /**
   * @param e
   */
  @Override
  public void addFirst(E e) {
    if(size == 0) {// Empty list, tail is currently null
      tail = new Node(e, null); // Create new Node
      tail.next = tail; // Link to itself
    } else {
      tail.next = new Node(e, tail.next); // Update tail pointer
    }
    size++; // Increment size
  }

  /**
   * @param e
   */
  @Override
  public void addLast(E e) {
    addFirst(e); // Add to the beginning of the list.
    tail = tail.next; // Move the tail forward one
  }

  /**
   *
   */
  public void rotate() {
    if(tail != null) { // If list is not empty
      tail = tail.next; // Move the tail
    }
  }

  /**
   * @return
   */
  public String toString() {
    Node current = tail.next; // Head of list
    StringBuilder list = new StringBuilder("[");

    while(current != tail) {
      list.append(current.data).append(", ");
      current = current.next;
    }

    return list.toString() + tail.data + "]";
  }

  private class Node {
    // Instance variables
    public E data;
    public Node next;

    public Node(E data) {
      this.data = data;
    }

    public Node(E data, Node next) {
      this.data = data;
      this.next = next;
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
      if(this != null) {
        return data.toString();
      } else {
        return "NULL";
      }
    }
  }

  private class CircularlyLinkedListIterator extends CircularlyLinkedList<E> implements Iterator<E> {
    Node prev = null;
    Node current = tail.next;

    @Override
    public boolean hasNext() {
      return !((prev == tail) && (current == tail.next));
    }

    @Override
    public E next() {
      E data = current.data; // Store current data
      prev = current; // Set prev to point to current Node
      current = current.next; // Set current to point to next Node
      return data;
    }
  }
}
