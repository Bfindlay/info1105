package week2;

// Don't edit this file

public abstract class SearchableArray<T extends Comparable<T>> {

	// The array of data
	protected T[] data;

	// construct from an array
	protected SearchableArray(T[] data) {
		this.data = data;
	}

	// fetch the element from the array at position i
	public T get(int i) {
		return data[i];
	}

	// set the element at position i
	public void set(int i, T element) {
		data[i] = element;
	}

	// methods marked 'abstract' are implemented in extending classes
	public abstract T search(T target);

}
