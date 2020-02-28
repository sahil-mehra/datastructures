package projectCode20280;

public class LinkedStack<E> implements Stack<E> {
  private SinglyLinkedList<E> stack;

  public LinkedStack() {
    this.stack = new SinglyLinkedList<>();
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  @Override
  public int size() {
    return this.stack.size;
  }

  @Override
  public boolean isEmpty() {
    return this.stack.isEmpty();
  }

  @Override
  public void push(E e) {
    stack.addFirst(e);
  }

  @Override
  public E top() {
    return stack.get(0);
  }

  @Override
  public E pop() {
    return stack.removeFirst();
  }

  public String toString() {
    StringBuilder output = new StringBuilder("[");
    for(int i = 0; i < this.stack.size(); i++) {
      output.append(stack.get(i)).append(",");
    }
    return output.toString();
  }

}
