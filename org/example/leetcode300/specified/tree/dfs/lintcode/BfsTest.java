package org.example.leetcode300.specified.tree.dfs.lintcode;


import org.example.leetcode300.demo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lhz
 * @date: 2020/7/2
 **/
public class BfsTest {
    public static void main(String[] args) {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(a));
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int i = 1; i <= n; i++) {
            f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
        }
        return f[n - 1];
    }

    public static int maxSubArray2(int[] nums) {
        int n = nums.length;
        int f[] = new int[n];
        f[0] = nums[0];
        for (int i = 1; i < n; i++) {
            //f[i]=Math.max(f[i-1]+nums[i],nums[i]);
            if (f[i - 1] > 0) {
                f[i] = f[i - 1] + nums[i];
            } else {
                f[i] = nums[i];
            }
        }
        System.out.println();
        return f[n - 1];
    }

    /**
     * 层次遍历二叉树
     *
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        //1定义一个队列，把每一层的节点放进去
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        if (null == root) {
            return result;
        }
        //2遍历每层，处理节点，并拓展新节点
        while (!queue.isEmpty()) {
            //3遍历每一层的每个数据
            int size = queue.size();
            //4创建集合 用来保存每一层的结果
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                level.add(node.val);
            }
            result.add(level);
        }
        return result;
    }
}
