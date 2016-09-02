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
		return isMinHeap(arr, 0);
	}

	public static boolean isMinHeap(Integer[] arr, int n) {
		if ((n > (arr.length - 2) / 2) || arr.length == 1)
			return true;
		Integer root = arr[n];
		Integer left = arr[2 * n + 1];
		Integer right = arr[2 * n + 2];
		if (root == null && left != null) {
			return false;
		} else if (root == null && left == null && right == null) {
			return true;
		}
		if (left <= root || right <= root) {
			return false;
		} else {
			// chain the left tree and compare its root to children
			isMinHeap(arr, left);
			// chain the right tree and compare its root to children
			isMinHeap(arr, right);
		}
		return true;

	}

}
