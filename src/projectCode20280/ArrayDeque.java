package projectCode20280;

/**
 * Implementing an ArrayDeque using a circular array.
 *
 * @param <E> General type that is specified when using the constructor
 */

public class ArrayDeque<E> implements Deque<E> {
  public static final int CAPACITY = 1000;
  private int size = 0;
  private E[] data; // generic Array used for storage
  private int head; // pointer to first element
  private int tail; // pointer to last element

  /**
   * General Constructor that constructs an ArrayDeque with a generic capacity
   */
  public ArrayDeque() {
    this(CAPACITY);
  }

  /**
   * Constructs an ArrayDeque with a given capacity
   *
   * @param capacity This is the size of the initialized array.
   * @throws IllegalArgumentException This is due to the capacity being less than 0
   */
  public ArrayDeque(int capacity) throws IllegalArgumentException {
    if(capacity < 0) {
      throw new IllegalArgumentException("Invalid Capacity");
    }
    data = (E[]) new Object[capacity]; // safe cast, compiler may give a warning
  }


  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  @Override
  public E first() {
    return isEmpty() ? null : data[head]; // Ternary operator; The conditional operator or ternary operator ?: is shorthand for if-then-else statement.
  }

  @Override
  public E last() {
    return isEmpty() ? null : data[tail]; // Ternary operator; The conditional operator or ternary operator ?: is shorthand for if-then-else statement.
  }

  /**
   * Adds a new element to the top of the Array
   *
   * @param e the new element
   * @throws IllegalStateException This throws an error if the array is full
   */
  @Override
  public void addFirst(E e) throws IllegalStateException {
    if(size == data.length) {
      throw new IllegalStateException("Queue is full");
    }
    // Check if the ArrayDeque is empty, then the first e goes to the first element in the array.
    if(isEmpty()) {
      data[head] = e;
    } else {
      head = (head - 1 + data.length) % data.length;
      data[head] = e;
    }
  }

  /**
   * Adds a new element to the end of the ArrayDeque
   *
   * @param e the new element
   * @throws IllegalStateException This throws an error if the array is full
   */
  @Override
  public void addLast(E e) throws IllegalStateException {
    if(size == data.length) {
      throw new IllegalStateException("Queue is full");
    }
    tail = tail % data.length;
    data[tail] = e;
    size++;
  }

  /**
   * This method removes the first element in the Deque
   *
   * @return The element that is being removed is returned
   */
  @Override
  public E removeFirst() throws IllegalStateException {
    if(isEmpty()) {
      throw new IllegalStateException("The deque is empty");
    }
    E output = data[head];
    data[head] = null; // dereference for garbage collection
    head = (head + 1) % data.length;
    size--;
    return output;
  }

  /**
   * This method removes the last element in the Deque
   *
   * @return The element that is being removed is returned
   */
  @Override
  public E removeLast() throws IllegalStateException {
    if(isEmpty()) {
      throw new IllegalStateException("The deque is empty");
    }
    E output = data[tail];
    data[tail] = null; // dereference for garbage collection
    tail = (tail - 1) % data.length;
    size--;
    return output;
  }

  public String toString() {
    StringBuilder output = new StringBuilder();
    for(int i = head; i < (size + head); i++) {
      output.append(data[i]).append(", ");
    }
    return output.toString();
  }
}
