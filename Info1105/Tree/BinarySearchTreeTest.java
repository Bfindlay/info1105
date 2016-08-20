package Tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void testCostructor() {
		BinarySearchTree<Integer> bt = new BinarySearchTree<>();
		assertEquals(null, bt.root());
	}

	@Test
	public void testCompare() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		TreeNode<Integer> newNode = new TreeNode<Integer>(10, null, null, null);
		TreeNode<Integer> testNode2 = new TreeNode<Integer>(9, null, null, null);
		TreeNode<Integer> testNode3 = new TreeNode<Integer>(11, null, null, null);
		bst.addRoot(10);
		assertEquals(0, bst.root().compareTo(newNode));
		// assertEquals(0, bst.root().element().compareTo(newNode.element()));
		// assertEquals(1, bst.root().element().compareTo(testNode2.element()));
		// assertEquals(-1,
		// bst.root().element().compareTo(testNode3.element()));

	}

	@Test
	public void testIsRoot() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.addRoot(10);
		assertEquals(true, bst.isRoot(bst.root()));

	}

	@Test
	public void testRootValue() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.addRoot(10);
		int element = bst.root().element();
		assertEquals(10, element);
	}

	@Test
	public void TestSize() {
		BinarySearchTree<Integer> bt = new BinarySearchTree<>();
		assertEquals(0, bt.size());
		bt.addRoot(10);
		assertEquals(1, bt.size());
	}

	@Test
	public void testAddRoot() {
		BinarySearchTree<Integer> bt = new BinarySearchTree<>();
		bt.addRoot(10);
		int element = bt.root().element();
		assertEquals(10, element);
	}

	@Test
	public void testIsEmpty() {
		BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>();
		assertEquals(true, bt.isEmpty());
		bt.addRoot(10);
		assertEquals(false, bt.isEmpty());
	}

	@Test
	public void testAddChild() {

	}
}
