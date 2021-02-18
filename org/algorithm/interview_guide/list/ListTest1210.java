package org.algorithm.interview_guide.list;

import org.algorithm.leetcode.basic.ListNode;

import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/12/10
 */
public class ListTest1210 {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(2);
		ListNode n5 = new ListNode(1);
		ListNode n6 = new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
//		n5.next = n6;
//		n6.next = n1;

		ListTest1210 t = new ListTest1210();

		System.out.println(t.isPalindrmoel(n1));


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

	/**
	 * 约瑟夫问题
	 */
	public ListNode josephusKill(ListNode head, int m) {
		ListNode prev = head;
		while (prev.next != head) {
			prev = prev.next;
		}
		int count = 1;
		while (prev != head) {
			//自杀
			if (count == m) {
				prev.next = head.next;
				count = 1;
			}else {
				prev = prev.next;
				count++;
			}
			head = head.next;
		}
		return head;
	}



	public boolean isPalindrmoel(ListNode head) {
		int i = 0;
		boolean isJishu = false;
		ListNode cur = head;
		while (cur != null) {
			cur = cur.next;
			i++;
		}
		if (i % 2 != 0) {
			isJishu = true;
		}
		i = i / 2;
		Stack<Integer> stack = new Stack<>();
		for (int j = 0; j < i; j++) {
			stack.push(head.val);
			head = head.next;
		}
		if(isJishu){
			head = head.next;
		}
		for (int j = 0; j < i; j++) {
			if (head.val == stack.pop()) {
				head = head.next;
			}else {
				return false;
			}
		}
		return true;
	}










}
