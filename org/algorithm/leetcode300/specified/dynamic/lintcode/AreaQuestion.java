package org.algorithm.leetcode300.specified.dynamic.lintcode;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/7/1
 * 区间型动态规划
 **/
public class AreaQuestion {
    public static void main(String[] args) {
//        int[] a = {1, 2, 3};
//        firstWillWin(a);
//        maxCoins(a);
        int a =10;
        float  f=20.0f;
        f=(float)a;
        a=(int)f;

    }

    /**
     * 最长回文子序列
     *
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public static int longestPalindromeSubSeq(String s) {
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
        //用来记录结果 0去头 1去尾 2去头去尾
        int[][] pi = new int[n][n];
        //1
        for (int i = 0; i < n; i++) {
            f[i][i] = 1;
        }
        //2
        for (int i = 0; i < n - 1; i++) {
            f[i][i + 1] = (ss[i] == ss[i + 1]) ? 2 : 1;
        }
        //枚举长度2后面的
        for (int len = 3; len <= n; len++) {
            //当开始为len=3： （0 ，1 ，2）    ···· （n-3 ，n-2， n-1）
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
        System.out.println(Arrays.toString(res));


        return f[0][n - 1];
    }

    public static boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length == 0) {
            return false;
        }
        int n = values.length;
        if (n == 1) {
            return true;
        }
        int f[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            f[i][i] = values[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                f[i][j] = Math.max(values[i] - f[i + 1][j], values[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] >= 0;
    }


    /**
     * 吹气球
     *
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public static int maxCoins(int[] nums) {
        // write your code here
        // write your code here
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        //假设边界气球还有两个为1的气球，永远不能被扎破
        int[] A = new int[n + 2];
        //得到一个新的数组
        System.arraycopy(nums ,0 ,A ,1 ,n);
        A[0]=1;
        A[n+1]=1;

        n+=2;

        int[][]f=new int[n][n];

        for (int i = 0;i < n-1 ;i++ ){
            f[i][i+1]=0;
        }

        for(int len = 3;len <= n;len++){
            for (int i = 0 ;i <= n-len ;i++ ){
                int j =  i + len - 1;
                f[i][j]=0;
                for(int k = i+1; k <= j-1 ; k++){
                    f[i][j]=Math.max(f[i][k] + f[k][j]+A[i]*A[k]*A[j],f[i][j]);
                }
            }
        }
        return f[0][n-1];
    }


}
