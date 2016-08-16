package week4;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListDequeTest {

	@Test
	public void testLinkedListDequeNode() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		assertNull(deque.peekFirst());
	}

	@Test
	public void testPeakLast() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("A");
		assertEquals("A", deque.peekLast());
	}

	@Test
	public void testPeakFirst() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("A");
		assertEquals("A", deque.peekFirst());
	}

	@Test
	public void testAddFirst() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("hello");
		deque.addFirst("new Hello");
		assertEquals("new Hello", deque.peekFirst());
	}

	@Test
	public void testAddLast() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		// deque.addFirst("first");
		deque.addLast("last");
		assertEquals("last", deque.peekLast());
	}

	@Test
	public void testSize() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addLast("h");
		assertEquals(1, deque.size());
	}

	@Test
	public void testEmpty() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		assertEquals(true, deque.isEmpty());
	}

	@Test
	public void testPollFirst() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("A");
		deque.addLast("B");
		System.out.println(deque.size());
		assertEquals("A", deque.pollFirst());
		System.out.println(deque.size());
	}

	@Test
	public void testPollLast() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("A");
		deque.addLast("B");
		assertEquals("B", deque.pollLast());
	}

}
