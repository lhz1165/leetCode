package org.example.leetcode300.specified.tree.dfs.model;

import org.example.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/8
 **/
public class TreeLasOrder {
    /**
     * divide-conquer的后续遍历
     * @param  root
     * @return List<Integer>
     */
    public List<Integer> lastorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }
        // Divide
        List<Integer> left = lastorderTraversal3(root.left);
        List<Integer> right = lastorderTraversal3(root.right);

        // Conquer
        result.addAll(left);
        result.addAll(right);
        result.add(root.val);
        return result;
    }
}
