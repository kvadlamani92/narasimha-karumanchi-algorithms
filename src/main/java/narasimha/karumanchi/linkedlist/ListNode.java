package narasimha.karumanchi.linkedlist;

public class ListNode implements Comparable {
	private int data;
	private ListNode next;

	public ListNode(int data) {
		this.data = data;
	}

	public ListNode(int data, ListNode next) {
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public ListNode getNext() {
		return this.next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public void printLinkedList() {
		ListNode temp = this;
		System.out.print(temp.data);
		while (temp.next != null) {
			if (temp.next == this) { // return if a loop is present
				System.out.println(" Loop is present at node: " + this.data);
				return;
			}
			System.out.print("->" + temp.next.data);
			temp = temp.next;

		}
	}

	@Override
	public int compareTo(Object o) {
		final ListNode other = (ListNode) o;
		return this.data > other.getData() ? 1 : (this.data < other.getData() ? -1 : 0);
	}
}
