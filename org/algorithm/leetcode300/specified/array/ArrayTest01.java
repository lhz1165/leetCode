package org.algorithm.leetcode300.specified.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: lhz
 * @date: 2020/7/10
 **/
public class ArrayTest01 {
    public static void main(String[] args) {
        //subarraySum
        int[] a = {2, 1, -1, 1, 2};
        int[] a2 = {1,2,3};
        //System.out.println(subarraySum(a, 3));
        //System.out.println(subarraySumEqualsKII(a, 3));
        System.out.println(subarraySumEqualsKIII(a, 3));
    }

    public static ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer, Integer> hash = new HashMap<>();
        // 初始化
        hash.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 累加前缀和
            sum += nums[i];
            // 前缀和曾经出现，即这个区间的和为0
            if (hash.containsKey(sum)) {
                result.add(hash.get(sum) + 1);
                result.add(i);
                break;
            }
            //前缀和第一次出现，存入hash
            hash.put(sum, i);
        }
        return result;

    }


    /**
     * 给定一个整数数组和一个整数k，你需要找到连续子数列的和为k的总个数。
     * subarraySum2,1,-1,1,2 k=3
     * <p>
     * 我自己做的 内存超过限制
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        // write your code here
        //存放sum 和 索引
        Map<Integer, Integer> map = new HashMap<>();
        //初始化
        map.put(-1, 0);
        int result = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.put(i, sum);
            for (int j = i - 1; j >= -1; j--) {
                if (sum - map.get(j) == k) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        return count;
    }

    /**
     * 给定一个整数数组和一个整数k，你需要找到和为k的最短子数组，并返回它的长度。
     * <p>
     * 如果没有这样的子数组，返回-1
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySumEqualsKII(int[] nums, int k) {
        // write your code here
        //存放sum 和 索引
        Map<Integer, Integer> map = new HashMap<>();
        //初始化
        map.put(0, -1);
        int result = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                result = Math.min(result, i - map.get(sum - k));
            }
            map.put(sum, i);
       }
        return result;
    }

    /**
     * 打印和为k的所有子数组
     *
     * @return
     */
    public static List<List<Integer>> subarraySumEqualsKIII(int[] nums, int k){
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] valid = new boolean[nums.length];
        subHelper(nums, k, result, results,0,valid);
        return results;
    }

    private static void subHelper(int[] nums, int k, List<Integer> result, List<List<Integer>> results,int sum,boolean[] valid) {
        if (sum == k) {
            results.add(new ArrayList<>(result));
            return;
        }
        if (sum > k) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (valid[i]) {
                continue;
            }
            if (i != 0 && nums[i] != nums[i - 1] && valid[i-1]) {
                continue;
            }

            sum += nums[i];
            result.add(nums[i]);
            valid[i] = true;
            subHelper(nums,k,result,results,sum,valid);
            valid[i] = false;
            result.remove(result.size() - 1);
            sum -= nums[i];
        }
    }
}

