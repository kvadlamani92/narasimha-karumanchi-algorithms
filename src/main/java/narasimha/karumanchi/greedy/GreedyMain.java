package narasimha.karumanchi.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import narasimha.karumanchi.arrays.Pair;

public class GreedyMain {
	public static void main(String[] args) {
		final GreedyMain greedyMain = new GreedyMain();
		// greedyMain.runNonOverlappingIntervals();
		// greedyMain.runMaxIntersectingPairs();
		greedyMain.runFindNonIntersectingPairIndexSet();
	}

	// Runners
	// 1. Run Find non overlapping intervals
	public void runNonOverlappingIntervals() {
		final var intervals = GreedyUtils.createIntervals();
		final var nonOverlappingIntervals = findNonOverlappingIntervals(intervals);
		System.out.println(nonOverlappingIntervals);
	}

	// 2. Max intersecting pairs
	public void runMaxIntersectingPairs() {
		final var intervals = GreedyUtils.createIntervals();
		final var maxCount = maxIntersectingPairs(intervals);
		System.out.println(maxCount);
	}

	// 3. Find non intersecting pair index set
	public void runFindNonIntersectingPairIndexSet() {
		final Integer[] start = { 1, 5, 3 };
		final Integer[] end = { 2, 8, 6 };
		final var set = findNonIntersectingPairIndexSet(start, end);
		System.out.println(set);
	}

	// Algorithms
	// 1. Huffman encoding

	// 2. Interval scheduling
	// Time - O(n), Space - O(1)
	public List<Pair<Integer, Integer>> findNonOverlappingIntervals(List<Pair<Integer, Integer>> intervals) {
		final var n = intervals.size();
		final List<Pair<Integer, Integer>> result = new ArrayList<>();
		// sort by end times
		Collections.sort(intervals, new Comparator<>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				final int y1 = (int) o1.getY();
				final int y2 = (int) o2.getY();
				return (y1 - y2);
			}
		});
		System.out.println(intervals);
		// first interval will always be included in the result
		result.add(intervals.get(0));
		// for every pair from 2nd pair, check if the previous pair end time <
		// current pair start time
		for (var i = 1; i < n; i++) {
			final Pair<Integer, Integer> previousPair = intervals.get(i - 1);
			final Pair<Integer, Integer> currentPair = intervals.get(i);
			if (previousPair.getY() < currentPair.getX()) {
				result.add(currentPair);
			}
		}
		return result;
	}

	// 3. Max number of classes required for (x1,y1), (x2,y2),..
	public int maxIntersectingPairs(List<Pair<Integer, Integer>> pairs) {
		final var n = pairs.size();
		final List<ValueTag> valueTagList = new ArrayList<>();
		for (final Pair<Integer, Integer> pair : pairs) {
			valueTagList.add(new ValueTag(pair.getX(), 'x'));
			valueTagList.add(new ValueTag(pair.getY(), 'y'));
		}
		Collections.sort(valueTagList, new Comparator<ValueTag>() {
			@Override
			// compare based on x and y co-ordinate values
			public int compare(ValueTag o1, ValueTag o2) {
				return o1.getValue() - o2.getValue();
			}
		});
		var count = 0;
		var maxCount = Integer.MIN_VALUE;
		for (final ValueTag valueTag : valueTagList) {
			if (valueTag.getTag() == 'x') {
				count++;
			} else if (valueTag.getTag() == 'y') {
				count--;
			}
			maxCount = Math.max(maxCount, count);
		}
		return maxCount;
	}

	// 4. Given start[] and end[] arrays with start and end times, find the set
	// of i,j such that start[i] > end[j] or start[j] > end[i]
	// Time - O(nlogn), Space - O(n)
	public Set<Integer> findNonIntersectingPairIndexSet(Integer[] start, Integer[] end) {
		final Set<Integer> result = new HashSet<>();
		if (start.length != end.length) {
			return result;
		}
		final int n = start.length; // end.length
		sortTwoArraysBasedOnOne(start, end);
		var index = 0;
		result.add(index);
		for (var i = 1; i < n; i++) {
			if (start[i] > end[index]) {
				index++;
				result.add(index);
			}
		}
		return result;
	}

	private void sortTwoArraysBasedOnOne(Integer[] a, Integer[] b) {
		final var n = a.length; // b.length
		final var indexArray = new Integer[n];
		for (var i = 0; i < indexArray.length; i++) {
			indexArray[i] = i;
		}
		System.out.println("indexArray:" + Arrays.toString(indexArray));
		// sort index array based on the indexes in b after it's sorted
		Arrays.sort(indexArray, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return b[o1] < b[o2] ? -1 : (b[o1] > b[o2] ? 1 : 0);
			}
		});
		System.out.println("indexArray:" + Arrays.toString(indexArray));
		Arrays.sort(b);
		System.out.println("b:" + Arrays.toString(b));
		System.out.println("a:" + Arrays.toString(a));
		// sort array A based on the index array (which inturn contains the
		// index order after b is sorted). So A and B would both be sorted in
		// the same way and would be in sync
		Arrays.sort(a, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		System.out.println("a:" + Arrays.toString(a));
	}
}

class ValueTag {
	int value;
	char tag;

	public ValueTag(int value, char tag) {
		super();
		this.value = value;
		this.tag = tag;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public char getTag() {
		return tag;
	}

	public void setTag(char tag) {
		this.tag = tag;
	}
}

class GreedyUtils {
	public static List<Pair<Integer, Integer>> createIntervals() {
		final List<Pair<Integer, Integer>> intervals = new ArrayList<>();
		intervals.add(new Pair<>(1, 2));
		intervals.add(new Pair<>(1, 3));
		intervals.add(new Pair<>(7, 8));
		intervals.add(new Pair<>(9, 11));
		intervals.add(new Pair<>(8, 15));
		// intervals.add(new Pair<>(13, 14));
		return intervals;
	}
}
