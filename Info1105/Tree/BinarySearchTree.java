package Tree;

import java.util.Iterator;

public class BinarySearchTree<T> {

	private int size;

	private TreeNode<T, E> root;

	public BinarySearchTree() {
		this.size = 0;
		this.root = null;
	}

	public void addRoot(T element) {
		TreeNode<T> root = new TreeNode<>(element, null, null, null);
		this.root = root;
		size++;
	}

	public void insert(T element) {
		TreeNode<T> insert_node = new TreeNode<>(element, null, null, null);
		if (this.root == null) {
			this.root = insert_node;
		} else {
			TreeNode<T> current_node = this.root;
			for (int i = 0; i < size; i++) {
				int cmp = current_node.compareTo(insert_node);

			}
		}
		size++;
	}

	public void addLeft(T element) {
		// TODO fix constructor and set parent of current node

	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<T> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	public TreeNode<T> root() {
		return this.root;
	}

	public TreeNode<T> parent(TreeNode<T> p) throws IllegalArgumentException {
		return p.Parent();
	}

	public TreeNode<T> children(TreeNode<T> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public int numChildren(TreeNode<T> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isInternal(TreeNode<T> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isExternal(TreeNode<T> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRoot(TreeNode<T> p) throws IllegalArgumentException {
		return (parent(p) == null);
	}

}
