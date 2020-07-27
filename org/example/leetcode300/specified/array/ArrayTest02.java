package org.example.leetcode300.specified.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/14
 **/
public class ArrayTest02 {
    public static void main(String[] args) {
        int[] a = {1, 2, 2};
        //System.out.println(subsets(a));
        System.out.println(subsetsWithDup(a));
        System.out.println(subsets(a));
    }

    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here

        int[] result = new int[A.length + B.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                result[index++] = A[i++];
            } else {
                result[index++] = B[j++];

            }
        }
        while (j < B.length) {
            result[index++] = B[j++];
        }
        while (i < A.length) {
            result[index++] = A[i++];
        }

        return result;
    }

    /**
     * 合并两个排序的整数数组A和B变成一个新的数组。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n     方法：从后往前
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /**
     * 不可重复
     *
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     * 使用搜索的方案，深度优先(递归+去重)
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return result;
        }
        List<Integer> subset = new ArrayList<>();
        subsetHelper(nums,0,subset,result);
        return result;

    }

    /**
     * SubSetI
     * 可重复
     *
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public static List<List<Integer>> subsets(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();
        subsetHelper2(nums, 0, subset, result);
        return result;
    }

    // 以 subset 开头的，
    // 配上 nums 以 index 开始的数所有组合放到 results
    private static void subsetHelper(int[] nums,
                                     int startIndex,
                                     List<Integer> subset,
                                     List<List<Integer>> results) {
        //拆解
        //deep copy subset & add to results
        results.add(new ArrayList<>(subset));

        for (int i = startIndex; i < nums.length; i++) {
            //上一个数 startindex-1
            if (i != 0 && nums[i] == nums[i - 1] && startIndex != i) {
                continue;
            }
            subset.add(nums[i]);
            //下一个
            subsetHelper(nums, i + 1, subset, results);
            // 回溯
            subset.remove(subset.size() - 1);
        }
        //递归出口
        //return;
    }

    private static void subsetHelper2(int[] nums, int start, List<Integer> subset, List<List<Integer>> results) {
        results.add(new ArrayList<>(subset));
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            subsetHelper2(nums, i + 1, subset, results);
            subset.remove(subset.size() - 1);
        }
    }
}
