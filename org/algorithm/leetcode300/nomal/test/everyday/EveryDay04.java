package org.algorithm.leetcode300.nomal.test.everyday;

/**
 * @author: lhz
 * @date: 2020/12/21星期一
 **/
public class EveryDay04 {
    /**
     * 746. 使用最小花费爬楼梯
     * 在某个台阶不花钱，cost[i]表示但是你想要走一步或者两步，要花的钱
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] f = new int[n + 1];
        //选择下标 0 或 1 作为初始阶梯
        f[0] = 0;
        f[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
        }
        return f[n];

    }

}
