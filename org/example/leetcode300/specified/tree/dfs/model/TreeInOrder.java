package org.example.leetcode300.specified.tree.dfs.model;

import org.example.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/8
 **/
public class TreeInOrder {

    /**
     * divide conquer的中序遍历
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
