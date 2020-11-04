package org.algorithm.leetcode300.specified.unifind;

/**
 * @author lhzlhz
 * @create 2020/11/5
 * 连接图II · Connecting GraphII
给一个图中的 n 个节点, 记为 1 到 n .在开始的时候图中没有边.
你需要完成下面两个方法：

connect(a, b), 添加一条连接节点 a, b的边
query(a), 返回图中含 a 的联通区域内节点个数
 */
public class ConnectingGraphII {
	public static void main(String[] args) {
		ConnectingGraphII g = new ConnectingGraphII(5);
		g.connect(1, 2);
		g.connect(2, 4);
		int i = g.find(1);
		System.out.println(i);
	}

	private int[] father=null;
	private int find(int x) {//查询
		int x2 = x;
		if (father[x] == x) {
			return x;
		}

		while (father[x] != x) {
			x = father[x];
		}
		//路径压缩
		while (x2 != x) {
			int temp = father[x2];
			father[x2] = x;
			x2 = temp;
		}
		return x;
	}
	/*递归版本
	private int find(int x) {
		if (father[x] == x) {
			return x;
		}
		return father[x] = find(father[x]);
	}
	*/
	public ConnectingGraphII(int n) {//初始化
		father = new int[n + 1];
		for (int i = 0; i <= n; i++)
			father[i] = i;
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
		}
	}
	/*
	 * @param a: An integer
	 * @param b: An integer
	 * @return: A boolean
	 */
	public boolean query(int a, int b) {//询问
		return find(a) == find(b);
	}
}
