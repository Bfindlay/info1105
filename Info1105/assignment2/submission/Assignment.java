
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Brett Findlay
 * @SID 450258163
 *
 */
public class Assignment implements PrefixMap {

	/*** TRIE DATA STRUCTURE ***/
	private class Trie {

		/********** NESTED NODE CLASS ************/
		protected class Node {
			private Node[] children;
			private String data;
			private Node parent;

			public Node() {
				this.children = new Node[4];
				this.data = null;
				this.parent = null;
			}

			/**
			 * 
			 * @return the children of the current node
			 */
			public Node[] getChildren() {
				return this.children;
			}

			/**
			 * 
			 * @return data of a current node, or null
			 */
			public String getData() {
				return this.data;
			}

			/**
			 * 
			 * @param d
			 *            Sets the nodes data to the paramater given
			 */
			public void setData(String data) {
				this.data = data;
			}

			/**
			 * 
			 * @return the parent of the current node
			 */
			public Node getParent() {
				return this.parent;
			}

			/**
			 * 
			 * @param node
			 *            Sets the parent of a node to a Node
			 */
			public void setParent(Node node) {
				this.parent = node;
			}
		}

		/********* TRIE CLASS *************/

		private Node root;
		private int size;
		private int prefix;
		private int preFixLength;

		/**
		 * Initializes an empty Trie data structure of size 0
		 */
		public Trie() {
			this.root = new Node();
			this.size = 0;
			this.prefix = 0;
			this.preFixLength = 0;
			this.root.setData("");
		}

		/**
		 * 
		 * @return the number of key/value pairs input to the trie
		 */
		public int size() {
			return this.size;
		}

		/**
		 * 
		 * @param key
		 *            The data to be added as key in the tri (or the prefix)
		 * @param value
		 *            The data to be set as the value of the string
		 * @return Null if the entry did not exist, or data if data was
		 *         overwritten
		 */
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

		/**
		 * Recursive helper function for add method
		 */
		private String add(String k, int i, Node[] nodes, String value, Node parent) {

			int index = parseIndex(k.charAt(i));
			if (nodes[index] != null) {
				// Index was not null (Value existed)
				// Check if last key in string
				if (i + 1 >= k.length()) {
					Node current = nodes[index];
					String oldData = current.getData();
					// Set new data
					current.setData(value);
					// return the data that was replaced
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

		/**
		 * 
		 * @param key
		 *            String to query the trie with
		 * @return the data associated with the string, or null if it doesnt
		 *         exist
		 */
		public String get(String key) {
			if (key.toCharArray().length == 0)
				return this.root.getData();
			return get(key, 0, this.root);
		}

		/**
		 * Recursive helper function to traverse the sub tries to find the data
		 * of the query string
		 * 
		 * @return the Data or null if none is found
		 */
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

		/**
		 * 
		 * @param key
		 *            prefix of the data to be removed from the Trie
		 * @return the data that is removed, or null if no value was removed
		 */
		public String remove(String key) {
			if (get(key) != null) {
				preFixLength -= key.length();
			}
			return remove(key, 0, this.root);
		}

		/**
		 * Recursive helper function to search each subtrie to find the data
		 * that is associated with the prefix
		 */
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
					if (isExternal(current)) {
						if (key.length() % 2 == 0) {
							prefix--;
						}
						removeAll(current.getParent(), current);
					}
					current.setData(null);
					return oldData;
				}
			}
			return remove(key, i += 1, current);
		}

		/**
		 * Recursive function used to trail back up the trie to remove the child
		 * node from its parent
		 * 
		 * @param current
		 * @param prev
		 */
		public void removeAll(Node current, Node prev) {
			if (current.getData() == null) {
				Node parent = current.getParent();
				current.setData(null);
				// current = null;
				prefix--;
				removeAll(parent, current);
			} else {
				// Remove the children from the data node to remove all links
				if (current.getParent() != null) {
					Node parent = current.getParent();
					Node[] arr = parent.getChildren();
					for (int i = 0; i < 4; i++) {
						if (arr[i] == prev) {
							arr[i] = null;
						}
					}
				}

			}
		}

		/**
		 * Function used to identify if any given node is internal to the trie
		 * or if it has no further children dependencies
		 * 
		 * @param node
		 * @return true if it is external node, else returns false
		 */
		public boolean isExternal(Node node) {
			if (node == null || node.getChildren() == null)
				return true;
			Node[] arr = node.getChildren();
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != null) {
					if (arr[i].getData() != null) {
						return false;
					}
					isExternal(arr[i]);
				}
			}
			return true;
		}

		/**
		 * Returns the List of keys matching the prefix input
		 * 
		 * @param pre
		 *            The prefix to search for
		 * @return List of prefixes, or an empty lsit if none exist
		 */
		public List<String> getKeys(String pre) {
			List<String> list = new ArrayList<String>();
			String s = get(pre);
			if (s != null)
				list.add(pre);
			Node node = getNode(pre, 0, this.root);
			return getKeys(pre, list, node, new String());
		}

		/**
		 * Recursive helper function to search each subtrie to find each prefix
		 */
		public List<String> getKeys(String pre, List<String> list, Node current, String build) {
			if (current != null) {
				Node[] arr = current.getChildren();
				// Search each node cell of the current node
				for (int i = 0; i < arr.length; i++) {
					// Append the current levels common prefix to the prefix
					// obtained from the current cell
					build = pre + getChar(i);
					// Check if data is present in the cell, and then recursivly
					// traverse down the sub trie
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

		/**
		 * Helper function used to obtain the node from any level of the trie
		 */
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

		/**
		 * Parses a character into its corresponding array index
		 * 
		 * @param k
		 *            Character to be parsed into integer
		 */
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

		// Returns the character that is relevent to the index of the ith array
		// cell
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

		/**
		 * returns the number of prefix in the trie
		 */
		public int countPrefix() {
			return this.prefix;
		}

	}

	private Trie trie;

	public Assignment() {
		trie = new Trie();
	}

	/**
	 * returns the number of key value pairs in the trie
	 */
	@Override
	public int size() {
		return trie.size();
	}

	/**
	 * returns true or false depending on the trie state
	 */
	@Override
	public boolean isEmpty() {
		return (trie.size == 0);
	}

	/**
	 * returns the value assosciated with the key, or null
	 */
	@Override
	public String get(String key) {
		if (key == null)
			throw new IllegalArgumentException();
		char[] c = key.toCharArray();
		for (char b : c) {
			trie.parseIndex(b);
		}
		return trie.get(key);
	}

	/**
	 * adds the key value pair to the trie structure
	 */
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

	/**
	 * removes the key from the trie structure if it exists
	 */
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
		return String.join("", this.getKeysMatchingPrefix("")).length();
	}

}
