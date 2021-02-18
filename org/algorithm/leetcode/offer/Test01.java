package org.algorithm.leetcode.offer;

/**
 * @author: lhz
 * @date: 2021/2/2
 * 矩阵
 **/
public class Test01 {
    public static void main(String[] args) {
        Test01 t = new Test01();
        int[][] ints = {{1, 2, 3}, {4,5,6}, {7,8,9}};
        //t.spiralOrder(ints);
        t.rotate(ints);
    }

    /**
     * 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] order = new int[n * m];
        int index = 0;
        int left = 0, right = m - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    /**
     * 旋转矩阵
     * 1 水平反转
     * 2 主对角线反转
     */
    public void rotate(int[][] matrix) {
        //水平反转
        reverseByLevel(matrix);
        //对角线反转
        reverseByDiagonal(matrix);
    }

    public void reverseByLevel(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i <n/2 ; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }

    }
    public void reverseByDiagonal(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }
            }
        }
        System.out.println();

    }

}
