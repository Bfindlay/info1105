package assignment1.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import assignment1.Appointment;
import assignment1.Calendar;
import assignment1.Assignment;

public class AssignmentTestOLD {

	// Set up JUnit to be able to check for expected exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// This will make it a bit easier for us to make Date objects
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// Helper method to build the example calendar
	private Calendar buildTinyExample() {
		Calendar calendar = new AssignmentOLD();
		try {
			calendar.add("A", df.parse("2016/09/03 09:00:00"), "SIT lab 117");
			calendar.add("B", df.parse("2016/09/03 16:00:00"), "SIT lab 117");
			calendar.add("C", df.parse("2016/09/03 16:00:00"), "SIT lab 118");
			calendar.add("D", df.parse("2016/09/03 18:00:00"), "SIT lab 117");
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
		return calendar;
	}

	@Test
	public void testGetAppointmentsExample() {

		Calendar calendar = buildTinyExample();

		// This should be a list containing Appointments A, B and D
		List<Appointment> appointments = calendar.getAppointments("SIT lab 117");

		// For this example, we'll just check the descriptions.
		// Good testing should be more thorough!
		List<String> descriptions = new ArrayList<String>();
		for (Appointment a : appointments) {
			descriptions.add(a.getDescription());
		}
		// Sorting the objects before we compare the list, since the assignment
		// doesn't require the output to be in any particular order
		Collections.sort(descriptions);
		assertEquals(Arrays.asList("A", "B", "D"), descriptions);
	}

	@Test
	public void testGetNextAppointment() throws ParseException {
		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");
		Date f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 13:00:00");
		Date g = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2014/01/03 13:00:00");

		Calendar calendar = new AssignmentOLD();
		calendar.add("Exam", a, "SIT");
		calendar.add("Lunch", b, "SIT");
		calendar.add("Second lunch", b, "Uni");
		calendar.add("Home Time", c, "BUS");
		assertEquals("SIT", calendar.getNextAppointment(a, "SIT").getLocation());
		assertEquals("SIT", calendar.getNextAppointment(b).getLocation());
		assertNull(calendar.getNextAppointment(g));
		assertNull(calendar.getNextAppointment(b, "HOME"));
		thrown.expect(IllegalArgumentException.class);
		calendar.getNextAppointment(null, null);
		calendar.add("Test", g, "Home");
		assertEquals("Home", calendar.getNextAppointment(g));
	}

	@Test
	public void testLocations() throws ParseException {
		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");

		Calendar calendar = new AssignmentOLD();
		calendar.add("Exam", a, "SIT");
		calendar.add("Exam", b, "SIT");
		calendar.add("Exam", b, "SIT");
		calendar.add("DERP", b, "HOME");
		calendar.add("Exam", b, "SIT");
		calendar.add("Lunch", c, "Broadway");
		calendar.add("Lunch", d, "SIT");
		calendar.add("Exam", d, "HOME");
		calendar.add("Lunch", e, "Carslaw");
		assertEquals(5, calendar.getAppointments("SIT").size());
		assertEquals(1, calendar.getAppointments("Carslaw").size());
		Appointment app = calendar.getNextAppointment(d);
		calendar.remove(app);
		assertEquals(1, calendar.getAppointments("Broadway").size());
		Appointment ap = calendar.getNextAppointment(c);
		calendar.remove(ap);
		assertEquals(0, calendar.getAppointments("Broadway").size());
	}

	@Test
	public void testRemove() throws ParseException {
		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");

		Calendar calendar = new AssignmentOLD();
		calendar.add("Exam", a, "SIT");
		calendar.add("Exam", b, "SIT");
		calendar.add("Exam", b, "SIT");
		calendar.add("DERP", b, "HOME");
		calendar.add("Exam", b, "SIT");
		calendar.add("REMOVE", c, "Broadway");
		calendar.add("Lunch", d, "SIT");
		calendar.add("Exam", d, "HOME");
		calendar.add("Lunch", e, "Carslaw");
		assertEquals(1, calendar.getAppointments("Carslaw").size());
		Appointment app = calendar.getNextAppointment(e);
		calendar.remove(app);
		assertEquals(0, calendar.getAppointments("Carslaw").size());

		Appointment app2 = calendar.getNextAppointment(c);
		assertEquals("REMOVE", app2.getDescription());
		calendar.remove(app2);
		assertEquals("Lunch", calendar.getNextAppointment(c).getDescription());
		// Check for null argument exception
		thrown.expect(IllegalArgumentException.class);
		calendar.remove(null);

		// TODO remove appointment not in the list;

	}

	@Test
	public void testGetNextAppointmentExample() {

		Calendar calendar = buildTinyExample();

		// This could return either Appointment B, or Appointment C
		// It does not matter which one, as they are both correct.
		try {
			Appointment appointment = calendar.getNextAppointment(df.parse("2016/09/03 13:00:00"));
			String description = appointment.getDescription();
			assertTrue(description.equals("B") || description.equals("C"));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}

		// This should return null
		try {
			assertNull(calendar.getNextAppointment(df.parse("2020/01/01 13:00:00")));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}

	}

	@Test
	public void testGetNextAppointmentLocationExample() {

		Calendar calendar = buildTinyExample();

		// This should return Appointment B
		try {
			Appointment appointment = calendar.getNextAppointment(df.parse("2016/09/03 13:00:00"),
					"SIT lab 117");
			String description = appointment.getDescription();
			assertTrue(description.equals("B"));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
	}

	@Test
	public void testIllegalArgumentExample() {

		Calendar calendar = buildTinyExample();

		// Tell JUnit to expect an IllegalArgumentException
		thrown.expect(IllegalArgumentException.class);

		// This should cause an IllegalArgumentException to be thrown
		calendar.getNextAppointment(null);

	}

	@Test
	public void testGetNextAPpointmentLocation() throws ParseException {

		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");
		Date f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/04 11:00:00");
		Date g = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/04 12:00:00");
		Date h = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/04 15:00:00");
		Date i = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/05 09:59:59");
		Date j = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/06 12:00:00");

		Calendar calendar = new AssignmentOLD();

		calendar.add("A", a, "A");
		calendar.add("C", a, "A");
		calendar.add("F", b, "A");
		calendar.add("D", c, "A");
		calendar.add("Z", d, "A");
		calendar.add("G", a, "A");
		calendar.add("G", a, "A");
		calendar.add("B", a, "A");
		calendar.add("Z", a, "A");
		calendar.add("L", c, "B");
		calendar.add("AA", e, "D");

		calendar.add("GG", g, "B");
		calendar.add("HH", h, "B");
		calendar.add("JJ", j, "B");

		List<Appointment> list = calendar.getAppointments("B");
		list.stream().forEach(event -> System.out.println(event.getDescription()));

		Appointment ap = calendar.getNextAppointment(c, "L");
		assertNull(ap);

		Appointment ap2 = calendar.getNextAppointment(f, "B");
		assertEquals("GG", ap2.getDescription());

		Appointment ap3 = calendar.getNextAppointment(e, "B");
		assertNotNull(ap3);

		assertEquals("A", calendar.getNextAppointment(a, "A").getDescription());

		Appointment ap4 = calendar.getNextAppointment(j, "B");
		assertNotNull(ap4);
		assertEquals("JJ", ap4.getDescription());

	}

	@Test
	public void testInsertRemoveMany() throws ParseException {

		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");

		Calendar calendar = new Assignment();

		calendar.add("A", e, "SIT");
		Appointment app = calendar.getNextAppointment(a);
		assertEquals("A", app.getDescription());
		assertNotNull(app);
		calendar.remove(app);
		assertNull(calendar.getNextAppointment(e));

		// Add elements to the calendar
		for (int i = 0; i < 20; i++) {
			calendar.add("A", a, "Sydney");
		}

		List<Appointment> list = calendar.getAppointments("Sydney");
		assertEquals(20, list.size());

		Appointment a20 = list.get(19);

		list.stream().forEach(en -> {
			calendar.remove(en);
		});

		assertEquals(0, list.size());
		// Appointment a0 = list.get(0);
		// Appointment a1 = list.get(1);
		// Appointment a2 = list.get(2);
		// Appointment a3 = list.get(3);
		// Appointment a4 = list.get(4);
		// Appointment a5 = list.get(5);
		// Appointment a6 = list.get(6);
		// Appointment a7 = list.get(7);
		//
		// calendar.remove(a0);
		// calendar.remove(a1);
		// calendar.remove(a2);
		// calendar.remove(a3);
		// calendar.remove(a4);
		// calendar.remove(a5);
		// calendar.remove(a6);
		// calendar.remove(a7);
		//
		// list.stream().forEach(element -> {
		// System.out.println("deleted");
		// calendar.remove(element);
		// });

	}

}
