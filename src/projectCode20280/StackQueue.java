/*
Java program to create a queue
using two stacks.
 This is only the pseudocode for the algorithm. Only the enqueue() and dequeue() methods are implemented.
 */

package projectCode20280;

public class StackQueue<E>{
    Stack<E> stack1; // This holds the queue most of the time
    Stack<E> stack2; // The queue is passed to the this when performing operations.


    public void enqueue(E input){
        // Move all the elements from stack1 to stack2
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        // Push the input into stack1
        stack1.push(input);
        // Push everything back into stack1 from stack2
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }

    public E dequeue() throws IllegalStateException {
        E out;
        if(stack1.isEmpty()){
            throw new IllegalStateException("Queue is empty");
        } else {
            out = stack1.pop();
        }
        return out;
    }

    public int size(){
        return this.stack1.size();
    }
    public boolean isEmpty(){
        return this.stack1.isEmpty();
    }

    public E first(){
        return this.stack1.top();
    }

    public String toString(){
        return this.stack1.toString();
    }

    public static void main(String[] args){

    }


}
