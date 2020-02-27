package projectCode20280;

public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000; // default array capacity
    private E[] data; // generic array used for storage
    private int t = 0; // index of the top element in stack

    // constructors
    public ArrayStack() { // constructs stack with default capacity
        this(CAPACITY);
    }

    public ArrayStack(int capacity) { // constructs stack with a given capacity
        data = (E[]) new Object[capacity]; // safe cast, compiler may give a warning
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    @Override
    public int size() {
        return (t);
    }

    @Override
    public boolean isEmpty() {
        return (t == 0);
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[t++] = e;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[t];
        data[t] = null;
        t--;
        return answer;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < t; i++) {
            output.append(data[i]).append(", ");
        }
        return output.toString();
    }

}