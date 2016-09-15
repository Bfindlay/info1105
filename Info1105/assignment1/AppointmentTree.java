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

public class AppointmentTree {

	/* NESTED APPOINTMENT CLASS IMPLEMENTATION */

	private class Entry implements Comparable<Entry>, Appointment {

		private final String DESCRIPTION;
		private final String LOCATION;
		private final Date DATE;
		private final Integer ID;

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
		 *            Unique identifier used to compare and sort an Entry into a
		 *            set
		 */
		private Entry(String description, String location, Date date, int id) {
			this.DESCRIPTION = description;
			this.LOCATION = location;
			this.DATE = date;
			this.ID = id;
		}

		@Override
		public int compareTo(Entry o) {
			return this.ID.compareTo(o.ID);
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

	private TreeMap<Date, TreeMap<String, TreeSet<Appointment>>> tree;
	private HashMap<String, TreeSet<Appointment>> map;

	// Auto incrementing ID used to reference entry objects in a TreeSet
	private int createID = 0;

	// Default constructor to initialize both TreeMap and HashMap structures
	public AppointmentTree() {
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
			// get and add the new entry to the existing Set or create a new Set
			TreeMap<String, TreeSet<Appointment>> tr = tree.get(when);
			TreeSet<Appointment> set = tree.get(when).get(location);
			if (set != null) {
				set.add(entry);
				tr.put(location, set);
			} else {
				// make a new set, add the entry and append to the sub tree
				TreeSet<Appointment> ts = new TreeSet<>();
				ts.add(entry);
				tr.put(location, ts);
			}
			// Append the new sub tree to the main tree
			tree.put(when, tr);
		} else {
			// Lets add a new event to this Current date key
			TreeSet<Appointment> eventSet = new TreeSet<>();
			eventSet.add(entry);
			TreeMap<String, TreeSet<Appointment>> tr = new TreeMap<>();
			tr.put(location, eventSet);
			tree.put(when, tr);
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
	 * Removes the exact given appointment from the calendar
	 * 
	 * @param appointment
	 *            obtained from getNextAppointment(*) methods
	 * 
	 */
	public void removeEntry(Appointment appointment) {

		if (appointment == null) {
			return;
		}

		Date time = appointment.getStartTime();
		String loc = appointment.getLocation();
		// Retrieve the set corresponding to this time/location
		TreeSet<Appointment> set = tree.get(time).get(loc);
		// Remove the appointment in O(log n) time through comparable ID
		set.remove(appointment);
		// If the exist is now empty, delete the list and remove from the tree
		if (tree.get(time).get(loc).size() == 0) {
			tree.get(time).remove(loc);
		}
		// If the entire subtree is now empty, delete this time from the tree
		if (tree.get(time).size() == 0) {
			tree.remove(time);
		}
		// Remove from location Map
		TreeSet<Appointment> locSet = map.get(appointment.getLocation());
		locSet.remove(appointment);
		if (locSet.size() == 0) {
			map.remove(appointment.getLocation());
		}
	}

	/**
	 * 
	 * 
	 * @param when
	 *            time at which Appointment starts, or after
	 * @return Returns a valid appointment at or after the given time or null if
	 *         none exist
	 */
	public Appointment getNextEntry(Date when) {
		// If the given date exists, return the first entry
		if (tree.containsKey(when)) {
			TreeMap<String, TreeSet<Appointment>> tr = tree.get(when);
			if (tr.firstEntry() != null) {
				// Return the first appointment found
				return tr.firstEntry().getValue().first();
			}
		}
		// No appointments existed at the given time, check the next time
		if (tree.higherEntry(when) != null) {
			TreeMap<String, TreeSet<Appointment>> tr = tree.higherEntry(when).getValue();
			if (tr.firstEntry() != null) {
				return tr.firstEntry().getValue().first();
			}
		}
		// No appointments found after this time
		return null;
	}

	/**
	 * 
	 * @param when
	 *            time at which to begin searching for an appointment
	 * @param location
	 *            Place at which the Appointment must occur
	 * @return A valid appointment at or after the given time and location or
	 *         null
	 */

	public Appointment getNextEntry(Date when, String location) {
		// Base case, no more appointments or exist in the tree
		if (when == null) {
			return null;
		}
		if (tree.containsKey(when)) {
			TreeMap<String, TreeSet<Appointment>> tr = tree.get(when);
			TreeSet<Appointment> res = tr.get(location);
			if (res != null && res.size() != 0)
				return res.first();
		}
		// No appointment found at this time and location, recursively itself
		return getNextEntry(tree.higherKey(when), location);
	}

	/**
	 * Helper access method for use in Assignment class
	 * 
	 * @param location
	 * @return true if the HashMap contains the given location
	 */
	public boolean hasMapKey(String location) {
		return map.containsKey(location);
	}

	/**
	 * Instantiates and constructs a new ArrayList from the set of location
	 * appointments
	 * 
	 * @param location
	 *            place at which the appointments must occur to be returned
	 * @return a list of Appointments that occur at the given location
	 */
	public List<Appointment> getMap(String location) {
		return new ArrayList<>(map.get(location));
	}
}
