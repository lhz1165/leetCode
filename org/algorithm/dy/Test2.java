package org.algorithm.dy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        Test2 t = new Test2();
        t.minWindow("ADOBECODEBANC", "ABC");
    }
    Map<Character, Integer> ori = new HashMap<>();
    //滑动窗口内部统计
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        //字符串t的数量统计
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }

            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    ansL = l;
                    ansR = r  + 1;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }
    //检查滑动窗口的字符串是否全在结果内部
    public boolean check() {
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Character key =  entry.getKey();
            Integer val =  entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        int[] s1Freq = new int[26];
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        for (char s1Char : s1Chars) {
            s1Freq[s1Char - 'a']++;
        }

        int left = 0;
        int right = 0;
        while (left <= len2 - len1) {
            while (right - left < len1 && s1Freq[s2Chars[right] - 'a'] > 0) {
                s1Freq[s2Chars[right] - 'a']--;
                right++;
            }
            //循环结束表示s1走完了，或者中间遇到不同的重来
            //s1走完了
            if (right - left == len1) {
                return true;
            }
            //中间遇到不同的 left向前移动一位
            s1Freq[s2Chars[left] - 'a']++;
            left++;
        }
        return false;
    }


}
