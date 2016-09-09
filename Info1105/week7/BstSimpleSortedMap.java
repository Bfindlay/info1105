package week7;

public class BstSimpleSortedMap implements SimpleSortedMap {

	private class MySimpleEntry implements SimpleEntry {

		private final Integer key;
		private final String value;
		private MySimpleEntry left;
		private MySimpleEntry right;
		private MySimpleEntry parent;

		MySimpleEntry(Integer key, String value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		@Override
		public Integer getKey() {
			return key;
		}

		@Override
		public String getValue() {
			return value;
		}

		protected MySimpleEntry getLeft() {
			return left;
		}

		protected MySimpleEntry getRight() {
			return right;
		}

		protected MySimpleEntry getParent() {
			return parent;
		}

		protected void setLeft(MySimpleEntry entry) {
			left = entry;
		}

		protected void setRight(MySimpleEntry entry) {
			right = entry;
		}

		protected void setParent(MySimpleEntry entry) {
			parent = entry;
		}
	}

	private int size;
	private MySimpleEntry root;

	public BstSimpleSortedMap() {
		size = 0;
		root = null;
	}

	// attaches the subtree rooted at 'child', to the parent
	private void attachLeft(MySimpleEntry parent, MySimpleEntry child) {
		if (child != null) {
			child.setParent(parent);
		}
		if (parent != null) {
			parent.setLeft(child);
		}
	}

	// attaches the subtree rooted at 'child', to the parent
	private void attachRight(MySimpleEntry parent, MySimpleEntry child) {
		if (child != null) {
			child.setParent(parent);
		}
		if (parent != null) {
			parent.setRight(child);
		}
	}

	//////////////////////////
	// Map ADT methods:
	//////////////////////////

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public String get(Integer k) {
		return get(k, root);
	}

	private String get(Integer k, MySimpleEntry subtreeRoot) {
		// base case: empty subtree
		if (subtreeRoot == null) {
			// k isn't in this subtree
			return null;
		}

		// base case: k matches the key in the current entry
		if (k.compareTo(subtreeRoot.getKey()) == 0) {
			// TODO: return the value
			return subtreeRoot.getValue();
		}
		// recursive case: k < the current entry
		else if (k.compareTo(subtreeRoot.getKey()) < 0) {
			// TODO: return the result of recursing to the left
			return get(k, subtreeRoot.getLeft());
		}
		// recursive case: k > the current entry
		else {
			// TODO: return the result of recursing to the right
			return get(k, subtreeRoot.getRight());
		}
	}

	@Override
	public String put(Integer k, String v) {
		// We will implement this recursively.

		// If the key already exists, we will need to return the old value
		String oldValue = get(k);

		// Replace the subtree rooted at 'root' with
		// the resulting subtree after doing the put
		root = put(k, v, root);

		return oldValue;
	}

	// Recursive helper method for put
	// Returns the subtree rooted at entry after performing the put
	private MySimpleEntry put(Integer key, String val, MySimpleEntry x) {
		if (x == null) {
			size++;
			return new MySimpleEntry(key, val);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(key, val, x.left);
		} else if (cmp > 0) {
			x.right = put(key, val, x.right);
		} else {
			// cmp == 0
			MySimpleEntry entry = new MySimpleEntry(key, val);
			entry.setParent(entry);
			int comp = key.compareTo(entry.getParent().getKey());
			if (comp < 0)
				entry.getParent().setLeft(entry);
			else if (comp > 0)
				entry.getParent().setRight(entry);
			MySimpleEntry left = (x.getLeft() != null) ? x.getLeft() : null;
			MySimpleEntry right = (x.getRight() != null) ? x.getRight() : null;
			entry.setLeft(left);
			entry.setRight(right);
			if (left != null)
				left.setParent(entry);
			if (right != null)
				right.setParent(entry);
			return entry;
		}
		return x;
	}

	public MySimpleEntry max(MySimpleEntry x) {
		return (x.getRight() != null) ? max(x.getRight()) : x;
	}

	@Override
	public String remove(Integer k) {
		String old = get(k);

		remove(this.root, k).getValue();
		return old;

	}

	public MySimpleEntry remove(MySimpleEntry start, Integer elem) {
		// If the element we want to delete wasn't found
		if (start == null) {
			// Go back up the recursive loop, but let our object know that the
			// element we wanted to delete wasn't found
			return null;
		}

		// Compare the current node's element to the element we're looking for
		int comparison = start.key.compareTo(elem);

		// If the deletion will happen somewhere down the left tree
		if (comparison > 0) {
			// Attempt to delete down the left tree
			start.left = remove(start.left, elem);
		}
		// If the deletion will happen somewhere down the right tree
		else if (comparison < 0) {
			// Attempt to delete down the right tree
			start.right = remove(start.right, elem);
		}
		// If we are at the element we want to delete
		else {
			// If the node we want to delete has two children
			if (start.left != null && start.right != null) {
				// Back up pointers
				MySimpleEntry left = start.left;
				MySimpleEntry right = start.right;

				// Replace the current element with the smallest element in the
				// right subtree
				start = removeMin(start.right, start);

				// Back up pointer
				MySimpleEntry minRight = start.right;

				// Fix pointers
				start.left = left;
				start.right = right;

				// We need to fix start.right if it points to the node we just
				// moved
				if (start.right.key == start.key) {
					start.right = minRight;
				}
			}
			// If the node we want to delete is a leaf
			else if (start.left == null && start.right == null) {
				// Delete the current node from the tree
				start = null;
			}
			// If the node we want to delete just has a left child
			else if (start.left != null) {
				start = start.left;
			}
			// If the node we want to delete just has a right child
			else {
				start = start.right;
			}
		}

		return start;
	}

	/**
	 * This method removes the minimum node that comes after the given starting
	 * node.
	 *
	 * @param start
	 *            The node from which to start the removal process
	 * @param parent
	 *            The parent node of "start"
	 * @return Returns a MySimpleEntry with: element: The element of the removed
	 *         node left: null right: The right subtree of the deleted node
	 */
	public MySimpleEntry removeMin(MySimpleEntry start, MySimpleEntry parent) {
		// If there is nothing to traverse or remove
		if (start == null) {
			return null;
		}

		// If we've found the minimum node
		if (start.left == null) {
			// Save the important values from the node
			// T elem = start.element;
			// MySimpleEntry deletedRight = start.right;

			// Rewire nodes
			if (parent != root) {
				parent.left = start.right; // deletedRight;
			}

			// start = null;

			// Return a new node that follows this method's specifications
			return start;
		}

		// Recurse until we get to the minimum node
		return removeMin(start.left, start);
	}

	@Override
	public Iterable<Integer> keySet() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Iterable<String> values() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Iterable<SimpleEntry> entrySet() {
		throw new java.lang.UnsupportedOperationException();
	}

	//////////////////////////
	// SortedMap ADT methods:
	//////////////////////////

	@Override
	public SimpleEntry firstEntry() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry lastEntry() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry ceilingEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry floorEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry lowerEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SimpleEntry higherEntry(Integer key) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Iterable<Integer> subMap(Integer fromKey, Integer toKey) {
		throw new java.lang.UnsupportedOperationException();
	}
}