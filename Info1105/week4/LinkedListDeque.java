package week4;

public class LinkedListDeque<E> implements Deque<E> {
	public static void main(String[] args) {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("Test");
		System.out.println(deque.first().getData());
	}

	// NESTED NODE CLASS
	static class Node<E> {
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
		this.tail = new Node<>(null, tail, null);
		head.setNext(tail);
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
		Node<E> newNode = new Node<E>(element, this.tail.prev, this.tail.prev.next);
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;

	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

}
