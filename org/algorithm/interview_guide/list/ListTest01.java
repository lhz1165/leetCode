package org.algorithm.interview_guide.list;

import org.algorithm.leetcode300.basic.ListNode;
import org.algorithm.leetcode300.basic.TreeNode;

import java.util.*;

/**
 * @author lhzlhz
 * @create 2020/11/12
 */
public class ListTest01 {
    public static void main(String[] args) {
        ListTest01 t = new ListTest01();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(6);
//        ListNode n8 = new ListNode(8);
////
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n1;
//		//n8.next = n1;
//		t.listPartition(n1, 6);
        System.out.println();
        t.josephusKill1(n1,2);
        System.out.println();

    }


    /**
     * 约瑟夫环问题
     * 先找到last
     * last永远在head之后
     * last.next = head .next就是删除节点
     *
     * @param head
     * @param m
     * @return
     */
    public ListNode josephusKill1(ListNode head, int m) {
        ListNode last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }


    /**
     * 将单向链表，按某值划分成左小，中间等，右边大的形式
     */
    public ListNode listPartition(ListNode head, int pivot) {
        ListNode cur = head;
        //多少个节点
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        //数组
        ListNode[] nodes = new ListNode[count];
        //放进去
        int i = 0;
        ListNode cur2 = head;
        while (cur2 != null) {
            nodes[i++] = cur2;
            cur2 = cur2.next;
        }
        //快速排序
        quickSort(nodes, pivot);
        for (int j = 0; j < nodes.length - 1; j++) {
            nodes[j].next = nodes[j + 1];
        }
        return nodes[0];
    }

    /**
     * nodes[start].val < pivot
     * nodes[end].val >= pivot
     * 表示右边是大于等于pivot的 左边是小于pivot的
     * 不能保证中间是pivot
     *
     * @param nodes
     * @param pivot
     */
    private void quickSort(ListNode[] nodes, int pivot) {
        int start = 0;
        int end = nodes.length - 1;
        while (start <= end) {
            while (start <= end && nodes[start].val < pivot) {
                start++;
            }
            while (start <= end && nodes[end].val >= pivot) {
                end--;
            }
            if (start <= end) {
                ListNode tmp = nodes[start];
                nodes[start] = nodes[end];
                nodes[end] = tmp;
                start++;
                end--;
            }
        }
    }

    /**
     * ！！！！！！严格按照小于pivot的排左边，大于pivot的排右边，等于pivot的排中间
     *
     * @param nodes
     * @param pivot
     */
    private void quickSort2(ListNode[] nodes, int pivot) {
        int start = 0;
        int end = nodes.length - 1;
        int index = 0;
        while (index != end) {
            if (nodes[index].val < pivot) {
                swap(nodes, index, start);
                index++;
                start++;
            } else if (nodes[index].val > pivot) {
                swap(nodes, index, end);
                end--;
            } else {
                index++;
            }
        }

    }

    public void swap(ListNode[] arr, int start, int end) {
        if (start <= end) {
            ListNode tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }
    }
    /**
     * 复制含有随机指针节点的链表
     */


    /**
     * 相加链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }
        ListNode pre = null;
        int cal = 0;
        ListNode cur = null;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int n1 = s1.isEmpty() ? 0 : s1.pop().val;
            int n2 = s2.isEmpty() ? 0 : s2.pop().val;
            int add = n1 + n2;
            cur = new ListNode(add % 10 + cal);
            cur.next = pre;
            pre = cur;
            cal = add / 10;
        }
        if (cal == 1) {
            cur = new ListNode(1);
            cur.next = pre;
        }
        return cur;
    }

    /**
     * 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        Stack<ListNode> nodes = new Stack<>();
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            ++len;
            cur = cur.next;
        }
        ListNode mid = head;
        for (int i = 0; i < len / 2; i++) {
            nodes.push(mid);
            mid = mid.next;
        }
        if (len % 2 != 0) {
            mid = mid.next;
        }
        while (mid != null) {
            if (nodes.pop().val != mid.val) {
                return false;
            }
            mid = mid.next;
        }
        return true;
    }

    /**
     * 奇偶链表
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            ++len;
        }
        int len1 = len % 2 == 0 ? len / 2 : len / 2 + 1;
        ListNode[] l1 = new ListNode[len1];
        ListNode[] l2 = new ListNode[len / 2];
        ListNode n1 = head;
        int index = 0;
        while (n1 != null) {
            l1[index++] = n1;

            if (n1.next != null) {
                n1 = n1.next.next;
            } else {
                n1 = n1.next;
            }
        }
        ListNode n2 = head.next;
        index = 0;
        while (n2 != null) {
            l2[index++] = n2;
            if (n2.next != null) {
                n2 = n2.next.next;
            } else {
                n2 = n2.next;
            }
        }
        ListNode prev = head;
        for (int i = 1; i < l1.length; i++) {
            prev.next = l1[i];
            prev = l1[i];
        }
        if (l2.length > 0) {
            prev.next = l2[0];
            prev = l2[0];
            for (int i = 1; i < l2.length; i++) {
                prev.next = l2[i];
                prev = l2[i];
            }
            l2[l2.length - 1].next = null;
        }

        return head;
    }

    /**
     * 搜索二叉树变成链表
     * left = last;
     * right = next;
     */
    public TreeNode convert1(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<>();
        inOrderToQueue(head,queue);
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll();
        TreeNode pre = head;
        pre.left = null;
        TreeNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right =cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;


    }

    public void inOrderToQueue(TreeNode node, Queue<TreeNode> queue) {
        if (node == null) {
            return;
        }
        inOrderToQueue(node.left, queue);
        queue.offer(node);
        inOrderToQueue(node.right, queue);


    }
}
