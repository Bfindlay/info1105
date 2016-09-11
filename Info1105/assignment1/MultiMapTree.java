package assignment1;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Brett
 * 
 *         This is a Wrapper class created to connect a Self Balancing Tree
 *         (TreeMap) with a HashMap to enable retrieval of a value using one of
 *         two keys;
 *
 */
@SuppressWarnings("serial")
public class MultiMapTree extends TreeMap<Object, Object> {

	public TreeMap<Date, List<Appointment>> tree;
	private HashMap<String, List<Appointment>> map;

	public MultiMapTree() {
		tree = new TreeMap<>();
		map = new HashMap<>();
	}

	public void insertEntry(String description, Date when, String location) {
		Entry entry = new Entry(description, location, when);
		if (tree.containsKey(when)) {
			List<Appointment> l = tree.get(when);
			l.add(entry);
			tree.put(when, l);
		} else {
			// lets add a new event to this date
			List<Appointment> event = new ArrayList<>();
			event.add(entry);
			tree.put(when, event);
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
		List<Appointment> list = tree.get(appointment.getStartTime());
		if (list.size() == 1)
			tree.remove(appointment.getStartTime());
		else
			tree.get(appointment.getStartTime()).remove(list.indexOf(appointment));

		// Remove from location Map
		List<Appointment> locList = map.get(appointment.getLocation());
		if (locList.size() == 1)
			map.remove(appointment.getLocation());
		else
			map.get(appointment.getLocation()).remove(locList.indexOf(appointment));
	}

	// TODO handle no event error
	// TODO Make sure it finds the next event
	public Appointment getNextEntry(Date when) {

		if (tree.containsKey(when))
			return tree.get(when).get(0);
		else
			return (tree.higherEntry(when) != null) ? tree.higherEntry(when).getValue().get(0) : null;
	}

	// TODO make sure it finds the enxt event at that location
	public Appointment getNextEntry(Date when, String location) {
		if (tree.containsKey(when)) {
			List<Appointment> list = tree.get(when);
			List<Appointment> result = list.stream().filter(entry -> entry.getLocation() == location)
					.collect(Collectors.toList());
			return (result.size() != 0) ? result.get(0) : getNextEntryHelp(tree.higherKey(when), location);
		} else {
			return getNextEntryHelp(tree.higherKey(when), location);
		}
	}

	public Appointment getNextEntryHelp(Date when, String location) {
		if (when == null) {
			return null;
		}
		List<Appointment> list = tree.get(when);
		List<Appointment> result = list.stream().filter(entry -> entry.getLocation() == location)
				.collect(Collectors.toList());
		if (result.size() != 0) {
			return result.get(0);
		}
		Date key = tree.higherKey(when);
		return getNextEntryHelp(key, location);
	}

	public boolean containsMapKey(String location) {
		return map.containsKey(location);
	}

	public List<Appointment> getMapValue(String location) {
		return map.get(location);
	}
}
