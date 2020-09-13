package org.algorithm.second.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/9/13
 * 背包问题
 */
public class DPTest05 {
	public static void main(String[] args) {
		DPTest05 d = new DPTest05();
		d.backPackV2(new int[]{2,2,3},5);
	}

	/**
	 * 在n个物品中挑选若干物品装入背包，最多能装多满？
	 * 假设背包的大小为m，每个物品的大小为A[i]
	 * <p>
	 * 以重量作为数组，看看能不能带走这么多重量的
	 * 最后一步就是最后一个物品是否能进入背包
	 * <p>
	 * <p>
	 * 思路
	 */
	public int backPack(int m, int[] A) {
		// write your code here
		//能否用前i个物品拼出w
		int n = A.length;
		boolean[][] f = new boolean[n + 1][m + 1];
		f[0][0] = true;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < m + 1; j++) {
				f[i][j] = f[i - 1][j];
				if (j >= A[i - 1] && f[i - 1][j - A[i - 1]]) {
					f[i][j] = true;
				}
			}
		}
		for (int i = m; i >= 0; i--) {
			if (f[n][i]) {
				return i;
			}
		}
		return 1;

	}
	//滚动数组版本
	public int backPack2(int m, int[] A) {
		// write your code here
		//能否用前i个物品拼出w
		int n = A.length;
		boolean[] f = new boolean[m + 1];
		f[0] = true;
		for (int i = 1; i <= n; i++) {

			for (int j = m; j >=1 ; j--) {
				if (j >= A[i - 1] && f[j-A[i-1]]){
					f[j] = true;
				}
			}
		}
		int res = 0;
		for (int i = m; i >=0; i--) {
			if (f[i]) {
				res = i;
				break;
			}
		}
		return res;
	}

	/**
	 *给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小,
	 * 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
	 * 每一个物品只能使用一次
	 *f[i][j]=f[i-1][j]+f[i-1][j - nums[j - i - 1]]
	 * 加上他等于目标值 或者 不加他等于目标是
	 */
	public int backPackV(int[] nums, int target) {
		// write your code here
		//前i个物品，得到target的方案数
		int n = nums.length;
		int[][] f = new int[n+1][target+1];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < target + 1; j++) {
				if (i == 0 && j == 0) {

					f[i][j] = 1;
					continue;
				}
				if (i == 0) {
					f[i][j] = 0;
					continue;
				}
				f[i][j] += f[i - 1][j];
				if (j - nums[i-1] >= 0) {
					f[i][j] += f[i - 1][j - nums[i-1]];
				}
			}
		}
		return f[n][target];

	}

	public int backPackV2(int[] nums, int target) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return 0;
		}
		//dfs
		Arrays.sort(nums);
		subHelper(target, results, new ArrayList<Integer>(), 0, nums, 0);
		return results.size();
	}

	private void subHelper(int target, List<List<Integer>> results, List<Integer> result, int curSum, int[] candidates, int index) {
		if (curSum == target) {
			results.add(new ArrayList<>(result));
			return;
		} else if (curSum > target) {
			return;
		}
		for (int i = index; i < candidates.length; i++) {
			//节省时间复杂度，只要这个大了，下面的一定也都大了不接着遍历
			if (curSum + candidates[i] > target) {
				break;
			}
			result.add(candidates[i]);
			subHelper(target, results, result, curSum + candidates[i], candidates, i + 1);
			result.remove(result.size() - 1);
		}
	}

}
