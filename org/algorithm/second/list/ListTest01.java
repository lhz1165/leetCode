package org.algorithm.second.list;

import org.algorithm.leetcode.basic.ListNode;

/**
 * @author: lhz
 * @date: 2020/9/11
 **/
public class ListTest01 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        ListTest01 t = new ListTest01();
        t.reverseBetween(n1, 2, 4);
    }


    /**
     *
     *
     * 翻转链表中第m个节点到第n个节点的部分
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        m--;
        n--;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        dummy.next = head;

        ListNode tail = null;
        ListNode secondHead = null;
        for (int i = 0; i <= m; i++) {
            if (i  == m) {
                secondHead = cur;
            }
            cur = cur.next;
        }
        tail = cur;
        ListNode prev = null;

        for (int i = 0; i < n - m + 1; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        secondHead.next = prev;
        tail.next = cur;
        return dummy.next;

    }
}
