package week2;

public class Fibonacci {

	public static int fibonacci(int n) {
		if (n == 0 || n == 1)
			return (n == 0) ? 0 : 1;
		return (fibonacci(n - 1) + fibonacci(n - 2));
	}

}