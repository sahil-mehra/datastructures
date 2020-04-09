package projectCode20280;

import java.util.Comparator;

/**
 * An implementation of a priority queue with a sorted list
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
  /**
   * primary collection of priority queue
   */
  private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

  /**
   * Creates an empty positional list based on the natural ordering of it's keys
   */
  public SortedPriorityQueue() {
    super();
  }

  /**
   * Creates an empty positional list using the given Comparator to order it's keys
   * @param comp comparator defining the order of keys in the priority queue
   */
  public SortedPriorityQueue(Comparator<K> comp) {
    super(comp);
  }

  public Entry<K, V> insert(K key, V value) {
    checkKey(key);
    Entry<K, V> newest = new PQEntry<>(key, value);
    Position<Entry<K, V>> walk = list.last();
    while(walk != null && compare(newest, walk.getElement()) < 0) {
      walk = list.before(walk);
    }
    if(walk == null) {
      list.addFirst(newest);
    } else {
      list.addAfter(walk, newest);
    }
    return newest;
  }

  /**
   * Returns (but does not remove) an entry with the minimal key
   */
  public Entry<K, V> min() {
    if(list.isEmpty()) return null;
    return list.first().getElement();
  }

  /**
   * Removes and returns the entry with the minimal key
   */
  public Entry<K, V> removeMin() {
    if(list.isEmpty()) return null;
    return list.remove(list.first());
  }

  /**
   * Remove and returns the entry with the minimal key
   */
  public int size() {
    return list.size();
  }


}