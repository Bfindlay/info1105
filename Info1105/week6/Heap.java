package week6;

public class Heap {

	public int[] heap;

	public Heap() {

	}

	public void set(int[] array) {
		this.heap = array;
	}

	public void heapify(int[] arr) {
		// TODO: bottom up heap construction, in-place
		heapify(arr, arr.length - 1);

	}

	private void heapify(int[] arr, int i) {
		if (i != 0) {

		}

	}

	public void heapSort(int[] array) {
		// convert the array to a heap
		this.heapify(array);
		// TODO: in-place sort
		// each time you remove-min, the heap will get one value smaller
		// and the sorted section at the end of the array will get one more
		// value
	}

}
