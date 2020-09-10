package org.algorithm.second.dp;

/**
 * @author lhzlhz
 * @create 2020/9/10
 * 划分型  博弈型  动态规划
 */
public class DPTest04 {
	/**
	 * 描述
	 * 有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。拿到最后一枚硬币的人获胜。
	 *
	 * 请判定 先手玩家 必胜还是必败?
	 *
	 * 若必胜, 返回 true, 否则返回 false.
	 * @param n
	 * @return
	 */
	public boolean firstWillWin(int n) {
		if (n==0)
			return false;
		if (n < 3) {
			return true;
		}
		// write your code here
		boolean f[] = new boolean[n+1];
		f[0] = false;
		f[1] = true;
		f[2] = true;
		for (int i = 3; i <= n; i++) {
			if (!f[i - 1] || !f[i - 2]) {
				f[i] = true;
			}
		}
		return f[n];
	}
}
