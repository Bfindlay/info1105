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
				System.out.println("setkeychar " + c);
				int i = parseIndex(c);
				System.out.println("set key " + i);
				keys[i] = c;

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

		public int size() {
			return this.size;
		}

		public void add(String key, String value) {
			System.out.println(key + " " + value);
			add(key, 0, this.root.getChildren(), value);

		}

		private void add(String k, int i, Node[] nodes, String value) {
			System.out.println("level " + i);
			if (i >= k.length()) {
				return;
			}
			int index = parseIndex(k.charAt(i));

			System.out.println("index " + index);

			if (nodes[index] != null) {
				// handle the recursion;
				System.out.println("index not null");
				add(k, i += 1, nodes[index].getChildren(), value);
			} else {
				System.out.println("index null, adding the value");
				Node current = new Node();
				nodes[index] = current;
				// Add the index to the root
				current.setKey(k.charAt(index));
				this.size++;
				System.out.println("size is " + size);
				if (i + 1 >= k.length() - 1) {
					System.out.println("set the data");
					current.setData(value);
				}
				add(k, i += 1, nodes[index].getChildren(), value);
			}
		}

		private String get(String key) {

			return null;
		}

		public int parseIndex(char k) {
			System.out.println(k);
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
		System.out.println("adding " + key);
		System.out.println("adding " + value);
		// TODO Implement this, then remove this comment

		trie.add(key, value);

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
