package com.interviewready.io.system.design.course.consistency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Message {
	int id;
	String user;
	String text;
	Integer dependsOn; // Message dependency (null if independent)

	public Message(int id, String user, String text, Integer dependsOn) {
		this.id = id;
		this.user = user;
		this.text = text;
		this.dependsOn = dependsOn;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + user + ": " + text + (dependsOn != null ? " (Depends on: " + dependsOn + ")" : "");
	}
}

class ChatNode {
	private final String nodeName;
	private final Map<Integer, Message> messages = new HashMap<>();
	private final Queue<Message> pendingMessages = new LinkedList<>();
	private static int messageCounter = 0;
	private final ExecutorService executor = Executors.newCachedThreadPool();
	private static final Random random = new Random();

	public ChatNode(String nodeName) {
		this.nodeName = nodeName;
	}

	public synchronized int sendMessage(String user, String text, Integer dependsOn) {
		final int messageId = ++messageCounter;
		final Message message = new Message(messageId, user, text, dependsOn);
		processMessage(message);
		asyncSyncWithReplicas(message);
		return messageId;
	}

	private synchronized void processMessage(Message message) {
		if (message.dependsOn == null || messages.containsKey(message.dependsOn)) {
			messages.put(message.id, message);
			System.out.println(nodeName + " received: " + message);
			checkPendingMessages();
		} else {
			pendingMessages.add(message);
		}
	}

	private synchronized void checkPendingMessages() {
		final Iterator<Message> iterator = pendingMessages.iterator();
		while (iterator.hasNext()) {
			final Message pending = iterator.next();
			if (messages.containsKey(pending.dependsOn)) {
				messages.put(pending.id, pending);
				System.out.println(nodeName + " processed pending: " + pending);
				iterator.remove();
			}
		}
	}

	private void asyncSyncWithReplicas(Message message) {
		executor.submit(() -> {
			try {
				Thread.sleep(random.nextInt(2000)); // Simulate random network
													// delay
				DistributedChatSystem.syncMessageToOtherNodes(message);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	public synchronized void receiveMessage(Message message) {
		processMessage(message);
	}

	public synchronized List<Message> getMessages() {
		return new ArrayList<>(messages.values());
	}

	public void shutdown() {
		executor.shutdown();
	}
}

class DistributedChatSystem {
	private static final List<ChatNode> nodes = new ArrayList<>();

	public static void addNode(ChatNode node) {
		nodes.add(node);
	}

	public static void syncMessageToOtherNodes(Message message) {
		for (final ChatNode node : nodes) {
			node.receiveMessage(message);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final ChatNode node1 = new ChatNode("Node 1");
		final ChatNode node2 = new ChatNode("Node 2");
		final ChatNode node3 = new ChatNode("Node 3");

		addNode(node1);
		addNode(node2);
		addNode(node3);

		// Simulate message sending
		final int msg1 = node1.sendMessage("Alice", "Hello, everyone!", null);
		Thread.sleep(500); // Simulate a short delay

		final int msg2 = node2.sendMessage("Bob", "Hey Alice!", msg1);
		final int msg3 = node3.sendMessage("Charlie", "What's up?", msg1);

		Thread.sleep(3000); // Allow time for async processing

		System.out.println("\nFinal messages in Node 1: " + node1.getMessages());
		System.out.println("Final messages in Node 2: " + node2.getMessages());
		System.out.println("Final messages in Node 3: " + node3.getMessages());

		// Shutdown executor services
		node1.shutdown();
		node2.shutdown();
		node3.shutdown();
	}
}
