package org.algorithm.second.list;

import org.algorithm.leetcode300.basic.ListNode;

/**
 * @author: lhz
 * @date: 2020/9/11
 **/
public class ListTest01 {


    /**
     *
     *
     * 翻转链表中第m个节点到第n个节点的部分
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        m--;
        n--;
        ListNode cur =head;
        ListNode newHead = null;
        for (int i = 0; i <= m; i++) {
            if (i == m) {
                newHead = cur;
            }
            cur = cur.next;
        }

        ListNode prev = null;
        for (int i = 0; i < n - m + 1; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

    }
}
