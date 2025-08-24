package com.medianet.com;

public class Solution3 {
	public static void main(String[] args) {
		final Solution3 sol = new Solution3();
		// final String str = "1110000011100111100000000000000";
		final String str = "111100100010000000000";
		// final String str = "000";
		System.out.println(sol.maxInvertedValue(str));
	}

	private int maxInvertedValue(String str) {
		final int n = str.length();
		int leftOnes = 0;
		int currentZeros = 0;
		int rightOnes = 0;
		int maxOnes = 0;
		if (n <= 2) {
			return n;
		}
		int i = 0;
		final char[] charArray = str.toCharArray();
		while (i < n) {
			while (i < n && charArray[i] == '1') {
				leftOnes++;
				i++;
			}
			while (i < n && charArray[i] == '0') {
				currentZeros++;
				i++;
			}
			while (i < n && charArray[i] == '1') {
				rightOnes++;
				i++;
			}
			final int sum = leftOnes + currentZeros + rightOnes;
			maxOnes = Math.max(maxOnes, sum);
			if (i > n) {
				return maxOnes;
			}
			leftOnes = rightOnes;
			currentZeros = 0;
			rightOnes = 0;
		}
		return maxOnes;
	}
}
