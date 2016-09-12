package assignment1;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EnhancedAssignment implements Calendar {

	// The default constructor for the class should be public
	// We will use this when we test your code!

	private EnhancedTree tree;

	public EnhancedAssignment() {
		tree = new EnhancedTree();
	}

	// TODO handle no event errors
	// TODO No event => Empty list
	// TODO THrows illegal Argument excetion if any argument is null
	@Override
	public List<Appointment> getAppointments(String location) {
		if (location == null) {
			throw new IllegalArgumentException("Location was null");
		}
		return (tree.containsMapKey(location)) ? tree.getMapValue(location)
				: new ArrayList<Appointment>();
	}

	// TODO Return the next appointment at or after the given time (in any
	// location) // If there is no such appointment, return null
	// TODO Throws IllegalArgumentException if the argument is null
	@Override
	public Appointment getNextAppointment(Date when) {
		if (when == null) {
			throw new IllegalArgumentException("time was null");
		}
		return tree.getNextEntry(when);
	}

	// TODO Return the next appointment at or after the given time,
	// If there is no such appointment, return null
	// Throws IllegalArgumentException if any argument is null
	@Override
	public Appointment getNextAppointment(Date when, String location) {
		if (when == null || location == null) {
			throw new IllegalArgumentException("time was null");
		}
		return tree.getNextEntry(when, location);
	}

	// TODO Throws IllegalArgumentException if any argument is null
	@Override
	public void add(String desc, Date when, String loc) {
		if (desc == null || when == null || loc == null) {
			throw new IllegalArgumentException("Argument was null");
		}
		tree.insertEntry(desc, when, loc);
	}

	// TODO // Throws IllegalArgumentException if the argument is null
	@Override
	public void remove(Appointment appointment) {
		if (appointment == null) {
			throw new IllegalArgumentException("Appointment was null");
		}
		tree.removeEntry(appointment);

	}
}
