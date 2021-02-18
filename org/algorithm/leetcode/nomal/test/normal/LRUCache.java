package org.algorithm.leetcode.nomal.test.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2021/2/18
 **/
public class LRUCache {
    int size;
    Map<Integer,Node> keyToPrev;
    int capacity;
    Node dummy;
    Node tail;

    public LRUCache(int capacity) {
        this.dummy = new Node(0);
        this.capacity = capacity;
        this.keyToPrev = new HashMap<>();
        this.size = 0;
        this.tail = dummy;
    }

    public int get(int key) {

    }

    public void put(int key, int value) {

    }

    public void moveToTail(Node cur) {
        Node prev = keyToPrev.get(cur.val);
        if (prev == null) {
            return;
        }



    }

    static class Node{
        Node next;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }
}
