package org.algorithm.interview_guide.stack_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/11/11
 */
public class Test02 {
	public static void main(String[] args) {
		Test02 t = new Test02();
		int[][] ints = t.rightWay(new int[]{3, 4, 1, 5, 6, 2, 7});
		System.out.println();
	}

	/**
	 * 单调栈结构
	 * 给定一个不含重复值的数组，找到每一个i位置 左右两边比他小的数
	 * [3,4,1,5,6,2,7]
	 * [-1,2],[0,2],[-1,-1],[2,5],[3,5],[2,-1],[5,-1]
	 *
	 * 如果找当前位置i 左右比他小的最近的
	 * 那么栈必须以从上到下  从大到小排列
	 * 如果不是 那么弹出一个最上的位置j
	 * j的右边最近就是当前i位置的值，j左边最近就是j挨着下面的值
	 */
	public int[][] rightWay(int[] arr){
		Stack<Integer> stack = new Stack<>();
		int[][] results = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int num = arr[i];
			while (!stack.isEmpty() && arr[stack.peek()] > num) {
				Integer popIndex = stack.pop();
				int leftIndex = stack.isEmpty() ? -1 : stack.peek();
				results[popIndex][0] = leftIndex;
				results[popIndex][1] = i;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			Integer popIndex = stack.pop();
			int leftIndex = stack.isEmpty() ? -1 : stack.peek();
			results[popIndex][0] = leftIndex;
			results[popIndex][1] = -1;
		}
		return results;
	}
}
