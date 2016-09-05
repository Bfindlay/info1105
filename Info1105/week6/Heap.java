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
		int temp;
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = (i - 1) / 2; j > 0; j--) {
				if (arr[i] < arr[j]) {
					// child is smaller than parent, switch them
					temp = arr[i];
					arr[i] = arr[(i - 1) / 2];
					arr[(i - 1) / 2] = temp;

					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		this.set(arr);
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
