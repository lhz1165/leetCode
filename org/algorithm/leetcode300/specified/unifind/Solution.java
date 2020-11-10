package org.algorithm.leetcode300.specified.unifind;

/**
 * @author: lhz
 * @date: 2020/11/9
 **/
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] chars ={{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        s.numIslands(chars);
    }

    public int numIslands(char[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
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
                if (grid[i][j] == '1') {
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

    public boolean validCoordinate(int x, int y, char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (x < 0 || x >= n) {
            return false;
        }
        if (y < 0 || y >= m) {
            return false;
        }
        if (grid[x][y] == '0') {
            return false;
        }
        return true;
    }

    static class UnionFind{
        int[] father;
        int count = 0;
        public UnionFind(int number){
            father = new int[number];
            for(int i = 0; i < number; i++){
                father[i] = i;
            }
        }
        public int find(int x){
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }
        public void union(int a,int b){
            int fa = find(a);
            int fb = find(b);
            if(fa != fb){
                father[fa] = fb;
                count--;
            }
        }
        public int getCount(){
            return count;
        }
        public void setCount(int count){
            this.count = count;
        }

    }
}