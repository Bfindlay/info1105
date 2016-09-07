package week7;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class BstSimpleSortedMapTest {

	@Test
	public void testBasicBST() {
		BstSimpleSortedMap tree = new BstSimpleSortedMap();

		tree.put(10, "A");
		assertEquals("A", tree.get(10));
		tree.put(9, "B");
		assertEquals("B", tree.get(9));
		tree.put(8, "C");
		assertEquals("C", tree.get(8));
		tree.put(11, "F");
		assertEquals("F", tree.get(11));
		tree.put(15, "D");
		assertEquals("D", tree.get(15));
		tree.put(19, "E");
		assertEquals("E", tree.get(19));
		tree.put(14, "G");
		assertEquals("G", tree.get(14));
		tree.put(22, "H");
		tree.put(1, "i");
		assertEquals("H", tree.get(22));

	}

	@Test
	public void testNullRoot() {
		BstSimpleSortedMap tree = new BstSimpleSortedMap();

		tree.put(4, "A");
		assertEquals("A", tree.get(4));
	}

	@Test
	public void testBasicRemove() {
		BstSimpleSortedMap tree = new BstSimpleSortedMap();

		tree.put(4, "A");
		tree.put(2, "B");
		tree.put(5, "C");
		assertEquals(3, tree.size());
		tree.remove(5);
		assertEquals(2, tree.size());
		assertNull(tree.get(5));

	}

	@Test
	public void testRemove() {
		BstSimpleSortedMap tree = new BstSimpleSortedMap();

		tree.put(4, "A");
		tree.put(2, "B");
		tree.put(5, "C");
		tree.put(1, "D");
		tree.put(3, "E");
		assertEquals(5, tree.size());
		tree.remove(2);
		assertEquals(4, tree.size());
		assertNull(tree.get(2));
	}

	@Test
	public void testDuplicates() {
		BstSimpleSortedMap tree = new BstSimpleSortedMap();

		tree.put(4, "A");
		tree.put(2, "B");
		tree.put(5, "C");
		tree.put(1, "D");
		tree.put(3, "E");
		assertEquals(5, tree.size());
		tree.put(2, "X");
		assertEquals("X", tree.get(2));
	}

	@Test
	public void testSize() {
		BstSimpleSortedMap tree = new BstSimpleSortedMap();

		tree.put(10, "A");
		assertEquals(1, tree.size());
		tree.put(9, "B");
		assertEquals(2, tree.size());
		tree.put(8, "C");
		assertEquals(3, tree.size());
		tree.put(11, "F");
		assertEquals(4, tree.size());
		tree.put(15, "D");
		assertEquals(5, tree.size());
		tree.put(19, "E");
		assertEquals(6, tree.size());
		tree.put(14, "G");
		assertEquals(7, tree.size());
		tree.put(22, "H");
		assertEquals(8, tree.size());
		tree.put(23, "i");
		assertEquals(9, tree.size());
		tree.put(24, "j");
		tree.put(25, "K");
		assertEquals(11, tree.size());
	}
}
