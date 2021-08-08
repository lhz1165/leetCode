package org.algorithm.leetcode.nomal.test.everyday.aug2021;

import java.util.*;

/**
 * @author lhzlhz
 * @create 2021/8/8
 */
public class Week02 {
	public static void main(String[] args) {
		Week02 w = new Week02();
		System.out.println(w.eventualSafeNodes(new int[][]{{1, 2}, {2,3}, {5}, {0}, {5}, {},{}}));
	}

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

}
