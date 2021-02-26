package org.algorithm.leetcode.nomal.test.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: lhz
 * @date: 2021/2/18
 **/
public class NormalTest03 {
    public static void main(String[] args) {
        NormalTest03 n = new NormalTest03();
//        n.topKFrequent(new int[]{3, 3, 2, 1}, 1);
//
//        n.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
//        System.out.println();
//        n.decodeString("3[a2[c]]");
        n.nthUglyNumber(364);


    }

    /**
     * 215. 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int i = start;
        int j = end;
        int pivot = nums[(i + j) / 2];
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            if (i <= j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }
        if (start + k - 1 <= j) {
            return findKthLargest(nums, k, start, j);
        }
        if (start + k - 1 >= i) {
            return findKthLargest(nums, k - (i - start), i, end);
        }
        return nums[j + 1];

    }

    /**
     * 494. 目标和
     */
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        //前i个数 和为 j的个数
        int[][] f = new int[n + 1][2001];
        for (int i = 0; i <= 2000; i++) {
            f[0][i] = 0;
        }
        f[0][1000] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 2000; j++) {
                if (j + nums[i - 1] <= 2000 && f[i - 1][j + nums[i - 1]] > 0) {
                    f[i][j] += f[i - 1][j + nums[i - 1]];
                }
                if (j - nums[i - 1] >= 0 && f[i - 1][j - nums[i - 1]] > 0) {
                    f[i][j] += f[i - 1][j - nums[i - 1]];
                }
            }
        }
        return f[n][S + 1000];
    }

    /**
     * 394. 字符串解码
     * *****
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        char l = '[';
        char r = ']';
        int times = 0;
        Stack<String> sStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (char c : chars) {
            //【
            if (c == l) {
                sStack.push(res.toString());
                numStack.push(times);
                times = 0;
                res = new StringBuilder();
                // 】
            } else if (c == r) {
                StringBuilder sub = new StringBuilder();
                Integer curTimes = numStack.pop();
                String subStr = sStack.pop();
                for (int i = 0; i < curTimes; i++) {
                    sub.append(res);
                }
                res = new StringBuilder(subStr + sub);
                //数字
            } else if (Character.isDigit(c)) {
                times = times * 10 + Integer.parseInt(c + "");
                //字母
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }


    /**
     * 找数字
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            int l = mid;
            int r = mid;
            //向左拓展判断数字是否相同
            while (l - 1 >= left && nums[l] == nums[l - 1]) {
                l -= 1;
            }
            //向右拓展数字是否相同
            while (r + 1 <= right && nums[r] == nums[r + 1]) {
                r += 1;
            }
            //判断前面的数字个数能否被3整除，如果能被整除则说明要找的数字再右半部分
            if (l % 3 == 0) {
                left = r + 1;
            } else {
                //不能被3整除则说明要找的数字再左半部分
                right = l - 1;
            }
        }
        return nums[right];


    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Node cur = head;
        Node dummy = new Node(0);
        Node prev = dummy;
        Map<Node, Node> oldTonew = new HashMap<>();
        while (cur != null) {
            Node newCur = new Node(cur.val);
            oldTonew.put(cur, newCur);
            prev.next = newCur;
            prev = newCur;
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node newRandom = oldTonew.get(cur.random);
            Node newCur = oldTonew.get(cur);
            newCur.random = newRandom;
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 剑指 Offer 49. 丑数
     * ********
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n <= 6) {
            return n;
        }
        int a = 1;
        int b = 1;
        int c = 1;
        int[] f = new int[n + 1];
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            int rA = f[a] * 2;
            int rB = f[b] * 3;
            int rC = f[c] * 5;
            f[i] = Math.min(rA, Math.min(rB, rC));
            if (f[i] == rA) {
                a++;
            }
            if (f[i] == rB) {
                b++;
            }
            if (f[i] == rC) {
                c++;
            }
        }
        return f[n];
    }


    

    static class Pair {
        int num;
        int count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


}

