package com.interviewready.io.system.design.course;

/*
Here’s a Java implementation of Consistent Hashing,which uses SHA-256 hashing and supports virtual nodes for better load distribution.
This implementation is useful for distributed caching,load balancing,and distributed databases.

Explanation
Hash Function:

Uses SHA-256 to generate a unique hash for nodes and keys.
Converts the first 8 bytes into a long value for the hash ring.
Ring-Based Mapping:

A TreeMap<Long, String> stores node hashes in sorted order.
A key is assigned to the nearest (clockwise) node.
Adding & Removing Nodes:

Adding a Node: Inserts virtual nodes (replicas) to the ring.
Removing a Node: Deletes all virtual nodes.
Key Lookups:

Uses TreeMap’s tailMap() to find the nearest node.
If no node is found, it wraps around to the first node.

Key Features
✔ Minimizes Data Movement: When a node is added/removed, only some keys move.
✔ Supports Virtual Nodes: Improves load balancing.
✔ Efficient Lookups: Uses TreeMap, allowing O(log N) lookups.
✔ Flexible & Scalable: Easily extendable to new nodes.

Use Cases
✅ Distributed Caching (e.g., Memcached, Redis clusters)
✅ Load Balancers (e.g., Consistent request routing)
✅ Sharded Databases (e.g., Amazon Dynamo, Apache Cassandra)
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
	private final int replicas; // Number of virtual nodes per physical node
	private final SortedMap<Long, String> ring = new TreeMap<>(); // Hash ring
	private final Set<String> nodes = new HashSet<>(); // Set of real nodes

	public ConsistentHashing(List<String> nodes, int replicas) {
		this.replicas = replicas;
		for (final String node : nodes) {
			addNode(node);
		}
	}

	private long hash(String key) {
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-256");
			final byte[] hash = md.digest(key.getBytes(StandardCharsets.UTF_8));
			return ((long) (hash[0] & 0xFF) << 56) | ((long) (hash[1] & 0xFF) << 48) | ((long) (hash[2] & 0xFF) << 40)
					| ((long) (hash[3] & 0xFF) << 32) | ((long) (hash[4] & 0xFF) << 24)
					| ((long) (hash[5] & 0xFF) << 16) | ((long) (hash[6] & 0xFF) << 8) | (hash[7] & 0xFF);
		} catch (final NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not found", e);
		}
	}

	public void addNode(String node) {
		nodes.add(node);
		for (int i = 0; i < replicas; i++) {
			final long hash = hash(node + "#" + i);
			System.out.println("Hash of node:" + i + " = " + hash);
			ring.put(hash, node);
		}
	}

	public void removeNode(String node) {
		nodes.remove(node);
		for (int i = 0; i < replicas; i++) {
			final long hash = hash(node + "#" + i);
			ring.remove(hash);
		}
	}

	public String getNode(String key) {
		if (ring.isEmpty()) {
			return null;
		}

		final long keyHash = hash(key);
		final SortedMap<Long, String> tailMap = ring.tailMap(keyHash);
		final long nodeHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
		return ring.get(nodeHash);
	}

	public static void main(String[] args) {
		final List<String> nodes = Arrays.asList("NodeA", "NodeB", "NodeC");
		final ConsistentHashing ch = new ConsistentHashing(nodes, 3);

		System.out.println("Key1 is assigned to: " + ch.getNode("Key1"));
		System.out.println("Key2 is assigned to: " + ch.getNode("Key2"));

		System.out.println("Adding NodeD...");
		ch.addNode("NodeD");

		System.out.println("Key1 is now assigned to: " + ch.getNode("Key1"));
		System.out.println("Key2 is now assigned to: " + ch.getNode("Key2"));
	}
}

/*
 * Load Distribution with Traditional Hashing vs. Consistent Hashing 1.
 * Traditional Hashing (Modulo Hashing) Traditional hashing distributes keys
 * among nodes using a modulo-based function:
 * 
 * Node=hash(key)modN where N is the number of available servers or nodes.
 * 
 * Example Assume we have 3 servers: Node A, Node B, Node C We use hash(key) % 3
 * to distribute keys. Key Hash Value Node (hash % 3) "User1" 1001 1001 % 3 = 1
 * → Node B "SessionX" 2032 2032 % 3 = 2 → Node C "Item234" 4577 4577 % 3 = 0 →
 * Node A This method distributes load evenly as long as the number of nodes
 * remains constant.
 * 
 * Problem with Traditional Hashing Node Addition/Removal Issues
 * 
 * If a new node is added (N → N+1), the modulo operation changes, and almost
 * all keys get reassigned. Example: Adding a 4th node means hash % 4, causing
 * keys to remap unpredictably. This leads to high cache misses and inefficiency
 * in load balancing. Example of Key Redistribution on Adding Node D
 * 
 * Before: Node = hash(key) % 3 After: Node = hash(key) % 4 Keys originally
 * mapped to Node C may now go to Node D, shifting 75% of the data.
 * 
 * 2. Why Consistent Hashing is Better Instead of using modulo-based mapping,
 * consistent hashing uses a circular hash space.
 * 
 * Only a small portion of keys move when a new node is added. The impact is
 * limited to neighboring nodes, improving stability and efficiency. Example of
 * Key Redistribution in Consistent Hashing Before: Keys → Nodes {A, B, C}
 * After: Add Node D Only some keys from Node C → Node D, while others remain
 * unchanged. Traditional Hashing (Modulo % N) Consistent Hashing Massive key
 * remapping when a node is added/removed Minimal key movement (only adjacent
 * nodes affected) High cache misses due to frequent rehashing Low cache misses
 * and better performance Not scalable for dynamic environments Highly scalable
 * for growing distributed systems Real-World Impact Traditional Hashing works
 * well for static environments (fixed servers). Consistent Hashing is preferred
 * for distributed caches (Memcached, Redis), databases (Cassandra, DynamoDB),
 * and load balancing (Nginx, Akamai). Would you like a code comparison between
 * traditional and consistent hashing to see the difference in action? 🚀
 * 
 * You said: yes ChatGPT said: ChatGPT Code Comparison: Traditional Hashing vs.
 * Consistent Hashing This example demonstrates how traditional hashing
 * (modulo-based hashing) and consistent hashing distribute load across servers.
 * 
 * 1. Traditional Hashing (Modulo Hashing)
 * 
 * import java.util.*;
 * 
 * public class TraditionalHashing { private final List<String> nodes;
 * 
 * public TraditionalHashing(List<String> nodes) { this.nodes = new
 * ArrayList<>(nodes); }
 * 
 * private int hash(String key) { return key.hashCode(); }
 * 
 * public String getNode(String key) { if (nodes.isEmpty()) return null; int
 * hash = Math.abs(hash(key)); int index = hash % nodes.size(); return
 * nodes.get(index); }
 * 
 * public static void main(String[] args) { List<String> nodes =
 * Arrays.asList("NodeA", "NodeB", "NodeC"); TraditionalHashing hashing = new
 * TraditionalHashing(nodes);
 * 
 * System.out.println("Key1 -> " + hashing.getNode("Key1"));
 * System.out.println("Key2 -> " + hashing.getNode("Key2"));
 * System.out.println("Key3 -> " + hashing.getNode("Key3"));
 * 
 * // Adding a new node (Rehashing required)
 * System.out.println("\nAfter adding NodeD:"); nodes = Arrays.asList("NodeA",
 * "NodeB", "NodeC", "NodeD"); hashing = new TraditionalHashing(nodes);
 * System.out.println("Key1 -> " + hashing.getNode("Key1"));
 * System.out.println("Key2 -> " + hashing.getNode("Key2"));
 * System.out.println("Key3 -> " + hashing.getNode("Key3")); } } 2. Consistent
 * Hashing
 * 
 * import java.nio.charset.StandardCharsets; import java.security.MessageDigest;
 * import java.security.NoSuchAlgorithmException; import java.util.*;
 * 
 * public class ConsistentHashing { private final SortedMap<Long, String> ring =
 * new TreeMap<>(); private final int replicas;
 * 
 * public ConsistentHashing(List<String> nodes, int replicas) { this.replicas =
 * replicas; for (String node : nodes) { addNode(node); } }
 * 
 * private long hash(String key) { try { MessageDigest md =
 * MessageDigest.getInstance("SHA-256"); byte[] hash =
 * md.digest(key.getBytes(StandardCharsets.UTF_8)); return ((long) (hash[0] &
 * 0xFF) << 56) | ((long) (hash[1] & 0xFF) << 48) | ((long) (hash[2] & 0xFF) <<
 * 40) | ((long) (hash[3] & 0xFF) << 32) | ((long) (hash[4] & 0xFF) << 24) |
 * ((long) (hash[5] & 0xFF) << 16) | ((long) (hash[6] & 0xFF) << 8) | ((long)
 * (hash[7] & 0xFF)); } catch (NoSuchAlgorithmException e) { throw new
 * RuntimeException("SHA-256 algorithm not found", e); } }
 * 
 * public void addNode(String node) { for (int i = 0; i < replicas; i++) { long
 * hash = hash(node + "#" + i); ring.put(hash, node); } }
 * 
 * public void removeNode(String node) { for (int i = 0; i < replicas; i++) {
 * long hash = hash(node + "#" + i); ring.remove(hash); } }
 * 
 * public String getNode(String key) { if (ring.isEmpty()) return null;
 * 
 * long keyHash = hash(key); SortedMap<Long, String> tailMap =
 * ring.tailMap(keyHash); long nodeHash = tailMap.isEmpty() ? ring.firstKey() :
 * tailMap.firstKey(); return ring.get(nodeHash); }
 * 
 * public static void main(String[] args) { List<String> nodes =
 * Arrays.asList("NodeA", "NodeB", "NodeC"); ConsistentHashing ch = new
 * ConsistentHashing(nodes, 3);
 * 
 * System.out.println("Key1 -> " + ch.getNode("Key1"));
 * System.out.println("Key2 -> " + ch.getNode("Key2"));
 * System.out.println("Key3 -> " + ch.getNode("Key3"));
 * 
 * // Adding a new node (Minimal redistribution)
 * System.out.println("\nAfter adding NodeD:"); ch.addNode("NodeD");
 * System.out.println("Key1 -> " + ch.getNode("Key1"));
 * System.out.println("Key2 -> " + ch.getNode("Key2"));
 * System.out.println("Key3 -> " + ch.getNode("Key3")); } }
 * 
 * Comparison: Load Redistribution 1. Traditional Hashing (Modulo) Before Adding
 * NodeD:
 * 
 * Key1 -> NodeB Key2 -> NodeC Key3 -> NodeA
 * 
 * After Adding NodeD (Rehashing Required)
 * 
 * Key1 -> NodeC Key2 -> NodeD Key3 -> NodeB
 * 
 * 75% of keys moved! (Almost everything gets reassigned) Major cache misses and
 * performance issues.
 * 
 * 2. Consistent Hashing Before Adding NodeD:
 * 
 * Key1 -> NodeB Key2 -> NodeC Key3 -> NodeA After Adding NodeD (Minimal
 * Movement)
 * 
 * Key1 -> NodeB Key2 -> NodeD Key3 -> NodeA Only some keys moved to NodeD.
 * Other keys remain with their previous nodes.
 * 
 * Key Takeaways
 * 
 * Feature Traditional Hashing (Modulo) | Consistent Hashing
 * 
 * Load Distribution Even but rigid | Even and flexible Scalability Poor (all
 * keys move) | Excellent (minimal key movement) Node Addition Requires full
 * rehashing | Only a small portion of keys move Cache Efficiency High cache
 * misses | Low cache misses Best for Static systems (fixed servers) |
 * Distributed systems (dynamic scaling)
 * 
 */
