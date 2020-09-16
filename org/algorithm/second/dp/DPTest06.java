package org.algorithm.second.dp;

/**
 * @author lhzlhz
 * @create 2020/9/16
 *
 *区间性动态规划
 */
public class DPTest06 {


	/**
	 *
	 *
	 *给一字符串 s, 找出在 s 中的最长回文子序列的长度. 你可以假设 s 的最大长度为 1000.
	 */
	public int longestPalindromeSubseq(String s) {
		int n = s.length();
		int[][] f = new int[n][n];
		for (int i = 0; i < n; i++) {
			f[i][i] = 1;
		}
		for (int i = 0; i < n - 1; i++) {

			f[i][i+1]=(s.charAt(i)==s.charAt(i+1)?2:1);
		}
		for (int len = 3; len <= n; len++) {
			for (int j = 0; j < n - len + 1; j++) {
				f[j][j + len-1] = Math.max(f[j][j + len-1], f[j + 1][j + len-1]);
				f[j][j + len-1] = Math.max(f[j][j + len-1], f[j][j + len - 2]);
				if (s.charAt(j) == s.charAt(j + len)) {
					f[j][j + len] = Math.max(f[j][j + len-1], f[j+1][j + len - 2]+2);
				}
			}
		}
		return f[0][n-1];
	}
}
