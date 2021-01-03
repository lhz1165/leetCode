package org.algorithm.leetcode300.nomal.test.everyday;

import org.algorithm.leetcode300.basic.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/12/30
 **/
public class EveryDay05 {
	public static void main(String[] args) {
		EveryDay05 e = new EveryDay05();
		//[[0,2],[1,3],[2,4],[3,5],[4,6]]
		int[][] ints = {{0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}};
		System.out.println(e.eraseOverlapIntervals(ints));
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(2);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(2);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		e.partition(n1,3);

	}

	/**
	 * 435. 无重叠区间
	 * 如果重合，需要删除，删掉右边界最长的那一段
	 * 接着遍历
	 */
	public int eraseOverlapIntervals(int[][] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		List<Pair> pairs = new ArrayList<>();
		for (int[] interval : intervals) {
			pairs.add(new Pair(interval[0], interval[1]));
		}
		pairs.sort((o1, o2) -> {
			if (o1.start - o2.start == 0) {
				return o1.end - o2.end;
			} else {
				return o1.start - o2.start;
			}
		});
		Pair pair = pairs.get(0);
		int res = 0;
		int prevStart = pair.start;
		int prevEnd = pair.end;
		for (int i = 1; i < pairs.size(); i++) {
			Pair current = pairs.get(i);
			int curStart = current.start;
			int curEnd = current.end;
			if (curStart < prevEnd) {
				if (curStart == prevStart && prevEnd == curEnd) {
					res++;
				} else if (curStart == prevStart && prevEnd < curEnd) {
					res++;
				} else if (curStart > prevStart && prevEnd >= curEnd) {
					res++;
					prevStart = curStart;
					prevEnd = curEnd;
				} else if (curStart > prevStart && curEnd > prevEnd) {
					res++;
				}
			} else {
				prevStart = curStart;
				prevEnd = curEnd;
			}
		}
		return res;


	}

	/**
	 * 86. 分隔链表
	 */
	public ListNode partition(ListNode head, int x) {
		//把链表放入数组
		ListNode[] listArr = putListIntoArr(head);
		//划分
		partition(listArr, x);


		//链接数组中的节点
		ListNode newHead = convertArrToList(listArr);
		return newHead;
	}

	/**
	 * 快速排序
	 * 小于x的节点一边
	 * 大于等于x的节点一边
	 */
	public void partition(ListNode[] nodes, int pivot) {
		int start = 0;
		int end = nodes.length - 1;
		while (start + 1 < end) {
			while (start + 1 < end && nodes[start].val < pivot) {
				start++;
			}
			while (start + 1 < end && nodes[end].val > pivot) {
				end--;
			}
			swap(nodes,start,end);
			start++;
			end--;
		}
	}
	/**
	 * 快速排序
	 * 小于x的节点一边
	 * 等于x的节点一边
	 * 大于x的节点一边
	 */
	public void partition2(ListNode[] nodes, int pivot) {
		int start = -1;
		int end = nodes.length ;
		int index = 0;
		while (index != end) {
			if (nodes[index].val < pivot) {
				start++;
				swap(nodes, index, start);
				index++;
			} else if (nodes[index].val > pivot) {
				end--;
				swap(nodes, index, end);
			} else {
				index++;
			}
		}
	}

	public void swap(ListNode[] arr, int a, int b) {
		ListNode tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;

	}

	public ListNode[] putListIntoArr(ListNode head) {
		ListNode cur = head;
		int len = 0;
		while (cur != null) {
			len++;
			cur = cur.next;
		}
		cur = head;
		ListNode[] listArr = new ListNode[len];
		int index = 0;
		while (cur != null) {
			listArr[index++] = cur;
			cur = cur.next;
		}
		return listArr;
	}

	public ListNode convertArrToList(ListNode[] arr) {
		ListNode prev = null;
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				prev = arr[i];
			} else {
				prev.next = arr[i];
				prev = arr[i];
			}
		}
		arr[arr.length - 1].next = null;
		return arr[0];
	}


}

class Pair {
	int start;
	int end;

	public Pair(int start, int end) {
		this.start = start;
		this.end = end;
	}
}
