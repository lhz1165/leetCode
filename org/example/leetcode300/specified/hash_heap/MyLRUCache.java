package org.example.leetcode300.specified.hash_heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhzlhz
 * @create 2020/8/1
 */
public class MyLRUCache {
	public static void main(String[] args) {
		MyLRUCache cache = new MyLRUCache(2);
		cache.set(2, 1);
		cache.set(1, 1);
		cache.get(2);
		cache.set(4,1);
		cache.get(1);
		cache.get(2);
		System.out.println();

	}

	private ListNode dummy;
	private int capacity;
	private ListNode tail;
	private int size;

	private Map<Integer,ListNode> keyOfPrev;

	/*
	 * @param capacity: An integer
	 */
	public MyLRUCache(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		keyOfPrev = new HashMap<>();
		dummy = new ListNode(0,0);
		tail = dummy;
	}

	/*
	 * @param key: An integer
	 * @return: An integer
	 */
	public int get(int key) {
		// write your code here
		if (!keyOfPrev.containsKey(key)) {
			return -1;
		}
		//先把它移动到最尾部
		moveToTail(key);
		return tail.val;
	}

	public void moveToTail(int key) {
		ListNode prev = keyOfPrev.get(key);
		ListNode cur = prev.next;
		//如果是最后一位无需移动
		if (cur.next == null) {
			return;
		}
		//修改prev
		ListNode newPrev = tail;
		keyOfPrev.put(cur.key, newPrev);
		keyOfPrev.put(cur.next.key, prev);
		prev.next = prev.next.next;
		tail.next = cur;
		tail = cur;
		tail.next = null;


	}

	/*
	 * @param key: An integer
	 * @param value: An integer
	 * @return: nothing
	 * 添加的同时记住把pre也存进去
	 */
	public void set(int key, int value) {
		if (keyOfPrev.containsKey(key)) {
			ListNode listNode = keyOfPrev.get(key);
			listNode.val = value;
		}
		ListNode cur=new ListNode(key,value);
		if (size < capacity) {
			tail.next = cur;
			ListNode prev = tail;
			keyOfPrev.put(key, prev);
			tail = cur;
			size++;
			return;
		}
		//如果溢出了，移除最落后的
		ListNode prev = tail;
		tail.next = cur;
		keyOfPrev.put(key, prev);
		keyOfPrev.remove(dummy.next.key);
		tail = cur;
		ListNode futureNext = dummy.next.next;
		dummy.next = futureNext;
		keyOfPrev.put(futureNext.key, dummy);
	}



	class ListNode {
		public int key, val;
		public ListNode next;
		public ListNode(int key, int val) {
			this.key = key;
			this.val = val;
			this.next = null;
		}
	}
}
