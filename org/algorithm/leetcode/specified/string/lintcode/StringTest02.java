package org.algorithm.leetcode.specified.string.lintcode;

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
        int i=0, j=0;
        while (i < word.length() && j < abbr.length()) {
            char cs = source[i];
            char ct = target[j];
            if (!Character.isDigit(ct)) {
                if (cs != ct) {
                    return false;
                }
                i++;
                j++;
            }else {
                int start = j;
                while (j < abbr.length()&&Character.isDigit(target[j])) {
                    j++;
                }
                count = Integer.parseInt(abbr.substring(start, j));
                i += count;
            }
        }

        return (i == word.length()) && (j == abbr.length());
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
            char[] chars = str.toCharArray();
            for (char c : chars) {
                if (!Character.isLetter(c) && !Character.isDigit(c)) {
                    sb.append(":");
                }
                sb.append(c);
            }
            sb.append(":;");
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
        boolean visitedS = false;
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c == ':' && !visitedS) {
                visitedS = true;
                continue;
            }
            if (c == ';' && visitedS) {
                results.add(sb.toString());
                sb = new StringBuilder();
                visitedS = false;
                continue;
            }
            sb.append(c);
            if (visitedS) {
                visitedS = false;
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
         System.out.println(s.encode(new ArrayList<>(Arrays.asList("123:", "456","789"))));
         System.out.println(s.decode("123:::;456:;789:;"));
       // System.out.println(s.intToRoman(6));
        //System.out.println(s.validWordAbbreviation("a", "01"));
    }


}
