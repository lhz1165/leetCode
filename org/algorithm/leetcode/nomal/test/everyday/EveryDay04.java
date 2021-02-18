package org.algorithm.leetcode.nomal.test.everyday;

import org.algorithm.leetcode.basic.TreeNode;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/12/21星期一
 **/
public class EveryDay04 {
    public static void main(String[] args) {
        EveryDay04 e = new EveryDay04();
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

        System.out.println(e.zigzagLevelOrder(n1));
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 在某个台阶不花钱，cost[i]表示但是你想要走一步或者两步，要花的钱
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] f = new int[n + 1];
        //选择下标 0 或 1 作为初始阶梯
        f[0] = 0;
        f[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
        }
        return f[n];
    }
    /**
     *103. 二叉树的锯齿形层序遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        boolean isRight = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> result = new ArrayList<>();
            Deque<Integer> curList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curList.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            if (isRight) {
                while (!curList.isEmpty()) {
                    result.add(curList.pollLast());
                }
            }else {
                while (!curList.isEmpty()) {
                    result.add(curList.pollFirst());
                }
            }
            isRight = !isRight;
            res.add(result);
        }
        return res;
    }
    /**
     *387. 字符串中的第一个唯一字符
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        Queue<Pair> queue = new LinkedList<Pair>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (!position.containsKey(ch)) {
                position.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                position.put(ch, -1);
                while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().pos;
    }

    class Pair {
        char ch;
        int pos;

        Pair(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }



}
