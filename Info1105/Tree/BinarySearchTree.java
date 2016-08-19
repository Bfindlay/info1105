package Tree;

import java.util.Iterator;

public class BinarySearchTree<E> {

	private int size;

	private TreeNode root;

	public BinarySearchTree() {
		this.size = 0;
		this.root = null;
	}

	public void addRoot(E element) {
		// TODO fix if root exists
		if (this.root == null) {
			TreeNode root = new TreeNode(element, null, null, null);
			this.root = root;
			size++;
		}
	}

	public void insert(E element) {
		TreeNode<E> node;
		E leftdata;
		E rightData;
	}

	public void addLeft(E element) {
		// TODO fix constructor and set parent of current node
		TreeNode<E> node = new TreeNode<E>(element, null, null, null);

		if (root.left() == null) {
			root.setLeft(node);
		} else {
			TreeNode<E> currentNode = root.left();
			for (int i = 0; i < size; i++) {
				if (currentNode.left() != null) {
					currentNode.setLeft(node);
				}
			}
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<E> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	public TreeNode<E> root() {
		return this.root;
	}

	public TreeNode<E> parent(TreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public TreeNode<E> children(TreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public int numChildren(TreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isInternal(TreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isExternal(TreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRoot(TreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

}
