package org.algorithm.leetcode.nomal.test.everyday;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/12/14
 **/
public class EveryDay03 {
    public static void main(String[] args) {
        EveryDay03 e = new EveryDay03();
        //"eat", "tea", "tan", "ate", "nat", "bat"
        e.monotoneIncreasingDigits(232);
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
            if (chars[i] >= stack.peek()) {
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
     //f[i][0]表示第i天，今天手上[没有]股票  这时的最大利润 = 【前一天卖掉，今天也不买】 max？ 【前一天持有一张今天卖了】
     //f[i][1]表示第i天，今天手上[有]一股票 这时的最大利润  = 【前一天卖掉的利润-今天的买的价格】max?【昨天都有股票今天也不用买就用昨天的】
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


    // 买卖股票的最佳时
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n][2];
        //f[i][0]表示第i天，今天手上[没有]股票最大利润 = 【前一天卖掉，今天也不买】？ 【前一天持有一张今天卖了】
        //f[i][1]表示第i天，今天手上[有]股票最大利润  = 【前一天卖掉的利润-今天的买的价格】?【昨天都有股票今天也不用买就用昨天的】
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
    /**
     * 316. 去除重复字母
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证
     * 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
    public String removeDuplicateLetters(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            count[chars[i] - 'a']--;

        }
        return sb.toString();


    }


}
