package com.interviewready.io.system.design.course.consistency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Linearizable consistency
 * */
class BankNode {
	private int balance = 0;
	private final Lock lock = new ReentrantLock();

	public void deposit(int amount) {
		lock.lock();
		try {
			balance += amount;
			syncWithReplicas();
		} finally {
			lock.unlock();
		}
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance; // Always returns latest balance
	}

	private void syncWithReplicas() {
		DistributedBankSystem.updateAllNodes(balance);
	}
}

public class DistributedBankSystem {
	private static final List<BankNode> nodes = new ArrayList<>();

	public static void addNode(BankNode node) {
		nodes.add(node);
	}

	public static void updateAllNodes(int newBalance) {
		for (final BankNode node : nodes) {
			node.setBalance(newBalance);
		}
	}

	public static void main(String[] args) {
		final var node1 = new BankNode();
		final var node2 = new BankNode();
		addNode(node1);
		addNode(node2);

		node1.deposit(100);
		System.out.println("Node 1 Balance: " + node1.getBalance());
		System.out.println("Node 2 Balance: " + node2.getBalance()); // Always
																		// 100
	}
}
