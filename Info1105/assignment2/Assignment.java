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

			public void setKey(char c) throws Exception {
				int i = parseIndex(c);
				if (keys[i] != 0) {
					keys[i] = c;
				}

			}

			public Node[] getChildren() {
				return this.children;
			}

			public void setData(String d) {
				this.data = d;
			}
		}

		/********* TRIE CLASS *************/

		private Node root;
		private int size;

		public Trie() {
			this.root = new Node();
			this.size = 0;
			this.root.setData("");
		}

		public void add(String key, String value) throws Exception {

			add(key, 0, 0, this.root.getChildren());

		}

		private void add(String k, int lvl, int i, Node[] nodes) {
			if (k.length() > lvl) {
				return;
			}
			int index = parseIndex(k.charAt(i));

			if (node[index] != null) {
				// handle the recursion;
				add(k, lvl++, index, i++);
			} else {
				// Add the index to the root
				root[index[i]].setKey(k.charAt(i));
				add(k, lvl++, index, i++);
			}
		}

		public int parseIndex(char k) throws Exception {

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
	public Assignment() {
		// TODO Initialise your data structure here
		Trie t = new Trie();
	}

	@Override
	public int size() {
		// TODO Implement this, then remove this comment
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Implement this, then remove this comment
		return false;
	}

	@Override
	public String get(String key) {
		// TODO Implement this, then remove this comment
		return null;
	}

	@Override
	public String put(String key, String value) {
		// TODO Implement this, then remove this comment
		return null;
	}

	@Override
	public String remove(String key) {
		// TODO Implement this, then remove this comment
		return null;
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
