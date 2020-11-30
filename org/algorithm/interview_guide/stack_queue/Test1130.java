package org.algorithm.interview_guide.stack_queue;

import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/11/30
 */
public class Test1130 {
	/**
	 * 仅用递归函数和栈  逆序栈
	 */

	public int getAndRemoveLastEle(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		}else {
			int last = getAndRemoveLastEle(stack);
			stack.push(result);
			return last;
		}
	}

	public void reverse(Stack<Integer>stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLastEle(stack);
		reverse(stack);
		stack.push(i);
	}


	/**
	 * 两个栈排序
	 */
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		Test1130 t = new Test1130();
		t.reverse(stack);
		System.out.println();

	}

	public int  fibonicc(int n) {

		if (n == 1 || n ==2) {
			return 1;
		}

		int x = fibonicc(n - 1);//n-2  n -3
		int y = fibonicc(n - 2);//n-3 n-4
		return x + y;
	}

}
