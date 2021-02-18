package org.algorithm.leetcode.specified.tree.divided.lintcode;

import org.algorithm.leetcode.basic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lhz
 * @date: 2020/11/24
 **/
public class Codec {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        Codec c = new Codec();
        String s = c.serialize(n1);
        System.out.println(s);
        TreeNode deserialize = c.deserialize(s);
        System.out.println();

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return "";
        }
        stack.offer(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.poll();

            if (cur.val == -1) {
                sb.append("#")
                        .append(",");
                continue;
            } else {
                sb.append(cur.val)
                        .append(",");
            }

            if (cur.left != null) {
                stack.offer(cur.left);
            } else {
                stack.offer(new TreeNode(-1));
            }

            if (cur.right != null) {
                stack.offer(cur.right);
            } else {
                stack.offer(new TreeNode(-1));
            }

        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        if (nodes.length == 1 && nodes[0].equals("")) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.offer(root);
        for (int i = 1; i < nodes.length; i++) {
            //队列中节点出栈
            TreeNode parent = queue.poll();
            //因为在BFS中左右子节点是成对出现的，所以这里挨着的两个值一个是
            //左子节点的值一个是右子节点的值，当前值如果是"#"就表示这个子节点
            //是空的，如果不是"#"就表示不是空的
            if (!"#".equals(nodes[i])) {
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                parent.left = left;
                queue.add(left);
            }
            //上面如果不为空就是左子节点的值，这里是右子节点的值，注意这里有个++i，
            if (!"#".equals(nodes[++i])) {
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));
                parent.right = right;
                queue.add(right);
            }
        }
        return root;
    }
}
