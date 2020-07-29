package org.example.leetcode300.specified.sort;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/7/29
 **/
public class SortTest01 {
    public static void main(String[] args) {
        int[] arr = {3, 4,5, 2, 1,8,10,0,2,3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     * 理想情况下
     * @param nums
     * @param start
     * @param end
     */
   public static void quickSort(int[] nums, int start, int end) {
       if (start >= end) {
           return;
       }
       int left = start;
       int right = end;
       //1 注意pivot选取最好中间
       int pivot = nums[(left + end) / 2];
       //2 left <= right 而不是 lef t< right
       while (left <= right) {
           while (left <= right && nums[left] < pivot) {
               left++;
           }
           while (left <= right && nums[right] > pivot) {
               right--;
           }
           if (left <= right) {
               int tmp = nums[left];
               nums[left] = nums[right];
               nums[right] = tmp;
               left++;
               right--;
           }
       }
       //left 交错了了
       quickSort(nums, start, right);
       quickSort(nums, left, end);
    }



}
