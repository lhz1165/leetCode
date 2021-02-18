package org.algorithm.leetcode.specified.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2020/10/23
 * 425. 电话号码的字母组合
 **/
public class DFSTest02 {




    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        // write your code here
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        char[] chars = digits.toCharArray();
        List<String> letters = new ArrayList<>();
        for (char aChar : chars) {
            letters.add(map.get(aChar - '0'));
        }

        dfsLetter(result, letters, new StringBuilder(), 0);
        return result;
    }

    private void dfsLetter(List<String> result, List<String> letters, StringBuilder sb, int listIndex) {
        if (listIndex >= letters.size()) {
            result.add(sb.toString());
            return;
        }
        char[] chars = letters.get(listIndex)
                .toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            dfsLetter(result, letters, sb, ++listIndex);
            sb.deleteCharAt(sb.length() - 1);
            --listIndex;
        }
    }

    /**
     * 652. 因式分解
     */
    public List<List<Integer>> getFactors(int n) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (n < 4) {
            return results;
        }
        //求子集
        dfsGetFactors(results, n, new ArrayList<Integer>() ,2);
        return results;
    }

    private void dfsGetFactors(List<List<Integer>> results, int n, ArrayList<Integer> result,int index) {
        if (!result.isEmpty()) {
            result.add(n);
            results.add(new ArrayList<>(result));
            result.remove(result.size() - 1);
        }
        for (int i = index; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                result.add(i);
                dfsGetFactors(results,n/i,result,i);
                result.remove(result.size() - 1);
            }

        }

    }
    public static void main(String[] args) {
        DFSTest02 d = new DFSTest02();
        System.out.println(d.addOperators("00",0));

    }

    /**
     *添加运算符
     */
    public List<String> addOperators(String num, int target) {
        // write your code here
        List<String> results = new ArrayList<>();
        char[] chars = num.toCharArray();
        dfsAddOperators(results, new StringBuilder(chars[0]+""), 1,target,chars,chars[0]-'0',0);
        return results;
    }

    private void dfsAddOperators(List<String> results, StringBuilder sb, int index,int target,char[] chars,long result,int prev) {
        if (index >= chars.length) {
            if (target == result) {
                results.add(sb.toString());
            }
            return;
        }
        long resultPrev = result;
        for (int i = 0; i < 3; i++) {
            //+
            if (i == 0) {
                sb.append("+");
                sb.append(chars[index]);
                result += (chars[index] - '0');
                prev = chars[index] - '0';
                //-
            } else if (i == 1) {
                sb.append("-");
                sb.append(chars[index]);
                result -= (chars[index] - '0');
                prev = chars[index] - '0';
                //*
            } else if (i == 2) {
                result = result - prev + prev * (chars[index] - '0');
                sb.append("*");
                sb.append(chars[index]);
                prev = chars[index] - '0';
            }
            dfsAddOperators(results, sb, ++index, target, chars,result,prev);
            result = resultPrev;
            --index;
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            prev = Integer.parseInt(sb.substring(sb.length()-1));
        }

    }




}
