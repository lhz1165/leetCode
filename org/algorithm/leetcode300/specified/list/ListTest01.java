package org.algorithm.leetcode300.specified.list;

import org.algorithm.leetcode300.basic.ListNode;

/**
 * @author lhzlhz
 * @create 2020/7/11
 */
public class ListTest01 {
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		ListNode.print(n1);
		System.out.println();
//		ListNode.print(reverse(n1));
//		System.out.println();

		//ListNode.print(reverseKGroup(n1,2));
		ListNode.print(rotateRight2(n1, 0));
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
	/**
	 * 两个排序链表合并为新链表
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// write your code here
		ListNode dummyNode = new ListNode(0);
		ListNode head = dummyNode;
		while (l1 != null && l2 != null) {
			if (l1.val > l2.val) {
				dummyNode.next = l2;
				dummyNode = dummyNode.next;
				l2 = l2.next;
			} else {
				dummyNode.next = l1;
				dummyNode = dummyNode.next;

				l1 = l1.next;
			}
		}

		while (l2 != null) {
			dummyNode.next = l2;
			dummyNode = dummyNode.next;

			l2 = l2.next;
		}

		while (l1 != null) {
			dummyNode.next = l1;
			dummyNode = dummyNode.next;
			l1 = l1.next;
		}


		return head.next;
	}

	/**
	 * 翻转链表中第m个节点到第n个节点的部分
	 *
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		// write your code here
		//设置虚拟头节点
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		dummy.next = head;

		//反转完之后的尾巴
		ListNode tail;
		//记录开始的前一个，secondHead.next=prev
		ListNode secondHead = null;
		for (int i = 0; i < m; i++) {
			if (i + 1 == m) {
				secondHead = cur;
			}
			cur = cur.next;
		}
		tail = cur;
		//反转后的头
		ListNode prev = null;

		for (int i = 0; i < n - m + 1; i++) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		//把反转后的头和尾连接起来
		secondHead.next = prev;
		tail.next = cur;
		return dummy.next;
	}

	/**
	 * 交换链表当中两个节点
	 *
	 * @param head
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static ListNode swapNodes(ListNode head, int v1, int v2) {
		// write your code here
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head;
		ListNode n1Pre = dummy;
		ListNode n1 = null;
		ListNode n1Next = null;
		ListNode n2Pre = dummy;
		ListNode n2 = null;
		ListNode n2Next = null;
		while (cur != null) {
			if (n1 == null && cur.val == v1) {
				n1 = cur;
				n1Next = n1.next;
			} else if (n1 == null) {
				n1Pre = n1Pre.next;
			}
			if (n2 == null && cur.val == v2) {
				n2 = cur;
				n2Next = n2.next;
			} else if (n2 == null) {
				n2Pre = n2Pre.next;
			}
			cur = cur.next;
			if (n1 != null && n2 != null) {
				break;
			}
		}
		if (n2 != null && n1 != null && n1.next == n2) {
			n1Pre.next = n2;
			n2.next = n1;
			n1.next = n2Next;
			return dummy.next;
		}
		if (n2 != null && n1 != null && n2.next == n1) {
			n2Pre.next = n1;
			n1.next = n2;
			n2.next = n1Next;
			return dummy.next;
		}

		if (n2 != null && n1 != null) {
			n1Pre.next = n2;
			n2.next = n1Next;

			n2Pre.next = n1;
			n1.next = n2Next;
		}
		return dummy.next;
	}


    /**
     * 旋转链表
     * @param head
     * @param k
     * @return
     */
	public static ListNode rotateRight2(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		int cont = 0;
        ListNode tail = null;
		ListNode cur = head;
		while (cur != null) {
            if (cur.next == null) {
                tail = cur;
            }
			cur = cur.next;
			cont++;
		}
		if (k == 0) {
			return head;
		}
		k = k%cont;
		if (k == 0) {
			return head;
		}
		ListNode dummyNode = new ListNode(0);
		//找出倒数第K个节点,为新的头节点
        ListNode newHead = getLastNumber(head, k);
        //新的尾节点
        ListNode prev = getLastNumber(head, k+1);
        dummyNode.next=newHead;
        tail.next = head;
        prev.next = null;
        return dummyNode.next;
	}

    public static ListNode getLastNumber(ListNode head, int k) {
        ListNode first = head;
        ListNode slow = head;

        for (int i = 0; i < k; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            slow = slow.next;
        }
        return slow;

    }


}
