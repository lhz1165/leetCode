package org.example.leetcode300.specified.two_point;

/**
 * @author: lhz
 * @date: 2020/7/29
 **/
public class TwoPointTest02 {
    public static void main(String[] args) {
        TwoPointTest02 t = new TwoPointTest02();
        int[] nums ={7,7,9,8,6,6,8,7,9,8,6,6};
        t.partitionArray(nums, 10);
    }

    /**
     * 划分数组，以k为界限
     * @param nums
     * @param k
     * @return
     */
    public int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            while (start <= end&&nums[end] >= k) {
                end--;
            }
            while (start <= end&&nums[start] < k) {
                start++;
            }
            if (start <= end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                start++;
                end--;
            }
        }
        return start;
    }

}
