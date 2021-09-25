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
		SubArray ss = new SubArray();
//		System.out.println(ss.minimumSize2(new int[]{2,3,1,2,4,3}, 7));
		System.out.println(ss.kDistinctCharacters("abcabcabcabc", 3));
	}

	/**
	 * 406 · 和大于S的最小子数组
	 * 【前缀和数组  前i个数只和】
	 * 同向双指针
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
			}else if (prefixSum[right] - prefixSum[start] >= target){
				end = right;
			}else {
				continue;
			}
			result = Integer.min(result, end - start);
		}
		return result == Integer.MAX_VALUE ? -1 : result;

	}

	/**
	 * 同向双指针 o（n）【模板】
	 * @param nums
	 * @param target
	 * @return
	 */
	public int minimumSize3(int[] nums, int target) {
		return 0;
	}

	/**
	 * 同向双指针 o（n）
	 * 1375 · 至少K个不同字符的子串
	 * 给定一个仅包含小写字母的字符串 S.
	 * 返回 S 中至少包含 k 个不同字符的子串的数量.
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
}
