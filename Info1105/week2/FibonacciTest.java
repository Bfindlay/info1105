package week2;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testStartingNumbers() {
		assertEquals(0, Fibonacci.fibonacci(0));
		assertEquals(1, Fibonacci.fibonacci(1));

	}

	public void testFirstFive() {
		assertEquals(1, Fibonacci.fibonacci(2));
		assertEquals(2, Fibonacci.fibonacci(3));
		assertEquals(3, Fibonacci.fibonacci(4));
		assertEquals(5, Fibonacci.fibonacci(5));
		assertEquals(8, Fibonacci.fibonacci(6));
	}

}
