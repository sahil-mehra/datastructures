package projectCode20280;

import java.util.Comparator;

/**
 * An implementation of a priority queue with an unsorted list.
 */
public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
  /**
   * A primary collection of priority queue entries
   */
  private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

  /**
   * Creates an empty priority queue based on the natural ordering of it's keys
   */
  public UnsortedPriorityQueue() {
    super();
  }

  /**
   * Creates an empty priority queue based on the comparator provided to order it's keys
   *
   * @param comp comparator defining the order of keys in the priority queue
   */
  public UnsortedPriorityQueue(Comparator<K> comp) {
    super(comp);
  }

  /**
   * Returns the Position of entry having minimal key
   */
  private Position<Entry<K, V>> findMin() { // Only called when non-empty
    Position<Entry<K, V>> small = list.first();
    for(Position<Entry<K, V>> walk : list.positions()) {
      if(compare(walk.getElement(), small.getElement()) < 0) {
        small = walk; // Found a smaller key
      }
    }
    return small;
  }

  /**
   * Inserts a key-value pair and returns the entry created.
   */
  public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key); // auxiliary key-checking method (could throw exception)
    Entry<K, V> newest = new PQEntry<>(key, value);
    list.addLast(newest);
    return newest;
  }

  /**
   * Returns (but does not remove) an entry with minimal key.
   */
  public Entry<K, V> min() {
    if(list.isEmpty()) return null;
    return findMin().getElement();
  }

  /**
   * Removes and returns the Entry with the minimal key
   */
  public Entry<K, V> removeMin() {
    if(list.isEmpty()) return null;
    return list.remove(findMin());
  }

  /**
   * Returns the number of elements in the priority queue
   */
  public int size() {
    return list.size();
  }


}