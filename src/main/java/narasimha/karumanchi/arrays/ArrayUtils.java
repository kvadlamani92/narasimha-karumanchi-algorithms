package narasimha.karumanchi.arrays;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
	public static List<Pair<Integer, Integer>> getListOfPairs() {
		final Pair<Integer, Integer> a = new Pair(2, 6);
		final Pair<Integer, Integer> b = new Pair(3, 7);
		final Pair<Integer, Integer> c = new Pair(4, 5);
		final Pair<Integer, Integer> d = new Pair(7, 3);
		final List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		pairs.add(a);
		pairs.add(b);
		pairs.add(c);
		pairs.add(d);
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