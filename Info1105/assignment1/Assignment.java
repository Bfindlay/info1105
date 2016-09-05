package assignment1;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Assignment implements Calendar {

	// The default constructor for the class should be public
	// We will use this when we test your code!

	// TODO make this private after testig
	private TreeMap<Date, List<Appointment>> tree;
	private Map<String, List<Appointment>> locations;

	public Assignment() {
		tree = new TreeMap<>();
		locations = new HashMap<>();
	}

	@Override
	public List<Appointment> getAppointments(String location) {
		return (locations.containsKey(location)) ? locations.get(location) : new ArrayList<Appointment>();
	}

	@Override
	public Appointment getNextAppointment(Date when) {
		if (when == null) {
			throw new IllegalArgumentException("time was null");
		}
		Appointment a = tree.higherEntry(when).getValue().get(0);
		return a;
	}

	@Override
	public Appointment getNextAppointment(Date when, String location) {
		// TODO Implement this! (then remove this TODO comment)
		List<Appointment> list = tree.higherEntry(when).getValue();
		List<Appointment> locations = list.stream().filter(entry -> entry.getLocation() == location)
				.collect(Collectors.toList());
		return locations.get(0);
	}

	@Override
	public void add(String description, Date when, String location) {
		// TODO Implement this! (then remove this TODO comment)

		// Insert to TreeMAP
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
		if (locations.containsKey(location)) {
			List<Appointment> loc = locations.get(location);
			loc.add(entry);
		} else {
			List<Appointment> locList = new ArrayList<>();
			locList.add(entry);
			locations.put(location, locList);
		}

	}

	@Override
	public void remove(Appointment appointment) {
		// TODO Implement this! (then remove this TODO comment)
		List<Appointment> list = tree.get(appointment.getStartTime());

		System.out.println(list.indexOf(appointment));
		// remove from map and tree

	}
}
