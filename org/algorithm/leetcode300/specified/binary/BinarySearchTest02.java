package org.algorithm.leetcode300.specified.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/11/8
 */
public class BinarySearchTest02 {
	/**
	 * 找峰值 II · Find Peak Element II
	 * 输入:
	 *     [
	 *       [1, 2, 3, 6,  5],
	 *       [16,41,23,22, 6],
	 *       [15,17,24,21, 7],
	 *       [14,18,19,20,10],
	 *       [13,14,11,10, 9]
	 *     ]
	 * 输出: [1,1]
	 * 解释: [2,2] 也是可以的. [1,1] 的元素是 41, 大于它四周的每一个元素 (2, 16, 23, 17).
	 *
	 * 使用二维来作为二分
	 * 1. 找到中间行的最大值a[i][j]
	 * 2. a[i][j]的上下比较
	 * 3. 这样能找到最大的在上部分还是下部分，变成n/2*m的问题
	 * 循环
	 */
	public List<Integer> findPeakII(int[][] A) {
		List<Integer> res = new ArrayList<>();
		int n = A.length;
		int start = 0;
		int end = n - 1;
		while (start + 1 < end) {
			int mid = end - (start + end) / 2;
			//这一行的最大值
			int maxIndex = find_maxCol(A, mid);
			//比较上下
			if (A[mid][maxIndex] < A[mid-1][maxIndex]) {
				end = mid;
			}else if (A[mid][maxIndex] > A[mid-1][maxIndex]){
				start = mid;
			}else {
				res.add(mid);
				res.add(maxIndex);
			}
		}
		int bottom_index = find_maxCol(A, start);
		int up_index = find_maxCol(A, end);
		if (A[start][up_index] < A[end][bottom_index]) {
			return new ArrayList<>(Arrays.asList(end, bottom_index));
		}else {
			return new ArrayList<>(Arrays.asList(start, up_index));
		}




	}
	//find_maxCol用于找到当前行最大值所在列
	private int find_maxCol(int[][] A, int row) {
		int col = 0;
		int m = A[0].length;
		for (int i = 1; i < m; i++) {
			if (A[row][col] < A[row][i]) col = i;
		}
		return col;

	}

	public static void main(String[] args) {
		BinarySearchTest02 b = new BinarySearchTest02();
		b.findDuplicate(new int[]{5,5,4,3,2,1});
	}
	/**
	 * 木材加工
	 * 有一些原木，现在想把这些木头切割成一些长度相同的小段木头，
	 * 需要得到的小段的数目至少为 k。当然，我们希望得到的小段越长越好，
	 * 你需要计算能够得到的小段木头的最大长度。
	 *
	 * 输入:
	 * L = [232, 124, 456]
	 * k = 7
	 * 输出: 114
	 * Explanation: 我们可以把它分成114cm的7段，而115cm不可以
	 */
	public int woodCut(int[] L, int k) {
		// write your code here
		int start = 1;
		int end = maxWood(L);
		while (start + 1 < end) {
			int mid = end - (end - start) / 2;
			int sum = woodsCount(L, mid);
			if (sum > k) {
				start = mid;
			} else if (sum < k) {
				end = mid;
			}else {
				start = mid;
			}
		}

		if (woodsCount(L, end)==k) {
			return end;
		}
		if (woodsCount(L, start)==k) {
			return start;
		}
		return -1;


	}

	public int maxWood(int[] L) {
		int maxVal = Integer.MIN_VALUE;
		for (int wood : L) {
			maxVal = Math.max(maxVal, wood);
		}
		return maxVal;
	}

	public int woodsCount(int[] L, int per) {
		int sum = 0;
		for (int wood : L) {
			sum += (wood / per);
		}
		return sum;
	}
	/**
	 * 给出一个数组 nums 包含 n + 1 个整数，
	 * 每个整数是从 1 到 n (包括边界)，
	 * 保证至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数
	 * 输入:
	 * [5,5,4,3,2,1]
	 * 输出:
	 * 5
	 */
	public int findDuplicate(int[] nums) {
		// write your code here
		int l = 1;
		int r = nums.length - 1;  // n

		while (l + 1 < r) {
			int mid = l + (r - l) / 2;
			if (findLess(nums, mid) <= mid) {
				l = mid;
			} else {
				r = mid;
			}
		}

		if (findLess(nums, l) <= l) {
			return r;
		}
		return l;

	}

	public int findLess(int[] nums,int n) {
		int count = 0;
		for (int num : nums) {
			if (num <= n) {
				count++;
			}
		}
		return count;
	}




}
