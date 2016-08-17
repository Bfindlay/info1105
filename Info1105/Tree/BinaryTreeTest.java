package Tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeTest {

	@Test
	public void testCostructor() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		assertEquals(null, bt.root());
	}

	@Test
	public void TestSize() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		assertEquals(0, bt.size());
		bt.addRoot(10);
		assertEquals(1, bt.size());
	}

	@Test
	public void testAddRoot() {
		BinaryTree<Integer> bt = new BinaryTree<>();
		bt.addRoot(10);
		int element = bt.root().element();
		assertEquals(10, element);
	}

	@Test
	public void testIsEmpty() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		assertEquals(true, bt.isEmpty());
		bt.addRoot(10);
		assertEquals(false, bt.isEmpty());
	}

	@Test
	public void testAddChild() {

	}
}
