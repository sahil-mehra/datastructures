package projectCode20280;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {


  /**
   * The root of the binary tree
   */
  protected Node<E> root = null;     // root of the tree
  /**
   * The number of nodes in the binary tree
   */
  private int size = 0;              // number of nodes in the tree

  // LinkedBinaryTree instance variables
  /**
   * Constructs an empty binary tree.
   */
  public LinkedBinaryTree() {
  }      // constructs an empty binary tree

  public static void main(String[] args) {
    LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();

    int[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
    for(int i : arr) {
      bt.insert(i);
    }
    System.out.println("bt: " + bt.size() + " " + bt);

  }

  // constructor

  /**
   * Factory function to create a new node storing element e.
   */
  protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
    return new Node<>(e, parent, left, right);
  }

  // nonpublic utility

  /**
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p a Position (that should belong to this tree)
   * @return the underlying Node instance for the position
   * @throws IllegalArgumentException if an invalid position is detected
   */
  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if(!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if(node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  // accessor methods (not already implemented in AbstractBinaryTree)

  /**
   * Returns the number of nodes in the tree.
   *
   * @return number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns the root Position of the tree (or null if tree is empty).
   *
   * @return root Position of the tree (or null if tree is empty)
   */
  @Override
  public Position<E> root() {
    return root;
  }

  /**
   * Returns the Position of p's parent (or null if p is root).
   *
   * @param p A valid Position within the tree
   * @return Position of p's parent (or null if p is root)
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getParent();
  }

  /**
   * Returns the Position of p's left child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the left child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> left(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getLeft();
  }

  /**
   * Returns the Position of p's right child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the right child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> right(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getRight();
  }

  // update methods supported by this class

  /**
   * Places element e at the root of an empty tree and returns its new Position.
   *
   * @param e the new element
   * @return the Position of the new element
   * @throws IllegalStateException if the tree is not empty
   */
  public Position<E> addRoot(E e) throws IllegalStateException {
    if(!isEmpty()) {
      throw new IllegalStateException("Tree is not empty");
    }
    root = createNode(e, null, null, null);
    size++;
    return root;
  }

  public void insert(E e) {
    //recursively add from root
    Node<E> temp = addRecursive(root, e);
    ++size;
  }

  /**
   * recursively add Nodes to binary tree in proper position
   * Add Nodes to the BinaryTree in a sorted order
   * This involves checking the value against the current position,
   * and inserting the node in the left or right sub tree
   * This method is recursive.
   *
   * @param p The Node that is being used as the parent.
   * @param e The element to be inserted
   * @return The node that is added
   * @throws IllegalArgumentException
   */
  private Node<E> addRecursive(Node<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    if(e.equals(p.getElement())) {
      return p;
    } else if(e.compareTo(p.getElement()) < 0) {
      if(p.getLeft() == null) {
        node = (Node<E>) addLeft(p, e);
      } else {
        addRecursive(p.getLeft(), e);
      }
    } else if(e.compareTo(p.getElement()) > 0) {
      if(p.getRight() == null) {
        node = (Node<E>) addRight(p, e);
      } else {
        addRecursive(p.getRight(), e);
      }
    } else {
      throw new IllegalArgumentException("For some reason this is not valid.");
    }
    return node;
  }


  /**
   * Creates a new left child of Position p storing element e and returns its Position.
   *
   * @param p the Position to the left of which the new element is inserted
   * @param e the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p already has a left child
   */
  public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if(parent.getLeft() != null) {
      throw new IllegalArgumentException("p already has a left child");
    }
    Node<E> child = createNode(e, parent, null, null);
    parent.setLeft(child);
    size++;
    return child;
  }

  /**
   * Creates a new right child of Position p storing element e and returns its Position.
   *
   * @param p the Position to the right of which the new element is inserted
   * @param e the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p already has a right child
   */
  public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if(parent.getRight() != null) {
      throw new IllegalArgumentException("p already has a right child");
    }
    Node<E> child = createNode(e, parent, null, null);
    parent.setRight(child);
    size++;
    return child;
  }

  /**
   * Replaces the element at Position p with element e and returns the replaced element.
   *
   * @param p the relevant Position
   * @param e the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E temp = node.getElement();
    node.setElement(e);
    return temp;
  }

  /**
   * Attaches trees t1 and t2, respectively, as the left and right subtree of the
   * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
   *
   * @param p  a leaf of the tree
   * @param t1 an independent tree whose structure becomes the left child of p
   * @param t2 an independent tree whose structure becomes the right child of p
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p is not a leaf
   */
  public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
    Node<E> node = validate(p);
    if(isInternal(p)) {
      throw new IllegalArgumentException("p must be leaf");
    }
    size += t1.size() + t2.size();
    // attach t1 as the left subtree of node
    if(!t1.isEmpty()) {
      t1.root.setParent(node);
      node.setLeft(t1.root);
      t1.root = null;
      t1.size = 0;
    }
    // attach t2 as the right subtree of the node
    if(!t2.isEmpty()) {
      t2.root.setParent(node);
      node.setLeft(t2.root);
      t2.root = null;
      t2.size = 0;
    }
  }

  /**
   * Removes the node at Position p and replaces it with its child, if any.
   *
   * @param p the relevant Position
   * @return element that was removed
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p has two children.
   */
  public E remove(Position<E> p) throws IllegalArgumentException {
    //TODO
    Node<E> node = validate(p);
    if(numChildren(p) == 2) {
      throw new IllegalArgumentException("p has 2 children");
    }
    Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
    if(child != null) {
      child.setParent(node.getParent()); // child's grandparent becomes child's parent
    }
    if(node == root) {
      root = child; // child becomes root
    } else {
      Node<E> parent = node.getParent();
      if(node == parent.getLeft()) {
        parent.setLeft(child);
      } else {
        parent.setRight(child);
      }
    }
    size--;
    E temp = node.getElement();
    // Help garbage collection
    node.setElement(null);
    node.setRight(null);
    node.setLeft(null);
    node.setParent(null);
    return temp;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for(Position<E> p : positions()) {
      sb.append(p.getElement());
      sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * Nested static class for a binary tree node.
   */
  protected static class Node<E> implements Position<E> {
    private E element; // An element to be inserted at this position
    private Node<E> parent; // a reference to the parent node, if any
    private Node<E> left; // a reference to the left child, if any
    private Node<E> right; // a reference to the right child, if any

    // Constructs a node with the given element and neighbors
    public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
      element = e;
      parent = above;
      left = leftChild;
      right = rightChild;
    }

    @Override
    public E getElement() {
      return element;
    }

    public void setElement(E element) {
      this.element = element;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    @Override
    public String toString() {
      return "Node{}";
    }
  } // End of nested node class
} 

