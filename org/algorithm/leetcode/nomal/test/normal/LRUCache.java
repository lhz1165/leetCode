package org.algorithm.leetcode.nomal.test.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2021/2/18
 **/
public class LRUCache {
    //["LRUCache","put","put","put","put","get","get"]
    //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(1,1); // 缓存是 {1=1, 2=2}
        lRUCache.put(2,3); // 缓存是 {1=1, 2=2}
        lRUCache.put(4,1); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.get(2);    // 返回 1



    }

    int size;
    Map<Integer,Node> keyToPrev;
    int capacity;
    Node dummy;
    Node tail;

    public LRUCache(int capacity) {
        this.dummy = new Node(0,0);
        this.capacity = capacity;
        this.keyToPrev = new HashMap<>();
        this.size = 0;
        this.tail = dummy;
    }

    public int get(int key) {
        Node prev = keyToPrev.get(key);
        if (prev == null) {
            return -1;
        }
        Node cur = prev.next;
        moveToTail(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        Node prev = keyToPrev.get(key);
        if (prev != null) {
            Node cur = prev.next;
            cur.val = value;
            moveToTail(cur);
            return;
        }

        Node newNode = new Node(key, value);
        if (capacity > size) {
            tail.next = newNode;
            keyToPrev.put(key, tail);
            tail = newNode;
            size++;
        }else {
            Node firstNode = dummy.next;
            keyToPrev.remove(firstNode.key);
            firstNode.key = key;
            firstNode.val = value;

            keyToPrev.put(key, dummy);
            moveToTail(firstNode);
        }


    }

    public void moveToTail(Node cur) {
        if (cur == tail) {
            return;
        }
        Node prev = keyToPrev.get(cur.key);
        if (prev == null) {
            return;
        }
        Node next = cur.next;
        keyToPrev.put(next.key, prev);
        keyToPrev.put(cur.key, tail);

        prev.next = next;
        tail.next = cur;
        tail = cur;
        tail.next = null;
    }

    static class Node{
        Node next;
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
