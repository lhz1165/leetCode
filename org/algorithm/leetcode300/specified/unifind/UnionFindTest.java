package org.algorithm.leetcode300.specified.unifind;

/**
 * @author lhzlhz
 * @create 2020/11/5
 */
public class UnionFindTest {
	public static void main(String[] args) {
		UnionFindTest u = new UnionFindTest();
		boolean[][] ints = new boolean[][]{{
				true, true, false, false, false},
				{false, true, false, true, true},
				{false, false, false, true, true},
				{false, false, false, false, false},
				{false, false, false, false, true}};
		u.numIslands(ints);
	}

	/**
	 * 岛屿个数
	 *
	 * @param grid
	 * @return
	 */
	public int numIslands(boolean[][] grid) {
		// write your code here
		int count = 0;
		int n = grid.length;
		if (n == 0)
			return 0;
		int m = grid[0].length;
		if (m == 0)
			return 0;
		UnionFind union_find = new UnionFind(n * m);

		int total = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				if (grid[i][j])
					total++;

			}
		}


		union_find.set_count(total);
		for (int i = 0; i < grid.length; ++i)
			for (int j = 0; j < grid[0].length; ++j)
				if (grid[i][j]) {
					if (i > 0 && grid[i - 1][j]) {
						union_find.connect(i * m + j, (i - 1) * m + j);
					}
					if (i < n - 1 && grid[i + 1][j]) {
						union_find.connect(i * m + j, (i + 1) * m + j);
					}
					if (j > 0 && grid[i][j - 1]) {
						union_find.connect(i * m + j, i * m + j - 1);
					}
					if (j < m - 1 && grid[i][j + 1]) {
						union_find.connect(i * m + j, i * m + j + 1);
					}
				}
		return union_find.query();
	}
}

class UnionFind {

	private int[] father = null;
	private int count;

	private int find(int x) {
		if (father[x] == x) {
			return x;
		}
		return father[x] = find(father[x]);
	}

	public UnionFind(int n) {
		// initialize your data structure here.
		father = new int[n];
		for (int i = 0; i < n; ++i) {
			father[i] = i;
		}
	}

	public void connect(int a, int b) {
		// Write your code here
		int root_a = find(a);
		int root_b = find(b);
		if (root_a != root_b) {
			father[root_a] = root_b;
			count--;
		}
	}

	public int query() {
		// Write your code here
		return count;
	}

	public void set_count(int total) {
		count = total;
	}
}
