package week2;

public class BinarySearchableArray<T extends Comparable<T>> extends SearchableArray<T> {

	// constructor
	public BinarySearchableArray(T[] data) {
		super(data); // call the constructor of the super-type as
						// SearchableArray<T>(data)
	}

	public T binarySearch(T[] data, T target, int lo, int hi) {
		if (hi < lo)
			return null;

		int mid = (hi + lo) / 2;
		int compare = target.compareTo(data[mid]);
		if (compare < 0)
			return binarySearch(data, target, lo, mid - 1);
		if (compare > 0)
			return binarySearch(data, target, mid + 1, hi);
		return data[mid];
	} // binarySearch

	public T search(T[] data, T target, int lo, int hi) {

		int mid = (hi + lo) / 2;
		if (hi < lo)
			return null;
		if (target.compareTo(data[mid]) == 0)
			return data[mid];

		return (target.compareTo(data[mid]) == -1) ? search(data, target, lo, (mid - 1))
				: search(data, target, (mid + 1), hi);

	}

	@Override
	public T search(T target) {

		T[] datas = super.data;
		return binarySearch(datas, target, 0, super.data.length - 1);

	}

}
