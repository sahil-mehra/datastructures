package projectCode20280;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {
    CircularlyLinkedList<E> queue;

    public LinkedCircularQueue() {
        this.queue = new CircularlyLinkedList<>();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public void rotate() {
        this.queue.rotate();
    }

    @Override
    public int size() {
        return this.queue.size();
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
