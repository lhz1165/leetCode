package org.algorithm.leetcode.specified.two_point;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhzlhz
 * @create 25/9/2021
 * 子数组 最多n平方
 * 子序列 最多2的n次方
 */
public class SubArray {
    public static void main(String[] args) {
//        SubArray ss = new SubArray();
//		System.out.println(ss.minimumSize2(new int[]{2,3,1,2,4,3}, 7));
//        //System.out.println(ss.kDistinctCharacters("abcabcabcabc", 3));
		int[] arr = new int[]{-1,-2,0,1, 2, 3, 4, 5, 6, 8, 9};
		System.out.println(Arrays.binarySearch(arr,7));
    }

    /**
     * 406 · 和大于S的最小子数组
     * 【前缀和数组  前i个数只和】
     * 同向双指针 一个数组中 i j都往前并且不回头
     * 两个技巧
     *
     * @param nums
     * @param target
     * @return o(n ^ 2)
     */
    public int minimumSize(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        //前缀和
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int result = Integer.MAX_VALUE;
        //双指针循环
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                if (prefixSum[end + 1] - prefixSum[start] >= target) {
                    result = Integer.min(result, end - start + 1);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * 继续优化,由于数组全是正数，所以只需要找到子数组里第一个结尾，然后后面不管一定加起来也大于s
     * 类似于xxxxoooo 二分
     *
     * @param nums
     * @param target
     * @return nlog(n)
     */
    public int minimumSize2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        //前缀和
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int result = Integer.MAX_VALUE;
        for (int start = 0; start <= n; start++) {
            //二分法找符合条件的end（左边界）
            int left = start;
            int right = n;
            int end;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (prefixSum[mid] - prefixSum[start] >= target) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (prefixSum[left] - prefixSum[start] >= target) {
                end = left;
            } else if (prefixSum[right] - prefixSum[start] >= target) {
                end = right;
            } else {
                continue;
            }
            result = Integer.min(result, end - start);
        }
        return result == Integer.MAX_VALUE ? -1 : result;

    }

    /**
     * 同向双指针 o（n）【模板】
     *
     * @param nums
     * @param s
     * @return
     */
    public int minimumSize3(int[] nums, int s) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        int result = Integer.MAX_VALUE;
        //前缀和
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int end = 0;
        for (int start = 0; start < n; start++) {
			while (prefixSum[end] - prefixSum[start] < s && end < n) {
				end++;
			}
			if (prefixSum[end] - prefixSum[start] >= s) {
				result = Integer.min(result, end - start);
			}
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * 同向双指针 o（n）
     * 1375 · 至少K个不同字符的子串
     * 给定一个仅包含小写字母的字符串 S.
     * 返回 S 中至少包含 k 个不同字符的子串的数量.
     *
     * @param s
     * @param k
     * @return
     */
    public long kDistinctCharacters(String s, int k) {
        // Write your code here
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        Map<Character, Integer> charToNum = new HashMap<>();
        int diff = 0;
        int end = 0;
        int result = 0;
        for (int start = 0; start < n; start++) {
            while (end < n && diff < k) {
                Integer curNum = charToNum.getOrDefault(s.charAt(end), 0);
                if (curNum == 0) {
                    diff++;
                }
                charToNum.put(s.charAt(end), ++curNum);
                end++;
            }
            if (diff == k) {
                result += n - end + 1;
            }
            charToNum.put(s.charAt(start), charToNum.get(s.charAt(start)) - 1);
            if (charToNum.get(s.charAt(start)) == 0) {
                diff--;
            }
        }
        return result;
    }

    /**
     * 1219 · 加热器
     * 两个数组双指针
     *
     * @param houses
     * @param heaters
     * @return
     */
	int findRadius(int[] houses, int[] heaters)
	{
		// Write your code here
		Arrays.sort(heaters);
		int ans = -1;
		for (int i = 0; i < houses.length; ++i)
		{
			ans = Math.max(ans, closestHeater(houses[i], heaters));
		}
		return ans;
	}
	//二分法找最近
	int closestHeater(int house,int[] heaters)
	{
		int start = 0;
		int end = heaters.length - 1;

		while (start + 1 < end)
		{
			int mid = start + (end - start) / 2;
			if (heaters[mid] == house)
				return 0;
			else if (heaters[mid] < house)
				start = mid;
			else
				end = mid;
		}

		return Math.min(Math.abs(house - heaters[start]), Math.abs(heaters[end] - house));
	}
}
