package org.algorithm.leetcode.nomal.test.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2021/1/29
 **/
public class LRUCache2 {
    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }

    ListNode dummy;
    Map<Integer,ListNode> key2PrevNode;
    Integer capacity;
    Integer size;
    ListNode tail;


    public LRUCache2(int capacity) {
        dummy = new ListNode(0, 0);
        key2PrevNode = new HashMap<>();
        this.capacity  = capacity;
        size = 0;
    }

    public int get(int key) {
        ListNode prev = key2PrevNode.get(key);
        if(prev == null){
            return -1;
        }
        moveToTail(key);
        return tail.val;
    }

    public void put(int key, int value) {
        ListNode prev = key2PrevNode.get(key);
        if(prev != null){
            moveToTail(key);
            tail.val = value;
            return;
        }

        if(size < capacity){
            ListNode newNode = new ListNode(key, value);
            tail.next = newNode;
            key2PrevNode.put(key, tail);
            tail = newNode;
            size++;
        }else{
            ListNode throwNode = dummy.next;
            dummy.next = throwNode.next;
            key2PrevNode.put(throwNode.next.val, dummy);

            ListNode newNode = new ListNode(key, value);
            tail.next = newNode;
            key2PrevNode.put(key, tail);
            tail = newNode;

        }
    }

    public void moveToTail(int key) {
        ListNode prev = key2PrevNode.get(key);
        ListNode cur = prev.next;
        if(tail == cur){
            return;
        }
        ListNode next = prev.next.next;
        tail.next = cur;
        prev.next = next;
        key2PrevNode.put(cur.val,tail);
        key2PrevNode.put(next.val,prev);
        tail = cur;
        tail.next = null;
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

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
