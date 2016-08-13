package week2;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinearSearchableArrayTest {

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testLinearSearch() {

		Comparable[] data = { 1, 2, 3, 4, 5, 6 };

		BinarySearchableArray search = new BinarySearchableArray(data);

		assertEquals(3, search.search(3));
		assertEquals(5, search.search(5));
		assertEquals(2, search.search(2));
		assertEquals(4, search.search(4));
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testNull() {

		Comparable[] data = { 1, 2, 3, 4, 5, 6 };

		BinarySearchableArray search = new BinarySearchableArray(data);

		assertEquals(null, search.search(7));

	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testStrings() {

		Comparable[] data = { "hi", "derp", "hello", "world", "", " " };

		BinarySearchableArray search = new BinarySearchableArray(data);

		assertEquals(null, search.search("derpp"));
		assertEquals("hi", search.search("hi"));
		assertEquals("world", search.search("world"));
		assertEquals("", search.search(""));
		assertEquals(" ", search.search(" "));

	}

}
