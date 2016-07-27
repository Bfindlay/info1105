package MatchingReadings;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MatchingReadingsTest {

	@Test
	public void test() {
		ArrayList<Report> list = new ArrayList<Report>();
		list.add(new Report(1, -10));
		list.add(new Report(2, 15));
		list.add(new Report(3, -10));
		list.add(new Report(5, 200));
		list.add(new Report(5, 200));
		list.add(new Report(5, 200));

		int test1 = MatchingReadings.countMatchingReadings(list, 0, 5);
		int test = MatchingReadings.maxMatchingReadings(list, 1, 300);
		int test3 = MatchingReadings.modeMatchingReadings(list, 1, 300);
		assertEquals(200, MatchingReadings.modeMatchingReadings(list, 0, 400));
		System.out.println(test3);
	}

}
