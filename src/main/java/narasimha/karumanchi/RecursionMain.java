package narasimha.karumanchi;

public class RecursionMain {
	public static void main(String[] args) {
		final var recursionMain = new RecursionMain();
		recursionMain.runIsArraySorted();
	}

	// ........Runners...........
	// 1. Runner for isArraySorted()
	private void runIsArraySorted() {
		final int[] arr = { 1, 2, 3, 4, 5 }; // NOSONAR
		System.out.println(isArraySorted(arr)); // NOSONAR
	}

	// ........Algorithms..........
	// 1. Check if array is sorted using recursion
	private boolean isArraySorted(int[] arr) {
		final int n = arr.length;
		return isArraySorted(arr, 0, n - 1);
	}

	private boolean isArraySorted(int[] arr, int lo, int hi) {
		if (lo == hi) {
			return true;
		}
		return arr[lo] <= arr[lo + 1] && isArraySorted(arr, lo + 1, hi);
	}
}
