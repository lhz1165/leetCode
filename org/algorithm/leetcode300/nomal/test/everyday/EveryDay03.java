package org.algorithm.leetcode300.nomal.test.everyday;

import javax.swing.*;
import java.util.*;

/**
 * @author: lhz
 * @date: 2020/12/14
 **/
public class EveryDay03 {
    public static void main(String[] args) {
        EveryDay03 e = new EveryDay03();
        //"eat", "tea", "tan", "ate", "nat", "bat"
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            List<String> re = map.getOrDefault(new String(chars), new ArrayList<>());
            re.add(str);
            map.put(new String(chars), re);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
    /**
     * 738. 单调递增的数字
     * 输入: N = 332
     * 输出: 299
     */
    public int monotoneIncreasingDigits(int N) {
        char[] chars = String.valueOf(N)
                .toCharArray();
        if (chars.length == 1) {
            return N;
        }
        Stack<Character> stack = new Stack<>();
        Stack<Character> helper = new Stack<>();
        stack.push(chars[chars.length-1]);
        for (int i = chars.length-2 ; i >= 0 ; i--) {
            if (chars[i] > stack.peek()) {
                chars[i] = (char) (chars[i] - 1);
                while (!stack.isEmpty()) {
                    stack.pop();
                    helper.push('9');
                }
                while (!helper.isEmpty()) {
                    stack.push(helper.pop());
                }
            }
            stack.push(chars[i]);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     *714. 买卖股票的最佳时机含手续费
     * f[i][0] 表示第i天卖获得的最大收益
     * f[i][1] 表示第i天不买获得的最大收益
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] f = new int[n][2];
        f[0][0] = 0;
        f[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            //今天不持有=昨天持有，今天卖
            //今天不持有=昨天卖，今天也不买
            f[i][0] = Math.max(f[i-1][0],f[i-1][1]+prices[i]-fee);
            //今天持有=昨天不持有，今天买入
            //今天持有=昨天持有，今天不买
            f[i][1] = Math.max(f[i-1][0]-prices[i] ,f[i-1][1]);
        }
        return Math.max(f[n-1][0],f[n-1][1]);
    }
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n][2];
        f[0][0] = 0;
        f[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            //今天不持有=昨天持有，今天卖
            //今天不持有=昨天卖，今天也不买
            f[i][0] = Math.max(f[i-1][0],f[i-1][1]+prices[i]);
            //今天持有=昨天不持有，今天买入
            //今天持有=昨天持有，今天不买
            f[i][1] = Math.max(f[i-1][0]-prices[i] ,f[i-1][1]);
        }
        return Math.max(f[n-1][0],f[n-1][1]);
    }

    public int maxProfit(int[] prices) {
        int min = prices[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        if (res < 0) {
            return 0;
        }
        return res;
    }

}
