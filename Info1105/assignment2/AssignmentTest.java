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
		assertEquals(1, a.size());
		a.put("A", "D");
		assertEquals(2, a.size());
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
		assertEquals(1, a.size());
		a.put("TAGA", "Derp");
		a.put("T", "Derp1");
		a.put("TA", "Derp2");
		a.put("GA", "Derp4");
		a.put("A", "Derp5");
		assertEquals("Derp2", a.get("TA"));
		assertEquals(6, a.size());
	}

	@Test
	public void testRemove() {
		Assignment a = new Assignment();
		System.out.println(a.size());
		a.put("ACGT", "John");
		a.put("A", "Aohn");
		a.put("C", "JCohn");
		a.put("G", "Gohn");
		a.put("T", "Tohn");

		assertEquals(5, a.size());
		assertEquals("John", a.get("ACGT"));
		assertEquals("John", a.remove("ACGT"));
		assertEquals(4, a.size());
		assertNull(a.get("ACGT"));
		a.remove("A");
		assertNull(a.get("A"));
		a.remove("C");
		assertNull(a.get("C"));
		a.remove("G");
		assertNull(a.get("G"));
		a.remove("T");
		assertNull(a.get("T"));
		assertEquals(0, a.size());
		a.put("T", "NEW");
		assertEquals(1, a.size());

	}

	@Test
	public void testGetRemove() {
		Assignment a = new Assignment();
		a.put("ACGT", "John");
		a.put("ACGTA", "TEST");
		a.remove("ACGT");
		assertEquals("TEST", a.get("ACGTA"));
	}

	@Test
	public void testSum() {
		// GAT , GATTC , GATTACA
		Assignment a = new Assignment();
		a.put("GAT", "A");
		a.put("GATTC", "B");
		a.put("GATTACA", "C");
		assertEquals(15, a.sumKeyLengths());
	}
}
