package assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrieTest {

	@Test
	public void testTrie() {
		Trie t = new Trie();
		t.insert("GAT");
		System.out.println(t.search("GAT"));
	}

}
