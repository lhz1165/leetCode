package org.algorithm.leetcode300.specified.hash_heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhzlhz
 * @create 2020/8/2
 */
public class Test {
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		Map<Integer, List<Integer>> map = new HashMap<>();
		map.put(0, l1);
		List<Integer> l2 = new ArrayList<>();
		l1 = l2;
		System.out.println();
	}
}
