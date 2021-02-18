package org.algorithm.leetcode.basic;

import java.util.List;

/**
 * @author: lhz
 * @date: 2020/5/22
 **/
public class ListNode {
     public int val;
     public ListNode next;
    public ListNode(int v) {
        val = v;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n4.next = n2;
        n1.next = n4;

        System.out.println();

        n1.printLn(n1);

    }

    public  void printLn(ListNode head){
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }


    }








    public static void print(ListNode node) {
        while (node.next != null) {
            System.out.print(node.val);
            System.out.print("->");
            node = node.next;
        }
        if (node.next == null) {
            System.out.print(node.val);
        }

    }

    public static void ad(List<Integer> list) {
        list.add(4);
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
