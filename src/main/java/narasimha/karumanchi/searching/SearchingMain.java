package narasimha.karumanchi.searching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SearchingMain {
	public static void main(String[] args) {
		final var searchingMain = new SearchingMain();
		// searchingMain.runFindIterative();
		// searchingMain.runFindBinary();
		// searchingMain.runFindInterpolationSearch();
		// searchingMain.runIsDuplicate();
		// searchingMain.runIsDuplicateExistNRange();
		// searchingMain.runFindMaxFrequencyElement();
		// searchingMain.runFindMaxFrequencyElementNRange();
		// searchingMain.runFindMaxFrequencyElementNRangeInPlace();
		// searchingMain.runFindFirstRepeatedElement();
		// searchingMain.runFindMissingNumberNRange();
		// searchingMain.runFindMissingNumberNRangeXOR();
		// searchingMain.runFindOddFrequencyNumber();
		// searchingMain.runFindTwoRepeatingElements();
		// searchingMain.runFindTwoRepeatingElementsXOR();
		// searchingMain.runFindSumToK();
		// searchingMain.runFindPythagoreanNumbers();
		// searchingMain.runFindClosestSumToZero();
		// searchingMain.runFindTripletToK();
		// searchingMain.runfindSumOfThreeNumbersClosestToZero();
		// searchingMain.runFindMountainNumber();
		// searchingMain.runFindPivotInRotatedSortedArray();
		// searchingMain.runFindInRoatedSortedArray();
		// searchingMain.runFindFirstOccuranceInSortedArray();
		// searchingMain.runFindLastOccuranceInSortedArray();
		// searchingMain.runFindFrequencyInASortedArray();
		// searchingMain.runFindMajorityNumber();
		// searchingMain.runFindLocalMinima();
		// searchingMain.runFindLocalMaxima();
		// searchingMain.runFindElementInSortedMatrix();
		// searchingMain.runFindElementInSortedMatrixEfficient();
		// searchingMain.runFindRowWithMaxZeros();
		// searchingMain.runSeparateEvenAndOddNumbers();
		// searchingMain.runMoveZerosTillTheEnd();
		// searchingMain.runSortArrayWith012s();
		// searchingMain.runFindNumberOfTrailingZerosInNFactorial();
		searchingMain.runFindPivotIndex();
	}

	// Runners
	// 1. Find linear
	public void runFindIterative() {
		final var arr = SearchingUtils.createArray();
		System.out.println(findLinear(arr, 5));
	}

	// 2. Find Binary
	public void runFindBinary() {
		final var arr = SearchingUtils.createSortedArray();
		System.out.println(findBinary(arr, 4));
	}

	// 3. Find interpolation search
	public void runFindInterpolationSearch() {
		final var arr = SearchingUtils.createSortedArray();
		System.out.println(findInterpolationSearch(arr, 10));
	}

	// 4. Check duplicates
	public void runIsDuplicate() {
		final var arr = SearchingUtils.createArrayWithDuplicates();
		System.out.println(isDuplicateExist(arr));
	}

	// 5. Check duplicates
	public void runIsDuplicateExistNRange() {
		final var arr = SearchingUtils.createArrayWithDuplicatesNRange();
		System.out.println(isDuplicateExistNRange(arr));
	}

	// 6. Check Max frequency element
	public void runFindMaxFrequencyElement() {
		final var arr = SearchingUtils.createArrayWithDuplicates();
		System.out.println(findMaxFrequencyElement(arr));
	}

	// 7. Check Max frequency element N Range
	public void runFindMaxFrequencyElementNRange() {
		final var arr = SearchingUtils.createArrayWithDuplicatesNRange();
		System.out.println(findMaxFrequencyElementNRange(arr));
	}

	// 8. Check Max frequency element N Range In Place
	public void runFindMaxFrequencyElementNRangeInPlace() {
		final var arr = SearchingUtils.createArrayWithDuplicatesNRange();
		System.out.println(findMaxFrequencyElementNRangeInPlace(arr));
	}

	// 9. Find first repeated element
	public void runFindFirstRepeatedElement() {
		final var arr = SearchingUtils.createArrayForFirstRepeatedElement();
		System.out.println(findFirstRepeatedElement(arr));
	}

	// 10. Find the missing number
	public void runFindMissingNumberNRange() {
		final var arr = SearchingUtils.createArrayForMissingElementNRange();
		System.out.println(findMissingNumberNRange(arr));
	}

	// 11. Find the missing number
	public void runFindMissingNumberNRangeXOR() {
		final var arr = SearchingUtils.createArrayForMissingElementNRange();
		System.out.println(findMissingNumberNRangeXOR(arr));
	}

	// 12. Run find odd frequency number
	public void runFindOddFrequencyNumber() {
		final var arr = SearchingUtils.createArrayWithOddFrequencyElement();
		System.out.println(findOddFrequencyNumber(arr));
	}

	// 13. Run find 2 repeating elements
	public void runFindTwoRepeatingElements() {
		final var arr = SearchingUtils.createArrayWithTwoRepeatingElements();
		System.out.println(Arrays.toString(findTwoRepeatingElements(arr)));
	}

	// 14. Run find 2 repeating elements XOR
	public void runFindTwoRepeatingElementsXOR() {
		final var arr = SearchingUtils.createArrayWithTwoRepeatingElements();
		System.out.println(Arrays.toString(findTwoRepeatingElementsXOR(arr)));
	}

	// 15. Run Find Sum to K
	public void runFindSumToK() {
		final var arr = SearchingUtils.createArray();
		final int[] result = findSumToK(arr, 13);
		System.out.println(Arrays.toString(result));
	}

	// 16. Find pythagorean numbers
	public void runFindPythagoreanNumbers() {
		final var arr = SearchingUtils.createArray();
		final int[] result = findPythagoreusNumbers(arr);
		System.out.println(Arrays.toString(result));
	}

	// 17. Find pythagorean numbers
	public void runFindClosestSumToZero() {
		final var arr = SearchingUtils.createArrayWithNegativeNumbers();
		final int[] result = findSumClosestToZero(arr);
		System.out.println(Arrays.toString(result));
	}

	// 18. Find triplet to K
	public void runFindTripletToK() {
		final var arr = SearchingUtils.createArray();
		final int[] result = findTripletSumToK(arr, 10);
		System.out.println(Arrays.toString(result));
	}

	// 19. Find sum of 3 numbers closest to zero
	public void runfindSumOfThreeNumbersClosestToZero() {
		final var arr = SearchingUtils.createArrayWithNegativeNumbers();
		final int[] result = findSumOfThreeNumbersClosestToZero(arr);
		System.out.println(Arrays.toString(result));
	}

	// 20. Find mountain number
	public void runFindMountainNumber() {
		final var arr = SearchingUtils.createArrayWithMountainNumber();
		final int result = findMountainNumber(arr);
		System.out.println(result);
	}

	// 21. Find pivot in roated sorted array
	public void runFindPivotInRotatedSortedArray() {
		final var arr = SearchingUtils.createRotatedSortedArray();
		final int pivot = findPivotInRotatedSortedArray(arr);
		System.out.println(pivot);
	}

	// 22. Find element in roated sorted array
	public void runFindInRoatedSortedArray() {
		final var arr = SearchingUtils.createRotatedSortedArray();
		final int index = findInRotatedSortedArray(arr, 44);
		System.out.println(index);
	}

	// 23. Find first occurance of the element in a sorted array
	public void runFindFirstOccuranceInSortedArray() {
		final var arr = SearchingUtils.createSortedArray();
		final int index = findFirstOccuranceInSortedArray(arr, 3);
		System.out.println(index);
	}

	// 24. Find last occurance of the element in a sorted array
	public void runFindLastOccuranceInSortedArray() {
		final var arr = SearchingUtils.createSortedArray();
		final int index = findLastOccuranceInSortedArray(arr, 3);
		System.out.println(index);
	}

	// 25. Find Frequency of a number in a sorted array
	public void runFindFrequencyInASortedArray() {
		final var arr = SearchingUtils.createSortedArray();
		final int index = countFrequencyInSortedArray(arr, 3);
		System.out.println(index);
	}

	// 26. Find majority number
	public void runFindMajorityNumber() {
		final var arr = SearchingUtils.createArray();
		final int number = findMajorityNumber(arr);
		System.out.println(number);
	}

	// 27. Find local minima
	public void runFindLocalMinima() {
		final var arr = SearchingUtils.createArray();
		final int number = findLocalMinima(arr);
		System.out.println(number);
	}

	// 28. Find local maxima
	public void runFindLocalMaxima() {
		final var arr = SearchingUtils.createArray();
		final int number = findLocalMaxima(arr);
		System.out.println(number);
	}

	// 29. Find element in a sorted matrix
	public void runFindElementInSortedMatrix() {
		final int[][] arr = SearchingUtils.createSortedMatrix();
		final int[] indexes = findElementInSortedMatrix(arr, 12);
		System.out.println(Arrays.toString(indexes));
	}

	// 30. Find element in a sorted matrix efficient
	public void runFindElementInSortedMatrixEfficient() {
		final int[][] arr = SearchingUtils.createSortedMatrix();
		final int[] indexes = findElementInSortedMatrixEfficient(arr, 11);
		System.out.println(Arrays.toString(indexes));
	}

	// 31. Find row with max zeros
	public void runFindRowWithMaxZeros() {
		final int[][] arr = SearchingUtils.createMatrixWithOnesAndZeros();
		final int[] rowWithZeroCount = findRowWithMaxZeros(arr);
		System.out.println(Arrays.toString(rowWithZeroCount));
	}

	// 32. Separate even and odd numbers
	public void runSeparateEvenAndOddNumbers() {
		final var arr = SearchingUtils.createArray();
		System.out.println(Arrays.toString(arr));
		separateEvenAndOddNumbers(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 33. Move zeros to the end
	public void runMoveZerosTillTheEnd() {
		final var arr = SearchingUtils.createArrayWithOnesAndZeros();
		System.out.println(Arrays.toString(arr));
		moveZerosToLast(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 34. Sort array with 0,1,2s
	public void runSortArrayWith012s() {
		final var arr = SearchingUtils.createArrayWithZeroOneTwos();
		System.out.println(Arrays.toString(arr));
		sortArrayWith012s(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 35. Find number of trailing 0s in n!
	public void runFindNumberOfTrailingZerosInNFactorial() {
		System.out.println(findTrailingZeroCountInNFactorial(20));
	}

	// 36. Find pivot index
	public void runFindPivotIndex() {
		final var arr = SearchingUtils.createArray();
		final var index = findPivotIndex(arr);
		System.out.println(index);
	}

	// Algorithms
	// 1. Linear search
	public int findLinear(int[] arr, int k) {
		final var n = arr.length;
		for (var i = 0; i < n; i++) {
			if (arr[i] == k) {
				return i;
			}
		}
		return -1;
	}

	// 2. Find Binary
	public int findBinary(int[] arr, int k) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (arr[mid] == k) {
				return mid;
			}
			if (arr[mid] < k) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return -1;
	}

	// 3. Find using interpolation search
	public int findInterpolationSearch(int[] arr, int k) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo < hi) {
			final var mid = lo + (k - arr[lo]) * (hi - lo) / (arr[hi] - arr[lo]);
			if (mid < 0 || mid > n - 1) { // out of range
				return -1;
			}
			if (arr[mid] == k) {
				return mid;
			}
			if (arr[mid] < k) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}

		}
		return -1;
	}

	// 4. Check for duplicates
	// Time - O(n), Space - O(n)
	public boolean isDuplicateExist(int[] arr) {
		final var n = arr.length;
		final Set<Integer> set = new HashSet<>();
		for (var i = 0; i < n; i++) {
			if (set.contains(arr[i])) {
				return true;
			} else {
				set.add(arr[i]);
			}
		}
		return false;
	}

	// 5. Check for duplicates assuming all elements in array are in range
	// [0..n-1]
	// Time - O(n), Space - O(1)
	public boolean isDuplicateExistNRange(int[] arr) {
		final var n = arr.length;
		for (var i = 0; i < n; i++) {
			if (arr[Math.abs(arr[i])] < 0) {
				return true;
			}
			arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
		}
		return false;
	}

	// 6. Find element that appears max number of times
	// Time - O(n), Space - O(n)
	public int findMaxFrequencyElement(int[] arr) {
		final var n = arr.length;
		final Map<Integer, Integer> map = new HashMap<>();
		for (var i = 0; i < n; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
		}
		var maxFrequencyElement = 0;
		var maxFrequency = 0;
		for (final var entry : map.entrySet()) {
			final int element = entry.getKey();
			final int count = entry.getValue();
			if (count > maxFrequency) {
				maxFrequency = count;
				maxFrequencyElement = element;
			}
		}
		return maxFrequencyElement;
	}

	// 7. Find element that appears max number of times N range
	// Time - O(n), Space - O(1)
	public int findMaxFrequencyElementNRange(int[] arr) {
		final var n = arr.length;
		final int[] countArr = new int[256];
		for (var i = 0; i < n; i++) {
			countArr[arr[i]]++;
		}
		var maxFrequencyElement = 0;
		var maxCount = 0;
		for (var i = 0; i < countArr.length; i++) {
			if (countArr[i] > maxCount) {
				maxCount = countArr[i];
				maxFrequencyElement = i;
			}
		}
		return maxFrequencyElement;
	}

	// 8. Find element that appears max number of times N range
	// Time - O(n), Space - O(1)
	public int findMaxFrequencyElementNRangeInPlace(int[] arr) {
		final var n = arr.length;
		for (var i = 0; i < n; i++) {
			// for every element i..increase arr[i] by n
			arr[arr[i] % n] += n;
		}
		var maxFrequencyElement = 0;
		var maxCount = 0;
		for (var i = 0; i < n; i++) {
			// count of repeated element i in the array
			final int count = arr[i] / n;
			if (count > maxCount) {
				maxCount = count;
				maxFrequencyElement = i;
			}
		}
		return maxFrequencyElement;
	}

	// 9. Find the first repeated element in the array
	// Time - O(n), Space - O(n)
	public int findFirstRepeatedElement(int[] arr) {
		final var n = arr.length;
		final Map<Integer, Boolean> map = new HashMap<>();
		for (var i = 0; i < n; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], false);
			} else {
				map.put(arr[i], true);
			}
		}
		for (var i = 0; i < n; i++) {
			if (map.containsKey(arr[i]) && map.get(arr[i]).booleanValue()) {
				return arr[i];
			}
		}
		return -1;
	}

	// 10. Find the missing number where we are given N-1 elements and all
	// elements are from [1..N]
	// Time - O(n), space - O(1)
	public int findMissingNumberNRange(int[] arr) {
		final var arrayLength = arr.length;
		final var n = arrayLength + 1;
		final long sum = n * (n + 1) / 2;
		long arraySum = 0;
		for (final var num : arr) {
			arraySum += num;
		}
		return (int) (sum - arraySum);
	}

	// 11. Find the missing number where we are given N-1 elements and all
	// elements are from [1..N]
	// Time - O(n), space - O(1)
	public int findMissingNumberNRangeXOR(int[] arr) {
		final var arrayLength = arr.length;
		final var n = arrayLength + 1;
		var xor = 0;
		for (final var num : arr) {
			xor ^= num;
		}
		for (var i = 1; i <= n; i++) {
			xor ^= i;
		}
		return xor;
	}

	// 12. All elements occur twice but one element occures thrice. Find that
	// number.
	// Time - O(n), space - O(1)
	public int findOddFrequencyNumber(int[] arr) {
		var xor = 0;
		for (final var num : arr) {
			xor ^= num;
		}
		return xor;
	}

	// 13. Find two repeating elements in the array...[4,2,4,5,2,3,1]
	public int[] findTwoRepeatingElements(int[] arr) {
		final var arrayLength = arr.length;
		final var n = arrayLength - 2; // # of distinct elements
		final var sum = n * (n + 1) / 2;
		var arraySum = 0;
		long product = 1;
		for (final var element : arr) {
			arraySum += element;
			product *= element;
		}
		final var sumOfTwoNumbers = arraySum - sum;
		final var productOfTwoNumbers = product / fact(n);
		final var differenceOfTwoNumbers = Math.sqrt(sumOfTwoNumbers * sumOfTwoNumbers - 4 * productOfTwoNumbers);
		final var result = new int[2];
		result[0] = (int) (sumOfTwoNumbers + differenceOfTwoNumbers) / 2;
		result[1] = (int) (sumOfTwoNumbers - differenceOfTwoNumbers) / 2;
		return result;
	}

	// 14. Find two repeating elements XOR
	public int[] findTwoRepeatingElementsXOR(int[] arr) {
		final var arrayLength = arr.length;
		final var n = arrayLength - 2;
		var x = 0;
		var y = 0;
		var xorOfTwoElements = 0;
		for (final var num : arr) {
			xorOfTwoElements ^= num;
		}
		for (var i = 1; i <= n; i++) {
			xorOfTwoElements ^= i;
		}
		// if xorOfTwoElements has 1 in a bit, it means x and y has different
		// bits at that place. So if we divide all numbers into 2 groups one
		// with last bit set and other with last bit unset, x and y would go
		// definitely go into different groups. And pairs of same numbers will
		// always go to the same group. (Last bit is either 1 in both or 0 in
		// both). So if we xor all the elements with the last bit set in the
		// array and in numbers [1..n], same numbers from the array and the
		// numbers 1..n would go into the same group and also x and y in those
		// respective groups. Eg: [4,2,4,5,2,3,1] -> [1,4,4,5] with right most
		// bit (bit 3) unset and [2,2,3] with bit set. Now xoring with [1..n] in
		// respective groups would yield [(1^4^4^5)^(1^4^5)] = 4 and
		// [(2^2^3)^(2^3)] = 2
		// set's only the right most set bit
		final var xorOfTwoElementsWithOnlyRightMostBit = xorOfTwoElements & ~(xorOfTwoElements - 1);
		// categorize x and y into 2 sets based on the last bit set/unset
		for (var i = 0; i < arrayLength; i++) {
			if ((arr[i] & xorOfTwoElementsWithOnlyRightMostBit) == 0) {
				x ^= arr[i];
			} else {
				y ^= arr[i];
			}
		}
		for (var i = 1; i <= n; i++) {
			if ((i & xorOfTwoElementsWithOnlyRightMostBit) == 0) {
				x ^= i;
			} else {
				y ^= i;
			}
		}
		final int[] result = new int[2];
		result[0] = x;
		result[1] = y;
		return result;
	}

	private long fact(int n) {
		if (n <= 1) {
			return 1;
		}
		return n * fact(n - 1);
	}

	// 15. Every element appears twice except single element. Find it
	// Ans: Do XOR of all elements. You'll get it

	// 16. Find a, b such that arr[a] + arr[b] = k
	// Time - O(n), Space - O(n)
	public int[] findSumToK(int[] arr, int k) {
		final var n = arr.length;
		final var result = new int[2];
		final Set<Integer> set = new HashSet<>();
		for (final var element : arr) {
			set.add(element);
		}
		for (var i = 0; i < n; i++) {
			if (set.contains(k - arr[i])) {
				result[0] = arr[i];
				result[1] = k - arr[i];
				return result;
			}
		}
		return null;
	}

	// 17. Find a,b,k such that arr[a]^2 + arr[b]^2 = arr[k]^2
	// Time - O(n^2), Space - O(1)
	public int[] findPythagoreusNumbers(int[] arr) {
		final var n = arr.length;
		final var result = new int[3];
		Arrays.sort(arr);
		for (var k = n - 1; k >= 0; k--) {
			var i = 0;
			var j = k - 1;
			final var cSquare = arr[k] * arr[k];
			while (i < j) {
				final var aSquare = arr[i] * arr[i];
				final var bSquare = arr[j] * arr[j];
				if (aSquare + bSquare < cSquare) {
					i++;
				} else if (aSquare + bSquare > cSquare) {
					j--;
				} else {
					result[0] = arr[i];
					result[1] = arr[j];
					result[2] = arr[k];
					return result;
				}
			}
		}
		return result;
	}

	// 18. Given an array with +ve and -ve numbers, find the numbers whose sum
	// is closest to zero
	// Time - O(nlogn), Space - O(1)
	public int[] findSumClosestToZero(int[] arr) {
		final int n = arr.length;
		final var result = new int[2];
		var i = 0;
		var j = n - 1;
		var x = 0;
		var y = 0;
		var minSum = Integer.MAX_VALUE;
		Arrays.sort(arr);
		while (i < j) {
			final var sum = arr[i] + arr[j];
			if (Math.abs(sum) < minSum) {
				x = i;
				y = j;
				minSum = Math.abs(sum);
			}
			if (sum > 0) {
				j--;
			} else if (sum < 0) {
				i++;
			} else {
				break;
			}
		}
		result[0] = arr[x];
		result[1] = arr[y];
		return result;
	}

	// 19. Find 3 numbers whose sum is equal to k
	// Time - O(n^2), space - O(n)
	public int[] findTripletSumToK(int[] arr, int k) {
		final int n = arr.length;
		final int[] result = new int[3];
		final Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(k - arr[i], i);
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (map.containsKey(arr[i] + arr[j])) {
					result[0] = i;
					result[1] = j;
					result[2] = map.get(arr[i] + arr[j]);
					return result;
				}
			}
		}
		return result;
	}

	// 20. Find sum of three numbers closest to zero
	// Time - O(n^2), Space - O(1)
	public int[] findSumOfThreeNumbersClosestToZero(int[] arr) {
		final int n = arr.length;
		final int[] result = new int[3];
		Arrays.sort(arr);
		var i = 0;
		var j = 0;
		var x = 0;
		var y = 0;
		var z = 0;
		var minSum = Integer.MAX_VALUE;
		for (var k = n - 1; k >= 0; k--) {
			i = 0;
			j = k - 1;
			while (i < j) {
				final var sum = arr[i] + arr[j] + arr[k];
				if (Math.abs(sum) < minSum) {
					x = i;
					y = j;
					z = k;
					minSum = Math.abs(sum);
				}
				if (sum > 0) {
					j--;
				} else if (sum < 0) {
					i++;
				} else {
					break;
				}
			}
		}
		result[0] = arr[x];
		result[1] = arr[y];
		result[2] = arr[z];
		return result;
	}

	// 21. Find mountain number: Find an index k such that arr[i] < arr[k] &&
	// arr[k]>arr[j] for all i<=k and k<=j
	public int findMountainNumber(int[] arr) {
		final int n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
				return arr[mid];
			}
			if (arr[mid - 1] < arr[mid]) {
				lo = mid + 1;
			} else if (arr[mid - 1] > arr[mid]) {
				hi = mid - 1;
			}
		}
		return -1;
	}

	// 22. Find pivot in a rotated sorted array
	// Time - O(logn), Space - O(1)
	public int findPivotInRotatedSortedArray(int[] arr) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		if (arr[lo] < arr[hi]) {
			return lo;
		}
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (mid < n - 1 && arr[mid] > arr[mid + 1]) {
				return mid + 1;
			}
			if (arr[mid] >= arr[lo]) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return lo;
	}

	// 23. Find an element in rotated sorted array
	// Time - O(logn), Space - O(1)
	public int findInRotatedSortedArray(int[] arr, int k) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (arr[mid] < k) {
				if (arr[hi] < k) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else if (arr[mid] > k) {
				if (arr[lo] <= k) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else {
				return mid;
			}
		}
		return -1;
	}

	// 24. Given a sorted array, find the index of first occurance of the
	// element
	// Time - O(logn), Space - O(1)
	public int findFirstOccuranceInSortedArray(int[] arr, int k) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if ((mid == lo && arr[mid] == k) || (arr[mid] == k && arr[mid - 1] < k)) {
				return mid;
			}
			if (arr[mid] < k) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return -1;
	}

	// 25. Given a sorted array, find the index of last occurance of the
	// element
	// Time - O(logn), Space - O(1)
	public int findLastOccuranceInSortedArray(int[] arr, int k) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if ((mid == hi && arr[mid] == k) || (arr[mid] == k && arr[mid + 1] > k)) {
				return mid;
			}
			if (arr[mid] <= k) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return -1;
	}

	// 26. Find number of occurances of a number in a sorted array
	// Time - O(logn), Space - O(1)
	public int countFrequencyInSortedArray(int[] arr, int k) {
		final var firstOccurance = findFirstOccuranceInSortedArray(arr, k);
		final var lastOccurance = findLastOccuranceInSortedArray(arr, k);
		return lastOccurance - firstOccurance + 1;
	}

	// 27. Find the majority number: number occuring more than n/2 times in the
	// array
	// Time - O(n), Space - O(1)
	public int findMajorityNumber(int[] arr) {
		final var n = arr.length;
		var count = 0;
		var majorityNumber = Integer.MIN_VALUE;
		for (var i = 0; i < n; i++) {
			if (count == 0) {
				majorityNumber = arr[i];
				count = 1;
			} else if (arr[i] == majorityNumber) {
				count++;
			} else {
				count--;
			}
		}
		count = 0;
		for (var i = 0; i < n; i++) {
			if (arr[i] == majorityNumber) {
				count++;
			}
		}
		return count > n / 2 ? majorityNumber : -1;
	}

	// 28. Find local minima
	// Time - O(logn), Space - O(1)
	public int findLocalMinima(int[] arr) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
				return arr[mid];
			}
			// search for local minima in the half having the smaller element
			// compared to arr[mid]
			if (arr[mid - 1] < arr[mid]) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return -1;
	}

	// 29. Find local minima
	// Time - O(logn), Space - O(1)
	public int findLocalMaxima(int[] arr) {
		final var n = arr.length;
		var lo = 0;
		var hi = n - 1;
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
				return arr[mid];
			}
			// search for local minima in the half having the smaller element
			// compared to arr[mid]
			if (arr[mid - 1] > arr[mid]) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return -1;
	}

	// 30. Given a NxM matrix with elements in non-decreasing order in each row
	// and column, find an element in O(n) time
	// Time - O(n), Space - O(1)
	public int[] findElementInSortedMatrix(int[][] arr, int k) {
		final var n = arr.length;
		final var m = arr[0].length;
		final var result = new int[2];
		result[0] = -1;
		result[1] = -1;
		if (k > arr[n - 1][m - 1]) {
			return result;
		}
		var row = 0;
		var column = m - 1;
		while (row < n && column >= 0) {
			if (arr[row][column] == k) {
				result[0] = row;
				result[1] = column;
				return result;
			}
			if (arr[row][column] < k) {
				row++;
			} else {
				column--;
			}
		}
		return result;
	}

	// 31. Given a NxM matrix with elements in non-decreasing order in each row
	// and column, find an element in O(logn+logm) time
	// Time - O(logn+logm), Space - O(1)
	public int[] findElementInSortedMatrixEfficient(int[][] arr, int k) {
		final var n = arr.length;
		final var m = arr[0].length;
		final var result = new int[2];
		result[0] = -1;
		result[1] = -1;
		if (k > arr[n - 1][m - 1]) {
			return result;
		}
		findPositionBinarySearch(arr, 0, n - 1, m, k, result);
		return result;
	}

	private void findPositionBinarySearch(int[][] arr, int lo, int hi, int m, int k, int[] result) {
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (arr[mid][0] <= k && arr[mid][m - 1] >= k) {
				findColumnBinarySearch(arr, mid, 0, m - 1, k, result);
				return;
			}
			if (arr[mid][0] > k) {
				hi = mid - 1;
			} else if (arr[mid][0] < k) {
				lo = mid + 1;
			}
		}
	}

	private void findColumnBinarySearch(int[][] arr, int row, int lo, int hi, int k, int[] result) {
		while (lo <= hi) {
			final var mid = lo + (hi - lo) / 2;
			if (arr[row][mid] == k) {
				result[0] = row;
				result[1] = mid;
				return;
			}
			if (arr[row][mid] > k) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
	}

	// 32. In a matrix there are all 1s followed by 0s in a row. Find the row
	// having max number of 0s
	// Time - O(n+m), Space - O(1)
	public int[] findRowWithMaxZeros(int[][] arr) {
		final var n = arr.length;
		final var m = arr[0].length;
		final var result = new int[2];
		var maxRowIndex = Integer.MIN_VALUE;
		var column = m - 1;
		for (var row = 0; row < n; row++) {
			while (column >= 0 && arr[row][column] == 0) {
				column--;
				maxRowIndex = row;
			}
		}
		result[0] = maxRowIndex;
		result[1] = m - column - 1;
		return result;
	}

	// 33. Separate even and odd numbers: {12,34,45,9,8,90,3} ->
	// {12,34,90,8,9,45,3}
	// 1) Initialize two index variables left and right
	// 2) Keep incrementing left index until we see an odd number.
	// 3) Keep decrementing right index until we see an even number.
	// 4) If left < right then swap arr[left] and arr[right]
	// Time - O(n), Space - O(1)
	public void separateEvenAndOddNumbers(int[] arr) {
		final var n = arr.length;
		var i = 0;
		var j = n - 1;
		while (i < j) {
			while (arr[i] % 2 == 0) {
				i++;
			}
			while (arr[j] % 2 != 0) {
				j--;
			}
			if (i > j) {
				break;
			}
			final var temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	// 34. Move all zeros to last
	// Time - O(n), Space - O(1)
	public void moveZerosToLast(int[] arr) {
		final var n = arr.length;
		var i = 0;
		var j = n - 1;
		while (i < j) {
			while (arr[i] == 1) {
				i++;
			}
			while (arr[j] == 0) {
				j--;
			}
			if (i > j) {
				break;
			}
			swap(arr, i, j);
		}
	}

	// 35. Sort an array with only 0s, 1s and 2s in random order using a single
	// array pass
	// Time - O(n), Space - O(1)
	public void sortArrayWith012s(int[] arr) {
		final var n = arr.length;
		var lo = 0;
		var mid = 0;
		var hi = n - 1;
		while (mid < hi) {
			switch (arr[mid]) {
			case 0:
				swap(arr, lo, mid);
				lo++;
				mid++;
				break;
			case 1:
				mid++;
				break;
			case 2:
				swap(arr, mid, hi);
				hi--;
				break;
			default:
				break;
			}
		}
	}

	public void swap(int[] arr, int i, int j) {
		final var temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// 36. Max difference between 2 elements with the condition that the larger
	// element appears after the smaller element
	// Divide and conquer

	// 37. Number of trailing 0s in n!
	// 0! -> 1, 5! ->120, 10! -> ..00, 15! -> ..000
	public int findTrailingZeroCountInNFactorial(int n) {
		if (n < 5) {
			return 0;
		}
		int count = 0;
		while (n >= 1) {
			count += n / 5;
			n /= 5;
		}
		return count;
	}

	// 38. Transform a1a2a3a4..b1b2b3b4 into a1b1a2b2a3b3a4b4..
	// Divide and conquer -> a1a2a3a4:b1b2b3b4 -> swap elements at the center
	// a1a2b1b2:a3a4b3b4 -> a1a2:b1b2 -> a1b1:a2b2

	// 39. Find pivot element -> sum of numbers to the left of this index is
	// equal to the sum of numbers to the right
	// Time - O(n), Space - O(1)
	public int findPivotIndex(int[] arr) {
		final var n = arr.length;
		var sum = 0;
		for (final var element : arr) {
			sum += element;
		}
		var leftSum = 0;
		for (var i = 0; i < n; i++) {
			if (2 * leftSum == sum - arr[i]) {
				return i;
			}
			leftSum += arr[i];
		}
		return -1;
	}

	// 40. Given an array, find the maximum j - i such that arr[j] > arr[i]. For
	// ex: {34,8,10,3,2,80,30,33,1} -> should output 6 (j=7, i=1)

}

class SearchingUtils {
	public static int[] createArray() {
		final int[] arr = { 3, 7, 4, 6, 5, 2, 5, 3, 10 };
		return arr;
	}

	public static int[] createSortedArray() {
		final int[] arr = { 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		return arr;
	}

	public static int[] createArrayWithDuplicates() {
		final int[] arr = { 1, 4, 5, 6, 7, 8, 9, 10, 10 };
		return arr;
	}

	public static int[] createArrayWithDuplicatesNRange() {
		final int[] arr = { 1, 4, 5, 6, 7, 8, 8, 2, 3 };
		return arr;
	}

	public static int[] createArrayForFirstRepeatedElement() {
		final int[] arr = { 3, 2, 1, 2, 2, 3 };
		return arr;
	}

	public static int[] createArrayForMissingElementNRange() {
		final int[] arr = { 1, 2, 3, 4, 5, 7, 8 };
		return arr;
	}

	public static int[] createArrayWithOddFrequencyElement() {
		final int[] arr = { 2, 2, 1, 4, 1, 3, 4, 4, 3 };
		return arr;
	}

	public static int[] createArrayWithTwoRepeatingElements() {
		final int[] arr = { 4, 2, 4, 5, 2, 3, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		return arr;
	}

	public static int[] createArrayWithNegativeNumbers() {
		final int[] arr = { -6, -3, 2, 4, 5 };
		return arr;
	}

	public static int[] createArrayWithMountainNumber() {
		final int[] arr = { 1, 2, 7, 6, 5, 4, 2, 2, 1 };
		return arr;
	}

	public static int[] createRotatedSortedArray() {
		final int[] arr = { 32, 33, 34, 44, 56, 57, 58, 1, 3, 5, 6, 7 };
		return arr;
	}

	public static int[][] createSortedMatrix() {
		final int[][] mat = { { 4, 6, 8 }, { 5, 7, 9 }, { 6, 11, 12 }, { 10, 12, 13 } };
		return mat;
	}

	public static int[][] createMatrixWithOnesAndZeros() {
		final int[][] mat = { { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 } };
		return mat;
	}

	public static int[] createArrayWithOnesAndZeros() {
		final int[] arr = { 1, 1, 0, 1, 1, 0, 0, 0, 1 };
		return arr;
	}

	public static int[] createArrayWithZeroOneTwos() {
		final int[] arr = { 1, 0, 2, 1, 0, 2, 2, 1, 0, 2, 0 };
		return arr;
	}
}
