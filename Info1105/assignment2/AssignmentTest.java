package assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssignmentTest {

	@Test
	public void testAdd() {
		Assignment a = new Assignment();
		assertNull(a.put("ACGT", "D"));
		assertEquals(4, a.size());
		a.put("A", "D");
		assertEquals(4, a.size());
		assertEquals("D", a.put("A", "DERP"));

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

}
