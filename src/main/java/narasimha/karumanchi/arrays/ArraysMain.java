package narasimha.karumanchi.arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ArraysMain {
	public static void main(String[] args) {
		final var arraysMain = new ArraysMain();
		// arraysMain.runRemoveAdjacentDuplicatesRecursively();
		arraysMain.runMaxSumInSlidingWindow();
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
		final int n = arr.length;
		final int[] res = new int[n - k + 1];
		final Deque<Integer> q = new ArrayDeque<>();
		int index = 0;
		for (int i = 0; i < n; i++) {
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
}