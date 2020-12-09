package org.algorithm.leetcode300.nomal.test;

import org.algorithm.leetcode300.basic.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: lhz
 * @date: 2020/12/8
 **/
public class EasyTest03 {
    public static void main(String[] args) {
        EasyTest03 e = new EasyTest03();
        e.masterMind2("YYRG", "RRRR");
    }

    /**
     * 1184. 公交站间的距离
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            int tmp = start;
            start = destination;
            destination = tmp;
        }

        int all = 0;
        for (int i : distance) {
            all += i;
        }
        int result = 0;

        for (int i = 0; i < distance.length; i++) {
            if (i == destination) {
                break;
            }
            if (start <= i) {
                result += distance[i];
            }
        }
        result = Math.min(result, all - result);


        return result;
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



}
