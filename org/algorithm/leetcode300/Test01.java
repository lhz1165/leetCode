package org.algorithm.leetcode300;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/8/19
 **/
public class Test01 {
    public static void main(String[] args) {
        Test01 test01 = new Test01();
        test01.slowestKey(new int[]{12,23,36,46,62}, "spuda");
        test01.checkArithmeticSubarrays(new int[]{4, 6, 5, 9, 3, 7}, new int[]{0, 0, 2}, new int[]{2, 3, 5});
        test01.minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}});

    }

    /**
     * [12,23,36,46,62]
     * "spuda"
     * 1629. 按键持续时间最长的键
     * @param releaseTimes
     * @param keysPressed
     * @return
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        if (keysPressed.length() == 1) {
            return keysPressed.charAt(0);
        }
        char[] chars = keysPressed.toCharArray();
        int maxGap = releaseTimes[0];
        int prevTimes = releaseTimes[0];
        int index = 0;
        for (int i = 1; i < releaseTimes.length; i++) {
            int gap = releaseTimes[i] - prevTimes;
            if ((gap > maxGap)||(gap == maxGap&& chars[i]>chars[index])) {
                index = i;
                maxGap = gap;
            }
            prevTimes = releaseTimes[i];
        }
        return chars[index];
    }

    /**
     *
     * 1630. 等差子数组
     * 4,6,5,9,3,7]
     * [0,0,2]
     * [2,3,5]
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> results = new ArrayList<>();
        int n = l.length;
        for (int i = 0; i < n; i++) {
            int start = l[i];
            int end = r[i];
            int[] current = Arrays.copyOfRange(nums, start, end+1);
            Arrays.sort(current);
            Boolean result = isArithmetic(current);
            results.add(result);
        }
        return results;

    }

    public boolean isArithmetic(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        int gap = nums[1] - nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] != gap) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1631. 最小体力消耗路径
     *
     */
    public int minimumEffortPath(int[][] heights) {


    }
}
