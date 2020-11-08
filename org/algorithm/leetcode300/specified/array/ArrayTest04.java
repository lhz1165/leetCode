package org.algorithm.leetcode300.specified.array;

import java.util.*;

/**
 * @author lhzlhz
 * @create 2020/11/8
 */
public class ArrayTest04 {
	/**
	 *
	 * 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
	 * 输入:
	 * [1,2,7,7,8]
	 * 3
	 * 输出:
	 * [7,7,8]
	 * 方法一：两个for 遍历k个数组
	 * 方法二：map 每次加一个 删一个
	 */
	void inQueue(Deque<Integer> deque, int num) {
		while (!deque.isEmpty() && deque.peekLast() < num) {
			deque.pollLast();
		}
		deque.offer(num);
	}

	void outQueue(Deque<Integer> deque, int num) {
		if (deque.peekFirst() == num) {
			deque.pollFirst();
		}
	}
	public static void main(String[] args) {
		ArrayTest04 a = new ArrayTest04();
		a.maxSlidingWindow(new int[]{1, 9, 0,7, 7, 8,10}, 4);
	}
	public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
		// write your code here
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Deque<Integer> deque = new ArrayDeque<Integer>();
		if (nums.length == 0) {
			return ans;
		}
		for (int i = 0; i < k ; i++) {
			inQueue(deque, nums[i]);
		}

		for(int i = k - 1; i < nums.length; i++) {
			//如果这个最大的那么移除
			inQueue(deque, nums[i]);
			//第一位永远存最大的
			ans.add(deque.peekFirst());

			//第一个是否不在窗口里面了
			outQueue(deque, nums[i - k + 1]);
		}
		return ans;
	}






}
