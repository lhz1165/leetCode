package org.example.leetcode300.specified.list;

import org.example.leetcode300.basic.ListNode;

import javax.swing.*;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/15
 **/
public class ListTest03 {
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
        reverseKGroup(n1, 2);

    }
    /**
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // write your code here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while (pre != null) {
            pre=reversePreK(pre, k);
        }
        return dummy.next;
    }

    private static ListNode reversePreK(ListNode pre, int k) {
        ListNode cur = pre;
        ListNode newHead = cur.next;

        for (int i = 0; i < k; i++) {
            cur = cur.next;
            if (cur == null) {
                return null;
            }
        }

        ListNode tail = cur;
        ListNode nextHead = tail.next;

        //反转
        ListNode head = pre.next;
        ListNode newPre = null;
        for (int i = 0; i < k; i++) {
            ListNode next = head.next;
            head.next = newPre;
            newPre = head;
            head = next;
        }
        pre.next = tail;
        newHead.next = nextHead;
        return newHead;


    }
}
