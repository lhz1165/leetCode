package org.example.leetcode300.specified.dynamic;

/**
 * @author lhzlhz
 * @create 2020/9/2
 */

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找出最长对称字符串
 *
 * @author Your Name
 *
 */
public class Test1 {
	public static void main(String[] args) {
		// TODO 输出最长对称字符串：goog
		String input1 = "google";
		System.out.println(longestPalindrome(input1));

		// TODO 输出3个最长对称字符串：aba/aca/ada
		String input2 = "abcda";
		//我只能求出一个最长的 所有最长的不会【自己写的】
		System.out.println(longestPalindromeSubSeq(input2));
		// TODO 输出2个最长对称字符串：pop/upu，不会输出特殊字符的对称字符串p-p
		String input3 = "pop-upu";

		test3(input3);




	}

	/**
	 * 最长回文子串 （动态规划）
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		int n = s.length();
		String res = "";
		boolean[][] dp = new boolean[n][n];
		for(int i = 0 ;i < n; i++){
			for(int j = i; j >= 0 ;j --){
				if(s.charAt(i) == s.charAt(j) && ( i - j < 2 || dp[i-1][j+1]))
					dp[i][j] = true;
				if (dp[i][j] && (i - j + 1 > res.length())){
					res = s.substring(j,i+1);
				}
			}
		}
		return res;
	}

	/**
	 * i-j的最长回文子序列（动态规划）
	 * 第一轮[1,1],[2,2,]...[n,n]求子序列
	 * 第二轮[1,2][2,3]...[n-1,n]求子序列
	 * ....
	 * [1,n]求子序列
	 * @param s
	 * @return
	 */
	public static String longestPalindromeSubSeq(String s) {
		if (s == null || s.length() == 0) {
			return "0";
		}
		if (s.length() == 1) {
			return s;
		}
		char[] ss = s.toCharArray();
		int n = ss.length;
		int[][] f = new int[n][n];
		//用来记录结果pi[i][j]: i到j下标  0去头 1去尾 2去头去尾  3又可以去头又可以去尾巴
		int[][] pi = new int[n][n];
		//枚举长度为1的子字符串
		for (int i = 0; i < n; i++) {
			f[i][i] = 1;
		}
		//枚举长度为2子字符串
		for (int i = 0; i < n - 1; i++) {
			f[i][i + 1] = (ss[i] == ss[i + 1]) ? 2 : 1;
		}
		//枚举长度3及其以后的子字符串
		for (int len = 3; len <= n; len++) {
			//当开始为len=3： （0 ，1 ，2）    ···· （n-3 ，n-2， n-1）
			//内层循环i 就是起点的序号
			//n-len就是最后一段长度为n的
			for (int i = 0; i <= n - len; i++) {
				//终点
				int j = len + i - 1;
				f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);

				if (f[i][j] == (f[i + 1][j])) {
					pi[i][j] = 0;
				} else {
					pi[i][j] = 1;
				}

				if (ss[i] == ss[j]) {
					//去头去尾继续
					f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + 2);
					if (f[i][j] == f[i + 1][j - 1] + 2) {
						pi[i][j] = 2;
					}
				}

			}
		}
		//用来保存结果的
		//最长的序列有f[0][n - 1]位
		char[] res = new char[f[0][n - 1]];
		int p = 0, q = f[0][n - 1] - 1;
		int i = 0, j = n - 1;
		while (i <= j) {
			if (i == j) {
				res[p] = ss[i];
				break;
			}
			if (i + 1 == j) {
				res[p] = ss[i];
				res[q] = ss[j];
				break;

			}
			if (pi[i][j] == 0) {
				i++;
			} else if (pi[i][j] == 1) {
				j--;
			} else if (pi[i][j] == 2) {
				res[p++] = ss[i];
				res[q--] = ss[j];
				i++;
				j--;
			}
		}
		return String.valueOf(res);
	}

	public static List<String>  test3(String s) {
		List<String> results = new ArrayList<>();
		for (String s1 : s.split("-")) {
			String result = longestPalindromeSubSeq(s1);
			results.add(result);
		}

		System.out.println(results);
		return results;
	}




}
