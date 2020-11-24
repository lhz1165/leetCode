package org.algorithm.leetcode300.specified.tree.divided.lintcode;

import org.algorithm.leetcode300.basic.TreeNode;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author: lhz
 * @date: 2020/11/24
 **/
public class TreeTest03 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftNum = countNodes(root.left);
        int rightNum = countNodes(root.right);
        return 1 + leftNum + rightNum;
    }

    public static void main(String[] args) {
        System.out.println(ChronoUnit.DAYS.between(LocalDate.of(2020,11,23), LocalDate.now()));
    }


}
