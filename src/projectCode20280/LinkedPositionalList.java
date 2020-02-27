package projectCode20280;

public class LinkedPositionalList<E> implements PositionalList<E>{

    private static class Node<E> implements Position<E>{
        private E element; // reference to the element stored at this node
        private Node<E> prev; // reference to the previous node
        private Node<E> next; // reference to the next node
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if(next == null){ // convention for defunct node
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
    } // End of nested class

    // Instance variables
    private Node<E> header; // header sentinel
    private Node<E> trailer; // trailer sentinel
    private int size = 0; // number of elements in the list

    // Constructs a new empty list
    public LinkedPositionalList(){
        header = new Node<>(null, null, null); // create header
        trailer = new Node<>(null, header, null); // trailer is preceded by header
        header.setNext(trailer); // header is followed by trailer
    }

    // Private Utilities

    /**
     * Validates the position and returns it as a node.
     * @param p The position.
     * @return The node.
     * @throws IllegalArgumentException Node is no longer in the list.
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node)){
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>)p; // Safe cast
        if(node.getNext() == null){
            throw new IllegalArgumentException("Node is no longer in the list");
        }
        return node;
    }

    /**
     * Returns the given node as a Position ( or null if it is a sentinel).
     * @param node The input node.
     * @return The position of the node.
     */
    private Position<E> position(Node<E> node){
        if(node == header || node == trailer){
            return null; // Do not expose users to sentinel
        }
        return node;
    }

    // Public accessor methods
    /**
     *
     * @return The number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Test if the list is empty.
     * @return true
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the first Position in the linked list( or null, if empty).
     * @return position(header.getNext())
     */
    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    /**
     * Returns the last Position in the linked list( or null, if empty).
     * @return position(trailer.getPrev())
     */
    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /**
     * Returns the Position immediately before Position p (or null, if p is first).
     * @param p The current position.
     * @return position(node.getPrev())
     * @throws IllegalStateException This comes from the validate method.
     */
    @Override
    public Position<E> before(Position<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /**
     * Returns the Position immediately after Position p (or null, if p is last).
     * @param p The current position.
     * @return position(node.getNext())
     * @throws IllegalStateException This comes from the validate method.
     */
    @Override
    public Position<E> after(Position<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    // Private utilities
    /**
     * Adds element e to the linked list between the given nodes
     * @param e The element to be added.
     * @param pred The previous node.
     * @param succ The next node.
     * @return the node that was added.
     */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }


    // Public update methods

    /**
     * Inserts element e at the front of the linked list and returns its new Position.
     * @param e element to be added.
     * @return calls the method addBetween with e, header, header.getNext() as parameters.
     */
    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /**
     * Inserts element e at the back of the linked list and returns its new Position.
     * @param e element to be added
     * @return calls the method addBetween with e, trailer.getPrev(), trailer as parameters
     */
    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Inserts element e immediately before Position p, and returns its new Position.
     * @param p The position after the new node is added.
     * @param e The element to be added.
     * @return Returns the new position.
     * @throws IllegalArgumentException From the validate() method.
     */
    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /**
     * Inserts element e immediately after Position p, and returns its new Position.
     * @param p The position before the new node.
     * @param e The element of the new node.
     * @return Returns the new position.
     * @throws IllegalArgumentException From the validate() method.
     */
    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    /**
     * Replaces the element stored at Position p and returns the replaced element.
     * @param p Specifies the position of the node.
     * @param e The new element.
     * @return Returns the old element.
     * @throws IllegalArgumentException From the LinkedList class.
     */
    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    /**
     * Removes the element stored at Position p and returns it (invalidating p).
     * @param p The position of the node.
     * @return The element stored at the node.
     * @throws IllegalArgumentException From various methods.
     */
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> pred = node.getPrev();
        Node<E> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        size--;
        E answer = node.getElement();
        node.setPrev(null);
        node.setNext(null);
        return answer;
    }

}
