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
}

