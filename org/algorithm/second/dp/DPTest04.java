package org.algorithm.second.dp;

/**
 * @author lhzlhz
 * @create 2020/9/10
 * 划分型  博弈型  动态规划
 */
public class DPTest04 {
	public static void main(String[] args) {
		DPTest04 t = new DPTest04();
		System.out.println(t.numSquares(1000));
	}

	/**
	 * 给一个正整数 n, 请问最少多少个完全平方数(比如1, 4, 9...)的和等于n。
	 *
	 * 给了n个数组，分成几段，每段必须是完全平方数
	 * @param n
	 * @return
	 */
	public int numSquares(int n) {
		// write your code here
		if (n == 1) {
			return 1;
		}
		int[] f = new int[n + 1];
		f[1] = 1;
		for (int i = 2; i <= n ; i++) {
			f[i] = Integer.MAX_VALUE;
			for (int j = 1; j*j <= i; j++) {
				f[i] = Math.min(f[i], f[i - j * j] + 1);
			}
		}
		return f[n];

	}

	/**
	 * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.
	 *
	 * 最少需要分割几次?
	 * abaa....asdaba
	 * 0        j  j+1..i
	 * 0<->j判断最少回文次数    ,j+1<--->i已经是回文了
	 * 先找出最后一段是回文串 ，
	 * 他的前i个字符最少可以划分几个回文串 s[j,i]是回文串并且s[0,j]最少
	 * @param s
	 * @return
	 */
	public int minCut(String s) {
		// write your code here
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] ss = s.toCharArray();
		int n = ss.length;

		//1先找出所有的回文串
		//这个字符串奇数的回文串和偶数的回文串
		//i -(j+1)是否回文串===》isPalin[j][i-1]
		boolean[][] isPalin = calPalin(ss);
		int[] f = new int[n + 1];
		f[0] = 0;

		for (int i = 1; i < n+1; i++) {
			f[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				//i-1是因为从1开始的  n=i-1  n=j+1-1
				if (isPalin[j][i-1]) {
					f[i] = Integer.min(f[i], f[j] + 1);
				}
			}

		}
		return f[n] - 1;
	}

	public boolean[][] calPalin(char[] chars) {
		int n = chars.length;
		boolean[][] res = new boolean[n][n];
		//偶数
		for (int i = 0; i < n - 1; i++) {
			int l = i;
			int r = i + 1;
			while (l >= 0 && r <= n - 1 && chars[l] == chars[r]) {
				res[l][r] = true;
				l--;
				r++;
			}
		}
		//奇数
		for (int i = 0; i < n; i++) {
			if (i == 0 || i == n - 1) {
				res[i][i] = true;
			}
			int l = i;
			int r = i;
			while (l >= 0 && r <= n - 1 && chars[l] == chars[r]) {
				res[l][r] = true;
				l--;
				r++;
			}
		}
		return res;
	}












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
