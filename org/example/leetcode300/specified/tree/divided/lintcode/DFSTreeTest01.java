package org.example.leetcode300.specified.tree.divided.lintcode;


import org.example.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/8
 **/
public class DFSTreeTest01 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root4 = new TreeNode(3);
        TreeNode root3 = new TreeNode(4);
        TreeNode root6 = new TreeNode(5);
        root.right = root1;
        root1.right = root4;
        root4.right = root3;
        root3.right = root6;

 //       root.right = root4;
//        root4.left = root3;
//        root4.right = root6;
        DFSTreeTest01 d = new DFSTreeTest01();
        System.out.println(d.isValidBST(root));
        DoublyListNode node = d.bstToDoublyList(root);
        System.out.println(node);
    }
    /**
     * 二叉树的最大深度
     *
     * @param root
     * @return
     */
    int depth = 0;//全局遍历记录最大深度

    public int maxDepth1(TreeNode root) {
        helper(root, 1);
        return depth;
    }

    public void helper(TreeNode node, int currentDepth) {
        if (node == null) {
            return;
        }
        depth = Math.max(depth, currentDepth);
        helper(node.left, currentDepth + 1);
        helper(node.right, currentDepth + 1);

    }

    /**
     * 分治法解决二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //分
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        //治
        return Math.max(left, right) + 1;
    }

    /**
     * 257. 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            result.add("" + root.val);
        }
        //递归拆解
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for (String leftPath : leftPaths) {
            result.add(root.val + "->" + leftPath);
        }
        for (String rightPath : rightPaths) {
            result.add(root.val + "->" + rightPath);
        }

        return result;

    }

    /**
     * 是否平衡二叉树 左右子树高度差不超过1
     *
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public int NOT_BALANCE = -1;

    public boolean isBalanced(TreeNode root) {
        // write your code here

        return helper(root) != NOT_BALANCE;

    }

    public int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //两棵树子树高度
        int leftVal = helper(node.left);
        int rightVal = helper(node.right);
        if (leftVal == NOT_BALANCE || rightVal == NOT_BALANCE || Math.abs(leftVal - rightVal) > 1) {
            return NOT_BALANCE;
        }
        return Math.max(leftVal, rightVal) + 1;
    }

    /**
     * 求二叉树的最小子树和
     */
    private TreeNode subTreeNode = null;
    private int subSum = Integer.MAX_VALUE;

    public TreeNode minimumSubTree(TreeNode root) {

        return subTreeNode;
    }

    public int helperMinSubTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSub = helper(root.left);
        int rightSub = helper(root.right);
        int val = leftSub + rightSub + root.val;
        subSum = Math.min(subSum, val);
        subTreeNode = subSum == val ? root : subTreeNode;
        return subSum;
    }

    /**
     * 子树的最大平均值
     *
     * @param root
     * @return
     */
    public ResultType subTreeResult = null;
    public TreeNode result = null;
    public TreeNode maximumAverageSubtree(TreeNode root) {
        helperMaxAvgSum(root);
        return result;
    }

    public ResultType helperMaxAvgSum(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType leftType = helperMaxAvgSum(root.left);
        ResultType rightType = helperMaxAvgSum(root.right);
        int curAvgSum = root.val + leftType.sum  + rightType.sum;
        int sizeSum = leftType.size + rightType.size + 1;
        ResultType curType = new ResultType(curAvgSum, sizeSum);
        if (result == null || subTreeResult.sum / subTreeResult.size < curAvgSum / sizeSum) {
            result = root;
            subTreeResult = curType;
        }
        return curType;
    }

    /**
     *
     * 236. 二叉树的最近公共祖先
     *
     * 如果p和q都在root下面 return root
     * 如果只有 p在root下面 return p
     * 如果只有 q在root下面 return q
     * 都不在return null
     * @param  root
     * @param  p
     * @param  q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {


        return null;
    }

    /**
     * 验证二叉搜索树
     * 使用divided conquer解决
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root){
        return helperValidBST(root).isBST;
    }

    public ReusltType2 helperValidBST(TreeNode root) {
        if (root == null) {
            return new ReusltType2(true,Integer.MIN_VALUE,Integer.MAX_VALUE);
        }
        ReusltType2 leftType = helperValidBST(root.left);
        ReusltType2 rightType = helperValidBST(root.right);

        if (!leftType.isBST || !rightType.isBST) {
            return new ReusltType2(false, 0, 0);
        }

        if ((root.left != null && leftType.maxVal > root.val) || (root.right != null && rightType.minVal < root.val)) {
            return new ReusltType2(false, 0, 0);
        }
        return new ReusltType2(true, Math.max(root.val, rightType.maxVal), Math.min(root.val, leftType.minVal));
    }



    /**
     *
     * 把二叉树转化成双向链表
     * @param root: The root of tree
     * @return: the head of doubly list node
     */

    public DoublyListNode bstToDoublyList(TreeNode root) {
        // write your code here
        return helperToDoublyList(root).head;
    }

    public ResultType3 helperToDoublyList(TreeNode root) {
         if (root == null) {
            return new ResultType3(null,null);
        }
        ResultType3 leftType = helperToDoublyList(root.left);
        ResultType3 rightType = helperToDoublyList(root.right);
        DoublyListNode rootNode = new DoublyListNode(root.val);
         DoublyListNode head = null;
         DoublyListNode tail = null;
        if (leftType.tail != null) {
            rootNode.prev = leftType.tail;
            leftType.tail.next = rootNode;
            head = leftType.head;
        }

        if (rightType.head != null) {
            rightType.head.prev = rootNode;
            rootNode.next = rightType.head;
            tail = rightType.tail;
        }
        /**
         * 设置当前的head 和tail
         */
        if(rightType.head == null &&leftType.tail == null){
            head = rootNode;
            tail = rootNode;
        } else if (leftType.head == null&&rightType.tail != null){
            head = rootNode;
        }else if(leftType.head != null&&rightType.tail == null){
            tail = rootNode;
        }
        return new ResultType3(head, tail);

    }
}
class ReusltType2{
    public boolean isBST;
    public int maxVal;
    public int minVal;

    public ReusltType2(boolean isBST, int maxVal, int minVal) {
        this.isBST = isBST;
        this.maxVal = maxVal;
        this.minVal = minVal;
    }
}
/**
 * 记录子树的和，以及节点个数，用来计算子树的平均值
 */
class ResultType {
    public int size;
    public int sum;

    public ResultType(int size, int sum) {
        this.size = size;
        this.sum = sum;
    }
}
class DoublyListNode {
    int val;
    DoublyListNode next, prev;

    DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }
}
class ResultType3 {
    public DoublyListNode head;
    public DoublyListNode tail;
    public ResultType3(DoublyListNode head, DoublyListNode tail) {
        this.head = head;
        this.tail = tail;
    }
}
