package assignment1;

import java.util.Date;
import java.util.List;

public interface Calendar {

	// Return a list of all appointments at the given location (at any time)
	// If there are no such appointments, return an empty list
	// Throws IllegalArgumentException if the argument is null
	public List<Appointment> getAppointments(String location);

	// Return the next appointment at or after the given time (in any location)
	// If there is no such appointment, return null
	// Throws IllegalArgumentException if the argument is null
	public Appointment getNextAppointment(Date when);

	// Return the next appointment at or after the given time, at that location
	// If there is no such appointment, return null
	// Throws IllegalArgumentException if any argument is null
	public Appointment getNextAppointment(Date when, String location);

	// Create a new appointment in the calendar
	// Throws IllegalArgumentException if any argument is null
	public void add(String description, Date when, String location);

	// Remove an appointment from the calendar
	// Throws IllegalArgumentException if the argument is null
	public void remove(Appointment appointment);
}