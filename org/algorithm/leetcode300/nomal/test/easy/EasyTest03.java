package org.algorithm.leetcode300.nomal.test.easy;

import org.algorithm.leetcode300.basic.TreeNode;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/12/8
 **/
public class EasyTest03 {
    public static void main(String[] args) {
        EasyTest03 e = new EasyTest03();
        TreeNode n1 = new TreeNode(2147483647);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        //n1.right = n2;
        //n2.left = n3;
        System.out.println(e.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }


    /**
     * 653. 两数之和 IV - 输入 BST
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findAnother(root, k, set);
    }

    public boolean findAnother(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        int remain = k - root.val;
        if (set.contains(remain)) {
            return true;
        } else {
            set.add(root.val);
            return findAnother(root.left, k, set) || findAnother(root.right, k, set);
        }
    }

    /**
     * "BGBG"
     * "RGBR"
     */
    public int[] masterMind(String solution, String guess) {
        int bingo = 0;
        int fakeBingo = 0;
        char[] cs = solution.toCharArray();
        char[] cg = guess.toCharArray();
        Map<Character, Integer> ms = new HashMap<>();
        for (char c : cs) {
            if (ms.containsKey(c)) {
                ms.put(c, ms.get(c) + 1);
            } else {
                ms.put(c, 1);
            }
        }

        Map<Character, Integer> mg = new HashMap<>();
        for (char c : cg) {
            if (mg.containsKey(c)) {
                mg.put(c, mg.get(c) + 1);
            } else {
                mg.put(c, 1);
            }
        }

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == cg[i]) {
                mg.put(cs[i], mg.get(cs[i]) - 1);
                ms.put(cs[i], ms.get(cs[i]) - 1);
                bingo++;
            }
        }
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] != cg[i] && mg.get(cs[i]) != null && mg.get(cs[i]) != 0) {
                mg.put(cs[i], mg.get(cs[i]) - 1);
                ms.put(cs[i], ms.get(cs[i]) - 1);
                fakeBingo++;
            }
        }
        return new int[]{bingo, fakeBingo};

    }

    public int[] masterMind2(String solution, String guess) {
        int fake = 0, real = 0;
        int[] map = new int[26];
        for (int i = 0; i < 4; i++) {
            char sol = solution.charAt(i), gue = guess.charAt(i);

            if (sol == gue) {
                real++;
            } else {
                if (map[sol - 'A'] < 0) {
                    fake++;
                    map[sol - 'A']++;
                }

                if (map[gue - 'A'] > 0) {
                    fake++;
                    map[gue - 'A']--;
                }

            }
        }
        int[] ans = {real, fake};
        return ans;
    }


    /**
     * 404. 左叶子之和
     * ***********
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int ans = 0;
        //左
        if (node.left != null) {
            if (isLeafNode(node.left)) {
                ans += node.left.val;
            } else {
                ans += dfs(node.left);
            }
        }
        //右
        if (node.right != null && !isLeafNode(node.right)) {
            ans += dfs(node.right);
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }


    /**
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        // write your code here
        int start = 0;
        int end = numbers.length - 1;
        // find the first element <= target
        while (start + 1 < end) {			//用来控制区间大小
            int mid = start + (end - start) / 2;
            if (numbers[mid] < numbers[end]) { 		//如果mid位置上的数字小于等于最右端的数字时，区间向左移动
                end = mid;
            } else if(numbers[mid] > numbers[end]) {
                start = mid;
            }else {
                end--;
            }
        }
        return Math.min(numbers[start],numbers[end]);
    }
    /**
     * 594. 最长和谐子序列
     */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>(Integer::compareTo);
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        if (map.size() == 1) {
            return 0;
        }
        int index = 0;
        int prev = -1;
        int prevVal = 0;
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (index != 0) {
                if (entry.getKey() - 1 == prev) {
                    result = Math.max(result, entry.getValue()+prevVal);
                }
            }
            index++;
            prev = entry.getKey();
            prevVal = entry.getValue();
        }
        return result;
    }
    /**
     *
     * 448. 找到所有数组中消失的数字
     * ******
     * 原地修改 不适用额外的空间
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i])-1] > 0) {
                nums[Math.abs(nums[i])-1] *= -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 191. 位1的个数
     * *******
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int mask = 1;
        int result = 0;
        for(int i = 0; i < 32; i++ ){
            if((n & mask) != 0){
                result++;
            }
            mask <<= 1;
        }
        return result;
    }



}
