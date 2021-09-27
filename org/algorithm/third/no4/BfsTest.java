package org.algorithm.third.no4;

import java.util.*;

/**
 * @author lhzlhz
 * @create 27/9/2021
 */
public class BfsTest {
	public static void main(String[] args) {
		BfsTest b = new BfsTest();
		System.out.println(b.shortestPath2(new boolean[][]{
				{false, false, false, false}
				, {false, false, false, false}
				, {false, false, false, false}}));
	}


	/**
	 * 630 · 骑士的最短路径II
	 *
	 * @param grid
	 * @return (x + 1, y + 2)
	 * (x - 1, y + 2)
	 * (x + 2, y + 1)
	 * (x - 2, y + 1)
	 */
	public int shortestPath2(boolean[][] grid) {
		// write your code here
		int n = grid.length;
		int m = grid[0].length;
		if (n == 0 || m == 0) {
			return -1;
		}
		int[] XX = new int[]{1, -1, 2, -2};
		int[] YY = new int[]{2, 2, 1, 1};
		Map<Point, Integer> map = new HashMap<>();
		Point startPoint = new Point(0, 0);
		map.put(startPoint, 0);
		Queue<Point> queue = new LinkedList<>();
		HashSet<Point> visited = new HashSet<>();
		queue.offer(startPoint);
		visited.add(startPoint);
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				Point next = new Point(now.x + XX[i], now.y + YY[i]);
				if (vlaid(next,grid,visited)) {
					queue.offer(next);
					map.put(next, map.get(now) + 1);
					visited.add(next);
				}
			}
		}
		Integer res = map.get(new Point(n - 1, m - 1));
		return res == null ? -1 : res;

	}

	private boolean vlaid(Point next,boolean[][] grid,HashSet<Point> visited) {
		if (visited.contains(next)) {
			return false;
		}
		if (next.x < 0 || next.x > grid.length - 1) {
			return false;
		}
		if (next.y < 0 || next.y > grid[0].length - 1) {
			return false;
		}
		if (grid[next.x][next.y]) {
			return false;
		}
		return true;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point() {
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return x * 2 + y;
		}
	}

}
