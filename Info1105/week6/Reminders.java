package week6;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;

public class Reminders {

	PriorityQueue<SimpleEntry<Date, String>> pq;

	public Reminders() {
		pq = new PriorityQueue<SimpleEntry<Date, String>>(Comparator.comparing(SimpleEntry::getKey));
	}

	/**
	 * Adds a reminder at the given time
	 */
	public void setReminder(Date time, String reminder) {
		pq.add(new SimpleEntry(time, reminder));
	}

	/**
	 * Retrieves & removes all reminders up to (and including) the given time
	 */
	public List<String> getReminders(Date currentTime) {
		List<String> list = new ArrayList<>();
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			if (pq.peek().getKey().compareTo(currentTime) > 0)
				return list;
			list.add(pq.poll().getValue());
		}
		return list;
	}
}
