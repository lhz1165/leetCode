package org.algorithm.leetcode300.nomal.test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: lhz
 * @date: 2020/11/11
 **/
public class EasyTest01 {
    public static void main(String[] args) {
        EasyTest01 e = new EasyTest01();
        e.shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3);
    }
    /**
     * 1470. 重新排列数组
     * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     * 输入：nums = [2,5,1,3,4,7], n = 3
     * 输出：[2,3,5,4,1,7]
     * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
     */
    public int[] shuffle(int[] nums, int n) {
        if (nums.length == 0) {
            return nums;
        }
        int back = 0;
        int front = n;
        int[] result = new int[nums.length];
        int index = 0;
        for (int i = 0; i < n; i++) {
            result[index++] = nums[back++];
            result[index++] = nums[front++];
        }
        return result;
    }
    /**
     * 1030. 距离顺序排列矩阵单元格
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, Comparator.comparingInt(a -> (Math.abs(a[0] - r0) + Math.abs(a[1] - c0))));
        return ret;
    }
}
