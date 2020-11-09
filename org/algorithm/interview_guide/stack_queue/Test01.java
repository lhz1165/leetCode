package org.algorithm.interview_guide.stack_queue;

import java.util.GregorianCalendar;
import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/11/9
 */
public class Test01 {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		Test01 t = new Test01();
		t.reverse(stack);
		System.out.println(stack);
	}
	/**
	 * 递归来逆序一个栈
	 */
	public int getAndRemoveLast(Stack<Integer> stack) {
		Integer result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		}
		int last = getAndRemoveLast(stack);
		stack.push(result);
		return last;
	}

	public void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLast(stack);
		reverse(stack);
		stack.push(i);
	}
}
