package org.example.leetcode300.dynamic;

import java.sql.SQLOutput;

/**
 * @author: lhz
 * @date: 2020/6/23
 **/
public class zuobiao {
    public static void main(String[] args) {
        System.out.println(fibonacci(6));
        char[][]chars={{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
        System.out.println(maxKilledEnemies(chars));
    }

    /**
     * @param A: An array of Integer
     * @return: an integer
     */
    public static int longestIncreasingContinuousSubsequence(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        // write your code here
        int up = dP(A);
        int i = 0;
        int j = A.length-1;
        while (i < j) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
            i++;
            j--;
        }
        int down = dP(A);
        return Math.max(up,down);
    }
    public static int dP(int[]A) {
        int[] f = new int[A.length];
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            f[i] = 1;
            if (i > 0&&A[i]>A[i-1]) {
                f[i] = f[i-1]+1;
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }

    public int minPathSum(int[][] grid) {
        // write your code here

        int m = grid.length;
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int f[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                if (i == 0 && j == 0) {
                    f[i][j] = grid[i][j];
                    continue;
                }

                int t = Integer.MAX_VALUE;
                if (i  > 0) {
                    t = Math.min(t, f[i - 1][j]);

                }
                if (j>0) {
                    t = Math.min(t, f[i ][j-1]);
                }
                f[i][j] = t + grid[i][j];
            }
        }
        return f[m-1][n-1];

    }

    /**
     * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return: an integer, the maximum enemies you can kill using one bomb
     */
    public static int maxKilledEnemies(char[][] grid) {

        // write your code here
        int m = grid.length;
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        //表示某个方向某个坐标能杀死的敌人
        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        //上
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {
                    up[i][j] = 0;
                    continue;
                }
                //如果有敌人直接算一个
                up[i][j] = grid[i][j] == 'E' ? 1 : 0;
                if (i > 0) {
                    up[i][j] += up[i - 1][j];
                }
            }
        }
        //左
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {
                    left[i][j] = 0;
                    continue;
                }
                if (j > 0) {
                    left[i][j] += left[i][j-1];
                    if (grid[i][j - 1] == 'E') {
                        left[i][j] += 1;
                    }
                }
            }
        }
        //下
        for (int i = m-1; i >=0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {
                    down[i][j] = 0;
                    continue;
                }
                if (i < m-1) {
                    down[i][j] += down[i + 1][j];
                    if (grid[i+1][j] == 'E') {
                        down[i][j] += 1;
                    }
                }
            }
        }
        //右
        for (int j =n-1 ; j >=0; j--) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 'W') {
                    right[i][j] = 0;
                    continue;
                }
                if (j<n-1 ) {
                    right[i][j] += right[i][j+1];
                    if (grid[i][j + 1] == 'E') {
                        right[i][j] += 1;
                    }
                }
            }

        }

        int t = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n ; j++) {
                t = Math.max(t, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
            }

        }
        return t;


    }


    public static int fibonacci(int i) {
        int[] f = new int[i];
        f[0] = 1;
        f[1] = 1;
        for (int j = 2; j <i ; j++) {
            f[j] = f[j - 2] + f[j - 1];
        }
        return f[i - 1];
    }


}
