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
        System.out.println(e.wordPattern("abba","cat dog cat dog"));
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

    public boolean wordPattern(String pattern, String s) {
        int n = pattern.length();
        String[] ss = s.split(" ");
        int m = ss.length;
        if (n != m) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        int index = 0;
        while (index < n) {
            if (!map.containsKey(pattern.charAt(index))) {
                if (map.containsValue(ss[index])) {
                    return false;
                }
                map.put(pattern.charAt(index), ss[index]);
            }else {
                if (!map.get(pattern.charAt(index)).equals(ss[index])) {
                    return false;
                }
            }
            index++;
        }
        return true;

    }

}
