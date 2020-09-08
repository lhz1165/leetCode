package org.algorithm.second.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/8/26
 * 数字组合关键两个痛点
 * 1.不能有重复的结果集，顺序不一样[2,3]--->[2,3],[3,2]
 * 2.不能有一样的结果集例如      [2,2,3]--->[2,3],[2,3]
 * 为了解决1，首先 第一排遍历第一个数字，到下一个第一排遍历第二个数字，第二排不能回头再遍历第一个数字，通过index来控制起始，以下这样
 * 1                 2          3
 * 1 2 3            2    3          3
 * 为了解决2，首先如果这个喝前一个数字相同，那么首先判断第一排第一个是否喝前一个第一排第一个相同candidates[i] == candidates[i - 1]
 * 3.如果[1,1,2]求4，要是按照解决2，那么不会有结果,[不允许第一排重复，可允许第二排重复],所以要允许第一个可以重复 index != i
 **/
public class DFSTest {
    public static void main(String[] args) {
        DFSTest d = new DFSTest();
        // System.out.println(d.combinationSum(new int[]{1, 1, 2}, 4));
        System.out.println(d.permute2(new int[]{3,3,0,3}));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        //dfs
        Arrays.sort(candidates);
        subHelper(target, results, new ArrayList<Integer>(), 0, candidates, 0);
        return results;
    }

    //结果可以使用相同的数字
    private void subHelper(int target, List<List<Integer>> results, List<Integer> result, int curSum, int[] candidates, int index) {
        if (curSum == target) {
            results.add(new ArrayList<>(result));
            return;
        } else if (curSum > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            //节省时间复杂度，只要这个大了，下面的一定也都大了不接着遍历
            if (curSum + candidates[i] > target) {
                break;
            }
            //******  预防[2,2,3]   结果出现[2,3][2,3]-->跳过相同的数
            //******    index!=i(也可以index < i) 也特别重要 代表了如果第一遍循环可以无视重复
            if (i != 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            result.add(candidates[i]);
            //********  index预防结果出现[2,2,3],[3,2,2]
            subHelper(target, results, result, curSum + candidates[i], candidates, i + 1);
            result.remove(result.size() - 1);
        }
    }

    /**
     * 排列方式（去重 和 不去重）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Integer> result = new ArrayList<>();
        boolean[] valid = new boolean[nums.length];
        subPermuteHelper(result,  results, nums,valid);
        return results;

    }

    /**
     * 不去重复[1,1]---》[1,1][1,1]
     * @param result
     * @param results
     * @param nums
     * @param valid
     */
    private void subPermuteHelper(List<Integer> result, List<List<Integer>> results, int[] nums,boolean[] valid) {
        if (result.size() == nums.length) {
            results.add(new ArrayList<>(result));
            return;

        }
        for (int i = 0; i < nums.length; i++) {
            if (valid[i]) {
                continue;
            }
            result.add(nums[i]);
            valid[i] = true;
            subPermuteHelper(result, results, nums,valid);
            valid[i] = false;
            result.remove(result.size() - 1);
        }
    }

    /**
     * 去重[1,2,2]->[1,2,2]|[2,1,2][2,2,1]
     * 解决办法，只要是同一层相同那么直接跳过
     * 比如第一层 1,2,2
     * 遍历过第一个2之后没必要遍历第二个2
     * 条件如果当前和前一个元素相同，并且前一个已经用过了，那么跳过
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Integer> result = new ArrayList<>();
        boolean[] valid = new boolean[nums.length];
        subPermuteHelper2(result,  results, nums,valid);
        return results;

    }

    private void subPermuteHelper2(List<Integer> result, List<List<Integer>> results, int[] nums, boolean[] valid) {
        if (result.size() == nums.length) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (valid[i]) {
                continue;
            }
            //*******  条件如果当前和前一个元素相同，并且前一个已经用过了，那么跳过
            if (i != 0 && nums[i - 1] == nums[i] && valid[i-1]) {
                continue;
            }
            result.add(nums[i]);
            valid[i] = true;
            subPermuteHelper2(result, results, nums,valid);
            valid[i] = false;
            result.remove(result.size() - 1);
        }
    }



}
