package com.phonepe.com;

import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		final Solution sol = new Solution();
		final int[] breads = { 2, 3 };
		final int[] sauces = { 4, 4, 10 };
		final int noOfVeggies = 5;
		System.out.println(sol.findOptimalBudget(breads, sauces, noOfVeggies, 18));
	}

	/*
	 * Input: breadPrices = [1,7], noVeggies = 5, saucePrices = [3,4], budget =
	 * 10 Output: 10 Explanation: Consider the following combination (all
	 * 0-indexed): - Choose bread 1: cost 7 - Take 1 of sauce 0: cost 1 x 3 = 3
	 * - Take 0 of sauce 1: cost 0 x 4 = 0 Total: 7 + 3 + 0 = 10.
	 * 
	 * Example 2: Input: breadPrices = [2,3], noVeggies = 3, saucePrices =
	 * [4,4,10], budget = 18 Output: 17
	 */
	public int findOptimalBudget(int[] breads, int[] sauces, int noOfVeggies, int budget) {
		final int n = breads.length;
		final int m = sauces.length;
		Arrays.sort(breads);
		Arrays.sort(sauces);
		int i = 0;
		int j = m - 1;
		int minDifference = Integer.MAX_VALUE;
		int totalValueFound = 0;
		while (i >= 0 && j >= 0 && i < n && j < m) {
			final int val = totalValueFound + breads[i] + sauces[j];
			final int doubleVal = val + sauces[j];
			if (val == budget || doubleVal == budget) {
				return budget;
			}
			if (val < budget) {
				if (doubleVal == budget) {
					return budget;
				} else {
					totalValueFound = Math.abs(budget - val) < Math.abs(budget - doubleVal) ? val : doubleVal;
					i++;
				}

			} else {
				totalValueFound = val;
				j--;
			}
			minDifference = Math.min(minDifference, Math.abs(budget - totalValueFound));
		}

		if (i > n - 1) {
			totalValueFound = Math.abs(budget - sauces[j]) < Math.abs(budget - 2 * sauces[j]) ? sauces[j]
					: 2 * sauces[j];
		} else if (j > m - 1) {
			totalValueFound = breads[i];
		}
		minDifference = Math.min(minDifference, Math.abs(budget - totalValueFound));
		System.out.println("i=" + i + " j=" + j + " totalValueFound=" + totalValueFound);

		return minDifference != 0 ? Math.abs(budget - minDifference) : totalValueFound;
	}
}
