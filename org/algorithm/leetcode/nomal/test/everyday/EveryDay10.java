package org.algorithm.leetcode.nomal.test.everyday;

import org.algorithm.leetcode.basic.TreeNode;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class EveryDay10 {
    private TreeNode resNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        resNode = node;

        inorder(node.right);
    }

//    1011. 在 D 天内送达包裹的能力
//    传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
//
//    传送带上的第 i 个包裹的重量为 weights[i]。
//    每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
//
//    返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
//    示例 1：
//    输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//    输出：15
//    解释：
//    船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//    第 1 天：1, 2, 3, 4, 5
//    第 2 天：6, 7
//    第 3 天：8
//    第 4 天：9
//    第 5 天：10
//
//    请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
//
//    public int shipWithinDays(int[] weights, int D) {
//
//
//    }

    /**
     * 403. 青蛙过河
     * 输入：stones = [0,1,3,5,6,8,12,17]
     * 输出：true
     * <p>
     * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
     * <p>
     * 如果青蛙上一步跳跃了k个单位，那么它接下来的跳跃距离只能选择为k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
     * <p>
     * 解释：青蛙可以成功过河，按照如下方案跳跃：
     * 跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
     */
    public static void main(String[] args) {
        EveryDay10 e = new EveryDay10();
        boolean b = e.canCross2(new int[]{0,1,3,5,6,8,12,17});
        System.out.println(b);
    }


    public boolean canCross2(int[] stones) {
        int n = stones.length;
        //我们也可以使用动态规划的方法，令f[i][k]
        // use k step can reach i
        boolean[][] f = new boolean[n][n];
        f[0][0] = true;
        for (int i = 1; i < n; i++) {
            f[i][0] = false;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int k = stones[i] - stones[j];

                if (k == 0 ) {
                    f[i][k] = f[j][k] || f[j][k + 1];
                } else if (k == n - 1) {
                    f[i][k] = f[j][k - 1] || f[j][k];
                } else if (k > 0 && k < n - 1) {
                    f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (f[n - 1][i]) {
                return true;
            }
        }
        return false;


    }

}
