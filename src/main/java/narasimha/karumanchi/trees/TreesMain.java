package narasimha.karumanchi.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class TreesMain {
	public static void main(String[] args) throws Exception {
		final var treesMain = new TreesMain();

		// *** Traversal ***

		// treesMain.runPreOrderTraversal();
		// treesMain.runInOrderTraversal();
		// treesMain.runPostOrderTraversal();
		// treesMain.runPreOrderIterative();
		// treesMain.runInOrderIterative();
		// treesMain.runPostOrderIterative();
		// treesMain.runPostOrderIterativeMorrisTraversal();
		// treesMain.runLevelOrderTraversal();
		// treesMain.runGetMaxInBinaryTree();
		// treesMain.runGetMaxInBinaryTreeIterative();
		// treesMain.runFind();
		// treesMain.runFindIterative();
		// treesMain.runInsert();
		// treesMain.runInsertIterative();
		// treesMain.runSize();
		// treesMain.runSizeIterative();
		// treesMain.runLevelOrderReversed();
		// treesMain.runHeight();
		// treesMain.runHeightWithStack();
		// treesMain.runHeightWithLevelOrder();
		treesMain.runMinDepth();
		// treesMain.runMinDepthIterative();
		// treesMain.runIsStructurallyIdentical();
		// treesMain.runMaxDiameterInTree();
		// treesMain.runLevelWithMaxSum();
		// treesMain.runFindAllPaths();
		// treesMain.runHasPathSum();
		// treesMain.runSumOfAllNodes();
		// treesMain.runMirror();
		// treesMain.runIsMirror();
		// treesMain.runBuildFromPreAndInOrder();
		// treesMain.runBuildFromPostAndInOrder();
		// treesMain.runBuildFromPreAndPostOrder();
		// treesMain.runFindAncestors();
		// treesMain.runLCA();
		// treesMain.runZigZagTraversal();
		// treesMain.runVerticalOrderTraversal();
		treesMain.runNumberOfBinaryTreesWithNNodes();
		// treesMain.runGenerateAllBinarySearchTrees();
		// treesMain.runConstructTreeFromPreOrderString();
	}

	// ....Trees Runners....

	// *** Traversal runners ***
	// 1. preOrder
	public void runPreOrderTraversal() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = preOrderTraversal(root);
		System.out.println(result);
	}

	// 2. inOrder
	public void runInOrderTraversal() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = inOrderTraversal(root);
		System.out.println(result);
	}

	// 3. postOrder
	public void runPostOrderTraversal() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = postOrderTraversal(root);
		System.out.println(result);
	}

	// 4. preOrder iterative
	public void runPreOrderIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = preOrderIterative(root);
		System.out.println(result);
	}

	// 5. inOrder iterative
	public void runInOrderIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = inOrderIterative(root);
		System.out.println(result);
	}

	// 6. postOrder iterative
	public void runPostOrderIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = postOrderIterative(root);
		System.out.println(result);
	}

	// 7. postOrder iterative morris traversal
	public void runPostOrderIterativeMorrisTraversal() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = postOrderIterativeMorrisTraversal(root);
		System.out.println(result);
	}

	// 8. level Order traversal
	public void runLevelOrderTraversal() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<List<Integer>> result = levelOrderTraversal(root);
		System.out.println(result);
	}

	// 9. Max in binary Tree
	public void runGetMaxInBinaryTree() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(getMaxInBinaryTree(root));
	}

	// 10. Max in binary tree iterative
	public void runGetMaxInBinaryTreeIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(getMaxInBinaryTreeIterative(root));
	}

	// 11. Find in binary tree
	public void runFind() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final TreeNode foundNode = find(root, 10);
		System.out.println(foundNode != null ? foundNode.getData() : -1);
	}

	// 12. Find in binary tree
	public void runFindIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final TreeNode foundNode = findIterative(root, 14);
		System.out.println(foundNode != null ? foundNode.getData() : -1);
	}

	// 13. Insert in binary tree
	public void runInsert() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final TreeNode node = insert(insert(insert(root, 8), 9), 17);
		System.out.println(levelOrderTraversal(node));
	}

	// 14. Insert in binary tree iterative
	public void runInsertIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final TreeNode node = insertIterative(insertIterative(insertIterative(root, 8), 9), 17);
		System.out.println(levelOrderTraversal(node));
	}

	// 15. Size of binary tree
	public void runSize() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(size(root));
	}

	// 16. Size of binary tree iterative
	public void runSizeIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(sizeIterative(root));
	}

	// 17. level Order traversal
	public void runLevelOrderReversed() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final List<Integer> result = levelOrderReversed(root);
		System.out.println(result);
	}

	// 18. Height/Depth of tree
	public void runHeight() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(height(root));
	}

	// 19. Height/Depth of tree with stack
	public void runHeightWithStack() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(heightWithStack(root));
	}

	// 20. Height/Depth of tree with level order
	public void runHeightWithLevelOrder() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(heightWithLevelOrder(root));
	}

	// 21. Min depth
	public void runMinDepth() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(minDepth(root));
	}

	// 22. Min depth iterative
	public void runMinDepthIterative() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(minDepthIterative(root));
	}

	// 23. Is structurally identical
	public void runIsStructurallyIdentical() {
		final TreeNode root1 = TreeUtils.createBinaryTree();
		final TreeNode root2 = TreeUtils.createBinaryTree2();
		System.out.println(isStructurallyIdentical(root1, root2));
	}

	// 24. Max diameter in the tree
	public void runMaxDiameterInTree() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(maxDiameterInTree(root));
	}

	// 25. Level with max sum
	public void runLevelWithMaxSum() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(levelWithMaxSum(root));
	}

	// 26. Find all paths from root
	public void runFindAllPaths() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(findAllPaths(root));
	}

	// 27. Has Path sum
	public void runHasPathSum() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(hasPathSum(root, 4));
	}

	// 28. Sum of all nodes
	// Without recursion - can use level order traversal as well
	public void runSumOfAllNodes() {
		final TreeNode root = TreeUtils.createBinaryTree();
		System.out.println(sumOfAllNodes(root));
	}

	// 29. mirror
	public void runMirror() {
		final TreeNode root = TreeUtils.createBinaryTree();
		mirror(root);
		System.out.println(levelOrderTraversal(root));
	}

	// 30. Is mirror
	public void runIsMirror() {
		final TreeNode root1 = TreeUtils.createBinaryTree();
		final TreeNode root2 = TreeUtils.createBinaryTreeMirror();
		System.out.println(isMirror(root1, root2));
	}

	// 31. Build from pre and in order
	public void runBuildFromPreAndInOrder() {
		final int[] preOrder = { 1, 2, 4, 3, 5, 7, 8, 6 };
		final int[] inOrder = { 4, 2, 1, 7, 5, 8, 3, 6 };
		System.out.println(Arrays.toString(preOrder));
		System.out.println(Arrays.toString(inOrder));
		final TreeNode root = buildFromPreAndInorder(preOrder, inOrder);
		System.out.println(preOrderTraversal(root));
		System.out.println(inOrderTraversal(root));
	}

	// 32. Build from post and in order
	public void runBuildFromPostAndInOrder() {
		final int[] postOrder = { 4, 2, 7, 8, 5, 6, 3, 1 };
		final int[] inOrder = { 4, 2, 1, 7, 5, 8, 3, 6 };
		System.out.println(Arrays.toString(postOrder));
		System.out.println(Arrays.toString(inOrder));
		final TreeNode root = buildFromPostAndInOrder(postOrder, inOrder);
		System.out.println(postOrderTraversal(root));
		System.out.println(inOrderTraversal(root));
	}

	// 32. Build from post and in order
	public void runBuildFromPreAndPostOrder() {
		final int[] preOrder = { 1, 2, 4, 3, 5, 7, 8, 6 };
		final int[] postOrder = { 4, 2, 7, 8, 5, 6, 3, 1 };
		System.out.println(Arrays.toString(preOrder));
		System.out.println(Arrays.toString(postOrder));
		final TreeNode root = buildFromPreAndPostOrder(preOrder, postOrder);
		System.out.println(preOrderTraversal(root));
		System.out.println(postOrderTraversal(root));
	}

	// 33. Find ancesters
	public void runFindAncestors() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final TreeNode node = find(root, 3);
		System.out.println(findAncestors(root, node));
	}

	// 34. Find LCA
	public void runLCA() {
		final TreeNode root = TreeUtils.createBinaryTree();
		final TreeNode node1 = find(root, 2);
		final TreeNode node2 = find(root, 3);
		System.out.println(LCA(root, node1, node2).getData());
	}

	// 35. zigzag traversal
	public void runZigZagTraversal() {
		final TreeNode root = TreeUtils.createBinaryTree3();
		System.out.println(zigZagTraversal(root));
	}

	// 36. Vertical Order Traversal
	public void runVerticalOrderTraversal() {
		final TreeNode root = TreeUtils.createBinaryTree3();
		System.out.println(verticalOrderTraversal(root));
	}

	// 37. Number of binary trees
	public void runNumberOfBinaryTreesWithNNodes() {
		System.out.println(numberOfBinaryTreesWithNNodes(4));
	}

	// 38. Generate all binary search trees from n numbers
	public void runGenerateAllBinarySearchTrees() {
		final List<TreeNode> trees = generateAllBinarySearchTrees(5);
		for (final TreeNode root : trees) {
			System.out.println(printHorizontalTree(root));
			System.out.println(preOrderTraversal(root));
			System.out.println();
		}
	}

	// 39. Run construct tree from preorder string
	public void runConstructTreeFromPreOrderString() {
		final String preOrderString = "ILILL";
		final TreeNode root = constructTreeFromPreOrderString(preOrderString);
		System.out.println(printHorizontalTree(root));
	}

	// ....Algorithms....

	// *** Traversals ***
	// 1. *preorder traversal*
	public List<Integer> preOrderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		return preOrderTraversal(root, result);
	}

	private List<Integer> preOrderTraversal(TreeNode node, List<Integer> result) {
		if (node != null) {
			result.add(node.getData());
			preOrderTraversal(node.getLeft(), result);
			preOrderTraversal(node.getRight(), result);
		}
		return result;
	}

	// 2. *Inorder traversal*
	public List<Integer> inOrderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		return inOrderTraversal(root, result);
	}

	private List<Integer> inOrderTraversal(TreeNode node, List<Integer> result) {
		if (node != null) {
			inOrderTraversal(node.getLeft(), result);
			result.add(node.getData());
			inOrderTraversal(node.getRight(), result);
		}
		return result;
	}

	// 3. *PostOrder traversal*
	public List<Integer> postOrderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		return postOrderTraversal(root, result);
	}

	private List<Integer> postOrderTraversal(TreeNode node, List<Integer> result) {
		if (node != null) {
			postOrderTraversal(node.getLeft(), result);
			postOrderTraversal(node.getRight(), result);
			result.add(node.getData());
		}
		return result;
	}

	// 4. *preOrder iterative*
	public List<Integer> preOrderIterative(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		if (root.getLeft() == null && root.getRight() == null) {
			result.add(root.getData());
			return result;
		}
		final Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.empty()) {
			final TreeNode node = stack.pop();
			result.add(node.getData());
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
		return result;
	}

	// 5. *InOrder iterative*
	// ***** See this for 2 stacks approach:
	// https://momchil-velikov.blogspot.com/2013/10/non-recursive-tree-traversal.html
	// *****
	public List<Integer> inOrderIterative(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		if (root.getLeft() == null && root.getRight() == null) {
			result.add(root.getData());
			return result;
		}
		final Stack<TreeNode> stack = new Stack<>();
		TreeNode currentNode = root;
		while (!stack.empty() || currentNode != null) {
			if (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.getLeft();
			} else {
				currentNode = stack.pop();
				result.add(currentNode.getData());
				currentNode = currentNode.getRight();
			}
		}
		return result;
	}

	// 6. *postOrder iterative - Time O(n), Space - O(n)*
	// ***** See this for 2 stacks approach:
	// https://momchil-velikov.blogspot.com/2013/10/non-recursive-tree-traversal.html
	// *****
	public List<Integer> postOrderIterative(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		if (root.getLeft() == null && root.getRight() == null) {
			result.add(root.getData());
			return result;
		}
		final Stack<TreeNode> stack = new Stack<>();
		// Initialize current with root
		TreeNode currentNode = root;
		// Run the while until the stack becomes empty or the current becomes
		// null
		while (!stack.empty() || currentNode != null) {
			// Push the left nodes onto the stack until the current becomes null
			if (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.getLeft();
				// When current is null, move to the else section
			} else {
				// Initialze a new node with the rightChild of the node present
				// at the top of the stack
				TreeNode previousNode = stack.peek().getRight();
				// If right child Found assign the value of previousNode to the
				// current Node and repeat the process until the stack becomes
				// empty
				if (previousNode != null) {
					currentNode = previousNode;
					// If no rightChild found pop out the node from the stack
					// and print it
				} else {
					previousNode = stack.pop();
					result.add(previousNode.getData());
					// Run a while loop until the previousNode matches with the
					// rightChild of the node present at the top of the stack
					while (!stack.empty() && previousNode == stack.peek().getRight()) {
						previousNode = stack.pop();
						result.add(previousNode.getData());
					}
				}
			}
		}
		return result;
	}

	// 7. *PostOrder iterative morris traversal - Time O(n), Space - O(1)*
	/**
	 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45648/three-ways-of-iterative-postorder-traversing-easy-explanation
	 * <p>
	 * <img src = "./doc-files/morris_traversal_postorder.jpeg" alt =
	 * "alternative directory" />
	 * 
	 * We reverse each diagonal shown in the picture (d1-d4), print it and
	 * re-reverse it.
	 * 
	 * Create a dummy node and make dummy.left = root. root = dummy Iterate till
	 * root is null. If root has a left child, Find the inorder predecessor =>
	 * pre. (Inorder predecessor of root is the right most child of its left
	 * child) pre.right = root (Make it point to root). root = root.left. If its
	 * already pointing to root (which means we have traversed it already and
	 * are on our way up.) Reverse from root.left to pre. Traverse from pre to
	 * root.left and print the nodes. Re-reverse it back to normal. pre.right =
	 * null. root = root.right. If left child is null root = root.right. (We are
	 * climbing up our link.)
	 * 
	 * </p>
	 * 
	 * @param root
	 * @return the list of values
	 */
	public List<Integer> postOrderIterativeMorrisTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		final TreeNode dummy = new TreeNode(-1);
		dummy.setLeft(root);
		root = dummy;
		TreeNode previousNode = null;
		while (root != null) {
			if (root.getLeft() != null) {
				previousNode = root.getLeft();
				while (previousNode.getRight() != null && previousNode.getRight() != root) {
					previousNode = previousNode.getRight();
				}
				if (previousNode.getRight() == null) {
					System.out.println(previousNode.getData() + "->" + root.getData());
					previousNode.setRight(root);
					root = root.getLeft();
					System.out.println("root = " + root.getData());
				} else {
					TreeNode node = previousNode;
					reverseNodes(root.getLeft(), previousNode);
					while (node != root.getLeft()) {
						result.add(node.getData());
						node = node.getRight();
					}
					result.add(node.getData());
					reverseNodes(previousNode, root.getLeft());
					previousNode.setRight(null);
					System.out.println("previous node = " + previousNode.getData() + " root to root.right = "
							+ root.getData() + "->" + root.getRight().getData());
					root = root.getRight();
				}
			} else {
				root = root.getRight();
				System.out.println("root from else = " + root.getData());
			}
		}
		return result;
	}

	private void reverseNodes(TreeNode from, TreeNode to) {
		System.out.println("reversing from " + from.getData() + " to " + to.getData());
		TreeNode prev = from;
		TreeNode cur = from.getRight();
		while (prev != to) {
			final TreeNode next = cur.getRight();
			System.out.println("in reverse: " + cur.getData() + "->" + prev.getData());
			cur.setRight(prev);
			prev = cur;
			cur = next;
		}
	}

	// 8. *Level order traversal*
	public List<List<Integer>> levelOrderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<List<Integer>> result = new ArrayList<>();
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			final int level = queue.size();
			final List<Integer> subList = new ArrayList<>();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				subList.add(node.getData());
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
			result.add(subList);
		}
		return result;
	}

	// 9. *Max in binary tree*
	public int getMaxInBinaryTree(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return root.getData();
		}
		final int leftMax = getMaxInBinaryTree(root.getLeft());
		final int rightMax = getMaxInBinaryTree(root.getRight());
		final int maxInSubLevels = Math.max(leftMax, rightMax);
		return Math.max(root.getData(), maxInSubLevels);
	}

	// 10. *Max in biinary tree iterative*
	// Use level order traversal and find the max value when deQueueing from the
	// queue
	public int getMaxInBinaryTreeIterative(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return root.getData();
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int maxValue = Integer.MIN_VALUE;
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				if (node.getData() > maxValue) {
					maxValue = node.getData();
				}
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
		}
		return maxValue;
	}

	// 11. *Find in binary tree*
	public TreeNode find(TreeNode root, int k) {
		if (root == null) {
			return null;
		}
		if (root.getData() == k) {
			return root;
		}
		final TreeNode left = find(root.getLeft(), k);
		final TreeNode right = find(root.getRight(), k);
		return left != null ? left : right;
	}

	// 12. *Find in binary tree Iterative*
	// use level order traversal and find the node when dequeuing from the queue
	public TreeNode findIterative(TreeNode root, int k) {
		if (root == null) {
			return null;
		}
		if (root.getData() == k) {
			return root;
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				if (node.getData() == k) {
					return node;
				}
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
		}
		return null;
	}

	// 13. *Insert in a binary tree*
	public TreeNode insert(TreeNode root, int data) {
		if (root == null) {
			return new TreeNode(data);
		}
		if (root.getData() < data) {
			root.setRight(insert(root.getRight(), data));
		} else {
			root.setLeft(insert(root.getLeft(), data));
		}
		return root;
	}

	// 14. *Insert iterative*
	public TreeNode insertIterative(TreeNode root, int data) {
		if (root == null) {
			return new TreeNode(data);
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				if (node.getLeft() == null) {
					node.setLeft(new TreeNode(data));
					return root;
				} else {
					queue.offer(node.getLeft());
				}
				if (node.getRight() == null) {
					node.setRight(new TreeNode(data));
					return root;
				} else {
					queue.offer(node.getRight());
				}
			}
		}
		return null;
	}

	// 15. *size of a binary tree - number of descendants of a node including*
	// itself = 1 (itself) + total # of left subtree nodes + right subtree nodes
	public int size(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + size(root.getLeft()) + size(root.getRight());
	}

	// 16. *size of binary tree iterative*
	// Use Level order!
	public int sizeIterative(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		var size = 0;
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
			size += level;
		}
		return size;
	}

	// 17. *Level order in reverse order*
	public List<Integer> levelOrderReversed(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();

		// to reverse the order of elements being dequeued from the queue
		final Stack<TreeNode> stack = new Stack<>();

		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				// enqueue the right elements at a level before the left so that
				// when popped from stack, left sibling comes before the right
				// sibling
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				stack.push(node);
			}
		}
		while (!stack.empty()) {
			result.add(stack.pop().getData());
		}
		return result;
	}

	// 18. *Height/Depth of a binary tree*
	public int height(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 0;
		}
		return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
	}

	// 19. *Height of tree with stacks*
	// https://stackoverflow.com/questions/19886297/finding-max-depth-of-binary-tree-without-recursion
	public int heightWithStack(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 0;
		}
		final Stack<TreeNode> work = new Stack<>();
		final Stack<TreeNode> parents = new Stack<>();
		work.push(root);
		var height = 0;
		while (!work.empty()) {
			final TreeNode node = work.peek();
			if (!parents.empty() && node == parents.peek()) {
				if (parents.size() > height) {
					// height is the number of edges from leaf to the node. So
					// -1 from the number of nodes from leaf to this node
					height = parents.size() - 1;
				}
				parents.pop();
				work.pop();
			} else {
				parents.push(node);
				if (node.getRight() != null) {
					work.push(node.getRight());
				}
				if (node.getLeft() != null) {
					work.push(node.getLeft());
				}
			}
		}
		return height;
	}

	// 20. *Height of tree with levelOrder*
	public int heightWithLevelOrder(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 0;
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int height = -1;
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
			height++;
		}
		return height;
	}

	// 21. *Min depth*
	public int minDepth(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 0;
		}
		return 1 + Math.min(minDepth(root.getLeft()), minDepth(root.getRight()));
	}

	// 22. *Min depth iterative*
	public int minDepthIterative(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 0;
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		var minDepth = -1;
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				if (node.getLeft() == null || node.getRight() == null) {
					// if left or right child is null, return the depth till
					// that point as it's the min from the root
					return 1 + minDepth;
				}
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
			minDepth++;
		}
		return -1;
	}

	// 23. *Find the deepest node in the tree*
	// Sol: The last element in the queue from level order traversal is the
	// deepest node

	// 24. *Delete the node in a binary tree*
	// Sol: Starting at root, find the node which we want to delete. Find the
	// deepest node in the tree. Replace the deepest node’s data with node to be
	// deleted. Then delete the deepest node.

	// 25. *Find the # of leaves in the binary tree*
	// Sol: In level order traversal, when the node is dequeued, increment the
	// count if it's left and right children are null and return the count

	// 26. *Find the # of full nodes in the binary tree*
	// Sol: Similar to solution #24 above but increment the count if both left
	// and right children are not null

	// 27. *Find the # of half nodes*
	// Sol: Similar to above solution. Increment the count if left is empty but
	// not right or right is empty but not left. Only 1 chilren should be empty

	// 28. *Find if 2 binary trees are structurally identical*
	public boolean isStructurallyIdentical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		return isStructurallyIdentical(root1.getLeft(), root2.getLeft())
				&& isStructurallyIdentical(root1.getRight(), root2.getRight());
	}

	// 29. *Max diameter in the tree*
	public int maxDiameterInTree(TreeNode root) {
		final var diameter = new int[1];
		maxDiameterInTree(root, diameter);
		return diameter[0];
	}

	private int maxDiameterInTree(TreeNode root, int[] diameter) {
		if (root == null) {
			return 0;
		}
		final var lh = maxDiameterInTree(root.getLeft(), diameter);
		final var rh = maxDiameterInTree(root.getRight(), diameter);
		// if the max diameter doesn't pass through the current node, max
		// diameter should be the max height in left or right subtree. If it
		// passes through the current node, it should be left + right heights
		diameter[0] = Math.max(diameter[0], lh + rh);
		return 1 + Math.max(lh, rh);
	}

	// 30. *Find the level that has maximum sum in the tree*
	public int levelWithMaxSum(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 0;
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		var maxSum = 0;
		var maxSumLevel = 0;
		var level = 0;
		while (!queue.isEmpty()) {
			level++;
			final int size = queue.size();
			var sum = 0;
			for (var i = 0; i < size; i++) {
				final TreeNode node = queue.poll();
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
				sum += node.getData();
			}
			if (sum > maxSum) {
				maxSum = sum;
				maxSumLevel = level;
			}
		}
		return maxSumLevel;
	}

	// 31. *Find all paths in the tree from root to leaf*
	public List<String> findAllPaths(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<String> result = new ArrayList<>();
		findAllPaths(root, "", result);
		return result;
	}

	private void findAllPaths(TreeNode root, String path, List<String> result) {
		if (root == null) {
			return;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			result.add(path + root.getData());
		}
		if (root.getLeft() != null) {
			findAllPaths(root.getLeft(), path + root.getData() + "->", result);
		}
		if (root.getRight() != null) {
			findAllPaths(root.getRight(), path + root.getData() + "->", result);
		}
	}

	// 32. *Find if a path exists from root to leaf with given sum*
	public boolean hasPathSum(TreeNode root, int k) {
		if (root == null) {
			return false;
		}
		if (root.getLeft() == null && root.getRight() == null && root.getData() == k) {
			return true;
		}
		return hasPathSum(root.getLeft(), k - root.getData()) || hasPathSum(root.getRight(), k - root.getData());
	}

	// 33. *Find sum of all nodes in a binary tree*
	public int sumOfAllNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return root.getData() + sumOfAllNodes(root.getLeft()) + sumOfAllNodes(root.getRight());
	}

	// 34. *Mirror of tree*
	public void mirror(TreeNode root) {
		if (root == null || (root.getLeft() == null && root.getRight() == null)) {
			return;
		}
		mirror(root.getLeft());
		mirror(root.getRight());
		final TreeNode left = root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(left);
	}

	// 35. *Is Mirror*
	public boolean isMirror(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null || root1.getData() != root2.getData()) {
			return false;
		}
		return isMirror(root1.getLeft(), root2.getRight()) && isMirror(root1.getRight(), root2.getLeft());
	}

	// 36. *Build tree from preorder and inorder arrays*
	public TreeNode buildFromPreAndInorder(int[] preOrder, int[] inOrder) {
		if (preOrder.length != inOrder.length) {
			return null;
		}
		final Map<Integer, Integer> map = new HashMap<>();
		for (var i = 0; i < inOrder.length; i++) {
			map.put(inOrder[i], i);
		}
		System.out.println("buildFromPreAndInorder(0," + (preOrder.length - 1) + ",0," + (inOrder.length - 1) + ")");
		final TreeNode root = buildFromPreAndInorder(0, preOrder.length - 1, 0, inOrder.length - 1, preOrder, map);
		System.out.println("postOrder" + postOrderTraversal(root));
		return root;
	}

	private TreeNode buildFromPreAndInorder(int preStart, int preEnd, int inStart, int inEnd, int[] preOrder,
			Map<Integer, Integer> map) {
		if (preStart > preEnd || inStart > inEnd) {
			System.out.println("Return null - preStart, preEnd, inStart, inEnd: " + preStart + "," + preEnd + ","
					+ inStart + "," + inEnd);
			return null;
		}
		final var root = new TreeNode(preOrder[preStart]);
		final int indexOfRootInInOrder = map.get(root.getData());
		final var numOfNodesOnLeftOfRoot = indexOfRootInInOrder - inStart;
		System.out.println("buildFromPreAndInorder(" + (preStart + 1) + "," + (preStart + numOfNodesOnLeftOfRoot) + ","
				+ (inStart) + "," + (indexOfRootInInOrder - 1) + ")");
		root.setLeft(buildFromPreAndInorder(preStart + 1, preStart + numOfNodesOnLeftOfRoot, inStart,
				indexOfRootInInOrder - 1, preOrder, map));
		System.out.println("buildFromPreAndInorder(" + (preStart + numOfNodesOnLeftOfRoot + 1) + "," + (preEnd) + ","
				+ (indexOfRootInInOrder + 1) + "," + (inEnd) + ")");
		root.setRight(buildFromPreAndInorder(preStart + numOfNodesOnLeftOfRoot + 1, preEnd, indexOfRootInInOrder + 1,
				inEnd, preOrder, map));
		return root;
	}

	// 37. *Build tree from post order and inorder*
	public TreeNode buildFromPostAndInOrder(int[] postOrder, int[] inOrder) {
		if (postOrder.length != inOrder.length) {
			return null;
		}
		final Map<Integer, Integer> map = new HashMap<>();
		for (var i = 0; i < inOrder.length; i++) {
			map.put(inOrder[i], i);
		}
		return buildFromPostAndInOrder(0, postOrder.length - 1, 0, inOrder.length - 1, postOrder, map);
	}

	// (inStart, indexOfRootInInOrder-1) and (indexOfRootInInOrder+1, inEnd)
	// should be straightforward because these are indices for left and right
	// subtrees in the inorder traversal. (postStart,
	// postStart+indexOfRootInInOrder-inStart-1) and
	// (postStart+indexOfRootInInOrder-inStart, postEnd-1) these are indices for
	// left and right subtrees in the postorder traversal. the trick is that no
	// matter what which traversal is used, the number of nodes in the subtrees
	// remain the same. so if in the inorder traversal the length of left
	// subarray is 3, this length is also the same as that for postorder
	// traversal
	private TreeNode buildFromPostAndInOrder(int postStart, int postEnd, int inStart, int inEnd, int[] postOrder,
			Map<Integer, Integer> map) {
		if (postStart > postEnd || inStart > inEnd) {
			return null;
		}
		final TreeNode root = new TreeNode(postOrder[postEnd]);
		final int indexOfRootInInOrder = map.get(root.getData());
		root.setLeft(buildFromPostAndInOrder(postStart, postStart + indexOfRootInInOrder - inStart - 1, inStart,
				indexOfRootInInOrder - 1, postOrder, map));
		root.setRight(buildFromPostAndInOrder(postStart + indexOfRootInInOrder - inStart, postEnd - 1,
				indexOfRootInInOrder + 1, inEnd, postOrder, map));
		return root;
	}

	// 37. *Build tree from pre order and post order*
	public TreeNode buildFromPreAndPostOrder(int[] preOrder, int[] postOrder) {
		if (preOrder.length != postOrder.length) {
			return null;
		}
		final Map<Integer, Integer> map = new HashMap<>();
		for (var i = 0; i < postOrder.length; i++) {
			map.put(postOrder[i], i);
		}
		return buildFromPreAndPostOrder(0, preOrder.length - 1, 0, postOrder.length - 1, preOrder, map);
	}

	private TreeNode buildFromPreAndPostOrder(int preStart, int preEnd, int postStart, int postEnd, int[] preOrder,
			Map<Integer, Integer> map) {
		if (preStart > preEnd || postStart > postEnd) {
			return null;
		}
		final var root = new TreeNode(preOrder[preStart]);
		if (preStart + 1 <= preEnd) {
			final int indexOfRootLeftInPostOrder = map.get(preOrder[preStart + 1]);
			final int delta = indexOfRootLeftInPostOrder - postStart;
			root.setLeft(buildFromPreAndPostOrder(preStart + 1, preStart + 1 + delta, postStart,
					indexOfRootLeftInPostOrder, preOrder, map));
			root.setRight(buildFromPreAndPostOrder(preStart + 1 + delta + 1, preEnd, indexOfRootLeftInPostOrder + 1,
					postEnd - 1, preOrder, map));
		}
		return root;
	}

	// 38. *Find all ancestors for a given tree node*
	public List<Integer> findAncestors(TreeNode root, TreeNode node) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new ArrayList<>();
		findAncestors(root, node, result);
		return result;
	}

	private boolean findAncestors(TreeNode root, TreeNode node, List<Integer> result) {
		if (root == null) {
			return false;
		}
		if (root == node) {
			return true;
		}
		final boolean canFindNodeFromCurrentNode = findAncestors(root.getLeft(), node, result)
				|| findAncestors(root.getRight(), node, result);
		if (canFindNodeFromCurrentNode) {
			result.add(root.getData());
			return true;
		}
		return false;
	}

	// 39. *Find Least common ancestor*
	public TreeNode LCA(TreeNode root, TreeNode node1, TreeNode node2) {
		if (root == null) {
			return null;
		}
		if (root == node1 || root == node2) {
			return root;
		}
		final TreeNode left = LCA(root.getLeft(), node1, node2);
		final TreeNode right = LCA(root.getRight(), node1, node2);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

	// 40. *zigzag traversal*
	public List<Integer> zigZagTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		final List<Integer> result = new LinkedList<>();

		final Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		var height = 0;
		int previousResultSize = 0;
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final TreeNode node = queue.poll();
				if (height % 2 != 0) {
					result.add(previousResultSize, node.getData());
				} else {
					result.add(node.getData());
				}
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
			previousResultSize = result.size();
			height++;
		}
		return result;
	}

	// 41. *Vertical order traversal*
	public List<List<Integer>> verticalOrderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		final Map<Integer, List<Integer>> map = new TreeMap<>();
		verticalOrderTraversal(root, 0, map);
		final List<List<Integer>> result = new ArrayList<>();
		for (final int column : map.keySet()) {
			result.add(map.get(column));
		}
		return result;
	}

	private void verticalOrderTraversal(TreeNode root, int column, Map<Integer, List<Integer>> map) {
		if (root == null) {
			return;
		}
		verticalOrderTraversal(root.getLeft(), column - 1, map);
		verticalOrderTraversal(root.getRight(), column + 1, map);
		if (!map.containsKey(column)) {
			final List<Integer> list = new ArrayList<>();
			list.add(root.getData());
			map.put(column, list);
		} else {
			final List<Integer> nodesInAColumn = map.get(column);
			nodesInAColumn.add(root.getData());
			map.put(column, nodesInAColumn);
		}
	}

	// 42. *Number of binary trees with n nodes*
	public int numberOfBinaryTreesWithNNodes(int n) {
		return (1 << n) - n; // 2^n - n
	}

	// 43. *Generate all binary search trees with n nodes*
	public List<TreeNode> generateAllBinarySearchTrees(int n) {
		if (n == 0) {
			return generateAllBinarySearchTrees(1, 0);
		}
		return generateAllBinarySearchTrees(1, n);
	}

	public List<TreeNode> generateAllBinarySearchTrees(int start, int end) {
		final List<TreeNode> trees = new ArrayList<>();
		if (start > end) {
			trees.add(null);
			return trees;
		}
		for (int i = start; i <= end; i++) {
			// for every node i from 1..n as root, generate their left subtree
			// from 1..i and right subtree from i..n for every 1<=i<=n as we are
			// constructing a BST
			for (final TreeNode left : generateAllBinarySearchTrees(start, i - 1)) {
				for (final TreeNode right : generateAllBinarySearchTrees(i + 1, end)) {
					final TreeNode root = new TreeNode(i);
					root.setLeft(left);
					root.setRight(right);
					trees.add(root);
				}
			}
		}
		return trees;
	}

	// 44. *Print horizonral tree structure*
	// https://www.baeldung.com/java-print-binary-tree-diagram
	public String printHorizontalTree(TreeNode root) {
		if (root == null) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		sb.append(root.getData());

		final boolean hasRightSibling = root.getRight() != null;
		final var rightPointer = "└──";
		final var leftPointer = hasRightSibling ? "├──" : "└──";
		printHorizontalTree(root.getLeft(), sb, "", leftPointer, hasRightSibling);
		printHorizontalTree(root.getRight(), sb, "", rightPointer, false);
		return sb.toString();
	}

	private void printHorizontalTree(TreeNode root, StringBuilder sb, String padding, String pointer,
			boolean hasRightSibling) {
		if (root == null) {
			return;
		}
		sb.append("\n").append(padding).append(pointer).append(root.getData());
		final var paddingBuilder = new StringBuilder(padding);
		if (hasRightSibling) {
			paddingBuilder.append("│  ");
		} else {
			paddingBuilder.append("  ");
		}
		final var paddingForBoth = paddingBuilder.toString();

		final var rightPointer = "└──";
		final var leftPointer = root.getRight() != null ? "├──" : "└──";

		printHorizontalTree(root.getLeft(), sb, paddingForBoth, leftPointer, hasRightSibling);
		printHorizontalTree(root.getRight(), sb, paddingForBoth, rightPointer, false);
	}

	// 45. *Let's assume the tree has only 0 or 2 children. Given a pre-order
	// string "ILILL" where I = intermediate node, L = Leaf node, construct the
	// tree*
	public TreeNode constructTreeFromPreOrderString(String preOrderSequence) {
		if (preOrderSequence == null) {
			return null;
		}
		return constructTreeFromPreOrderString(preOrderSequence.toCharArray(), 0);
	}

	private TreeNode constructTreeFromPreOrderString(char[] arr, int index) {
		if (arr == null) {
			return null;
		}
		if (arr.length == index) {
			return null;
		}
		final TreeNode root = new TreeNode(arr[index]);
		if (arr[index] == 'L') {
			return null;
		}
		root.setLeft(constructTreeFromPreOrderString(arr, ++index));
		root.setRight(constructTreeFromPreOrderString(arr, ++index));
		return root;
	}

	// 46. *Given a SiblingBinaryTree with default nextSibling pointers pointing
	// to null, populate them*
	public SiblingTreeNode populateNextSiblingNodes(SiblingTreeNode root) {
		if (root == null) {
			return null;
		}
		final Queue<SiblingTreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			final int level = queue.size();
			for (var i = 0; i < level; i++) {
				final SiblingTreeNode node = queue.poll();
				node.setNextSibling(queue.peek());
				if (node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
		}
		return root;
	}
}

class TreeUtils {
	public static TreeNode createBinaryTree() {
		final var left = new TreeNode(1, new TreeNode(2), new TreeNode(3));
		final var right = new TreeNode(4, new TreeNode(5), new TreeNode(6));
		final var root = new TreeNode(0, left, right); // NOSONAR
		return root;
	}

	public static TreeNode createBinaryTree2() {
		final var left = new TreeNode(11, new TreeNode(12), new TreeNode(13));
		final var right = new TreeNode(14, new TreeNode(15), new TreeNode(16));
		final var root = new TreeNode(10, left, right); // NOSONAR
		return root;
	}

	public static TreeNode createBinaryTreeMirror() {
		final var left = new TreeNode(4, new TreeNode(6), new TreeNode(5));
		final var right = new TreeNode(1, new TreeNode(3), new TreeNode(2));
		final var root = new TreeNode(0, left, right); // NOSONAR
		return root;
	}

	public static TreeNode createBinaryTree3() {
		final var leaf1 = new TreeNode(4);
		final var leaf2 = new TreeNode(5);
		final var rootLeftLeft = new TreeNode(2, leaf1, leaf2);

		final var leaf3 = new TreeNode(6);
		final var leaf4 = new TreeNode(7);
		final var rootLeftRight = new TreeNode(3, leaf3, leaf4);

		final var rootLeft = new TreeNode(1, rootLeftLeft, rootLeftRight);

		final var leaf5 = new TreeNode(9);
		final var leaf6 = new TreeNode(10);
		final var rootRight = new TreeNode(8, leaf5, leaf6);

		final var root = new TreeNode(0, rootLeft, rootRight); // NOSONAR
		return root;
	}

	public static SiblingTreeNode createNextSiblingNodeBinaryTree() {
		final var leaf1 = new SiblingTreeNode(4);
		final var leaf2 = new SiblingTreeNode(5);
		final var rootLeftLeft = new SiblingTreeNode(2, leaf1, leaf2);

		final var leaf3 = new SiblingTreeNode(6);
		final var leaf4 = new SiblingTreeNode(7);
		final var rootLeftRight = new SiblingTreeNode(3, leaf3, leaf4);

		final var rootLeft = new SiblingTreeNode(1, rootLeftLeft, rootLeftRight);

		final var leaf5 = new SiblingTreeNode(9);
		final var leaf6 = new SiblingTreeNode(10);
		final var rootRight = new SiblingTreeNode(8, leaf5, leaf6);

		final var root = new SiblingTreeNode(0, rootLeft, rootRight); // NOSONAR
		return root;
	}
}

class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;

	public TreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public TreeNode(int data, TreeNode left, TreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
}

class SiblingTreeNode {
	int data;
	SiblingTreeNode left;
	SiblingTreeNode right;
	SiblingTreeNode nextSibling;

	public SiblingTreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public SiblingTreeNode(int data, SiblingTreeNode left, SiblingTreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public SiblingTreeNode(int data, SiblingTreeNode left, SiblingTreeNode right, SiblingTreeNode nextSibling) {
		this(data, left, right);
		this.nextSibling = nextSibling;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public SiblingTreeNode getLeft() {
		return left;
	}

	public void setLeft(SiblingTreeNode left) {
		this.left = left;
	}

	public SiblingTreeNode getRight() {
		return right;
	}

	public void setRight(SiblingTreeNode right) {
		this.right = right;
	}

	public SiblingTreeNode getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(SiblingTreeNode nextSibling) {
		this.nextSibling = nextSibling;
	}
}