package week6;

public class ArrayHeapChecker {

	// Check if the given array is a representation of a binary tree
	public static boolean isBinaryTree(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				int parent = (i - 1) / 2;
				if (array[parent] == null)
					return false;
			}
		}
		return true;
	}

	public static boolean isCompleteBinaryTree(Integer[] arr) {
		if (!isBinaryTree(arr)) {
			return false;
		}
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] == null && arr[i + 1] != null) {
				return false;
			}
		}
		return true;
	}

	// Check if the given array is a min-heap
	public static boolean isMinHeap(Integer[] arr) {
		if (!isCompleteBinaryTree(arr)) {
			return false;
		}
		for (int i = 0; i < arr.length - (2 * i + 2); i++) {
			if (!(arr[i] <= arr[2 * i + 1]) || !(arr[i] <= arr[2 * i + 2])) {
				return false;
			}
		}
		return true;
	}

}
