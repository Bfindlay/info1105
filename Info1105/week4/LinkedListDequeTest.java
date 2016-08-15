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
	public void testAddFirst() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
		deque.addFirst("hello");
		assertEquals("hello", deque.peekFirst());
	}

	@Test
	public void testAddLast() {
		LinkedListDeque<String> deque = new LinkedListDeque<>();
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

}
