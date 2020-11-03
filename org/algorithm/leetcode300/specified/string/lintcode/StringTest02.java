package org.algorithm.leetcode300.specified.string.lintcode;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/10/16
 **/
public class StringTest02 {




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
                } else {
                    if (source[i] != target[j]) {
                        return false;
                    } else {
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

    /**
     * 640. 一次编辑距离
     */
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() == 0 && t.length() == 0) {
            return true;
        }

        if (s.length() < t.length()) {
            String tmp = "";
            tmp = s;
            s = t;
            t = tmp;
        }
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int sl = sc.length;
        int tl = tc.length;
        if (sl - tl > 1) {
            return false;
        }
        if (sl - tl == 1) {
            for (int i = 0; i < tl; i++) {
                if (sc[i] != tc[i]) {
                    return s.substring(i + 1, sl)
                            .equals(t.substring(i, tl));
                }
            }
            return true;
        }
        if (sl == tl) {
            for (int i = 0; i < sl; i++) {
                if (sc[i] != tc[i]) {
                    return s.substring(i + 1, sl)
                            .equals(t.substring(i + 1, tl));
                }
            }

        }
        return true;
    }

    /**
     * 字符串编解码
     *
     * @param strs
     * @return
     */
    public String encode(List<String> strs) {
        // write your code here
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length());
            sb.append("&");
            sb.append(str);
        }
        return sb.toString();
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        List<String> results = new ArrayList<>();
        char[] chars = str.toCharArray();
        int num = 0;
        boolean isAnd = false;
        boolean isStr = false;
        for (int i = 0; i < chars.length; ) {
            while (chars[i] >= '0' && chars[i] <= '9') {
                num = num * 10 + (chars[i] - '0');
                i++;
                isAnd = true;
            }
            if (isAnd) {
                i++;
                isStr = true;
            }

            if (isStr) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    sb.append(chars[i]);
                    i++;
                }
                results.add(sb.toString());
                isAnd = false;
                isStr = false;
                num = 0;

            }
        }
        return results;
    }

    /**
     * 罗马数字转化为整数
     *
     */
    public int romanToInt(String s) {
        // write your code here
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        if (s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int result = 0;
        int prev = 0;
        for (int i = 0; i < chars.length; i++) {
            Integer cur = romanMap.get(chars[i]);
            if (prev < cur) {
                result = result - prev;
                result += (cur - prev);

            } else {
                result += cur;
            }
            prev = romanMap.get(chars[i]);
        }
        return result;
    }

    /**
     * 整数转化为罗马数字
     *
     */
    public String intToRoman(int n) {
        // write your code here
        Map<Integer, String> romanMap = new HashMap<>();
        romanMap.put(1, "I");
        romanMap.put(5, "V");
        romanMap.put(10, "X");
        romanMap.put(50, "L");
        romanMap.put(100, "C");
        romanMap.put(500, "D");
        romanMap.put(1000, "M");
        romanMap.put(4, "IV");
        romanMap.put(9, "IX");
        StringBuilder sb = new StringBuilder();
        //个 - 》 十 - 》百 - 》千
        int qian = n / 1000;
        for (int i = 0; i < qian; i++) {
            sb.append("M");
        }
        n = n % 1000;

        //900?
        if (n / 100 == 9) {
            sb.append("CM");
            n = n - 900;
        } else if (n / 100 == 5) {
            //500?
            sb.append("D");
            n = n - 500;
        } else if (n / 100 == 4) {
            sb.append("CD");
            n = n - 400;
        }
        //100?
        int bai1 = n / 100;
        for (int i = 0; i < bai1; i++) {
            sb.append("C");
        }
        n = n % 100;


        //90-40
        if (n / 10 == 9) {
            sb.append("XC");
            n = n - 90;
        } else if (n / 10 >= 5) {
            //50?
            sb.append("L");
            n = n - 50;
        } else if (n / 10 == 4) {
            sb.append("XL");
            n = n - 40;
        }
        //10
        int shi1 = n / 10;
        for (int i = 0; i < shi1; i++) {
            sb.append("X");
        }
        n = n % 10;

        if (n == 9) {
            sb.append("IX");
        } else if (n == 4) {
            sb.append("IV");
        } else if (n == 5) {
            sb.append("V");
        } else {
            if (n >= 5) {
                sb.append("V");
                n = n - 5;
                for (int i = 0; i < n; i++) {
                    sb.append("I");
                }
            } else {
                for (int i = 0; i < n; i++) {
                    sb.append("I");
                }
            }
        }
        return sb.toString();
    }
    /**
     * 645. 识别名人
     */
    public int findCelebrity(int n) {
        // Write your code here
        Map<Integer, Integer> mapKnown = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            mapKnown.put(i, 0);
        }
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (knows(j, i)) {
                        Integer jKnown = mapKnown.get(i);
                        mapKnown.put(i, ++jKnown);
                    }
                }

            }
        }
        int result=-1;
        for (Map.Entry<Integer, Integer> entry : mapKnown.entrySet()) {
            if (entry.getValue() == n - 1) {
                result = entry.getKey();
            }
        }
        return result;

    }
    boolean knows(int a, int b){
        return true;
    }



    public static void main(String[] args) {
        StringTest02 s = new StringTest02();
        // System.out.println(s.encode(new ArrayList<>(Arrays.asList("123", "4567","890101010"))));
        System.out.println(s.intToRoman(6));
    }


}
