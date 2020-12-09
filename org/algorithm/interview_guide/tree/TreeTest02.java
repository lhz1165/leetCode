package org.algorithm.interview_guide.tree;

import java.util.LinkedList;

/**
 * @author lhzlhz
 * @create 2020/12/7
 */
public class TreeTest02 {
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n4.left = n2;
		n4.right = n5;
		n2.left = n1;
		n2.right = n3;
		TreeTest02 t = new TreeTest02();
		t.treeToDoublyList2(n4);

	}


	public Node treeToDoublyList(Node root) {
		if(root == null){
			return root;
		}
		LinkedList<Node> nodes = inOrderAdd(root);
		Node head = nodes.poll();
		Node cur = null;
		Node pre = head;
		pre.left = null;
		while(!nodes.isEmpty()){
			cur = nodes.poll();
			pre.right = cur ;
			cur.left = pre;
			pre = cur;
		}
		cur.right = null;
		return head;

	}
	public Node treeToDoublyList2(Node root) {
		if (root == null) {
			return null;
		}
		Node head = null, pre = null, cur = root;
		LinkedList<Node> stack = new LinkedList<>();
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			if (pre == null) {
				head = cur;
			} else {
				pre.right = cur;
				cur.left = pre;
			}
			pre = cur;
			cur = cur.right;
		}
		head.left = pre;
		pre.right = head;
		return head;
	}

	public LinkedList<Node> inOrderAdd(Node root){
		LinkedList<Node> nodes = new LinkedList<>();
		if(root == null){
			return nodes;
		}
		//divided
		LinkedList<Node> leftNodes = inOrderAdd(root.left);
		LinkedList<Node> rightNodes = inOrderAdd(root.right);

		//conquer
		nodes.addAll(leftNodes);
		nodes.offer(root);
		nodes.addAll(rightNodes);
		return nodes;
	}
}
class Node {
	public int val;
	public Node left;
	public Node right;

	public Node() {}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val,Node _left,Node _right) {
		val = _val;
		left = _left;
		right = _right;
	}
};
