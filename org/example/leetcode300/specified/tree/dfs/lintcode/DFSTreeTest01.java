package org.example.leetcode300.specified.tree.dfs.lintcode;


import org.example.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/8
 **/
public class DFSTreeTest01 {
    /**
     * 二叉树的最大深度
     *
     * @param root
     * @return
     */
    int depth = 0;//全局遍历记录最大深度
    public int maxDepth1(TreeNode root) {
        helper(root, 1);
        return depth;
    }
    public void helper(TreeNode node, int currentDepth) {
        if (node == null) {
            return;
        }
        depth = Math.max(depth, currentDepth);
        helper(node.left, currentDepth + 1);
        helper(node.right, currentDepth + 1);

    }

    /**
     *
     * 分治法解决二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //分
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        //治
        return Math.max(left, right) + 1;
    }

    /**
     * 257. 二叉树的所有路径
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        if(root.left == null && root.right == null){
            result.add(""+root.val);
        }
        //递归拆解
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for(String leftPath : leftPaths){
            result.add(root.val+"->"+leftPath);
        }
        for(String rightPath : rightPaths){
            result.add(root.val+"->"+rightPath);
        }

        return result;

    }

    /**
     *
     * 是否平衡二叉树 左右子树高度差不超过1
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public int NOT_BALANCE = -1;
    public boolean isBalanced(TreeNode root) {
        // write your code here

        return helper(root) != NOT_BALANCE;

    }
    public int helper(TreeNode node){
        if(node == null){
            return 0;
        }
        //两棵树子树高度
        int leftVal = helper(node.left);
        int rightVal = helper(node.right);
        if(leftVal == NOT_BALANCE || rightVal == NOT_BALANCE || Math.abs(leftVal-rightVal)>1){
            return NOT_BALANCE;
        }
        return Math.max(leftVal,rightVal)+1;
    }
}
