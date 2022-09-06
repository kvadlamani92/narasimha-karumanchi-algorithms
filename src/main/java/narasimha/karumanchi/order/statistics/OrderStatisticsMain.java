package narasimha.karumanchi.order.statistics;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.TreeMap;

public class OrderStatisticsMain {

	public static void main(String[] args) {
		final var orderStatisticsMain = new OrderStatisticsMain();
		// orderStatisticsMain.runMinMax();
		// orderStatisticsMain.runMinMaxLessComparisions();
		// orderStatisticsMain.runSecondMax();
		// orderStatisticsMain.runSecondMin();
		// orderStatisticsMain.runKthSmallest();
		// orderStatisticsMain.runKthLargest();
		// orderStatisticsMain.runKthSmallestTreeMap();
		// orderStatisticsMain.runKthSmallestMedianOfMedians();
		// orderStatisticsMain.runKthLargestMedianOfMedians();
		// orderStatisticsMain.runMedianOfSortedArrays();
		orderStatisticsMain.test();
	}

	public void test() {
		final int[] arr = { 1, 2, 3, 4, 5 };
		final Queue<Integer> minHeap = new PriorityQueue<>();
		for (final int element : arr) {
			minHeap.add(element);
		}
		System.out.println(minHeap.peek());
	}

	// Runners
	// 1. Find minMax
	public void runMinMax() {
		final int[] arr = { 1, 2, -4, 5, -56 };
		System.out.println(Arrays.toString(minMax(arr)));
	}

	// 2. Find min max less comparisions
	public void runMinMaxLessComparisions() {
		final int[] arr = { 1, 2, -4, 5, -56 };
		System.out.println(Arrays.toString(minMaxLessComparisions(arr)));
		final int[] arr2 = { 1, 2, -4, 5, -56, 7 };
		System.out.println(Arrays.toString(minMaxLessComparisions(arr2)));
	}

	// 3. Find second max
	public void runSecondMax() {
		final int[] arr = { 1, 2, -4, 5, -56 };
		System.out.println(secondMax(arr));
		final int[] arr2 = { 1, 2, -4, 5, -56, 7 };
		System.out.println(secondMax(arr2));
	}

	// 4. Find second min
	public void runSecondMin() {
		final int[] arr = { 1, 2, -4, 5, -56 };
		System.out.println(secondMin(arr));
		final int[] arr2 = { 1, 2, -41, 5, -56, 7 };
		System.out.println(secondMin(arr2));
	}

	// 5. Find kth smallest treeMap
	public void runKthSmallestTreeMap() {
		final int[] arr = { 1, 2, -41, 5, -56, 7 };
		System.out.println(kthSmallestTreeMap(arr, 6));
	}

	// 6. Find kth smallest
	public void runKthSmallest() {
		final int[] arr = { 1, 2, -41, 5, -56, 7 };
		for (var i = 1; i <= arr.length; i++) {
			System.out.println(kthSmallest(arr, i));
		}
	}

	// 7. Find kth largest
	public void runKthLargest() {
		final int[] arr = { 1, 2, -41, 5, -56, 7 };
		for (var i = 1; i <= arr.length; i++) {
			System.out.println(kthLargest(arr, i));
		}
	}

	// 8. Find kth smallest Median Of Medians
	public void runKthSmallestMedianOfMedians() {
		final int[] arr = { 1, 2, -41, 5, -56, 7, 9, -4324, 64225, 56 };
		System.out.println(Arrays.toString(arr));
		for (var i = 1; i <= arr.length; i++) {
			System.out.println(kthSmallestMedianOfMedians(arr, i));
		}
	}

	// 9. Find kth largest Median Of Medians
	public void runKthLargestMedianOfMedians() {
		final int[] arr = { 1, 2, -41, 5, -56, 7, 9, -4324, 64225, 56 };
		System.out.println(Arrays.toString(arr));
		for (var i = 1; i <= arr.length; i++) {
			System.out.println(kthLargestMedianOfMedians(arr, i));
		}
	}

	// 10. Find median of 2 sorted arrays
	public void runMedianOfSortedArrays() {
		final int[] arr1 = { 1, 3 };
		final int[] arr2 = { 2 };
		System.out.println(findMedianOfSortedArrays(arr1, arr2));
	}

	// Algorithms
	// 1. Find max of all elements
	public int max(int[] arr) {
		var max = Integer.MIN_VALUE;
		for (final var element : arr) {
			max = Math.max(max, element);
		}
		return max;
	}

	// 2. Find max and min of all elements
	// Time - O(n), Space - O(1), # of comparisions = 2(n-1)
	public int[] minMax(int[] arr) {
		var max = Integer.MIN_VALUE;
		var min = Integer.MAX_VALUE;
		final var result = new int[2];
		for (final var element : arr) {
			if (element > max) {
				max = element;
			} else if (element < min) {
				min = element;
			}
		}
		result[0] = min;
		result[1] = max;
		return result;
	}

	// 3. Find min and max with less comparisions
	// Time - O(n), Space - O(1), # of comparisions = 3*(n/2 - 1)
	public int[] minMaxLessComparisions(int[] arr) {
		final var n = arr.length;
		var min = Integer.MAX_VALUE;
		var max = Integer.MIN_VALUE;

		final var result = new int[2];
		for (var i = 0; i < n - 1; i += 2) {
			if (arr[i] < arr[i + 1]) {
				if (arr[i] < min) {
					min = arr[i];
				}
				if (arr[i + 1] > max) {
					max = arr[i + 1];
				}
			} else {
				if (arr[i] > max) {
					max = arr[i];
				}
				if (arr[i + 1] < min) {
					min = arr[i + 1];
				}
			}
		}
		if (n % 2 != 0) {
			if (arr[n - 1] < min) {
				min = arr[n - 1];
			} else if (arr[n - 1] > max) {
				max = arr[n - 1];
			}
		}
		result[0] = min;
		result[1] = max;
		return result;
	}

	// 4. Find secondMax
	// Time - O(n), Space - O(1)
	public int secondMax(int[] arr) {
		var max = Integer.MIN_VALUE;
		var secondMax = Integer.MIN_VALUE;
		for (final var element : arr) {
			if (element > max) {
				secondMax = max;
				max = element;
			} else if (element > secondMax) {
				secondMax = element;
			}
		}
		return secondMax;
	}

	// 5. Find secondMin
	// Time - O(n), Space - O(1)
	public int secondMin(int[] arr) {
		var min = Integer.MAX_VALUE;
		var secondMin = Integer.MAX_VALUE;
		for (final var element : arr) {
			if (element < min) {
				secondMin = min;
				min = element;
			} else if (element < secondMin) {
				secondMin = element;
			}
		}
		return secondMin;
	}

	// 6. Find kth smallest TreeMap
	// Time - O(nlogn), Space - O(n)
	public int kthSmallestTreeMap(int[] arr, int k) {
		final var n = arr.length;
		if (k < 1 || k > n) {
			return -1;
		}
		final Map<Integer, Integer> treeMap = new TreeMap<>();
		// TreeMap insertion takes O(nlogn) time to insert n elements
		for (final var element : arr) {
			if (!treeMap.containsKey(element)) {
				treeMap.put(element, 1);
			} else {
				treeMap.put(element, treeMap.get(element) + 1);
			}
		}
		var cummulativeFrequency = 0;
		var kthSmallestElement = -1;
		for (final var entry : treeMap.entrySet()) {
			cummulativeFrequency += entry.getValue();
			if (cummulativeFrequency >= k) {
				kthSmallestElement = entry.getKey();
				break;
			}
		}
		return kthSmallestElement;
	}

	// 7. Find kth smallest element
	// *Time - Amortized case O(n)*, Space - O(n)
	public int kthSmallest(int[] arr, int k) {
		final int n = arr.length;
		// k is 1-based
		if (k < 1 || k > n) {
			return -1;
		}
		return kthSmallest(arr, 0, n - 1, k);
	}

	private int kthSmallest(int[] arr, int lo, int hi, int k) {
		OrderStatisticsUtils.randomizePivot(arr, lo, hi);
		final var pivotIndex = OrderStatisticsUtils.partition(arr, lo, hi);
		// as k is 1-based, k would be pivotIndex + 1 if kth min is pivot
		// element
		if (k == pivotIndex + 1) {
			return arr[pivotIndex];
		} else if (k < pivotIndex + 1) {
			return kthSmallest(arr, lo, pivotIndex - 1, k);
		} else {
			return kthSmallest(arr, pivotIndex + 1, hi, k);
		}
	}

	// 8. Find kth largest
	// *Time - Amortized case O(n)*, Space - O(n)
	public int kthLargest(int[] arr, int k) {
		return kthSmallest(arr, arr.length - k + 1);
	}

	// **9. kth smallest element Median of Medians**
	// *Time - Worse Case O(n)*, Space - O(n)
	public int kthSmallestMedianOfMedians(int[] arr, int k) {
		final int n = arr.length;
		if (k < 1 || k > n) {
			return -1;
		}
		final int pivot = findMoMPivot(arr);
		System.out.println("MoM Pivot: " + pivot);
		OrderStatisticsUtils.swap(arr, 0, pivot);
		return kthSmallestMedianOfMedians(arr, 0, arr.length - 1, k);
	}

	private int kthSmallestMedianOfMedians(int[] arr, int lo, int hi, int k) {
		final var pivotIndex = OrderStatisticsUtils.partition(arr, lo, hi);
		System.out.println("lo: " + lo + " hi: " + hi + " pivotIndex: " + pivotIndex);
		if (k == pivotIndex + 1) {
			return arr[pivotIndex];
		} else if (k < pivotIndex + 1) {
			return kthSmallestMedianOfMedians(arr, lo, pivotIndex - 1, k);
		} else {
			return kthSmallestMedianOfMedians(arr, pivotIndex + 1, hi, k);
		}
	}

	private int findMoMPivot(int[] arr) {
		final var n = arr.length;
		final var subArraySize = 5;
		final int numberOfSubArrays = (n % subArraySize != 0) ? (n / subArraySize) + 1 : (n / subArraySize);

		final var subArrayMatrix = new int[numberOfSubArrays][subArraySize];
		for (var i = 0; i < numberOfSubArrays; i++) {
			subArrayMatrix[i] = new int[subArraySize];
		}

		for (var i = 0; i < numberOfSubArrays; i++) {
			for (var j = 0; j < subArraySize; j++) {
				if (i * subArraySize + j < n) {
					subArrayMatrix[i][j] = arr[i * subArraySize + j];
				}
			}
		}

		for (var i = 0; i < numberOfSubArrays; i++) {
			Arrays.sort(subArrayMatrix[i]);
		}
		final var mediansArray = new int[numberOfSubArrays];
		for (var i = 0; i < numberOfSubArrays; i++) {
			mediansArray[i] = subArrayMatrix[i][subArraySize / 2];
		}
		Arrays.sort(mediansArray);
		final var medianOfMedians = mediansArray[mediansArray.length / 2];
		for (var i = 0; i < numberOfSubArrays; i++) {
			if (subArrayMatrix[i][subArraySize / 2] == medianOfMedians) {
				return i * subArraySize + subArraySize / 2;
			}
		}
		return -1;
	}

	// 10. Kth Largest element Median of Medians
	public int kthLargestMedianOfMedians(int[] arr, int k) {
		return kthSmallestMedianOfMedians(arr, arr.length - k + 1);
	}

	// 11. Find median of sorted arrays in O(logn)
	// https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/
	// https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2471/Very-concise-O(log(min(MN)))-iterative-solution-with-detailed-explanation
	// Time - O(logn), Space - O(1)
	public double findMedianOfSortedArrays(int[] arr1, int[] arr2) {
		final var n1 = arr1.length;
		final var n2 = arr2.length;
		if (n1 < n2) {
			// make sure the arr2 is shorter
			return findMedianOfSortedArrays(arr2, arr1);
		}
		// lo and hi represent the range of cut locations in the shorter array
		var lo = 0;
		var hi = n2;
		while (lo <= hi) {
			// Try cut 2
			final var cut2 = (lo + hi) / 2;
			// calculate position of cut1 accordingly
			final var cut1 = (n1 + n2) / 2 - cut2;

			final var l1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
			final var l2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];

			final var r1 = (cut1 == n1) ? Integer.MAX_VALUE : arr1[cut1];
			final var r2 = (cut2 == n2) ? Integer.MAX_VALUE : arr2[cut2];

			if (l1 > r2) {
				// A1's lower half is too big, need to move A1's cut to the left
				// (A2's cut to the right)
				lo = cut2 + 1;
			} else if (l2 > r1) {
				// A2's lower half is too big, need to move A2's cut to the left
				hi = cut2 - 1;
			} else {
				// Otherwise, it's the right cut!
				return (n1 + n2) % 2 == 1 ? Math.min(r1, r2) : (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
			}
		}
		return -1;
	}
}

class OrderStatisticsUtils { // NOSONAR
	private static final Random random = new Random();

	public static void swap(int[] arr, int i, int j) {
		final var temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void randomizePivot(int[] arr, int lo, int hi) {
		if (lo == hi) {
			return;
		}
		final var randomPivotIndex = lo + random.nextInt(hi - lo);
		swap(arr, lo, randomPivotIndex);
	}

	public static int partition(int[] arr, int lo, int hi) {
		final var pivot = lo;
		var i = lo;
		var j = hi;
		while (i <= j) {
			while (i <= hi && arr[i] <= arr[pivot]) {
				i++;
			}
			while (j >= lo && arr[j] > arr[pivot]) {
				j--;
			}
			if (i < j) {
				swap(arr, i, j);
			}
		}
		swap(arr, pivot, j);
		return j;
	}
}