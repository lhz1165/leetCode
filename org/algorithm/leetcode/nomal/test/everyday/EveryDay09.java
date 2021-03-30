package org.algorithm.leetcode.nomal.test.everyday;

/**
 * @author: lhz
 * @date: 2021/3/29
 **/
public class EveryDay09 {
    public static void main(String[] args) {
        EveryDay09 e = new EveryDay09();

        System.out.println(e.reverseBits(1));
        //[[1,3,5,7],[10,11,16,20],[23,30,34,60]]
        //13
        System.out.println(e.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 5));
    }

    public int reverseBits(int n) {
        int res = 0;
        int flag = 1;
        for (int i = 0; i < 32; i++) {
            res += n & flag;
            if (i != 31) {
                res <<= 1;
                n >>= 1;
            }
        }
        return res;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int num = n * m - 1;
        int start = 0;
        int end = num;
        while (start <= end) {
            int mid = (start + end) / 2;
            int i = mid/m;
            int j = mid % m;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return false;

    }
}
