package projectCode20280;

import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
  /**
   * primary collection of priority queue entries
   */
  protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

  /**
   * Creates an empty priority queue based on the natural ordering of its keys.
   */
  public HeapPriorityQueue() {
    super();
  }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   *
   * @param comp comparator defining the order of keys in the priority queue
   */
  public HeapPriorityQueue(Comparator<K> comp) {
    super(comp);
  }

  /**
   * Creates a priority queue initialized with the respective
   * key-value pairs.  The two arrays given will be paired
   * element-by-element. They are presumed to have the same
   * length. (If not, entries will be created only up to the length of
   * the shorter of the arrays)
   *
   * @param keys   an array of the initial keys for the priority queue
   * @param values an array of the initial values for the priority queue
   */
  public HeapPriorityQueue(K[] keys, V[] values) {
    super();
    for(int j = 0; j < Math.min(keys.length, values.length); j++) {
      heap.addLast(new PQEntry<K, V>(keys[j], values[j]));
      heapify();
    }
  }

  // protected utilities
  protected int parent(int j) { // truncating division
    return (j - 1) / 2;
  }

  protected int left(int j) {
    return 2 * j + 1;
  }

  protected int right(int j) {
    return 2 * j + 2;
  }

  protected boolean hasLeft(int j) {
    return left(j) < heap.size();
  }

  protected boolean hasRight(int j) {
    return right(j) < heap.size();
  }

  /**
   * Exchanges the entries at indices i and j of the array list.
   */
  protected void swap(int i, int j) {
    Entry<K, V> temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  /**
   * Moves the entry at index j higher, if necessary, to restore the heap property.
   */
  protected void upheap(int j) {
    while(j > 0) {
      int p = parent(j); // Continue until you reach root, or break the loop
      if(compare(heap.get(j), heap.get(p)) > 0) {
        break; // Heap property verified
      }
      swap(j, p);
      j = p; // Continue from the parent's location
    }
  }

  /**
   * Moves the entry at index j lower, if necessary, to restore the heap property.
   */
  protected void downheap(int j) {
    while(hasLeft(j)) { // Continue till bottom, or break
      int leftIndex = left(j);
      int smallChildLeftIndex = leftIndex; // although right may be smaller
      if(hasRight(j)) {
        int rightIndex = right(j);
        if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
          smallChildLeftIndex = rightIndex; // right is smaller
        }
        if(compare(heap.get(smallChildLeftIndex), heap.get(j)) > 0) {
          break; // heap property has been restored
        }
        swap(j, smallChildLeftIndex);
        j = smallChildLeftIndex; // Continue at the position of the child
      }
    }
  }

  /**
   * Performs a bottom-up construction of the heap in linear time.
   */
  protected void heapify() {
    int startIndex = parent(size() - 1); // Start at PARENT of last entry
    for(int j = startIndex; j > 0; j--) { // Loop until processing the root
      downheap(j);
    }
  }

  // public methods

  /**
   * Returns the number of items in the priority queue.
   *
   * @return number of items
   */
  @Override
  public int size() {
    return heap.size();
  }

  /**
   * Returns (but does not remove) an entry with minimal key.
   *
   * @return entry having a minimal key (or null if empty)
   */
  @Override
  public Entry<K, V> min() {
    if(heap.isEmpty()) {
      return null;
    }
    return heap.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   *
   * @param key   the key of the new entry
   * @param value the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  @Override
  public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key); // Auxiliary key-checking method (could throw an exception)
    Entry<K, V> newest = new PQEntry<>(key, value);
    heap.addLast(newest); // Add to the end of the list
    upheap(heap.size() - 1); // upheap newly added entry
    return newest;
  }

  /**
   * Removes and returns an entry with minimal key.
   *
   * @return the removed entry (or null if empty)
   */
  @Override
  public Entry<K, V> removeMin() {
    if(heap.isEmpty()) {
      return null;
    }
    Entry<K, V> answer = heap.get(0);
    swap(0, heap.size() - 1); // put minimum item to the end
    heap.remove(heap.size() - 1); // then remove from list
    downheap(0); // fix the root
    return answer;
  }

  @Override
  public String toString() {
    return "HeapPriorityQueue{" +
            "heap=" + heap +
            '}';
  }

  /**
   * Used for debugging purposes only
   */
  private void sanityCheck() {
    for(int j = 0; j < heap.size(); j++) {
      int left = left(j);
      int right = right(j);
      if(left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
        System.out.println("Invalid left child relationship");
      if(right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
        System.out.println("Invalid right child relationship");
    }
  }
}

