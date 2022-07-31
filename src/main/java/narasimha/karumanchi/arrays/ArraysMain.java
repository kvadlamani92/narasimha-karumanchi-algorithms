package narasimha.karumanchi.arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ArraysMain {
	public static void main(String[] args) {
		final var arraysMain = new ArraysMain();
		arraysMain.runRemoveAdjacentDuplicatesRecursively();
		// arraysMain.runMaxSumInSlidingWindow();
		// arraysMain.runIntersectionOfPairs();
		// arraysMain.runIntersectionOfPairLists();
		// arraysMain.runMergeKSortedArrays();
		arraysMain.runSquaresOfSortedArray();
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

	// 5. Run merge k sorted arrays
	public void runMergeKSortedArrays() {
		final int[][] arrayOfArrays = { { 2, 4, 6, 8, 10 }, { 1, 3, 5, 7, 9 }, { 5, 5, 5 } };
		final int[] result = mergeKSortedArrays(arrayOfArrays);
		System.out.println(Arrays.toString(result));
	}

	// 6. Squares of sorted array
	public void runSquaresOfSortedArray() {
		final int[] arr = { -6, -4, 1, 2, 3, 5 };
		System.out.println(Arrays.toString(squaresOfSortedArray(arr)));
	}

	// ....Algorithms....
	// 1. Remove adjacent duplicates recursively
	public int[] removeAdjacentDuplicatesRecursively(int[] arr) {
		final int n = arr.length;
		var i = 0;
		var pointer = -1;
		while (i < n) {
			if (pointer == -1 || arr[pointer] != arr[i]) {
				arr[++pointer] = arr[i];
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

	// 5. Merge k sorted arrays
	public int[] mergeKSortedArrays(int[][] arrayOfArrays) {
		final int n = arrayOfArrays.length;
		final int m = arrayOfArrays[0].length;
		final var result = new int[n * m];
		final Queue<ArrayIndexWithValueIndex> minHeap = new PriorityQueue<>();
		for (var i = 0; i < n; i++) {
			minHeap.add(new ArrayIndexWithValueIndex(i, 0, arrayOfArrays[i][0]));
		}
		var j = 0;
		while (!minHeap.isEmpty()) {
			final ArrayIndexWithValueIndex indexWithValue = minHeap.poll();
			result[j++] = indexWithValue.getValue();
			final var arrayIndex = indexWithValue.getArrayIndex();
			final var nextValueIndex = indexWithValue.getValueIndex() + 1;
			final var arrayLength = arrayOfArrays[arrayIndex].length;
			if (nextValueIndex < arrayLength) {
				final var nextValue = arrayOfArrays[arrayIndex][nextValueIndex];
				minHeap.add(new ArrayIndexWithValueIndex(arrayIndex, nextValueIndex, nextValue));
			}
		}
		return result;
	}

	// 6. Squares of sorted array
	/*
	 * Given an array of numbers A sorted in ascending order, return an array of
	 * the squares of each number, also in sorted ascending order. For array =
	 * [-6, -4, 1, 2, 3, 5], the output should be [1, 4, 9, 16, 25, 36].
	 */
	public int[] squaresOfSortedArray(int[] arr) {
		final var n = arr.length;
		final var result = new int[n];
		var i = 0;
		var j = n - 1;
		var k = n - 1;
		while (i <= j) {
			final int iSquare = arr[i] * arr[i];
			final int jSquare = arr[j] * arr[j];
			if (iSquare < jSquare) {
				result[k--] = jSquare;
				j--;
			} else {
				result[k--] = iSquare;
				i++;
			}
		}
		return result;
	}
}

class ArrayIndexWithValueIndex implements Comparable {
	int arrayIndex;
	int valueIndex;
	int value;

	public ArrayIndexWithValueIndex(int arrayIndex, int valueIndex, int value) {
		super();
		this.arrayIndex = arrayIndex;
		this.valueIndex = valueIndex;
		this.value = value;
	}

	public int getArrayIndex() {
		return arrayIndex;
	}

	public void setArrayIndex(int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	public int getValueIndex() {
		return valueIndex;
	}

	public void setValueIndex(int valueIndex) {
		this.valueIndex = valueIndex;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Object o) {
		final ArrayIndexWithValueIndex other = (ArrayIndexWithValueIndex) o;
		return this.value > other.value ? 1 : (this.value < other.value ? -1 : 0);
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