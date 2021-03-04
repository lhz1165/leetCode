package org.test;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
    }

	//给你一个数组，选三个数字，组成最小整数
	//输入5，10，20，30
	//输出10205
	public int test01(int[] num) {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				for (int k = 0; k < num.length; k++) {
					StringBuilder sb = new StringBuilder();
					if (i == j || i == k || j == k ||num[i] == 0) {
						continue;
					}
					Integer curNum = Integer.valueOf(sb.append(num[i])
							.append(num[j])
							.append(num[k]).toString());
					result = Math.min(result, curNum);
				}
			}
		}
		return result;
	}

	// 输入n 例如3  ；
	//n= 3有 123 132 213 231 312 321排列方式。返回第k个，k=2 返回132
	//n=4 有 1234 1243 1324 1342。。。返回第k个   k=3 返回1324
	public int test2(int n, int k) {
		List<List<Integer>> results = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(i + 1);
		}
		boolean[] valid = new boolean[n];
		subset(sb.toString(), results,0,new ArrayList<>(),valid);
		List<Integer> result = results.get(k - 1);
		StringBuilder r = new StringBuilder();
		for (Integer i : result) {
			r.append(i);
		}
		return Integer.parseInt(r.toString());

	}

	private void subset(String str, List<List<Integer>> results,int  index,List<Integer> result,boolean[] valid) {
		if (index == str.length()) {
			results.add(new ArrayList<>(result));
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			if (valid[i]) {
				continue;
			}
			result.add(str.charAt(i)-'0');
			valid[i] = true;
			subset(str, results, ++index, result,valid);
			index--;
			valid[i] = false;
			result.remove(result.size() - 1);
		}
	}



}
