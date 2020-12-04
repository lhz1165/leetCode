package org.algorithm.leetcode300.nomal.test;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/12/1
 **/
public class EveryDay02 {


    public static void main(String[] args) {
        EveryDay02 e = new EveryDay02();
        e.isPossibleII(new int[]{1, 2, 3,3,4,4,5,5});
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * <p>
     * <p>
     * 思路两次二分，求最左边和最右边的值
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        //向左压缩
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            res[0] = start;
        } else if (nums[end] == target) {
            res[0] = end;
        } else {
            res[0] = -1;
        }

        start = 0;
        end = nums.length - 1;
        //向右压缩
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) {
            res[1] = end;
        } else if (nums[start] == target) {
            res[1] = start;
        } else {
            res[1] = -1;
        }
        return res;
    }

    /**
     * 204. 计数质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        boolean[] results = new boolean[n];
        //假设所有数字都是素数
        Arrays.fill(results, true);
        for (int i = 2; i < n; i++) {
            //素数的倍数不是素数
            if (results[i]) {
                for (int j = 2 * i; j < n; j = i + j) {
                    results[j] = false;
                }
            }
        }
        int count = 0;
        for (boolean result : results) {
            if (result) {
                count++;
            }
        }
        return count - 2;
    }

    /**
     * 659. 分割数组为连续子序列
     * 哈希表的键为子序列的 key = [最后一个数字]，值为最小堆，用于存储所有的子序列 value = [长度]，
     * 最小堆满足堆顶的元素是最小的，因此堆顶的元素即为最小的子序列长度。
     * 12334455
     * {key = 5,value = {3,5}}
     */
    public boolean isPossibleII(int[] nums) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                LinkedList<Integer> list = new LinkedList<>();
                map.put(num, list);
            }
            //如果前面有序列接着长度+1
            if (map.containsKey(num - 1)){
                //把最短的取出来
                Integer minLen = map.get(num - 1).pollFirst();
                LinkedList<Integer> newList = map.get(num);
                newList.add(minLen + 1);

                //3->{1，3}  来了个 4
                if (map.get(num - 1).isEmpty()) {
                    map.remove(num - 1);
                }
            }else {
                //如果前面没有序列接着，新开一个序列，长度为1
                map.get(num).addFirst(1);
            }
        }

        for (Map.Entry<Integer, LinkedList<Integer>> entry : map.entrySet()) {
            for (Integer len : entry.getValue()) {
                if (len < 3) {
                    return false;
                }
            }
        }


        return true;
    }


}
