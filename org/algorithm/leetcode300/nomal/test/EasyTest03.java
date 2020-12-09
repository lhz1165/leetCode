package org.algorithm.leetcode300.nomal.test;

import org.algorithm.leetcode300.basic.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: lhz
 * @date: 2020/12/8
 **/
public class EasyTest03 {
    /**
     * 1184. 公交站间的距离
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            int tmp = start;
            start = destination;
            destination = tmp;
        }

        int all = 0;
        for (int i : distance) {
            all += i;
        }
        int result = 0;

        for (int i = 0; i < distance.length; i++) {
            if (i == destination) {
                break;
            }
            if (start <= i) {
                result += distance[i];
            }
        }
        result = Math.min(result, all - result);


        return result;
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findAnother(root, k, set);
    }

    public boolean findAnother(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        int remain = k - root.val;
        if (set.contains(remain)) {
            return true;
        }else {
            set.add(root.val);
            return findAnother(root.left, k, set) || findAnother(root.right, k, set);
        }
    }


}
