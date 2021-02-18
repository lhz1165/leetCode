package org.algorithm.leetcode.specified.unifind;

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
		ConnectingGraphII g = new ConnectingGraphII(6);
		g.connect(2, 1);
		g.connect(1, 3);
		g.connect(3, 2);
		//g.connect(6, 3);
		int i = g.query(1);
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

	public ConnectingGraphII(int n) {//初始化
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
	public int query(int a) {//询问
        return size[find(a)];
	}
}
