package week6;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayHeapCheckerTest {

	@Test(timeout = 100)
	public void testIsBinaryTree_ExampleTest() {

		// These are all binary trees
		assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 0, 1, 2, 3, 4, 5, 6 }));
		assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 8, 8, 8, 8, 8, 8, 8 }));
		assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 0, 1, null, 3, 4, null, null }));
		assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 0, 1, 2, null, 4, null, 6 }));
		assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 0, null, null }));
		// assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 0 }));
		assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { null }));
		assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] {}));

		// These are not binary trees (because nodes exist which don't have
		// parents)
		assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] { null, 1, 2, 3, 4, 5, 6 }));
		assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] { 0, 1, null, 3, 4, 5, null }));
		assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] { 0, 1, null, null, 4, 5, 6 }));
		assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] { null, 0 }));
	}

	@Test
	public void testComplete() {
		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 1, 2, 5, 6, -1, 8, 11 }));
		// assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 1,
		// 2, 5, null, -1, 8, 11 }));

		assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 1, null, 5, 6, -18, 11 }));

		assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 1, 2, null, 6, -1, 8, 11 }));

		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { null }));

		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0, 1, 2, 3, 4, 5, 6 }));

		// test null tree true;
		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { null }));
		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] {}));
		// Test array size 3

		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { null, null, null }));
		assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { null, null, 0 }));
		assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { null, 0, null }));
		assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { null, 1, 1 }));

		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0, null, null }));
		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0, 0, 0 }));
		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0, 0, null }));
		// assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0,
		// null, 0 }));

		assertFalse(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0, null, 0 }));
		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0, null, null }));
		assertTrue(ArrayHeapChecker.isCompleteBinaryTree(new Integer[] { 0, 0, 0 }));
	}

	@Test
	public void testMinHeap() {
		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { 6, 7, 12, 10, 15, 17 }));
		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { 6, 7, 12, 10, 15, 17, 18 }));
		// assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { null, null,
		// null, null, null }));
		// assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { 0, null, null
		// }));
		assertFalse(ArrayHeapChecker.isMinHeap(new Integer[] { 6, 3, 12, 10, 15, 17 }));
		assertFalse(ArrayHeapChecker.isMinHeap(new Integer[] { 6, 3, 12 }));
		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { 6, 7, 8 }));
		assertFalse(ArrayHeapChecker.isMinHeap(new Integer[] { 4, 3, 2 }));

		assertFalse(ArrayHeapChecker.isMinHeap(new Integer[] { 4, 3, 2 }));
		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { null, null, null }));
		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { 0, 0, 0 }));

		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] {}));
		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { 1 }));
		assertFalse(ArrayHeapChecker.isMinHeap(new Integer[] { null, 0, null }));
		assertFalse(ArrayHeapChecker.isMinHeap(new Integer[] { 0, 0, 0 }));
		assertTrue(ArrayHeapChecker.isMinHeap(new Integer[] { null, null, null }));

	}

}
