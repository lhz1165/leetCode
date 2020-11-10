package org.algorithm.interview_guide.stack_queue;

import java.util.*;
import java.util.concurrent.DelayQueue;

/**
 * @author lhzlhz
 * @create 2020/11/9
 */
public class Test01 {

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

	/**
	 * 利用两个栈来排序
	 * @param stack
	 */
	public void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> helper = new Stack<>();
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			while (!helper.isEmpty() && cur < helper.peek()) {
				int h = helper.pop();
				stack.push(h);
			}
			helper.push(cur);
		}
		//再把helper的放回去
		while (!helper.isEmpty()) {
			stack.push(helper.pop());
		}
	}

	/**
	 * 滑动窗口的最大值
	 * @param arr
	 * @param w
	 * @return
	 */
	public List<Integer> getMaxWindow(int[] arr, int w) {
		//存放下标的
		Deque<Integer> deque = new LinkedList<>();
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			while (!deque.isEmpty()&& arr[i] >= arr[deque.peekLast()]) {
				deque.pollLast();
			}
			deque.offerLast(i);
			if (i -w  == deque.peekFirst()) {
				deque.pollFirst();
			}
			if (i >= w - 1) {
				result.add(arr[deque.peekFirst()]);
			}
		}
		return result;
	}


	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(4);
		stack.push(3);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(3);
		stack.push(6);
		stack.push(7);
		Test01 t = new Test01();
		List<Integer> maxWindow = t.getMaxWindow(new int[]{4, 3, 5, 4, 3, 3, 6, 7}, 3);
		System.out.println(stack);
	}




}
