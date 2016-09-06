package assignment1;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Assignment implements Calendar {

	// The default constructor for the class should be public
	// We will use this when we test your code!

	private MultiMapTree tree;

	public Assignment() {
		tree = new MultiMapTree();
	}

	// TODO handle no event errors
	@Override
	public List<Appointment> getAppointments(String location) {
		return (tree.containsMapKey(location)) ? tree.getMapValue(location) : new ArrayList<Appointment>();
	}

	// TODO handle no event errors
	@Override
	public Appointment getNextAppointment(Date when) {
		if (when == null) {
			throw new IllegalArgumentException("time was null");
		}
		return tree.getNextEntry(when);
	}

	@Override
	public Appointment getNextAppointment(Date when, String location) {
		return tree.getNextEntry(when, location);
	}

	@Override
	public void add(String description, Date when, String location) {
		tree.insertEntry(description, when, location);
	}

	@Override
	public void remove(Appointment appointment) {
		tree.removeEntry(appointment);

	}
}
