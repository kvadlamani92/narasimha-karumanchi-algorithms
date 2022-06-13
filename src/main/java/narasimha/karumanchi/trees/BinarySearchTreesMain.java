package narasimha.karumanchi.trees;

import narasimha.karumanchi.linkedlist.LinkedListUtilities;
import narasimha.karumanchi.linkedlist.ListNode;

public class BinarySearchTreesMain {

	public static void main(String[] args) {
		final var binarySearchTreesMain = new BinarySearchTreesMain();
		// binarySearchTreesMain.runFind();
		// binarySearchTreesMain.runFindMin();
		// binarySearchTreesMain.runFindMax();
		// binarySearchTreesMain.runInsert();
		// binarySearchTreesMain.runDelete();
		// binarySearchTreesMain.runLCA();
		// binarySearchTreesMain.runIsBST();
		// binarySearchTreesMain.runBSTToDLL();
		// binarySearchTreesMain.runDLLToBST();
		// binarySearchTreesMain.runArrayToBST();
		binarySearchTreesMain.runKthSmallest();
	}

	// ...Binary Search Trees Runners...
	// 1. Run find
	public void runFind() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		System.out.println(find(root, 6).getData());
	}

	// 2. Find min
	public void runFindMin() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		System.out.println(findMin(root).getData());
	}

	// 3. Find max
	public void runFindMax() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		System.out.println(findMax(root).getData());
	}

	// 4. Insert in BST
	public void runInsert() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		final BinarySearchTreeNode rootAfterInsertion = insert(root, 4);
		System.out.println(printHorizontalTree(rootAfterInsertion));
	}

	// 5. Delete in BST
	public void runDelete() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		final BinarySearchTreeNode rootAfterDeletion = delete(root, 2);
		System.out.println(printHorizontalTree(rootAfterDeletion));
	}

	// 6. Find LCA
	public void runLCA() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		System.out.println(printHorizontalTree(root));
		final BinarySearchTreeNode node1 = find(root, 7);
		final BinarySearchTreeNode node2 = find(root, 1);
		final BinarySearchTreeNode lcaNode = findLCA(root, node1, node2);
		System.out.println(lcaNode.getData());
	}

	// 7. Run isBST
	public void runIsBST() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		System.out.println(isBST(root));
	}

	// 8. Run BSTToDLL
	public void runBSTToDLL() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		final BinarySearchTreeNode dllRoot = bstToDLL(root);
		System.out.println(printHorizontalTree(dllRoot));
	}

	// 9. Run dll to BST
	public void runDLLToBST() {
		final ListNode head = LinkedListUtilities.createLinkedListSorted();
		final BinarySearchTreeNode bstRoot = dllToBST(head);
		System.out.println(printHorizontalTree(bstRoot));
	}

	// 10. Run array to BST
	public void runArrayToBST() {
		final int[] arr = { 1, 2, 3, 5, 6, 7, 8, 9, 10 };
		final BinarySearchTreeNode bstRoot = arrayToBST(arr);
		System.out.println(printHorizontalTree(bstRoot));
	}

	// 11. Run find kth smallest
	public void runKthSmallest() {
		final BinarySearchTreeNode root = BinarySearchTreeUtils.createBinarySearchTree();
		System.out.println(printHorizontalTree(root));

		System.out.println(findKthSmallestElement(root, 7).getData());

	}

	// ...Algorithms...
	// 1. *Find in BST*
	public BinarySearchTreeNode find(BinarySearchTreeNode root, int data) {
		if (root == null) {
			return null;
		}
		if (root.getData() == data) {
			return root;
		}
		if (data < root.getData()) {
			return find(root.getLeft(), data);
		}
		return find(root.getRight(), data);
	}

	// 2. *Find min*
	public BinarySearchTreeNode findMin(BinarySearchTreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return root;
		}
		BinarySearchTreeNode curNode = root;
		while (curNode.getLeft() != null) {
			curNode = curNode.getLeft();
		}
		return curNode;
	}

	// 3. *Find max*
	public BinarySearchTreeNode findMax(BinarySearchTreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return root;
		}
		BinarySearchTreeNode curNode = root;
		while (curNode.getRight() != null) {
			curNode = curNode.getRight();
		}
		return curNode;
	}

	// 4. Insert in a BST
	public BinarySearchTreeNode insert(BinarySearchTreeNode root, int data) {
		if (root == null) {
			return new BinarySearchTreeNode(data);
		}
		if (data < root.getData()) {
			root.setLeft(insert(root.getLeft(), data));
		} else if (data > root.getData()) {
			root.setRight(insert(root.getRight(), data));
		}
		return root;
	}

	// 5. Delete from BST
	public BinarySearchTreeNode delete(BinarySearchTreeNode root, int data) {
		if (root == null) {
			return null;
		}
		if (data < root.getData()) {
			root.setLeft(delete(root.getLeft(), data));
		} else if (data > root.getData()) {
			root.setRight(delete(root.getRight(), data));
		} else { // Found node
			if (root.getLeft() != null && root.getRight() != null) {
				final BinarySearchTreeNode leftMax = findMax(root.getLeft());
				root.setData(leftMax.getData());
				root.setLeft(delete(root.getLeft(), root.getData()));
			} else {
				if (root.getLeft() == null) {
					root = root.getRight();
				} else if (root.getRight() == null) {
					root = root.getLeft();
				}
			}
		}
		return root;
	}

	// 6. LCA of BST
	public BinarySearchTreeNode findLCA(BinarySearchTreeNode root, BinarySearchTreeNode node1,
			BinarySearchTreeNode node2) {
		if (root == null) {
			return null;
		}
		if (root == node1 || root == node2) {
			return root;
		}
		// If node1.data < root.data && node2.data < root.data
		if (Math.max(node1.getData(), node2.getData()) < root.getData()) {
			return findLCA(root.getLeft(), node1, node2);
		}
		// If node1.data > root.data || node2.data > root.data
		if (Math.min(node1.getData(), node2.getData()) > root.getData()) {
			return findLCA(root.getRight(), node1, node2);
		}
		return root;
	}

	// 7. Check if BST or not
	public boolean isBST(BinarySearchTreeNode root) {
		if (root == null || root.getLeft() == null && root.getRight() == null) {
			return true;
		}
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isBST(BinarySearchTreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
		return (min < root.getData() && root.getData() < max) && isBST(root.getLeft(), min, root.getData())
				&& isBST(root.getRight(), root.getData(), max);
	}

	// 8. Convert BST to DLL
	public BinarySearchTreeNode bstToDLL(BinarySearchTreeNode root) {
		if (root == null) {
			return null;
		}

		final var leftHead = bstToDLL(root.left);
		final var rightHead = bstToDLL(root.right);
		root.left = root;
		root.right = root;
		return connect(connect(leftHead, root), rightHead);
	}

	// Used to connect two circular doubly linked lists. n1 is the head of
	// circular DLL as well as n2.
	private BinarySearchTreeNode connect(BinarySearchTreeNode n1, BinarySearchTreeNode n2) {
		if (n1 == null) {
			return n2;
		}
		if (n2 == null) {
			return n1;
		}

		System.out.println("n1=" + n1.getData() + ", n2=" + n2.getData());
		final var tail1 = n1.left;
		final var tail2 = n2.left;
		System.out.println("tail1=" + tail1.getData() + ", tail2=" + tail2.getData());

		tail1.right = n2;
		n2.left = tail1;
		tail2.right = n1;
		n1.left = tail2;

		return n1;
	}

	// 9. Convert Linkedlist to BST
	public BinarySearchTreeNode dllToBST(ListNode head) {
		if (head == null) {
			return null;
		}
		var listLength = 0;
		for (var temp = head; temp != null; temp = temp.getNext()) {
			listLength++;
		}
		return dllToBST(head, 0, listLength - 1);
	}

	private BinarySearchTreeNode dllToBST(ListNode head, int start, int end) {
		if (start > end) {
			return null;
		}
		final int mid = start + (end - start) / 2;
		final var left = dllToBST(head, start, mid - 1);
		final var root = new BinarySearchTreeNode(head.getData());
		root.setLeft(left);
		if (head.getNext() != null) {
			// head = head.getNext();
			// This is wrong as we are changing the pointer of the head variable
			// (memory reference) in-between recursive calls. Which means once
			// the sub-call is executed and returns to the caller, the caller
			// still would have the pointer to the earlier memory reference (as
			// the parameters are stored in a stack and only the memory
			// reference of an object is stored into the stack)
			// Eg: Let's say the execution is as follows:
			// dllToBST(head, 0,6) and head is at memory reference 0x123 ->
			// Inside this method, the parameter stack would contain [0x123,0,6
			// (top)]
			// Inside it, the left = dllToBST(head, 0,2) - Inside it, the
			// parameter stack = [0x123,0,2 (top)] which calls
			// dllToBST(head,0,0) , Inside it, paramater stack = [0x123,0,0
			// (top)] -> [mid = 0, left = null , root = new
			// BinarySearchTreeNode(1), let's say head = head.right (new head
			// reference is at 0x124), right = null and returns root]
			// We want the head to point to the next element after 1 (i.e 2)
			// when we return to dllToBST(0,2) but still the head points to
			// memory ref 0x123 instead of 0x124 which was modified in it's
			// sub-call! So it still points to 1.
			// The fix is not to change the head pointer but to make the current
			// head pointer point to it's
			// next's data and modify it's next to it's next.next

			head.setData(head.getNext().getData());
			head.setNext(head.getNext().getNext());
		}
		final var right = dllToBST(head, mid + 1, end);
		root.setRight(right);
		return root;
	}

	// 10. Sorted array to BST
	public BinarySearchTreeNode arrayToBST(int[] arr) {
		return arrayToBST(arr, 0, arr.length - 1);
	}

	private BinarySearchTreeNode arrayToBST(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		final int mid = start + (end - start) / 2;
		final var root = new BinarySearchTreeNode(arr[mid]);
		final var left = arrayToBST(arr, start, mid - 1);
		final var right = arrayToBST(arr, mid + 1, end);
		root.setLeft(left);
		root.setRight(right);
		return root;
	}

	// 11. Kth smallest element in a BST
	public BinarySearchTreeNode findKthSmallestElement(BinarySearchTreeNode root, int k) {
		if (root == null) {
			return null;
		}
		final var count = new int[1];
		count[0] = 0;
		return findKthSmallestElement(root, k, count);
	}

	private BinarySearchTreeNode findKthSmallestElement(BinarySearchTreeNode root, int k, int[] count) {
		if (root == null) {
			return null;
		}
		final BinarySearchTreeNode left = findKthSmallestElement(root.getLeft(), k, count);
		if (left != null) {
			return left;
		}
		if (++count[0] == k) {
			return root;
		}
		return findKthSmallestElement(root.getRight(), k, count);
		// if (root == null) {
		// return null;
		// }
		// final BinarySearchTreeNode left = findKthSmallestElement(root.left,
		// k, count);
		// if (left != null) {
		// return left;
		// }
		// if (++count == k) {
		// return root;
		// }
		// return findKthSmallestElement(root.right, k, count);
	}

	// 44. *Print horizonral tree structure*
	// https://www.baeldung.com/java-print-binary-tree-diagram
	public String printHorizontalTree(BinarySearchTreeNode root) {
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

	private void printHorizontalTree(BinarySearchTreeNode root, StringBuilder sb, String padding, String pointer,
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
}

class BinarySearchTreeUtils {
	public static BinarySearchTreeNode createBinarySearchTree() {
		final BinarySearchTreeNode leftLeft = new BinarySearchTreeNode(1);
		final BinarySearchTreeNode leftRight = new BinarySearchTreeNode(3);

		final BinarySearchTreeNode left = new BinarySearchTreeNode(2, leftLeft, leftRight);

		final BinarySearchTreeNode rightLeft = new BinarySearchTreeNode(6);
		final BinarySearchTreeNode rightRight = new BinarySearchTreeNode(8);

		final BinarySearchTreeNode right = new BinarySearchTreeNode(7, rightLeft, rightRight);

		final BinarySearchTreeNode root = new BinarySearchTreeNode(5, left, right);
		return root;
	}
}

class BinarySearchTreeNode {
	int data;
	BinarySearchTreeNode left;
	BinarySearchTreeNode right;

	public BinarySearchTreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public BinarySearchTreeNode(int data, BinarySearchTreeNode left, BinarySearchTreeNode right) {
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

	public BinarySearchTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinarySearchTreeNode left) {
		this.left = left;
	}

	public BinarySearchTreeNode getRight() {
		return right;
	}

	public void setRight(BinarySearchTreeNode right) {
		this.right = right;
	}
}