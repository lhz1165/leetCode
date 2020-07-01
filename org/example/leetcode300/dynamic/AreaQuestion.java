package org.example.leetcode300.dynamic;

/**
 * @author: lhz
 * @date: 2020/7/1
 * 区间型动态规划
 **/
public class AreaQuestion {

    /**
     *
     * 最长回文子序列
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public int longestPalindromeSubseq(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] ss = s.toCharArray();
        int n = ss.length;
        int[][] f = new int[n][n];
        //1
        for (int i = 0; i < n; i++) {
            f[i][i] = 1;
        }
        //2
        for (int i = 0; i < n - 1; i++) {
            f[i][i + 1] = (ss[i] == ss[i + 1]) ? 2 : 1;
        }
        //枚举长度2后面的
        for (int i = 3; i <= n; i++) {
            for (int j = n; j < n-i; j++) {
                int k = i + j - 1;
                f[i][k]=Math.max()

            }

        }







        
    }
}
