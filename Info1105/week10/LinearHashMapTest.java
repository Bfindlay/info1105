package week10;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinearHashMapTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testCollision() {
		LinearHashMap<Integer, String> map = new LinearHashMap<Integer, String>(5);
		map.put(1, "one");
		exception.expect(RuntimeException.class);
		map.put(6, "oops");
	}

	@Test
	public void testConstruction() {
		LinearHashMap<Integer, String> map = new LinearHashMap<Integer, String>(5);
		assertEquals(0, map.size());
		Iterable<String> is = map.values();
		Iterable<Integer> iv = map.keySet();
		Iterable<Entry<Integer, String>> ie = map.entrySet();

		assertFalse(is.iterator().hasNext());
		assertFalse(iv.iterator().hasNext());
		assertFalse(ie.iterator().hasNext());

		assertNull(map.get(1));
		assertNull(map.remove(1));

	}

	@Test
	public void testEmpty() {
		LinearHashMap<Integer, String> map = new LinearHashMap<Integer, String>(5);
		assertTrue(map.isEmpty());
	}

	@Test
	public void testSize() {
		LinearHashMap<Integer, String> map = new LinearHashMap<Integer, String>(5);
		map.put(1, "one");
		assertEquals(1, map.size());
		map.put(2, "two");
		assertEquals(2, map.size());
		map.put(3, "three");
		assertEquals(3, map.size());
	}

	@Test
	public void testSmallHashMap() {
		LinearHashMap<Integer, String> map = new LinearHashMap<Integer, String>(5);
		map.put(1, "one");
		assertEquals(1, map.size());
		map.put(2, "two");
		assertEquals(2, map.size());
		map.put(3, "three");
		assertEquals(3, map.size());

		assertEquals("one", map.get(1));
		assertEquals("two", map.get(2));
		assertEquals("three", map.get(3));
		map.remove(2);
		assertEquals(2, map.size());
		assertNull(map.get(2));
		map.put(1, "1");
		assertEquals(2, map.size());
		assertEquals("1", map.get(1));
		map.put(100, "100");
		assertEquals("100", map.get(100));

		exception.expect(RuntimeException.class);
		map.put(101, "oops");
	}

	@Test
	public void testKeySet() {
		LinearHashMap<Integer, String> map = new LinearHashMap<>(5);
		map.put(1, "one");
		assertEquals(1, map.size());
		map.put(2, "two");
		assertEquals(2, map.size());
		map.put(3, "three");
		assertEquals(3, map.size());

		for (Integer i : map.keySet()) {
			System.out.println(i);
		}

		for (String i : map.values()) {
			System.out.println(i);
		}
		for (Entry i : map.entrySet()) {
			System.out.println(i);
		}
	}

	@Test
	public void testGet() {
		LinearHashMap<Integer, String> map = new LinearHashMap<>(5);
		assertNull(map.get(1));
		map.put(1, "one");
		assertEquals(1, map.size());
		map.remove(1);
		assertNull(map.get(1));
		assertEquals(0, map.size());
		map.put(1, "one");
		map.put(1, "two");
		assertEquals("two", map.get(1));
	}

}
