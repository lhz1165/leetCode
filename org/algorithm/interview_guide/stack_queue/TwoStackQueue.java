package org.algorithm.interview_guide.stack_queue;

import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/11/9
 */
public class TwoStackQueue {
	Stack<Integer> stackPush;
	Stack<Integer> stackPop;

	public TwoStackQueue() {
		this.stackPush = new Stack<>();
		this.stackPop = new Stack<>();
	}

	public void pushToPop() {
		if (stackPop.isEmpty()) {
			while (!stackPush.isEmpty()) {
				stackPop.push(stackPush.pop());
			}
		}
	}

	public void add(int x) {
		stackPush.push(x);
		pushToPop();
	}

	public int poll() {
		pushToPop();
		return stackPop.pop();
	}

	public static void main(String[] args) {
		TwoStackQueue q = new TwoStackQueue();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		System.out.println(q.poll());
		q.add(5);
		q.add(6);
		System.out.println(q.poll());
		System.out.println(q.poll());
		q.add(7);
	}
}
