package assignment1;

import java.util.Date;

public class Entry implements Appointment {
	private String description;
	private String location;
	private Date date;

	public Entry(String d, String l, Date t) {
		this.description = d;
		this.location = l;
		this.date = t;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public Date getStartTime() {
		return this.date;
	}

}
