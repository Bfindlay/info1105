package Tree;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {
	// GENERIC METHODS
	public int size();

	public boolean isEmpty();

	public Iterator<E> iterator();

	public Iterable<E> positions();

	// ACCESS METHODS
	public TreeNode<E> root();

	public TreeNode<E> parent(TreeNode<E> p) throws IllegalArgumentException;

	public TreeNode<E> children(TreeNode<E> p) throws IllegalArgumentException;

	public int numChildren(TreeNode<E> p) throws IllegalArgumentException;

	// Query Methods

	public boolean isInternal(TreeNode<E> p) throws IllegalArgumentException;

	public boolean isExternal(TreeNode<E> p) throws IllegalArgumentException;

	public boolean isRoot(TreeNode<E> p) throws IllegalArgumentException;

}
