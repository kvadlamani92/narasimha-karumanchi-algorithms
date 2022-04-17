package narasimha.karumanchi.linkedlist;

public class DoublyLinkedListMain {
	public static void main(String[] args) {
		final var doublyLinkedListMain = new DoublyLinkedListMain();
		// doublyLinkedListMain.runInsert();
		// doublyLinkedListMain.runDeleteFromList();
		// doublyLinkedListMain.runFindPosition();
		// doublyLinkedListMain.runClear();
	}

	// ...utility methods...
	private DoublyLinkedListADT createDoublyLinkedList() {
		final var doublyLinkedListADT = new DoublyLinkedListADT();
		doublyLinkedListADT.insert(10, 0);
		doublyLinkedListADT.insert(11, 1);
		doublyLinkedListADT.insert(12, 2);
		doublyLinkedListADT.insert(13, 3);
		doublyLinkedListADT.insert(14, 2);
		return doublyLinkedListADT;
	}

	// ....Runners....
	// 1. Run insert()
	public void runInsert() {
		final var linkedListADT = createDoublyLinkedList();
		System.out.println(linkedListADT.toString());
	}

	// 2. Run deleteFromList
	public void runDeleteFromList() {
		final var linkedListADT = createDoublyLinkedList();
		System.out.println(linkedListADT.toString());
		linkedListADT.delete(4);
		System.out.println(linkedListADT.getHead().getData());
		System.out.println(linkedListADT.getTail().getData());
		System.out.println(linkedListADT.toString());

	}

	// 3. Run findPosition
	public void runFindPosition() {
		final var doublyLinkedListADT = createDoublyLinkedList();
		System.out.println(doublyLinkedListADT.getPosition(12));
	}

	// 4. Run clear
	public void runClear() {
		final var doublyLinkedListADT = createDoublyLinkedList();
		doublyLinkedListADT.clear();
		System.out.println(doublyLinkedListADT.getHead());
		System.out.println(doublyLinkedListADT.getTail());
		System.out.println(doublyLinkedListADT.getLength());
	}
}

class DoublyLinkedListADT {
	private int length;
	private DLLNode head;
	private DLLNode tail;

	public DoublyLinkedListADT() {
		this.length = 0;
	}

	public int getLength() {
		return this.length;
	}

	public DLLNode getHead() {
		return this.head;
	}

	public DLLNode getTail() {
		return this.tail;
	}

	@Override
	public String toString() {
		final var sb = new StringBuilder();
		sb.append(this.head.getData());
		for (DLLNode node = head.getNext(); node != null; node = node.getNext()) {
			sb.append(" " + node.getData());
		}
		return sb.toString();
	}

	// ....Algorithms....
	// 1. Insert data into a DoublyLinkedList
	public void insert(int data, int position) {
		// fix position
		if (position < 0) {
			position = 0;
		} else if (position > length) {
			position = length;
		}
		if (head == null) { // if the Doubly Linked List is empty
			head = new DLLNode(null, data, null);
			tail = head;
		} else if (position == 0) { // insertion at the beginning of the DLL
			final var newNode = new DLLNode(data);
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		} else if (position == length) { // insertion at the end of the DLL
			final var newNode = new DLLNode(data);
			while (tail.getNext() != null) {
				tail = tail.getNext();
			}
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		} else { // insertion in-between the DLL
			final var newNode = new DLLNode(data);
			DLLNode temp = head;
			for (var i = 0; i < position - 1; i++) {
				temp = temp.getNext();
			}
			newNode.setPrev(temp);
			newNode.setNext(temp.getNext());
			if (temp.getNext() != null) {
				temp.getNext().setPrev(newNode);
			}
			temp.setNext(newNode);
		}
		length++;
	}

	// 2. Delete data from a position in linkedList
	public void delete(int position) {
		if (head == null) {
			return;
		}
		if (position <= 0) { // Delete from the beginning
			head.getNext().setPrev(null);
			head = head.getNext();
			length--;
			return;
		}
		if (position >= length - 1) { // Delete from the end
			tail = tail.getPrev();
			tail.setNext(null);
			length--;
			return;
		}
		// Delete from in-between the doubly linked list
		DLLNode temp = null;
		if (position <= length / 2) {
			temp = head;
			for (var i = 0; i < position - 1; i++) {
				temp = temp.getNext();
			}
		} else {
			temp = tail;
			for (var i = length - position - 1; i >= 0; i--) {
				temp = temp.getPrev();
			}
		}
		if (temp.getNext() != null) {
			if (temp.getNext().getNext() != null) {
				temp.getNext().getNext().setPrev(temp);
			}
			temp.setNext(temp.getNext().getNext());
		}

		length--;
	}

	//
	// // 3. Find position of data in the linkedList
	public int getPosition(int data) {
		if (head == null) {
			return -1;
		}
		if (head.getNext() == null && head.getData() == data) {
			return 0;
		}
		if (tail.getNext() == null && tail.getData() == data) {
			return length - 1;
		}
		var position = 0;
		for (DLLNode headNode = head, tailNode = tail; headNode != null
				&& tailNode != null; headNode = headNode.getNext(), tailNode = tailNode.getPrev()) {
			if (headNode.getData() == data) {
				return position;
			} else if (tailNode.getData() == data) {
				return length - position - 1;
			}
			position++;
		}
		return -1;
	}

	// 4. clear
	public void clear() {
		this.head = null;
		this.tail = null;
		this.length = 0;
	}
}

class DLLNode {
	private int data;
	private DLLNode prev;
	private DLLNode next;

	public DLLNode(int data) {
		this.data = data;
	}

	public DLLNode(DLLNode prev, int data, DLLNode next) {
		this.prev = prev;
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public DLLNode getPrev() {
		return this.prev;
	}

	public void setPrev(DLLNode prev) {
		this.prev = prev;
	}

	public DLLNode getNext() {
		return this.next;
	}

	public void setNext(DLLNode next) {
		this.next = next;
	}
}