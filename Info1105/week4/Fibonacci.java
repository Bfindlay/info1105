package week4;

import java.util.Stack;

public class Fibonacci {

	public static Stack<Integer> getNumbers(int n) {
		Stack<Integer> s = new Stack<>();
		int x = 0, y = 0, z = 1;
		for (int i = 0; i < n + 1; i++) {
			x = y;
			y = z;
			z = x + y;
			s.push(x);
		}
		return s;
	}

	public static void main(String[] args) {
		Stack<Integer> test = getNumbers(3);
		System.out.println(test);
	}
}
