package org.algorithm.leetcode.nomal.test.everyday;

import org.algorithm.leetcode.basic.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2020/11/16
 **/
public class EveryDay {
    public static void main(String[] args) {
        EveryDay e = new EveryDay();
        //[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]
//        int[][] p = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2},{3,0}};
//        e.reconstructQueue(p);
        System.out.println(e.fourSumCount(new int[]{-1,-1},new int[]{-1,1 },new int[]{-1,1},new int[]{1,-1}));

    }

    /**
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person1[0] - person2[0];
            } else {
                return person2[1] - person1[1];
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] p : people) {
            int number = p[1] + 1;
            for (int i = 0; i < n; i++) {
                if (ans[i] == null) {
                    number--;
                    if (number == 0) {
                        ans[i] = p;
                        break;
                    }
                }
            }
        }
        return ans;
    }
    //222. 完全二叉树的节点个数

    /**
     * 完全二叉树，要么左边是满的，要么右边是满的  (2的n次方-1，n为高度)
     */
    public int countNodes(TreeNode root) {
        //计算树的高度，
        int height = treeHeight(root);
        //如果树是空的，或者高度是1，直接返回
        if (height == 0 || height == 1) {
            return height;
        }
        //如果右子树的高度是树的高度减1，说明左子树是满二叉树，
        //左子树可以通过公式计算，只需要递归右子树就行了
        if (treeHeight(root.right) == height - 1) {
            //注意这里的计算，左子树的数量是实际上是(1 << (height - 1))-1，
            //不要把根节点给忘了，在加上1就是(1 << (height - 1))
            return (1 << (height - 1)) + countNodes(root.right);
        } else {
            //如果右子树的高度不是树的高度减1，说明右子树是满二叉树，可以通过
            //公式计算右子树，只需要递归左子树就行了
            return (1 << (height - 2)) + countNodes(root.left);
        }
    }

    //计算树的高度
    private int treeHeight(TreeNode root) {
        return root == null ? 0 : 1 + treeHeight(root.left);
    }

    /**
     * 1370. 上升下降字符串
     */
    public String sortString(String s) {
        int[] bucket = new int[26];
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            bucket[c - 'a']++;
        }
        while (sb.length() != s.length()) {
            //1 2 3轮
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != 0) {
                    sb.append((char) (i + 'a'));
                    bucket[i]--;
                }
            }
            //456轮
            for (int i = bucket.length - 1; i >= 0; i--) {
                if (bucket[i] != 0) {
                    sb.append((char) (i + 'a'));
                    bucket[i]--;
                }
            }

        }
        return sb.toString();
    }

    /**
     *
     * 454. 四数相加 II
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //A+B的和《 == 》 次数
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                if (map1.containsKey(sum)) {
                    map1.put(sum, map1.get(sum) + 1);
                }else {
                    map1.put(sum, 1);
                }
            }
        }
        int result = 0;
        for (int c : C) {
            for (int d : D) {
                int sum = c + d;
                if (map1.containsKey(-sum)) {
                    result += (map1.get(-sum));
                }
            }
        }

        return result;

    }


}
