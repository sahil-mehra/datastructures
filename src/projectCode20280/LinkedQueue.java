package projectCode20280;

public class LinkedQueue<E> implements Queue<E> {
  private SinglyLinkedList<E> queue;

  public static void main(String[] args) {
    LinkedQueue<Integer> test = new LinkedQueue<>();
  }

  @Override
  public int size() {
    return this.queue.size;
  }

  @Override
  public boolean isEmpty() {
    return this.queue.isEmpty();
  }

  @Override
  public void enqueue(E e) {
    this.queue.addLast(e);
  }

  @Override
  public E first() {
    return this.queue.get(0);
  }

  @Override
  public E dequeue() {
    return this.queue.removeFirst();
  }

  public String toString() {
    return this.queue.toString();
  }

}
