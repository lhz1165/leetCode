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


    public int longestOnes2(int[] nums, int k) {
        //前i个总共几个0
        int[] P = new int[nums.length + 1];
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


    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        char[] cs = s.toCharArray();

        Map<Character, Integer> c2Index = new HashMap<>();
        c2Index.put(cs[0], 0);
        int result = 1;
        int ans = 1;
        while (right < cs.length - 1) {
            right++;
            if (c2Index.get(cs[right]) != null) {
                while (c2Index.get(cs[right]) != null) {
                    c2Index.remove(cs[left]);
                    ans--;
                    left++;
                }
            }
            ans++;
            c2Index.put(cs[right], right);
            result = Math.max(result, ans);
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(new Test01().removeDuplicateLetters("bcabc"));
    }




    /**
     * 方法描述
     * cbacdcbc
     * @param s
     * @return java.lang.String
     * @author laihz
     * @date 2024/12/27 11:19
     */
    public String removeDuplicateLetters(String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> from = new Stack<>();

        //用于每个字符计算，后续如果还出现那么pop
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char c : charArray) {
            if (!charCountMap.containsKey(c)) {
                charCountMap.put(c, 1);
            } else {
                charCountMap.put(c, charCountMap.get(c) + 1);
            }

        }

        Set<Character> set = new HashSet<>();

        //cbacdcbc
        for (char c : charArray) {
            charCountMap.put(c,charCountMap.get(c) - 1);
            if (set.contains(c)) {
                continue;
            }

            while (!from.isEmpty() && from.peek() > c && charCountMap.get(from.peek()) > 0){
                set.remove(from.peek());
                from.pop();
            }
            from.push(c);
            set.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!from.isEmpty()) {
            sb = new StringBuilder(String.valueOf(from.pop())).append(sb);
        }
        return sb.toString();

    }


}
