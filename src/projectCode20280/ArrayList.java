package projectCode20280;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
  // instance variables
  public static final int CAPACITY = 16; // default array capacity
  private int size = 0; // Current number of elements
  private E[] data; // Generic array used for storage

  /**
   * Constructs ArrayList with default capacity.
   */
  public ArrayList() {
    this(CAPACITY);
  }

  // Constructors

  /**
   * Constructs ArrayList with a user given capacity.
   *
   * @param capacity This is used to construct list.
   */
  public ArrayList(int capacity) {
    data = (E[]) new Object[capacity]; // Safe cast. Compiler may give warning
  }

  /**
   * Replaces the element at the specified position in this list with the specified element.
   * @param index The Position in the list
   * @param newVal The new Element
   * @return The old Element
   */
  public E set(int index, E newVal) {
    if(index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
    E old = data[index];
    data[index] = newVal;
    return old;
  }

  // Public Methods

  /**
   * This returns the number of elements in the ArrayList.
   *
   * @return This is the number of variables in the ArrayList.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * This returns if the ArrayList is empty.
   *
   * @return This returns true if the ArrayList is empty, otherwise it returns false.
   */
  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  /**
   * Returns (but does not remove) the element at index i.
   *
   * @param i TODO
   * @return TODO
   */
  @Override
  public E get(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size);
    return data[i];
  }

  /**
   * @param i TODO
   * @param e TODO
   * @throws IllegalStateException
   */
  @Override
  public void add(int i, E e) throws IllegalStateException {
    checkIndex(i, size + 1);
    if(size == data.length) {
      throw new IllegalStateException("Array is full");
    }
    if(size - i >= 0) System.arraycopy(data, i, data, i + 1, size - i);
    data[i] = e;
    size++;
  }

  /**
   * @param e TODO
   */
  @Override
  public void addFirst(E e) {
    add(0, e);
  }

  /**
   * @param e TODO
   */
  @Override
  public void addLast(E e) {
    add(size + 1, e);
  }

  /**
   * @param i TODO
   * @return TODO
   * @throws IllegalStateException TODO
   */
  @Override
  public E remove(int i) throws IllegalStateException {
    if(size == data.length) {
      throw new IllegalStateException("Array is full");
    }
    E temp = data[i];
    if(size - 1 - i >= 0) System.arraycopy(data, i + 1, data, i, size - 1 - i);
    data[size - 1] = null;
    size--;
    return temp;
  }

  /**
   * @return TODO
   */
  @Override
  public E removeFirst() {
    return remove(0);
  }

  /**
   * @return TODO
   */
  @Override
  public E removeLast() {
    return remove(size - 1);
  }

  @Override
  public Iterator<E> iterator() {
    return new ArrayListIterator();
  }

  // Utility Methods

  /**
   * @param i TODO
   * @param n TODO
   * @throws IndexOutOfBoundsException TODO
   */
  protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
    if(i < 0 || i > n) {
      throw new IndexOutOfBoundsException("Illegal index: " + i);
    }
  }

  protected void resize(int capacity) {
    E[] temp = (E[]) new Object[capacity]; // Safe cast, compiler may give warning
    if(size >= 0) System.arraycopy(data, 0, temp, 0, size);
    data = temp;
  }

  private class ArrayListIterator implements Iterator<E> {

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public E next() {
      return null;
    }
  }

}

