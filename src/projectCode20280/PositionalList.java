package projectCode20280;

public interface PositionalList<E> {
  /**
   * Returns the number of elements in a list .
   *
   * @return The number of elements in the list.
   */
  int size();

  /**
   * Tests if the list is empty.
   *
   * @return True if the queue is empty, false otherwise.
   */
  boolean isEmpty();

  /**
   * Returns the first Position in the list (or null, if empty).
   *
   * @return The first position in the list.
   */
  Position<E> first();

  /**
   * Returns the last Position in the list (or null, if empty).
   *
   * @return the last Position in the list (or null, if empty).
   */
  Position<E> last();

  /**
   * Returns the Position immediately before Position p (or null, if p is first).
   *
   * @param p The position for the current node.
   * @return The Position immediately before Position p (or null, if p is first).
   * @throws IllegalStateException
   */
  Position<E> before(Position<E> p) throws IllegalStateException;

  /**
   * Returns the Position immediately after Position p (or null, if p is last).
   *
   * @param p The current position.
   * @return The position after the @param.
   * @throws IllegalStateException If the @param is not a valid Position.
   */
  Position<E> after(Position<E> p) throws IllegalStateException;

  /**
   * Inserts element e at the front of the the list and returns its new Position.
   *
   * @param e It's the element to be added.
   * @return The new position.
   */
  Position<E> addFirst(E e);

  /**
   * Inserts element e at the end of the list and return it's new Position.
   *
   * @param e It's the element to be added.
   * @return The new position.
   */
  Position<E> addLast(E e);

  /**
   * Inserts element e immediately before Position p and returns its new Position.
   *
   * @param p The current Position
   * @param e The element to be added
   * @return The new Position
   * @throws IllegalArgumentException
   */
  Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

  /**
   * Inserts element e immediately after Position p and returns it's new Position.
   *
   * @param p The input Position.
   * @param e The input element.
   * @return The new Position.
   * @throws IllegalArgumentException If the either of the parameters are invalid, it throws an error
   */
  Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

  /**
   * Replaces the element stored at Position p and returns the replaced element.
   *
   * @param p
   * @param e
   * @return
   * @throws IllegalArgumentException
   */
  E set(Position<E> p, E e) throws IllegalArgumentException;

  /**
   * Removes the element stored at Position p and returns it(invalidating p).
   *
   * @param p
   * @return
   * @throws IllegalArgumentException
   */
  E remove(Position<E> p) throws IllegalArgumentException;
}
