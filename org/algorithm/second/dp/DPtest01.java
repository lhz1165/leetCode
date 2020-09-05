package org.algorithm.second.dp;

import org.algorithm.leetcode300.basic.TreeNode;

/**
 * @author lhzlhz
 * @create 2020/9/5
 *
 *初识动态规划
 */
public class DPtest01 {
	public static void main(String[] args) {
		DPtest01 d = new DPtest01();
		System.out.println(d.coinChange(new int[]{2, 5, 7}, 20));
		d.uniquePaths(1, 3);

	}

	/**
	 *
	 *
	 * 换硬币
	 * 给出不同面额的硬币以及一个总金额. 写一个方法来计算给出的总金额可以换取的最少的硬币数量.
	 * 如果已有硬币的任意组合均无法与总金额面额相等, 那么返回 -1
	 *
	 *
	 * 输入：
	 * [1, 2, 5]
	 * 11
	 * 输出： 3
	 * 解释： 11 = 5 + 5 + 1
	 *
	 * f[n]代表n元的最少钱币
	 * f[n]=min{f[n-1]+1,f[n-2]+1,f[n-5]+1}最后一步取最小值
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChange(int[] coins, int amount) {
		// write your code here
		//0元到 amount的最优接
		int[] dp = new int[amount+1];
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (j == 0) {
					dp[i] = Integer.MAX_VALUE;
				}
				if (i>=coins[j]&&dp[i - coins[j]]<Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}else {
					dp[i] = Math.min(dp[i], Integer.MAX_VALUE);
				}
			}

		}
		if (dp[amount] < Integer.MAX_VALUE) {
			return -1;

		}
		return dp[amount];
	}

	/**
	 *描述
	 * 有一个机器人的位于一个 m × n 个网格左上角。
	 * 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
	 * 问有多少条不同的路径？
	 *
	 * //最后一步
	 *f[m][n]=f[m-1][n]+f[m][n-1]
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		// write your code here
		int[][] f = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					f[0][0] = 1;
					continue;
				}

				if (i > 1 && j == 0) {
					f[i][j] = f[i - 1][j] ;
					continue;
				}
				if (j > 1 && i == 0){
					f[i][j] = f[i][j - 1];
					continue;
				}
				if (i > 1 && j > 1) {
					f[i][j] = f[i - 1][j] + f[i][j - 1];
				}
			}
		}
		return f[m - 1][n - 1];

	}

	/**
	 * 描述
	 * 给出一个非负整数数组，你最初定位在数组的第一个位置。　　　
	 * 数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　　
	 * 判断你是否能到达数组的最后一个位置
	 *
	 * 输入 : [2,3,1,1,4]
	 * 输出 : true
	 *
	 * //最后一步
	 * 假设能跳到那么，先一定能跳到i，然后a【i】的距离也够
	 * @param A
	 * @return
	 */
	public boolean canJump(int[] A) {
		// write your code here
		boolean f[] = new boolean[A.length];
		for (int i = 0; i < A.length; i++) {
			if (i == 0) {
				f[i] = true;
				continue;
			}
			for (int j = 0; j < i; j++) {
				if (A[j] + j >= i && f[j]) {
					f[i] = true;
					break;
				}
			}
		}
		return f[A.length - 1];

	}

	/**
	 *
	 * 描述
	 * 找出一个序列中乘积最大的连续子序列（至少包含一个数）
	 *输入:[2,3,-2,4]
	 * 输出:6
	 *
	 * f[n]最优就是以f[n-1]结尾的序列最大的情况
	 *
	 * @param nums
	 * @return
	 */
	public int maxProduct(int[] nums) {
		// write your code here


		int[] f = new int[nums.length];
		int[] g = new int[nums.length];
		int result = nums[0];
		f[0] = nums[0];
		g[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			f[i] = Math.max(nums[i], Math.max(f[i - 1] * nums[i], g[i - 1] * nums[i]));
			g[i] = Math.min(nums[i], Math.min(f[i - 1] * nums[i], g[i - 1] * nums[i]));
			result = Math.max(result, f[i]);
		}
		return result;


	}

}
