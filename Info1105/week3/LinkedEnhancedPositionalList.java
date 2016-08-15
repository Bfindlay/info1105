package week3;
/*
 * Derived from materials:
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class LinkedEnhancedPositionalList<E> implements EnhancedPositionalList<E> {

	/**
	 * Implementation of a positional list stored as a doubly linked list.
	 *
	 * @author Michael T. Goodrich
	 * @author Roberto Tamassia
	 * @author Michael H. Goldwasser
	 */
	// ---------------- nested Node class ----------------
	/**
	 * Node of a doubly linked list, which stores a reference to its element and
	 * to both the previous and next node in the list.
	 */
	private static class Node<E> implements Position<E> {

		/** The element stored at this node */
		private E element; // reference to the element stored at this node

		/** A reference to the preceding node in the list */
		private Node<E> prev; // reference to the previous node in the list

		/** A reference to the subsequent node in the list */
		private Node<E> next; // reference to the subsequent node in the list

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e
		 *            the element to be stored
		 * @param p
		 *            reference to a node that should precede the new node
		 * @param n
		 *            reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		// public accessor methods
		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the stored element
		 * @throws IllegalStateException
		 *             if node not currently linked to others
		 */
		public E getElement() throws IllegalStateException {
			if (next == null) // convention for defunct node
				throw new IllegalStateException("Position no longer valid");
			return element;
		}

		/**
		 * Returns the node that precedes this one (or null if no such node).
		 * 
		 * @return the preceding node
		 */
		public Node<E> getPrev() {
			return prev;
		}

		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<E> getNext() {
			return next;
		}

		// Update methods
		/**
		 * Sets the node's element to the given element e.
		 * 
		 * @param e
		 *            the node's new element
		 */
		public void setElement(E e) {
			element = e;
		}

		/**
		 * Sets the node's previous reference to point to Node n.
		 * 
		 * @param p
		 *            the node that should precede this one
		 */
		public void setPrev(Node<E> p) {
			prev = p;
		}

		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n
		 *            the node that should follow this one
		 */
		public void setNext(Node<E> n) {
			next = n;
		}
	} // ----------- end of nested Node class -----------

	// instance variables of the LinkedPositionalList
	/** Sentinel node at the beginning of the list */
	private Node<E> header; // header sentinel

	/** Sentinel node at the end of the list */
	private Node<E> trailer; // trailer sentinel

	/** Number of elements in the list (not including sentinels) */
	private int size = 0; // number of elements in the list

	/** Constructs a new empty list. */
	public LinkedEnhancedPositionalList() {
		header = new Node<>(null, null, null); // create header
		trailer = new Node<>(null, header, null); // trailer is preceded by
													// header
		header.setNext(trailer); // header is followed by trailer
	}

	// private utilities
	/**
	 * Verifies that a Position belongs to the appropriate class, and is not one
	 * that has been previously removed. Note that our current implementation
	 * does not actually verify that the position belongs to this particular
	 * list instance.
	 *
	 * @param p
	 *            a Position (that should belong to this list)
	 * @return the underlying Node instance at that position
	 * @throws IllegalArgumentException
	 *             if an invalid position is detected
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getNext() == null) // convention for defunct node
			throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}

	/**
	 * Returns the given node as a Position, unless it is a sentinel, in which
	 * case null is returned (so as not to expose the sentinels to the user).
	 */
	private Position<E> position(Node<E> node) {
		if (node == header || node == trailer)
			return null; // do not expose user to the sentinels
		return node;
	}

	// public accessor methods
	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Tests whether the list is empty.
	 * 
	 * @return true if the list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the first Position in the list.
	 *
	 * @return the first Position in the list (or null, if empty)
	 */
	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	/**
	 * Returns the last Position in the list.
	 *
	 * @return the last Position in the list (or null, if empty)
	 */
	@Override
	public Position<E> last() {
		return position(trailer.getPrev());
	}

	/**
	 * Returns the Position immediately before Position p.
	 * 
	 * @param p
	 *            a Position of the list
	 * @return the Position of the preceding element (or null, if p is first)
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	/**
	 * Returns the Position immediately after Position p.
	 * 
	 * @param p
	 *            a Position of the list
	 * @return the Position of the following element (or null, if p is last)
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	// private utilities
	/**
	 * Adds an element to the linked list between the given nodes. The given
	 * predecessor and successor should be neighboring each other prior to the
	 * call.
	 *
	 * @param pred
	 *            node just before the location where the new element is
	 *            inserted
	 * @param succ
	 *            node just after the location where the new element is inserted
	 * @return the new element's node
	 */
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ); // create and link a new
													// node
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}

	// public update methods
	/**
	 * Inserts an element at the front of the list.
	 *
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 */
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext()); // just after the header
	}

	/**
	 * Inserts an element at the back of the list.
	 *
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 */
	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer); // just before the
															// trailer
	}

	/**
	 * Inserts an element immediately before the given Position.
	 *
	 * @param p
	 *            the Position before which the insertion takes place
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	/**
	 * Inserts an element immediately after the given Position.
	 *
	 * @param p
	 *            the Position after which the insertion takes place
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	/**
	 * Replaces the element stored at the given Position and returns the
	 * replaced element.
	 *
	 * @param p
	 *            the Position of the element to be replaced
	 * @param e
	 *            the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	/**
	 * Removes the element stored at the given Position and returns it. The
	 * given position is invalidated as a result.
	 *
	 * @param p
	 *            the Position of the element to be removed
	 * @return the removed element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null); // help with garbage collection
		node.setNext(null); // and convention for defunct node
		node.setPrev(null);
		return answer;
	}

	// ---------------- INFO1105 code starts here ------------------
	// You should not need to modify any code above this point!
	// -------------------------------------------------------------

	/**
	 * Find a given element in the list
	 * 
	 * @param value
	 *            the value of the element to search for
	 * @return the Position representing the location of the element or null, if
	 *         the element is not in the list
	 */

	@Override
	public Position<E> search(E e) {
		// TODO Auto-generated method stub
		Position<E> current = this.first();
		while (current != null) {
			if (current.getElement().equals(e))
				return current;
			current = this.after(current);
		}
		return null;
	}

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
	@Override
	public Position<E> addBefore(Position<E> p, PositionalList<E> sublist) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Position<E> current = sublist.first();

		for (int i = 0; i < sublist.size(); i++) {
			this.addBefore(p, current.getElement());
			current = this.after(current);
		}
		return this.first();

	}

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
	@Override
	public Position<E> addAfter(Position<E> p, PositionalList<E> sublist) throws IllegalArgumentException {
		Position<E> current = sublist.last();

		for (int i = 0; i < sublist.size(); i++) {
			this.addAfter(p, current.getElement());
			current = this.before(current);
		}
		return this.first();


	}

	

	/**
	 * Inserts a list of elements at the front of the list.
	 *
	 * @param sublist
	 *            the PositionalList containing the new elements
	 * @return the Position representing the location of the first new element
	 */
	@Override
	public Position<E> addFirst(PositionalList<E> sublist) {
		// TODO Auto-generated method stub
		Position<E> current = sublist.last();

		for (int i = 0; i < sublist.size(); i++) {
			this.addFirst(current.getElement());
			current = this.before(current);
		}
		return this.first();
	}

	/**
	 * Inserts a list of elements at the back of the list.
	 *
	 * @param sublist
	 *            the PositionalList containing the new elements
	 * @return the Position representing the location of the first new element
	 */
	@Override
	public Position<E> addLast(PositionalList<E> sublist) {
		// TODO Auto-generated method stub
		Position<E> current = sublist.first();

		for (int i = 0; i < sublist.size(); i++) {
			this.addLast(current.getElement());
			current = this.after(current);
		}
		return this.first();
	}

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
	@Override
	public int distance(Position<E> a, Position<E> b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Position<E> current = this.first();
		int size = this.size();
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (current.equals(a)) {
				for (int j = i; j < size; j++) {
					if (current.equals(b))
						return count;
					current = this.after(current);
					count++;
				}
			} else if (current.equals(b)) {
				for (int k = i; k < size; k++) {
					if (current.equals(a)) {
						return count;
					}
					current = this.after(current);
					count--;
				}
			}
			current = this.after(current);
		}
		return count;
	}
}
