package org.algorithm.interview_guide.stack_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/11/29
 */
public class Test1129 {
	public static void main(String[] args) {
//
//		Stack<Integer> stack = new Stack<>();
//		stack.push(1);
//		stack.push(2);
//		stack.push(3);
//		System.out.println(stack.pop());
//		System.out.println(stack.pop());
//		System.out.println(stack.pop());
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		System.out.println(queue.poll());

	}
}
