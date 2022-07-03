package narasimha.karumanchi.unionFind;

import java.util.Arrays;

public class UnionFindMain {
	public static void main(String[] args) {
		final var unionFindMain = new UnionFindMain();
		unionFindMain.runUnionFind();
	}

	// Runners
	public void runUnionFind() {
		final var unionFindADT = new UnionFindADT(10);
		System.out.println("parent:" + Arrays.toString(unionFindADT.getParentArray())); // NOSONAR
		System.out.println("rank:" + Arrays.toString(unionFindADT.getRankArray())); // NOSONAR
		unionFindADT.union(1, 2);
		System.out.println("parent:" + Arrays.toString(unionFindADT.getParentArray()));
		System.out.println("rank:" + Arrays.toString(unionFindADT.getRankArray()));
		unionFindADT.union(2, 3);
		System.out.println("parent:" + Arrays.toString(unionFindADT.getParentArray()));
		System.out.println("rank:" + Arrays.toString(unionFindADT.getRankArray()));
		unionFindADT.union(4, 5);
		System.out.println("parent:" + Arrays.toString(unionFindADT.getParentArray()));
		System.out.println("rank:" + Arrays.toString(unionFindADT.getRankArray()));
		unionFindADT.union(6, 7);
		System.out.println("parent:" + Arrays.toString(unionFindADT.getParentArray()));
		System.out.println("rank:" + Arrays.toString(unionFindADT.getRankArray()));
		unionFindADT.union(7, 8);
		System.out.println("parent:" + Arrays.toString(unionFindADT.getParentArray()));
		System.out.println("rank:" + Arrays.toString(unionFindADT.getRankArray()));
		unionFindADT.union(1, 7);
		System.out.println("parent:" + Arrays.toString(unionFindADT.getParentArray()));
		System.out.println("rank:" + Arrays.toString(unionFindADT.getRankArray()));
	}
}

class UnionFindADT {
	int[] parent;
	int[] rank;

	public UnionFindADT(int n) {
		parent = new int[n];
		rank = new int[n];
		for (var i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	public int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return (parent[x] = find(parent[x])); // with path compression
	}

	public void union(int x, int y) {
		final var xRoot = find(x);
		final var yRoot = find(y);

		if (xRoot != yRoot) {
			if (rank[x] < rank[y]) {
				// swap x and y to always make sure x is of greater rank
				final var temp = x;
				x = y;
				y = temp;
			}
			parent[y] = x;
			if (rank[x] == rank[y]) {
				rank[x]++;
			}
		}
	}

	public int[] getParentArray() {
		return parent;
	}

	public int[] getRankArray() {
		return rank;
	}
}
