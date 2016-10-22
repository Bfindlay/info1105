package assignment2;

import static org.junit.Assert.*;

import java.util.List;

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

	@Test
	public void testRemoveCount() {
		Assignment a = new Assignment();
		assertEquals(0, a.countPrefixes());
		assertEquals(0, a.sumKeyLengths());
	}

	@Test
	public void testRemoveSumKeys() {
		Assignment a = new Assignment();
		a.put("GAT", "g");
		a.put("GATTC", "gg");
		a.put("GATTACA", "ggg");
		assertEquals(15, a.sumKeyLengths());
		a.remove("GATTACA");
		assertEquals(8, a.sumKeyLengths());
	}

	@Test
	public void testGetKeysMatchingPrefix() {
		Assignment a = new Assignment();
		a.put("A", "1");
		a.put("AC", "2");
		a.put("AT", "3");
		a.put("AG", "F");
		a.put("AAA", "F");
		a.put("AAC", "F");
		a.put("ACT", "F");
		a.put("AGT", "F");
		a.put("AGA", "F");
		a.put("ACTA", "F");
		a.put("AGTA", "F");
		a.put("AGTC", "F");
		a.put("AGTG", "F");

		List<String> test = a.getKeysMatchingPrefix("");
		// assertEquals(1, test.size());
		for (String s : test) {
			System.out.println(s);
		}
		assertEquals(13, test.size());

		List<String> fail = a.getKeysMatchingPrefix("TTTT");
		assertEquals(0, fail.size());

	}

	@Test
	public void testGetMatchingNullKey() {
		Assignment a = new Assignment();
		try {
			a.getKeysMatchingPrefix(null);
			fail("didnt throw exception");
		} catch (Exception e) {

		}
	}

	@Test
	public void testCOuntMatchingMalformedKey() {
		Assignment a = new Assignment();
		try {
			a.countKeysMatchingPrefix("P");
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == malformed key exception
		}
	}

	@Test
	public void testGetKeysMatchingAfterPutOverwrite() {
		Assignment a = new Assignment();
		a.put("A", "1");
		a.put("AC", "2");
		a.put("AT", "3");
		a.put("AG", "F");
		a.put("AAA", "F");
		a.put("AAC", "F");

		assertEquals(6, a.getKeysMatchingPrefix("A").size());
		a.put("A", "gggg");
		assertEquals(6, a.getKeysMatchingPrefix("A").size());
	}

	@Test
	public void testCountMatchingNullKey() {
		Assignment a = new Assignment();
		try {
			a.countKeysMatchingPrefix(null);
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == illegal argument exception
		}
	}

	@Test
	public void testConstruction() {
		Assignment a = new Assignment();
		assertEquals(0, a.size());
		assertEquals(0, a.getKeysMatchingPrefix("").size());

	}

	@Test
	public void testGetMatchingMalformedKey() {
		Assignment a = new Assignment();
		try {
			a.getKeysMatchingPrefix("P");
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == malformed exception
		}
	}

	@Test
	public void testPutReturnValue() {
		Assignment a = new Assignment();
		a.put("A", "a");
		assertEquals("a", a.put("A", "b"));
		assertNull(a.put("T", "t"));
	}

	@Test
	public void testGetKeysMatchingAfterRemove() {
		Assignment a = new Assignment();
		a.put("A", "1");
		a.put("AC", "2");
		a.put("AT", "3");
		a.put("AG", "F");
		a.put("AAA", "F");
		a.put("AAC", "F");
		assertEquals(6, a.countKeysMatchingPrefix("A"));
		a.remove("AAC");
		assertEquals(5, a.countKeysMatchingPrefix("A"));

	}

	@Test
	public void testPutReturnNull() {
		Assignment a = new Assignment();
		assertNull(a.put("A", "test"));
		assertNotNull(a.put("A", "not null"));
	}

	@Test
	public void testCountKeysMatchingExample() {
		fail("not yet done");
	}

	@Test
	public void testPutNullKey() {
		Assignment a = new Assignment();
		try {
			a.put(null, "A");
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == illegal argument exception
		}

		try {
			a.put("A", null);
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == illegal argument exception
		}
	}

	@Test
	public void testRemovePrefixDoesnNotBreakCounts() {
		fail("not yet done");
	}

	@Test
	public void testCountPrefixesExample() {
		fail("not yet done");
	}

	@Test
	public void testRemoveReturnValue() {
		fail("not yet done");
	}

	@Test
	public void testSumKeyLengthsExample() {
		fail("not yet done");
	}

	@Test
	public void testOverWriteKeyDoesNotBreakCounts() {
		fail("not yet done");
	}

	@Test
	public void testGetNullKey() {
		Assignment a = new Assignment();
		try {
			a.get(null);
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == illegal argument exception
		}

	}

	@Test
	public void testPutMalformedKey() {
		Assignment a = new Assignment();
		try {
			a.put("P", "A");
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == illegal argument exception
		}

	}

	@Test
	public void testMixOfPutGetRemove() {
		fail("not yet done");
	}

	@Test
	public void testRemoveNonExistentKeyDoesNotBreakCounts() {
		Assignment a = new Assignment();
		a.put("A", "1");
		a.put("AC", "2");
		a.put("AT", "3");
		a.put("AG", "F");
		a.put("AAA", "F");
		a.put("AAC", "F");

		assertEquals(6, a.countKeysMatchingPrefix("A"));
		assertEquals(6, a.size());
		assertEquals(7, a.countPrefixes());
		assertEquals(7, a.sumKeyLengths());
		fail("test these more");
	}

	@Test
	public void testRemoveNullKey() {
		Assignment a = new Assignment();
		a.put("G", "G");
		try {
			a.remove(null);
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == malformed key exception
		}
	}

	@Test
	public void testGetMalformedKey() {
		Assignment a = new Assignment();
		a.put("G", "G");
		try {
			a.get("p");
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == malformed key exception
		}
	}

	@Test
	public void testRemoveReturnNull() {
		Assignment a = new Assignment();
		assertNull(a.remove("A"));
	}

	@Test
	public void testGetExample() {
		Assignment a = new Assignment();
		a.put("A", "1");
		a.put("ACAT", "2");
		a.put("ACCCTGTCA", "3");
		a.put("C", "4");
		a.put("CAT", "5");
		a.put("CATTAGAT", "6");
		a.put("G", "7");
		a.put("GATCGAT", "8");
		a.put("T", "9");
		a.put("TAGAGT", "10");

		assertEquals("1", a.get("A"));
		assertEquals("2", a.get("ACAT"));
		assertEquals("3", a.get("ACCCTGTCA"));
		assertEquals("4", a.get("C"));
		assertEquals("5", a.get("CAT"));
		assertEquals("6", a.get("CATTAGAT"));
		assertEquals("7", a.get("G"));
		assertEquals("8", a.get("GATCGAT"));
		assertEquals("9", a.get("T"));
		assertEquals("10", a.get("TAGAGT"));
		assertNull(a.get("TAGAGCTGA"));
	}

	@Test
	public void testRemovePrefixDoesNotBreakGet() {
		Assignment a = new Assignment();
		a.put("A", "1");
		a.put("ACAT", "2");
		a.put("ACCCTGTCA", "test");
		assertEquals("2", a.remove("ACAT"));
		assertEquals("test", a.get("ACCCTGTCA"));

	}

	@Test
	public void testGetKeysMatchingExamle() {
		Assignment a = new Assignment();
		a.put("A", "1");
		a.put("AC", "2");
		a.put("ACCCTGTCA", "test");

		List<String> list = a.getKeysMatchingPrefix("A");
		assertEquals("A", list.get(0));
		assertEquals("AC", list.get(1));
		assertEquals("ACCCTGTCA", list.get(2));
	}

	@Test
	public void testPutNullValue() {
		Assignment a = new Assignment();
		try {
			a.put("A", null);
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == illegal argument exception
		}
	}

	@Test
	public void testRemoveMalformedKey() {
		Assignment a = new Assignment();
		try {
			a.remove("K");
			fail("didnt throw exception");
		} catch (Exception e) {
			// assert e == malformed key exception
		}
	}

	@Test
	public void countPrefixes() {
		Assignment map = new Assignment();

		map.put("GAT", "zzzz");
		map.put("GATTACA", "zzzz");
		map.put("GATTC", "zzzz");
		assertEquals(8, map.countPrefixes());
		map.remove("GATTACA");
		assertEquals(5, map.countPrefixes());

		Assignment m = new Assignment();
		m.put("ACA", "a");
		m.put("ACATA", "a");
		assertEquals(5, m.countPrefixes());

	}

	@Test
	public void testRemoveNstuff() {
		Assignment DNAmodel = new Assignment();

		DNAmodel.put("ACAC", "intron");
		DNAmodel.put("ACACCCACCTGG", "rerograde virus");
		assertEquals(12, DNAmodel.countPrefixes());
		assertEquals(16, DNAmodel.sumKeyLengths());
		DNAmodel.remove("ACACCCACCTGG");
		assertEquals(4, DNAmodel.countPrefixes());
		assertEquals(4, DNAmodel.sumKeyLengths());
	}

	@Test
	public void testMoreRemove() {
		Assignment a = new Assignment();
		a.put("GATT", "x");
		a.put("GATTC", "y");
		a.put("GATTACA", "z");

		assertEquals(8, a.countPrefixes());
		a.remove("GATT");
		assertEquals(8, a.countPrefixes());

	}
}
