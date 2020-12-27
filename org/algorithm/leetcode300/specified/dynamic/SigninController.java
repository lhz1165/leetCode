package org.algorithm.leetcode300.specified.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lhzlhz
 * @create 2020/9/3
 */
public class SigninController {
	public static void main(String[] args) {
		SigninController s = new SigninController();
		System.out.println(s.houseRobber(new int[]{5, 2, 1, 3}));

	}
	public long houseRobber(int[] A) {
		// write your code here
		int n = A.length;
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return A[0];
		}

		int[] f = new int[n];
		f[0] = A[0];
		f[1] = A[1];
		int res = Math.max(f[0], f[1]);
		for (int i = 2; i < n; i++) {
			for (int j = 0; j < i-1; j++) {
				f[i] = Math.max(f[j] + A[i], f[i]);
			}
			res = Math.max(res, f[i]);

		}
		return res;
	}

}
