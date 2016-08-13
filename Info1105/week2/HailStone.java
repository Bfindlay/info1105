package week2;

import java.util.ArrayList;
import java.util.List;

public class HailStone {

	static ArrayList<Integer> list = new ArrayList<Integer>();

	public static List<Integer> hailstone(int n) {
		list.add(n);
		if (n == 1)
			return list;
		return (n % 2 == 0) ? hailstone(n / 2) : hailstone((n * 3) + 1);

	}

	public static void main(String[] args) {
		hailstone(24);
		for(int i : list){
			System.out.println(i);
		}
	}
}
