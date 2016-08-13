package week2;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class HailStoneTest {

	@Test
	public void testHail() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.addAll(HailStone.hailstone(2));
	}

}
