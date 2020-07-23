package org.example.leetcode300.specified.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/23
 **/
public class DfsTest01 {
    public static void main(String[] args) {
        int[] candidates = {7,1,2,5,1,6,10};
        System.out.println(combinationSum(candidates, 8));

        System.out.println(partition("abac"));


    }

    /**
     * 给定一个候选数字的集合 candidates 和一个目标值 target. 找到 candidates 中所有的和为 target 的组合.在同一个组合中, candidates 中的某个数字限一次数地出现.
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> subset = new ArrayList<>();
        subSetHelper2(candidates,0,target,  subset, result);
        return result;
    }

    /**
     * 每个数字可以使用多次
     * @param candidates
     * @param index
     * @param remainTarget
     * @param subset
     * @param result
     */
    private static void subSetHelper(int[] candidates,int index,int remainTarget , List<Integer> subset, List<List<Integer>> result) {
        if (0 == remainTarget) {
            result.add(new ArrayList<>(subset));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (remainTarget < candidates[i]) {
                break;
            }
            if (i != 0 && candidates[i] == candidates[i - 1] && index < i) {
                continue;
            }
            subset.add(candidates[i]);
            subSetHelper(candidates,i, remainTarget-candidates[i],subset, result);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * 每个数字只能使用一次
     */
    private static void subSetHelper2(int[] candidates, int index, int remainTarget, List<Integer> subset, List<List<Integer>> result) {
        if (remainTarget == 0) {
            result.add(new ArrayList<>(subset));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (remainTarget < candidates[i]) {
                break;
            }
            if (i != 0 && candidates[i] == candidates[i - 1] && index != i) {
                continue;
            }

            subset.add(candidates[i]);
            subSetHelper2(candidates,i+1,remainTarget-candidates[i],subset,result);
            subset.remove(subset.size() - 1);
        }

    }

    /**
     * 136. 分割回文串
     * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.返回所有可能的分割方案.
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        // write your code here
        List<List<String>>result = new ArrayList<>();
        if (null == s ) {
            return result;
        }
        List<String> subSet = new ArrayList<>();
        if (s.length() < 2) {
            subSet.add(s);
            result.add(subSet);
            return result;
        }
        partitionHelper(s,0,subSet,result);
        return result;

    }

    public static void partitionHelper(String s ,int index,List<String> subSet,List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(subSet));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String sb = s.substring(index, i+1);
            if (isHuiWen(sb)) {
                subSet.add(sb);
                partitionHelper(s, i + 1, subSet, result);
                subSet.remove(subSet.size() - 1);
            }

        }

    }

    public static boolean isHuiWen(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 1) {
            return true;
        }
        int r = 0;
        int l = chars.length - 1;
        while (r < l) {
            if (chars[r] != chars[l]) {
                return false;
            }
            r++;
            l--;
        }
        return true;



    }

    /**
     * 15. 全排列
     * 给定一个数字列表，返回其所有可能的排列
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> subSet = new ArrayList<>();
        permuteHelper(nums,0,0,subSet,result);
        return result;
    }

    private static void permuteHelper(int[] nums, int startIndex,int head,List<Integer> subSet, List<List<Integer>> result) {
        result.add(subSet);
        for (int i = startIndex; i <nums.length; i++) {
            if (i == head) {
                continue;
            }
            subSet.add(nums[i]);
            permuteHelper(nums, i + 1,i, subSet, result);
            subSet.remove(subSet.size() - 1);

        }

    }

}
