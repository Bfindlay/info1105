package assignment1;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class AssignmentTest {

	@Test
	public void testGetNextAppointment() throws ParseException {
		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");
		Date f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 13:00:00");
		Date g = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2014/01/03 13:00:00");

		Calendar calendar = new Assignment();
		calendar.add("Exam", a, "SIT");
		calendar.add("Lunch", b, "SIT");
		calendar.add("Second lunch", b, "Uni");
		calendar.add("Home Time", c, "BUS");
		assertEquals("SIT", calendar.getNextAppointment(a, "SIT").getLocation());
		assertEquals("BUS", calendar.getNextAppointment(b).getLocation());
		assertNull(calendar.getNextAppointment(g));
		// assertNull(calendar.getNextAppointment(b, "HOME"));

	}

	@Test
	public void testLocations() throws ParseException {
		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");

		Calendar calendar = new Assignment();
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
		Appointment ap = calendar.getNextAppointment(b);
		calendar.remove(ap);
		assertEquals(0, calendar.getAppointments("Broadway").size());
	}

	@Test
	public void testGetAppointmentsExample() {

	}

	@Test
	public void testRemove() throws ParseException {
		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");

		Calendar calendar = new Assignment();
		calendar.add("Exam", a, "SIT");
		calendar.add("Exam", b, "SIT");
		calendar.add("Exam", b, "SIT");
		calendar.add("DERP", b, "HOME");
		calendar.add("Exam", b, "SIT");
		calendar.add("Lunch", c, "Broadway");
		calendar.add("Lunch", d, "SIT");
		calendar.add("Exam", d, "HOME");
		calendar.add("Lunch", e, "Carslaw");
		assertEquals(1, calendar.getAppointments("Carslaw").size());
		Appointment app = calendar.getNextAppointment(d);
		calendar.remove(app);
		assertEquals(1, calendar.getAppointments("Broadway").size());
	}

}
