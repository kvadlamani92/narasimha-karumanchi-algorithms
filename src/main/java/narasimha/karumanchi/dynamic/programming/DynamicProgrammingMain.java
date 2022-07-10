package narasimha.karumanchi.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

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
 *  2. guessing => where to end first line, say i : j => # choices = n − i = O(n)
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
 */
public class DynamicProgrammingMain {
	public static void main(String[] args) {
		final var dpMain = new DynamicProgrammingMain();
		// dpMain.runComputeNthFibonaciiMemoized();
		// dpMain.runComputeNthFibonaciiBottomUp();
		dpMain.runComputeNthFibonaciiThreeVariables();
		dpMain.runComputeNthFibonaciiMath();
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
		System.out.println(computeNthFibMath(93));
	}

	// Algorithms
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
}
