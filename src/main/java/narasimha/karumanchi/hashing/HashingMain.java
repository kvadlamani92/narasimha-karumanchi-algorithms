package narasimha.karumanchi.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import narasimha.karumanchi.arrays.ArrayUtils;
import narasimha.karumanchi.arrays.Pair;

/*
 * https://stackoverflow.com/questions/4282375/what-is-the-advantage-to-using-bloom-filters
 * */
public class HashingMain {

	public static void main(String[] args) {
		final var hashingMain = new HashingMain();
		// hashingMain.runRemoveDuplicates();
		hashingMain.runFindSymmetricalPairs();
	}

	// Runners
	// 1. Remove duplicates
	public void runRemoveDuplicates() {
		final int[] arr = { 1, 2, 3, 3, 2, 5, 1 };
		System.out.println(Arrays.toString(removeDuplicates(arr)));
	}

	public void runFindSymmetricalPairs() {
		final List<Pair<Integer, Integer>> pairs = ArrayUtils.getListOfPairs();
		System.out.println(findSymmetricalPairs(pairs));
	}

	// Algorithms
	// 1. Remove duplicates from unsorted array
	// Time - O(n), Space - O(1)
	public int[] removeDuplicates(int[] arr) {
		final int n = arr.length;
		final Set<Integer> set = new HashSet<>();
		var j = 0;
		for (var i = 0; i < n; i++) {
			if (!set.contains(arr[i])) {
				arr[j++] = arr[i];
				set.add(arr[i]);
			}
		}
		// Reset the rest of the array
		while (j < n) {
			arr[j++] = -1;
		}
		return arr;
	}

	// 2. Find if 2 pairs having (x1,y1) and (y1,x1) exist in a list of pairs
	public List<Pair> findSymmetricalPairs(List<Pair<Integer, Integer>> pairs) {
		final List<Pair> result = new ArrayList<>();
		final Map<Integer, Integer> map = new HashMap<>();
		for (final Pair<Integer, Integer> pair : pairs) {
			if (map.containsKey(pair.getY()) && map.get(pair.getY()) == pair.getX()) {
				result.add(pair);
				result.add(new Pair(pair.getY(), pair.getX()));
				return result;
			}
			map.put(pair.getX(), pair.getY());
		}
		return result;
	}
}
