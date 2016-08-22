package Tree;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeTest {

	@Test
	public void testCostructor() {
		BinarySearchTree<Integer> bt = new BinarySearchTree<>();
		assertEquals(null, bt.root());
	}

	@Test
	public void testCompare() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		TreeNode<Integer> newNode = new TreeNode(10, null, null, null);
		bst.addRoot(10);
		assertEquals(0, bst.root().compareTo(newNode));
	}

	@Test
	public void TestSize() {
		BinarySearchTree<Integer> bt = new BinarySearchTree<>();
		assertEquals(0, bt.size());
		bt.addRoot(10);
		assertEquals(1, bt.size());
	}

	@Test
	public void testChildren() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		List<TreeNode<Integer>> list = new ArrayList<>();
		bst.addRoot(10);
		bst.insert(11);
		bst.insert(9);
		list = bst.children(bst.root());
		int test1 = list.get(0).element();
		int test2 = list.get(1).element();
		assertEquals(9, test1);
		assertEquals(11, test2);
	}

	@Test
	public void testNumChildren() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.addRoot(10);
		assertEquals(0, bst.numChildren(bst.root()));
		bst.insert(11);
		assertEquals(1, bst.numChildren(bst.root()));
		bst.insert(9);
		assertEquals(2, bst.numChildren(bst.root()));
		bst.insert(12);
		assertEquals(2, bst.numChildren(bst.root()));
	}

	@Test
	public void testInsert() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.addRoot(10);
		assertEquals(1, bst.size());
		bst.insert(9);
		assertEquals(2, bst.size());
		int element = bst.root().left().element();
		assertEquals(9, element);
		bst.insert(11);
		assertEquals(3, bst.size());
		int element_2 = bst.root().right().element();
		assertEquals(11, element_2);
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
