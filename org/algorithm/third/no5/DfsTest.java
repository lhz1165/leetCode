package org.algorithm.third.no5;

import org.algorithm.leetcode.basic.TreeNode;

/**
 * @author lhzlhz
 * @create 28/9/2021
 */
public class DfsTest {

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		DfsTest d = new DfsTest();
		System.out.println(d.diameterOfBinaryTree(n1));

	}

	/**
	 * 二叉树的直径
	 * @param root
	 * @return
	 */
	public int diameterOfBinaryTree(TreeNode root) {
		// write your code here
		if (root == null) {
			return 0;
		}
		return 0;
	}
	public int dfs (TreeNode root) {
		if (root == null) {
			return 0;
		}
		int ld = dfs( root.left);
		int rd = dfs(root.right);
		return Math.max(ld, rd) + 1;
	}
}
