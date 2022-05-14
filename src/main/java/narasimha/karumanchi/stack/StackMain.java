package narasimha.karumanchi.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class StackMain {
	public static void main(String[] args) {
		final var stackMain = new StackMain();
		// stackMain.runIsBalanced();
		// stackMain.runConvertInfixToPostfix();
		// stackMain.runConvertInfixToPrefix();
		// stackMain.runConvertPrefixToPostfix();
		// stackMain.runEvaluatePostfixExpression();
		// stackMain.runEvaluateInfixExpression();
		// stackMain.runEvaluatePrefixExpression();
		// stackMain.runFindSpansOfArray();
		// stackMain.runMaxAreaInAHIstogram();
		stackMain.runSortStack();
	}

	// ....Stack Runners....
	// 1. Run isBalanced
	public void runIsBalanced() {
		final String str = "(A+B) + {C+D";
		System.out.println(isBalanced(str));
	}

	// 2. Convert infix to postfix
	public void runConvertInfixToPostfix() {
		final String str = "A*B-(C+D)+E";
		System.out.println(convertInfixToPostfix(str));
	}

	// 3. Convert infix to prefix
	public void runConvertInfixToPrefix() {
		final String str = "A*B-(C+D)+E";
		System.out.println(convertInfixToPrefix(str));
	}

	// 4. Convert prefix to postfix
	public void runConvertPrefixToPostfix() {
		final String str = "+-*AB+CDE";
		System.out.println(convertPrefixToPostfix(str));
	}

	// 5. Evaluate postfix expression
	public void runEvaluatePostfixExpression() {
		final String str = "123*+5-";
		System.out.println(evaluatePostFixExpression(str));
	}

	// 6. Evaluate infix expression
	public void runEvaluateInfixExpression() {
		String str = "1+(2*3)-5";
		System.out.println(evaluateInfixExpression(str));
		str = "2*(5*(3+6))/5-2";
		System.out.println(evaluateInfixExpression(str));
	}

	// 7. Evaluate prefix expression
	public void runEvaluatePrefixExpression() {
		final String str = "-/*2*5+3652";
		System.out.println(evaluatePrefixExpression(str));
	}

	// 8. Find spans of array
	public void runFindSpansOfArray() {
		final int[] arr = { 6, 3, 4, 5, 2 };
		System.out.println(Arrays.toString(findSpansOfArray(arr)));
	}

	// 9. Max area of a rectangle in a histogram
	public void runMaxAreaInAHIstogram() {
		final int[] arr = { 3, 2, 5, 6, 1, 4, 4 };
		System.out.println(maxAreaInAHistogram(arr));
	}

	// 10. Sort stack
	public void runSortStack() {
		final int[] arr = { 3, 2, 5, 6, 1, 4, 4 };
		System.out.println(Arrays.toString(sortStack(arr)));
	}

	// ....Algorithms....
	// 1. Check if a String has balanced parenthesis
	public boolean isBalanced(String str) {
		if (str == null) {
			return true;
		}
		final Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		final Stack<Character> stack = new Stack<>();
		for (final char c : str.toCharArray()) {
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else if (c == ')' || c == ']' || c == '}') {
				if (stack.empty()) {
					return false;
				}
				final char poppedChar = stack.pop();
				if (poppedChar != map.get(c)) {
					return false;
				}
			}
		}
		return stack.empty();
	}

	// 2. Infix to postfix conversion eg: A*B-(C+D)+E -> AB*CD+-E+
	/*
	 * If c is an operand { Output c } Else if c is a right parentheses { Pop
	 * and output tokens until a left parentheses is popped } Else { // c is an
	 * operator or left parentheses Pop and output tokens until one of the lower
	 * priorities than c are encountered, or a left parentheses is encountered,
	 * or the stack is empty. Push c }
	 * https://www.codingninjas.com/blog/2021/09/06/infix-postfix-and-prefix-
	 * conversion/#Definition_of_Infix_Postfix_and_Prefix
	 */
	public String convertInfixToPostfix(String str) {
		if (str == null) {
			return str;
		}
		final Stack<Character> stack = new Stack<>();
		final var sb = new StringBuilder();
		for (final char c : str.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				while (!stack.empty() && stack.peek() != '(') {
					sb.append(stack.pop()); // pop operators from the stack
											// until we encounter ( or stack is
											// empty
				}
				if (!stack.empty() && stack.peek() == '(') {
					stack.pop(); // remove '('
				}
			} else {
				while (!stack.empty() && getPreference(c) <= getPreference(stack.peek())) {
					sb.append(stack.pop());
				}
				stack.push(c);
			}
		}
		while (!stack.empty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}

	// 3. Infix to prefix conversion eg: A*B-(C+D)+E -> +-*AB+CDE
	/*
	 * To convert an infix to postfix expression refer to this article Stack |
	 * Set 2 (Infix to Postfix)[url:
	 * https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/]. We use the
	 * same to convert Infix to Prefix. Step 1: Reverse the infix expression i.e
	 * A+B*C will become C*B+A. Note while reversing each ‘(‘ will become ‘)’
	 * and each ‘)’ becomes ‘(‘. Step 2: Obtain the “nearly” postfix expression
	 * of the modified expression i.e CB*A+. Step 3: Reverse the postfix
	 * expression. Hence in our example prefix is +A*BC.
	 */
	public String convertInfixToPrefix(String str) {
		if (str == null) {
			return str;
		}
		final Stack<Character> stack = new Stack<>();
		var reversedStr = new StringBuilder(str).reverse().toString();
		final char[] reversedStrCharArray = reversedStr.toCharArray();

		// replace ( with ) and viceversa as they are also reversed when
		// reversing the string above
		for (var i = 0; i < reversedStrCharArray.length; i++) {
			if (reversedStrCharArray[i] == '(') {
				reversedStrCharArray[i] = ')';
			} else if (reversedStrCharArray[i] == ')') {
				reversedStrCharArray[i] = '(';
			}
		}
		reversedStr = String.valueOf(reversedStrCharArray);

		final var sb = new StringBuilder();
		for (final char c : reversedStr.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				while (!stack.empty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				if (!stack.empty() && stack.peek() == '(') {
					stack.pop();
				}
			} else {
				while (!stack.empty() && (getPreference(c) < getPreference(stack.peek())
						|| getPreference(c) <= getPreference(stack.peek()) && stack.peek() == '^')) {
					sb.append(stack.pop());
				}
				stack.push(c);
			}
		}
		while (!stack.empty()) {
			sb.append(stack.pop());
		}

		return sb.reverse().toString();
	}

	// 4. Convert Prefix to postfix expression
	// https://www.codingninjas.com/blog/2021/09/06/infix-postfix-and-prefix-conversion/#Definition_of_Infix_Postfix_and_Prefix
	public String convertPrefixToPostfix(String str) {
		if (str == null) {
			return str;
		}
		final Stack<String> operandStack = new Stack<>();
		final var reversedString = new StringBuilder(str).reverse().toString();
		System.out.println("Reversed prefix string: " + reversedString);
		for (final char c : reversedString.toCharArray()) {
			if (!isOperator(c)) {
				operandStack.push(String.valueOf(c));
			} else {
				final char operator = c;
				final String a = operandStack.pop(); // it is a here instead of
														// b as we are
														// traversing the
														// reversed string
				final String b = operandStack.pop();
				final String result = a + b + operator;
				operandStack.push(result);
			}
		}
		return operandStack.pop();
	}

	private boolean isOperator(char c) {
		return (c == '*' || c == '/' || c == '+' || c == '-');
	}

	private int getPreference(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}

	// 5. Evaluate postfix expression ex: 123*+5- -> returns 2
	public int evaluatePostFixExpression(String str) {
		if (str == null) {
			return -1;
		}
		final Set<Character> operators = new HashSet<>();
		operators.add('+');
		operators.add('-');
		operators.add('*');
		operators.add('/');

		final Stack<Integer> stack = new Stack<>();
		for (final char c : str.toCharArray()) {
			if (Character.isDigit(c)) {
				stack.push(c - '0');
			} else if (!stack.empty() && operators.contains(c)) {
				final int b = stack.pop();
				final int a = stack.pop();
				var res = 0;
				if (c == '+') {
					res = a + b;
				} else if (c == '-') {
					res = a - b;
				} else if (c == '*') {
					res = a * b;
				} else if (c == '/' && b != 0) {
					res = a / b;
				}
				stack.push(res);
			}
		}
		return stack.pop();
	}

	// 6. Evaluate infix expression ex: 1+(2*3)-5 -> returns 2
	public int evaluateInfixExpression(String str) {
		if (str == null) {
			return -1;
		}
		final Stack<Character> operatorStack = new Stack<>();
		final Stack<Integer> operandStack = new Stack<>();

		for (final char c : str.toCharArray()) {
			// there are 4 cases for c -> digit, '(' , ')' and operator
			if (Character.isDigit(c)) {
				operandStack.push(c - '0');
			} else if (c == '(') {
				operatorStack.push(c);
			} else if (c == ')') {
				while (!operatorStack.empty() && operatorStack.peek() != '(') {
					final int res = performOperation(operatorStack, operandStack);
					operandStack.push(res);
				}
				if (!operatorStack.empty() && operatorStack.peek() == '(') {
					operatorStack.pop();
				}
			} else {
				// this is when c is an operator -> use the operator preference
				// here and pop elements if the current operator preference is
				// lesser than the one existing on the top of the stack
				while (!operatorStack.empty() && getPreference(c) <= getPreference(operatorStack.peek())) {
					final int res = performOperation(operatorStack, operandStack);
					operandStack.push(res);
				}
				operatorStack.push(c);
			}
		}
		while (!operatorStack.empty()) {
			final int res = performOperation(operatorStack, operandStack);
			operandStack.push(res);
		}
		return operandStack.pop();
	}

	private int performOperation(Stack<Character> operatorStack, Stack<Integer> operandStack) {
		final char operator = operatorStack.pop();
		final int b = operandStack.pop();
		final int a = operandStack.pop();
		var res = 0;
		if (operator == '+') {
			res = a + b;
		} else if (operator == '-') {
			res = a - b;
		} else if (operator == '*') {
			res = a * b;
		} else if (operator == '/' && b != 0) {
			res = a / b;
		}
		return res;
	}

	// 7. Evaluate prefix expression ex: -/*2*5+3652 -> 16
	public int evaluatePrefixExpression(String str) {
		if (str == null) {
			return -1;
		}
		final String reversedStr = new StringBuilder(str).reverse().toString();
		final Stack<Integer> stack = new Stack<>();
		for (final char c : reversedStr.toCharArray()) {
			if (Character.isDigit(c)) {
				stack.push(c - '0');
			} else {
				final int a = stack.pop();
				final int b = stack.pop();
				int res = 0;
				if (c == '+') {
					res = a + b;
				} else if (c == '-') {
					res = a - b;
				} else if (c == '*') {
					res = a * b;
				} else if (c == '/' && b != 0) {
					res = a / b;
				}
				stack.push(res);
			}
		}
		return stack.pop();
	}

	// 8. Finding of Spans: Given an array A and the span S[i] of A[i] is the
	// maximum number of consecutive elements A[j] immediately preceding A[i]
	// and such that A[j]<=A[j+1]
	// span[i] can be calculated if we know the closest day preceding to i that
	// has the value greater than or equal to arr[i]. Let's call that index as
	// P. Then span is defined as span[i] = i - P
	public int[] findSpansOfArray(int[] arr) {
		final int n = arr.length;
		final int[] spans = new int[n];
		final Stack<Integer> stack = new Stack<>();
		for (var i = 0; i < n; i++) {
			while (!stack.empty() && arr[i] >= arr[stack.peek()]) {
				stack.pop();
			}
			int indexOfLargestNumberBeforeCurrentElement = -1;
			if (!stack.empty()) {
				indexOfLargestNumberBeforeCurrentElement = stack.peek();
			}
			spans[i] = i - indexOfLargestNumberBeforeCurrentElement;
			stack.push(i);
		}
		return spans;
	}

	// 9. Max area of a rectangle in a histogram
	public int maxAreaInAHistogram(int[] arr) {
		final int n = arr.length;
		final Stack<Integer> stack = new Stack<>();
		var i = 0;
		var maxArea = 0;
		while (i < n) {
			if (stack.empty() || arr[i] >= arr[stack.peek()]) {
				stack.push(i++); // increment index only when the current
									// element is greater than the top of the
									// stack
			} else { // calculate the areas based on the stack top and the
						// current index
				final int top = stack.pop(); // calculate area based on stack
												// top
				maxArea = Math.max(maxArea, arr[top] * (stack.empty() ? i : (i - stack.peek() - 1)));
			}
		}
		while (!stack.empty()) {
			final int top = stack.pop();
			maxArea = Math.max(maxArea, arr[top] * (stack.empty() ? i : (i - stack.peek() - 1)));
		}
		return maxArea;
	}

	// 10. Sort a stack O(n^2)
	public int[] sortStack(int[] arr) {
		final int n = arr.length;
		final int[] resultArray = new int[n];
		final Stack<Integer> stack = new Stack<>();
		for (final int i : arr) {
			stack.push(i);
		}
		final Stack<Integer> resultStack = new Stack<>();
		while (!stack.empty()) {
			final int temp = stack.pop();
			while (!resultStack.empty() && resultStack.peek() > temp) {
				stack.push(resultStack.pop());
			}
			resultStack.push(temp);
		}
		for (int i = resultArray.length - 1; i >= 0; i--) {
			resultArray[i] = resultStack.pop();
		}
		return resultArray;
	}

}