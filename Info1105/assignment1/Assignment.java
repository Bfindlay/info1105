package assignment1;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author Brett Findlay
 * @SID 450258163
 * 
 *      This is the solution to Assignment 1, With all of the main methods being
 *      Abstracted to the Appointment Tree Class
 * 
 *
 */
public class Assignment implements Calendar {

	private AppointmentTree tree;

	/**
	 * Initialization of a new Enhanced Tree for the Calendar
	 */
	public Assignment() {
		tree = new AppointmentTree();
	}

	/**
	 * Returns a list of Appointments at the given location, or an empty list if
	 * no appointments occur at that location.
	 * 
	 * @throws IllegalArgumentException
	 *             when a null value is input as a parameter
	 */
	@Override
	public List<Appointment> getAppointments(String location) {
		if (location == null) {
			throw new IllegalArgumentException("Location was null");
		}
		return (tree.hasMapKey(location)) ? tree.getMap(location) : new ArrayList<Appointment>();
	}

	/**
	 * Returns the next appointment at or after the given time, or null if no
	 * appointment exists within this time frame
	 * 
	 * @throws IllegalArgumentException
	 *             when a null value is input as a parameter
	 */
	@Override
	public Appointment getNextAppointment(Date when) {
		if (when == null) {
			throw new IllegalArgumentException("time was null");
		}
		return tree.getNextEntry(when);
	}

	/**
	 * Returns the next appointment that occurs at or after the given time, and
	 * at the given location, or null if no appointments exist
	 * 
	 * @throws IllegalArgumentException
	 *             when a null value is input as a parameter
	 */
	@Override
	public Appointment getNextAppointment(Date when, String location) {
		if (when == null || location == null) {
			throw new IllegalArgumentException("time was null");
		}
		return tree.getNextEntry(when, location);
	}

	@Override
	public void add(String desc, Date when, String loc) {
		if (desc == null || when == null || loc == null) {
			throw new IllegalArgumentException("Argument was null");
		}
		tree.insertEntry(desc, when, loc);
	}

	/**
	 * Removes the exact appointment that is passed as a parameter from the
	 * Calendar.
	 * 
	 * @throws IllegalArgumentException
	 *             when a null value is input as a parameter
	 */
	@Override
	public void remove(Appointment appointment) {
		if (appointment == null) {
			throw new IllegalArgumentException("Appointment was null");
		}
		tree.removeEntry(appointment);

	}
}
