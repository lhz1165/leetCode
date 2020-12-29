package org.algorithm.leetcode300.nomal.test.normal;

import org.algorithm.leetcode300.basic.ListNode;

import javax.swing.*;

/**
 * @author: lhz
 * @date: 2020/12/29
 **/
public class NormalTest02 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}};
        NormalTest02 n = new NormalTest02();
        System.out.println(n.minPathSum(a));

    }
    /**
     *96. 不同的二叉搜索树
     * dp 【离谱】
     *   G[i] 为根的二叉搜索树的个数
     * f[n] = G[1]+G[2]+...G[N]
     *
     * G[j]从j又分为两段，
     * 0 - （i-1）表示左子树个数
     * （j+1） - n 表示右子树个数
     * 所以 G[j] = f[j-1] * f[n - j];   ==> 笛卡尔积
     *
     *
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
     *
     * 287. 寻找重复数
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
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
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;


    }



}
