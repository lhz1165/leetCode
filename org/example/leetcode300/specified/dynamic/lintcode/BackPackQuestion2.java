package org.example.leetcode300.specified.dynamic.lintcode;

/**
 * @author: lhz
 * @date: 2020/7/1
 **/
public class BackPackQuestion2 {
    public static void main(String[] args) {
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackII2(10, A, V));

    }

    /**
     *
     * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
     *
     * 问最多能装入背包的总价值是多大?
     *
     * 输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
     * 输出: 9
     * 解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
     *
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public static int backPackII(int m, int[] A, int[] V) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int f[][] = new int[n+1][m + 1];
        f[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            //-1表示不能拼出这个重量
            f[0][i] = -1;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 0; j--) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i - 1]&&f[i-1][j-A[i-1]]!=-1) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - A[i - 1]] + V[i-1]);
                    res = Math.max(f[i][j], res);
                }
            }
        }
        return res;
    }

    /**
     * 一维数组来解决
     * @param m
     * @param A
     * @param V
     * @return
     */
    public static int backPackII2(int m, int[] A, int[] V) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int f[] = new int[m + 1];
        f[0] = 0;
        for (int i = 1; i <= m; i++) {
            //-1表示不能拼出这个重量
            f[i] = -1;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 0; j--) {
                if (j >= A[i - 1]&&f[j - A[i - 1]] != -1 ) {
                    //已经算出来的重量价格，和新的重量价格对比
                    f[j]=Math.max(f[j],f[j-A[i - 1]]+V[i-1]);
                    res = Math.max(f[j], res);
                }

            }
        }
        return res;
    }

    /**
     * • 给定N种物品，重量分别为正整数A0,A1, …, AN-1，价值分别为正整数V0,
     * V1, …, VN-1
     * • 每种物品都有无穷多个
     * • 一个背包最大承重是正整数M
     * • 最多能带走多大价值的物品
     * @param m
     * @param A
     * @param V
     * @return
     */
    public static int backPackIII(int m, int[] A, int[] V) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int f[] = new int[m + 1];
        f[0] = 0;
        for (int i = 1; i <= m; i++) {
            //-1表示不能拼出这个重量
            f[i] = -1;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 0; j--) {
                if (j >= A[i - 1]&&f[j - A[i - 1]] != -1 ) {
                    //已经算出来的重量价格，和新的重量价格对比
                    f[j]=Math.max(f[j],f[j-A[i - 1]]+V[i-1]);
                    res = Math.max(f[j], res);
                }

            }
        }
        return res;
    }
}
