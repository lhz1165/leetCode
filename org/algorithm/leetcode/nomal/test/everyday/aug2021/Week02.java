package org.algorithm.leetcode.nomal.test.everyday.aug2021;

import java.util.*;

/**
 * @author lhzlhz
 * @create 2021/8/8
 */
public class Week02 {


	public List<Integer> eventualSafeNodes(int[][] graph) {
		//出度数量
		List<Integer>  indexToNum = getTO(graph);
		//入度有哪些点
		Map<Integer, List<Integer>> indexToIn = goIn(graph);
		//找到初度为0的点
		List<Integer> noway = noway(graph);
		if (noway.isEmpty()) {
			return new ArrayList<>();
		}
		List<Integer> list = bfs(noway, indexToNum, indexToIn);
		Collections.sort(list);
		return list;

	}

	public List<Integer> getTO(int[][] graph) {
		List<Integer> toNum = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			toNum.add(graph[i].length);
		}
		return toNum;
	}

	public Map<Integer, List<Integer>> goIn(int[][] graph) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < graph.length; i++) {
			map.put(i, new ArrayList<>());
		}

		for (int i = 0; i < graph.length; i++) {
			//入度的 key
			int[] inNodes = graph[i];
			for (int inNode : inNodes) {
				List<Integer> inList = map.get(inNode);
				inList.add(i);
			}
		}
		return map;
	}

	public List<Integer> noway(int[][] graph) {
		List<Integer> noway = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			if (graph[i].length == 0) {
				noway.add(i);
			}
		}
		return noway;
	}

	public List<Integer> bfs(List<Integer> noway, List<Integer> indexTo, Map<Integer, List<Integer>> indexToIn) {
		List<Integer> result = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>(noway);

		while (!queue.isEmpty()) {
			//取一个结果
			Integer curNode = queue.poll();
			//找出该点的所有的入度点
			List<Integer> InNodes = indexToIn.get(curNode);
			for (Integer inNode : InNodes) {
				indexTo.set(inNode, indexTo.get(inNode) - 1);
				if (indexTo.get(inNode) == 0) {
					queue.offer(inNode);
				}
			}
		}
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < indexTo.size(); i++) {
			if (indexTo.get(i) == 0) {
				ans.add(i);
			}
		}
		return ans;

	}
	public static void main(String[] args) {
		Week02 w = new Week02();
		System.out.println(w.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5, 7, 8,9}));

	}

	public int numberOfArithmeticSlices(int[] nums) {
		int length = nums.length;
		//如果构不成等差数列，返回0
		if (length < 3)
			return 0;
		int[] dp = new int[length];
		//等差数列的个数
		int count = 0;
		//等差数列的差值
		int diff = nums[1] - nums[0];
		for (int i = 2; i < length; i++) {
			if (nums[i] - nums[i - 1] == diff) {
				//如果当前数字和前面的可以构成等差数列，
				//就更新dp和count的值
				dp[i] = dp[i - 1] + 1;
				count += dp[i];
			} else {
				//如果不能和前面的构成等差数列，要重新计算diff
				diff = nums[i] - nums[i - 1];
			}
		}
		return count;
	}

}
