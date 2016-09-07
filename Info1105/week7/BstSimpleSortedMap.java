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

		remove(k, root);
		return null;
	}

	public MySimpleEntry remove(Integer k, MySimpleEntry x) {

		if (x == null)
			return null;
		int cmp = k.compareTo(x.getKey());
		if (cmp == 0) {
			// BINGO found the key, remove it
			if (x.getLeft() != null && x.getRight() != null) {
				// find position r in order predecessor of P
				// swap r and p
				// delete R from tree
				MySimpleEntry r = max(x);
				MySimpleEntry currentParent = x.getParent();
				MySimpleEntry currentLeft = x.getLeft();
				MySimpleEntry currentRight = x.getRight();

				// Remove the reference to r from its parent
				r.getParent().setRight(null);
				// move r to position p
				r.setParent(currentParent);
				r.setLeft(currentLeft);
				r.setRight(currentRight);
				currentRight.setParent(r);
				currentLeft.setParent(r);

			}
			if (x.getLeft() == null && x.getRight() == null) {
				int comp = x.getKey().compareTo(x.getParent().getKey());
				if (comp < 0) {
					x.getParent().setLeft(null);
					x.setParent(null);
				} else if (comp > 0) {
					x.getParent().setRight(null);
				}
			}

		} else if (cmp > 0) {
			remove(k, x.getRight());
		} else {
			return remove(k, x.getLeft());
		}
		size--;
		return x;
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