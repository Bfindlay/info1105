package assignment1;

import java.util.HashMap;
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

	private TreeMap<Date, TreeMap<String, TreeSet<Appointment>>> tree;
	private HashMap<String, TreeSet<Appointment>> map;

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
			TreeSet<Appointment> s = tree.get(when).get(location);
			if (s != null) {
				s.add(entry);
				tr.put(location, s);
			} else {
				TreeSet<Appointment> ts = new TreeSet<>();
				ts.add(entry);
				tr.put(location, ts);

			}
			// Replace the new TreeSet elements back into the main Tree
			tree.put(when, tr);

		} else {
			// Lets add a new event to this Current date key
			TreeSet<Appointment> eventSet = new TreeSet<>();
			eventSet.add(entry);
			TreeMap<String, TreeSet<Appointment>> t = new TreeMap<>();
			t.put(location, eventSet);
			tree.put(when, t);
		}
		// Insert locations into the HashMap
		if (map.containsKey(location)) {
			map.get(location).add(entry);
		} else {
			TreeSet<Appointment> set = new TreeSet<>();
			set.add(entry);
			map.put(location, set);
		}
	}

	/**
	 * 
	 * @param appointment
	 */
	public void removeEntry(Appointment appointment) {

		if (appointment == null)
			return;

		Date time = appointment.getStartTime();
		String loc = appointment.getLocation();

		// System.out.println(loc);

		TreeSet<Appointment> list = tree.get(time).get(loc);
		list.remove(appointment);
		if (tree.get(time).get(loc).size() == 0) {
			tree.get(time).remove(loc);
		}
		if (tree.get(time).size() == 0)
			tree.remove(time);

		// Remove from location Map
		TreeSet<Appointment> locSet = map.get(appointment.getLocation());
		locSet.remove(appointment);
		if (locSet.size() == 0) {
			map.remove(appointment.getLocation());
		}
	}

	/**
	 * 
	 * @param when
	 * @return
	 */
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
	/**
	 * 
	 * @param when
	 * @param location
	 * @return
	 */
	public Appointment getNextEntry(Date when, String location) {
		if (when == null) {
			return null;
		}
		if (tree.containsKey(when)) {
			TreeMap<String, TreeSet<Appointment>> tr = tree.get(when);
			TreeSet<Appointment> res = tr.get(location);
			if (res != null && res.size() != 0)
				return res.first();
		}
		return getNextEntry(tree.higherKey(when), location);
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public boolean containsMapKey(String location) {
		return map.containsKey(location);
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public List<Appointment> getMapValue(String location) {
		return new ArrayList<>(map.get(location));
	}
}
