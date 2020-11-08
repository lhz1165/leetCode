package org.algorithm.leetcode300.specified.unifind;

import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/11/5
 */
public class UnionFindTest {
    public static void main(String[] args) {
        UnionFindTest u = new UnionFindTest();
//		boolean[][] ints = new boolean[][]{{
//				true, true, false, false, false},
//				{false, true, false, true, true},
//				{false, false, false, true, true},
//				{false, false, false, false, false},
//				{false, false, false, false, true}};
        boolean[][] ints = {{false, true, false}, {true, false, true}, {false, true, false}};
        u.numIslands(ints);
        int[][] ints1 = {{0, 1}, {0, 2}, {1, 2}};
        System.out.println(u.validTree(4, ints1));
        char[][] chars ={{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        u.surroundedRegions(chars);
    }

    /**
     * 岛屿个数
     *
     * @param grid
     * @return
     */
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    count++;
                }
            }
        }
        int[] directionX = {0, 0, 1, -1};
        int[] directionY = {1, -1, 0, 0};
        UnionFind unionFind = new UnionFind(m * n);
        unionFind.setCount(count);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    for (int k = 0; k < 4; k++) {
                        int x = directionX[k] + i;
                        int y = directionY[k] + j;
                        if (!validCoordinate(x, y, grid)) {
                            continue;
                        }
                        //把旁边的点连接起来
                        int currentVal = i * m + j;
                        int neighborVal = x * m + y;
                        unionFind.union(currentVal, neighborVal);
                    }
                }
            }
        }
        return unionFind.getCount();

    }

    /**
     * 给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A.
     * 初始二维矩阵全0.
     * 二元数组A内的k个元素代表k次操作, 设第i个元素为 (A[i].x, A[i].y), 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿.
     * 问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // write your code here
        return null;

    }

    /**
     * 图是否是树
     * n个点 n-1条边 是否都联通
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        ConnectingGraphIII uf = new ConnectingGraphIII(n);
        if (n - 1 != edges.length) {

            return false;
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            uf.connect(a, b);
        }
        return uf.query() == 1;
    }


    public boolean validCoordinate(int x, int y, boolean[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (x < 0 || x >= n) {
            return false;
        }
        if (y < 0 || y >= m) {
            return false;
        }
        if (!grid[x][y]) {
            return false;
        }
        return true;
    }

    /**
     * 被围绕区域
     * 给一个二维的矩阵，包含 'X' 和 'O',
     * 找到所有被 'X' 围绕的区域，并用 'X' 替换其中所有的 'O'。
     */
    public void surroundedRegions(char[][] board) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
         char X = 'X';
         char O = 'O';
        int[] deltaX = {-1, 1, 0, 0};
        int[] deltaY = {0, 0, -1, 1};
        int n = board.length;
        int m = board[0].length;
        UnionFind uf = new UnionFind(m * n+1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == O) {
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1){
                        uf.union(i * m + j, m * n);
                        continue;
                    }
                    for (int k = 0; k < 4; k++) {
                        int x = i + deltaX[k];
                        int y = j + deltaY[k];
                        if (!validBoard(x, y, board)) {
                            continue;
                        }
                        uf.union(i * m + j, x * m + y);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m - 1; j++){
                if (board[i][j] == 'O' && uf.find(i * m + j) != m*n){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private boolean validSide(int i, int j,char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (i == 0 || i == n - 1) {
            return true;
        }
        if (j == 0 || j == m - 1) {
            return true;
        }

        return false;
    }

    public boolean validBoard(int x, int y, char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (x < 0 || x >= n) {
            return false;
        }
        if (y < 0 || y >= m) {
            return false;
        }
        if (grid[x][y] == 'X') {
            return false;
        }
        return true;
    }

}

class UnionFind {
    int[] father;
    int count = 0;

    public UnionFind(int number) {
        this.father = new int[number];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
    }

    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }

    public void union(int x, int y) {
        int findX = find(x);
        int findY = find(y);
        if (findX != findY) {
            father[Math.min(findX,findY)] = Math.max(findX,findY);
            //每次合并岛屿数量 -1
            count--;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
