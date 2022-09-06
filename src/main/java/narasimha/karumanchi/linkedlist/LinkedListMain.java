package narasimha.karumanchi.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class LinkedListMain {
	public static void main(String[] args) {
		final var linkedListMain = new LinkedListMain();
		// linkedListMain.runInsert();
		// linkedListMain.runDeleteFromList();
		// linkedListMain.runFindPosition();
		// linkedListMain.runClear();
		// linkedListMain.runFindNthNodeFromTheEnd();
		// linkedListMain.runIsCycleExists();
		// linkedListMain.runFindStartNodeOfLoop();
		// linkedListMain.runRemoveLoop();
		// linkedListMain.runFindLengthOfLoop();
		// linkedListMain.runInsertInSortedList();
		// linkedListMain.runReverseIterative();
		// linkedListMain.runReverseRecursive();
		// linkedListMain.runFindIntersectionOfLists();
		// linkedListMain.runGetMiddleOfList();
		// linkedListMain.runPrintListInReverse();
		// linkedListMain.runMergeTwoSortedLinkedLists();
		// linkedListMain.runMergeTwoSortedLinkedListsRecursive();
		// linkedListMain.runReverseListInPairs();
		// linkedListMain.runSplitCircularList();
		// linkedListMain.runIsPalindromeList();
		// linkedListMain.runReverseKNodesInList();
		// linkedListMain.runJosepheusCircleNode();
		// System.out.println(josephus(7, 2));
		// linkedListMain.runClone();
		// linkedListMain.runRotate();
		// linkedListMain.runAddNumbers();
		// linkedListMain.runPartition();
		// linkedListMain.runRemoveDuplicates();
		// linkedListMain.runCommonElements();
		linkedListMain.runMergeKLists();
	}

	// ....LinkedListADT Runners....
	// 1. Run insert()
	public void runInsert() {
		final var linkedListADT = LinkedListUtilities.createLinkedList();
		System.out.println(linkedListADT.toString());
	}

	// 2. Run deleteFromList
	public void runDeleteFromList() {
		final var linkedListADT = LinkedListUtilities.createLinkedList();
		System.out.println(linkedListADT.toString());
		linkedListADT.delete(2);
		System.out.println(linkedListADT.toString());

	}

	// 3. Run findPosition
	public void runFindPosition() {
		final var linkedListADT = LinkedListUtilities.createLinkedList();
		System.out.println(linkedListADT.getPosition(13));
	}

	// 4. Run clear
	public void runClear() {
		final var linkedListADT = LinkedListUtilities.createLinkedList();
		linkedListADT.clear();
		System.out.println(linkedListADT.getHead());
		System.out.println(linkedListADT.getLength());
	}

	// 5. Run FindNthNodeFromTheEnd
	public void runFindNthNodeFromTheEnd() {
		final var linkedListADT = LinkedListUtilities.createLinkedList();
		System.out.println(linkedListADT.findNthNodeFromTheEnd(3).getData());
	}

	// ....ListNode Runners....
	// 1. Run is CycleExists
	public void runIsCycleExists() {
		final var listNode = LinkedListUtilities.createLinkedListWithCycle();
		System.out.println(isCycleExists(listNode));
	}

	// 2. Find start node of loop
	public void runFindStartNodeOfLoop() {
		final var listNode = LinkedListUtilities.createLinkedListWithCycle();
		System.out.println(findStartNodeOfLoop(listNode).getData());
	}

	// 3. Remove loop in a linkedList
	public void runRemoveLoop() {
		final var head = LinkedListUtilities.createLinkedListWithCycle();
		final ListNode newHead = removeLoop(head);
		newHead.printLinkedList();
	}

	// 4. Find length of loop
	public void runFindLengthOfLoop() {
		final var head = LinkedListUtilities.createLinkedListWithCycle();
		System.out.println(findLengthOfLoop(head));

	}

	// 5. Insert in sortedList
	public void runInsertInSortedList() {
		final ListNode head = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode newHead = insertElementInSortedLinkedList(head, 12);
		newHead.printLinkedList();
	}

	// 6. Reverse a linked list
	public void runReverseIterative() {
		final ListNode head = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode newHead = reverseIterative(head);
		newHead.printLinkedList();
	}

	// 7. Reverse a linked list recursive
	public void runReverseRecursive() {
		final ListNode head = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode newHead = reverseRecursive(head);
		newHead.printLinkedList();
	}

	// 8. Find intersection of lists
	public void runFindIntersectionOfLists() {
		final var head1 = new ListNode(10);
		var node1 = head1;
		node1.setNext(new ListNode(12));
		node1 = node1.getNext();
		node1.setNext(new ListNode(14));
		node1 = node1.getNext();

		final var intersectionNode = new ListNode(16);
		node1.setNext(intersectionNode);
		node1 = node1.getNext();
		node1.setNext(new ListNode(17));
		node1 = node1.getNext();
		node1.setNext(new ListNode(18));

		final var head2 = new ListNode(11);
		var node2 = head2;
		node2.setNext(new ListNode(13));
		node2 = node2.getNext();
		node2.setNext(new ListNode(14));
		node2 = node2.getNext();
		node2.setNext(new ListNode(15));
		node2 = node2.getNext();
		node2.setNext(intersectionNode);

		head1.printLinkedList();
		System.out.println();
		head2.printLinkedList();
		System.out.println();
		System.out.println(findIntersectionOfLists(head1, head2).getData());
	}

	// 9. Find middle of list
	public void runGetMiddleOfList() {
		final ListNode head = LinkedListUtilities.createLinkedListWithoutCycle();
		System.out.println(findMiddleOfList(head).getData());
	}

	// 10. Print list in reverse
	public void runPrintListInReverse() {
		final ListNode head = LinkedListUtilities.createLinkedListWithoutCycle();
		printListInReverse(head);
	}

	// 11. Merge 2 sorted linked lists
	public void runMergeTwoSortedLinkedLists() {
		final var head1 = new ListNode(10);
		var node1 = head1;
		node1.setNext(new ListNode(12));
		node1 = node1.getNext();
		node1.setNext(new ListNode(14));
		node1 = node1.getNext();

		final var head2 = new ListNode(11);
		var node2 = head2;
		node2.setNext(new ListNode(13));
		node2 = node2.getNext();
		node2.setNext(new ListNode(15));

		head1.printLinkedList();
		System.out.println();
		head2.printLinkedList();
		System.out.println();
		System.out.println(merge(head1, head2).getData());
	}

	// 12. Merge 2 sorted linked lists recursive
	public void runMergeTwoSortedLinkedListsRecursive() {
		final var head1 = new ListNode(10);
		var node1 = head1;
		node1.setNext(new ListNode(12));
		node1 = node1.getNext();
		node1.setNext(new ListNode(14));
		node1 = node1.getNext();

		final var head2 = new ListNode(11);
		final var node2 = head2;
		node2.setNext(new ListNode(13));

		head1.printLinkedList();
		System.out.println();
		head2.printLinkedList();
		System.out.println();
		mergeRecursive(head1, head2).printLinkedList();
	}

	// 13. Print list in reverse
	public void runReverseListInPairs() {
		final ListNode head = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode reversedListInPairs = reverseListInPairs(head);
		reversedListInPairs.printLinkedList();
	}

	// 14. Split circular list
	public void runSplitCircularList() {
		final ListNode head = LinkedListUtilities.createCircularList();
		final Pair<ListNode, ListNode> pair = splitCircularList(head);
		pair.getKey().printLinkedList();
		pair.getValue().printLinkedList();
	}

	// 15. Run palindrome list
	public void runIsPalindromeList() {
		final ListNode head = LinkedListUtilities.createPalindromeList();
		System.out.println(isPalindromeList(head));
	}

	// 16. Reverse K nodes in a list
	public void runReverseKNodesInList() {
		final ListNode head = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode newHead = reverseKNodesInList(head, 3);
		newHead.printLinkedList();
	}

	// 17. Josepheus circle
	public void runJosepheusCircleNode() {
		// final ListNode head = LinkedListUtilities.createCircularList();
		// final ListNode newHead = findJosepheusCircleNode(head, 2);
		// newHead.printLinkedList();
		System.out.println(josephus(3, 2));
	}

	// 18. Clone linked list with random pointers
	public void runClone() {
		final LinkedListRandom head = LinkedListUtilities.createLinkedListWithRandomPointersWithoutCycle();
		final LinkedListRandom clonedHead = clone(head);
		clonedHead.printLinkedList();
	}

	// 19. Alternate elements in 2 linked lists
	public void runAlternate() {
		final ListNode head1 = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode head2 = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode finalHead = alternate(head1, head2);
		finalHead.printLinkedList();
	}

	// 20. Rotate linked list by k
	public void runRotate() {
		final ListNode head1 = LinkedListUtilities.createLinkedListWithoutCycle();
		final ListNode finalHead = rotate(head1, 3);
		finalHead.printLinkedList();
	}

	// 21. Add 2 numbers
	public void runAddNumbers() {
		final ListNode head1 = LinkedListUtilities.createLinkedListNumber1();
		final ListNode head2 = LinkedListUtilities.createLinkedListNumber2();
		final ListNode finalHead = add(head1, head2);
		finalHead.printLinkedList();
	}

	// 22. Partition based on pivot
	public void runPartition() {
		final ListNode head = LinkedListUtilities.createLinkedListUnsorted();
		final ListNode finalHead = partition(head, 4);
		finalHead.printLinkedList();
	}

	// 23. Remove duplicates in linked list
	public void runRemoveDuplicates() {
		final ListNode head = LinkedListUtilities.createLinkedListWithDuplicates();
		final ListNode finalHead = removeDuplicates(head);
		finalHead.printLinkedList();
	}

	// 24. Print common elements in sorted linked lists
	public void runCommonElements() {
		final ListNode head1 = LinkedListUtilities.createLinkedListNumber1();
		final ListNode head2 = LinkedListUtilities.createLinkedListNumber2();
		final ListNode finalHead = commonElements(head1, head2);
		finalHead.printLinkedList();
	}

	// 25. Merge k linkedLists
	public void runMergeKLists() {
		final ListNode head1 = LinkedListUtilities.createLinkedListNumber1();
		final ListNode head2 = LinkedListUtilities.createLinkedListNumber2();
		final List<ListNode> listOfLists = new ArrayList<>();
		listOfLists.add(head1);
		listOfLists.add(head2);
		final ListNode finalHead = mergeKLists(listOfLists);
		finalHead.printLinkedList();
	}

	// ....Algorithms....
	// 1. isCycleExists in the LinkedList
	public boolean isCycleExists(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (true) {
			fast = fast.getNext();
			slow = slow.getNext();
			if (fast != null) {
				fast = fast.getNext();
			}
			if (fast == null || slow == null) {
				return false;
			}
			if (slow == fast) {
				return true;
			}
		}
	}

	// 2. Find the meeting point of slow and fast pointer in the linked list
	// loop
	private ListNode findMeetingPointOfSlowAndFastPointer(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (true) {
			fast = fast.getNext();
			slow = slow.getNext();
			if (fast != null) {
				fast = fast.getNext();
			}
			if (fast == null || slow == null) {
				return null;
			}
			if (slow == fast) {
				break;
			}
		}
		return fast; // or slow as both meet at this point
	}

	// 3. Find the loop node in a linked list
	public ListNode findStartNodeOfLoop(ListNode head) {
		ListNode fast = findMeetingPointOfSlowAndFastPointer(head);
		if (fast == null) {
			return null;
		}
		ListNode slow = head; // restart the slow node from the beginning
		while (slow != fast) {
			slow = slow.getNext();
			fast = fast.getNext();
		}
		return fast; // or slow as both meet at the loop node
	}

	// 4. Find the length of the loop
	public int findLengthOfLoop(ListNode head) {
		final ListNode meetingPointInLoop = findMeetingPointOfSlowAndFastPointer(head);
		if (meetingPointInLoop == null) {
			return 0;
		}
		ListNode temp = meetingPointInLoop;
		var length = 1; // count the existing meeting point node
		while (temp.getNext() != meetingPointInLoop) {
			temp = temp.getNext();
			length++;
		}
		return length;
	}

	// 5. Remove loop in a linked list
	public ListNode removeLoop(ListNode head) {
		final ListNode loopNode = findStartNodeOfLoop(head);
		if (loopNode != head) {
			loopNode.setNext(null);
		} else {
			ListNode temp = head;
			while (temp.getNext() != head) {
				temp = temp.getNext();
			}
			temp.setNext(null);
		}
		return head;
	}

	// 6. Insert element in a sorted linked list
	public ListNode insertElementInSortedLinkedList(ListNode head, int data) {
		if (head == null) {
			return new ListNode(data);
		}
		ListNode temp = head;
		while (temp.getNext() != null && temp.getNext().getData() < data) {
			temp = temp.getNext();
		}
		final var newNode = new ListNode(data);
		newNode.setNext(temp.getNext());
		temp.setNext(newNode);
		return head;
	}

	// 7. Reverse a linked list iterative
	public ListNode reverseIterative(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode prev = null;
		ListNode cur = head;
		ListNode next = null;
		while (cur != null) {
			next = cur.getNext();
			cur.setNext(prev);
			prev = cur;
			cur = next;
		}
		return prev;
	}

	// 8. Reverse a linked list recursive
	public ListNode reverseRecursive(ListNode head) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		final ListNode newHead = reverseRecursive(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return newHead;
	}

	// 9. Find intersection point of 2 linked lists
	public ListNode findIntersectionOfLists(ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		ListNode runner1 = head1;
		ListNode runner2 = head2;
		while (true) {
			runner1 = runner1.getNext();
			runner2 = runner2.getNext();
			if (runner1 == null) {
				runner1 = head2;
			}
			if (runner2 == null) {
				runner2 = head1;
			}
			if (runner1 == runner2) {
				return runner1; // or runner2 as both meet at the point of
								// intersection
			}
			if (runner1 == head1 || runner2 == head2) { // no intersection point
														// as both runners came
														// back to their
														// original positions
				return null;
			}
		}
	}

	// 10. Middle of the linked list
	public ListNode findMiddleOfList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.getNext() != null) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
		}
		return slow;
	}

	// 11. Print linkedlist in reverse
	public void printListInReverse(ListNode head) {
		if (head == null) {
			return;
		}
		printListInReverse(head.getNext());
		System.out.println(head.getData());
	}

	// 12. Merge 2 sorted linked lists
	public ListNode merge(ListNode head1, ListNode head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		var sentinel = new ListNode(-1);
		final var mergedHead = sentinel;

		while (head1 != null && head2 != null) {
			if (head1.getData() < head2.getData()) {
				sentinel.setNext(head1);
				head1 = head1.getNext();
			} else {
				sentinel.setNext(head2);
				head2 = head2.getNext();
			}
			sentinel = sentinel.getNext();
		}
		if (head1 == null) {
			sentinel.setNext(head2);
		} else {
			sentinel.setNext(head1);
		}
		return mergedHead.getNext();
	}

	// 13. Merge recursive
	public ListNode mergeRecursive(ListNode head1, ListNode head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		ListNode sentinel = null;
		if (head1.getData() < head2.getData()) {
			sentinel = head1;
			head1.setNext(mergeRecursive(head1.getNext(), head2));
		} else {
			sentinel = head2;
			head2.setNext(mergeRecursive(head1, head2.getNext()));
		}
		return sentinel;
	}

	// 14. Reverse linked list in pairs
	// solution:
	// https://leetcode.com/problems/swap-nodes-in-pairs/discuss/1775033/SWAPPING-NODES-(Not-just-the-values)-oror-Visual-Explanation-oror-Well-Explained-oror-C%2B%2B
	public ListNode reverseListInPairs(ListNode head) {
		if (head == null) {
			return null;
		}
		final var sentinel = new ListNode(-1);
		ListNode prev = sentinel;
		ListNode cur = head;
		while (cur != null && cur.getNext() != null) {
			prev.setNext(cur.getNext());
			if (prev.getNext() != null) {
				cur.setNext(prev.getNext().getNext());
			}
			prev.getNext().setNext(cur);

			prev = cur;
			cur = cur.getNext();
		}
		return sentinel.getNext();
	}

	// 15. Split a circular linkedList into 2 circular linked lists. If length
	// is odd, make the 1st list one element more than the 2nd one
	public Pair<ListNode, ListNode> splitCircularList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while (fast.getNext() != head && fast.getNext().getNext() != head) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
		}
		if (fast.getNext().getNext() == head) { // if length of list is even
			fast = fast.getNext(); // make the fast pointer point to last
									// element in the 2nd list
		}

		fast.setNext(slow.getNext());
		slow.setNext(head);
		return new Pair<>(head, fast.getNext());
	}

	// 16. Check if a list is a palindrome
	public boolean isPalindromeList(ListNode head) {
		if (head == null || head.getNext() == null) {
			return true;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.getNext() != null && fast.getNext().getNext() != null) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
		}
		ListNode reversedSecondHalfHead = null;
		if (fast.getNext() == null) { // if the list is odd - fast.next is null
										// and slow.next points to the head of
										// the 2nd half
			reversedSecondHalfHead = reverseRecursive(slow.getNext());
		} else { // if the list is even - fast.next.next is null and slow points
					// to the head of the 2nd half
			reversedSecondHalfHead = reverseRecursive(slow);
		}
		ListNode head1 = head;
		ListNode head2 = reversedSecondHalfHead;
		while (head1 != null && head2 != null) { // this will work for the case
													// when list is odd as well
													// as we are just comparing
													// the elements in 2 lists
													// one to one and the middle
													// element would not be
													// compared at all. For even
													// list, both the middle
													// elements would be
													// compared
			if (head1.getData() != head2.getData()) {
				slow.setNext(reverseRecursive(reversedSecondHalfHead));
				return false;
			}
			head1 = head1.getNext();
			head2 = head2.getNext();
		}
		return true;
	}

	// 17. Reverse k nodes in a linkedlist
	/*
	 * https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11413/
	 * Share-my-Java-Solution-with-comments-in-line # see comment by motorix
	 * https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11413/
	 * Share-my-Java-Solution-with-comments-in-line/12122 For example,
	 * 
	 * list = 1 -> 2 -> 3 -> 4 -> 5, k = 3 Use a dummy head to simplify
	 * operations. Dummy -> 1 -> 2 -> 3 -> 4 -> 5 Use three pointers. The
	 * operation is similar to Leetcode#92 Reverse Linked List II. The pointer n
	 * will go k steps further. (If there are no k nodes further, it means we
	 * don't have to reverse these k nodes. ==> Stop. ) The pointer p is always
	 * at the fixed position in this for-loop. The pointer c = p.next, which
	 * means the current node we want to move. The pointer start means the
	 * starting node for the next loop. Dummy -> 1 -> 2 -> 3 -> 4 -> 5 p c n
	 * start Dummy -> 2 -> 3 -> 1 -> 4 -> 5 p c n start Dummy -> 3 -> 2 -> 1 ->
	 * 4 -> 5 p c start n
	 * 
	 */

	public ListNode reverseKNodesInList(ListNode head, int k) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		final var sentinel = new ListNode(-1);
		sentinel.setNext(head);
		ListNode start = sentinel;
		while (true) {
			final ListNode p = start;
			ListNode c = null;
			ListNode n = p;
			start = p.getNext();
			for (var i = 0; i < k && n != null; i++) {
				n = n.getNext();
			}
			if (n == null) {
				break;
			}
			for (var i = 0; i < k - 1; i++) {
				c = p.getNext();
				p.setNext(c.getNext());
				c.setNext(n.getNext());
				n.setNext(c);
			}
		}
		return sentinel.getNext();
	}

	// 18. Josepheus circle
	// Given a circular linked list of N items and remove every kth element
	// (including the 1st element). Find the last element standing
	public ListNode findJosepheusCircleNode(ListNode head, int k) {
		if (head == null) {
			return head;
		}
		if (k == 1) {
			ListNode temp = head;
			while (temp.getNext() != head) {
				temp = temp.getNext();
			}
			temp.setNext(null);
			return temp;
		}
		ListNode temp = head;
		while (temp.getNext() != temp) {
			for (var i = 0; i < k - 2; i++) {
				temp = temp.getNext();
			}

			temp.setNext(temp.getNext().getNext());
			temp.printLinkedList();
			temp = temp.getNext();
		}
		return temp;
	}

	static int josephus(int N, int K) {
		if (N == 1) {
			return 1;
		} else {
			return (josephus(N - 1, K) + K - 1) % N + 1;
		}
	}

	// 19. Clone a linked list with random pointer
	public LinkedListRandom clone(LinkedListRandom head) {
		if (head == null) {
			return head;
		}
		final Map<LinkedListRandom, LinkedListRandom> map = new HashMap<>();

		LinkedListRandom head1 = head;
		final var clonedHead = new LinkedListRandom(head.getData());
		LinkedListRandom head2 = clonedHead;
		map.put(head1, head2);

		while (head1 != null) {
			head1 = head1.getNext();
			if (head1 == null) {
				break;
			}
			final var head2Next = new LinkedListRandom(head1.getData());
			head2.setNext(head2Next);
			head2 = head2.getNext();
			map.put(head1, head2);
		}

		head1 = head;
		head2 = clonedHead;
		while (head1 != null && head2 != null) {
			head2.setRandom(map.get(head1.getRandom()));
			head1 = head1.getNext();
			head2 = head2.getNext();
		}
		return clonedHead;
	}

	// 20. Alternate the elements in 2 linked lists
	public ListNode alternate(ListNode head1, ListNode head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		final var sentinel = new ListNode(-1);
		ListNode sentinelTemp = sentinel;
		var counter = 0;

		while (head1 != null && head2 != null) {
			if (counter % 2 == 0) {
				sentinelTemp.setNext(head1);
				head1 = head1.getNext();
			} else {
				sentinelTemp.setNext(head2);
				head2 = head2.getNext();
			}
			sentinelTemp = sentinelTemp.getNext();
			counter++;
		}
		if (head1 == null) {
			sentinelTemp.setNext(head2);
		} else {
			sentinelTemp.setNext(head1);
		}
		return sentinel.getNext();
	}

	// 21. Rotate the list to the right by k places
	public ListNode rotate(ListNode head, int k) {
		if (head == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;

		// move the fast by k elements so that slow is before the 1st element
		// after the rotated list
		for (var i = 0; i < k && fast != null; i++) {
			fast = fast.getNext();
		}
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext();
		}
		final ListNode newHead = slow.getNext();
		slow.setNext(null);
		fast.setNext(head);
		return newHead;
	}

	// 22. Add 2 numbers represented as linked lists in reverse order
	public ListNode add(ListNode head1, ListNode head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		final var sentinel = new ListNode(-1);
		ListNode sentinelTemp = sentinel;
		var data = 0;
		var carry = 0;
		while (head1 != null || head2 != null) {
			data = (head1 != null ? head1.getData() : 0) + (head2 != null ? head2.getData() : 0) + carry;
			if (data > 9) {
				data %= 10;
				carry = 1;
			} else {
				carry = 0;
			}
			sentinelTemp.setNext(new ListNode(data));
			sentinelTemp = sentinelTemp.getNext();
			if (head1 != null) {
				head1 = head1.getNext();
			}
			if (head2 != null) {
				head2 = head2.getNext();
			}
		}
		if (carry > 0) {
			sentinelTemp.setNext(new ListNode(carry));
		}
		return sentinel.getNext();
	}

	// 23. Parition linkedlist based on a pivot k (elements lesser than k should
	// be on left and elements >= k should be on right)
	public ListNode partition(ListNode head, int k) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		final var lowerSentinel = new ListNode(-1);
		ListNode lowerSentinelTemp = lowerSentinel;

		final var pivotSentinel = new ListNode(-1);
		ListNode pivotSentinelTemp = pivotSentinel;

		ListNode current = head;
		while (current != null) {
			final ListNode next = current.getNext();
			if (current.getData() < k) {
				lowerSentinelTemp.setNext(current);
				lowerSentinelTemp = lowerSentinelTemp.getNext();
			} else {
				pivotSentinelTemp.setNext(current);
				pivotSentinelTemp = pivotSentinelTemp.getNext();
			}
			current.setNext(null);
			current = next;
		}
		lowerSentinelTemp.setNext(pivotSentinel.getNext());
		return lowerSentinel.getNext();
	}

	// 24. Remove duplicates in unordered list
	public ListNode removeDuplicates(ListNode head) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		final Set<Integer> set = new HashSet<>();
		set.add(head.getData());
		ListNode current = head;
		while (current.getNext() != null) {
			final ListNode next = current.getNext();
			if (set.contains(next.getData())) {
				current.setNext(next.getNext());
			} else {
				set.add(next.getData());
				current = current.getNext();
			}
		}

		return head;
	}

	// 25. Print common elements in 2 sorted linked lists
	public ListNode commonElements(ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		final var sentinel = new ListNode(-1);
		ListNode sentinelTemp = sentinel;
		while (head1 != null && head2 != null) {
			if (head1.getData() == head2.getData()) {
				sentinelTemp.setNext(new ListNode(head1.getData()));
				head1 = head1.getNext();
				head2 = head2.getNext();
				sentinelTemp = sentinelTemp.getNext();
			} else if (head1.getData() < head2.getData()) {
				head1 = head1.getNext();
			} else {
				head2 = head2.getNext();
			}
		}
		return sentinel.getNext();
	}

	// 26. Merge k sorted linked lists
	public ListNode mergeKLists(List<ListNode> listOfLinkedLists) {
		final ListNode sentinel = new ListNode(-1);
		final Queue<ListNode> minHeap = new PriorityQueue<ListNode>();
		for (final ListNode linkedListHead : listOfLinkedLists) {
			minHeap.add(linkedListHead);
		}
		ListNode sentinelTemp = sentinel;
		while (!minHeap.isEmpty()) {
			final ListNode currentNode = minHeap.poll();
			sentinelTemp.setNext(currentNode);
			sentinelTemp = sentinelTemp.getNext();
			if (currentNode.getNext() != null) {
				minHeap.add(currentNode.getNext());
			}
		}
		return sentinel.getNext();
	}
}

class LinkedListRandom {
	int data;
	LinkedListRandom next;
	LinkedListRandom random;

	public LinkedListRandom(int data) {
		super();
		this.data = data;
		this.next = null;
		this.random = null;
	}

	public LinkedListRandom(int data, LinkedListRandom next, LinkedListRandom random) {
		super();
		this.data = data;
		this.next = next;
		this.random = random;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public LinkedListRandom getNext() {
		return next;
	}

	public void setNext(LinkedListRandom next) {
		this.next = next;
	}

	public LinkedListRandom getRandom() {
		return random;
	}

	public void setRandom(LinkedListRandom random) {
		this.random = random;
	}

	public void printLinkedList() {
		LinkedListRandom temp = this;
		System.out.print(temp.data);
		while (temp.next != null) {
			if (temp.next == this) { // return if a loop is present
				System.out.println(" Loop is present at node: " + this.data);
				return;
			}
			if (temp.next == null) {
				break;
			}
			System.out.print("-> [" + temp.next.data);
			if (temp.random != null) {
				System.out.print(", " + temp.random.data + "]");
			} else {
				System.out.print(",]");
			}
			temp = temp.next;

		}
	}
}

class Pair<K, V> {
	K key;
	V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}

class LinkedListADT {
	private int length;
	private ListNode head;

	public LinkedListADT() {
		this.length = 0;
	}

	public int getLength() {
		return this.length;
	}

	public ListNode getHead() {
		return this.head;
	}

	@Override
	public String toString() {
		final var sb = new StringBuilder();
		sb.append(this.head.getData());
		for (ListNode node = head.getNext(); node != null; node = node.getNext()) {
			sb.append(" " + node.getData());
		}
		return sb.toString();
	}

	// ....Algorithms....
	// 1. Insert data into a linkedList
	public void insert(int data, int position) {
		if (position < 0) {
			position = 0;
		} else if (position > length) {
			position = length;
		}
		if (head == null) {
			head = new ListNode(data);
		} else if (position == 0) {
			final var newNode = new ListNode(data);
			newNode.setNext(head);
			head = newNode;
		} else {
			final var newNode = new ListNode(data);
			ListNode temp = head;
			for (var i = 0; i < position - 1; i++) {
				temp = temp.getNext();
			}
			newNode.setNext(temp.getNext());
			temp.setNext(newNode);
		}
		length++;
	}

	// 2. Delete data from a position in linkedList
	public void delete(int position) {
		if (head == null) {
			return;
		}
		if (position < 0) {
			position = 0;
		} else if (position >= length) {
			position = length - 1;
		}
		if (position == 0) {
			head = head.getNext();
		} else {
			ListNode temp = head;
			for (var i = 0; i < position - 1; i++) {
				temp = temp.getNext();
			}
			temp.setNext(temp.getNext().getNext());
		}
		length--;
	}

	// 3. Find position of data in the linkedList
	public int getPosition(int data) {
		if (head == null) {
			return -1;
		}
		if (head.getNext() == null && head.getData() == data) {
			return 0;
		}
		var position = 0;
		for (ListNode node = head; node != null; node = node.getNext()) {
			if (node.getData() == data) {
				return position;
			}
			position++;
		}
		return -1;
	}

	// 4. clear linkedList
	public void clear() {
		this.head = null;
		this.length = 0;
	}

	// 5. Find kth node from the end
	public ListNode findNthNodeFromTheEnd(int k) {
		if (k < 0 || k >= length) {
			return null;
		}
		ListNode temp = head;
		for (var i = 0; i < length - k; i++) {
			temp = temp.getNext();
		}
		return temp;
	}
}