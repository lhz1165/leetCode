package org.algorithm.leetcode.specified.dynamic.leetcode.mid;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/9/24
 **/
public class DpMidTest {
    public static void main(String[] args) {
        DpMidTest d = new DpMidTest();
        //d.findCheapestPrice2(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1);
        d.cuttingRope(10);
    }

    /**
     * 300. 最长上升子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 初始值为1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // 若nums[j] < nums[i]那么可以接在该序列后，更新状态
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 记录所有状态中的最大值
            if (dp[i] > ans) {
                ans = dp[i];
            }
        }
        return ans;

    }


    /**
     * 978. 最长湍流子数组
     *
     * @param A
     * @return
     */
    public int maxTurbulenceSize(int[] A) {
        int n = A.length;
        int result = 1;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                if (A[i] == A[i - 1]) {
                    f[i] = 1;
                } else {
                    f[i] = 2;
                }
                result = Math.max(f[i], result);
                continue;
            }
            if (A[i - 1] > A[i - 2] && A[i] < A[i - 1]) {
                f[i] = f[i - 1] + 1;
            } else if (A[i - 1] < A[i - 2] && A[i] > A[i - 1]) {
                f[i] = f[i - 1] + 1;
            } else if (A[i] == A[i - 1]) {
                f[i] = 1;
            } else {
                f[i] = 2;
            }
            result = Math.max(f[i], result);

        }
        return result;
    }

    /**
     * 787. K 站中转内最便宜的航班
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[n][K + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[flight[1]][0] = flight[2];
            }
        }
        for (int i = 0; i <= K; i++) {
            dp[src][i] = 0;
        }
        for (int k = 1; k <= K; k++) {
            for (int[] flight : flights) {
                if (dp[flight[0]][k - 1] != Integer.MAX_VALUE) {
                    //dp[flight[0]][k - 1]表示上一步是经过中转，再加上价格
                    //dp[flight[1]][k] 表示上一步的目的地直接到达
                    dp[flight[1]][k] = Math.min(dp[flight[1]][k], dp[flight[0]][k - 1] + flight[2]);
                }
            }
        }
        return dp[dst][K] == Integer.MAX_VALUE ? -1 : dp[dst][K];
    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        //表示中转k步 到达n的最小花费
        int[][] f = new int[K + 1][n];

        for (int i = 0; i <= K; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        //src 可以直达的班次
        for (int[] flight : flights) {
            if (flight[0] == src) {
                f[0][flight[1]] = flight[2];
            }
        }
        //src--》src最小花费为0
        for (int i = 0; i <= K; i++) {
            f[i][src] = 0;
        }

        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < flights.length; i++) {
                if (f[k - 1][flights[i][0]] < Integer.MAX_VALUE) {
                    f[k][flights[i][1]] = Math.min(f[k][flights[i][1]], f[k - 1][flights[i][0]] + flights[i][2]);
                }
            }
        }
        return f[K][dst] == Integer.MAX_VALUE ? -1 : f[K][dst];
    }

    public int cuttingRope(int n) {
        if (n < 3) {
            return 1;
        }
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < n; i++) {
            f[i] = 0;
            for (int j = 0; j < i; j++) {
                f[i] = Math.max(f[i], f[j] * (i - j));
                f[i] = Math.max(f[i], (j + 1) * (i - j));
            }
        }
        return f[n - 1];
    }

}
