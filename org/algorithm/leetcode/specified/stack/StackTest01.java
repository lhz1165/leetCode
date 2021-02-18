package org.algorithm.leetcode.specified.stack;

import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/12/2
 * 单调栈的应用
 **/
public class StackTest01 {
    StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        StackTest01 s = new StackTest01();
        System.out.println(s.removeKdigits("1234",1));
        System.out.println(s.removeDuplicateLetters("cbacdcbc"));
    }

    /**
     * 402. 移掉K位数字
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
     *
     */
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return num;
        }
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        char[] chars = num.toCharArray();
        int n = k;
        //-------------------------------------------------------------
        //单调栈，如果当前数的比栈里的小  那么弹出栈里所有的比它大的 ， 知道找到比当前小的才放进去
        for (int i = 0; i < chars.length; i++) {
            int cur = chars[i] - '0';
            while (!stack.isEmpty() && stack.peek() > cur && k-- > 0) {
                stack.pop();
            }
            if (stack.isEmpty() && cur == 0) {
                continue;
            }
            stack.push(cur);

        }
        //----------------------------------------------------------------

        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        for (int i = 0; i < num.length() - n; i++) {
            if (stack2.isEmpty()) {
                break;
            }
            sb.append(stack2.pop());
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * 316. 去除重复字母
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     */
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int[] count = new int[26];

        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            count[c - 'a'] += 1;
        }

        boolean[] isExist = new boolean[26];
        for (int i = 0; i < chars.length; i++) {
            count[chars[i] - 'a']--;

            if (isExist[chars[i] - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > chars[i]) {
                //如果后面没这个字母了，不弹出，直接push
                if (count[stack.peek() - 'a'] == 0) {
                    break;
                }
                //如果后面还有这个字母了，弹出，并且设置为已出现
                isExist[stack.pop() - 'a'] = false;
            }
            stack.push(chars[i]);
            isExist[chars[i] - 'a'] = true;
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
