package org.example.leetcode300.specified;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/27
 **/
public class Test {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(subsets(nums));
    }


    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            results.add(subset);
            return results;
        }
        subsetHelper(results,subset,0,nums);
        return results;

    }

    public static void subsetHelper(List<List<Integer>> results, List<Integer> subset, int startIndex,int[] nums) {
        results.add(new ArrayList<>(subset));
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            subsetHelper(results, subset, i + 1, nums);
            subset.remove(subset.size() - 1);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            results.add(subset);
            return results;
        }
        //验证这个数字是否被访问过
        boolean[] valid = new boolean[nums.length];
        subsetHelper2(results,subset,nums,valid);
        return results;

    }
    public static void subsetHelper2(List<List<Integer>> results, List<Integer> subset, int[] nums,boolean[] valid) {
        if (subset.size() == nums.length) {
            results.add(new ArrayList<>(subset));
        }
        for (int i = 0; i < nums.length; i++) {
            if (valid[i]) {
                continue;
            }
            subset.add(nums[i]);
            valid[i] = true;
            subsetHelper2(results,subset,nums,valid);
            valid[i] = false;
            subset.remove(subset.size() - 1);
        }
    }




}
