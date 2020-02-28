package projectCode20280;

public class BoundedStack<E> implements Stack<E> {
  public static final int CAPACITY = 20;
  private int max_size;

  private ArrayStack<E> stack; // Uses an ArrayStack to create a stack and store all the data.

  public BoundedStack() { // constructs stack with default capacity
    this.stack = new ArrayStack<>(CAPACITY);
    this.max_size = CAPACITY;
  }

  public BoundedStack(int capacity) { // constructs stack with given capacity
    this.stack = new ArrayStack<>(capacity);
    this.max_size = capacity;
  }

  public static void main(String[] args) {
    // TODO
  }

  @Override
  public int size() {
    return this.stack.size();
  }

  @Override
  public boolean isEmpty() {
    return this.stack.isEmpty();
  }

  @Override
  public void push(E e) throws StackOverflowError { // Throws an error if the stack is full, else pushes the element to the stack
    if(this.stack.size() == this.max_size) {
      throw new StackOverflowError("Stack is full");
    } else {
      this.stack.push(e);
    }
  }

  @Override
  public E top() {
    return this.stack.top();
  }

  @Override
  public E pop() {
    return this.stack.pop();
  }

  public String toString() {
    return this.stack.toString();
  }

}
