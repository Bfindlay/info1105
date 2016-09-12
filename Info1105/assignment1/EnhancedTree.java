package assignment1;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Brett Findlay
 * @SID 450258163
 * 
 *      This is a Wrapper class created to connect a Self Balancing Binary Tree
 *      (TreeMap), with a HashMap to enable retrieval of a value using one of
 *      two keys;
 *
 */

public class EnhancedTree {

	/* NESTED APPOINTMENT CLASS IMPLEMENTATION */
	private class Entry implements Appointment {

		private final String DESCRIPTION;
		private final String LOCATION;
		private final Date DATE;

		public Entry(String desc, String loc, Date date) {
			this.DESCRIPTION = desc;
			this.LOCATION = loc;
			this.DATE = date;
		}

		@Override
		public String getDescription() {
			return this.DESCRIPTION;
		}

		@Override
		public String getLocation() {
			return this.LOCATION;
		}

		@Override
		public Date getStartTime() {
			return this.DATE;
		}

	}

	/* BEGIN MULTIKEY TREE IMPLEMENTATION */

	public TreeMap<Date, TreeMap<String, List<Appointment>>> tree;
	private HashMap<String, List<Appointment>> map;

	public EnhancedTree() {
		tree = new TreeMap<>();
		map = new HashMap<>();
	}

	public void insertEntry(String description, Date when, String location) {
		Entry entry = new Entry(description, location, when);
		if (tree.containsKey(when)) {
			List<Appointment> l = tree.get(when).get(location);
			TreeMap<String, List<Appointment>> tr = tree.get(when);
			if (l != null) {
				l.add(entry);
				tr.put(location, l);
			} else {
				List<Appointment> ls = new ArrayList<>();
				ls.add(entry);
				tr.put(location, ls);
			}
			tree.put(when, tr);

		} else {
			// lets add a new event to this date
			List<Appointment> event = new ArrayList<>();
			event.add(entry);
			TreeMap<String, List<Appointment>> t = new TreeMap<>();
			t.put(location, event);
			tree.put(when, t);

			// List<Appointment> event = new ArrayList<>();
			// event.add(entry);
			// tree.put(when, event);
		}
		// Insert locations
		if (map.containsKey(location)) {
			List<Appointment> loc = map.get(location);
			loc.add(entry);
		} else {
			List<Appointment> locList = new ArrayList<>();
			locList.add(entry);
			map.put(location, locList);
		}
	}

	public void removeEntry(Appointment appointment) {
		// TODO add null checks & Add case for list === 0
		// remove from the tree
		Date time = appointment.getStartTime();
		String loc = appointment.getLocation();
		List<Appointment> list = tree.get(time).get(loc);
		if (list != null) {
			if (list.size() == 1) {
				tree.get(time).remove(loc);
			} else {

				list.remove(list.indexOf(appointment));
			}

			// Remove from location Map
			List<Appointment> locList = map.get(appointment.getLocation());
			if (locList.size() == 1)
				map.remove(appointment.getLocation());
			else
				locList.remove(locList.indexOf(appointment));
		}
	}

	public Appointment getNextEntry(Date when) {
		if (tree.containsKey(when)) {
			if (tree.get(when).firstEntry().getValue().get(0) != null) {
				return tree.get(when).firstEntry().getValue().get(0);
			}
		}

		return (tree.higherEntry(when) != null)
				? tree.higherEntry(when).getValue().firstEntry().getValue().get(0) : null;
	}

	// TODO make sure it finds the enxt event at that location
	public Appointment getNextEntry(Date when, String location) {
		if (when == null) {
			return null;
		}
		if (tree.containsKey(when)) {
			TreeMap<String, List<Appointment>> tr = tree.get(when);
			List<Appointment> result = tr.get(location);
			return (result != null && result.size() != 0) ? result.get(0)
					: getNextEntry(tree.higherKey(when), location);
		} else {
			return getNextEntry(tree.higherKey(when), location);
		}
	}

	public boolean containsMapKey(String location) {
		return map.containsKey(location);
	}

	public List<Appointment> getMapValue(String location) {
		return map.get(location);
	}
}
