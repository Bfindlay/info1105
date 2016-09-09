package assignment1;

import java.util.Date;

/**
 * 
 * @author Brett
 *
 */

public class Entry implements Appointment {

	private final String DESCRIPTION;
	private final String LOCATION;
	private final Date DATE;

	public Entry(String desc, String loc, Date date) {
		this.DESCRIPTION = desc;
		this.LOCATION = loc;
		this.DATE = date;
	}

	@Override
	public String getDescription() {
		return this.DESCRIPTION;
	}

	@Override
	public String getLocation() {
		return this.LOCATION;
	}

	@Override
	public Date getStartTime() {
		return this.DATE;
	}

}
