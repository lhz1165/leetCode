package org.example.leetcode300.specified.tree.divided.model;


import org.example.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/7/8
 * 中序遍历
 **/
public class TreeInOrder {
    /**
     * 非递归 ，迭代中序编列
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        TreeNode node = root;
        while (!stack.isEmpty()||node!=null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }


    /**
     * traverse
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helperinorderTraversal2(root, result);
        return result;
    }

    public void helperinorderTraversal2(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        helperinorderTraversal2(root.left, result);
        result.add(root.val);
        helperinorderTraversal2(root.right, result);


    }


    /**
     * divide conquer的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }
        // Divide
        List<Integer> left = inorderTraversal3(root.left);
        List<Integer> right = inorderTraversal3(root.right);

        // Conquer
        result.addAll(left);
        result.add(root.val);
        result.addAll(right);
        return result;
    }
}
