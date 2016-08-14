package sorting;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = { 1, 4, 77, 8, 55, 3, 44, 5, 3, 99, 3, 54 };
		int[] sorted = bubblesort(arr);
		for (int i : sorted) {
			System.out.print(i + " ");
		}
	}

	public static int[] bubblesort(int[] arr) {
		int temp;
		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 1; j < arr.length - i; j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
}
