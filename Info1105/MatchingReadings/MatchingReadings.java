package MatchingReadings;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.HashMap;

public class MatchingReadings {

	static int countMatchingReadings(List<Report> reportList, int startTime, int endTime) {
		// TODO: return the number of readings which were logged between
		// startTime and endTime (inclusive)
		int[] r = new int[reportList.size()];
		int s = 0;
		for (Report i : reportList) {
			int time = i.getTime();
			r[s] = (time >= startTime && time <= endTime) ? r[s++] : r[s];
		}
		return r.length;

	}

	static int maxMatchingReadings(List<Report> reportList, int startTime, int endTime) {
		// TODO: return the maximum value of any reading which was logged
		// between startTime and endTime (inclusive)
		int max = 0;
		for (Report r : reportList) {
			int t = r.getTime();
			if (t <= startTime && t >= endTime)
				continue;
			int v = r.getValue();
			max = (v > max) ? v : max;
		}
		return max;
	}

	static int modeMatchingReadings(List<Report> reportList, int startTime, int endTime) {
		// TODO: return the mode (most common value) of any reading which was
		// logged between startTime and endTime (inclusive)
		HashMap<Integer, Integer> map = new HashMap<>();
		for (Report r : reportList) {
			int v = r.getValue();
			int t = r.getTime();
			if (t <= startTime && t >= endTime)
				continue;
			if (map.get(v) == null) {
				map.put(v, 1);
			} else {
				map.put(v, map.get(v) + 1);
			}
		}
		int key = modeHelper(map).getKey();
		return key;
	}

	public static Entry<Integer, Integer> modeHelper(Map<Integer, Integer> map) {
		Entry<Integer, Integer> maxEntry = null;
		Integer max = Collections.max(map.values());

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			Integer value = entry.getValue();
			if (null != value && max == value) {
				maxEntry = entry;
			}
		}
		return maxEntry;
	}

}