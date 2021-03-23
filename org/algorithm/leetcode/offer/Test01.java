package org.algorithm.leetcode.offer;

import org.algorithm.leetcode.basic.ListNode;
import sun.security.pkcs11.wrapper.CK_SLOT_INFO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2021/2/2
 * 矩阵
 **/
public class Test01 {
    public static void main(String[] args) {
        Test01 t = new Test01();
        int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //t.spiralOrder(ints);
        t.rotate(ints);
        //System.out.println(t.firstUniqChar("leetcode"));
        t.findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
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
        for (int i = 0; i < n / 2; i++) {
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


    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            }else {
                start = mid;
            }
        }
        int count = 0;
        if (nums[end] == target) {
            while (end>=0 && nums[end] == target) {
                count++;
                end--;
            }
        }else {
            while (start>=0 && nums[start] == target) {
                count++;
                start--;
            }
        }
        return count;
    }
    public int findRepeatNumber(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }



}
