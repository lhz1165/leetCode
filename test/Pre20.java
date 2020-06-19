package org.example.leetcode300.test;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/6/19
 **/
public class Pre20 {
    public static void main(String[] args) {
        int[] a = {-1, 2, 1, 4};
        System.out.println(threeSumClosest(a,1));

    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        int minL = 0;
        int minR = 0;
        int minI = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length-3; i++) {
            int goal = target - nums[i];
            int LO = i + 1;
            int HI = nums.length - 1;
            while (LO < HI) {
                int two = nums[HI] + nums[LO];
                if (Math.abs(two-goal)<min) {
                    min = Math.abs(two-goal);
                    minL = LO;
                    minR = HI;
                    minI = i;
                }
                if (goal > two) {
                    LO++;
                } else if (goal < two) {
                    HI--;
                }else {
                    return 0;
                }
            }


        }
        return nums[minI] + nums[minL] + nums[minR];

    }
}
