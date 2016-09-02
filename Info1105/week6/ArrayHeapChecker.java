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

	public static boolean isMinHeap(Integer[] arr) {
		int n = arr.length;
		return isMinHeap(arr, 0, n);
	}

	public static boolean isMinHeap(Integer[] arr, int i, int n) {
		// If a leaf node
		if (i > (n - 2) / 2)
			return false;

		// If an internal node and is greater than its children, and
		// same is recursively true for the children
		if (arr[i] >= arr[2 * i + 1] && arr[i] >= arr[2 * i + 2] && isMinHeap(arr, 2 * i + 1, n)
				&& isMinHeap(arr, 2 * i + 2, n))
			return false;

		return true;
	}

}
