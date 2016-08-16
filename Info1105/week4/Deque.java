package week4;

public interface Deque<E> {
	public int size();

	public boolean isEmpty();

	public E peekFirst();

	public E peekLast();

	public void addFirst(E element);

	public void addLast(E element);

	public E pollFirst();

	public E pollLast();

}
