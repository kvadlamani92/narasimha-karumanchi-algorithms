package narasimha.karumanchi.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;
import java.util.Vector;

/*
 * Bubble sort takes n^2/2 comparisions and n^2/2 swaps (inversions)
 * Selection sort takes n^2/2 comparisons and n swaps
 * Insertion sort takes n^2/4 comparisions and n^2/8 swaps in average case and in worst case, it's n^2/4 
 * Insertion sort is almost linear for partially sorted input.
 * Selection sort best suits for elements with bigger values and small keys.
 * 
 * External Merge sort:
 * 
 * A number of records from each tape are read into main memory, sorted using an internal sort, and then output to the tape. For the sake of clarity, 
 * let us assume that 900 megabytes of data needs to be sorted using only 100 megabytes of RAM.
 * 1) Read 100MB of the data into main memory and sort by some conventional method (let us say Quick sort).
 * 2) Write the sorted data to disk
 * 3) Repeat steps 1 and 2 until all of the data is sorted in chunks of 100MB. Now we need to merge them into one single sorted output file.
 * 4) Read the first 10MB of each sorted chunk (call them input buffers) in main memory (90MB total) and allocate the remaining 10MB for output buffer.
 * 5) Perform a 9-way Mergesort and store the result in the output buffer. If the output buffer is full, write it to the final sorted file. If any of the 9 input buffers gets empty,
 * fill it with the next 10MB of its associated 100MB sorted chunk; or if there is no more data in the sorted chunk, mark it as exhausted and do not use it for merging.
 * The above algorithm can be generalized by assuming that the amount of data to be sorted exceeds the available memory by a factor of K. Then, K chunks of data need to be sorted and a K-way merge has to be completed.
 * If X is the amount of main memory available, there will be K input buffers and 1 output buffer of size X/(K + 1) each. Depending on various factors (how fast is the hard drive?)
 *  better performance can be achieved if the output buffer is made larger (for example, twice as large as one input buffer).
 * */
public class SortingMain {
	public static void main(String[] args) {
		final var sortingMain = new SortingMain();
		// sortingMain.runBubbleSort();
		// sortingMain.runSelectionSort();
		// sortingMain.runInsertionSort();
		// sortingMain.runShellSort();
		// sortingMain.runMergeSort();
		// sortingMain.runHeapSort();
		sortingMain.runQuickSort();
		// sortingMain.runTreeSort();
		// sortingMain.runCountingSort();
		// sortingMain.runBucketSort();
		// sortingMain.runRadixSort();
	}

	// Runners
	public void runBubbleSort() {
		final int[] arr = { 3, 5, 2, 1, 6 };
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runSelectionSort() {
		final int[] arr = { 3, 5, 2, 1, 6 };
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runInsertionSort() {
		final int[] arr = { 3, 5, 2, 1, 6 };
		insertionSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runShellSort() {
		final int[] arr = { 3, 5, 2, 6, 4, 1 };
		shellSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runMergeSort() {
		final int[] arr = { 3, 5, 2, 6, 4, 1 };
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runHeapSort() {
		final int[] arr = { 3, 5, 2, 6, 4, 1 };
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runQuickSort() {
		final int[] arr = { 3, 5, 2, 6, 4, 1 };
		quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runTreeSort() {
		final int[] arr = { 3, 5, 2, 6, 4, 1 };
		treeSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runCountingSort() {
		final int[] arr = { 3, 3, 5, 5, 2, 6, 4, 1 };
		countingSort(arr);
		System.out.println(Arrays.toString(arr));
		final int[] arr2 = { 1, 4, 1, 2, 7, 5, 2 };
		countingSort(arr2);
		System.out.println(Arrays.toString(arr2));
	}

	public void runBucketSort() {
		final double[] arr = { 0.5, 0.24, 0.36, 0.35, 0.33, 0.9, 0.74, 0.99 };
		bucketSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void runRadixSort() {
		final int[] arr = { 3, 3, 5, 5, 2, 6, 4, 1 };
		radixSort(arr);
		System.out.println(Arrays.toString(arr));
		final int[] arr2 = { 18, 14, 1, 24, 7, 5, 2, 432, 65645, 4342 };
		radixSort(arr2);
		System.out.println(Arrays.toString(arr2));
	}

	// Algorithms
	// 1. Bubble Sort
	// For n-1 iterations, compare every element with it's next to make the
	// largest number in every iteration to the right most in the array
	public void bubbleSort(int[] arr) {
		final var n = arr.length;
		for (var i = 0; i < n - 1; i++) {
			for (var j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					final int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	// 2. Selection Sort
	// For n-1 iterations, for every element, select a min from the next element
	// till the end and swap with the current element if min < current element
	// Alternatively you can choose max from next elements and swap with the
	// current element from end as well
	public void selectionSort(int[] arr) {
		final var n = arr.length;
		for (var i = 0; i < n - 1; i++) {
			var minIndex = i + 1;
			for (var j = i + 1; j < n - 1; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			if (arr[minIndex] < arr[i]) {
				swap(arr, i, minIndex);
			}
		}
	}

	private void swap(int[] arr, int i, int j) {
		final var temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// 3. Insertion Sort
	// We begin by assuming that a list with one item (position 0) is already
	// sorted. On each pass, one for each item 1 through 􏰀 − 1, the current
	// item is checked against those in the already sorted sublist. As we look
	// back into the already sorted sublist, we shift those items that are
	// greater to the right. When we reach a smaller item or the end of the
	// sublist, the current item can be inserted.
	// Insertion sort is one of the elementary sorting algorithms with O(n^2)
	// worst-case time. Insertion sort is used when the data is nearly sorted
	// (due to its adaptiveness) or when the input size is small (due to its low
	// overhead). For these reasons and due to its stability, insertion sort is
	// used as the recursive base case (when the problem size is small) for
	// higher overhead divide-and-conquer sorting algorithms, such as merge sort
	// or quick sort.
	// Time - O(n^2), Space - O(1)
	public void insertionSort(int[] arr) {
		final var n = arr.length;
		for (var i = 1; i < n; i++) {
			final var val = arr[i];
			var j = i;
			for (; j > 0 && arr[j - 1] > val; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = val;
		}
	}

	// 4. Shell Sort
	// The basic idea in shellsort is to exchange every hth element in the
	// array. h determines how far apart element exchange can happen, for ex: if
	// h as 13, the first element (index-0) is exchanged with the 14th element
	// (index-13) if necessary (of course). The second element with the 15th
	// element, and so on. Now if we take h as 1, it is exactly the same as a
	// regular insertion sort.
	// Time - O(n(logn)^2), Space - O(1)
	public void shellSort(int[] arr) {
		final var n = arr.length;
		var h = 0;
		for (h = 1; 3 * h + 1 < n; h = 3 * h + 1) {
		}
		for (; h > 0; h = h / 3) {
			System.out.println("h:" + h);
			for (var i = h; i < n; i++) {
				System.out.println("i:" + i);
				System.out.println(Arrays.toString(arr));
				final var val = arr[i];
				var j = i;
				for (; j >= h && arr[j - h] > val; j -= h) {
					arr[j] = arr[j - h];
				}
				arr[j] = val;
				System.out.println(Arrays.toString(arr));
			}
		}
	}

	// 5. Merge sort
	// Time - O(nlogn), Space - O(n)
	public void mergeSort(int[] arr) {
		final var n = arr.length;
		final var temp = new int[n];
		mergeSort(arr, 0, n - 1, temp);
	}

	private void mergeSort(int[] arr, int lo, int hi, int[] temp) {
		if (lo < hi) {
			final var mid = lo + (hi - lo) / 2;
			mergeSort(arr, lo, mid, temp);
			mergeSort(arr, mid + 1, hi, temp);
			merge(arr, lo, mid, hi, temp);
		}
	}

	private void merge(int[] arr, int lo, int mid, int hi, int[] temp) {
		var i = lo;
		var j = mid + 1;
		var k = lo;
		while (i <= mid && j <= hi) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[k++] = arr[i++];
		}
		while (j <= hi) {
			temp[k++] = arr[j++];
		}
		for (var index = lo; index <= hi; index++) {
			arr[index] = temp[index]; // NOSONAR
		}
	}

	// 6. Heap Sort
	// Time - O(nlogn), Space - O(n)
	public void heapSort(int[] arr) {
		final var minHeap = new PriorityQueue<>();
		for (final var element : arr) {
			minHeap.add(element);
		}
		var index = 0;
		while (!minHeap.isEmpty()) {
			arr[index++] = (int) minHeap.poll();
		}
	}

	// 7. Quick sort
	// Time - O(nlogn), Space - O(1)
	public void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private void quickSort(int[] arr, int lo, int hi) {
		if (lo < hi) {
			final int partitionIndex = partition(arr, lo, hi);
			quickSort(arr, lo, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, hi);
		}
	}

	// Partitioning begins by locating two position markers—let’s call them i
	// and j at the beginning and end of the remaining items in the list. The
	// goal of the partition process is to move items that are on the wrong side
	// with respect to the pivot value while converging on the split point also.
	// At the point where j becomes less than i, we stop. The position of j is
	// now the partition point (as it is the position before the final element
	// greater than the pivot)
	private int partition(int[] arr, int lo, int hi) {
		randomizePivot(arr, lo, hi);
		// pivot is always lo as randomized index is swapped with lo in above
		// step
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

	private void randomizePivot(int[] arr, int lo, int hi) {
		final var random = new Random();
		final int randomPivotIndex = lo + random.nextInt(hi - lo);
		swap(arr, lo, randomPivotIndex);
	}

	// 8. Tree Sort
	// Time - O(nlogn), Space - O(n)
	public void treeSort(int[] arr) {
		final var treeSet = new TreeSet<>();
		for (final var element : arr) {
			treeSet.add(element);
		}
		var index = 0;
		while (!treeSet.isEmpty()) {
			arr[index++] = (int) treeSet.pollFirst();
		}
	}

	// Linear Sorting Algorithms
	// 9. Counting Sort
	// It assumes the numbers in the array are in range [0..255]
	// Time - O(n), Space - O(n)
	public void countingSort(int[] arr) {
		final var n = arr.length;
		final var countingArr = new int[256];
		for (var i = 0; i < n; i++) {
			countingArr[arr[i]]++;
		}
		// change countingArr[i] so that countingArr[i] now contains the actual
		// position of number i in the output array
		for (var i = 1; i < 256; i++) {
			countingArr[i] += countingArr[i - 1];
		}
		final var outputArr = new int[n];
		// Build the output array. To make it stable, we are iterating in the
		// reverse order from last to first
		for (int i = n - 1; i >= 0; i--) {
			outputArr[countingArr[arr[i]] - 1] = arr[i];
			countingArr[arr[i]]--;
		}
		// copy the output array to arr so that arr contains the final sorted
		// numbers`
		System.arraycopy(outputArr, 0, arr, 0, n);
	}

	// 10. Bucket sort
	// Lets say we want to sort a list of double values distrubuted uniformly
	// across the number range from [0..1] for ex: [0.2,0.34,0.5,0.74,0.99]
	// Time - O(n), Space - O(bucketSize)
	public void bucketSort(double[] arr) {
		final var bucketSize = 10;
		final var buckets = new Vector[bucketSize];
		for (var i = 0; i < bucketSize; i++) {
			buckets[i] = new Vector<>();
		}
		var maxElement = Double.MIN_VALUE;
		for (final var element : arr) {
			maxElement = Math.max(maxElement, element);
		}
		for (final var element : arr) {
			var bucketIndex = (int) ((element / maxElement) * bucketSize);
			if (bucketIndex > 0) {
				bucketIndex--;
			}
			buckets[bucketIndex].add(element);
		}
		for (final var vector : buckets) {
			Collections.sort(vector);
		}
		var index = 0;
		for (final Vector<Double> vector : buckets) {
			for (final double element : vector) {
				arr[index++] = element;
			}
		}
	}

	// 11. Radix sort
	public void radixSort(int[] arr) {
		var maxNumber = Integer.MIN_VALUE;
		for (final var element : arr) {
			maxNumber = Math.max(maxNumber, element);
		}
		for (var exponent = 1; maxNumber / exponent > 0; exponent *= 10) {
			radixCountSort(arr, exponent);
		}
	}

	private void radixCountSort(int[] arr, int exponent) {
		final var n = arr.length;
		// as a single digit number fits from [0..9]
		final var countArraySize = 10;
		final var countingArr = new int[countArraySize];
		for (var i = 0; i < n; i++) {
			countingArr[(arr[i] / exponent) % 10]++;
		}
		// change countingArr[i] so that countingArr[i] now contains the actual
		// position of number i in the output array
		for (var i = 1; i < countArraySize; i++) {
			countingArr[i] += countingArr[i - 1];
		}
		final var outputArr = new int[n];
		// Build the output array. To make it stable, we are iterating in the
		// reverse order from last to first
		for (var i = n - 1; i >= 0; i--) {
			outputArr[countingArr[(arr[i] / exponent) % 10] - 1] = arr[i];
			countingArr[(arr[i] / exponent) % 10]--;
		}
		// copy the output array to arr so that arr contains the final sorted
		// numbers`
		System.arraycopy(outputArr, 0, arr, 0, n);
	}

	// 12. Given an array A of n elements, each of which is an integer in the
	// range [1..n^2]. How do we sort the array in O(n) time?
	// Solution: If we subtract each number by 1 then we get the range [0, n^2 –
	// 1]. If we consider all number as 2−digit base n. Each digit ranges from 0
	// to n^2 - 1. Sort this using radix sort. This uses only two calls to
	// counting sort. Finally, add 1 to all the numbers. Since there are 2
	// calls, the complexity is 2*O(n) = O(n)

	// 13. What if the range is [1..n^3] in the above problem?
	// Solution: Still use Radix sort. If we subtract each number by 1 then we
	// get the range [0, n^3 –
	// 1]. If we consider all number as 3−digit base n. Each digit ranges from 0
	// to n^3 - 1. Sort this using radix sort. This uses three calls to
	// counting sort. Finally, add 1 to all the numbers. Since there are 3
	// calls, the complexity is 3 * O(n) = O(n)
}
