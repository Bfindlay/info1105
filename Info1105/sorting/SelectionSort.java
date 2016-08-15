package sorting;

public class SelectionSort {

	public static void main(String[] args) {
		int[] test = { 1, 55, 66, 45, 3, 2, 7, 89, 12, 43, 11, 17, 37, 232, 342, 74, 53, 88, 4, 15 };

		int[] sorted = selectionSort(test);
		for (int i : sorted) {
			System.out.print(i + " ");
		}

	}

	public static int[] selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[index])
					index = j;
			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		}
		return arr;
	}
}