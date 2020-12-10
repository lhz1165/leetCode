package org.algorithm.interview_guide.list;

import org.algorithm.leetcode300.basic.ListNode;

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
		t.removeK(n1,3);


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




}
