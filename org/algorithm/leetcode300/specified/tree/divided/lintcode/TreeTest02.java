package org.algorithm.leetcode300.specified.tree.divided.lintcode;

import org.algorithm.leetcode300.basic.TreeNode;

/**
 * @author: lhz
 * @date: 2020/10/14
 **/
public class TreeTest02 {
    public boolean isValidBST(TreeNode root) {
        // write your code here
        if ( root.right != null) {
            if (root.right.val < root.val) {
                return false;
            }else {
                return isValidBST(root.right);
            }
        }else if ( root.left != null) {
            if (root.left.val > root.val) {
                return false;
            }else {
                return isValidBST(root.left);
            }
        }else {
            return true;
        }
    }
}
