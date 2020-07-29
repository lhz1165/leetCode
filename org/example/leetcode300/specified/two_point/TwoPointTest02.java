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

    /**
     * 寻找倒数k个最大值 k从1开始
     * @param n
     * @param nums
     * @return
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return quickSelect(nums, 0, nums.length - 1, n);
    }

    /**
     * 从大到小快速排序
     */
    private int quickSelect(int[] nums, int start, int end, int k) {
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
                nums[i] = nums[ j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }
        if (start + k - 1 <= j) {
            return quickSelect(nums, start, j, k);
        }
        if (start + k - 1 >= i) {
            return quickSelect(nums, i, end, k - (i - start));
        }
        return nums[j + 1];

    }

}
