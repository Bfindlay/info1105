package assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssignmentTest {

	@Test
	public void testAdd() {
		Assignment a = new Assignment();

		a.put("", "empty");
		assertEquals("empty", a.get(""));
		assertNull(a.put("ACGT", "D"));
		assertEquals(4, a.size());
		a.put("A", "D");
		assertEquals(4, a.size());
		assertEquals("D", a.put("A", "DERP"));
		a.put("AA", "AA");
		a.put("CC", "CC");
		a.put("TAGA", "TAGA");
		a.put("GCAT", "GCAT");
		a.put("G", "G");
		a.put("T", "T");
		a.put("TT", "TT");
		a.put("GG", "GG");
		a.put("GGG", "GGG");

	}

	@Test
	public void testGet() {
		Assignment a = new Assignment();
		a.put("ACGT", "D");
		assertEquals("D", a.get("ACGT"));
		assertNull(a.get("AC"));
		assertNull(a.get("ACGA"));

		a.put("A", "A");
		assertEquals("A", a.get("A"));
		a.put("AA", "AA");
		assertEquals("AA", a.get("AA"));
		a.put("AAAA", "AAAA");
		assertEquals("AAAA", a.get("AAAA"));
		a.put("GCA", "GCA");
		assertEquals("GCA", a.get("GCA"));
		a.put("GGGCCCAAATTT", "long");
		assertEquals("long", a.get("GGGCCCAAATTT"));
		System.out.println(a.get("GGGCCCAAATTT"));
	}

	@Test
	public void testEmpty() {
		Assignment a = new Assignment();
		assertEquals(true, a.isEmpty());
		a.put("ACGT", "D");
		assertEquals(false, a.isEmpty());
	}

	@Test
	public void testSize() {
		Assignment a = new Assignment();
		assertEquals(0, a.size());
		a.put("ACGT", "D");
		assertEquals(4, a.size());
	}

	@Test
	public void testRemove() {
		Assignment a = new Assignment();
		a.put("ACGT", "John");
		assertEquals("John", a.get("ACGT"));
		assertEquals("John", a.remove("ACGT"));
		assertNull(a.get("ACGT"));
	}

	@Test
	public void test() {
		Assignment a = new Assignment();
		a.put("ACGT", "John");
		String s = "";
		char[] c = s.toCharArray();
		System.out.println(c.length);
	}
}
