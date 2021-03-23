package org.algorithm.leetcode.nomal.test.everyday;

import sun.font.Decoration;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println(Arrays.toString(e.generateMatrix(0)));
        e.numDistinct("rabbbit", "rabbit");
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
            }left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

    /**
     * 59. 螺旋矩阵 II
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int target = n * n;
        int count = 1;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        while (count <= target) {
            for (int i = left; i <= right; i++) {
                result[top][i] = count++;
                if (count > target) {
                    return result;
                }
            }

            for (int i = top + 1; i <= bottom; i++) {
                result[i][right] = count++;
                if (count > target) {
                    return result;
                }
            }

            for (int i = right - 1; i >= left; i--) {
                result[bottom][i] = count++;
                if (count > target) {
                    return result;
                }
            }
            for (int i = bottom-1; i > top; i--) {
                result[i][left] = count++;
                if (count > target) {
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

    public int numDistinct2(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        //初始化第一行
        for(int j = 0; j <= s.length(); j++){
            dp[0][j] = 1;
        }

        for(int i = 1; i <= t.length(); i++){
            for(int j = 1; j <= s.length(); j++){
                if(t.charAt(i-1) == s.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }


    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return 0;
        }
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 ) {
                    f[i][j] = 1;
                    continue;
                }
                if (j == 0) {
                    f[i][j] = 0;
                    continue;
                }
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    f[i][j] = f[i][j - 1] + f[i-1][j-1];
                 }else {
                    f[i][j] = f[i][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
