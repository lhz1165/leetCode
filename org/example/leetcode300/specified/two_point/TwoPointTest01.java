package org.example.leetcode300.specified.two_point;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: lhz
 * @date: 2020/7/27
 **/
public class TwoPointTest01 {

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum2(arr, 9)));

    }

    /**
     * easy 双指针
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int head = 0;
        int tail = k - 1;
        int[] results = new int[nums.length - k + 1];
        int index = 0;
        while (tail < nums.length) {
            int sum = 0;
            for (int i = head; i <= tail; i++) {
                sum += nums[i];
            }
            results[index] = sum;
            index++;
            head++;
            tail++;
        }
        return results;
    }

    /**
     * easy 双指针
     * 给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        // write your code here
        int[] result = new int[nums.length];
        int head = 0;
        int tail = nums.length - 1;

        int index = 0;
        while (index < nums.length) {
            if (nums[index] != 0) {
                result[head] = nums[index];
                index++;
                head++;
                continue;
            }
            index++;
            tail--;
        }
        for (int i = 0; i < result.length; i++) {
            nums[i] = result[i];
        }
    }

    /**
     * 两数之和
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        // write your code here
        Pair[] pairs = getPairs(numbers);
        int[] result = {-1, -1};
        int head = 0;
        int tail = numbers.length - 1;

        while (head < tail) {
            if (pairs[head].number + pairs[tail].number < target) {
                head++;
            } else if (pairs[head].number + pairs[tail].number > target) {
                tail--;
            } else {
                result[0] = Math.min(pairs[head].index, pairs[tail].index);
                result[1] = Math.max(pairs[head].index, pairs[tail].index);
                return result;
            }
        }
        return result;


    }

    public static Pair[] getPairs(int[] nums) {
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        Arrays.sort(pairs, Comparator.comparingInt(o -> o.number));
        return pairs;
    }

    static class Pair {
        int number;
        int index;

        public Pair(int number, int index) {
            this.number = number;
            this.index = index;
        }
    }

    public static int[] twoSum2(int[] nums, int target) {
        // write your code here
        int[] result = {-1, -1};
        int head = 0;
        int tail = nums.length - 1;
        while (head < tail) {
            if (nums[head] + nums[tail] < target) {
                head++;
            } else if (nums[head] + nums[tail] > target) {
                tail--;
            } else {
                result[0] = head;
                result[1] = tail;
                return result;
            }

        }
        return result;

    }


}
