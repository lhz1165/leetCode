package org.example.leetcode300.specified.list;

import org.example.leetcode300.basic.ListNode;

/**
 * @author: lhz
 * @date: 2020/7/13
 **/
public class ListTest02 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        //n6.next = n3;
        System.out.println(detectCycle(n1));

    }
    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. if there is no cycle, return null
     */
    public static ListNode detectCycle(ListNode head) {
        // write your code here
        if (head == null || head.next == null) {
            return null;
        }
        ListNode p1 = head;//fast
        ListNode p2 = head;//slow
        ListNode p3 = head;
        ListNode p4 = null;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) {
                p4 = p1;
                break;
            }
        }
        if (p4 != null) {
            while (p3 != p4) {
                p3 = p3.next;
                p4 = p4.next;
            }
            return p3;
        }else {
            return null;

        }
    }


}
