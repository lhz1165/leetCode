package org.algorithm.leetcode.nomal.test.normal;

/**
 * @author: lhz
 * @date: 2021/3/17
 **/
public class NormalTest04 {
    public static void main(String[] args) {
        NormalTest04 n = new NormalTest04();
        n.findNumberIn2DArray(new int[][]{{18, 21, 23, 26, 30}}, 27);

       n.sortedSquares(new int[]{-7,-3,2,3,11});
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;

    }

    public boolean exist(char[][] board, String word) {
        int[] w1 = new int[]{1, -1, 0, 0};
        int[] w2 = new int[]{0, 0, -1, 1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, word.toCharArray(), 0, i, j)) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean dfs(char[][] board, char[] words, int k, int i, int j) {
        if (k >= words.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != words[k]) {
            return false;
        }
        return dfs(board,words,k+1,i,j-1)||dfs(board,words,k+1,i-1,j)||dfs(board,words,k+1,i,j+1)||dfs(board,words,k+1,i+1,j);
    }

    public int[] sortedSquares(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int[] res = new int[nums.length];
        int index = nums.length - 1;
        while (i <= j) {
            if (nums[i] * nums[i] < nums[j] * nums[j]) {
                res[index--] = nums[j] * nums[j];
                j--;
            }else {
                res[index--] = nums[i] * nums[i];
                i++;
            }
        }
        return res;
    }


}
