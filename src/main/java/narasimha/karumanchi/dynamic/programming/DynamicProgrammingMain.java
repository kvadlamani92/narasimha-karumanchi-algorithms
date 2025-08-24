package narasimha.karumanchi.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* 	MIT opencourseware - Prof.Erik Demaine
 *  Lecture Notes: https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-fall-2011/pages/lecture-notes/
 * 	Lecture #19 - https://www.youtube.com/watch?v=OQ5jsbhAv_M&t=365s
 *  
 * 	Dynamic programming has 2 variants
 * 	1. Top Down Approach - Memoization (came from MEMO (dictionary)) - Remember the answer to the subproblems
 * 	2. Bottom up Approach - It's actually a topological sort of sub-problem dependency DAG  
 * 
 * 	Lecture #20 - https://www.youtube.com/watch?v=ENyox7kNKeY
 * 
 *  DP ~ Careful brute-force
 *  DP ~ guessing + recursion + memoization
 *  DP ~ shortest paths in some DAG (Wrong! It's not. You can see it in Parenthesization (Best way to parenthesize matrices to have min cost))
 *  Time taken for DP algorithm = # of subproblems * (time / subproblem (treating recursive calls as O(1) -
 *  because with memoization we only call the subproblem only once and the next time, it's fetched from the memopad (dictionary))
 * 
 *  5 "Easy" steps to DP:
 *  
 *  1. Define subproblem -> And for analysis, we need # of subproblems
 *  2. Guess (part of solution) -> For analysis, we need # of different choices for the guess --|
 * 																				 			    |-- Usually will be the same 
 *  3. Relate subproblem solutions -> For analysis, we get time/subproblem ---------------------|  
 *  4. Build the algorithm - (Recurse & Memoize) or (Build DP table bottom up) -> For analysis, we need to check if the subproblem recurrence is acyclic 
 *  																			  i.e has topological order and compute the total time which is #1*#3 above 
 *  5. Solve the original problem -> Make sure the answer for the original problem gets solved from the above steps and find the answer. 
 *  								 Sometimes need extra time for this step
 *  1. **Shortest path:**
 *  δk(s, v) = shortest s → v path using ≤ k edges
 *  δk(s, v) = min{δk−1(s, u) + w(u, v) | (u, v) ∈ E}
 *  δ0(s, v) = ∞ for s = v (base case)
 *  δk(s, s) = 0 for any k (base case, if no negative cycles)
 *  
 *  2. **Text Justification:**
 *  Let's say badness(i, j) for line of words[i : j] = if total length > page width ? infinity, else (page width − total length)^3 
 *  (to make it very bad if the page width and total length are not almost the same)
 *  *Goal*: split words into lines to minimize {sum of badness} 
 *  1. subproblem => min. badness for suffix words[i :] . Let's call it DP[i] => # subproblems = Θ(n) where n = # words
 *  2. guessing => where to end first line, say i => # choices = n − i = O(n)
 *  3. recurrence => DP[i] = min(badness (i, j) + DP[j] for j in range (i + 1, n + 1)) and base case => DP[n] = 0 => Time per subproblem = O(n)
	*  4. Order => for i = n, n-1, n-2,..0 (build it from the end till the beginning to get DP[0])
 *  5. solution => DP[0]
 *  
 *  **Parent Pointers:**
 *  To construct the actual solution (which j gave the min badness), use Parent Pointer Technique
 *  To recover actual solution in addition to cost, store parent pointers (which guess used at each subproblem) & walk back
 *  typically: remember argmin/argmax (what was the value of j which gave min/max value for dp[i]) in addition to min/max
 *  ex: parent[i] = argmin(badness (i, j) + DP[j] for j in range (i + 1, n + 1)) = j value which gave min
 *  To construct the values that gave min badness => Start from the original problem 
 *  => Start with 0 -> Go to parent[0] (that's where the 2nd line begins) -> parent[parent[0]] (that's where the 3rd line begins) ->...till you reach n
 *  
 *  3. **Perfect information Blackjack:**
 *  1. subproblems: BJ(i) = best play of ci, . . . cn−1 (remaining cards)(where i is # cards “already played”) => # subproblems = n 
 *  2. guess: how many times player “hits” (hit means draw another card) => # choices ≤ n
 *  3. recurrence: BJ(i) = max(outcome ∈ {+1, 0, −1} + BJ(i + # cards used) for # hits in 0, 1, . . . if valid play ∼ don’t hit after bust => O(n)
 *  => time/subproblem = Θ(n^2)
 *  4. order: for i in reversed(range(n)) => total time = Θ(n^3)
 *  5. solution: BJ(0)
 *  BJ(i):
		if n − i < 4: return 0 (not enough cards)
		for p in range(2, n − i − 1): (# cards taken)
		 player = sum(ci, ci+2, ci+4:i+p+2)
		 if player > 21:
		 	options.append(−1(bust) + BJ(i + p + 2))
		 break
		 for d in range(2, n − i − p)
			dealer = sum(ci+1, ci+3, ci+p+2:i+p+d)
			if dealer ≥ 17: break
		if dealer > 21: dealer = 0 (bust)
		options.append(cmp(player, dealer) + BJ(i + p + d))
	return max(options)
 *
 * **Tips for choosing subproblems for Strings/Sequences**
 *  1. Suffixes - X[i:] for all 0<=i<=n - O(n) suffixes
 *  2. Prefixes - X[:i] for all 0<=i<=n - O(n) prefixes
 *  3. Substrings - X[i:j] for all i<=j and 0<=i,j<=n - O(n^2) substrings
 *
 * 4. **Parenthesization**
 * Optimal evaluation of associative expression A[0] · A[1] · · · A[n − 1] —> e.g., multiplying rectangular matrices
 * 2. guessing => outermost multiplication (or last multiplication) => choices O(n)
 * 1. subproblems => prefixes & suffixes? NO => Cost of substring A[i..j] => # of subproblems = O(n^2)
 * 3. recurrence => DP[i,j] = min{DP[i,k-1] + DP[k,j] + cost of multiplying (A[i]..A[k-1]) by (A[k]..A[j]) for k in range (i+1,j)}, base case DP[i,i+1] = 0
 * => cost per subproblem = O(j-i) = O(n)
 * 4. Topological order => Increasing substring size = Total time = O(n^3)
 * 5. Original problem => DP[0,n-1] (& use parent pointers to recover the actual k values which lead to min cost) 
 * 
 * 5. **Edit Distance**
 * Given two strings x & y, what is the cheapest possible sequence of character edits (insert c, delete c, replace c -> c’) to transform x into y?
 * • cost of edit depends only on characters c, c' and cost of sequence = sum of costs of edits
 * If insert & delete cost 1, replace costs 0, minimum edit distance equivalent to finding longest common subsequence (LCS).
 * It is because insertions in X correspond to deletions in Y and deletions in X correspond to deletion in X (Yes, we just delete the character from X). 
 * *So LCS is basically the minimum number of deletions required to make both X and Y equal!* 
 * Note that a subsequence is sequential but not necessarily contiguous.
 * 
 * **Subproblems for multiple strings/sequences**
 * 1. combine suffix/prefix/substring subproblems
 * 2. multiply state spaces 
 * 3. still polynomial for O(1) strings
 * 
 * **Edit distance DP**
 * 1. subproblems => c(i, j) = edit-distance(x[i :], y[j :]) for 0 ≤ i < |x|, 0 ≤ j < |y| => O(|x|*|y|) subproblems (|x| = length of x)
 * 2. guess => whether, to turn x into y, (3 choices):
 * • x[i] deleted -> corresponds to delete c
 * • y[j] inserted -> corresponds to insert c
 * • x[i] replaced by y[j] -> corresponds to replace c -> c'
 * 3. recurrence => c(i, j) = maximum of:
 * • cost(delete x[i]) + c(i + 1, j) if i < |x|,
 * • cost(insert y[j]) + c(i, j + 1) if j < |y|,
 * • cost(replace x[i] → y[j]) + c(i + 1, j + 1) if i < |x|&j < |y|
 * Base case => c(|x|,|y|) = 0; => O(1) time per subproblem
 * 4. topological order: DAG in 2D table:
 * -----------------
 * |   ......left  |
 * |   . .         |
 * |   .  .        |
 * |   .   .diagnol|
 * |___up__________|
 * Bottom up or right to left => only need to keep last 2 rows/columns => linear space O(n) and total time = O(|x|*|y|)
 * 5. Original problem: c(0,0)
 * 
 * **Knapsack**
 * Knapsack of size S you want to pack
 * • item i has integer size si & real value vi
 * • goal: choose subset of items of maximum total value subject to total size ≤ S
 * 1. subproblem => value for suffix i:, given knapsack of size X => # of subproblems O(nS)
 * 2. guess => Whether to include item i in the set or not
 * 3. recurrence => dp[i,x] = max{dp[i+1,x], dp[i+1,x-si]+vi for all 0<=i<n && si<=X}
 * Base case => dp[n,S] = 0; => time per subproblem => O(1)
 * 4. topological order => i in n-1,n-2,.. for x in 0,..,S => total time = O(nS) 
 * => PseudoPolynomial time!(S is exponential in the number of bits required to represent the length of all the n items)
 * 5. Original problem => dp[0,S] (& use parent pointers to recover the subset)
 * Polynomial time = polynomial in input size. 
 * Pseudopolynomial time = polynomial in the problem size AND the numbers (here: S, si’s,vi’s) in input. O(nS) is pseudopolynomial
 * 
 * 
 * ***Most consistent ways of dealing with the series of stock problems***
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
 * 
 * Up to this point, I believe you have finished the following series of stock problems:
 * 121. Best Time to Buy and Sell Stock
 * 122. Best Time to Buy and Sell Stock II
 * 123. Best Time to Buy and Sell Stock III
 * 188. Best Time to Buy and Sell Stock IV
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * 
 * For each problem, we've got a couple of excellent posts explaining how to approach it. However, most of the posts failed to identify the connections among
 * these problems and made it hard to develop a consistent way of dealing with this series of problems. Here I will introduce the most generalized solution
 * applicable to all of these problems, and its specialization to each of the six problems above
 * The idea begins with the following question: Given an array representing the price of stocks on each day, what determines the maximum profit we can obtain?
 * Ans: "it depends on which day we are and how many transactions we are allowed to complete". Sure, those are important factors as they manifest themselves in the problem descriptions. 
 * However, there is a hidden factor that is not so obvious but vital in determining the maximum profit, which is elaborated below.
 * 
 * Let prices[i] be the prices array of length "n" and "i" denote the i-th day (0≤i<n), "k" denote the maximum number of transactions allowed to complete, 
 * T[i][k] be the maximum profit that could be gained at the end of the i-th day with at most k transactions. Apparently we have base cases: T[-1][k] = T[i][0] = 0, 
 * that is, no stock or no transaction yield no profit (note the first day has i = 0 so i = -1 means no stock). Now if we can somehow relate T[i][k] to its subproblems like 
 * T[i-1][k], T[i][k-1], T[i-1][k-1], ..., we will have a working recurrence relation and the problem can be solved recursively. So how do we achieve that?
 * The most straightforward way would be looking at actions taken on the i-th day. How many options do we have? The answer is three: buy, sell, rest. 
 * We do have an extra restriction saying no multiple transactions are allowed at the same time, meaning if we decide to buy on the i-th day, there should be 0 stock held in our hand before we buy;
 * if we decide to sell on the i-th day, there should be exactly 1 stock held in our hand before we sell. The number of stocks held in our hand is the hidden factor mentioned above that will affect
 * the action on the i-th day and thus affect the maximum profit.
 * 
 * Therefore our definition of T[i][k] should really be split into two: T[i][k][0] and T[i][k][1], where the former denotes the maximum profit at the end of the i-th day with at most k transactions
 * and with 0 stock in our hand AFTER taking the action, while the latter denotes the maximum profit at the end of the i-th day with at most k transactions and with 1 stock in our hand AFTER taking the action.
 * Now the base cases and the recurrence relations can be written as:
 * 
 * Base cases:
 * T[-1][k][0] = 0, T[-1][k][1] = -Infinity
 * T[i][0][0] = 0, T[i][0][1] = -Infinity
 * 
 * For the base cases, T[-1][k][0] = T[i][0][0] = 0 has the same meaning as before while T[-1][k][1] = T[i][0][1] = -Infinity emphasizes the fact that it is impossible for us to have 1 stock in hand 
 * if there is no stock available or no transactions are allowed.
 * 
 * Recurrence relations:
 * T[i][k][0] = max(T[i-1][k][0],T[i-1][k][1]+prices[i])
 * T[i][k][1] = max(T[i-1][k][1],T[i-1][k-1][0]-prices[i])
 * 
 * For T[i][k][0] in the recurrence relations, the actions taken on the i-th day can only be rest and sell, since we have 0 stock in our hand at the end of the day. T[i-1][k][0] is the maximum profit 
 * if action rest is taken, while T[i-1][k][1] + prices[i] is the maximum profit if action sell is taken. Note that the maximum number of allowable transactions remains the same, due to the fact that a transaction
 * consists of two actions coming as a pair -- buy and sell. Only action buy will change the maximum number of transactions allowed (well, there is actually an alternative interpretation, see my comment below).
 * 
 * For T[i][k][1] in the recurrence relations, the actions taken on the i-th day can only be rest and buy, since we have 1 stock in our hand at the end of the day. T[i-1][k][1] is the maximum profit if action rest 
 * is taken, while T[i-1][k-1][0] - prices[i] is the maximum profit if action buy is taken. Note that the maximum number of allowable transactions decreases by one, since buying on the i-th day will use one transaction
 * 
 * To find the maximum profit at the end of the last day, we can simply loop through the prices array and update T[i][k][0] and T[i][k][1] according to the recurrence relations above. The final answer will be T[i][k][0]
 * (we always have larger profit if we end up with 0 stock in hand)
 * 
 */
public class DynamicProgrammingMain {
	public static void main(String[] args) {
		final DynamicProgrammingMain dpMain = new DynamicProgrammingMain();
		// dpMain.runComputeNthFibonaciiMemoized();
		// dpMain.runComputeNthFibonaciiBottomUp();
		// dpMain.runComputeNthFibonaciiThreeVariables();
		dpMain.runComputeNthFibonaciiMath();
		// dpMain.runShortestPathBinaryMatrix();
		// dpMain.runHouseRobberAlternateHouses();
		// dpMain.runLongestIncreasingSubsequence();
		// dpMain.runPatienceSort();
		// dpMain.runMinCostClimbingStairs();
		// dpMain.runNthTribonaciiNumber();
		// dpMain.runDeleteAndEarn();
		// dpMain.runMaxScoreFromMultiplications();
		// dpMain.runLongestCommonSubsequence();
		// dpMain.runMaximalSquare();
		// dpMain.runMaximalSquareSpaceEfficient();
		// dpMain.runMinDifficultyOfJobSchedule();
		// dpMain.runMinCoinChange();
		// dpMain.runWordBreak();
		// dpMain.runMaxProfitIV();
		// dpMain.runMaxProfitIVEfficient();
		// dpMain.runMaxProfitWithCooldown();
		// dpMain.runPaintFence();
		// dpMain.runCoinCombinations();
		// dpMain.runDecodeWays();
		// dpMain.runMaximumSumSubarray();
		// dpMain.runMaxProfitBuyAndSellStock();
		// dpMain.runMaximumSumSubarrayCircular();
		// dpMain.runUniquePathsInAMatrix();
		// dpMain.runUniquePathsInAMatrixSpaceEfficient();
		// dpMain.runUniquePathsInAMatrixMath();
		// dpMain.runUniquePathsInAMatrixWithObstacle();
		// dpMain.runMinPathSumMatrix();
		// dpMain.runMinPathSumMatrixUsingOriginalInput();
		// dpMain.runMinFallingPathSum();
		// dpMain.runMaxProfitFromBuyAndSellSingleTransaction();
		// dpMain.runMaxProfitFromBuyAndSellInfiniteTransactions();
		// dpMain.runMaxProfitFromBuyAndSellAtMostTwoTransactions();
		// dpMain.runMaxProfitFromBuyAndSellAtMostKTransactions();
		// dpMain.runMaxProfitFromBuyAndSellInfiniteTransactionsWithCooldown();
		// dpMain.runMaxProfitFromBuyAndSellInfiniteTransactionsWithFee();
		// dpMain.runPaintHouseI();
		// dpMain.runPaintHouseII();
		// dpMain.runPaintHouseIII();
		// dpMain.runCountVowelsPermutations();
		// dpMain.runMaxLengthOfRepeatedSubArray();
		// dpMain.runNumberOfDiceRollsToTarget();
		// dpMain.runNumberOfTilings();
		// dpMain.runMinCostOfTickets();
		// dpMain.runCanMakeSquare();
		// dpMain.runCanPartitionIntoKEqualSubsetSum();
		dpMain.runIsInterleave();
	}

	// Runners
	// 1. Compute nth Fib
	public void runComputeNthFibonaciiMemoized() {
		System.out.println(computeNthFibonaciiMemoized(26));
	}

	// 2. Compute nth Fib bottom up
	public void runComputeNthFibonaciiBottomUp() {
		System.out.println(computeNthFibBottomUp(26));
	}

	// 3. Compute nth Fib three variables
	public void runComputeNthFibonaciiThreeVariables() {
		System.out.println(computeNthFibThreeVariables(92));
	}

	// 4. Compute nth Fib Math
	public void runComputeNthFibonaciiMath() {
		System.out.println(computeNthFibMath(4));
	}

	// 5. Shortest path length
	public void runShortestPathBinaryMatrix() {
		final int[][] grid = { { 0, 1, 1, 0, 0, 0 }, { 0, 1, 0, 1, 1, 0 }, { 0, 1, 1, 0, 1, 0 }, { 0, 0, 0, 1, 1, 0 },
				{ 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 0 } };
		System.out.println(shortestPathBinaryMatrix(grid));
	}

	// 6. House robber
	public void runHouseRobberAlternateHouses() {
		final int[] arr = { 1, 2, 3, 1 };
		System.out.println(houseRobberAlternateHouses(arr));
	}

	// 7. Longest increasing subsequence length
	public void runLongestIncreasingSubsequence() {
		final int[] arr = { 2, 3, 4, 1, 5, 6, 3, 7 };
		System.out.println(longestIncreasingSubsequence(arr));
	}

	// 8. patience sort
	public void runPatienceSort() {
		final int[] arr = { 2, 3, 4, 1, 5, 6, 3, 7 };
		System.out.println(patienceSort(arr));
	}

	// 9. Min cost climbing stairs
	public void runMinCostClimbingStairs() {
		final int[] arr = { 10, 15, 20 };
		System.out.println(minCostClimbingStairs(arr));
	}

	// 10. nth tribonacii number
	public void runNthTribonaciiNumber() {
		System.out.println(nthTribonaciiNumber(5));
	}

	// 10. Delete and Earn
	public void runDeleteAndEarn() {
		final int[] arr = { 3, 4, 2 };
		System.out.println(deleteAndEarn(arr));
	}

	// 11. Max score from multiplications
	public void runMaxScoreFromMultiplications() {
		final int[] arr = { 1, 2, 3 };
		final int[] mul = { 3, 2, 1 };
		System.out.println(maxScoreFromMultiplications(arr, mul));
		final int[] arr1 = { -5, -3, -3, -2, 7, 1 };
		final int[] mul1 = { -10, -5, 3, 4, 6 };
		System.out.println(maxScoreFromMultiplications(arr1, mul1));
	}

	// 12. LCS
	public void runLongestCommonSubsequence() {
		final String a = "ace";
		final String b = "abcde";
		System.out.println(longestCommonSubsequence(a, b));
	}

	// 13. Maximal Square
	public void runMaximalSquare() {
		final int[][] mat = { { 1, 0, 1, 0, 0 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0 } };
		System.out.println(maximalSquare(mat));
	}

	// 14. Maximal Square space efficient
	public void runMaximalSquareSpaceEfficient() {
		final int[][] mat = { { 1, 0, 1, 0, 0 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0 } };
		System.out.println(maximalSquareSpaceEfficient(mat));
	}

	// 15. Min difficulty of job schedule
	public void runMinDifficultyOfJobSchedule() {
		final int[] jobDifficulty = { 6, 5, 4, 3, 2, 1 };
		final int days = 2;
		System.out.println(minDifficultyOfJobSchedule(jobDifficulty, days));
	}

	// 16. Coin change
	public void runMinCoinChange() {
		final int[] coins = { 1, 2, 5 };
		final int amount = 11;
		System.out.println(minCoinChangeRequired(coins, amount));
	}

	// 17. Word Break
	public void runWordBreak() {
		final List<String> wordDict = Arrays.asList("leet", "code");
		final String s = "leetcode";
		System.out.println(wordBreak(s, wordDict));
	}

	// 18. Buy and sell max profit IV
	public void runMaxProfitIV() {
		final int[] arr = { 3, 2, 6, 5, 0, 3 };
		System.out.println(maxProfitIV(arr, 2));
	}

	// 19. Buy and sell max profit IV efficient
	public void runMaxProfitIVEfficient() {
		final int[] arr = { 3, 2, 6, 5, 0, 3 };
		System.out.println(maxProfitIVEfficient(arr, 2));
	}

	// 20. Buy and sell max profit IV with cooldown
	public void runMaxProfitWithCooldown() {
		final int[] arr = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfitWithCoolDown(arr));
	}

	// 21. Number of ways to paint fence
	public void runPaintFence() {
		System.out.println(paintFence(7, 2));
	}

	// 22. coin change 2
	public void runCoinCombinations() {
		final int[] arr = { 1, 2, 5 };
		System.out.println(numberOfCoinCombinations(arr, 5));
	}

	// 23. Decode ways
	public void runDecodeWays() {
		String s = "12";
		System.out.println(decodeWays(s));
		s = "226";
		System.out.println(decodeWays(s));
		s = "06";
		System.out.println(decodeWays(s));
	}

	// 24. Maximum sum subarray
	public void runMaximumSumSubarray() {
		final int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(maximumSumSubarray(arr));
	}

	// 25. Best time to buy and sell stocks (unlimited transactions)
	public void runMaxProfitBuyAndSellStock() {
		final int[] arr1 = { 7, 1, 5, 3, 6, 4 };
		System.out.println(maxProfitBuyAndSellStockI(arr1));
		final int[] arr2 = { 7, 6, 4, 3, 1 };
		System.out.println(maxProfitBuyAndSellStockI(arr2));
	}

	// 25. Maximum sum subarray circular
	public void runMaximumSumSubarrayCircular() {
		final int[] arr1 = { 1, -2, 3, -2 };
		System.out.println(maximumSumSubarrayCircular(arr1));
		final int[] arr2 = { 5, -3, 5 };
		System.out.println(maximumSumSubarrayCircular(arr2));
		final int[] arr3 = { -3, -2, -3 };
		System.out.println(maximumSumSubarrayCircular(arr3));
	}

	// 25. Unique paths in a matrix
	public void runUniquePathsInAMatrix() {
		System.out.println(uniquePathsInAMatrix(3, 7));
	}

	// 26. Unique paths in a matrix space efficient
	public void runUniquePathsInAMatrixSpaceEfficient() {
		System.out.println(uniquePathsInAMatrixSpaceEfficient(3, 7));
	}

	// 27. Unique paths in a matrix math
	public void runUniquePathsInAMatrixMath() {
		System.out.println(uniquePathsInAMatrixMath(3, 7));
	}

	// 28. Unique paths in a matrix math with obstacle
	public void runUniquePathsInAMatrixWithObstacle() {
		final int[][] mat = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		System.out.println(uniquePathsInAMatrixWithObstacle(mat));
	}

	// 29. Min path sum matrix
	public void runMinPathSumMatrix() {
		final int[][] mat = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(minPathSumInMatrix(mat));
		final int[][] mat2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(minPathSumInMatrix(mat2));
	}

	// 30. Min path sum matrix using original matrix
	public void runMinPathSumMatrixUsingOriginalInput() {
		final int[][] mat = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(minPathSumInMatrixUsingOriginalInput(mat));
		final int[][] mat2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(minPathSumInMatrixUsingOriginalInput(mat2));
	}

	// 31. Min falling path sum matrix using original matrix
	public void runMinFallingPathSum() {
		final int[][] mat = { { -19, 57 }, { -40, -5 } };
		System.out.println(minFallingPathSum(mat));
	}

	// 32. Max profit single buy and sell
	public void runMaxProfitFromBuyAndSellSingleTransaction() {
		final int[] prices = { 7, 1, 5, 3, 6, 4 };
		System.out.println(maxProfitFromBuyAndSellSingleTransaction(prices));
		final int[] prices1 = { 7, 6, 4, 3, 1 };
		System.out.println(maxProfitFromBuyAndSellSingleTransaction(prices1));
	}

	// 33. Max profit single buy and sell infinite transactions
	public void runMaxProfitFromBuyAndSellInfiniteTransactions() {
		final int[] prices = { 7, 1, 5, 3, 6, 4 };
		System.out.println(maxProfitFromBuyAndSellInfiniteTransactions(prices));
		final int[] prices1 = { 1, 2, 3, 4, 5 };
		System.out.println(maxProfitFromBuyAndSellInfiniteTransactions(prices1));
	}

	// 34. Max profit single buy and sell maximum 2 transactions
	public void runMaxProfitFromBuyAndSellAtMostTwoTransactions() {
		final int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
		System.out.println(maxProfitFromBuyAndSellAtMostTwoTransactions(prices));
		final int[] prices1 = { 1, 2, 3, 4, 5 };
		System.out.println(maxProfitFromBuyAndSellAtMostTwoTransactions(prices1));
	}

	// 35. Max profit single buy and sell maximum k transactions
	public void runMaxProfitFromBuyAndSellAtMostKTransactions() {
		final int[] prices = { 2, 4, 1 };
		System.out.println(maxProfitFromBuyAndSellAtMostKTransactions(prices, 2));
		final int[] prices1 = { 3, 2, 6, 5, 0, 3 };
		System.out.println(maxProfitFromBuyAndSellAtMostKTransactions(prices1, 2));
	}

	// 36. Max profit single buy and sell infinite transactions with cooldown
	public void runMaxProfitFromBuyAndSellInfiniteTransactionsWithCooldown() {
		final int[] prices = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfitFromBuyAndSellInfiniteTransactionsWithCooldown(prices));
		final int[] prices1 = { 1 };
		System.out.println(maxProfitFromBuyAndSellInfiniteTransactionsWithCooldown(prices1));
	}

	// 37. Max profit single buy and sell infinite transactions with fee
	public void runMaxProfitFromBuyAndSellInfiniteTransactionsWithFee() {
		final int[] prices = { 1, 3, 2, 8, 4, 9 };
		System.out.println(maxProfitFromBuyAndSellInfiniteTransactionsWithFee(prices, 2));
		final int[] prices1 = { 1, 3, 7, 5, 10, 3 };
		System.out.println(maxProfitFromBuyAndSellInfiniteTransactionsWithFee(prices1, 3));
	}

	// 38. Paint house I
	public void runPaintHouseI() {
		final int[][] costs = { { 17, 2, 17 }, { 16, 16, 5 }, { 14, 3, 19 } };
		System.out.println(paintHouseI(costs));
	}

	// 38. Paint house II
	public void runPaintHouseII() {
		final int[][] costs = { { 1, 5, 3 }, { 2, 9, 4 } };
		System.out.println(paintHouseII(costs));
		final int[][] costs1 = { { 1, 3 }, { 2, 4 } };
		System.out.println(paintHouseII(costs1));
	}

	// 39 . Paint house III
	public void runPaintHouseIII() {
		final int[] houses = { 0, 0, 0, 0, 0 };
		final int[][] costs = { { 1, 10 }, { 10, 1 }, { 10, 1 }, { 1, 10 }, { 5, 1 } };
		final int target = 3;
		System.out.println(paintHouseIII(houses, costs, target));
	}

	// 40 . Count vowels permutation
	public void runCountVowelsPermutations() {
		System.out.println(countVowelsPermutation(2));
		System.out.println(countVowelsPermutation(144));
	}

	// 41 . Max length of repeated sub array
	public void runMaxLengthOfRepeatedSubArray() {
		final int[] a = { 1, 2, 3, 2, 1 };
		final int[] b = { 3, 2, 1, 4, 7 };
		System.out.println(maxLengthOfRepeatedSubArray(a, b));
		final int[] c = { 0, 0, 0, 0, 0 };
		final int[] d = { 0, 0, 0, 0, 0 };
		System.out.println(maxLengthOfRepeatedSubArray(c, d));
	}

	// 41 . Number of dice rolls to target
	public void runNumberOfDiceRollsToTarget() {
		System.out.println(numberOfDiceRollsToTarget(1, 6, 3));
		System.out.println(numberOfDiceRollsToTarget(2, 6, 7));
	}

	// 42 . Number of tilings
	public void runNumberOfTilings() {
		System.out.println(numberOfTilings(5));
		System.out.println(numberOfTilings(7));
	}

	// 43 . Min cost of tickets
	public void runMinCostOfTickets() {
		final int[] days = { 1, 4, 6, 7, 8, 20 };
		final int[] costs = { 2, 7, 15 };
		System.out.println(minCostTickets(days, costs));
	}

	// 44 . Make square
	public void runCanMakeSquare() {
		final int[] arr = { 1, 1, 2, 2, 2 };
		System.out.println(canMakeSquare(arr));
	}

	// 45 . Can partition into K subsets with equal sum
	public void runCanPartitionIntoKEqualSubsetSum() {
		final int[] arr = { 4, 3, 2, 3, 5, 2, 1 };
		System.out.println(canPartitionIntoKEqualSubsetSum(arr, 4));
	}

	// 46 . Is Interleaved string
	public void runIsInterleave() {
		final String s1 = "aabcc";
		final String s2 = "dbbca";
		final String s3 = "aadbbcbcac";
		System.out.println(isInterleave(s1, s2, s3));
	}

	// Algorithms
	// ***https://leetcode.com/explore/learn/card/dynamic-programming***
	// 1. Compute Nth fibonacii number
	// Time - O(n), Space - O(n)
	public long computeNthFibonaciiMemoized(int n) {
		if (n <= 2) {
			return 1;
		}
		final Map<Integer, Long> map = new HashMap<>();
		return computeNthFibonaciiMemoized(n, map);
	}

	private long computeNthFibonaciiMemoized(int n, Map<Integer, Long> map) {
		if (n <= 2) {
			return 1;
		}
		if (map.containsKey(n)) {
			return map.get(n);
		}
		final long nthFib = computeNthFibonaciiMemoized(n - 1, map) + computeNthFibonaciiMemoized(n - 2, map);
		map.put(n, nthFib);
		return nthFib;
	}

	// 2. Compute nth fib bottom up
	// Time - O(n), Space - O(n)
	public long computeNthFibBottomUp(int n) {
		if (n <= 2) {
			return 1;
		}
		final var dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 1;
		for (var i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	// 3. Compute nth fib with 2 variables
	// Time - O(n), Space - O(1)
	public long computeNthFibThreeVariables(int n) {
		if (n <= 2) {
			return 1;
		}
		double a = 1;
		double b = 1;
		double c = 0;
		for (var i = 3; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return (long) c;
	}

	// 4. Compute nth Fib using Math
	// Time - O(1), Space - O(1)
	public long computeNthFibMath(int n) {
		if (n <= 2) {
			return 1;
		}
		final double phi = (1 + Math.sqrt(5)) / 2;
		final double minusPhi = (1 - Math.sqrt(5)) / 2;
		final double nthFib = (Math.pow(phi, n) - Math.pow(minusPhi, n)) / Math.sqrt(5);
		return (long) nthFib;
	}

	// 5. Shortest path length
	// Time - O(n*m), Space - O(n*m)
	public int shortestPathBinaryMatrix(int[][] grid) {
		final int n = grid.length;
		final int m = grid[0].length;
		if (n != m || grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
			return -1;
		}
		final int[][] dirArray = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
				{ -1, -1 } };
		final Queue<int[]> queue = new LinkedList<>();
		final boolean[][] visited = new boolean[n][n];
		visited[0][0] = true;
		queue.add(new int[] { 0, 0 });
		int minPath = 0;
		while (!queue.isEmpty()) {
			final int size = queue.size();
			for (int i = 0; i < size; i++) {
				final int[] pair = queue.poll();
				final int pairX = pair[0];
				final int pairY = pair[1];
				if (pairX == n - 1 && pairY == n - 1) {
					return minPath + 1;
				}
				for (int j = 0; j < dirArray.length; j++) {
					final int nextX = pairX + dirArray[j][0];
					final int nextY = pairY + dirArray[j][1];
					if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextX][nextY]
							&& grid[nextX][nextY] == 0) {
						queue.add(new int[] { nextX, nextY });
						visited[nextX][nextY] = true;
					}
				}
			}
			minPath++;
		}
		return -1;
	}

	// 6. House Robber
	/*
	 * Given an integer array nums representing the amount of money of each
	 * house, return the maximum amount of money you can rob with the condition
	 * that you can rob only alternate houses
	 */
	// Time - O(n), Space - O(n)
	public int houseRobberAlternateHouses(int[] arr) {
		final var n = arr.length;
		if (n <= 1) {
			return arr[0];
		}
		// dp[i] = Max amount of money you can rob upto and including i
		final int[] dp = new int[n];
		dp[0] = arr[0];
		dp[1] = Math.max(arr[0], arr[1]);
		for (var i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 1]);
		}
		return dp[n - 1];
	}

	// 7. Longest Increasing subsequence
	// Time - O(n^2), Space - O(n)
	public int longestIncreasingSubsequence(int[] arr) {
		final int n = arr.length;
		// dp[i] = length of longest increasing subsequence that ends with ith
		// element
		final int[] dp = new int[n];
		Arrays.fill(dp, 1);
		var lisLength = 1;
		for (var i = 1; i < n; i++) {
			for (var j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				if (dp[i] > lisLength) {
					lisLength = dp[i];
				}
			}
		}
		return lisLength;
	}

	// 8. Patience sort
	// https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
	// https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
	// Time - O(nlogn), Space - O(1)
	// **Note: Patience sort gives the Length of the longest increasing
	// subsequence problem above**
	public int patienceSort(int[] arr) {
		final List<Integer> piles = new ArrayList<>(arr.length);
		for (final int num : arr) {
			// pile would have the insertion point of the number in the array
			int pile = Collections.binarySearch(piles, num);
			if (pile < 0) {
				pile = ~pile;
			}
			// System.out.println("pile:" + pile + " size:" + piles.size());
			if (pile == piles.size()) {
				piles.add(num);
			} else {
				piles.set(pile, num);
			}
		}
		return piles.size();
	}

	// 9. Climbing stairs
	/*
	 * You are climbing a staircase. It takes n steps to reach the top.Each time
	 * you can either climb 1 or 2 steps. In how many distinct ways can you
	 * climb to the top?
	 */
	// Time - O(1), Space - O(1)
	public long maxWaysToClimbStairs(int n) {
		return computeNthFibMath(n);
	}

	// 10. Min cost climbing stairs
	// https://leetcode.com/problems/min-cost-climbing-stairs
	/*
	 * You are given an integer array cost where cost[i] is the cost of ith step
	 * on a staircase. Once you pay the cost, you can either climb one or two
	 * steps. You can either start from the step with index 0, or the step with
	 * index 1. Return the minimum cost to reach the top of the floor.
	 */
	// Time - O(n), Space - O(1)
	public int minCostClimbingStairs(int[] arr) {
		final int n = arr.length;
		var prev = 0;
		var cur = 0;
		var result = 0;
		for (var i = 2; i <= n; i++) {
			result = Math.min(prev + arr[i - 2], cur + arr[i - 1]);
			prev = cur;
			cur = result;
		}
		return result;
	}

	// 11. Nth tribonacii number
	// t0 = 0, t1 = 1, t2 = 1, tn = tn-1 + tn-2 + tn-3 , n >=0. Find tn
	// Time - O(n), Space - O(1)
	public int nthTribonaciiNumber(int n) {
		var x1 = 0;
		var x2 = 1;
		var x3 = 1;
		var result = 0;
		for (var i = 3; i <= n; i++) {
			result = x1 + x2 + x3;
			x1 = x2;
			x2 = x3;
			x3 = result;
		}
		return result;
	}

	// 12. Delete and Earn
	// https://leetcode.com/problems/delete-and-earn
	/*
	 * You are given an integer array nums. You want to maximize the number of
	 * points you get by performing the following operation any number of times:
	 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you
	 * must delete every element equal to nums[i] - 1 and every element equal to
	 * nums[i] + 1. Return the maximum number of points you can earn by applying
	 * the above operation some number of times.
	 */
	// Time - O(n), Space - O(n)
	public int deleteAndEarn(int[] arr) {
		var maxNumber = 0;
		for (final var num : arr) {
			if (maxNumber < num) {
				maxNumber = num;
			}
		}
		final var countArr = new int[maxNumber + 1];
		for (var i = 0; i < arr.length; i++) {
			countArr[arr[i]]++;
		}
		// dp[i] = max points earned till i for i in [0..maxNumber] (including
		// maxNumber)
		// if i is not deleted, dp[i] = dp[i-1], else if i is deleted, i-1 and
		// i+1 are deleted (but i+1 is not there yet as we are considereing only
		// till i). So dp[i] = i*countArr[i] (value of arr[i]*frequency) +
		// dp[i-2] (max points till before last element as it's not affected by
		// taking i to be deleted). So dp[i] = max of those 2 cases.
		final var dp = new int[countArr.length];
		dp[0] = countArr[0];
		dp[1] = countArr[1];
		for (var i = 2; i <= maxNumber; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * countArr[i]);
		}
		return dp[maxNumber];
	}

	// 13. Max score from multiplications
	// https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
	/*
	 * You are given two integer arrays nums and multipliers of size n and m
	 * respectively, where n >= m. You begin with a score of 0. You want to
	 * perform exactly m operations. On the ith operation (1-indexed), you will:
	 * 1. Choose one integer x from either the start or the end of the array
	 * nums. 2. Add multipliers[i] * x to your score. 3. Remove x from the array
	 * nums. Return the maximum score after performing m operations.
	 */
	// Time - O(m^2), Space - O(m^2)
	public int maxScoreFromMultiplications(int[] arr, int[] mul) {
		final var n = arr.length;
		final var m = mul.length;
		// dp(i, left) = return the maximum possible score if we have already
		// done i total operations and used left numbers from the left side. To
		// answer the original problem, we should return dp(0, 0)
		final var dp = new int[m + 1][m + 1];
		for (var i = m - 1; i >= 0; i--) {
			for (var left = i; left >= 0; left--) {
				final var right = n - 1 - (i - left);
				dp[i][left] = Math.max(mul[i] * arr[left] + dp[i + 1][left + 1], mul[i] * arr[right] + dp[i + 1][left]);
			}
		}
		return dp[0][0];
	}

	// 14. Longest common subsequence
	// https://leetcode.com/problems/longest-common-subsequence/
	/*
	 * Given two strings text1 and text2, return the length of their longest
	 * common subsequence. If there is no common subsequence, return 0. For
	 * example, "ace" is a subsequence of "abcde".
	 */
	// Time - O(n*m), Space - O(n*m)
	public int longestCommonSubsequence(String a, String b) {
		final var n = a.length();
		final var m = b.length();
		// dp[i][j] = *return the* answer to the problem (length of longest
		// common subsequence) *after* taking i chars from a and j chars from b
		final int[][] dp = new int[n + 1][m + 1];
		for (var i = n - 1; i >= 0; i--) {
			for (var j = m - 1; j >= 0; j--) {
				if (a.charAt(i) == b.charAt(j)) {
					// if both chars are equal, return 1 + answer to the next
					// sub problem
					dp[i][j] = 1 + dp[i + 1][j + 1];
				} else {
					// else return the max of length of LCS by deleting the 1st
					// character from a and b
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}
		return dp[0][0];
	}

	// 15. Maximal square
	// https://leetcode.com/problems/maximal-square/
	/*
	 * Given an m x n binary matrix filled with 0's and 1's, find the largest
	 * square containing only 1's and return its area.
	 */
	// Time - O(n*m), Space - O(n*m)
	public int maximalSquare(int[][] mat) {
		final var n = mat.length;
		final var m = mat[0].length;
		// dp(i,j) represents the side length of the maximum square whose bottom
		// right corner is the cell with index (i,j) in the original matrix.
		// Starting from index (0,0), for every 1 found in the original matrix,
		// we update the value of the current element as
		// dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1.We also remember the
		// size of the largest square found so far. In this way, we traverse the
		// original matrix once and find out the required maximum size. This
		// gives the side length of the square (say maxSquareLength). The
		// required result is the area is maxSquareLength^2
		final int[][] dp = new int[n + 1][m + 1];
		var maxSquareLength = 0;
		for (var i = 1; i <= n; i++) {
			for (var j = 1; j <= m; j++) {
				if (mat[i - 1][j - 1] == 1) {
					// // dp[i][j] = length of max square with ones till (i,j).
					// We take n+1 and m+1 as sizes to make dp[1][1] correspond
					// to matrix[0][0] and finally compute till dp[n][m]
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
					maxSquareLength = Math.max(maxSquareLength, dp[i][j]);
				}
			}
		}
		return maxSquareLength * maxSquareLength;
	}

	// 16. Maximal square optimized space version
	// https://leetcode.com/problems/maximal-square/
	/*
	 * Given an m x n binary matrix filled with 0's and 1's, find the largest
	 * square containing only 1's and return its area.
	 */
	// Time - O(n*m), Space - O(m)
	public int maximalSquareSpaceEfficient(int[][] mat) {
		final var n = mat.length;
		final var m = mat[0].length;
		// In the previous approach for calculating dp of i th row we are using
		// only the previous element and the (i−1) th row. Therefore, we don't
		// need 2D dp matrix as 1D dp array will be sufficient for
		// this.Initially the dp array contains all 0's. As we scan the elements
		// of the original matrix across a row, we keep on updating the dp array
		// as per the equation dp[j]=min(dp[j−1],dp[j],prev) + 1, where prev
		// refers to the old dp[j−1]. For every row, we repeat the same process
		// and update in the same dp array.

		final int[] dp = new int[m + 1];
		var maxSquareLength = 0;
		var prev = 0;
		for (var i = 1; i <= n; i++) {
			for (var j = 1; j <= m; j++) {
				final var temp = dp[j];
				if (mat[i - 1][j - 1] == 1) {
					// // dp[j] = length of max square with ones till column j
					// for each row.
					// We take m+1 as sizes to make dp[1] correspond
					// to matrix[0] and finally compute till dp[m]
					dp[j] = Math.min(prev, Math.min(dp[j], dp[j - 1])) + 1;
					maxSquareLength = Math.max(maxSquareLength, dp[j]);
				} else {
					dp[j] = 0;
				}
				prev = temp; // prev stores dp[j-1] in the (i-1)th row
			}
		}
		return maxSquareLength * maxSquareLength;
	}

	// 17. Minimum difficulty of a job schedule
	// https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
	/*
	 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To
	 * work on the ith job, you have to finish all the jobs j where 0 <= j < i).
	 * You have to finish at least one task every day. The difficulty of a job
	 * schedule is the sum of difficulties of each day of the d days. The
	 * difficulty of a day is the maximum difficulty of a job done on that day.
	 * You are given an integer array jobDifficulty and an integer d. The
	 * difficulty of the ith job is jobDifficulty[i]. Return the minimum
	 * difficulty of a job schedule. If you cannot find a schedule for the jobs
	 * return -1.
	 */
	// Time - O(d*(n-d)^2), Space - O(n*d)
	public int minDifficultyOfJobSchedule(int[] jobDifficulty, int d) {
		final var n = jobDifficulty.length;
		if (d > n) {
			return -1;
		}
		// dp[i][day] = min difficulty of a job schedule starting with index i
		// for day "day". Take d+1 as size as we count days from 1
		final int[][] dp = new int[n][d + 1];
		// dp[i][day] = min(hardest + dp[j+1][day+1] for all i<=j<n-(d-day) and
		// hardest = max(jobDifficulty[k]) for all i<=k<=j)

		for (final int[] row : dp) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}

		// Base case
		dp[n - 1][d] = jobDifficulty[n - 1];
		// populate the max difficulty of last day based on if you started from
		// index i
		for (var i = n - 2; i >= 0; i--) {
			dp[i][d] = Math.max(jobDifficulty[i], dp[i + 1][d]);
		}

		for (var day = d - 1; day >= 1; day--) {
			for (var i = day - 1; i < n - (d - day); i++) {
				var hardest = 0;
				for (var j = i; j < n - (d - day); j++) {
					hardest = Math.max(hardest, jobDifficulty[j]);
					dp[i][day] = Math.min(dp[i][day], hardest + dp[j + 1][day + 1]);
				}
			}
		}
		DPUtils.printMatrix(dp);
		return dp[0][1];
	}

	// 18. Coin change
	// https://leetcode.com/problems/coin-change/
	/*
	 * You are given an integer array coins representing coins of different
	 * denominations and an integer amount representing a total amount of money.
	 * Return the fewest number of coins that you need to make up that amount.
	 * If that amount of money cannot be made up by any combination of the
	 * coins, return -1. You may assume that you have an infinite number of each
	 * kind of coin.
	 */
	public int minCoinChangeRequired(int[] coins, int amount) {
		final var n = coins.length;
		final var dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);

		// Base case
		dp[0] = 0;

		for (var i = 1; i <= amount; i++) {
			for (var j = 0; j < n; j++) {
				if (i >= coins[j]) {
					dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	// 19. Word break
	// https://leetcode.com/problems/word-break/
	/*
	 * Given a string s and a dictionary of strings wordDict, return true if s
	 * can be segmented into a space-separated sequence of one or more
	 * dictionary words. Note that the same word in the dictionary may be reused
	 * multiple times in the segmentation. Input: s = "leetcode", wordDict =
	 * ["leet","code"] Output: true Explanation: Return true because "leetcode"
	 * can be segmented as "leet code".
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
		final var n = s.length();
		// the criteria is: A word from wordDict can end at the current index i.
		// If that word is to end at index i, then it starts at index i -
		// word.length + 1. The index before that i - word.length should also be
		// formable from wordDict.
		// dp[i] represents if it is possible to build the string s up to index
		// i from wordDict
		// dp[i] = s.substring(i - word.length() +1, i+1).equals(word) &&
		// dp[i-word.length()]
		final var dp = new boolean[n];
		for (var i = 0; i < n; i++) {
			for (final String word : wordDict) {
				final var wordLength = word.length();
				if (i >= wordLength - 1 && (i == wordLength - 1 || dp[i - wordLength])) {
					if (s.substring(i - wordLength + 1, i + 1).equals(word)) {
						dp[i] = true;
						break;
					}
				}
			}
		}
		return dp[n - 1];
	}

	// 20. Best time to buy and sell stock IV
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
	/*
	 * You are given an integer array prices where prices[i] is the price of a
	 * given stock on the ith day, and an integer k. Find the maximum profit you
	 * can achieve. You may complete at most k transactions. Note: You may not
	 * engage in multiple transactions simultaneously (i.e., you must sell the
	 * stock before you buy again).
	 */
	// Time - O(k*n), Space - O(k*n)
	public int maxProfitIV(int[] prices, int k) {
		final var n = prices.length;
		final var dp = new int[n + 1][k + 1][2];
		for (var i = n - 1; i >= 0; i--) {
			for (var transactionsRemaining = 1; transactionsRemaining <= k; transactionsRemaining++) {
				for (var holding = 0; holding < 2; holding++) {
					final var doNothing = dp[i + 1][transactionsRemaining][holding];
					var doSomething = 0;
					if (holding == 1) {
						// sell stock
						doSomething = prices[i] + dp[i + 1][transactionsRemaining - 1][0];
					} else if (holding == 0) {
						doSomething = -prices[i] + dp[i + 1][transactionsRemaining][1];
					}
					dp[i][transactionsRemaining][holding] = Math.max(doNothing, doSomething);
				}
			}
		}
		return dp[0][k][0];
	}

	// 21. Best time to buy and sell stock IV efficient
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
	// ***https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems***
	/*
	 * You are given an integer array prices where prices[i] is the price of a
	 * given stock on the ith day, and an integer k. Find the maximum profit you
	 * can achieve. You may complete at most k transactions. Note: You may not
	 * engage in multiple transactions simultaneously (i.e., you must sell the
	 * stock before you buy again).
	 */
	// Time - O(k*n), Space - O(k*n)
	public int maxProfitIVEfficient(int[] prices, int k) {
		final var n = prices.length;
		final var dp = new int[n][k + 1];
		int maxProfitTillLast = -prices[0];
		for (var i = 1; i < n; i++) {
			for (var transactionsRemaining = 1; transactionsRemaining <= k; transactionsRemaining++) {
				dp[i][transactionsRemaining] = Math.max(dp[i - 1][transactionsRemaining],
						maxProfitTillLast + prices[i]);
				maxProfitTillLast = Math.max(maxProfitTillLast, dp[i - 1][transactionsRemaining - 1] - prices[i]);
			}
		}
		return dp[n - 1][k];
	}

	// 22. Best time to buy and sell stock with cooldown
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
	/*
	 * buy[i] means before day i what is the maxProfit for any sequence end with
	 * buy. sell[i] means before day i what is the maxProfit for any sequence
	 * end with sell. rest[i] means before day i what is the maxProfit for any
	 * sequence end with rest. Then we want to deduce the transition functions
	 * for buy sell and rest. By definition we have: buy[i] =
	 * max(rest[i-1]-price, buy[i-1]) sell[i] = max(buy[i-1]+price, sell[i-1])
	 * rest[i] = max(sell[i-1], buy[i-1], rest[i-1]) Where price is the price of
	 * day i. All of these are very straightforward. They simply represents :
	 * (1) We have to `rest` before we `buy` and (2) we have to `buy` before we
	 * `sell` One tricky point is how do you make sure you sell before you buy,
	 * since from the equations it seems that [buy, rest, buy] is entirely
	 * possible. Well, the answer lies within the fact that buy[i] <= rest[i]
	 * which means rest[i] = max(sell[i-1], rest[i-1]). That made sure [buy,
	 * rest, buy] is never occurred. A further observation is that and rest[i]
	 * <= sell[i] is also true therefore rest[i] = sell[i-1] Substitute this in
	 * to buy[i] we now have 2 functions instead of 3: buy[i] =
	 * max(sell[i-2]-price, buy[i-1]) sell[i] = max(buy[i-1]+price, sell[i-1])
	 * This is better than 3, but we can do even better Since states of day i
	 * relies only on i-1 and i-2 we can reduce the O(n) space to O(1).
	 */
	public int maxProfitWithCoolDown(int[] prices) {
		final var n = prices.length;
		int buy = Integer.MIN_VALUE, prevBuy = 0, sell = 0, prevSell = 0; // NOSONAR
		for (var i = 0; i < n; i++) {
			prevBuy = buy;
			buy = Math.max(prevSell - prices[i], prevBuy);
			prevSell = sell;
			sell = Math.max(prevBuy + prices[i], prevSell);
		}
		return sell;
	}

	// 23. Number of ways to paint a fence
	// https://leetcode.com/problems/paint-fence/
	/*
	 * You are painting a fence of n posts with k different colors. You must
	 * paint the posts following these rules: Every post must be painted exactly
	 * one color. There cannot be three or more consecutive posts with the same
	 * color. Given the two integers n and k, return the number of ways you can
	 * paint the fence.
	 */
	// Time - O(n), Space - O(1)
	public int paintFence(int n, int k) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return k;
		}
		if (n == 2) {
			return k * k;
		}
		// numWaysToPaint(i) = numOfWaysToPaintDifferentlyFromPrevious(i) +
		// numOfWaysToPaintSameAsPrevious(i)
		// numOfWaysToPaintDifferentlyFromPrevious(i) = numWaysToPaint(i-1) *
		// (k-1)
		// numOfWaysToPaintSameAsPrevious(i) =
		// numOfWaysToPaintDifferentlyFromPrevious(i-1) * 1 (as three in a row
		// shouldn't be painted with same color)
		// => = numWaysToPaint(i-2) * (k-1) * 1 (as three in a row shouldn't be
		// painted with same color)
		// => numWaysToPaint(i) = numWaysToPaint(i-1) * (k-1) +
		// numWaysToPaint(i-2) * (k-1)
		// = (numWaysToPaint(i-1) + numOfWaysToPaint(i-2)) * (k-1)

		var prevPrev = k; // dp[1]
		var prev = k * k; // dp[2]
		var cur = 0; // dp[i]
		for (var i = 3; i <= n; i++) {
			// dp[i] = (dp[i-2]+dp[i-1])*(k-1)
			cur = (prevPrev + prev) * (k - 1);
			prevPrev = prev;
			prev = cur;
		}
		return cur; // dp[n]
	}

	// 24. Coin change 2
	// https://leetcode.com/problems/coin-change-2/
	// https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
	/*
	 * https://www.youtube.com/watch?v=_fgjrs570YE You are given an integer
	 * array coins representing coins of different denominations and an integer
	 * amount representing a total amount of money. Return the number of
	 * combinations that make up that amount. If that amount of money cannot be
	 * made up by any combination of the coins, return 0. You may assume that
	 * you have an infinite number of each kind of coin.
	 */
	// Time - O(k*n), Space - O(n)
	public int numberOfCoinCombinations(int[] coins, int amount) {
		final var dp = new int[amount + 1];
		// # of combinations to make amount 0 is 1 (using none of the coins)
		dp[0] = 1;
		for (final var coin : coins) {
			for (var i = coin; i <= amount; i++) {
				dp[i] += dp[i - coin];
			}
		}
		return dp[amount];
	}

	// 25. Decode ways
	// https://leetcode.com/problems/decode-ways/
	/*
	 * A message containing letters from A-Z can be encoded into numbers as 'A'
	 * -> "1", 'B' -> "2", ..'Z' -> "26". To decode an encoded message, all the
	 * digits must be grouped then mapped back into letters using the reverse of
	 * the mapping above (there may be multiple ways). For example, "11106" can
	 * be mapped into: 1. "AAJF" with the grouping (1 1 10 6) 2. "KJF" with the
	 * grouping (11 10 6). Note that the grouping (1 11 06) is invalid because
	 * "06" cannot be mapped into 'F' since "6" is different from "06". Given a
	 * string s containing only digits, return the number of ways to decode it.
	 */
	// TIme - O(n), Space - O(n)
	public int decodeWays(String s) {
		final var n = s.length();
		final var dp = new int[n];
		dp[0] = s.charAt(0) - '0' != 0 ? 1 : 0;
		for (var i = 1; i < n; i++) {
			final var first = Integer.parseInt(s.substring(i, i + 1));
			final var second = Integer.parseInt(s.substring(i - 1, i + 1));
			if (1 <= first && first <= 9) {
				dp[i] += dp[i - 1];
			}
			if (10 <= second && second <= 26) {
				dp[i] += (i >= 2) ? dp[i - 2] : 1;
			}
		}
		return dp[n - 1];
	}

	// 26. Maximum sum subarray
	// ***Kadane's algorithm***
	/*
	 * https://leetcode.com/problems/maximum-subarray/ Given an integer array
	 * nums, find the contiguous subarray (containing at least one number) which
	 * has the largest sum and return its sum.A subarray is a contiguous part of
	 * an array.
	 */
	// Time - O(n), Space - O(1)
	public int maximumSumSubarray(int[] arr) {
		final var n = arr.length;
		var currentSum = 0;
		var maxSum = Integer.MIN_VALUE;
		for (var windowEnd = 0; windowEnd < n; windowEnd++) {
			currentSum += arr[windowEnd];
			if (currentSum > maxSum) {
				maxSum = currentSum;
			}
			if (currentSum < 0) {
				currentSum = 0;
			}
		}
		return maxSum;
	}

	// 27. Best time to buy and sell stock I
	// ***Kadane's algorithm variant***
	/*
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ You are
	 * given an array prices where prices[i] is the price of a given stock on
	 * the ith day. You want to maximize your profit by choosing a single day to
	 * buy one stock and choosing a different day in the future to sell that
	 * stock. Return the maximum profit you can achieve from this transaction.
	 * If you cannot achieve any profit, return 0.
	 */
	// Time - O(n), Space - O(1)
	public int maxProfitBuyAndSellStockI(int[] arr) {
		final var n = arr.length;
		var minValueTillNow = Integer.MAX_VALUE;
		var maxProfit = 0;
		for (var i = 0; i < n; i++) {
			if (arr[i] < minValueTillNow) {
				minValueTillNow = arr[i];
			} else if (arr[i] - minValueTillNow > maxProfit) {
				maxProfit = arr[i] - minValueTillNow;
			}
		}
		return maxProfit;
	}

	// 28. Maximum sum circular subarray
	// https://leetcode.com/problems/maximum-sum-circular-subarray/
	/*
	 * Given a circular integer array nums of length n, return the maximum
	 * possible sum of a non-empty subarray of nums. A circular array means the
	 * end of the array connects to the beginning of the array. Formally, the
	 * next element of nums[i] is nums[(i + 1) % n] and the previous element of
	 * nums[i] is nums[(i - 1 + n) % n]. A subarray may only include each
	 * element of the fixed buffer nums at most once. Formally, for a subarray
	 * nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j
	 * with k1 % n == k2 % n.
	 */
	public int maximumSumSubarrayCircular(int[] arr) {
		var maxSumTillNow = 0;
		var minSumTillNow = 0;
		var maxSum = Integer.MIN_VALUE;
		var minSum = Integer.MAX_VALUE;
		var total = 0;
		// If the max sum subarray lies in prefix + suffix instead of in middle,
		// max(prefix + suffix) = max(totalSum - subarray) = totalSum +
		// max(-subarray) = totalSum - min(subarray)
		for (final var element : arr) {
			maxSumTillNow = Math.max(maxSumTillNow + element, element);
			maxSum = Math.max(maxSum, maxSumTillNow);
			minSumTillNow = Math.min(minSumTillNow + element, element);
			minSum = Math.min(minSum, minSumTillNow);
			total += element;
		}
		return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
	}

	// 29. Unique paths in a matrix
	// https://leetcode.com/problems/unique-paths/
	/*
	 * A robot starting from top left corner (0,0) tries to move to bottom right
	 * corner (m-1,n-1). It can move only down or right. Given the two integers
	 * m and n, return the number of possible unique paths that the robot can
	 * take to reach the bottom-right corner.
	 */
	// Time - O(m*n), Space - O(m*n)
	public int uniquePathsInAMatrix(int m, int n) {
		final int[][] dp = new int[m][n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (i == m - 1 || j == n - 1) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
				}
			}
		}
		return dp[0][0];
	}

	// 30. Unique paths in a matrix space efficient
	// https://leetcode.com/problems/unique-paths/
	/*
	 * A robot starting from top left corner (0,0) tries to move to bottom right
	 * corner (m-1,n-1). It can move only down or right. Given the two integers
	 * m and n, return the number of possible unique paths that the robot can
	 * take to reach the bottom-right corner.
	 */
	// Time - O(m*n), Space - O(n)
	public int uniquePathsInAMatrixSpaceEfficient(int m, int n) {
		// This is a typical 2D DP problem, we can store value in 2D DP array,
		// but since we only need to use value at dp[i - 1][j] and dp[i][j - 1]
		// to update dp[i][j], we don't need to store the whole 2D table, but
		// instead store value in an 1D array, and update data by using dp[j] =
		// dp[j] + dp[j - 1], (where here dp[j] corresponding to the dp[i -
		// 1][j]) and dp[j - 1] corresponding to the dp[i][j - 1] in the 2D
		// array)
		if (m == 1 && n == 1) {
			return 1;
		}
		final var dp = new int[n];
		dp[0] = 1;
		for (var i = 0; i < m; i++) {
			for (var j = 0; j < n; j++) {
				if (j > 0) {
					dp[j] += dp[j - 1];
				}
			}
		}
		return dp[n - 1];
	}

	// 30. Unique paths in a matrix combinatorics
	/*
	 * https://leetcode.com/problems/unique-paths/ A robot starting from top
	 * left corner (0,0) tries to move to bottom right corner (m-1,n-1). It can
	 * move only down or right. Given the two integers m and n, return the
	 * number of possible unique paths that the robot can take to reach the
	 * bottom-right corner.
	 */
	// Time - O(max(m,n)), Space - O(1)
	public int uniquePathsInAMatrixMath(int m, int n) {
		// (m+n-2)C(m-1) = (m+n-2)!/(m-1)!*(n-1)!
		if (m == 1 && n == 1) {
			return 1;
		}
		m--;
		n--;
		// after this the formula becomes (m+n)!/m!*n! =
		// (m+n)*(m+n-1)*..*(m+1)/n!
		long result = 1;
		for (int i = m + 1, j = 1; i <= m + n && j <= n; i++, j++) {
			result *= i;
			result /= j;
		}
		return (int) result;
	}

	// 31. Unique paths in a matrix II
	// https://leetcode.com/problems/unique-paths-ii/
	// https://leetcode.com/problems/unique-paths-ii/discuss/23250/Short-JAVA-solution
	/*
	 * A robot starting from top left corner (0,0) tries to move to bottom right
	 * corner (m-1,n-1). It can move only down or right. An obstacle and space
	 * are marked as 1 or 0 respectively in grid. A path that the robot takes
	 * cannot include any square that is an obstacle. Return the number of
	 * possible unique paths that the robot can take to reach the bottom-right
	 * corner.
	 */
	// Time - O(m*n), Space - O(n)
	public int uniquePathsInAMatrixWithObstacle(int[][] mat) {
		final var m = mat.length;
		final var n = mat[0].length;
		if (mat[0][0] == 1 || mat[m - 1][n - 1] == 1) {
			return 0;
		}
		final var dp = new int[n];
		dp[0] = 1;
		for (var i = 0; i < m; i++) {
			for (var j = 0; j < n; j++) {
				if (mat[i][j] == 1) {
					dp[j] = 0;
				} else if (j > 0) {
					dp[j] += dp[j - 1];
				}
			}
		}
		return dp[n - 1];
	}

	// 32. Minimum path sum
	// https://leetcode.com/problems/minimum-path-sum/submissions/
	/*
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right, which minimizes the sum of all numbers along its
	 * path. Note: You can only move either down or right at any point in time.
	 */
	// Time - O(m*n), Space - O(n)
	public int minPathSumInMatrix(int[][] mat) {
		final var m = mat.length;
		final var n = mat[0].length;
		// extra space - O(n)
		final var dp = new int[n];
		dp[0] = mat[0][0];
		// as the only way to go to the columns in row 0 is through right, the
		// sum of values in it's path is previous sum + current element
		for (var j = 1; j < n; j++) {
			dp[j] = dp[j - 1] + mat[0][j];
		}
		// loop through the matrix and update every cell with the min from top
		// and left
		for (var i = 1; i < m; i++) {
			// update left in 0th column before using it in the same row
			// (current loop)
			dp[0] += mat[i][0];
			for (var j = 1; j < n; j++) {
				dp[j] = Math.min(dp[j], dp[j - 1]) + mat[i][j];
			}
		}
		return dp[n - 1];
	}

	// 33. Minimum path sum using original input (if allowed to modify)
	// https://leetcode.com/problems/minimum-path-sum/submissions/
	/*
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right, which minimizes the sum of all numbers along its
	 * path. Note: You can only move either down or right at any point in time.
	 */
	// Time - O(m*n), Space - O(1)
	public int minPathSumInMatrixUsingOriginalInput(int[][] mat) {
		final var m = mat.length;
		final var n = mat[0].length;
		// if allowed to modify the original matrix, it can be used as a dp[][]
		// extra space - O(1)
		for (var i = 0; i < m; i++) {
			for (var j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					continue;
				} else if (i != 0 && j == 0) {
					mat[i][j] += mat[i - 1][j];
				} else if (i == 0 && j != 0) {
					mat[i][j] += mat[i][j - 1];
				} else {
					mat[i][j] += Math.min(mat[i][j - 1], mat[i - 1][j]);
				}
			}
		}
		return mat[m - 1][n - 1];
	}

	// 34. Minimum falling path sum
	// https://leetcode.com/problems/minimum-falling-path-sum/
	/*
	 * Given an n x n array of integers matrix, return the minimum sum of any
	 * falling path through matrix.A falling path starts at any element in the
	 * first row and chooses the element in the next row that is either directly
	 * below or diagonally left/right. Specifically, the next element from
	 * position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row +
	 * 1, col + 1).
	 */
	public int minFallingPathSum(int[][] mat) {
		final var m = mat.length;
		final var n = mat[0].length;
		var minFallingPathSum = Integer.MAX_VALUE;
		for (var i = 1; i < m; i++) {
			for (var j = 0; j < n; j++) {
				mat[i][j] += Math.min(mat[i - 1][j],
						Math.min(mat[i - 1][Math.max(j - 1, 0)], mat[i - 1][Math.min(n - 1, j + 1)]));
				if (i == m - 1 && (mat[i][j] < minFallingPathSum)) {
					minFallingPathSum = mat[i][j];
				}
			}
		}
		return minFallingPathSum;
	}

	// ***Stock problems***

	// 35. Best time to buy and sell stock
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock
	/*
	 * prices[i] is the price of a given stock on i-th day. You want to maximize
	 * your profit by choosing a single day to buy one stock and choosing a
	 * different day in the future to sell that stock. Return the maximum profit
	 * you can achieve from this transaction. If you cannot achieve any profit,
	 * return 0. Analysis: In the general scenario of the problem, k = 1.
	 * Recurrence: T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
	 * T[i][1][1] = max(T[i-1][1][1], T[i-1][0][0] - prices[i]) =
	 * max(T[i-1][1][1], -prices[i]) (as T[i-1][0][0] = 0 from base case)
	 */
	// Time - O(n), Space - O(1)
	public int maxProfitFromBuyAndSellSingleTransaction(int[] prices) {
		var ti10 = 0;
		var ti11 = Integer.MIN_VALUE;
		for (final var price : prices) {
			ti10 = Math.max(ti10, ti11 + price);
			ti11 = Math.max(ti11, -price);
		}
		return ti10;
	}

	// 36. Best time to buy and sell stock II
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
	/*
	 * prices[i] is the price of a given stock on i-th day. On each day, you may
	 * decide to buy and/or sell the stock. You can only hold at most one share
	 * of the stock at any time. However, you can buy it then immediately sell
	 * it on the same day. Find and return the maximum profit you can achieve.
	 * Analysis: In the general scenario of the problem, k = +Infinity. If k is
	 * infinity, k-1 and k do not really make a difference. Recurrence:
	 * T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i]) T[i][k][1] =
	 * max(T[i-1][k][1], T[i-1][k][0] - prices[i]) (we have taken advantage of
	 * the fact that T[i-1][k-1][0] = T[i-1][k][0])
	 */
	// Time - O(n), Space - O(1)
	public int maxProfitFromBuyAndSellInfiniteTransactions(int[] prices) {
		var tik0 = 0;
		var tik1 = Integer.MIN_VALUE;
		for (final var price : prices) {
			final var tik0Prev = tik0;
			tik0 = Math.max(tik0, tik1 + price);
			tik1 = Math.max(tik1, tik0Prev - price);
		}
		return tik0;
	}

	// 37. Best time to buy and sell stock III
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii
	/*
	 * prices[i] is the price of a given stock on i-th day. Find the maximum
	 * profit you can achieve. You may complete at most two transactions.
	 * Analysis: In the general scenario of the problem, k = 2. So we would have
	 * 4 options in a day Recurrence: T[i][2][0] = max(T[i-1][2][0],
	 * T[i-1][2][1] + prices[i]) T[i][2][1] = max(T[i-1][2][1], T[i-1][1][0] -
	 * prices[i]) T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
	 * T[i][1][1] = max(T[i-1][1][1], T[i-1][0][0] - prices[i]) =
	 * max(T[i-1][1][1], -prices[i])
	 */
	// Time - O(n), Space - O(1)
	public int maxProfitFromBuyAndSellAtMostTwoTransactions(int[] prices) {
		var ti20 = 0;
		var ti21 = Integer.MIN_VALUE;
		var ti10 = 0;
		var ti11 = Integer.MIN_VALUE;
		for (final var price : prices) {
			ti20 = Math.max(ti20, ti21 + price);
			ti21 = Math.max(ti21, ti10 - price);
			ti10 = Math.max(ti10, ti11 + price);
			ti11 = Math.max(ti11, -price);
		}
		return ti20;
	}

	// 38. Best time to buy and sell stock IIII
	/*
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv
	 * prices[i] is the price of a given stock on i-th day. Find the maximum
	 * profit you can achieve. You may complete at most k transactions. Note:
	 * You may not engage in multiple transactions simultaneously (i.e., you
	 * must sell the stock before you buy again). Analysis*: This is the most
	 * general case so on each day we need to update all the maximum profits
	 * with different k values corresponding to 0 or 1 stocks in hand at the end
	 * of the day. However, there is a minor optimization we can do if k exceeds
	 * some critical value, beyond which the maximum profit will no long depend
	 * on the number of allowable transactions but instead will be bound by the
	 * number of available stocks (length of the prices array). Let's figure out
	 * what this critical value will be. A profitable transaction takes at least
	 * two days (buy at one day and sell at the other, provided the buying price
	 * is less than the selling price). If the length of the prices array is n,
	 * the maximum number of profitable transactions is n/2 (integer division).
	 * After that no profitable transaction is possible, which implies the
	 * maximum profit will stay the same. Therefore the critical value of k is
	 * n/2. If the given k is no less than this value, i.e., k >= n/2, we can
	 * extend k to positive infinity and the problem is equivalent to Case II
	 * (problem 36 above)
	 */
	// Time - O(k*n), Space - O(n)
	public int maxProfitFromBuyAndSellAtMostKTransactions(int[] prices, int k) {
		final var n = prices.length;
		if (k >= (n >> 2)) {
			var tik0 = 0;
			var tik1 = Integer.MIN_VALUE;
			for (final var price : prices) {
				final var tik0Old = tik0;
				tik0 = Math.max(tik0, tik1 + price);
				tik1 = Math.max(tik1, tik0Old - price);
			}
			return tik0;
		}

		final var tik0 = new int[k + 1];
		final var tik1 = new int[k + 1];
		Arrays.fill(tik1, Integer.MIN_VALUE);

		for (final var price : prices) {
			for (var j = k; j > 0; j--) {
				tik0[j] = Math.max(tik0[j], tik1[j] + price);
				tik1[j] = Math.max(tik1[j], tik0[j - 1] - price);
			}
		}
		return tik0[k];
	}

	// 39. Best time to buy and sell stock with cooldown
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
	/*
	 * prices[i] is the price of a given stock on i-th day. Find the maximum
	 * profit you can achieve. You may complete as many transactions as you like
	 * (i.e., buy one and sell one share of the stock multiple times) with the
	 * following restrictions: After you sell your stock, you cannot buy stock
	 * on the next day (i.e., cooldown one day). Note: You may not engage in
	 * multiple transactions simultaneously (i.e., you must sell the stock
	 * before you buy again). Analysis: This is similar to Problem II with k =
	 * +Infinity except now the recurrence relations have to be modified
	 * slightly to account for the "cooldown" requirement. The original
	 * recurrence relations for Case II are given by T[i][k][0] =
	 * max(T[i-1][k][0], T[i-1][k][1] + prices[i]) T[i][k][1] =
	 * max(T[i-1][k][1], T[i-1][k][0] - prices[i]). But with "cooldown", we
	 * cannot buy on the i-th day if a stock is sold on the (i-1)-th day.
	 * Therefore, in the second equation above, instead of T[i-1][k][0], we
	 * should actually use T[i-2][k][0] if we intend to buy on the i-th day.
	 * Everything else remains the same and the new recurrence relations are
	 * T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i]) T[i][k][1] =
	 * max(T[i-1][k][1], T[i-2][k][0] - prices[i])
	 */
	// Time - O(n), Space - O(1)
	public int maxProfitFromBuyAndSellInfiniteTransactionsWithCooldown(int[] prices) {
		var tik0PrevPrev = 0;
		var tik0 = 0;
		var tik1 = Integer.MIN_VALUE;
		for (final var price : prices) {
			final var tik0Prev = tik0;
			tik0 = Math.max(tik0, tik1 + price);
			tik1 = Math.max(tik1, tik0PrevPrev - price);
			tik0PrevPrev = tik0Prev;
		}
		return tik0;
	}

	// 40. Best time to buy and sell stock with transaction fee
	/*
	 * prices[i] is the price of a given stock on i-th day and an integer fee
	 * representing a transaction fee.Find the maximum profit you can achieve.
	 * You may complete as many transactions as you like, but you need to pay
	 * the transaction fee for each transaction. Note: You may not engage in
	 * multiple transactions simultaneously (i.e., you must sell the stock
	 * before you buy again). Analysis: This is similar to Problem II with k =
	 * +Infinity except now the recurrence relations have to be modified
	 * slightly to account for the "transaction fee" requirement. The original
	 * recurrence relations for Case II are given by T[i][k][0] =
	 * max(T[i-1][k][0], T[i-1][k][1] + prices[i]) T[i][k][1] =
	 * max(T[i-1][k][1], T[i-1][k][0] - prices[i]). Since now we need to pay
	 * some fee (denoted as fee) for each transaction made, the profit after
	 * buying or selling the stock on the i-th day should be subtracted by this
	 * amount, therefore the new recurrence relations will be either T[i][k][0]
	 * = max(T[i-1][k][0], T[i-1][k][1] + prices[i]) T[i][k][1] =
	 * max(T[i-1][k][1], T[i-1][k][0] - prices[i] - fee) or T[i][k][0] =
	 * max(T[i-1][k][0], T[i-1][k][1] + prices[i] - fee) T[i][k][1] =
	 * max[T[i-1][k][1], T[i-1][k][0] - prices[i]) Time - O(n), Space - O(1)
	 */
	public int maxProfitFromBuyAndSellInfiniteTransactionsWithFee(int[] prices, int fee) {
		var tik0 = 0;
		var tik1 = Integer.MIN_VALUE;
		for (final var price : prices) {
			final var tik0Prev = tik0;
			tik0 = Math.max(tik0, tik1 + price);
			tik1 = Math.max(tik1, tik0Prev - price - fee);
		}
		return tik0;
	}

	// 41. Paint house I
	// https://leetcode.com/problems/paint-house/
	/*
	 * There is a row of n houses, where each house can be painted one of three
	 * colors: red, blue, or green. The cost of painting each house with a
	 * certain color is different. You have to paint all the houses such that no
	 * two adjacent houses have the same color. The cost of painting each house
	 * with a certain color is represented by an n x 3 cost matrix costs For
	 * example, costs[0][0] is the cost of painting house 0 with the color red;
	 * costs[1][2] is the cost of painting house 1 with color green, and so
	 * on...Return the minimum cost to paint all houses.
	 */
	// Time - O(n), Space - O(1)
	public int paintHouseI(int[][] costs) {
		final var m = costs.length;
		final var n = costs[0].length;
		if (n != 3) {
			return -1;
		}
		var red = 0;
		var blue = 0;
		var green = 0;
		for (var i = 0; i < m; i++) {
			final var oldRed = red;
			final var oldBlue = blue;
			red = costs[i][0] + Math.min(blue, green);
			blue = costs[i][1] + Math.min(oldRed, green);
			green = costs[i][2] + Math.min(oldRed, oldBlue);
		}
		return Math.min(red, Math.min(blue, green));
	}

	// 42. Paint House II
	// https://leetcode.com/problems/paint-house-ii/
	// https://leetcode.com/problems/paint-house-ii/discuss/69495/Fast-DP-Java-solution-Runtime-O(nk)-space-O(1)
	// **Generalization of problem above**
	/*
	 * There are a row of n houses, each house can be painted with one of the k
	 * colors. The cost of painting each house with a certain color is
	 * different. You have to paint all the houses such that no two adjacent
	 * houses have the same color. The cost of painting each house with a
	 * certain color is represented by an n x k cost matrix costs. For example,
	 * costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is
	 * the cost of painting house 1 with color 2, and so on...Return the minimum
	 * cost to paint all houses.
	 */
	// Time - O(n*k), Space - O(1)
	public int paintHouseII(int[][] costs) {
		final var n = costs.length;
		final var k = costs[0].length;
		var prevMin = 0;
		var prevSecondMin = 0;
		var prevColor = 0;
		// Explanation: dp[i][j] represents the min paint cost from house 0 to
		// house i when house i use color j; The formula will be dp[i][j] =
		// Math.min(any k!= j| dp[i-1][k]) + costs[i][j].

		// Take a closer look at the formula, we don't need an array to
		// represent dp[i][j], we only need to know the min cost to the previous
		// house of any color and if the color j is used on previous house to
		// get prev min cost, use the second min cost that are not using color j
		// on the previous house. So I have three variable to record: prevMin,
		// prevMinColor, prevSecondMin. and the above formula will be translated
		// into: dp[currentHouse][currentColor] = (currentColor == prevMinColor?
		// prevSecondMin: prevMin) + costs[currentHouse][currentColor].
		for (var i = 0; i < n; i++) {
			var minValue = Integer.MAX_VALUE;
			var minPrevValue = Integer.MAX_VALUE;
			var minColor = -1;
			for (var j = 0; j < k; j++) {
				final int value = costs[i][j] + ((prevColor == j) ? prevSecondMin : prevMin);
				if (minColor < 0) {
					minValue = value;
					minColor = j;
				} else if (value < minValue) {
					minPrevValue = minValue;
					minValue = value;
					minColor = j;
				} else if (value < minPrevValue) {
					minPrevValue = value;
				}
			}
			prevSecondMin = minPrevValue;
			prevMin = minValue;
			prevColor = minColor;
		}
		return prevMin;
	}

	// 43, Paint House III
	// https://leetcode.com/problems/paint-house-iii/
	/*
	 * There is a row of m houses in a small city, each house must be painted
	 * with one of the n colors (labeled from 1 to n), some houses that have
	 * been painted last summer should not be painted again. A neighborhood is a
	 * maximal group of continuous houses that are painted with the same color.
	 * For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1},
	 * {2,2}, {3,3}, {2}, {1,1}] Given an array houses, an m x n matrix cost and
	 * an integer target where: houses[i]: is the color of the house i, and 0 if
	 * the house is not painted yet. cost[i][j]: is the cost of paint the house
	 * i with the color j + 1. Return the minimum cost of painting all the
	 * remaining houses in such a way that there are exactly target
	 * neighborhoods. If it is not possible, return -1.
	 */
	// Time - O(m*t*n^2) Space - O(m*t*n)
	public int paintHouseIII(int[] houses, int[][] costs, int target) {
		final int m = houses.length;
		final int n = costs[0].length;
		final Integer[][][] memo = new Integer[m][n + 1][target + 1];
		final int MAX = (int) 1e8;
		final int value = paintHouseDfs(houses, costs, 0, 0, target, memo, MAX);
		return value >= MAX ? -1 : value;
	}

	private int paintHouseDfs(int[] houses, int[][] costs, int houseIndex, int prevColor, int target,
			Integer[][][] memo, int MAX) {
		final var m = houses.length;
		final var n = costs[0].length;
		if (houseIndex > m - 1 || target < 0) {
			return target == 0 ? 0 : MAX;
		}
		if (memo[houseIndex][prevColor][target] != null) {
			return memo[houseIndex][prevColor][target];
		}
		if (houses[houseIndex] != 0) {
			final var currentColor = houses[houseIndex];
			return memo[houseIndex][prevColor][target] = paintHouseDfs(houses, costs, houseIndex + 1, currentColor,
					target - ((prevColor != currentColor) ? 1 : 0), memo, MAX);
		}
		int minValue = MAX;
		for (var currentColor = 1; currentColor <= n; currentColor++) {
			final var value = paintHouseDfs(houses, costs, houseIndex + 1, currentColor,
					target - ((prevColor != currentColor) ? 1 : 0), memo, MAX);
			minValue = Math.min(minValue, value + costs[houseIndex][currentColor - 1]);
		}
		return memo[houseIndex][prevColor][target] = minValue;
	}

	// 44. Count Vowels permutation
	// https://leetcode.com/problems/count-vowels-permutation/
	/*
	 * Given an integer n, your task is to count how many strings of length n
	 * can be formed under the following rules: Each character is a lower case
	 * vowel ('a', 'e', 'i', 'o', 'u') Each vowel 'a' may only be followed by an
	 * 'e'. Each vowel 'e' may only be followed by an 'a' or an 'i'. Each vowel
	 * 'i' may not be followed by another 'i'. Each vowel 'o' may only be
	 * followed by an 'i' or a 'u'. Each vowel 'u' may only be followed by an
	 * 'a'. Since the answer may be too large, return it modulo 10^9 + 7.
	 */
	public int countVowelsPermutation(int n) {
		final int a = 0, e = 1, i = 2, o = 3, u = 4;
		final var vowelLength = 5;
		if (n == 1) {
			return 5;
		}
		var dp = new long[vowelLength];
		var prevDp = new long[vowelLength];
		Arrays.fill(prevDp, 1);
		final int mod = (int) (Math.pow(10, 9) + 7);
		/*
		 * a -> e; e -> a,i; i !-> i => a,e,o,u; o -> i,u; u -> a
		 */

		for (var j = 1; j < n; j++) {
			dp[a] = (prevDp[e] + prevDp[i] + prevDp[u]) % mod;
			dp[e] = (prevDp[a] + prevDp[i]) % mod;
			dp[i] = (prevDp[e] + prevDp[o]) % mod;
			dp[o] = (prevDp[i]) % mod;
			dp[u] = (prevDp[i] + prevDp[o]) % mod;
			final long[] temp = dp;
			dp = prevDp;
			prevDp = temp;
		}
		return (int) ((prevDp[a] + prevDp[e] + prevDp[i] + prevDp[o] + prevDp[u]) % mod);
	}

	// 45. Maximum length of repeated subarray
	// https://leetcode.com/problems/maximum-length-of-repeated-subarray
	/*
	 * Given two integer arrays nums1 and nums2, return the maximum length of a
	 * subarray that appears in both arrays.
	 */
	// Time - O(n*m) Space - O(m)
	public int maxLengthOfRepeatedSubArray(int[] nums1, int[] nums2) {
		final var m = nums1.length;
		final var n = nums2.length;
		final var dp = new int[n + 1];
		var max = Integer.MIN_VALUE;
		// Reduce 2D DP into 1D DP using link below
		// https://stackoverflow.com/questions/17246670/0-1-knapsack-dynamic-programming-optimization-from-2d-matrix-to-1d-matrix
		// You can compare how the recurrence is calculated in method1 &
		// method3. dp[j] in 1d array (method1) should correspond to dp[i-1][j]
		// in 2d array (method3) not dp[i][j] i.e. we need the value of dp[j]
		// from the last iteration of the i-loop not the current iteration.
		// Also, note that since all w[i] are +ve, j-w[i] < j - i.e. we only
		// read from slots to the left of the one we're writing to, never read
		// from the right. We can exploit this to reduce from 2 rows to 1 row
		// while still being able to read the value from previous iteration of
		// the i-loop by just reversing the j-loop.
		for (var i = m - 1; i >= 0; i--) {
			for (var j = 0; j < n; j++) {
				if (nums1[i] == nums2[j]) {
					dp[j] = 1 + dp[j + 1];
					max = Math.max(max, dp[j]);
				} else {
					dp[j] = 0;
				}
			}
		}
		return max;
	}

	// 46. Sum of dice rolls with target sum
	// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum
	// combinatorial solution:
	// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/discuss/1744362/Simple-Python-using-math-module-(better-than-99.7)
	// Reference:
	// https://math.stackexchange.com/questions/2290090/probability-that-the-sum-of-k-dice-is-n
	/*
	 * You have n dice and each die has k faces numbered from 1 to k. Given
	 * three integers n, k, and target, return the number of possible ways (out
	 * of the kn total ways) to roll the dice so the sum of the face-up numbers
	 * equals target. Since the answer may be too large, return it modulo 109 +7
	 */
	// Time - O(n*k*target), Space - O(target)
	public int numberOfDiceRollsToTarget(int n, int k, int target) {
		final var dp = new int[target + 1];
		final var mod = (int) Math.pow(10, 9) + 7;
		dp[0] = 1;
		for (var i = 0; i < n; i++) {
			for (var total = target; total >= 0; total--) {
				dp[total] = 0;
				for (var diceFaceIndex = 1; diceFaceIndex <= k; diceFaceIndex++) {
					if (total >= diceFaceIndex) {
						dp[total] = (dp[total] + dp[total - diceFaceIndex]) % mod;
					} else {
						break;
					}
				}
			}
		}
		return dp[target] % mod;
	}

	// 47. Number of tilings
	// https://leetcode.com/problems/domino-and-tromino-tiling
	/*
	 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape.
	 * You may rotate these shapes. {–,|_}. Given an integer n, return the
	 * number of ways to tile an 2 x n board. Since the answer may be very
	 * large, return it modulo 109 + 7. In a tiling, every square must be
	 * covered by a tile. Two tilings are different if and only if there are two
	 * 4-directionally adjacent cells on the board such that exactly one of the
	 * tilings has both squares occupied by a tile. 1 <= n <= 1000
	 */
	// https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3
	// Time - O(n), Space - O(n)
	public int numberOfTilings(int n) {
		final var dp = new long[1000 + 1];
		final int mod = (int) Math.pow(10, 9) + 7;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 5;
		if (n <= 3) {
			return (int) dp[n];
		}
		for (var i = 4; i <= n; i++) {
			dp[i] = (2 * dp[i - 1] + dp[i - 3]) % mod;
		}
		return (int) (dp[n] % mod);
	}

	// 48. Minimum cost of the tickets
	// https://leetcode.com/problems/minimum-cost-for-tickets/
	/*
	 * You have planned some train traveling one year in advance. The days of
	 * the year in which you will travel are given as an integer array days.
	 * Each day is an integer from 1 to 365. Train tickets are sold in three
	 * different ways: a 1-day pass is sold for costs[0] dollars, a 7-day pass
	 * is sold for costs[1] dollars, and a 30-day pass is sold for costs[2]
	 * dollars. The passes allow that many days of consecutive travel. For
	 * example, if we get a 7-day pass on day 2, then we can travel for 7 days:
	 * 2, 3, 4, 5, 6, 7, and 8. Return the minimum number of dollars you need to
	 * travel every day in the given list of days.
	 */
	// Time - O(n), Space - O(n)
	public int minCostTickets(int[] days, int[] costs) {
		final var n = days.length;
		final var maxDay = days[n - 1];
		var travelDay = 0;
		final var dp = new int[maxDay + 1];
		dp[0] = 0;
		for (var i = days[0]; i <= maxDay; i++) {
			if (travelDay < n && i != days[travelDay]) {
				dp[i] = dp[i - 1];
			} else {
				dp[i] = Math.min(dp[i - 1] + costs[0],
						Math.min(dp[Math.max(0, i - 7)] + costs[1], dp[Math.max(0, i - 30)] + costs[2]));
				travelDay++;
			}
		}
		return dp[maxDay];
	}

	// 49. Matchsticks to square
	// Return true if you can make this square and false otherwise.
	/*
	 * You are given an integer array matchsticks where matchsticks[i] is the
	 * length of the ith matchstick. You want to use all the matchsticks to make
	 * one square. You should not break any stick, but you can link them up, and
	 * each matchstick must be used exactly one time. Return true if you can
	 * make this square and false otherwise.
	 */
	// Time - O(n*2^n), Space - O(n + 2^n)
	public boolean canMakeSquare(int[] arr) {
		final var n = arr.length;
		var sum = 0;
		for (final var num : arr) {
			sum += num;
		}
		if (sum % 4 != 0) {
			return false;
		}
		// sorting the array and iterating it in desc order to put the largest
		// values in subsets first and then move to the smallest for efficient
		// juggling between the subsets
		Arrays.sort(arr);
		final var sidesLength = new int[4];
		return canMakeSquare(arr, sidesLength, n - 1, sum / 4);
	}

	private boolean canMakeSquare(int[] arr, int[] sidesLength, int index, int target) {
		if (index < 0) {
			for (var i = 0; i < sidesLength.length - 1; i++) {
				if (sidesLength[i] != sidesLength[i + 1]) {
					return false;
				}
			}
			return true;
		}
		for (var i = 0; i < sidesLength.length; i++) {
			if (sidesLength[i] + arr[index] > target) {
				continue;
			}
			var j = i;
			while (--j >= 0) {
				if (sidesLength[i] == sidesLength[j]) {
					break;
				}
			}
			if (j != -1) {
				continue;
			}
			sidesLength[i] += arr[index];
			if (canMakeSquare(arr, sidesLength, index - 1, target)) {
				return true;
			}
			sidesLength[i] -= arr[index];
		}
		return false;
	}

	// 50. Partition into K equal sum subsets
	// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
	/*
	 * Given an integer array nums and an integer k, return true if it is
	 * possible to divide this array into k non-empty subsets whose sums are all
	 * equal.
	 */
	// Time - O(n*2^n), Space - O(n + 2^n)
	public boolean canPartitionIntoKEqualSubsetSum(int[] arr, int k) {
		final var n = arr.length;
		var sum = 0;
		for (final var num : arr) {
			sum += num;
		}
		if (sum % k != 0) {
			return false;
		}
		Arrays.sort(arr);
		final var subsetLengths = new int[k];
		return canPartitionIntoKEqualSubsetSum(arr, subsetLengths, n - 1, sum / k);
	}

	private boolean canPartitionIntoKEqualSubsetSum(int[] arr, int[] subsetLengths, int index, int target) {
		if (index < 0) {
			for (var i = 0; i < subsetLengths.length - 1; i++) {
				if (subsetLengths[i] != subsetLengths[i + 1]) {
					return false;
				}
			}
			return true;
		}
		for (var i = 0; i < subsetLengths.length; i++) {
			if (subsetLengths[i] + arr[index] > target) {
				continue;
			}
			var j = i;
			while (--j >= 0) {
				if (subsetLengths[i] == subsetLengths[j]) {
					break;
				}
			}
			if (j != -1) {
				continue;
			}
			subsetLengths[i] += arr[index];
			if (canPartitionIntoKEqualSubsetSum(arr, subsetLengths, index - 1, target)) {
				return true;
			}
			subsetLengths[i] -= arr[index];
		}
		return false;
	}

	// 51. Interleaving string
	// https://leetcode.com/problems/interleaving-string/
	/*
	 * Given strings s1, s2, and s3, find whether s3 is formed by an
	 * interleaving of s1 and s2. An interleaving of two strings s and t is a
	 * configuration where s and t are divided into n and m non-empty substrings
	 * respectively, such that: s = s1 + s2 + ... + sn t = t1 + t2 + ... + tm |n
	 * - m| <= 1 The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 +
	 * s1 + t2 + s2 + t3 + s3 + ... Note: a + b is the concatenation of strings
	 * a and b.
	 */
	// Time - O(m*n), Space - O(n)
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		// final int[][] memo = new int[s1.length()][s2.length()];
		// for (final int[] row : memo) {
		// Arrays.fill(row, -1);
		// }
		// return isInterleave(s1, 0, s2, 0, s3, 0, memo);
		final var m = s1.length();
		final var n = s2.length();
		final var dp = new boolean[n + 1];
		for (var i = 0; i <= m; i++) {
			for (var j = 0; j <= n; j++) {
				if (i == 0 && j == 0) {
					dp[j] = true;
				} else if (i == 0) {
					dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
				} else if (j == 0) {
					dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
				} else {
					dp[j] = (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
							|| (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
				}
			}
		}
		return dp[n];
	}

	private boolean isInterleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
		if (i == s1.length()) {
			return s2.substring(j).equals(s3.substring(k));
		}
		if (j == s2.length()) {
			return s1.substring(i).equals(s3.substring(k));
		}
		if (memo[i][j] != -1) {
			return (memo[i][j] == 1 ? true : false);
		}
		boolean answer = false;
		if ((s3.charAt(k) == s1.charAt(i) && isInterleave(s1, i + 1, s2, j, s3, k + 1, memo))
				|| (s3.charAt(k) == s2.charAt(j) && isInterleave(s1, i, s2, j + 1, s3, k + 1, memo))) {
			answer = true;
		}
		memo[i][j] = (answer) ? 1 : 0;
		return answer;
	}

}

class DPUtils {
	public static void printNewLine() {
		System.out.println();
	}

	public static void printArray(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	public static void printMatrix(int[][] mat) {
		for (var i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
	}
}