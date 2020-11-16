package org.algorithm.interview_guide.tree;

import org.algorithm.leetcode300.basic.TreeNode;

/**
 * @author lhzlhz
 * @create 2020/11/16
 */
public class TreeTest01 {
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);
		TreeNode n10 = new TreeNode(10);
		TreeNode n11= new TreeNode(11);
		TreeNode n12 = new TreeNode(12);
		TreeNode n13 = new TreeNode(13);
		TreeNode n14 = new TreeNode(14);
		TreeNode n15 = new TreeNode(15);
		TreeNode n16= new TreeNode(16);
		n1.left = n2;
		n1.right = n3;
		n2.right = n4;
		n3.left = n5;
		n3.right = n6;
		n4.left = n7;
		n4.right = n8;
		n5.left = n9;
		n5.right = n10;
		n8.right = n11;
		n9.left = n12;
		n11.left = n13;
		n11.right = n14;
		n12.left = n15;
		n12.right = n16;
		TreeTest01 t =new TreeTest01();
		t.printEdge1(n1);
		System.out.println();


	}

	/**
	 * 边界节点
	 * 1打印每一层的最左最右和所有的叶子节点
	 */
	public void printEdge1(TreeNode head) {
		int height = getHeight(head, 0);
		//每层的最左和最有两个点
		TreeNode[][] edgeMap = new TreeNode[height][2];
		setEdgeMap(head, 0, edgeMap);
	}


	public int getHeight(TreeNode head, int l) {
		if (head == null) {
			return l;
		}
		return Math.max(getHeight(head.left, l + 1), getHeight(head.right, l + 1));
	}

	/**
	 * 找出每一层最左和最右的两个点
	 * @param h
	 * @param l
	 * @param edgeMap
	 */
	public void setEdgeMap(TreeNode h, int l, TreeNode[][] edgeMap) {
		if (h == null) {
			return;
		}
		edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
		edgeMap[l][1] = h;
		setEdgeMap(h.left, l + 1, edgeMap);
		setEdgeMap(h.right, l + 1, edgeMap);
	}

}
