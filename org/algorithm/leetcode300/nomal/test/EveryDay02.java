package org.algorithm.leetcode300.nomal.test;

import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/12/1
 **/
public class EveryDay02 {


    /**
     *34. 在排序数组中查找元素的第一个和最后一个位置
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     *
     *
     * 思路两次二分，求最左边和最右边的值
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0) {
            return new int[]{-1,-1};
        }
        int start = 0;
        int end = nums.length - 1;
        //向左压缩
        while (start + 1 < end) {
            int mid = start + (end -start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            }else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            res[0] = start;
        }else if (nums[end] == target){
            res[0] = end;
        }else {
            res[0] = -1;
        }

        start = 0;
        end = nums.length - 1;
        //向右压缩
        while (start + 1 < end) {
            int mid = start + (end -start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            }else {
                start = mid;
            }
        }
        if (nums[end] == target) {
            res[1] = end;
        }else if(nums[start] == target){
            res[1] = start;
        }else {
            res[1] = -1;
        }
        return res;
    }
    /**
     *
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        Stack<Integer> stack = new Stack<>();
        return res;
    }

}
