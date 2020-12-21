package org.algorithm.leetcode300.nomal.test.normal;

import org.algorithm.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/12/15
 **/
public class NormalTest {
    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 1.暴力，先把所有的组合枚举出来再判断是否是有效组合
     * *********
     */


    public static void main(String[] args) {
        NormalTest n = new NormalTest();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;
        n.flatten(n1);
        System.out.println(n.combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(n.countSubstrings("aaab"));
    }

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {

        System.out.println(Arrays.toString(current));
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    /**
     * 方法2 回溯
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helperBackTrace(sb, res, 0, 0, n);
        return res;

    }

    /**
     * @param res
     * @param openNum  （数量
     * @param closeNum ）数量
     * @param num      一半括号 例如: ((())) n = 3;
     */
    private void helperBackTrace(StringBuilder sb, List<String> res, int openNum, int closeNum, int num) {
        if (sb.length() == num * 2) {
            res.add(sb.toString());
            return;
        }
        if (openNum < num) {
            sb.append("(");
            helperBackTrace(sb, res, openNum + 1, closeNum, num);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (closeNum < openNum) {
            sb.append(")");
            helperBackTrace(sb, res, openNum, closeNum + 1, num);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    /**
     * 338. 比特位计数
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * <p>
     * 动态规划
     * 0    1
     * 10   11  b=2
     * 100  101 110  111  b=4
     * P(x+b) = P(x)+1  b = 1 << m; (2的m次方)
     */
    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        if (num == 1) {
            return new int[]{0, 1};
        }
        int[] f = new int[num + 1];
        f[0] = 0;
        f[1] = 1;
        int index = 2;
        int m = 1;
        int b = 1 << m;
        while (index <= num) {
            for (int i = 0; i < b && index <= num; i++) {
                f[index] = f[index - b] + 1;
                index++;
            }
            m++;
            b = 1 << m;
        }
        return f;
    }

    /**
     * 给定一个二叉树，原地将它展开为一个单链表。
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        dfsFlatten(root, treeNodes);
        TreeNode prev = root;
        prev.left = null;
        prev.right = null;
        for (int i = 1; i < treeNodes.size(); i++) {
            TreeNode cur = treeNodes.get(i);
            cur.left = null;
            cur.right = null;
            prev.right = cur;
            prev = cur;
        }
        System.out.println();

    }

    public void dfsFlatten(TreeNode root, List<TreeNode> treeNodes) {
        if (root == null) {
            return;
        }
        treeNodes.add(root);
        dfsFlatten(root.left, treeNodes);
        dfsFlatten(root.right, treeNodes);


    }

    /**
     * 39. 组合总和
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfsCombinationSum(candidates, result, new ArrayList<>(), 0, target, 0);
        return result;
    }

    private void dfsCombinationSum(int[] candidates, List<List<Integer>> results, ArrayList<Integer> result, int sum, int target, int index) {

        if (sum == target) {
            results.add(new ArrayList<>(result));
        }
        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            result.add(candidates[i]);
            dfsCombinationSum(candidates, results, result, sum + candidates[i], target, i);
            result.remove(result.size() - 1);
        }

    }

    /**
     * 647. 回文子串
     */
    public int countSubstrings(String s) {
        if(s == null || s.equals("")){
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int result = s.length();


        return result;

    }



}
