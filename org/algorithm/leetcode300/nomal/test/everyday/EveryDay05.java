package org.algorithm.leetcode300.nomal.test.everyday;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/12/30
 **/
public class EveryDay05 {
    public static void main(String[] args) {
        EveryDay05 e = new EveryDay05();
        System.out.println(e.lastStoneWeight(new int[]{2,2}));
    }

    /**
     * 1046. 最后一块石头的重量
     */
    public int lastStoneWeight(int[] stones) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> helper = new Stack<>();
        Arrays.sort(stones);
        for (int stone : stones) {
            stack.push(stone);
        }
        while (!stack.isEmpty() && stack.size() != 1 ) {
            Integer bigger = stack.pop();
            Integer little = stack.pop();
            Integer gap = bigger - little;
            if (gap == 0) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > gap) {
                helper.push(stack.pop());
            }
            stack.push(gap);
            while (!helper.isEmpty()) {
                stack.push(helper.pop());
            }
        }
        return stack.isEmpty() ? 0: stack.pop();
    }

}
