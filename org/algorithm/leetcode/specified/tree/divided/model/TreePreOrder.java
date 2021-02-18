package org.algorithm.leetcode.specified.tree.divided.model;

import org.algorithm.leetcode.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/7/8
 **/
public class TreePreOrder {
    /**
     * 非递归的方式
     * 前序遍历二叉树
     *
     * @param root
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }


    /**
     * 递归解决二叉树前序遍历
     * @param root
     * @return
     */
    public ArrayList<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        traverse(root, result);
        return result;
    }

    // 把root为跟的preorder加入result里面
    private void traverse(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }

    //Version 2: Divide & Conquer
    /**
     * 分治法来遍历
     * @param root
     * @return
     */
    public ArrayList<Integer> preorderTraversal3(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }
        // Divide
        ArrayList<Integer> left = preorderTraversal3(root.left);
        ArrayList<Integer> right = preorderTraversal3(root.right);

        // Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }


}
