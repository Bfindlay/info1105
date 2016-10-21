package assignment2;

import java.util.Arrays;
import java.util.List;

public class Assignment implements PrefixMap {

	// TODO implement a nested Node class for your linked tree structure
	private class Trie {

		/********** NESTED NODE CLASS ************/
		protected class Node {
			// TODO implement this
			private Node[] children;
			private String data;
			private char[] keys;

			public Node() {
				this.keys = new char[4];
				this.children = new Node[4];
				this.data = null;
			}

			public void setKey(char c) {
				int i = parseIndex(c);
				keys[i] = c;

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
		}

		/********* TRIE CLASS *************/

		private Node root;
		// private int size;
		private int size;

		public Trie() {
			this.root = new Node();
			this.size = 0;
			// this.prefix = 0;
			this.root.setData("");
		}

		public int size() {
			return this.size;
		}

		public String add(String key, String value) {
			if (key == null || value == null)
				throw new IllegalArgumentException();
			if (key.toCharArray().length == 0) {
				String old = this.root.getData();
				this.root.setData(value);
				return old;
			}
			return add(key, 0, this.root.getChildren(), value);

		}

		private String add(String k, int i, Node[] nodes, String value) {

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
				return add(k, i += 1, nodes[index].getChildren(), value);
			} else {
				Node current = new Node();
				nodes[index] = current;
				// Add the index to the root
				current.setKey(k.charAt(i));
				this.size++;
				if (i + 1 >= k.length()) {
					// Store old data;
					// Set new data
					// this.size++;
					current.setData(value);
					return null;
				}
				return add(k, i += 1, nodes[index].getChildren(), value);
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
			return remove(key, 0, this.root);
		}

		public String remove(String key, int i, Node current) {
			int index = parseIndex(key.charAt(i));
			Node[] nodes = current.getChildren();
			if (nodes[index] != null) {
				current = nodes[index];
				if (i + 1 >= key.length()) {
					// Return the data at the last value of the string;
					String oldData = current.getData();
					current.setData(null);
					nodes[index] = null;
					this.size--;
					return oldData;
				}
				return remove(key, i += 1, current);
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

	}

	/*
	 * The default constructor will be called by the tests on Ed
	 */

	private Trie trie;

	public Assignment() {
		// TODO Initialise your data structure here
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
		return trie.add(key, value);

	}

	@Override
	public String remove(String key) {
		if (key == null)
			throw new IllegalArgumentException();
		return trie.remove(key);
	}

	@Override
	public int countKeysMatchingPrefix(String prefix) {
		// TODO Implement this, then remove this comment
		return 0;
	}

	@Override
	public List<String> getKeysMatchingPrefix(String prefix) {
		// TODO Implement this, then remove this comment
		return null;
	}

	@Override
	public int countPrefixes() {
		// TODO Implement this, then remove this comment
		return 0;
	}

	@Override
	public int sumKeyLengths() {
		// TODO Implement this, then remove this comment
		return 0;
	}

}
