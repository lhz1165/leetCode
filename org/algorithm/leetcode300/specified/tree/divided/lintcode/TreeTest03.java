package org.algorithm.leetcode300.specified.tree.divided.lintcode;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.algorithm.leetcode300.basic.TreeNode;

import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/11/24
 **/
public class TreeTest03 {
    /**
     * 1372. 二叉树中的最长交错路径
     */
    int res;
    /**
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     */
    int K = 0;

    public static void main(String[] args) {
        TreeTest03 t = new TreeTest03();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(2);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        n1.left = n2;
        n1.right = n3;
//
//
//
//        n3.left = n4;
//        n3.right = n5;

        t.lowestCommonAncestor(n1,n4,n3);
        //System.out.println(isSymmetric);

    }

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        res = 0;
        //traverse(root, false, 0);
        traverse(root, true, 0);
        return res;
    }

    /**
     * // 返回两个值
     */
    private void traverse(TreeNode root, boolean dir, int len) {
        //应该向左走
        res = Math.max(len, res);
        if (!dir) {
            if (root.left != null) {
                traverse(root.left, true, ++len);
            }

            if (root.right != null) {
                traverse(root.right, false, 1);
            }

        } else {
            //应该向右走
            if (root.right != null) {
                traverse(root.right, false, ++len);
            }

            if (root.left != null) {
                traverse(root.left, true, 1);
            }
        }
    }

    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        invertTreeHelper(root);
        return root;
    }

    private void invertTreeHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        invertTreeHelper(root.left);
        invertTreeHelper(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
     }

    /**
     * 617. 合并二叉树
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    public int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        Stack<Integer> list = new Stack<>();
        kthLargestHelper(root, k, list);
        return list.pop();

    }

    private void kthLargestHelper(TreeNode root, int k, Stack<Integer> list) {
        if (k == list.size()) {
            return;
        }
        if (root.right != null) {
            kthLargestHelper(root.right, k, list);
        }
        if (k == list.size()) {
            return;
        }
        list.push(root.val);
        if (k == list.size()) {
            return;
        }
        if (root.left != null) {
            kthLargestHelper(root.left, k, list);
        }
    }


    /**
     * 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
    /**
     * 二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorII(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorII(root.right, p, q);
        }
        return root;
    }

    /**
     * 对称的二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isLeftAndRightSymmetric(root.left,root.right);

    }

    private boolean isLeftAndRightSymmetric(TreeNode left,TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        boolean lr = isLeftAndRightSymmetric(left.left, right.right);
        boolean rl = isLeftAndRightSymmetric(left.right, right.left);
        return left.val == right.val&&lr && rl ;

    }

    /**
     *是否平衡二叉树
     *
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSearch(root,Integer.MIN_VALUE,Integer.MAX_VALUE) && isHeight(root);
    }

    /**
     * 是否二叉搜索树
     * @param root
     * @param lower
     * @param upper
     * @return
     */
    public boolean isSearch(TreeNode root,int lower,int upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        return isSearch(root.left, lower, root.val) && isSearch(root.right, root.val, upper);

    }

    /**
     * 高度差是否不为2
     * @param root
     * @return
     */
    public boolean isHeight(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(height(root.left) - height(root.right)) >= 2) {
            return false;
        }
        return isHeight(root.left) && isHeight(root.right);

    }

    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 二叉树直径
     */
    int result = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helperDiameterOfBinaryTree(root)-1;
    }

    private int helperDiameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        result = height(root.left) + height(root.right) + 1;
        result = Math.max(result, helperDiameterOfBinaryTree(root.left));
        result = Math.max(result, helperDiameterOfBinaryTree(root.right));
        return result;
    }


    /**
     * 修剪二叉搜索树
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;

    }




}
