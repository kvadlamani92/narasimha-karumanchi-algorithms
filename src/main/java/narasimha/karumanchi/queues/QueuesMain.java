package narasimha.karumanchi.queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueuesMain {
	public static void main(String[] args) throws Exception {
		final var queuesMain = new QueuesMain();
		// queuesMain.runCreateQueue();
		queuesMain.runInterleaveElements();
	}

	// ....Arrays Runners....
	// 1. Run remove adjacent duplicates recursively
	public static void runCreateQueue() throws Exception {
		final QueueADT queue = QueueUtils.createQueue();
		queue.printQueue();
	}

	// 2. Interleave elements of 1st half with 2nd half
	public void runInterleaveElements() {
		// if you use priority queue instad of linked list, the order would
		// always be sorted when printed as it uses heap internally and
		// re-arranges the elements when polled
		final Queue<Integer> queue = new LinkedList<>();
		queue.add(11);
		queue.add(12);
		queue.add(13);
		queue.add(14);
		queue.add(15);
		queue.add(16);
		queue.add(17);
		queue.add(18);
		queue.add(19);
		queue.add(20);
		final Queue<Integer> resultQueue = interleaveElements(queue);
		System.out.println("queue size " + resultQueue.size());
		while (!resultQueue.isEmpty()) {
			System.out.println(resultQueue.poll());
		}
	}

	// ....Algorithms....

	// 1. Interleave the first half with second half in a queue
	// [11,12,13,14,15,16,17,18,19,20] -> [11,16,12,17,13,18,14,19,15,20]
	public Queue<Integer> interleaveElements(Queue<Integer> queue) {
		final int n = queue.size();
		if (n % 2 != 0) {
			return new LinkedList<>();
		}
		final int halfSize = n / 2;
		final Stack<Integer> stack = new Stack<>();

		// queue: [16,17,18,19,20] stack: [11,12,13,14,15 (T)]
		for (var i = 0; i < halfSize; i++) {
			stack.push(queue.poll());
		}

		// queue: [16,17,18,19,20,15,14,13,12,11] stack: []
		for (var i = 0; i < halfSize; i++) {
			queue.add(stack.pop());
		}

		// queue: [15,14,13,12,11,16,17,18,19,20] stack: []
		for (var i = 0; i < halfSize; i++) {
			queue.add(queue.poll());
		}

		// queue: [16,17,18,19,20] stack: [15,14,13,12,11 (T)]
		for (var i = 0; i < halfSize; i++) {
			stack.push(queue.poll());
		}

		// queue: [11,16,12,17,13,18,14,19,15,20] stack: []
		for (var i = 0; i < halfSize; i++) {
			queue.add(stack.pop());
			queue.add(queue.poll());
		}
		return queue;
	}

}

class QueueUtils {
	public static QueueADT createQueue() throws Exception {
		final QueueADT queue = new QueueADT();
		queue.enQueue(11);
		queue.enQueue(12);
		queue.enQueue(13);
		queue.enQueue(14);
		queue.enQueue(15);
		queue.enQueue(16);
		queue.enQueue(17);
		queue.enQueue(18);
		queue.enQueue(19);
		queue.enQueue(20);
		return queue;
	}
}

class QueueADT {
	private int CAPACITY = 3;
	int front;
	int rear;
	int size;
	int[] arr;

	public QueueADT() {
		arr = new int[CAPACITY];
		front = 0;
		rear = 0;
		size = 0;
	}

	public void enQueue(int data) {
		arr[rear % CAPACITY] = data;
		size++;
		if (size == CAPACITY) {
			expand();
		}
		rear = (rear + 1) % CAPACITY;
	}

	public int deQueue() throws Exception {
		if (size == 0) {
			throw new Exception();
		}
		final int temp = arr[front];
		arr[front % CAPACITY] = Integer.MIN_VALUE;
		front = (front + 1) % CAPACITY;
		size--;
		return temp;
	}

	public void expand() {
		final var newArray = new int[CAPACITY << 1];
		for (int i = front; i <= rear; i++) {
			newArray[i - front] = arr[i];
		}
		arr = newArray;
		CAPACITY <<= 1;
		rear = size - 1;
	}

	public void printQueue() {
		if (size != 0) {
			System.out.print(arr[front]);
			for (int i = front + 1; i < rear; i++) {
				System.out.print("," + arr[i]);
			}
		}
	}

	public int size() {
		return this.size;
	}
}