package com.interviewready.io.system.design.course.consistency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Eventual Consistency

class ProfileNode {
	private String profilePicture;
	private static final Random random = new Random();

	public void updateProfilePicture(String newPicture) {
		setProfilePicture(newPicture);
		asyncSyncWithReplicas(newPicture);
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getProfilePicture() {
		return profilePicture != null ? profilePicture : "Old Picture";
	}

	private void asyncSyncWithReplicas(String newPicture) {
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			try {
				Thread.sleep(random.nextInt(3000)); // Simulate network delay
				DistributedProfileService.syncProfilePicture(newPicture);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		});
		executor.shutdown();
	}
}

class DistributedProfileService {
	private static final List<ProfileNode> nodes = new ArrayList<>();

	public static void addNode(ProfileNode node) {
		nodes.add(node);
	}

	public static void syncProfilePicture(String newPicture) {
		for (final ProfileNode node : nodes) {
			node.setProfilePicture(newPicture);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final ProfileNode node1 = new ProfileNode();
		final ProfileNode node2 = new ProfileNode();
		addNode(node1);
		addNode(node2);

		node1.updateProfilePicture("new_image.jpg");

		System.out.println("Node 1 sees: " + node1.getProfilePicture());
		System.out.println("Node 2 sees: " + node2.getProfilePicture()); // May
																			// return
																			// old
																			// picture

		Thread.sleep(4000); // Wait for sync to complete
		System.out.println("Node 2 (after sync) sees: " + node2.getProfilePicture());
	}
}
