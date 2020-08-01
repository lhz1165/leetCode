package org.example.leetcode300.specified.hash_heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhzlhz
 * @create 2020/8/1
 * LRU建议反复练习
 */
public class LRUCache {
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);
		cache.set(1,2);
		cache.set(2,3);
		cache.set(3,3);
		cache.set(4,3);

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
	//容量
	private int capacity;
	//当前大小
	private int size;
	private ListNode dummy;
	private ListNode tail;
	//用它来保存某个节点的前一个节点
	private Map<Integer,ListNode> keyToPrev;

	/*
	 * @param capacity: An integer
	 */public LRUCache(int capacity) {
		// do intialization if necessary
		this.capacity = capacity;
		this.keyToPrev = new HashMap<Integer, ListNode>();
		this.dummy = new ListNode(0, 0);
		this.tail = this.dummy;
	}

	/*
	 * @param key: An integer
	 * @return: An integer
	 * 获取了之后需要更新到最尾部 防止被淘汰
	 */
	public int get(int key) {
		// write your code here
		if (!keyToPrev.containsKey(key)) {
			return -1;
		}
		//移动到链表的最尾部，
		//找出最新的那一个
		moveToTail(key);

		return tail.val;
	}

	/**
	 * 元素给更新到最尾部
	 * @param key
	 */
	private void moveToTail(int key) {
		//找出当前key的前一个
		ListNode prev = keyToPrev.get(key);
		ListNode curt = prev.next;
		//如果取得就是最后一个，那么不用更新
		if (tail == curt) {
			return;
		}

		prev.next = prev.next.next;
		tail.next = curt;
		//因为当前节点跑到最后面去了
		//下一个节点的前一个节点
		if (prev.next != null) {
			keyToPrev.put(prev.next.key, prev);
		}
		tail=curt;
	}

	/*
	 * @param key: An integer
	 * @param value: An integer
	 * @return: nothing
	 */
	public void set(int key, int value) {
		// write your code here
		if (get(key) != -1) {
			ListNode prev = keyToPrev.get(key);
			prev.next.val = value;
			return;
		}

		//淘汰最后一个
		if (size < capacity) {
			size++;
			ListNode curt = new ListNode(key, value);
			tail.next = curt;
			keyToPrev.put(key, tail);

			tail = curt;
			return;
		}

		//先把最旧的点换成新来的节点
		//然后再把这个新来的节点移动到最后面去
		ListNode first = dummy.next;
		keyToPrev.remove(first.key);
		first.key = key;
		first.val = value;
		keyToPrev.put(key, dummy);
		moveToTail(key);
	}
}
