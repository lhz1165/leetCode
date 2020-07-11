package org.example.leetcode300.specified.list;

import org.example.leetcode300.basic.ListNode;
import sun.util.resources.cldr.xh.CurrencyNames_xh;

/**
 * @author lhzlhz
 * @create 2020/7/11
 */
public class ListTest01 {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		ListNode.print(n1);
		System.out.println();
//		ListNode.print(reverse(n1));
//		System.out.println();

		//ListNode.print(reverseKGroup(n1,2));
		ListNode.print(partition(n1, 3));
	}

	/**
	 * 反转链表
	 * @param head
	 * @return
	 */
	public static ListNode reverse(ListNode head) {
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	/**
	 *
	 * K组翻转链表
	 *
	 * Input:
	 * list = 1->2->3->4->5->null
	 * k = 2
	 * Output:
	 * 2->1->4->3->5
	 *
	 * 思路：增加一个dummyNode (dummy->1->2->3->4->5->null)他永远指向头
	 * @param head: a ListNode
	 * @param k: An integer
	 * @return: a ListNode
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
		// write your code here
		ListNode cur = head;
		ListNode dummyNode = new ListNode(0);
		dummyNode.next = head;
		ListNode pre = dummyNode;
		while (pre != null) {
			//部分反转
			//例如1->2->3->4 ===> 2->1->3->4 ==>2->1->4->3
			pre=reversePreK(pre, k);
		}

		return dummyNode.next;
	}
	//k=2 先反转前两个
	//1-2-3-4===》2-1-3-4
	public static ListNode reversePreK(ListNode head, int k) {
		//cur =0(dummy)
		ListNode cur = head;
		//n1 = 1
		ListNode n1 = head.next;
		for (int i = 0; i < k; i++) {
			cur = cur.next;
			if (cur == null) {
				return null;
			}
		}
		//设定边界
		//cur = 2
		//nkplus = 3
		ListNode nk = cur;
		ListNode nkplus = cur.next;

		//reverse
		//pre = 0(dummy)
		//cur = 1
		ListNode pre = head;
		cur = head.next;
		while (cur != nkplus) {
			ListNode tmp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = tmp;
		}
		//2-1-0 ,3-4再组合在一起
		//head =0 ,n1 =1,nkplus=3,nk=2
		head.next = nk;
		n1.next = nkplus;
		//0-2-1-3-4
		//返回下一个前驱
		return n1;
	}

	/**
	 * 86. 分隔链表 或划分链表
	 * @param head
	 * @param x
	 * @return
	 */
	public static ListNode partition(ListNode head, int x) {
		ListNode dummy = new ListNode(0);
		ListNode dummyHead = dummy;
		ListNode curLittle = head;
		ListNode curBig = head;
		while (curLittle != null) {
			if (curLittle.val < x) {
				dummy.next = new ListNode(curLittle.val);
				dummy = dummy.next;
			}
			curLittle = curLittle.next;
		}
		while (curBig != null) {
			if (curBig.val >= x) {
				dummy.next = new ListNode(curBig.val);
				dummy = dummy.next;
			}
			curBig = curBig.next;
		}
		return dummyHead.next;
	}
}