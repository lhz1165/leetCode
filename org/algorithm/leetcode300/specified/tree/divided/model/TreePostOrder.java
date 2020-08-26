package org.algorithm.leetcode300.specified.tree.divided.model;

import org.algorithm.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/7/8
 * 后续遍历
 **/
public class  TreePostOrder {
    /**
     * 非递归迭代的方式后续遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return output;
    }
    /**
     * traverse
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helperPostorderTraversal2(root,result);
        return result;
    }
    public void helperPostorderTraversal2(TreeNode root,List<Integer>result) {
        if (root == null) {
            return;
        }
        helperPostorderTraversal2(root.left,result);
        helperPostorderTraversal2(root.right,result);
        result.add(root.val);

    }




    /**
     * divide-conquer的后续遍历
     * @param  root
     * @return List<Integer>
     */
    public List<Integer> postOrderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }
        // Divide
        List<Integer> left = postOrderTraversal3(root.left);
        List<Integer> right = postOrderTraversal3(root.right);

        // Conquer
        result.addAll(left);
        result.addAll(right);
        result.add(root.val);
        return result;
    }
}
