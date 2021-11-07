package org.prepare;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lhzlhz
 * @create 7/11/2021
 */
public class BFS {
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n3.left = n5;
		n4.left = n6;
		BFS b = new BFS();
		String data = b.serialize(n1);
		System.out.println(data);
		TreeNode node = b.deserialize(data);
		System.out.println();
	}

	public String serialize(TreeNode root) {
		if (root == null) {
			return "{}";
		}
		List<TreeNode> nodes = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		nodes.add(root);
		while (!queue.isEmpty()) {
			TreeNode now = queue.poll();
			if (now.left != null) {
				queue.offer(now.left);
			}
			if (now.right != null) {
				queue.offer(now.right);
			}
			nodes.add(now.left);
			nodes.add(now.right);
		}
		while (nodes.get(nodes.size() - 1) == null) {
			nodes.remove(nodes.size() - 1);
		}
		StringBuilder result = new StringBuilder();
		result.append("{");
		for (int i = 0; i < nodes.size(); i++) {
			if (i > 0) {
				result.append(",");
			}
			if (nodes.get(i) == null) {
				result.append("#");
			}
			else {
				result.append(Integer.toString(nodes.get(i).val));
			}
		}
		result.append("}");
		return result.toString();
	}
	public TreeNode deserialize(String data) {
		if (data.equals("{}")) {
			return null;
		}
		String[] nodes = data.substring(1,data.length()-1).split(",");
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode root = getNode(nodes[0]);
		queue.add(root);
		int count = 1;
		while (!queue.isEmpty()) {
			if (count >= nodes.length) {
				break;
			}
			TreeNode now = queue.poll();
			if (now == null) {
				continue;
			}
			TreeNode left = getNode(nodes[count]);
			count++;
			now.left = left;
			queue.offer(left);
			if (count >= nodes.length) {
				break;
			}

			TreeNode right = getNode(nodes[count]);
			count++;
			now.right = right;
			queue.offer(right);
		}
		return root;
	}

	public TreeNode getNode(String str) {
		if (str.equals("#")) {
			return null;
		}
		return new TreeNode(Integer.parseInt(str));
	}

  public static class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = this.right = null;
      }
  }
}
