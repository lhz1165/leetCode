package org.algorithm.leetcode.nomal.test.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2021/2/18
 **/
public class NormalTest03 {
    public static void main(String[] args) {
        NormalTest03 n = new NormalTest03();
        n.topKFrequent(new int[]{3, 3, 2, 1}, 1);
        int kthLargest = n.findKthLargest(new int[]{-1, 2, 0}, 1, 0, 2);
        System.out.println();
    }


    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> numToCount = new HashMap<>();

        for (int num : nums) {
            numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        }

        List<Pair> pairs = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
            pairs.add(new Pair(entry.getKey(), entry.getValue()));
        }

        pairs.sort((a, b) -> b.count - a.count);

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = pairs.get(i).num;
        }
        return res;

    }

    /**
     * 215. 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int i = start;
        int j = end;
        int pivot = nums[(i + j) / 2];
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }

            if (i <= j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }
        if (start + k - 1 <= j) {
            return findKthLargest(nums, k, start, j);
        }
        if (start + k - 1 >= i) {
            return findKthLargest(nums, k - (i - start), i, end);
        }
        return nums[j + 1];

    }

    /**
     * 494. 目标和
     */
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        //前i个数 和为 j的个数
        int[][] f = new int[n + 1][2001];
        for (int i = 0; i <= S; i++) {
            f[0][i] = 0;
        }
        f[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 2001; j++) {
                if (j + nums[i - 1] < 2000 &&f[i-1][j+ nums[i - 1]] >0 ) {
                    f[i][j] = f[i - 1][j + nums[i - 1]] + 1;
                }
                if (j - nums[i - 1] > 0 && f[i-1][j - nums[i - 1]] >0) {
                    f[i][j] = f[i - 1][j - nums[i - 1]] + 1;
                }
            }
        }

        return f[n][S + 1001];

    }

    static class Pair {
        int num;
        int count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

}

