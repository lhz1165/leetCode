package org.algorithm.leetcode.specified.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author lhzlhz
 * @create 25/9/2021
 */
public class StackTest02 {
	public static void main(String[] args) {
		StackTest02 s = new StackTest02();
		int[] ints = s.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
		System.out.println(Arrays.asList(ints));
	}

	public int[] dailyTemperatures(int[] temperatures) {
		int n = temperatures.length;
		if (n == 0) {
			return new int[0];
		}
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[n];
		for (int i = temperatures.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && temperatures[stack.peek()]< temperatures[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				result[i] = 0;
			}else {
				result[i] = stack.peek() - i;
			}
			stack.push(i);
		}
		return result;
	}
}
