package org.algorithm.second.dp;

/**
 * @author lhzlhz
 * @create 2020/9/10
 * 买彩票！！！
 */
public class DPTest03 {

    /**
     * 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润
     *
     * 输入: [3, 2, 3, 1, 2]
     * 输出: 1
     * 说明：你可以在第三天买入，第四天卖出，利润是 2 - 1 = 1
     */
    public int maxProfit(int[] prices) {
        // write your code here
        //枚举j天卖，时刻保存最低价格
        int n = prices.length;
        int res = 0;
//        for (int i = 0; i < n-1; i++) {
//            for (int j = i+1; j < n; j++) {
//                int profit = prices[j] - prices[i];
//                res = Math.max(res, profit);
//            }
//        }
        int minV = prices[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, prices[i] - minV);
            minV = Math.min(minV, prices[i]);

        }
        return res;
    }

    /**
     *
     给定一个数组 prices 表示一支股票每天的价格.
     你可以完成任意次数的交易, 不过你不能同时参与多个交易 (也就是说, 如果你已经持有这支股票, 在再次购买之前, 你必须先卖掉它).
     设计一个算法求出最大的利润.
     */
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int minV = prices[0];
        int result = 0;
        for (int i = 1; i < n; i++) {
            if (minV < prices[i]) {
                result += prices[i] - minV;
            }
            minV = prices[i];
        }
        return result;
    }

    /**
     *
     * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。设计一个算法来找到最大的利润。你最多可以完成两笔交易。
     * 输入 : [4,4,6,1,1,4,2,5]
     * 输出 : 6
     *1 2 3 4 5个阶段
     * f[i][j] 第i天再阶段j的最大获利
     *
     * //前一天在阶段5    前一天阶段4，今天刚卖
     * 1 3 5 阶段一个方程 f[i][j] = Max(f[i-1][j],f[i-1][j-1]+P(i)-P(i-1))
     *
     * //前一天也在阶段 4 ，前一天在阶段阶段3今天刚买
     * 2 4 阶段一个方程   f[i][j] = Max(f[i-1][j]+P(i)-P(i-1)，f[i-1][j-1])
     */
    public int maxProfitIII(int[] prices) {
           // write your code here
        //枚举最后一天卖发生再第几天
        int n = prices.length;
        int[][] f = new int[n+1][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {

            }
        }

        return f[n][4];

    }
}
