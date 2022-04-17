package narasimha.karumanchi.linkedlist;

public class CircularLinkedListMain {
	public static void main(String[] args) {
		final var circularLinkedListMain = new CircularLinkedListMain();
		// circularLinkedListMain.runInsert();
		// circularLinkedListMain.runDeleteFromList();
		// circularLinkedListMain.runFindPosition();
		// circularLinkedListMain.runClear();
	}

	// ...utility methods...
	private CircularLinkedListADT createLinkedList() {
		final var linkedListADT = new CircularLinkedListADT();
		linkedListADT.insert(10, 0);
		linkedListADT.insert(11, 1);
		linkedListADT.insert(12, 2);
		linkedListADT.insert(13, 3);
		linkedListADT.insert(14, 2);
		return linkedListADT;
	}

	// ....Runners....
	// 1. Run insert()
	public void runInsert() {
		final var linkedListADT = createLinkedList();
		System.out.println(linkedListADT.toString());
	}

	// 2. Run deleteFromList
	public void runDeleteFromList() {
		final var linkedListADT = createLinkedList();
		System.out.println(linkedListADT.toString());
		linkedListADT.delete(2);
		System.out.println("head: " + linkedListADT.getHead().getData());
		System.out.println("next: " + linkedListADT.getHead().getNext().getData());
		System.out.println(linkedListADT.toString());

	}

	// 3. Run findPosition
	public void runFindPosition() {
		final var linkedListADT = createLinkedList();
		System.out.println(linkedListADT.getPosition(13));
	}

	// 4. Run clear
	public void runClear() {
		final var linkedListADT = createLinkedList();
		linkedListADT.clear();
		System.out.println(linkedListADT.getHead());
		System.out.println(linkedListADT.getLength());
	}
}

class CircularLinkedListADT {
	private int length;
	private CLLNode head;

	public CircularLinkedListADT() {
		this.length = 0;
	}

	public int getLength() {
		return this.length;
	}

	public CLLNode getHead() {
		return this.head;
	}

	@Override
	public String toString() {
		final var sb = new StringBuilder();
		sb.append(this.head.getData());
		CLLNode node = head.getNext();
		while (node != head) {
			sb.append(" " + node.getData());
			node = node.getNext();
		}

		return sb.toString();
	}

	// ....Algorithms....
	// 1. Insert data into a circular linked ist
	public void insert(int data, int position) {
		// fix position
		if (position < 0) {
			position = 0;
		} else if (position > length) {
			position = length;
		}
		if (head == null) { // if the Circular Linked List is empty
			head = new CLLNode(data);
		} else if (position == 0) { // insertion at the beginning of the CLL
			final var newNode = new CLLNode(data);
			CLLNode lastNode = head;
			while (lastNode.getNext() != head) {
				lastNode = lastNode.getNext();
			}
			lastNode.setNext(newNode);
			newNode.setNext(head);
			head = newNode;
		} else if (position == length) { // insertion at the end of the DLL
			final var newNode = new CLLNode(data);
			CLLNode lastNode = head;
			while (lastNode.getNext() != head) {
				lastNode = lastNode.getNext();
			}

			lastNode.setNext(newNode);
			newNode.setNext(head);
		} else {
			// insertion in-between the CLL
			final var newNode = new CLLNode(data);
			CLLNode temp = head;
			for (var i = 0; i < position - 1; i++) {
				temp = temp.getNext();
			}
			newNode.setNext(temp.getNext());
			temp.setNext(newNode);
		}
		length++;
	}

	//
	// // 2. Delete data from a position in linkedList
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
			CLLNode lastNode = head.getNext();
			while (lastNode.getNext() != head) {
				lastNode = lastNode.getNext();
			}
			lastNode.setNext(head.getNext());
			head = head.getNext();
		} else {
			CLLNode temp = head;
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
		if (head.getData() == data) {
			return 0;
		}
		var position = 1;
		for (CLLNode node = head.getNext(); node != head; node = node.getNext()) {
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
}

class CLLNode {
	private int data;
	private CLLNode next;

	public CLLNode(int data) {
		this.data = data;
		this.next = this;
	}

	public CLLNode(int data, CLLNode next) {
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public CLLNode getNext() {
		return this.next;
	}

	public void setNext(CLLNode next) {
		this.next = next;
	}
}