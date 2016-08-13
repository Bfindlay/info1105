package week3;

public interface EnhancedPositionalList<E> extends PositionalList<E> {

	/**
	 * Find a given element in the list
	 * 
	 * @param value
	 *            the value of the element to search for
	 * @return the Position representing the location of the element or null, if
	 *         the element is not in the list
	 */
	Position<E> search(E value);

	/**
	 * Inserts a list of elements immediately before the given Position.
	 *
	 * @param p
	 *            the Position before which the insertion takes place
	 * @param sublist
	 *            the PositionalList containing the new elements
	 * @return the Position representing the location of the first new element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	Position<E> addBefore(Position<E> p, PositionalList<E> sublist) throws IllegalArgumentException;

	/**
	 * Inserts a list of elements immediately after the given Position.
	 *
	 * @param p
	 *            the Position after which the insertion takes place
	 * @param sublist
	 *            the PositionalList containing the new elements
	 * @return the Position representing the location of the first new element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	Position<E> addAfter(Position<E> p, PositionalList<E> sublist) throws IllegalArgumentException;

	/**
	 * Inserts a list of elements at the front of the list.
	 *
	 * @param sublist
	 *            the PositionalList containing the new elements
	 * @return the Position representing the location of the first new element
	 */
	Position<E> addFirst(PositionalList<E> sublist);

	/**
	 * Inserts a list of elements at the back of the list.
	 *
	 * @param sublist
	 *            the PositionalList containing the new elements
	 * @return the Position representing the location of the first new element
	 */
	Position<E> addLast(PositionalList<E> sublist);

	/**
	 * Calculate the distance between two positions in the list
	 * 
	 * @param a
	 *            a Position in the list
	 * @param b
	 *            a Position in the list
	 * @return the number of links that must be traversed to get from a to b If
	 *         b is before a in the list, a negative number corresponding to the
	 *         number of links that must be traversed to get from b to a. e.g.
	 *         -a---b- -> 4 -b--a-- -> -3
	 * @throws IllegalArgumentException
	 *             if a or b are not valid positions for this list
	 */
	int distance(Position<E> a, Position<E> b) throws IllegalArgumentException;

}
