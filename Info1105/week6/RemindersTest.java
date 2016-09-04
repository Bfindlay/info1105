package week6;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RemindersTest {

	@Test
	public void testBasicReminder() throws ParseException {
		Reminders reminder = new Reminders();

		Date a = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 08:00:00");
		Date b = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date c = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 15:00:00");
		Date d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 09:59:59");
		Date e = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 12:00:00");
		Date f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 13:00:00");
		Date g = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2014/01/03 13:00:00");

		Date t1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 10:00:00");
		Date t2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 11:30:00");
		Date t3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 14:30:00");
		Date t4 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/02 16:00:00");
		Date t5 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2010/01/03 10:00:00");

		reminder.setReminder(t1, "Have Breakfast");
		reminder.setReminder(t2, "Call Janet about carpet");
		reminder.setReminder(t3, "Pick up Dry cleaning");
		reminder.setReminder(t4, "Watch 'the Block'");
		reminder.setReminder(t5, "Have a different Breakfast");

		List<String> list = reminder.getReminders(b);

		for (String l : list) {
			System.out.println(l);
		}
		System.out.println("___________");
		List<String> list1 = reminder.getReminders(c);

		for (String l : list1) {
			System.out.println(l);
		}
	}

}
