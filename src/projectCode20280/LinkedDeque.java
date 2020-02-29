package projectCode20280;

public class LinkedDeque<E> implements Deque<E> {
  public int size;
  private DoublyLinkedList<E> queue;


  public static void main(String[] args) {
    LinkedDeque<Integer> test;
    test = new LinkedDeque<>();
    test.addFirst(1);
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
    return (this.queue.get(0));
  }

  @Override
  public E last() {
    return (this.queue.get(size));
  }

  @Override
  public void addFirst(E e) {
    this.queue.addFirst(e);
  }

  @Override
  public void addLast(E e) {
    this.queue.addLast(e);
  }

  @Override
  public E removeFirst() {
    return (this.queue.removeFirst());
  }

  @Override
  public E removeLast() {
    return (this.queue.removeLast());
  }

  public String toString() {
    return (this.queue.toString());
  }

}
