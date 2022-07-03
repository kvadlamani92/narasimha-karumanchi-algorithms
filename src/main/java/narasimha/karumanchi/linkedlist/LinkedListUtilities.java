package narasimha.karumanchi.linkedlist;

public class LinkedListUtilities {
	// ...utility methods...
	public static LinkedListADT createLinkedList() {
		final var linkedListADT = new LinkedListADT();
		linkedListADT.insert(10, 0);
		linkedListADT.insert(11, 1);
		linkedListADT.insert(12, 2);
		linkedListADT.insert(13, 3);
		return linkedListADT;
	}

	public static ListNode createLinkedListWithCycle() {
		final var head = new ListNode(10);
		var listNode = head;
		listNode.setNext(new ListNode(11));
		listNode = listNode.getNext();
		final ListNode listNodeCycleMeetingPoint = new ListNode(12);
		listNode.setNext(listNodeCycleMeetingPoint);
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(13));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(14));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(15));
		listNode = listNode.getNext();
		listNode.setNext(listNodeCycleMeetingPoint);
		return head;
	}

	public static ListNode createLinkedListWithoutCycle() {
		final var head = new ListNode(1);
		var listNode = head;
		listNode.setNext(new ListNode(2));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(3));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(4));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(5));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(6));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(7));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(8));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(9));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(10));
		return head;
	}

	public static ListNode createLinkedListUnsorted() {
		final var head = new ListNode(2);
		var listNode = head;
		listNode.setNext(new ListNode(5));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(4));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(3));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(6));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(3));
		return head;
	}

	public static ListNode createLinkedListSorted() {
		final var head = new ListNode(1);
		var listNode = head;
		listNode.setNext(new ListNode(2));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(3));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(5));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(6));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(7));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(8));
		// listNode = listNode.getNext();
		// listNode.setNext(new ListNode(9));
		// listNode = listNode.getNext();
		// listNode.setNext(new ListNode(10));
		return head;
	}

	public static ListNode createLinkedListWithDuplicates() {
		final var head = new ListNode(1);
		var listNode = head;
		listNode.setNext(new ListNode(2));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(2));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(4));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(7));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(6));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(7));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(8));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(8));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(10));
		return head;
	}

	public static ListNode createLinkedListNumber1() {
		final var head = new ListNode(3);
		var listNode = head;
		listNode.setNext(new ListNode(6));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(7));
		return head;
	}

	public static ListNode createLinkedListNumber2() {
		final var head = new ListNode(4);
		var listNode = head;
		listNode.setNext(new ListNode(5));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(7));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(9));
		return head;
	}

	public static ListNode createCircularList() {
		final var head = new ListNode(1);
		var listNode = head;
		listNode.setNext(new ListNode(2));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(3));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(4));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(5));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(6));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(7));
		listNode = listNode.getNext();
		listNode.setNext(head);
		return head;
	}

	public static ListNode createPalindromeList() {
		final var head = new ListNode(10);
		var listNode = head;
		listNode.setNext(new ListNode(11));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(12));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(12));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(11));
		listNode = listNode.getNext();
		listNode.setNext(new ListNode(10));
		return head;
	}

	public static LinkedListRandom createLinkedListWithRandomPointersWithoutCycle() {
		final var head = new LinkedListRandom(1);
		var listNode = head;
		final var listNode1 = new LinkedListRandom(2);
		final var listNode2 = new LinkedListRandom(3);
		final var listNode3 = new LinkedListRandom(4);
		final var listNode4 = new LinkedListRandom(5);
		final var listNode5 = new LinkedListRandom(6);
		final var listNode6 = new LinkedListRandom(7);
		final var listNode7 = new LinkedListRandom(8);
		final var listNode8 = new LinkedListRandom(9);
		final var listNode9 = new LinkedListRandom(10);
		final var listNode10 = new LinkedListRandom(11);
		listNode.setNext(listNode1);
		listNode.setRandom(listNode2);
		listNode = listNode.getNext();
		listNode.setNext(listNode2);
		listNode.setRandom(listNode3);
		listNode = listNode.getNext();
		listNode.setNext(listNode3);
		listNode.setRandom(listNode4);
		listNode = listNode.getNext();
		listNode.setNext(listNode4);
		listNode.setRandom(listNode5);
		listNode = listNode.getNext();
		listNode.setNext(listNode5);
		listNode.setRandom(listNode6);
		listNode = listNode.getNext();
		listNode.setNext(listNode6);
		listNode.setRandom(listNode7);
		listNode = listNode.getNext();
		listNode.setNext(listNode7);
		listNode.setRandom(listNode8);
		listNode = listNode.getNext();
		listNode.setNext(listNode8);
		listNode.setRandom(listNode9);
		listNode = listNode.getNext();
		listNode.setNext(listNode9);
		listNode.setRandom(listNode10);
		listNode = listNode.getNext();
		listNode.setNext(listNode10);
		listNode.setRandom(head);
		return head;
	}
}
