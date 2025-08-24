package narasimha.karumanchi.arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ArraysMain {
	public static void main(String[] args) {
		final var arraysMain = new ArraysMain();
		// arraysMain.runRemoveAdjacentDuplicatesRecursively();
		// arraysMain.runMaxSumInSlidingWindow();
		// arraysMain.runIntersectionOfPairs();
		// arraysMain.runIntersectionOfPairLists();
		// arraysMain.runMergeKSortedArrays();
		// arraysMain.runSquaresOfSortedArray();
		// arraysMain.runSubArraySum();
		// arraysMain.runCombinationSum();
		arraysMain.runRemoveAdjacentDuplicatesRecursively();
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

	// 7. Subarray sum
	public void runSubArraySum() {
		final int[] arr = { 142, 112, 54, 69, 148, 45, 63, 158, 38, 60, 124, 142, 130, 179, 117, 36, 191, 43, 89, 107,
				41, 143, 65, 49, 47, 6, 91, 130, 171, 151, 7, 102, 194, 149, 30, 24, 85, 155, 157, 41, 167, 177, 132,
				109, 145, 40, 27, 124, 138, 139, 119, 83, 130, 142, 34, 116, 40, 59, 105, 131, 178, 107, 74, 187, 22,
				146, 125, 73, 71, 30, 178, 174, 98, 113 };
		final List<Integer> result = subArraySum(arr, 665);
		System.out.println(result);
	}

	// 8. k closest points to origin
	public void runKClosestPointsToOrigin() {
		// TODO:
	}

	// 9. combination sum
	public void runCombinationSum() {
		final int[] arr = { 2, 3, 6, 7 };
		final int k = 7;
		final List<List<Integer>> result = combinationSum(arr, k);
		for (final List<Integer> row : result) {
			System.out.println(row);
		}
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

	// 7. Subarray Sum
	public List<Integer> subArraySum(int[] arr, int k) {
		final var n = arr.length;
		var windowStart = 0;
		var sum = 0;
		final List<Integer> result = new ArrayList<>();
		if (n == 0 || (k == 0 && arr[0] != 0)) {
			result.add(-1);
			return result;
		}
		for (var windowEnd = 0; windowEnd <= n; windowEnd++) {
			System.out.println("windowStart: " + windowStart + " windowEnd: " + windowEnd + " sum: " + sum);
			while (sum > k && windowStart < n) {
				sum -= arr[windowStart];
				windowStart++;
			}
			if (sum == k) {
				result.add(windowStart + 1);
				result.add(windowEnd);
				return result;
			}
			if (windowEnd < n) {
				sum += arr[windowEnd];
			}
		}
		if (result.isEmpty()) {
			result.add(-1);
		}
		return result;
	}

	// 8. Count of Smaller Numbers After Self
	// https://leetcode.com/problems/count-of-smaller-numbers-after-self/
	/*
	 * Given an integer array nums, return an integer array counts where
	 * counts[i] is the number of smaller elements to the right of nums[i].
	 */
	// Time - O(nlogn), Space - O(n)
	public List<Integer> countSmaller(int[] arr) {
		final int n = arr.length;
		final ArrayValueWithIndex[] nums = new ArrayValueWithIndex[n];
		for (int i = 0; i < n; i++) {
			nums[i] = new ArrayValueWithIndex(arr[i], i);
		}

		final List<Integer> resultList = new ArrayList<>();
		final int[] result = new int[n];
		countSmaller(nums, 0, n - 1, result);
		for (final int entry : result) {
			resultList.add(entry);
		}
		return resultList;
	}

	private void countSmaller(ArrayValueWithIndex[] nums, int start, int end, int[] result) {
		if (start >= end) {
			return;
		}
		final int mid = start + (end - start) / 2;
		countSmaller(nums, start, mid, result);
		countSmaller(nums, mid + 1, end, result);
		final List<ArrayValueWithIndex> merged = new LinkedList<>();
		merge(nums, start, mid, end, result, merged);
	}

	private void merge(ArrayValueWithIndex[] nums, int start, int mid, int end, int[] result,
			List<ArrayValueWithIndex> merged) {
		if (start >= end) {
			return;
		}
		int numberOfRightSmallerThanLeft = 0;
		int i = start;
		int j = mid + 1;

		while (i <= mid && j <= end) {
			if (nums[i].getValue() > nums[j].getValue()) {
				numberOfRightSmallerThanLeft++;
				merged.add(nums[j++]);
			} else {
				result[nums[i].getIndex()] += numberOfRightSmallerThanLeft;
				merged.add(nums[i++]);
			}
		}
		while (i <= mid) {
			result[nums[i].getIndex()] += numberOfRightSmallerThanLeft;
			merged.add(nums[i++]);
		}
		while (j <= end) {
			merged.add(nums[j++]);
		}

		int k = start;
		for (final ArrayValueWithIndex entry : merged) {
			nums[k++] = entry;
		}
	}

	// 9. K closest points to origin
	public int[][] kClosestPointsToOrigin(int[][] points, int k) {
		// return kClosestPointsToOriginFullSort(points, k);
		// return kClosestPointsToOriginMaxHeap(points, k);
		return kClosestPointsToOriginRankBased(points, k);
	}

	private int[][] kClosestPointsToOriginFullSort(int[][] points, int k) {
		Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				final int distance1 = o1[0] * o1[0] + o1[1] * o1[1];
				final int distance2 = o2[0] * o2[0] + o2[1] * o2[1];
				return Integer.compare(distance1, distance2);
			}
		});
		return Arrays.copyOfRange(points, 0, k);
	}

	private int[][] kClosestPointsToOriginMaxHeap(int[][] points, int k) {
		final PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				final int distance1 = o1[0] * o1[0] + o1[1] * o1[1];
				final int distance2 = o2[0] * o2[0] + o2[1] * o2[1];
				return Integer.compare(distance2, distance1);
			}
		});
		for (final int[] point : points) {
			maxHeap.add(point);
			if (maxHeap.size() > k) {
				maxHeap.poll();
			}
		}
		final int[][] result = new int[k][2];
		while (k > 0) {
			result[k - 1] = maxHeap.poll();
			k--;
		}
		return result;
	}

	private int[][] kClosestPointsToOriginRankBased(int[][] points, int k) {
		final int n = points.length;
		int lo = 0;
		int hi = n - 1;
		while (lo <= hi) {
			final int partitionIndex = partition(points, lo, hi);
			if (partitionIndex == k) {
				break;
			} else if (partitionIndex < k) {
				lo = partitionIndex + 1;
			} else {
				hi = partitionIndex - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, k);
	}

	private int partition(int[][] points, int lo, int hi) {
		final int pivotIndex = lo;
		final int[] pivot = points[lo];
		int i = lo;
		int j = hi;
		while (i < j) {
			while (i <= hi && compareBasedOnDistanceFromOrigin(points[i], pivot) <= 0) {
				i++;
			}
			while (j >= lo && compareBasedOnDistanceFromOrigin(points[j], pivot) > 0) {
				j--;
			}
			if (i < j) {
				swap(points, i, j);
			}
		}
		swap(points, j, pivotIndex);
		return j;
	}

	private int compareBasedOnDistanceFromOrigin(int[] arr1, int[] arr2) {
		final int x1 = arr1[0];
		final int y1 = arr1[1];
		final int x2 = arr2[0];
		final int y2 = arr2[1];
		return (x1 * x1 + y1 * y1 - x2 * x2 - y2 * y2);
	}

	private void swap(int[][] mat, int x, int y) {
		final int[] temp = mat[x];
		mat[x] = mat[y];
		mat[y] = temp;
	}

	// 10. Combination sum
	/*-
	 * Given an array of distinct integers candidates and a target integer
	 * target, return a list of all unique combinations of candidates where the
	 * chosen numbers sum to target. You may return the combinations in any
	 * order. The same number may be chosen from candidates an unlimited number
	 * of times. Two combinations are unique if the frequency of at least one of
	 * the chosen numbers is different.
	 * 
	 * Input: candidates = [2,3,6,7], target = 7, Output: [[2,2,3],[7]] 
	 * Input: candidates = [2,3,5], target = 8, Output: [[2,2,2,2],[2,3,3],[3,5]]
	 */
	public List<List<Integer>> combinationSum(int[] arr, int k) {
		final List<List<Integer>> result = new ArrayList<>();
		final List<Integer> subList = new ArrayList<>();
		final int[] sumArr = new int[1];
		combinationSum(arr, k, 0, sumArr, subList, result);
		return result;
	}

	private void combinationSum(int[] arr, int k, int index, int[] sumArr, List<Integer> subList,
			List<List<Integer>> result) {
		if (index >= arr.length) {
			return;
		}
		for (int i = index; i < arr.length; i++) {
			sumArr[0] += arr[i];
			subList.add(arr[i]);
			if (sumArr[0] == k) {
				result.add(subList);
				subList.clear();
				sumArr[0] = 0;
				return;
			}
			if (sumArr[0] > k) {
				sumArr[0] -= arr[i];
				subList.remove(Integer.valueOf(arr[i]));
				combinationSum(arr, k, index + 1, sumArr, subList, result);
			}
			combinationSum(arr, k, index, sumArr, subList, result);
		}
	}
}

class ArrayValueWithIndex {
	int value;
	int index;

	public ArrayValueWithIndex(int value, int index) {
		this.value = value;
		this.index = index;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
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
