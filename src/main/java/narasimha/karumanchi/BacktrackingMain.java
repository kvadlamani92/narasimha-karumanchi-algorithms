package narasimha.karumanchi;

import java.util.Arrays;

public class BacktrackingMain {
	public static void main(String[] args) {
		final var backtrackingMain = new BacktrackingMain();
		backtrackingMain.runGenerateNBitBinaryStrings();
		// backtrackingMain.runGenerateKAryStrings();
	}

	// ....Runners....
	// 1.
	private void runGenerateNBitBinaryStrings() {
		generateNBitBinaryStrings(3);
	}

	// 2.
	private void runGenerateKAryStrings() {
		generateKAryStrings(3, 3);
	}

	// ....Algorithms....
	// 1. Generate all N bit binary strings
	private void generateNBitBinaryStrings(int n) {
		final var arr = new int[n];
		generateNBitBinaryStrings(arr, n);
	}

	private void generateNBitBinaryStrings(int[] arr, int n) {
		if (n == 0) {
			System.out.println(Arrays.toString(arr)); // NOSONAR
		} else {
			arr[n - 1] = 0;
			generateNBitBinaryStrings(arr, n - 1);
			arr[n - 1] = 1;
			generateNBitBinaryStrings(arr, n - 1);
		}
	}
	// .....................................................

	// 2. Generate all the strings of length n drawn from 0..k-1
	private void generateKAryStrings(int n, int k) {
		final var arr = new int[n];
		generateKAryStrings(arr, n, k);
	}

	private void generateKAryStrings(int[] arr, int n, int k) {
		if (n == 0) {
			System.out.println(Arrays.toString(arr)); // NOSONAR
		} else {
			for (var i = 0; i < k; i++) {
				arr[n - 1] = i;
				generateKAryStrings(arr, n - 1, k);
			}
		}
	}
	// .......................................................

}
