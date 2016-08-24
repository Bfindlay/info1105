package week5;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedBinaryTreeTest {

	@Test
	public void testMirrorSingle() {
		LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();
		lbt.addRoot(20);
		LinkedBinaryTree<Integer> mirr = lbt.mirror();
		assertEquals(lbt.root.getElement(), mirr.root.getElement());

	}

	@Test
	public void testMirrorTwo() {
		LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();
		lbt.addRoot(20);

		int test = lbt.root.getElement();
		lbt.addLeft(lbt.root, 10);
		lbt.addRight(lbt.root, 40);
		assertEquals(20, test);
		int leftelem = lbt.root.getLeft().getElement();
		int rightelem = lbt.root.getRight().getElement();
		assertEquals(10, leftelem);
		assertEquals(40, rightelem);

		LinkedBinaryTree<Integer> mirror = lbt.mirror();
		int test2 = mirror.root.getElement();
		int testLeft = mirror.root.getLeft().getElement();
		int testRight = mirror.root.getRight().getElement();

		int currentLeft = lbt.root.getLeft().getElement();
		int currentRight = lbt.root.getRight().getElement();

		assertEquals(20, test2);
		assertEquals(40, testLeft);
		assertEquals(10, testRight);
		assertEquals(currentRight, testLeft);
		assertEquals(currentLeft, testRight);

	}

	@Test
	public void TestMirrorMany() {
		LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();
		lbt.addRoot(20);

		LinkedBinaryTree<Integer> mirror = lbt.mirror();

	}

}
