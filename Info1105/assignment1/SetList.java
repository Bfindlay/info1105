package assignment1;

import java.util.AbstractList;
import java.util.TreeSet;

public class SetList<Appointment> extends AbstractList<Appointment> {

	private int size;
	private TreeSet<Appointment> set;

	public SetList() {
		this.size = 0;
		set = new TreeSet<>();
	}

	public boolean add(Appointment a) {
		this.size++;
		return set.add(a);
	}

	public boolean delete(Appointment a) {
		return set.remove(a);
	}

	@Override
	public Appointment get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

}
