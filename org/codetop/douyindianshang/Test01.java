package org.codetop.douyindianshang;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lhzlhz
 * @create 7/11/2021
 */
public class Test01 {

	public static void main(String[] args) {
		Test01 t = new Test01();
		List<List<Integer>> subsets = t.subsets(new int[]{1, 2, 3});
		System.out.println(subsets);
		System.out.println(t.permute2(new int[]{1, 1, 3}));
	}

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
				dp[i][j] = dp[i - 1][(j - 1+n) % n] + dp[i - 1][(j + 1) % n];
			}
		}
		return dp[k][0];
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		dfs(nums, new ArrayList<>(), results, 0);
		return results;
	}

	public void dfs(int[] nums, List<Integer> result, List<List<Integer>> results,int startIndex) {
		results.add(new ArrayList<>(result));
		for (int i = startIndex; i < nums.length; i++) {
			result.add(nums[i]);
			dfs(nums, result, results, i + 1);
			result.remove(result.size() - 1);
		}
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		boolean[] visited = new boolean[nums.length];
		dfs2(nums, new ArrayList<>(), results,visited,0);
		return results;
	}

	private void dfs2(int[] nums, List<Integer> result, List<List<Integer>> results,boolean[] visited,int num) {
		if (num >= nums.length) {
			results.add(new ArrayList<>(result));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}
			if (i != 0 && visited[i - 1] &&nums[i] ==nums[i-1]) {
				continue;
			}
			result.add(nums[i]);
			visited[i] = true;
			dfs2(nums, result, results, visited, ++num);
			visited[i] = false;
			num--;
			result.remove(result.size() - 1);

		}

	}

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
			//表示每次接着的元素遇到相同的，只取第一个
			//【3(1)，3(2),0】-->[3(1),3(2),0]x [3(1),0,3(2)]x
			// [3(2),3(1),0]ok [3(2),0,3(1)]
			// [3(2),0,3(1)]ok[3(1),0,3(2)]x 不能去2即第二个
			// [0,3,3]
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
