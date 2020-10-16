package org.algorithm.leetcode300.specified.string.lintcode;

import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2020/10/16
 **/
public class StringTest02 {


    public static void main(String[] args) {
        StringTest02 s = new StringTest02();

        System.out.println(s.validWordAbbreviation("sdkabk",
                "sd3k"));
    }

    public boolean isIsomorphic(String s, String t) {
        // write your code here
        return compare(s, t) && compare(t, s);

    }

    public boolean compare(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        if (s1.length != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s1.length; i++) {
            Character mapT = map.get(s1[i]);
            if (mapT == null) {
                map.put(s1[i], t1[i]);
            } else {
                if (!mapT.equals(t1[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 现在给你一个加密过后的消息，问有几种解码的方式
     * a->1
     * ...
     * z->26
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以被解码为 AB (1 2) 或 L (12).
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1) {
                int v = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                if (v >= 10 && v <= 26) {
                    f[i] += f[i - 2];
                }
            }

        }
        return f[n];
    }

    /**
     * 637. 检查缩写字
     * 输入 : s = "apple", abbr = "a2e"
     * 输出 : false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        char[] source = word.toCharArray();
        char[] target = abbr.toCharArray();
        int count = 0;
        int i, j;
        for (i = 0, j = 0; i < source.length && j < target.length; ) {
            if (target[j] < '0' || target[j] > '9' && count != 0) {
                i += count;
            }
            if (source[i] != target[j]) {
                if (target[j] > '0' && target[j] <= '9') {
                    int val = target[j] - '0';
                    count = count * 10 + val;
                    j++;
                }else {
                    if (source[i] != target[j]) {
                        return false;
                    }else {
                        i++;
                        j++;
                        count = 0;
                    }
                }
            } else {
                count = 0;
                i++;
                j++;
            }
        }
        if (j - target.length != i - source.length) {
            if (source.length - i != target[target.length - 1] - '0') {
                return false;
            }
        }
        return true;
    }

}
