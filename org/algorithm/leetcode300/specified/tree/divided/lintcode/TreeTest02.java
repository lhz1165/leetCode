package org.algorithm.leetcode300.specified.tree.divided.lintcode;

import org.algorithm.leetcode300.basic.TreeNode;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/10/14
 **/
public class TreeTest02 {
    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(-5);
        TreeNode r3 = new TreeNode(2);
        TreeNode r4 = new TreeNode(0);
        TreeNode r5 = new TreeNode(3);
        TreeNode r6 = new TreeNode(-4);
        TreeNode r7 = new TreeNode(-5);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        //r3.left = r6;
        //r3.right = r7;
        TreeTest02 t = new TreeTest02();
        System.out.println(t.getHeight(r1,0));

    }

    public int getHeight(TreeNode node, int h) {
        if (node == null) {
            return h;
        }
        return Math.max(getHeight(node.left, h + 1), getHeight(node.left, h + 1));

    }

    /**
     * 分治法
     * 要求右边的任何数都要比root大，左边的任何数都要比root小
     * 左子树下界是负无穷，上界是根节点
     * 右子树上界是正无穷，下界是根节点
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {

        Long lower = Long.MIN_VALUE;
        Long upper = Long.MAX_VALUE;
        return isValidBSTHelper(root, lower, upper);
    }

    public boolean isValidBSTHelper(TreeNode cur, Long lower, Long upper) {
        if (cur == null) {
            return true;
        }
        if (cur.val <= lower || cur.val >= upper) {
            return false;
        }
        //左边的所有数都 必须比根节点的最小值小
        boolean isLeftValid=isValidBSTHelper(cur.left,lower, (long) cur.val);
        boolean isRightValid=isValidBSTHelper(cur.right,(long)cur.val,upper);
        return isLeftValid && isRightValid;

    }

    /**
     * 650. 二叉树叶子顺序遍历
     * 给定一个二叉树，像这样收集树节点：收集并移除所有叶子，重复，直到树为空。
     * 输入： {1,2,3,4,5}
     * 输出： [[4, 5, 3], [2], [1]].
     * 解释：
     * <p>
     *     1
     *    / \
     *   2   3
     *  / \
     * 4   5
     *
     *
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        // write your code here
        Map<Integer, List<Integer>> map = new HashMap<>();
        findLeavesDfs(root, map);
        List<List<Integer>> results = new ArrayList<>();
        map.forEach((k, v) -> {
            results.add(v);
        });
        return results;
    }

    /**
     * 节点下面的深度
     */
    public int findLeavesDfs(TreeNode cur, Map<Integer, List<Integer>> map) {
        if (cur == null) {
            return 0;
        }
        //当前几点的左右子树的最大深度来分组
        int maxDepth = Math.max(findLeavesDfs(cur.left, map), findLeavesDfs(cur.right, map)) + 1;
        List<Integer> integerList = map.get(maxDepth);
        if (integerList != null) {
            integerList.add(cur.val);
        } else {
            List<Integer> result = new ArrayList<>();
            result.add(cur.val);
            map.put(maxDepth, result);
        }
        return maxDepth;
    }


    /**
     * 651. 二叉树垂直遍历
     * 输入： {3,9,20,#,#,15,7}
     * 输出： [[9],[3,15],[20],[7]]
     * 解释：
     *        3
     *       /\
     *      /  \
     *     9  20
     *    /\
     *   /  \
     * 15   7
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        // write your code here
        if (null == root) {
            return results;
        }
        Map<Integer, List<Integer>> map = new TreeMap<>(Comparator.comparingInt(o -> o));
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q1.offer(root);
        q2.offer(0);
        while (!q1.isEmpty()) {
            TreeNode curNode = q1.poll();
            int curVal = q2.poll();
            List<Integer> list = map.get(curVal);
            if (list == null) {
                List<Integer> result = new ArrayList<>();
                result.add(curNode.val);
                map.put(curVal, result);
            } else {
                list.add(curNode.val);
            }

            if (curNode.left != null) {
                int val = curVal - 1;
                q1.offer(curNode.left);
                q2.offer(val);
            }
            if (curNode.right != null) {
                int val = curVal + 1;
                q1.offer(curNode.right);
                q2.offer(val);
            }
        }
        map.forEach((k,v)->{
            results.add(v);
        });

        return results;
    }



    public TreeNode findSubtree(TreeNode root) {
        // write your code here
        //findSubtreeHelper(root,map);
        int v = Integer.MIN_VALUE;
        List<TreeNode> nodes = preOrder(root);
        TreeNode result = null;
        for (TreeNode node : nodes) {
            if (getSubNumber(node) > v) {
                result = node;
                v = getSubNumber(node);
            }
        }
        return result;

    }

    private List<TreeNode> preOrder(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        List<TreeNode> left = preOrder(root.left);
        List<TreeNode> right = preOrder(root.right);
        nodes.add(root);
        nodes.addAll(left);
        nodes.addAll(right);
        return nodes;


    }


    private int getSubNumber(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getSubNumber(root.left) + getSubNumber(root.right) + root.val;
    }


}
