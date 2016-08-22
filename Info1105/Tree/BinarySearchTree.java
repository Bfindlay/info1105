package Tree;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> {

	private int size;

	private TreeNode<T> root;

	public BinarySearchTree() {
		this.size = 0;
		this.root = null;
	}

	public void addRoot(T element) {
		TreeNode<T> root = new TreeNode<T>(element, null, null, null);
		this.root = root;
		size++;
	}

	// TODO fix this mess
	public void insert(T element) {
		TreeNode<T> insert_node = new TreeNode<>(element, null, null, null);
		if (this.root == null) {
			this.root = insert_node;
		} else {
			TreeNode<T> current_node = this.root;
			for (int i = 0; i < size; i++) {
				// TODO add case for cmp = 0; don't increase size;
				int cmp = current_node.compareTo(insert_node);
				if (cmp == -1) {
					if (current_node.left() == null) {
						current_node.setLeft(insert_node);
						insert_node.setParent(current_node);
						size++;
						break;
					} else {
						current_node = current_node.left();
					}
				} else if (cmp == 1) {
					if (current_node.right() == null) {
						current_node.setRight(insert_node);
						insert_node.setParent(current_node);
						size++;
						break;
					} else {
						current_node = current_node.right();
					}
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

	public List<TreeNode<T>> children(TreeNode<T> p) throws IllegalArgumentException {
		List<TreeNode<T>> list = new ArrayList<TreeNode<T>>();
		if (p.left() != null)
			list.add(p.left());
		if (p.right() != null)
			list.add(p.right());
		return list;
	}

	public int numChildren(TreeNode<T> p) throws IllegalArgumentException {
		// TODO fix null pointer when no root node exists;
		int numChildren = 0;
		if (p.left() != null)
			numChildren++;
		if (p.right() != null)
			numChildren++;
		return numChildren;
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