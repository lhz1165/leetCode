package org.codetop.hw;

import java.util.*;

/**
 * @author laihz
 * @date 2024/12/26 9:15
 */
public class Test01 {

    /**
     * 739. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * <p>
     * 思路：从后向前遍历，组成底部从大到小的单调栈，最近距离就是栈顶到当前元素，如果不满足单调栈，一个一个pop
     * <p>
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     *
     * @param temperatures
     * @return int[]
     * @author laihz
     * @date 2024/12/26 9:16
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                ans[i] = stack.peek() - i;
            }
            stack.push(i);

        }
        return ans;

    }


    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (char c : charArray) {
            if (!stack.isEmpty() && map.get(stack.peek()) != null && c == map.get(stack.peek())) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 最大连续1的个数 III
     *
     * @param nums
     * @param k
     * @return int
     * @author laihz
     * @date 2024/12/26 16:17
     */
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            rsum += 1 - nums[right];
            while (lsum < rsum - k) {
                lsum += 1 - nums[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        new Test01().longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
    }

    public int longestOnes2(int[] nums, int k) {
        //前i个总共几个0
        int[] P = new int[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            P[i] = P[i - 1];
            if (nums[i - 1] == 0) {
                P[i] += 1;
            }

        }
        int left = 0;
        int right = 0;
        int ans = 0;
        int temp = 0;
        for (right = 0; right < nums.length; ) {
            int zeroCount = P[right + 1] - P[left];
            if (zeroCount <= k) {
                temp++;
                right++;
                ans = Math.max(ans, temp);
            } else {
                temp--;
                left++;
            }
        }
        return ans;
    }


}
