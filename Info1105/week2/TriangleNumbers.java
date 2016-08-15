package week2;

public class TriangleNumbers {

	public static int triangle(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 0) {
			return 0;
		}
		return n + triangle(n - 1);
	}
}