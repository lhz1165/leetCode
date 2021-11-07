package org.algorithm.third.no4;

import org.algorithm.leetcode.specified.bfs.BfsTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lhzlhz
 * @create 1/10/2021
 */
public class Bfs2 {
	public static void main(String[] args) {
		int[][] arrs = new int[][]{
				{0,  0, 0},
				{0,  0, 0},
				{ 0, 0, 1}

		};
		Bfs2 b = new Bfs2();
		System.out.println(b.zombie(arrs));
	}

	public int modernLudo(int length, int[][] connections) {
		// Write your code here
		return 0;
	}
	public int zombie(int[][] grid) {
		// write your code here
		int[] deltaX = new int[]{0, 0, 1, -1};
		int[] deltaY = new int[]{1, -1, 0, 0};

		if (grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int n = grid.length;
		int m = grid[0].length;
		int peopleNum = 0;
		Queue<Peo> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					queue.offer(new Peo(i, j));
				}
				if (grid[i][j] == 0) {
					peopleNum++;
				}
			}
		}
		if (peopleNum == 0) {
			return 0;
		}
		int day = 0;
		while (!queue.isEmpty()) {
			day++;
			int size = queue.size();
			for (int j = 0; j < size; j++) {
				Peo nowPeo = queue.poll();
				for (int i = 0; i < 4; i++) {
					int X = nowPeo.x + deltaX[i];
					int Y = nowPeo.y + deltaY[i];
					if (isPeopel(X, Y, grid)) {
						grid[X][Y] = 1;
						peopleNum--;
						queue.offer(new Peo(X, Y));
					}
				}
				if (peopleNum <= 0) {
					return day;
				}
			}

		}

		return -1;
	}
	static class Peo {
		int x;
		int y;

		public Peo(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	boolean isPeopel(int x, int y, int[][] grid) {

		if (x < 0 || x >= grid.length) {
			return false;
		}
		if (y < 0 || y >= grid[0].length) {
			return false;
		}
		if (grid[x][y] != 0) {
			return false;
		}
		return true;
	}


}
