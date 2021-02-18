package org.algorithm.leetcode.basic;

/**
 * @author: lhz
 * @date: 2020/7/2
 **/

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}


