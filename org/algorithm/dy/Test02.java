package org.algorithm.dy;

import org.algorithm.leetcode.basic.ListNode;
import org.algorithm.leetcode.basic.TreeNode;

import java.util.*;

/**
 * @author lhzlhz
 * @create 8/11/2021
 */
public class Test02 {


	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		while (cur != null) {
			Node clonCur = new Node(cur.val);

			//next
			clonCur.next = cur.next;
			cur.next = clonCur;

			cur = cur.next.next;
		}
		cur = head;
		while (cur != null) {
			Node random = cur.random;
			Node cloneCur = cur.next;
			//random
			if (random != null) {
				cloneCur.random = random.next;
			}
			cur = cur.next.next;
		}

		Node cloneHead = head.next;

		cur = head;
		while (cur != null) {
			Node cloneCur = cur.next;
			cur.next = cloneCur.next;
			if (cur.next != null) {
				cloneCur.next = cur.next.next;
			}
			cur = cur.next;
		}

		return cloneHead;
	}

	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}


	public int findDuplicate(int[] nums) {
		int start = 1;
		int end = nums.length - 1;
		while (start + 1 < end) {
			int midVal = (start + end) / 2;
			int lessNum = findLessThanMid(nums, midVal);
			if (lessNum <= midVal) {
				start = midVal;
			} else {
				end = midVal;
			}
		}

		if (findLessThanMid(nums, start) <= start) {
			return end;
		}
		return start;

	}

	private int findLessThanMid(int[] nums, int midVal) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= midVal) {
				count++;
			}
		}
		return count;

	}


	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return 1;
		}

		int min_depth = Integer.MAX_VALUE;
		if (root.left != null) {
			min_depth = Math.min(minDepth(root.left), min_depth);
		}
		if (root.right != null) {
			min_depth = Math.min(minDepth(root.right), min_depth);
		}

		return min_depth + 1;


	}

	public int rob(int[] nums) {
		int n = nums.length;
		int[] f = new int[n];
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return nums[0];
		}
		if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}
		f[1] = nums[0];
		//可拿1
		for (int i = 2; i <= n - 1; i++) {
			f[i] = Math.max(f[i - 1], f[i - 2] + nums[i]);
		}
		//不拿1
		int[] f2 = new int[n];
		for (int i = 2; i <= n - 1; i++) {
			f2[i] = Math.max(f[i - 1], f[i - 2] + nums[i]);
		}

		int res = Math.max(nums[n - 1] + f2[n - 2], f[n - 1]);

		return res;


	}


	public ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = null;
		ListNode p = dummy;
		ListNode q = head;

		while (q != null) {
			while (q != null && q.val == p.val) {
				q = q.next;
			}
			if (p.next != q) {
				prev.next = q;
				if (q != null) {
					p = prev.next;
					q = p.next;
				} else {
					p = null;
					q = null;
				}

			} else {
				prev = p;
				p = q;
				q = q.next;

			}
		}
		return dummy.next;

	}

	public boolean searchMatrix(int[][] matrix, int target) {

		int n = matrix.length;
		int m = matrix[0].length;

		int num = n * m - 1;

		int left = 0;
		int right = num;

		while (left <= right) {
			int mid = (left + right) / 2;
			int column = mid % m;
			int row = mid % m == 0 ? (mid / m) - 1 : mid / m;
			if (matrix[row][column] == target) {
				return true;
			} else if (matrix[row][column] > target) {
				right = mid;
			} else {
				left = mid;
			}
		}

		return false;


	}

	public int longestCommonSubsequence(String text1, String text2) {
		if (text1.length() == 0 || text2.length() == 0) {
			return 0;
		}

		if (text1.length() > text2.length()) {
			String temp = text1;
			text1 = text2;
			text2 = temp;
		}
		char[] cs1 = text1.toCharArray();
		char[] cs2 = text2.toCharArray();
		int n = cs1.length;
		int m = cs2.length;
		int[][] f = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (cs1[i - 1] == cs2[j - 1]) {
					f[i][j] = f[i - 1][j - 1] + 1;
				} else {
					f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);

				}
			}
		}
		return f[n][m];
	}


	public int longestConsecutive(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}

		int res = 0;
		int curNum;
		for (int num : nums) {
			int curRes = 0;
			if (set.contains(num - 1)) {
				continue;
			}
			curNum = num;
			while (set.contains(curNum)) {
				set.add(curNum);
				curRes++;
				curNum++;
			}
			res = Math.max(res, curRes);
		}
		return res;
	}


	public int calculate(String s) {
		s = s.replaceAll(" ", "");
		Stack<Integer> stack = new Stack<>();
		String prevSymbal = "+";
		int num = 0;
		for (int i = 0; i <= s.length(); i++) {
			if (i<s.length() &&Character.isDigit(s.charAt(i))) {
				num = num * 10 + (s.charAt(i) - '0');
			} else {
				if (prevSymbal .equals("+") ) {
					stack.push(num);
				} else if (prevSymbal .equals("-")) {
					stack.push(-num);
				} else if (prevSymbal .equals("*")) {
					stack.push(stack.pop() * num);
				} else if (prevSymbal .equals("/")) {
					stack.push(stack.pop() / num);
				}
				if (i < s.length()) {
					prevSymbal = s.charAt(i) + "";
				}
				num = 0;
			}

		}
		while(!stack.isEmpty()){
			num += stack.pop();
		}
		return num;
	}


	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<Status> queue = new PriorityQueue<>();
		for (ListNode list : lists) {
			queue.offer(new Status(list.val, list));
		}
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		while (!queue.isEmpty()) {
			Status curStatus = queue.poll();
			ListNode curNode = curStatus.listNode;
			current.next = curNode;
			if (curNode.next != null) {
				queue.offer(new Status(curNode.next.val, curNode.next));
			}
			curNode.next = null;
			current = curNode;
		}
		return dummy.next;

	}

	 static class Status implements Comparable<Status>{
		int val;
		 ListNode listNode;

		 public Status(int val, ListNode listNode) {
			 this.val = val;
			 this.listNode = listNode;
		 }

		 @Override
		 public int compareTo(Status o) {
			 return this.val - o.val;
		 }
	 }



	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		ListNode pA = headA, pB = headB;
		while (pA != pB) {
			pA = pA == null ? headB : pA.next;
			pB = pB == null ? headA : pB.next;
		}
		return pA;
	}





}
