package org.algorithm.interview_guide.stack_queue;

import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/11/29
 */
public class MyStack {
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(0);
		System.out.println(stack.getMin());
		stack.pop();
		System.out.println(stack.getMin());



	}


	Stack<Integer> stackData;
	Stack<Integer> stackMin;
	public MyStack() {
		stackData = new Stack<>();
		stackMin = new Stack<>();
	}



	public void push(int newNum){
		if (stackMin.isEmpty() || newNum <= getMin()) {
			stackMin.push(newNum);
		}
		stackData.push(newNum);
	}

	public int pop() {
		int val = stackData.pop();
		if (val == getMin()) {
			stackMin.pop();
		}
		return val;
	}


	public int getMin() {
		return stackMin.peek();
	}



}
