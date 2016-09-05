package week6;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeapTest {

	@Test
	public void testBuildHeap() {
		Heap heap = new Heap();
		int[] test = { 6, 7, 12, 10, 15, 17, 18 };
		int[] test2 = { 6, 3, 2 };
		int[] test3 = { 17, 10, 15, 6, 7, 12 };
		// heap.heapify(test);
		// heap.heapify(test2);
		heap.heapify(test3);

		for (int i : heap.heap) {
			System.out.print(i + " ");
		}
	}

}
