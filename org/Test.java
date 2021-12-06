package org;

/**
 * @author lhzlhz
 * @create 23/11/2021
 */
public class Test {
	public static void main(String[] args) {
		Test t = new Test();
		System.out.println(t.getMax(new int[][]{{1}, {2, 3}, {4, 5, 6}, {7, 8, 9, 10}}));
	}

	public int getMax(int[][] grid) {
		int n = grid.length;
		int m = grid[grid.length-1].length;
		int f[][] = new int[n][m];
		//f[i][j] = Math.max{f[i-1][j-1],f[i-1][j]} + grid[i][j];
		f[0][0] = grid[0][0];
		int result = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i + 1; j++) {
				f[i][j] = f[i - 1][j] + grid[i][j];
				if (j - 1 >= 0) {
					f[i][j] = Math.max(f[i][j], f[i - 1][j - 1]+ grid[i][j]);
				}
				result = Math.max(result, f[i][j]);
			}
		}
		return result;
	}

}
