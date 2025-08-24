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
		// hashingMain.runFindSymmetricalPairs();

		hashingMain.runFindPageEnding();
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

	public void runFindPageEnding() {
		final int[] endings = { 6, 15, 21, 30 };
		final int[][] choices1 = { { 3, 14, 2 } };
		final int[][] choices2 = { { 5, 11, 28 }, { 9, 19, 29 }, { 14, 16, 20 }, { 18, 7, 22 }, { 25, 6, 30 } };
		final int[][] choices3 = {};
		final int[][] choices4 = { { 2, 10, 15 }, { 3, 4, 10 }, { 4, 3, 15 }, { 10, 3, 15 } };
		System.out.println(findEnding(endings, choices1, 1));
		System.out.println(findEnding(endings, choices1, 2));
		System.out.println(findEnding(endings, choices2, 1));
		System.out.println(findEnding(endings, choices2, 2));
		System.out.println(findEnding(endings, choices3, 1));
		System.out.println(findEnding(endings, choices3, 2));
		System.out.println(findEnding(endings, choices4, 1));
		System.out.println(findEnding(endings, choices4, 2));
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

	// 3. Find Page ending (Indeed Interview 14/9/2022)
	/*
	 * You are reading a Build Your Own Story book. It is like a normal book
	 * except that choices on some pages affect the story, sending you to one of
	 * two options for your next page.
	 * 
	 * These choices are really stressing you out, so you decide that you'll
	 * either always pick the first option, or always pick the second option.
	 * 
	 * You start reading at page 1 and read forward one page at a time unless
	 * you reach a choice or an ending.
	 * 
	 * The choices are provided in a list, sorted by the page containing the
	 * choice, and each choice has two options of pages to go to next. In this
	 * example, you are on page 3, where there is a choice. Option 1 goes to
	 * page 14 and Option 2 goes to page 2.
	 * 
	 * choices1 = [[3, 14, 2]] => [current_page, option_1, option_2] The ending
	 * pages are also given in a sorted list:
	 * 
	 * endings = [6, 15, 21, 30]
	 * 
	 * Given a list of endings, a list of choices with their options, and the
	 * choice you will always take (Option 1 or Option 2), return the ending you
	 * will reach. If you get stuck in a loop, return -1.
	 * 
	 * Example: find_ending(endings, choices1, 1) (always Option 1) Start: 1 ->
	 * 2 -> 3(choice) -> 14 -> 15(end) => Return 15
	 * 
	 * find_ending(endings, choices1, 2) (always Option 2) Start: 1 -> 2 ->
	 * 3(choice) -> 2 -> 3(choice) -> 2... => Return -1
	 * 
	 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21
	 * 
	 * Additional inputs: choices2 = [[5, 11, 28], [9, 19, 29], [14, 16, 20],
	 * [18, 7, 22], [25, 6, 30]] choices3 = [] choices4 = [[2, 10, 15], [3, 4,
	 * 10], [4, 3, 15], [10, 3, 15]]
	 * 
	 * Complexity Variable: n = number of pages (endings and choices are bound
	 * by the number of pages)
	 * 
	 * All Test Cases - snake_case: find_ending(endings, choices1, 1) => 15
	 * find_ending(endings, choices1, 2) => -1 find_ending(endings, choices2, 1)
	 * => 21 find_ending(endings, choices2, 2) => 30 find_ending(endings,
	 * choices3, 1) => 6 find_ending(endings, choices3, 2) => 6
	 * find_ending(endings, choices4, 1) => -1 find_ending(endings, choices4, 2)
	 * => 15
	 * 
	 * All Test Cases - camelCase: findEnding(endings, choices1, 1) => 15
	 * findEnding(endings, choices1, 2) => -1 findEnding(endings, choices2, 1)
	 * => 21 findEnding(endings, choices2, 2) => 30 findEnding(endings,
	 * choices3, 1) => 6 findEnding(endings, choices3, 2) => 6
	 * findEnding(endings, choices4, 1) => -1 findEnding(endings, choices4, 2)
	 * => 15
	 */
	/*
	 * int[][] mat 5 11 28 9 19 29 14 16 20 18 7 22 25 6 30
	 */
	public int findEnding(int[] endings, int[][] choices, int choiceNumber) {
		final var n = choices.length;
		if (n == 0) {
			return endings[0];
		}
		final var endLength = endings.length;
		final Map<Integer, Integer> choiceMap = new HashMap<>();
		final Map<Integer, Boolean> endingMap = new HashMap<>();
		for (final int[] row : choices) {
			if (choiceNumber == 1) {
				choiceMap.put(row[0], row[1]);
			} else if (choiceNumber == 2) {
				choiceMap.put(row[0], row[2]);
			}
		}
		for (final var ending : endings) {
			endingMap.put(ending, true);
		}
		var pageNumber = 1;
		final Set<Integer> visitedSet = new HashSet<>();
		while (true) {
			while (!choiceMap.containsKey(pageNumber) && !endingMap.containsKey(pageNumber)) {
				pageNumber++;
			}
			if (endingMap.containsKey(pageNumber)) {
				return pageNumber;
			}
			if (choiceMap.containsKey(pageNumber)) {
				pageNumber = choiceMap.get(pageNumber);
				if (visitedSet.contains(pageNumber)) {
					return -1;
				}
				visitedSet.add(pageNumber);
			}
			if (pageNumber > endings[endLength - 1]) {
				break;
			}
		}
		return -1;
	}
}
