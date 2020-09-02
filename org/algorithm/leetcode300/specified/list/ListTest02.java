package org.algorithm.leetcode300.specified.list;

import org.algorithm.leetcode300.basic.ListNode;

import java.util.Arrays;

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

        int[] arr ={3,2,5,6,1,4};
        System.out.println(Arrays.toString(mergeSort(arr)));

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
        } else {
            return null;

        }
    }

    /**
     * 归并排序
     * 采用分治的思想
     *
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // write your code here
        ListNode middleNode = findMiddle(head);

        ListNode l1 = sortList(middleNode.next);
        middleNode.next = null;
        ListNode l2 = sortList(head);

        return mergeList(l2, l1);
    }


    /**
     * 找到中点
     *
     * @param head
     * @return
     */
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 合并链表
     *
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeList(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    tail.next = l1;
                    tail = l1;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    tail = l2;
                    l2 = l2.next;
                }
            }

            if (l1 != null) {
                tail.next = l1;
            } else {
                tail.next = l2;
            }
            return dummy.next;
    }


    /**
     * 数组的归并排序
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length<2) {
            return arr;
        }
        int mid = (arr.length-1) / 2;
        int start = 0;
        int end = arr.length - 1;
        //divided
        int[] leftArr = mergeSort(Arrays.copyOfRange(arr, start, mid + 1));
        int[] rightArr = mergeSort(Arrays.copyOfRange(arr, mid + 1, end + 1));
        //conquer
        return merge(leftArr, rightArr);
    }

    public static int[] merge(int[] leftArr,int[] rightArr){
        int[] result = new int[leftArr.length + rightArr.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                result[index++] = leftArr[i++];
            }else {
                result[index++] = rightArr[j++];

            }
        }
        while (j < rightArr.length) {
            result[index++] = rightArr[j++];
        }
        while (i < leftArr.length) {
            result[index++] = leftArr[i++];
        }

        return result;
    }


}
