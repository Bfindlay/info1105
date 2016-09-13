package assignment1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Date;

/**
 * 
 * @author Brett Findlay
 * @SID 450258163
 * 
 *      This is a Wrapper class created to connect a Self Balancing Binary Tree
 *      (TreeMap), with a HashMap to enable retrieval of a value using one of
 *      two keys, a Date or a Location;
 *
 */

public class EnhancedTree {

	/* NESTED APPOINTMENT CLASS IMPLEMENTATION */

	private class Entry implements Comparable<Entry>, Appointment {

		private final String DESCRIPTION;
		private final String LOCATION;
		private final Date DATE;
		private Integer id;

		/**
		 * Entry Node implementation of Appointment
		 * 
		 * @param description
		 *            Description of the scheduled appointment
		 * @param location
		 *            The location of the scheduled appointment
		 * @param date
		 *            Time of the scheduled appointmen
		 * @param id
		 *            Unique identifier used to identify and sort an Entry in a
		 *            set
		 */
		public Entry(String description, String location, Date date, int id) {
			this.DESCRIPTION = description;
			this.LOCATION = location;
			this.DATE = date;
			this.id = id;
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

		@Override
		public int compareTo(Entry o) {
			return this.id.compareTo(o.id);
		}

	}

	/* BEGIN MULTIKEY TREE IMPLEMENTATION */

	public TreeMap<Date, TreeMap<String, TreeSet<Appointment>>> tree;
	private HashMap<String, List<Appointment>> map;

	// Auto incrementing ID used to reference entry objects in a TreeSet
	private int createID = 0;

	// Default constructor to initialize both TreeMap and HashMap structures
	public EnhancedTree() {
		tree = new TreeMap<>();
		map = new HashMap<>();
	}

	/**
	 * Creates and then inserts a new Appointment object to then be stored in
	 * both a TreeMap and HashMap with guaranteed worst case insertion
	 * complexity O(log n)
	 * 
	 * @param description
	 * @param when
	 * @param location
	 */
	public void insertEntry(String description, Date when, String location) {
		Entry entry = new Entry(description, location, when, createID);
		this.createID++;
		// Checks if an Key with Date 'when' exists in the TreeMap
		if (tree.containsKey(when)) {
			// Retrieve the TreeSet and add the new entry to the existing Set
			// or create a new Set
			TreeMap<String, TreeSet<Appointment>> tr = tree.get(when);
			TreeSet<Appointment> l = tree.get(when).get(location);
			if (l != null) {
				l.add(entry);
				tr.put(location, l);
			} else {
				TreeSet<Appointment> ls = new TreeSet<>();
				ls.add(entry);
				tr.put(location, ls);
			}
			// Replace the new TreeSet elements back into the main Tree
			tree.put(when, tr);

		} else {
			// Lets add a new event to this Current date key
			TreeSet<Appointment> event = new TreeSet<>();
			event.add(entry);
			TreeMap<String, TreeSet<Appointment>> t = new TreeMap<>();
			t.put(location, event);
			tree.put(when, t);
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
		TreeSet<Appointment> list = tree.get(time).get(loc);
		if (tree.get(time).size() == 1) {
			tree.remove(time);
			return;
		}
		if (list != null) {
			list.remove(appointment);
		}

		// Remove from location Map
		List<Appointment> locList = map.get(appointment.getLocation());
		if (locList.size() == 1)
			map.remove(appointment.getLocation());
		else
			locList.remove(locList.indexOf(appointment));
	}

	public Appointment getNextEntry(Date when) {
		if (tree.containsKey(when)) {
			TreeMap<String, TreeSet<Appointment>> tr = tree.get(when);
			if (tr.firstEntry() != null) {
				return tr.firstEntry().getValue().first();
			}
		}
		if (tree.higherEntry(when) != null) {
			TreeMap<String, TreeSet<Appointment>> tr = tree.higherEntry(when).getValue();
			if (tr.firstEntry() != null) {
				return tr.firstEntry().getValue().first();
			}
		}
		return null;
	}

	// TODO make sure it finds the next event at that location
	public Appointment getNextEntry(Date when, String location) {
		if (when == null) {
			return null;
		}
		if (tree.containsKey(when)) {
			TreeMap<String, TreeSet<Appointment>> tr = tree.get(when);
			TreeSet<Appointment> result = tr.get(location);
			return (result != null && result.size() != 0) ? result.first()
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
