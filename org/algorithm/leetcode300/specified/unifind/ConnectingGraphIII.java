package org.algorithm.leetcode300.specified.unifind;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lhzlhz
 * @create 2020/11/5
 * 连接图II · Connecting GraphII
给一个图中的 n 个节点, 记为 1 到 n . 在开始的时候图中没有边.
你需要完成下面两个方法:

connect(a, b), 添加一条连接节点 a, b的边
query(), 返回图中联通区域个数
 */
public class ConnectingGraphIII {
	public static void main(String[] args) {
		ConnectingGraphIII g = new ConnectingGraphIII(6);
		g.connect(2, 1);
		g.connect(1, 3);
		g.connect(3, 2);
		//g.connect(6, 3);
		int i = g.query();
		System.out.println(i);
	}
	private int[] father=null;
    private int[] size = null;



	private int find(int x) {
		if (father[x] == x) {
			return x;
		}
		return father[x] = find(father[x]);
	}

	public ConnectingGraphIII(int n) {//初始化
		father = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
	}
	/*
	 * @param a: An integer
	 * @param b: An integer
	 * @return: nothing
	 */
	public void connect(int a, int b) {//合并
		int roota = find(a),rootb = find(b);
		if (roota != rootb) {
			father[roota] = rootb;
            size[rootb]+=1;
		}
	}
	/*
	 * @param a: An integer
	 * @param b: An integer
	 * @return: A boolean
	 */
	public int query() {//询问
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < father.length; i++) {
            if (i != 0) {
                set.add(father[i]);
            }
        }
        return set.size();
	}
}
