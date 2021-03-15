package org.algorithm.leetcode.nomal.test.everyday;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2021/3/15
 **/
public class EveryDay08 {
    public static void main(String[] args) {
        EveryDay08 e = new EveryDay08();
        int[][] m =new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(e.spiralOrder(m));
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;//行
        int n = matrix[0].length;//列
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        int target = m * n;
        int count = 0;
        List<Integer> result = new ArrayList<>();
        while (count <= target) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
                count++;
                if (count == target) {
                    return result;
                }
            }

            for (int i = top + 1; i <= bottom; i++) {
                result.add(matrix[i][right]);
                count++;
                if (count == target) {
                    return result;
                }
            }

            for (int i = right - 1; i >= left; i--) {
                result.add(matrix[bottom][i]);
                count++;
                if (count == target) {
                    return result;
                }
            }

            for (int i = bottom -1 ; i > top; i--) {
                result.add(matrix[i][left]);
                count++;
                if (count == target) {
                    return result;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }
}
