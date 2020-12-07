package org.algorithm.interview_guide.stack_queue;

import java.time.chrono.MinguoChronology;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
		int i = getAndRemoveLastEle(stack);//1
		reverse(stack);
		stack.push(i);
	}


	/**
	 * 两个栈排序
	 */
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(6);
		stack.push(3);
		stack.push(4);
		stack.push(2);
		stack.push(5);
		Test1130 t = new Test1130();
		int[] max = t.getMaxWindow(new int[]{4,3,5,4,3,3,6,7}, 3);
		System.out.println(Arrays.toString(max));

	}

	public int  fibonicc(int n) {

		if (n == 1 || n ==2) {
			return 1;
		}

		int x = fibonicc(n - 1);//n-2  n -3
		int y = fibonicc(n - 2);//n-3 n-4
		return x + y;
	}

	/**
	 * 用一个栈，实现另一个栈的排序
	 * @param stack
	 */
	public void sortStack(Stack<Integer> stack) {
		Stack<Integer> helper = new Stack<>();

		while (!stack.isEmpty()) {
			Integer cur = stack.pop();
			//helper [] ==> helper.peek()
			while (!helper.isEmpty() && helper.peek() < cur) {
				Integer h = helper.pop();
				stack.push(h);
			}
			helper.push(cur);
		}

		while (!helper.isEmpty()) {
			Integer h = helper.pop();
			stack.push(h);
		}

	}
	// 4 3 5 4 3 3 6 7
	public int[] getMax(int[] arr, int w) {
		int n = arr.length - w + 1;
		int[] result = new int[n];
		for (int i = 0; i < n-w; i++) {
			int max = Integer.MIN_VALUE;
			//4 3 5
			//0 1 2
			for (int j = 0; j < w; j++) {
				//i+j = 0+0  0+1  0+2
				//i+j = 1+0   1+1  1+2
				int index = i + j;
				max = Math.max(max, arr[index]);
			}
			result[i] = max;
		}

		return result;

	}

	public int[] getMaxWindow(int[] arr, int w) {

		LinkedList<Integer> queue = new LinkedList<>();

		int n = arr.length;

		int[] result =new int[n - w +1];
		int index = 0;
		for (int i = 0; i < n; i++) {

			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
				queue.pollLast();
			}
			queue.addLast(i);
			//检查头元素是否已经走过了
			if (queue.peekFirst() == i - w) {
				queue.pollFirst();
			}

			//给result赋值
			if (i >= w - 1) {
				Integer res = queue.peekFirst();
				result[index] =arr[res];
				index++;
			}
		}

		return result;
	}











}
