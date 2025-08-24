package narasimha.karumanchi.tries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//https://stackoverflow.com/questions/43011837/trie-implementation-in-java
public class TriesMain {
	public static void main(String[] args) {
		final TriesMain triesMain = new TriesMain();
		triesMain.run();
	}

	// Runners
	// 1. Trie run
	public void run() {
		final String word1 = "Home";
		final String word2 = "Hometown";
		final String word3 = "Hotel";

		final Trie trie = new Trie();
		trie.insert(word1);
		trie.insert(word2);
		trie.insert(word3);
		trie.printTrie();
		System.out.println(trie.search(word1));
		System.out.println(trie.startsWith("Ho"));
		System.out.println(trie.listAllChildren("Ho"));
	}

	// Algorithms
}

class TrieNode {
	private Map<Character, TrieNode> children;
	private boolean end;

	public TrieNode() {
		children = new TreeMap<>();
	}

	public TrieNode(boolean end) {
		super();
		children = new TreeMap<>();
		this.end = end;
	}

	public TrieNode(Map<Character, TrieNode> children, boolean end) {
		super();
		this.children = children;
		this.end = end;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public void setChildren(Map<Character, TrieNode> children) {
		this.children = children;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public void printNode() {
		final TrieNode current = this;
		for (final Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
			printNode(entry.getKey(), entry.getValue());
			System.out.println();
		}
	}

	private void printNode(char character, TrieNode node) {
		System.out.println(character);
		if (node.children.size() > 1) {
			System.out.println("---");
		}
		if (node.end) {
			System.out.println(" Is Current Node a Word: " + node.end);
		}
		for (final Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
			printNode(entry.getKey(), entry.getValue());
		}
	}
}

class Trie {
	private final TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public Trie(TrieNode root) {
		this.root = root;
	}

	public void insert(String word) {
		TrieNode current = root;
		for (final char c : word.toCharArray()) {
			final TrieNode node = current.getChildren().getOrDefault(c, new TrieNode());
			current.getChildren().putIfAbsent(c, node);
			current = node;
		}
		current.setEnd(true);
	}

	public boolean search(String word) {
		TrieNode current = root;
		for (final char c : word.toCharArray()) {
			if (!current.getChildren().containsKey(c)) {
				return false;
			}
			final TrieNode node = current.getChildren().get(c);
			current = node;
		}
		return current.isEnd();
	}

	public boolean startsWith(String prefix) {
		TrieNode current = root;
		for (final char c : prefix.toCharArray()) {
			if (!current.getChildren().containsKey(c)) {
				return false;
			}
			final TrieNode node = current.getChildren().get(c);
			current = node;
		}
		return true;
	}

	private List<String> findAll(String prefix, TrieNode node) {
		final List<String> result = new ArrayList<>();
		if (node.isEnd()) {
			result.add(prefix);
		}
		for (final Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
			result.addAll(findAll(prefix + entry.getKey(), entry.getValue()));
		}
		return result;
	}

	public List<String> listAllChildren(String word) {
		TrieNode current = root;
		final List<String> result = new ArrayList<>();

		for (final char c : word.toCharArray()) {
			if (!current.getChildren().containsKey(c)) {
				return result;
			}
			final TrieNode node = current.getChildren().get(c);
			current = node;
		}
		return findAll(word, current);
	}

	public void printTrie() {
		root.printNode();
	}
}