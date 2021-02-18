package org.algorithm.leetcode.nomal.test.normal;


import org.algorithm.leetcode.basic.ListNode;
import org.algorithm.leetcode.basic.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/12/29
 **/
public class NormalTest02 {
    public static void main(String[] args) {

        NormalTest02 n = new NormalTest02();

        List<Integer> preOrder = new ArrayList<>(Arrays.asList(3,2,1,4));
        List<Integer> inOrder = new ArrayList<>(Arrays.asList(1,2,3,4));
        TreeNode treeNode = n.helperBuildTree(preOrder, inOrder);
        System.out.println();


    }

    /**
     * 96. 不同的二叉搜索树
     * dp 【离谱】
     * G[i] 为根的二叉搜索树的个数
     * f[n] = G[1]+G[2]+...G[N]
     * <p>
     * G[j]从j又分为两段，
     * 0 - （i-1）表示左子树个数
     * （j+1） - n 表示右子树个数
     * 所以 G[j] = f[j-1] * f[n - j];   ==> 笛卡尔积
     */
    public int numTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                f[i] += f[j - 1] * f[i - j];
            }
        }
        return f[n];
    }

    /**
     * 64. 最小路径和
     * dp
     */
    public int minPathSum(int[][] grid) {
        //行
        int n = grid.length;
        //列
        int m = grid[0].length;
        int[][] f = new int[n][m];
        f[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            f[0][i] = f[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = Math.min(f[i - 1][j] + grid[i][j], f[i][j - 1] + grid[i][j]);
            }
        }
        return f[n - 1][m - 1];
    }

    /**
     * 148. 排序链表
     * 归并排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode midNode = findMiddleNode(head);
        ListNode rightNode = sortList(midNode.next);
        midNode.next = null;
        ListNode leftNode = sortList(head);
        return mergeTwoList(rightNode, leftNode);

    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }
        return dummy.next;
    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 287. 寻找重复数
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     * <p>
     * 快慢指针法
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        List<Integer> preorder2 = new ArrayList<>();
        List<Integer> inorder2 = new ArrayList<>();
        for (int i : preorder) {
            preorder2.add(i);
        }
        for (int i : inorder) {
            inorder2.add(i);
        }
        return helperBuildTree(preorder2, inorder2);
    }

    public TreeNode helperBuildTree(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.size() == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder.get(0));
        if (preorder.size() == 1) {
            return root;
        }
        List<Integer> leftInorder = new ArrayList<>();
        List<Integer> leftPrerder = new ArrayList<>();
        List<Integer> rightInorder = new ArrayList<>();
        List<Integer> rightPrerder = new ArrayList<>();
        boolean isleft = true;
        for (Integer p : inorder) {
            if (p.equals(preorder.get(0))) {
                isleft = false;
                continue;
            }
            if (isleft) {
                leftInorder.add(p);
            } else {
                rightInorder.add(p);
            }
        }
        for (Integer p : preorder) {
            if (p.equals(preorder.get(0))) {
                continue;
            }
            if (leftInorder.size() == 0) {
                rightPrerder.add(p);
            }else if (rightInorder.size() == 0) {
                leftPrerder.add(p);
            } else {
                if (leftInorder.contains(p)) {
                    leftPrerder.add(p);
                }else {
                    rightPrerder.add(p);
                }
            }
        }
        root.left = helperBuildTree(leftPrerder, leftInorder);
        root.right = helperBuildTree(rightPrerder, rightInorder);
        return root;
    }


}
