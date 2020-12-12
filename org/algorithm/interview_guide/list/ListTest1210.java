package org.algorithm.interview_guide.list;

import org.algorithm.leetcode300.basic.ListNode;

import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/12/10
 */
public class ListTest1210 {

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
		ListTest1210 t = new ListTest1210();

		n1.printLn(t.reverseList(n1));


	}



	public ListNode removeK(ListNode head, int k) {
		ListNode fast = head;
		ListNode slow =head;
		for (int i = 0; i < k; i++) {
			fast = fast.next;
		}

		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		return slow;
	}


	public ListNode removeByRatio(ListNode head, int a, int b) {
		int len = 0;
		ListNode cur = head;
		while (cur != null) {
			len++;
			cur = cur.next;
		}
		//5 / 3 = 1
		int i = (int) Math.ceil((double) (a * len) /(double) b);
		cur = head;
		for (int j = 0; j < i; j++) {
			cur = cur.next;
		}
		cur.next = cur.next.next;
		return  head;
	}

	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode cur =head;
		ListNode next ;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur =next;
		}
		return prev;

	}










}
