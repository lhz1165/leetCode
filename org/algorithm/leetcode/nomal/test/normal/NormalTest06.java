package org.algorithm.leetcode.nomal.test.normal;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: 2021/8/9
 * Description:
 *
 * @author hz.lai
 */
public class NormalTest06 {

    public static void main(String[] args) {
        NormalTest06 n = new NormalTest06();
        n.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
    }

    /**
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 128. 最长连续序列
     *
     *
     * @param nums
     * @return
     */

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int longestStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;

    }
}
