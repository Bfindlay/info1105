package assignment2;

import java.util.ArrayList;
import java.util.List;

public class Assignment implements PrefixMap {

	// TODO implement a nested Node class for your linked tree structure
	private class Trie {

		/********** NESTED NODE CLASS ************/
		protected class Node {
			// TODO implement this
			private Node[] children;
			private String data;
			private Node parent;

			public Node() {
				this.children = new Node[4];
				this.data = null;
				this.parent = null;
			}

			public Node[] getChildren() {
				return this.children;
			}

			public String getData() {
				return this.data;
			}

			public void setData(String d) {
				this.data = d;
			}

			public Node getParent() {
				return this.parent;
			}

			public void setParent(Node node) {
				this.parent = node;
			}
		}

		/********* TRIE CLASS *************/

		private Node root;
		private int size;
		private int prefix;
		private int preFixLength;

		public Trie() {
			this.root = new Node();
			this.size = 0;
			this.prefix = 0;
			this.preFixLength = 0;
			this.root.setData("");
		}

		public int size() {
			return this.size;
		}

		public int prefixSum() {
			return this.preFixLength;
		}

		public String add(String key, String value) {
			if (key == null || value == null)
				throw new IllegalArgumentException();
			if (key.toCharArray().length == 0) {
				String old = this.root.getData();
				this.root.setData(value);
				return old;
			}
			return add(key, 0, this.root.getChildren(), value, root);

		}

		private String add(String k, int i, Node[] nodes, String value, Node parent) {
			this.preFixLength++;
			int index = parseIndex(k.charAt(i));
			if (nodes[index] != null) {
				// Index was not null (Value existed)
				// Check if last key in string
				if (i + 1 >= k.length()) {
					Node current = nodes[index];
					String oldData = current.getData();
					// Set new data
					current.setData(value);
					return oldData;
				}
				return add(k, i += 1, nodes[index].getChildren(), value, parent);
			} else {
				Node current = new Node();
				current.setParent(parent);
				this.prefix++;
				nodes[index] = current;
				// Add the index to the root
				if (i + 1 >= k.length()) {
					// Set new data
					current.setData(value);
					return null;
				}
				return add(k, i += 1, nodes[index].getChildren(), value, current);
			}
		}

		public String get(String key) {
			if (key.toCharArray().length == 0)
				return this.root.getData();
			return get(key, 0, this.root);
		}

		private String get(String key, int i, Node current) {

			int index = parseIndex(key.charAt(i));
			Node[] nodes = current.getChildren();
			if (nodes[index] != null) {
				current = nodes[index];
				if (i + 1 >= key.length()) {
					// Return the data at the last value of the string;
					return (current.getData() != null) ? current.getData() : null;
				}
				return get(key, i += 1, current);
			}
			return null;

		}

		public String remove(String key) {
			if (get(key) != null) {
				preFixLength -= key.length();
			}
			return remove(key, 0, this.root);
		}

		public String remove(String key, int i, Node current) {
			if (i >= key.length()) {
				return null;
			}
			int index = parseIndex(key.charAt(i));
			Node[] nodes = current.getChildren();
			if (nodes[index] != null) {
				current = nodes[index];
				if (i + 1 >= key.length()) {
					// Return the data at the last value of the string;
					String oldData = current.getData();
					Node parent = current.getParent();
					removeAll(current.getParent());
					current.setData(null);
					current = null;
					prefix--;
					return oldData;
				}
			}
			return remove(key, i += 1, current);
		}

		public void removeAll(Node current) {
			if (current.getData() != null) {
				return;
			}
			if (current.getData() == null) {
				Node parent = current.getParent();
				current.setData(null);
				current = null;
				prefix--;
				removeAll(parent);
			}
		}

		public List<String> getKeys(String pre) {

			List<String> list = new ArrayList<String>();
			String s = get(pre);
			if (s != null)
				list.add(pre);
			Node node = getNode(pre, 0, this.root);
			return getKeys(pre, list, node, new String());
		}

		public List<String> getKeys(String pre, List<String> list, Node current, String build) {
			if (current != null) {
				Node[] arr = current.getChildren();
				for (int i = 0; i < arr.length; i++) {
					build = pre + getChar(i);
					if (arr[i] != null) {
						if (arr[i].getData() != null) {
							list.add(build);
						}
						getKeys(build, list, arr[i], build);
					}
				}
			}
			return list;
		}

		public Node getNode(String key, int i, Node current) {
			if (key == "") {
				return this.root;
			}
			int index = parseIndex(key.charAt(i));
			Node[] nodes = current.getChildren();
			if (nodes[index] != null) {
				current = nodes[index];
				if (i + 1 >= key.length()) {
					// Return the data at the last value of the string;
					return (current != null) ? current : null;
				}
				return getNode(key, i += 1, current);
			}
			return null;
		}

		public int parseIndex(char k) {
			switch (k) {
			case 'A':
				return 0;
			case 'C':
				return 1;
			case 'G':
				return 2;
			case 'T':
				return 3;
			default:
				throw new MalformedKeyException();

			}
		}

		public char getChar(int i) {
			switch (i) {
			case 0:
				return 'A';
			case 1:
				return 'C';
			case 2:
				return 'G';
			case 3:
				return 'T';
			default:
				throw new MalformedKeyException();

			}
		}

		public int countPrefix() {
			return this.prefix;
		}

	}

	/*
	 * The default constructor will be called by the tests on Ed
	 */

	private Trie trie;

	public Assignment() {
		trie = new Trie();
	}

	@Override
	public int size() {
		return trie.size();
	}

	@Override
	public boolean isEmpty() {
		return (trie.size == 0);
	}

	@Override
	public String get(String key) {
		if (key == null)
			throw new IllegalArgumentException();
		return trie.get(key);
	}

	@Override
	public String put(String key, String value) {
		if (key == null || value == null)
			throw new IllegalArgumentException();
		String res = trie.add(key, value);
		if (res == null) {
			trie.size++;
		}
		return res;

	}

	@Override
	public String remove(String key) {
		if (key == null)
			throw new IllegalArgumentException();
		String res = trie.remove(key);
		if (res != null)
			trie.size--;
		return res;
	}

	@Override
	public int countKeysMatchingPrefix(String prefix) {
		if (prefix == null)
			throw new IllegalArgumentException();
		char[] c = prefix.toCharArray();
		for (char b : c) {
			trie.parseIndex(b);
		}
		return trie.getKeys(prefix).size();
	}

	@Override
	public List<String> getKeysMatchingPrefix(String prefix) {
		if (prefix == null)
			throw new IllegalArgumentException();
		for (char b : prefix.toCharArray()) {
			trie.parseIndex(b);
		}
		List<String> list = trie.getKeys(prefix);
		if (prefix == "") {
			list.remove(list.indexOf(""));
		}
		return list;
	}

	@Override
	public int countPrefixes() {
		return trie.countPrefix();
	}

	@Override
	public int sumKeyLengths() {
		return trie.prefixSum();
	}

}
