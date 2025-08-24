package narasimha.karumanchi.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GraphsMain {
	public static void main(String[] args) {
		final GraphsMain graphsMain = new GraphsMain();
		graphsMain.runCloneGraph();
	}

	// Runners
	// 1. clone Graph
	public void runCloneGraph() {
		final GraphNode node = GraphUtilities.createGraph();
		final GraphNode clonedNode = cloneGraph(node);
		printGraph(clonedNode);
	}

	// Algorithms
	// 1. Clone a graph
	public GraphNode cloneGraph(GraphNode node) {
		if (node == null) {
			return null;
		}
		final Map<GraphNode, Boolean> visitedMap = new HashMap<>();
		final Map<GraphNode, GraphNode> nodeMap = new HashMap<>();

		final GraphNode clonedNode = new GraphNode(node.val);
		nodeMap.put(node, clonedNode);

		final Queue<GraphNode> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			final int size = queue.size();
			for (int i = 0; i < size; i++) {
				final GraphNode currentNode = queue.poll();
				final GraphNode clonedCurrentNode = nodeMap.getOrDefault(currentNode, new GraphNode(currentNode.val));

				if (!visitedMap.containsKey(currentNode)) {
					for (final GraphNode neighbour : currentNode.neighbors) {
						queue.offer(neighbour);
						final GraphNode clonedNeighbour = nodeMap.getOrDefault(neighbour, new GraphNode(neighbour.val));
						clonedCurrentNode.neighbors.add(clonedNeighbour);
						nodeMap.put(neighbour, clonedNeighbour);
						// visitedMap.put(neighbour, true);
					}
					visitedMap.put(currentNode, true);
				}
			}
		}

		printGraph(node);
		System.out.println();
		printGraph(clonedNode);

		return clonedNode;
	}

	private void printGraph(GraphNode node) {
		if (node == null) {
			return;
		}
		final Map<GraphNode, Boolean> visitedMap = new HashMap<>();
		final Queue<GraphNode> queue = new LinkedList<>();
		queue.add(node);

		while (!queue.isEmpty()) {
			final int size = queue.size();
			for (int i = 0; i < size; i++) {
				final GraphNode currentNode = queue.poll();
				System.out.println("currentNode: " + currentNode.val + "-" + currentNode);
				if (!visitedMap.containsKey(currentNode)) {
					System.out.print("Neighbours: ");
					for (final GraphNode neighbour : currentNode.neighbors) {
						System.out.print(neighbour.val + "-" + neighbour + " ");
						queue.offer(neighbour);
					}
					System.out.println();
					visitedMap.put(currentNode, true);
				}
			}
		}
	}

}

class GraphNode {
	int val;
	List<GraphNode> neighbors;

	public GraphNode(int val) {
		super();
		this.val = val;
		this.neighbors = new ArrayList<>();
	}

	public GraphNode(int val, List<GraphNode> neighbors) {
		super();
		this.val = val;
		this.neighbors = neighbors;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public List<GraphNode> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<GraphNode> neighbors) {
		this.neighbors = neighbors;
	}
}
