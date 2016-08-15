package week2;

import java.util.ArrayList;
import java.util.List;

public class HailStone {

	public static List<Integer> hailstone(int n) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(n);
		while (n != 1) {
			if ((n & 1) == 0)
				n = n / 2;
			else
				n = 3 * n + 1;
			list.add(n);
		}
		return list;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list = hailstone(1);
		for (int i : list) {
			System.out.println(i);
		}
		System.out.println("___________________________");
		list = hailstone(2);
		for (int i : list) {
			System.out.println(i);
		}
	}
}