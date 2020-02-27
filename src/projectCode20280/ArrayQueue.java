package project20280;

public class ArrayQueue<E> implements Queue<E> {
    public static final int CAPACITY = 1000; // default array capacity
    private int size = 0;
    private E[] data; // generic Array used for storage
    private int front; // pointer to front element

    // constructors
    public ArrayQueue() {
        this(CAPACITY);
    } // constructs stack with default capacity

    public ArrayQueue(int capacity) { // constructs stack with a given capacity
        data = (E[]) new Object[capacity]; // safe cast, compiler may give a warning
    }

    public static void main(String[] args) {
        // Tests
        ArrayQueue<Integer> test = new ArrayQueue<>(4);
        test.enqueue(1);
        System.out.println(test);
        test.enqueue(2);
        System.out.println(test);
        test.enqueue(3);
        System.out.println(test);
        test.dequeue();
        System.out.println(test);
        test.dequeue();
        System.out.println(test);
        test.dequeue();
    }

    /**
     *
     * @return This returns the current size of the array.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    // Adds element at the end of the queue
    public void enqueue(E e) throws IllegalStateException {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }
        int available = (front + size) % data.length; // modular arithmetic
        data[available] = e;
        size++;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[front];
        data[front] = null; // dereference for garbage collection
        front = (front + 1) % data.length;
        size--;
        return answer;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = front; i < (size + front); i++) {
            output.append(data[i]).append(", ");
        }
        return output.toString();
    }

}
