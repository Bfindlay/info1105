package week1;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnglishCalculatorTest {

	@Test
	public void testCalculator() {
		double t1 = EnglishCalculator.calculate("1 plus 2");
		double t2 = EnglishCalculator.calculate("1 divided by 2");
		double t6 = EnglishCalculator.calculate("1 divided by 2");
		double t3 = EnglishCalculator.calculate("1 times 2");
		double t4 = EnglishCalculator.calculate("1 minus 2");
		// double t5 = EnglishCalculator.calculate("1 divided 0");

		assertEquals(3, t1, 0001);
		assertEquals(0.5, t2, 0001);
		assertEquals(2, t3, 0001);
		assertEquals(-1, t4, 0001);
		assertEquals(0.5, t6, 0001);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		double t0 = EnglishCalculator.calculate("1 divided 0");
	}

}
