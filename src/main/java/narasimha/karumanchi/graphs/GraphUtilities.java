package narasimha.karumanchi.graphs;

import java.util.Arrays;

public class GraphUtilities {
	public static GraphNode createGraph() {
		final GraphNode node1 = new GraphNode(1);
		final GraphNode node2 = new GraphNode(2);
		final GraphNode node3 = new GraphNode(3);
		final GraphNode node4 = new GraphNode(4);
		node1.setNeighbors(Arrays.asList(node2, node4));
		node2.setNeighbors(Arrays.asList(node1, node3));
		node3.setNeighbors(Arrays.asList(node2, node4));
		node4.setNeighbors(Arrays.asList(node1, node3));
		return node1;
	}
}
