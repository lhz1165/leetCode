package org.algorithm.leetcode.nomal.test.everyday;

import java.util.Stack;

/**
 * @author: lhz
 * @date: 2021/3/29
 **/
public class EveryDay09 {
	public static void main(String[] args) {
		EveryDay09 e = new EveryDay09();

		System.out.println(e.reverseBits(1));
		//[[1,3,5,7],[10,11,16,20],[23,30,34,60]]
		//13
		//System.out.println(e.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 5));
		System.out.println(e.clumsy(10));
	}

	public int reverseBits(int n) {
		int res = 0;
		int flag = 1;
		for (int i = 0; i < 32; i++) {
			res += n & flag;
			if (i != 31) {
				res <<= 1;
				n >>= 1;
			}
		}
		return res;
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int n = matrix.length;
		int m = matrix[0].length;
		int num = n * m - 1;
		int start = 0;
		int end = num;
		while (start <= end) {
			int mid = (start + end) / 2;
			int i = mid / m;
			int j = mid % m;
			if (matrix[i][j] == target) {
				return true;
			} else if (matrix[i][j] > target) {
				end = mid;
			} else {
				start = mid;
			}

		}
		return false;

	}

	//1006. 笨阶乘
	public int clumsy(int N) {
		Stack<Integer> stack = new Stack<>();
		int flag = 0;
		stack.push(N);
		for (int i = N - 1; i >= 1; i--) {
			if (flag == 0) {
				stack.push(stack.pop() * i);
			} else if (flag == 1) {
				stack.push(stack.pop() / i);
			} else if (flag == 2) {
				stack.push(i);
			} else {
				stack.push(-i);
			}
			flag++;
			flag %= 4;
		}
		int res = 0;
		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		return res;

	}
}
