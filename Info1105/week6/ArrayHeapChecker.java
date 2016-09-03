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

	public static boolean isMinHeap(Integer[] array) {
		if (!isCompleteBinaryTree(array)) {
			return false;
		}
		return isMinHeap(array, array.length - 1);

	}

	public static boolean isMinHeap(Integer[] arr, int n) {
		System.out.println(n);
		if (!(n > 0))
			return true;
		if (arr[n] != null) {
			return ((arr[(n - 1) / 2].compareTo(arr[n]) > 0)) ? false : (!((n - 1) > 0)) ? true : isMinHeap(arr, n - 1);
		}
		return true;
	}

	public static boolean isMinHeap2(Integer[] array) {
		if (!isCompleteBinaryTree(array)) {
			return false;
		}
		for (int i = array.length - 1; i > 0; i--) {
			if (array[i] != null) {
				if (array[(i - 1) / 2].compareTo(array[i]) > 0) {
					return false;
				}
			}
		}
		return true;

	}

}
