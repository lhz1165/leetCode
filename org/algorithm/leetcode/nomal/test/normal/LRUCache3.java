package org.algorithm.leetcode.nomal.test.normal;

import java.util.HashMap;

/**
 * @author: lhz
 * @date: 2021/1/11
 **/
public class LRUCache3 {
    public static void main(String[] args) {
        LRUCache3 cache  = new LRUCache3(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

    }

    public final void a() {
        System.out.println("a");
    }

    ListNode dummy;
    int size;
    int capacity;
    ListNode tail;
    HashMap<Integer,ListNode> keyToPrevNode;

    public LRUCache3(int capacity) {
        size = 0;
        this.capacity = capacity;
        dummy = new ListNode(0,0);
        keyToPrevNode = new HashMap<>();
        tail = dummy;
    }

    public int get(int key) {
        ListNode prevNode = keyToPrevNode.get(key);
        if (prevNode == null) {
            return -1;
        }
        moveToTail(key);
        return tail.val;
    }

    public void put(int key, int value) {
        ListNode prevNode = keyToPrevNode.get(key);
        if (prevNode != null) {
            ListNode currentNode = prevNode.next;
            currentNode.val = value;
            moveToTail(key);
        } else if (size < capacity) {
            ListNode newNode = new ListNode(key, value);
            keyToPrevNode.put(key, tail);
            tail.next = newNode;
            tail = newNode;
            size++;
        } else {
            ListNode first = dummy.next;
            keyToPrevNode.remove(first.key);
            first.key = key;
            first.val = value;
            keyToPrevNode.put(key, dummy);
            moveToTail(key);
        }


    }

    public void moveToTail(int key) {
        //找出当前key的前一个
        ListNode prev = keyToPrevNode.get(key);
        ListNode curt = prev.next;
        //如果取得就是最后一个，那么不用更新
        if (tail == curt) {
            return;
        }
        prev.next = prev.next.next;
        tail.next = curt;
        //if (prev.next != null) {
            keyToPrevNode.put(prev.next.key, prev);
        //}
        keyToPrevNode.put(curt.key, tail);
        tail=curt;
    }


    static class ListNode{
        ListNode next;
        int val;
        int key;

        public ListNode(int key,int val) {
            this.key = key;
            this.val = val;
        }
    }
}
