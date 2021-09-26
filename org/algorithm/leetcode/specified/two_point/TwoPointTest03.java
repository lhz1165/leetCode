package org.algorithm.leetcode.specified.two_point;

/**
 * @author hz.lai
 * @date 2021-09-26
 */
public class TwoPointTest03 {
    public static void main(String[] args) {
        TwoPointTest03 t = new TwoPointTest03();
        System.out.println(t.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    /**
     * 7
     * [2,3,1,2,4,3]
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        int end = 0;
        int preSum = 0;
        for (int start = 0; start < n; start++) {
            while (end < n && preSum < target) {
                preSum += nums[end];
                end++;
            }
            if (end >= n) {
                end = n;
            }
            result = Integer.min(result, end - start);
            preSum -= nums[start];
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

}
