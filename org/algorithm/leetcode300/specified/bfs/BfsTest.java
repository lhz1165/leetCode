package org.algorithm.leetcode300.specified.bfs;


import org.algorithm.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: lhz
 * @date: 2020/7/2
 **/
public class BfsTest {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode n1 = new TreeNode(9);
		TreeNode n2 = new TreeNode(20);
		TreeNode n3 = new TreeNode(15);
		//TreeNode n4 = new TreeNode(7);
		root.left = n1;
		root.right = n2;
		n2.right = n3;
		//n2.left = n4;
		System.out.println(serialize(root));
		TreeNode deserialize = deserialize("{3,9,20,#,#,7,15}");


	}


	/**
	 * 层次遍历二叉树
	 *
	 * @param root: A Tree
	 * @return: Level order a list of lists of integer
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		// write your code here
		List<List<Integer>> result = new ArrayList<>();
		//1定义一个队列，把每一层的节点放进去
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		if (null == root) {
			return result;
		}
		//2遍历每层，处理节点，并拓展新节点
		while (!queue.isEmpty()) {
			//3遍历每一层的每个数据
			int size = queue.size();
			//4创建集合 用来保存每一层的结果
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				level.add(node.val);
			}
			result.add(level);
		}
		return result;
	}

	/**
	 * 序列化
	 *
	 * @param root
	 * @return
	 */
	public static String serialize(TreeNode root) {
		// write your code here
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		//1定义一个队列，把每一层的节点放进去
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		if (null == root) {
			return "{}";
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			StringBuilder subSb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				subSb.append(node.val == -1 ? "#," : node.val + ",");
				if (node.val != -1) {
					if (node.left != null) {
						queue.offer(node.left);
					} else {
						queue.offer(new TreeNode(-1));
					}
					if (node.right != null) {
						queue.offer(node.right);
					} else {
						queue.offer(new TreeNode(-1));
					}
				}
			}
			boolean last = true;
			char[] chars = subSb.toString().toCharArray();
			for (char aChar : chars) {
				if (aChar != '#' && aChar != ',') {
					last = false;
					break;
				}
			}
			if (!last) {
				sb.append(subSb);
			}

		}
		String res = sb.substring(0, sb.length() - 1);
		res += "}";

		return res;

	}

	/**
	 * 反序列化
	 * index指向父节点，node表示左还是右孩子
	 *
	 * @param data
	 * @return
	 */
	public static TreeNode deserialize(String data) {
		// write your code here
		if (data.equals("{}")) {
			return null;
		}
		String[] vals = data.substring(1, data.length() - 1).split(",");
		ArrayList<TreeNode> queue = new ArrayList<>();
		TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
		queue.add(root);
		int index = 0;
		boolean isLeftChild = true;
		for (int i = 1; i < vals.length; i++) {
			if (!vals[i].equals("#")) {
				TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
				if (isLeftChild) {
					queue.get(index).left = node;
				} else {
					queue.get(index).right = node;
				}
				queue.add(node);
			}
			if (!isLeftChild) {
				index++;
			}
			isLeftChild = !isLeftChild;
		}
		return root;
	}

	public static TreeNode deserialize2(String data) {
		// write your code here
		if (data.equals("{}")) {
			return null;
		}
		String[] vals = data.substring(1, data.length() - 1).split(",");
		ArrayList<TreeNode> queue = new ArrayList<>();
		TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
		queue.add(root);
		int index = 0;
		boolean isLeftChild = true;
		for (int i = 1; i < vals.length; i++) {
			if (!vals[i].equals("#")) {
				TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
				if (isLeftChild) {
					queue.get(index).left = node;
				} else {
					queue.get(index).right = node;
				}
				queue.add(node);
			}
			if (!isLeftChild) {
				index++;
			}
			isLeftChild = !isLeftChild;
		}
		return root;
	}


}
