package narasimha.karumanchi.arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class ArraysMain {
	public static void main(String[] args) {
		final var arraysMain = new ArraysMain();
		// arraysMain.runRemoveAdjacentDuplicatesRecursively();
		// arraysMain.runMaxSumInSlidingWindow();
		// arraysMain.runIntersectionOfPairs();
		arraysMain.runIntersectionOfPairLists();
	}

	// ....Arrays Runners....
	// 1. Run remove adjacent duplicates recursively
	public void runRemoveAdjacentDuplicatesRecursively() {
		final int[] arr = { 1, 5, 6, 8, 8, 0, 1, 1, 0, 6, 5 };
		System.out.println(Arrays.toString(removeAdjacentDuplicatesRecursively(arr)));
		final int[] arr1 = { 1, 9, 6, 8, 8, 0, 1, 1, 0, 6, 5 };
		System.out.println(Arrays.toString(removeAdjacentDuplicatesRecursively(arr1)));
	}

	// 2. MaxSum in sliding window
	public void runMaxSumInSlidingWindow() {
		// final int[] arr = { 1, 3, -1, -3, 5, 3, 6, 7 };
		// System.out.println(Arrays.toString(getMaxSumInSlidingWindow(arr,
		// 3)));
		final int[] arr = { 7, 1, 3, 4, 6, 3, 8 };
		System.out.println(Arrays.toString(getMaxSumInSlidingWindow(arr, 3)));
	}

	// 3. Intersection of pairs
	public void runIntersectionOfPairs() {
		final List<Pair<Integer, Integer>> pairs = ArrayUtils.getListOfPairs();
		final List<Pair<Integer, Integer>> result = findIntersectionOfPoints(pairs);
		System.out.println(result);
	}

	// 4. Intersection of pair lists
	public void runIntersectionOfPairLists() {
		final List<Pair<Integer, Integer>> pairs1 = ArrayUtils.getListOfPairs4();
		final List<Pair<Integer, Integer>> pairs2 = ArrayUtils.getListOfPairs5();
		final List<Pair<Integer, Integer>> result = getIntersectionOfPairLists(pairs1, pairs2);
		System.out.println(result);
	}

	// ....Algorithms....
	// 1. Remove adjacent duplicates recursively
	public int[] removeAdjacentDuplicatesRecursively(int[] arr) {
		final int n = arr.length;
		var i = 0;
		var pointer = -1;
		while (i < n) {
			if (pointer == -1 || arr[pointer] != arr[i]) {
				pointer++;
				arr[pointer] = arr[i];
				i++;
			} else {
				while (i < n && arr[pointer] == arr[i]) {
					i++;
				}
				pointer--;
			}
		}
		return Arrays.copyOfRange(arr, 0, pointer + 1);
	}

	// 2. Max sum in a sliding window
	// https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
	public int[] getMaxSumInSlidingWindow(int[] arr, int k) {
		final var n = arr.length;
		final int[] res = new int[n - k + 1];
		final Deque<Integer> q = new ArrayDeque<>();
		var index = 0;
		for (var i = 0; i < n; i++) {
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			while (!q.isEmpty() && arr[q.peekLast()] < arr[i]) {
				q.pollLast();
			}
			q.offer(i);
			if (i >= k - 1) {
				res[index++] = arr[q.peek()];
			}
		}
		return res;
	}

	// 3. Find intersection of points [2,6], [3,7], [4,5] -> [4,5]
	// .......__. [4,5]
	// ..bx._______.by [3,7]
	// ax._______.ay [2,6]
	// --|-|-|-|-|-|-|-
	// 1 2 3 4 5 6 7
	public List<Pair<Integer, Integer>> findIntersectionOfPoints(List<Pair<Integer, Integer>> points) {
		final var n = points.size();
		Collections.sort(points);
		final List<Pair<Integer, Integer>> result = new ArrayList<>();
		result.add(points.get(0));
		for (var i = 1; i < n; i++) {
			final int size = result.size();
			final Pair<Integer, Integer> a = result.get(size - 1);
			final Pair<Integer, Integer> b = points.get(i);
			if (b.getX() > a.getY()) {
				return Collections.emptyList();
			}
			if (b.getY() <= a.getY()) {
				result.set(size - 1, new Pair(b.getX(), b.getY()));
			} else {
				result.set(size - 1, new Pair(b.getX(), a.getY()));
			}
		}
		return result;
	}

	// 4. Intersection of 2 lists having list of pairs
	public List<Pair<Integer, Integer>> getIntersectionOfPairLists(List<Pair<Integer, Integer>> pairs1,
			List<Pair<Integer, Integer>> pairs2) {
		final int n = pairs1.size();
		final int m = pairs2.size();
		var i = 0;
		var j = 0;
		final List<Pair<Integer, Integer>> result = new ArrayList<>();
		while (i < n && j < m) {
			final Pair<Integer, Integer> a = pairs1.get(i);
			final Pair<Integer, Integer> b = pairs2.get(j);
			if (b.getX() > a.getY()) {
				i++;
				continue;
			}
			var x = 0;
			var y = 0;
			if (b.getY() <= a.getY()) {
				if (b.getX() < a.getX()) {
					x = a.getX();
				} else {
					x = b.getX();
				}
				y = b.getY();
				if (b.getY() == a.getY()) {
					i++;
				}
				j++;
			} else {
				y = a.getY();
				if (b.getX() < a.getY()) {
					x = b.getX();
				} else {
					x = a.getY();
				}
				i++;
			}
			result.add(new Pair(x, y));
		}
		return result;
	}
}

class ArrayUtils {
	public static List<Pair<Integer, Integer>> getListOfPairs() {
		final Pair<Integer, Integer> a = new Pair(2, 6);
		final Pair<Integer, Integer> b = new Pair(3, 7);
		final Pair<Integer, Integer> c = new Pair(4, 5);
		final List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		pairs.add(a);
		pairs.add(b);
		pairs.add(c);
		return pairs;
	}

	public static List<Pair<Integer, Integer>> getListOfPairs2() {
		final Pair<Integer, Integer> a = new Pair(1, 3);
		final Pair<Integer, Integer> b = new Pair(5, 7);
		final Pair<Integer, Integer> c = new Pair(9, 12);
		final List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		pairs.add(a);
		pairs.add(b);
		pairs.add(c);
		return pairs;
	}

	public static List<Pair<Integer, Integer>> getListOfPairs3() {
		final Pair<Integer, Integer> a = new Pair(5, 10);
		final List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		pairs.add(a);
		return pairs;
	}

	public static List<Pair<Integer, Integer>> getListOfPairs4() {
		final Pair<Integer, Integer> a = new Pair(1, 3);
		final Pair<Integer, Integer> b = new Pair(5, 6);
		final Pair<Integer, Integer> c = new Pair(7, 9);
		final List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		pairs.add(a);
		pairs.add(b);
		pairs.add(c);
		return pairs;
	}

	public static List<Pair<Integer, Integer>> getListOfPairs5() {
		final Pair<Integer, Integer> a = new Pair(2, 3);
		final Pair<Integer, Integer> b = new Pair(5, 7);
		final List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		pairs.add(a);
		pairs.add(b);
		return pairs;
	}
}

class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>> {
	K x;
	V y;

	public Pair(K x, V y) {
		this.x = x;
		this.y = y;
	}

	public K getX() {
		return x;
	}

	public void setX(K x) {
		this.x = x;
	}

	public V getY() {
		return y;
	}

	public void setY(V y) {
		this.y = y;
	}

	@Override
	public int compareTo(Pair<K, V> o) {
		return this.getX().compareTo(o.getX());
	}

	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}
}