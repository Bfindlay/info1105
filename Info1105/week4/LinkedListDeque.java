package week4;

public class LinkedListDeque<E> implements Deque<E> {
	public static void main(String[] args) {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("Test");
		System.out.println(deque.first().getData());
	}

	// NESTED NODE CLASS
	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		public Node(E data, Node<E> p, Node<E> n) {
			this.data = data;
			this.next = n;
			this.prev = p;
		}

		public Node<E> getPrev() {
			return this.prev;
		}

		public Node<E> getNext() {
			return this.next;
		}

		public E getData() {
			return this.data;
		}

		public void setPrev(Node<E> p) {
			this.prev = p;
		}

		public void setNext(Node<E> n) {
			this.next = n;
		}
	}

	// BEGIN DEQUE IMPLEMENTATION

	private int size;
	private Node<E> head;
	private Node<E> tail;

	public LinkedListDeque() {
		this.head = new Node<>(null, null, null);
		this.tail = new Node<>(null, head, null);
		head.next = tail;
		this.size = 0;
	}

	public Node<E> first() {
		return this.head.next;
	}

	public Node<E> last() {
		return this.tail.prev;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {

		return (size() == 0);
	}

	@Override
	public E peekFirst() {
		return (head.next != null) ? head.next.getData() : null;
	}

	@Override
	public E peekLast() {
		return (tail.prev != null) ? tail.prev.getData() : null;
	}

	@Override
	public void addFirst(E element) {
		Node<E> newNode = new Node<E>(element, this.head, this.head.prev);
		head.next.prev = newNode;
		head.next = newNode;
		size++;

	}

	@Override
	public void addLast(E element) {
		Node<E> newNode = new Node<E>(element, this.tail.prev, this.tail);
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
	}

	@Override
	public E pollFirst() {
		// get the data from first element
		E data = head.getNext().getData();
		// Delete the first node and set head.next.next to head.next
		head.next = head.getNext().getNext();
		// reduce size by 1;
		size--;
		return data;
	}

	@Override
	// TODO fix the public class declarations
	public E pollLast() {
		E data = tail.getPrev().getData();
		tail.prev = tail.prev.prev;
		size--;
		return data;
	}

}
