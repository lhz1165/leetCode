package org.algorithm.leetcode.nomal.test.normal;

import java.util.*;

/**
 * @author: lhz
 * @date: 2021/2/18
 **/
public class NormalTest03 {
    public static void main(String[] args) {
        NormalTest03 n = new NormalTest03();
        n.topKFrequent(new int[]{3, 3, 2, 1}, 1);

        n.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println();
        n.decodeString("3[a2[c]]");
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
        for (int i = 0; i <= 2000; i++) {
            f[0][i] = 0;
        }
        f[0][1000] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 2000; j++) {
                if (j + nums[i - 1] <= 2000 && f[i - 1][j + nums[i - 1]] > 0) {
                    f[i][j] += f[i - 1][j + nums[i - 1]];
                }
                if (j - nums[i - 1] >= 0 && f[i - 1][j - nums[i - 1]] > 0) {
                    f[i][j] += f[i - 1][j - nums[i - 1]];
                }
            }
        }
        return f[n][S + 1000];
    }

    /**
     * 394. 字符串解码
     ******
     * @param s
     * @return
     */
    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        char l = '[';
        char r = ']';
        Integer times = 0;
        Stack<String> sStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder  res = new StringBuilder();
        for (char c : chars) {
            //【
            if (c == l) {
                sStack.push(res.toString());
                numStack.push(times);
                times = 0;
                res = new StringBuilder();
                // 】
            } else if (c == r) {
                StringBuilder  sub = new StringBuilder();
                Integer curTimes = numStack.pop();
                String subStr = sStack.pop();
                for (int i = 0; i < curTimes; i++) {
                    sub.append(res);
                }
                res = new StringBuilder(subStr+sub);
                //数字
            } else if (Character.isDigit(c)) {
                times = times * 10 + Integer.parseInt(c + "");
                //字母
            } else {
                res.append(c);
            }
        }

        return res.toString();
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

