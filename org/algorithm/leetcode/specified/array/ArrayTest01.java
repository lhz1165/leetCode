package org.algorithm.leetcode.specified.array;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/7/10
 **/
public class ArrayTest01 {
    /**
     * 给定一个整数数组和一个整数k，你需要找到连续子数列的和为k子数列的和为k的总个数。
     * subarraySum2,1,-1,1,2 k=3
     *
     * @param nums
     * @param k
     * @return
     */
    public  int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] == k) {
                return 1;
            }else {
                return 0;
            }
        }
        // write your code here
        //存放sum 和 sum个数
        Map<Integer, Integer> map = new HashMap<>();
        //初始化
        map.put(0, 1);
        int res = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int key = sum - k;
            if (map.containsKey(key)) {
                res += map.get(key);
            }

            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }

        }
        return res;
    }
    /**
     * 给定一个整数数组和一个整数k，你需要找到和为k的最短子数组，并返回它的长度。
     * <p>
     * 如果没有这样的子数组，返回-1
     *
     * @param nums
     * @param k
     *
     * @return
     */
    public static int subarraySumEqualsKII(int[] nums, int k) {
        // write your code here
        //存放sum 和 索引
        Map<Integer, Integer> map = new HashMap<>();
        //初始化
        map.put(0, -1);
        int len = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int key = sum - k;
            if (map.containsKey(key)) {
                len = Math.min(len, i - map.get(key));
            }
            map.put(sum, i);
        }
        return len;
    }

    /**
     * 打印和为k的所有子数组
     *
     * @return
     */
    public  List<List<Integer>> subarraySumEqualsKIII(int[] nums, int k) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        //是否访问过
        subHelper(nums, k, result, results, 0,0);
        return results;
    }


    private  void subHelper(int[] nums, int k, List<Integer> result, List<List<Integer>> results, int sum,int index) {
        if (sum == k) {
            results.add(new ArrayList<>(result));
            return;
        }
        if (sum > k) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            //1 2 2 3 求k = 5
            //结果1 2 2 / 23 / 23（不要了）
            //index != i 表示 2 和2在同列不影响
            if (i != 0 && nums[i] == nums[i - 1] && index != i ) {
                continue;
            }
            sum += nums[i];
            result.add(nums[i]);
            subHelper(nums, k, result, results, sum,i+1);
            result.remove(result.size() - 1);
            sum -= nums[i];
        }
    }

    public static void main(String[] args) {
        ArrayTest01 a = new ArrayTest01();
        System.out.println(a.subarraySum(new int[]{2,1,-1,1,2}));

    }

    /**
     * 最大子数组
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        // write your code here
        //Dp 以nums[i]结尾的数组的最大和

        //要么nums[i]要和就它一个
        // 要么在nums[i]+之前的；
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < n; i++) {
            if (f[i - 1] > 0) {
                f[i] = f[i - 1] + nums[i];
            } else {
                f[i] = nums[i];
            }
            result = Math.max(result, f[i]);
        }
        return result;

    }

    /**
     *
     *
     * @param nums
     * @return
     */
    public List<Integer> subarraySum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                int result1 = map.get(sum) + 1;
                int result2 = i;
                result.add(result1);
                result.add(result2);
                break;
            } else {
                map.put(sum, i);
            }
        }
        return result;
    }

    public int[] subarraySumClosest2(int[] nums) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }

        int len = nums.length;
        if (len == 1) {
            res[0] = res[1] = 0;
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(-1, 0);//前0个数的和
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            map.put(i, sum);
        }
        //按照value排序
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Comparator.comparingInt(Map.Entry::getValue));
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < entries.size(); i++) {
            int gapSnap = entries.get(i)
                    .getValue() - entries.get(i - 1)
                    .getValue();
            if (gapSnap < ans) {
                ans = gapSnap;
                res[0] = entries.get(i)
                        .getKey();
                res[1] = entries.get(i - 1)
                        .getKey();
            }
        }
        Arrays.sort(res);
        //          array -3,1,1,-3,5
        //假设entry  sum:  -4,-3,-2,-1, 0, 1
        //          index: 3, 0, 1, 2,-1, 4
        // -3,1,1和最接近0 那就是[0,2]
        //但是res这个时候算的是sum(2)-sum(-1) = 1
        //所以res[0]需要加1才是子数组的开始下标
        res[0] = res[0] + 1;
        return res;
    }




}

