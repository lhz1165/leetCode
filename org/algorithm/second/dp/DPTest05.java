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
		//d.backPackV2(new int[]{2,2,3},5);
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

	/**
	 * 给出一个都是正整数的数组 nums，其中没有重复的数。从中找出所有的和为 target 的组合个数。
	 *
	 */
	public int backPackVI(int[] nums, int target) {
		// write your code here
		int n = nums.length;
		int[] f = new int[target+1];
		f[0] = 1;
		for (int i = 1; i < target+1; i++) {
			for (int j = 0; j < n; j++) {
				if (i - nums[j] >= 0) {
					f[i] += f[i - nums[j]];
				}
			}

		}
		return f[target];

	}
	/**
	 *有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
	 *
	 * 问最多能装入背包的总价值是多大?
	 */
	public int backPackII(int m, int[] A, int[] V) {
		// write your code here
		//前n个物品 拼出V重量的
		int n = A.length;
		int[][] f = new int[n + 1][m + 1];
		f[0][0] = 0;
		for (int i = 0; i <= m; i++) {
			f[0][i] = -1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				f[i][j] = f[i - 1][j];
				if ( j - A[i - 1] >= 0&&f[i - 1][j - A[i - 1]]!=-1) {
					f[i][j] = Math.max(f[i][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
				}
			}
		}
		int res = 0;
		for (int i = m; i >= 0; i--) {
			if (f[n][i] != -1) {
				res = Math.max(res, f[n][i]);
			}
		}
		return res;


	}
	/**
	 * 给定 n 种物品, 每种物品都有无限个. 第 i 个物品的体积为 A[i], 价值为 V[i].
	 *
	 * 再给定一个容量为 m 的背包. 问可以装入背包的最大价值是多少?
	 *
	 * f[i][w]  前i【种】拼出w的最大总价值
	 * */
	public int backPackIII(int[] A, int[] V, int m) {
		int n = A.length;
		int[][] f = new int[n + 1][m + 1];
		f[0][0] = 0;
		for (int i = 0; i <= m; i++) {
			f[0][i] = -1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				f[i][j] = f[i - 1][j];
				if ( j - A[i - 1] >= 0&&f[i - 1][j - A[i - 1]]!=-1) {
					f[i][j] = Math.max(f[i][j], f[i][j - A[i - 1]] + V[i - 1]);
				}
			}
		}
		int res = 0;
		for (int i = m; i >= 0; i--) {
			if (f[n][i] != -1) {
				res = Math.max(res, f[n][i]);
			}
		}
		return res;
	}



}
