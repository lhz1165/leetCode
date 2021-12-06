package org.algorithm.dy;

import org.algorithm.leetcode.basic.TreeNode;

import java.util.*;

/**
 * @author lhzlhz
 * @create 7/11/2021
 */
public class Test01 {



	public static int solution(int n, int k) {
		if (n == 0) {
			return 1;
		}
		// dp[i][j]为从0点出发走i步到j点的方案数
		int[][] dp = new int[k + 1][n];
		dp[0][0] = 1;
		// 0步到达任何位置(除0)的方法数为0，java可省略
		for (int j = 1; j < n; j++) {
			dp[0][j] = 0;
		}

		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = dp[i - 1][(j - 1 + n) % n] + dp[i - 1][(j + 1) % n];
			}
		}
		return dp[k][0];
	}





	public List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return results;
		}
		List<Integer> result = new ArrayList<>();
		boolean[] valid = new boolean[nums.length];
		subPermuteHelper2(result, results, nums, valid);
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
			//表示每次接着的元素遇到相同的，只取第一个
			//【3(1)，3(2),0】-->[3(1),3(2),0]x [3(1),0,3(2)]x
			// [3(2),3(1),0]ok [3(2),0,3(1)]
			// [3(2),0,3(1)]ok[3(1),0,3(2)]x 不能去2即第二个
			// [0,3,3]
			if (i != 0 && nums[i - 1] == nums[i] && !valid[i - 1]) {
				continue;
			}
			result.add(nums[i]);
			valid[i] = true;
			subPermuteHelper2(result, results, nums, valid);
			valid[i] = false;
			result.remove(result.size() - 1);
		}
	}

	public void nextPermutation(int[] nums) {
		int firstIndex = -1;
		int secondIndex = -1;
		int prev = Integer.MIN_VALUE;
		int i;
		for (i = nums.length - 1; i >= 0; i--) {
			if (nums[i] < prev) {
				firstIndex = i;
				break;
			}
			prev = nums[i];
		}
		if (i == -1) {
			int l = 0;
			int r = nums.length - 1;
			while (l < r) {
				int temp = nums[l];
				nums[l] = nums[r];
				nums[r] = temp;
				l++;
				r--;
			}
		} else {
			for (int j = nums.length - 1; j >= i; j--) {
				if (nums[j] > nums[firstIndex]) {
					secondIndex = j;
					break;
				}
			}
			int temp = nums[firstIndex];
			nums[firstIndex] = nums[secondIndex];
			nums[secondIndex] = temp;
			int l = firstIndex + 1;
			int r = nums.length - 1;
			while (l < r) {
				int temp2 = nums[l];
				nums[l] = nums[r];
				nums[r] = temp2;
				l++;
				r--;
			}
		}


	}

	public boolean repeatedSubstringPattern(String s) {
		int i = (s + s).indexOf(s, 1);
		int length = s.length();
		return i != length;
	}


	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if(nums == null || nums.length < 3){
			return results;
		}
		Arrays.sort(nums);
		for(int i = 0; i < nums.length - 2; i++){
			if(i != 0 && nums[i] == nums[i-1]){
				continue;
			}
			List<List<Integer>> subResult = twoSum(nums, i + 1, -nums[i]);
			if(subResult.size() > 0){
				for(List<Integer> result: subResult){
					result.add(nums[i]);
				}
				results.addAll(subResult);
			}

		}
		return results;


	}

	public List<List<Integer>> twoSum(int[] nums, int startIndex, int target){
		int start = startIndex;
		int end = nums.length - 1;
		List<List<Integer>> results =  new ArrayList<>();
		while(start < end){
			if(nums[start] + nums[end] > target){
				end--;
			}else if(nums[start] + nums[end] < target){
				start++;
			}else{
				List<Integer> result = new ArrayList<>();
				result.add(nums[start]);
				result.add(nums[end]);
				results.add(result);
				start++;
				end--;
				while(start < end && nums[start]==nums[start - 1]){
					start++;
				}
				while(start < end && nums[end]==nums[end + 1]){
					end--;
				}
			}
		}
		return results;

	}


	public int[] smallestK(int[] arr, int k) {
		int[] res = new int[k];

		int KIndex = findKthLargestIndex(arr, 0, arr.length - 1, k);
		int index = 0;
		while (index <= KIndex) {
			res[index] = arr[index];
			index++;
		}


		return res;
	}


	private int findKthLargestIndex(int[] nums, int start, int end, int k) {
		if (start == end) {
			return start;
		}
		int i = start;
		int j = end;
		int pivot = nums[(i + j) / 2];
		while (i <= j) {
			while (i <= j && nums[i] < pivot) {
				i++;
			}
			while (i <= j && nums[j] > pivot) {
				j--;
			}
			if (i <= j) {
				int tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
				i++;
				j--;
			}
		}
		// 如果取第一个  那么就是satrt(0) +k(1) - 1 = 下标为0
		if (start + k - 1 <= j) {
			return findKthLargestIndex(nums, start, j, k);
		}
		//去掉了左边  那么就不看了 左边有 left -satr 个数
		//假设 start = 1  left = 2，就是start -left =1  ，1个不要了
		if (start + k - 1 >= i) {
			return findKthLargestIndex(nums, i, end, k - (i - start));
		}
		return j + 1;
	}

	public String removeKdigits(String num, int k) {
		if (num == null || num.length() == 0) {
			return num;
		}
		if (k >= num.length()) {
			return "0";
		}
		Stack<Integer> stack = new Stack<>();
		char[] chars = num.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			int cur = chars[i] - '0';
			while (!stack.isEmpty() && stack.peek() > cur && k > 0) {
				stack.pop();
				k--;
			}
			//0跳过
			if (stack.isEmpty() && cur == 0) {
				continue;
			}
			stack.push(cur);
		}
		StringBuilder res = new StringBuilder();
		while (!stack.isEmpty()) {
			res.insert(0, stack.pop().toString());
		}
		return res.toString();
	}

	public int[] maxSlidingWindow(int[] nums, int k) {
		int len = nums.length - k + 1;
		int[] result = new int[len];
		int index = 0;
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 0; i <= nums.length - 1; i++) {
			int cur = nums[i];
			while (!deque.isEmpty() && nums[deque.peekLast()] <= cur) {
				deque.pollLast();
			}

			deque.offerLast(i);

			if (i + 1 >= k) {
				result[index++] = nums[deque.peekFirst()];
			}

			if (i + 1 - k >= deque.peekFirst()) {
				deque.pollFirst();
			}
		}
		return result;

	}



	public String decodeString(String s) {
		Stack<Integer> times = new Stack<>();
		Stack<StringBuilder> strs = new Stack<>();
		char[] chars = s.toCharArray();
		int time = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '[') {
				times.push(time);
				//把之前[]里面的str 保存一下
				strs.push(sb);

				//用新的str接收新的[]里面的
				sb = new StringBuilder();
				time = 0;
			} else if (chars[i] == ']') {
				Integer nums = times.pop();
				StringBuilder subSb = strs.pop();

				StringBuilder temp = new StringBuilder();
				for (int k = 0; k < nums; k++) {
					temp.append(sb);
				}
				sb = new StringBuilder(subSb.append(temp));

			} else if (Character.isDigit(chars[i])) {
				time = time * 10 + (chars[i] - '0');
			} else {
				sb.append(chars[i]);
			}
		}
		return sb.toString();

	}



}
