package org.algorithm.leetcode300.specified.dfs;

import org.algorithm.leetcode300.basic.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/23
 **/
public class DfsTest01 {
    public static void main(String[] args) {
        DfsTest01 d = new DfsTest01();
        int[] candidates = {1, 1, 2};
////        System.out.println(combinationSum(candidates, 8));
////
////        System.out.println(partition("abac"));
//
//
//        System.out.println(permuteUnique(candidates));
//
//
//
//        System.out.println(solveNQueens(4));
        TreeNode root = new TreeNode(5);
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r5 = new TreeNode(6);
        TreeNode rr = new TreeNode(4);
        root.right = r5;
        root.left = r2;
        r2.left = r1;
        r2.right = r3;
        r3.right=rr;
        d.inorderSuccessor(root,new TreeNode(3));



    }

    /**
     * 给定一个候选数字的集合 candidates 和一个目标值 target. 找到 candidates 中所有的和为 target 的组合.在同一个组合中, candidates 中的某个数字限一次数地出现.
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> subset = new ArrayList<>();
        subSetHelper2(candidates, 0, target, subset, result);
        return result;
    }

    /**
     * 每个数字可以使用多次
     *
     * @param candidates
     * @param index
     * @param remainTarget
     * @param subset
     * @param result
     */
    private static void subSetHelper(int[] candidates, int index, int remainTarget, List<Integer> subset, List<List<Integer>> result) {
        if (0 == remainTarget) {
            result.add(new ArrayList<>(subset));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (remainTarget < candidates[i]) {
                break;
            }
            if (i != 0 && candidates[i] == candidates[i - 1] && index < i) {
                continue;
            }
            subset.add(candidates[i]);
            subSetHelper(candidates, i, remainTarget - candidates[i], subset, result);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * 每个数字只能使用一次
     */
    private static void subSetHelper2(int[] candidates, int index, int remainTarget, List<Integer> subset, List<List<Integer>> result) {
        if (remainTarget == 0) {
            result.add(new ArrayList<>(subset));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (remainTarget < candidates[i]) {
                break;
            }
            if (i != 0 && candidates[i] == candidates[i - 1] && index != i) {
                continue;
            }
            subset.add(candidates[i]);
            subSetHelper2(candidates, i + 1, remainTarget - candidates[i], subset, result);
            subset.remove(subset.size() - 1);
        }

    }

    /**
     * 136. 分割回文串
     * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.返回所有可能的分割方案.
     *
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (null == s) {
            return result;
        }
        List<String> subSet = new ArrayList<>();
        if (s.length() < 2) {
            subSet.add(s);
            result.add(subSet);
            return result;
        }
        partitionHelper(s, 0, subSet, result);
        return result;

    }

    public static void partitionHelper(String s, int index, List<String> subSet, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(subSet));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String sb = s.substring(index, i + 1);
            if (isHuiWen(sb)) {
                subSet.add(sb);
                partitionHelper(s, i + 1, subSet, result);
                subSet.remove(subSet.size() - 1);
            }

        }

    }

    public static boolean isHuiWen(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 1) {
            return true;
        }
        int r = 0;
        int l = chars.length - 1;
        while (r < l) {
            if (chars[r] != chars[l]) {
                return false;
            }
            r++;
            l--;
        }
        return true;
    }

    /**
     * 15. 全排列
     * 给定一个数字列表，返回其所有可能的排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subSet = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            result.add(subSet);
            return result;
        }
        permuteHelper(nums, subSet, result);
        return result;
    }

    private static void permuteHelper(int[] nums, List<Integer> subSet, List<List<Integer>> result) {
        if (subSet.size() == nums.length) {
            result.add(new ArrayList<>(subSet));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!subSet.contains(nums[i])) {
                subSet.add(nums[i]);
                permuteHelper(nums, subSet, result);
                subSet.remove(subSet.size() - 1);
            }
        }

    }

    /**
     * 16. 带重复元素的排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subSet = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(subSet);
            return result;
        }
        boolean[] visited = new boolean[nums.length];
        permuteHelper2(nums, visited, subSet, result);
        return result;

    }

    private static void permuteHelper2(int[] nums, boolean[] visited, List<Integer> subSet, List<List<Integer>> result) {
        if (subSet.size() == nums.length) {
            result.add(new ArrayList<>(subSet));
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //不等于前一个，并且不能早于前一个出现
            if (i != 0 && nums[i] == nums[i - 1]&&!visited[i - 1]) {
                continue;
            }

            subSet.add(nums[i]);
            visited[i] = true;
            permuteHelper2(nums, visited, subSet, result);
            subSet.remove(subSet.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * n皇后
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> result = new ArrayList<>();

        if (n < 0) {
            return result;
        }
        //集合下标i表示第几排，get（i）表示第几行
        List<List<Integer>> resultAbs = new ArrayList<>();
        search(resultAbs,new ArrayList<Integer>(),n);
        return draw(resultAbs);
    }

    private static void search(List<List<Integer>> resultAbs,
                        List<Integer> clos,
                        int n) {
        if (clos.size() == n) {
            resultAbs.add(new ArrayList<>(clos));
            return;
        }
        //接下来的棋子 放在哪一列（colIndex）合适
        for (int colIndex = 0; colIndex < n; colIndex++) {
            if (!isValid(clos, colIndex)) {
                continue;
            }
            clos.add(colIndex);
            search(resultAbs,clos,n);
            clos.remove(clos.size() - 1);
        }
    }

    /**
     * 这颗棋子放在在哪一列
     * @param cols 已知棋子位置
     * @param col 放置棋子位置列
     * 下标i表示第几列，get（i）表示第几行
     * @return
     */
    public static boolean isValid(List<Integer> cols, int col) {
        //已经放了几行了
        int row = cols.size();
        //和每一行作比较 判断是否同列或者斜着
        for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
            //若有其他皇后在同一列或同一斜线上则不合法
            if (cols.get(rowIndex) == col) {
                return false;
            }
            //斜率是否相同 y = -x+k
            if (row + col == rowIndex + cols.get(rowIndex)) {
                return false;
            }
            //斜率是否相同 y = x+k
            if (row - col == rowIndex - cols.get(rowIndex)) {
                return false;
            }
        }
        return true;
    }
    public static List<List<String>> draw(List<List<Integer>> resultAbs) {
        List<List<String>> results = new ArrayList<>();
        for (List<Integer> resultAb : resultAbs) {
            List<String> result = new ArrayList<>();
            for (Integer res : resultAb) {
                String s = construct(res, resultAb.size());
                result.add(s);
            }
            results.add(result);
        }
        return results;
    }

    public static String construct(int res,int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <n ; i++) {
            if (i == res) {
                sb.append("Q");
            }else {
                sb.append(".");
            }
        }
        return sb.toString();
    }


    /**
     * 给定二叉搜索树(BST)，将其转换为更大的树，使原始BST上每个节点的值都更改为在原始树中大于等于该节点值的节点值之和
     * 输入 : {5,2,13}
     * 5
     * /   \
     * 2     13
     * 输出 : {18,20,13}
     * 18
     * /   \
     * 20     13
     * <p>
     * 以右中左的方式遍历----》以sum记录遍历的和 然后加起来
     *
     * @param root
     * @return
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        // write your code here
        dfs(root);
        return root;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        //右
        dfs(node.right);
        //中
        sum += node.val;
        node.val = sum;
        //左
        dfs(node.left);
    }

    /**
     * 给定一个二叉查找树(什么是二叉查找树)，以及一个节点，求该节点在中序遍历的后继，如果没有则返回null
     *
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }


}
