package week2;
// Don't edit this file

/**
 * This is a class which holds and can be compared using an int value. It counts
 * how many comparisons have been made in total across all instantiations.
 * 
 * Note: In practice, you would be more likely to make a custom Comparator
 * <T> class and pass that to the search method. We're using this so you can
 * focus on working on the algorithms without needing to think about
 * Comparators.
 * 
 * @author jgod5665
 */

public class ComparisonCounter implements Comparable<ComparisonCounter> {

	// a value to compare
	private int value;

	// the number of comparisons made between any instances of the class
	// static variables (class variables) are shared across all instances
	private static int comparisons = 0;

	// constructor
	public ComparisonCounter(int value) {
		this.value = value;
	}

	// get method to return the wrapped up object
	public int getValue() {
		return value;
	}

	// a static method (class method) which returns the number of comparisons
	// made between any instances of this class since the program started
	public static int getComparisons() {
		return comparisons;
	}

	// override the default compareTo method (which just compares the memory
	// addresses). Increments the comparison count, then returns
	// the result of calling the compareTo method on the contained objects.
	@Override
	public int compareTo(ComparisonCounter other) {
		comparisons += 1;
		return value - other.getValue();
	}

	// compare equality using the value
	@Override
	public boolean equals(Object other) {
		if (other.getClass() == this.getClass()) {
			comparisons += 1;
			return value == ((ComparisonCounter) other).getValue();
		}
		return false;
	}

	// override the default toString method, so the toString method of the
	// contained element is returned instead
	@Override
	public String toString() {
		return Integer.toString(value);
	}

}
