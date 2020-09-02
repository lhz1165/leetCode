package org.algorithm.leetcode300.specified.dynamic.lintcode;

/**
 * @author: lhz
 * @date: 2020/6/22
 **/
public class RobotWalk {
    public static void main(String[] args) {
        System.out.println(howManyWalkWay(4, 3));
        int[] arr = {3,2,1,0,4};
        System.out.println(jump(arr));
    }
    /**
     * // 转移方程         f[i][j]=f[i][j-1]+f[i-1][j-1]
     * // 二维数组先走第0行  f[0][0] f[0][1] f[0][2]... f[0][n-1]
     * 坐标性的动态规划
     * n*m的格子 机器人从00走到mn一共有多少种走法
     * @param m
     * @param n
     * @return
     */
    public static int howManyWalkWay(int m,int n) {
        int f[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                if (i == 0 && j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = 0;
                //加右边前一格
                if (i > 0) {
                    f[i][j] += f[i - 1][j];
                }
                //加左边前一格
                if (j > 0) {
                    f[i][j] += f[i ][j-1];
                }
            }
        }
        return f[m - 1][n - 1];
    }



    /**
     * 存在性问题
     * @param A
     * @return
     */
    public static boolean jump(int[] A) {
        if (A.length == 1) {
            return true;
        }
        boolean[] f = new boolean[A.length];
        f[0] = true;
        for (int i = 1; i < f.length; i++) {
            for (int j = 0; j <i ; j++) {
                f[i]=f[j]&&A[j]>=(i-j);
                if (f[i]) {
                    break;
                }
            }
        }
        return f[A.length-1];

    }









}
