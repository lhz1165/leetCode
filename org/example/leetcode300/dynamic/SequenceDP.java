package org.example.leetcode300.dynamic;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * @author: lhz
 *
 * @date: 2020/6/23
 * 序列性动态规划问题
 **/
public class SequenceDP {
    public static void main(String[] args) {
//        int c[][] = {{14, 2, 11,10}, {11, 14, 5,3}};
//        System.out.println(minCost(c));

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        int[] A = {3, 8, 4};
        System.out.println(houseRobber(A));

    }
    /**
     * 房子染色问题，相邻房子颜色不同
     * @param costs 假设三栋房子，不同房子，不同颜色的价格{ {14,2,11},{11,14,5},{14,3,10}  }
     * 时间复杂度O（nk²）
     * @return
     */
    public static int minCost(int[][] costs) {
        int houseNum = costs.length;
        int colorCost = costs[0].length;
        //序列型多开以为从0开始
        int f[][] = new int[houseNum + 1][colorCost];
        for (int i = 0; i < houseNum+1; i++) {
            // 当前这一栋枚举一种颜色
            //f【m】【n】代表第m个房子颜色涂成n色和之前m-1个房子的花费价格
            for (int j = 0; j < colorCost; j++) {
                if (i == 0) {
                    //f[0][0]， f[0][1] ， f[0][2]=0
                    f[0][j] = 0;
                }else {
                    // 当前这一栋的前一栋应该取染成什么色
                    f[i][j] = Integer.MAX_VALUE;
                    for (int k = 0; k < colorCost; k++) {
                        //不撞色
                        if (k != j) {
                            //i-1染成k色 花费
                            f[i][j] = Math.min(f[i][j], f[i - 1][k] + costs[i - 1][j]);
                        }
                    }

                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < colorCost; i++) {
            res = Math.min(res, f[houseNum][i]);
        }
        return res;
    }

    /**
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public static long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        //f[i]栋房子能偷的钱
        long[] f = new long[A.length + 1];
        f[0] = 0;
        f[1] = A[0];
        for (int i = 2; i < A.length+1; i++) {
            f[i] = Math.max(f[i - 1], f[i - 2] + A[i - 1]);
        }
        return f[A.length];
    }
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     * 枚举第几天卖记录最低值
     */
    public static int maxProfit(int[] prices) {
        // write your code here
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        int minV = prices[0];
        for (int i = 1; i <prices.length ; i++) {
            res = Math.max(res, prices[i] - minV);
            minV = Math.min(minV, prices[i]);
        }
        return res;
    }

    /**
     * @param prices: Given an integer array可以买卖多次
     *   [2, 1, 2, 0, 1]
     * @return: Maximum profit
     */
    public int maxProfitII(int[] prices) {
        // write your code here
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        int minV = prices[0];
        for (int i = 1; i <prices.length ; i++) {
            if (prices[i] > minV) {
                res += (prices[i] - minV);
            }
            minV = prices[i];
        }
        return res;

    }

    /**
     * @param prices: Given an integer array
     *   只可以买卖2次
     *   [2, 1, 2, 0, 1]
     * @return: Maximum profit
     */
    public int maxProfitIII(int[] prices) {
        // write your code here
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        int minV = prices[0];
        for (int i = 1; i <prices.length ; i++) {
            if (prices[i] > minV) {
                res += (prices[i] - minV);
            }
            minV = prices[i];
        }
        return res;

    }





}
