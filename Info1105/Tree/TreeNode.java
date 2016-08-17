package Tree;

public class TreeNode<E> {

	private E element;
	private TreeNode<E> parent;
	private TreeNode<E> left;
	private TreeNode<E> right;

	public TreeNode(E element, TreeNode<E> parent, TreeNode<E> left, TreeNode<E> right) {
		this.element = element;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	public E element() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public TreeNode<E> Parent() {
		return parent;
	}

	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}

	public TreeNode<E> left() {
		return left;
	}

	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}

	public TreeNode<E> right() {
		return right;
	}

	public void setRight(TreeNode<E> right) {
		this.right = right;
	}
}
