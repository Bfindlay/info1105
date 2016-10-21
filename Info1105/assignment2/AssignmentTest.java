package assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssignmentTest {

	@Test
	public void testAdd() {
		Assignment a = new Assignment();
		a.put("ACGT", "D");
		assertEquals(4, a.size());
		a.put("A", "D");
		assertEquals(4, a.size());

	}

}
