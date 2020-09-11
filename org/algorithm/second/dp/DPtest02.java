package org.algorithm.second.dp;

/**
 * @author lhzlhz
 * @create 2020/9/6
 *
 * 坐标型动态规划
 *
 */
public class DPtest02 {
	public static void main(String[] args) {
		DPtest02 t = new DPtest02();
		t.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});

		t.minCost(new int[][]{{14, 2, 11}, {11, 14, 5}, {14, 3, 10}});
		t.numDecodings("12");

		t.longestIncreasingSubsequence(new int[]{1,3,6,7,9,4,10,5,6});
	}

	/**
	 * 描述
	 * "不同的路径" 的跟进问题：
	 * 现在考虑网格中有障碍物，那样将会有多少条不同的路径？
	 * 网格中的障碍和空位置分别用 1 和 0 来表示。
	 *
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		// write your code here
		int m = obstacleGrid.length;//列
		int n = obstacleGrid[0].length;//行
		int[][] f = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					f[i][j] = 0;
					continue;
				}
				if (i == 0 && j == 0) {
					f[0][0] = 1;
					continue;
				}

				if (i > 0 && j == 0) {
					f[i][j] = f[i - 1][j] ;
					continue;
				}
				if (j > 0 && i == 0){
					f[i][j] = f[i][j - 1];
					continue;
				}
				if (i > 0 && j > 0) {
					f[i][j] = f[i - 1][j] + f[i][j - 1];
				}
			}
		}
		return f[m - 1][n - 1];
	}

	public int minCost(int[][] costs) {
		// write your code here
		//房子个数
		int n = costs.length;
		//颜色个数
		int m = costs[0].length;
		int result = Integer.MAX_VALUE;
		int[][] f = new int[n+1][m];
		for (int i = 0; i < m; i++) {
			f[0][i] = 0;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				f[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k < m; k++) {
					if (j != k) {
						f[i][j] = Math.min(f[i][j], costs[i-1][j]+f[i-1][k]);
					}
				}
			}
		}
		for (int i = 0; i < m; i++) {
			result = Math.min(f[n][i], result);
		}
		return result;
	}

	/**
	 * 有一个消息包含A-Z通过以下规则编码
	 *
	 * 'A' -> 1
	 * 'B' -> 2
	 * ...
	 * 'Z' -> 26
	 * 现在给你一个加密过后的消息，问有几种解码的方式
	 */
	public int numDecodings(String s) {
		// write your code here
		if (s.equals("0")) {
			return 0;
		}

		if (s.length() == 1) {
			return 1;
		}
		int[] f = new int[s.length() + 1];
		f[0] = 1;
		for (int i = 1; i <= s.length(); i++) {
			if (Integer.parseInt(s.substring(i-1 , i ))>0) {
				f[i] = f[i - 1] ;
			}
			if(i>=2&&Integer.parseInt(s.substring(i-2 , i ))<=26){
				f[i] = f[i - 2];
			}
		}

		return f[s.length()];
	}

	/**
	 * 最长上升连续子序列
	 *
	 * @param nums
	 * @return
	 */
	public int longestIncreasingContinuousSubsequence(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int[] f = new int[nums.length];
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				f[i] = 1;
				result = f[i];
				continue;
			}
			if (nums[i] > nums[i - 1]) {
				f[i] = f[i - 1] + 1;
				result = Math.max(result, f[i]);
			}else {
				f[i] = 1;
			}

		}

		return result;


	}
	/**
	 * 最长上升子序列(不连续)
	 * 凡是涉及子序列不连续的，
	 * 那么以结尾为满足条件的最后一步
	 *
	 * @param nums
	 * @return
	 */
	public int longestIncreasingSubsequence(int[] nums) {
		int []dp = new int[nums.length];
		int ans = 0;
		for (int i = 0; i < nums.length; i++) {
			// 初始值为1
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					// 若nums[j] < nums[i]那么可以接在该序列后，更新状态
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			// 记录所有状态中的最大值
			if (dp[i] > ans) {
				ans = dp[i];
			}
		}
		return dp[nums.length - 1];
	}
}

