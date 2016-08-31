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

	// Check if the given array is a complete binary tree
	public static boolean isCompleteBinaryTree(Integer[] arr) {
		if (!isBinaryTree(arr)) {
			return false;
		}
		if (arr.length == 3 && arr[1] == null && arr[2] != null) {
			return false;
		}
		for (int i = 0; i < arr.length - 2; i++) {
			int parent = (i - 1) / 2;
			if (arr[parent] == null && arr[parent + 1] != null) {
				return false;
			}

		}
		return true;
	}

	public static boolean isCompleteBinaryTree2(Integer[] arr) {
		if (!isBinaryTree(arr)) {
			return false;
		}

		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] != null && arr[i] == null) {
				return false;
			}

		}
		return true;
	}

	// Check if the given array is a min-heap
	public static boolean isMinHeap(Integer[] array) {
		if (!isCompleteBinaryTree(array)) {
			return false;
		}
		// TODO: implement this
		return true;
	}

}
