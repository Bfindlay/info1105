package assignment1;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AssignmentAlpha implements Calendar {

	// The default constructor for the class should be public
	// We will use this when we test your code!

	private MultiMapTree tree;

	public AssignmentAlpha() {
		tree = new MultiMapTree();
	}

	@Override
	public List<Appointment> getAppointments(String location) {
		return (tree.containsMapKey(location)) ? tree.getMapValue(location) : new ArrayList<Appointment>();
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
		tree.insertEntry(description, when, location);
	}

	@Override
	public void remove(Appointment appointment) {
		tree.removeEntry(appointment);

	}
}
