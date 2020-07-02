package org.example.leetcode300.dynamic;

import sun.print.SunMinMaxPage;

/**
 * @author lhzlhz
 * @create 2020/6/29
 */
public class FenduanDp {
	/**
	 * 抄书
	 * @param pages
	 * @param k
	 * @return
	 */
	public int copyBooks(int[] pages, int k) {
		// write your code here
		if (pages == null || pages.length == 0) {
			return 0;
		}

		int n = pages.length;
		if (k >n ) {
			k=n;
		}
		int[][] f = new int[k+1][n+1];
		f[0][0] = 0;

		for (int i = 1; i <= n; i++) {
			f[0][i] = Integer.MAX_VALUE;
		}
		for (int kk = 1; kk <= k; kk++) {
			f[kk][0] = 0;
			for (int i = 1; i <= n; i++) {
				f[kk][i] = Integer.MAX_VALUE;
				int sum = 0;
				//这个循环sum代表第k个人写l--j本书的时间
				//第k个人不写书---->第k个人写所有书
				for (int j = i; j >=0 ; j--) {
					f[kk][i] = Math.min(f[kk][i], Math.max(f[kk - 1][j], sum));
					if (j > 0) {

						sum += pages[j-1];
					}
				}
			}
		}

		return f[k][n];
	}


}
