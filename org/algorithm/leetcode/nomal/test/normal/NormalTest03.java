package org.algorithm.leetcode.nomal.test.normal;

import org.algorithm.leetcode.basic.TreeNode;

import java.util.*;


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
        n.singleNumber(new int[]{3,4,3,3});
        n.minNumber(new int[]{3, 30, 34});
        n.singleNumbers(new int[]{1, 2, 2, 3, 3, 4});
        System.out.println((double) 1/216);
        n.dicesProbability(2);
        System.out.println(n.myPow(3, 5));


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

    /**
     * 剑指 Offer 66. 构建乘积数组
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        int[] tmp = new int[n];
        tmp[0] = 1;
        for (int i = 1; i < tmp.length; i++) {
            tmp[i] = tmp[i - 1] * a[i - 1];
        }
        int[] tmp2 = new int[n];
        tmp2[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            tmp2[i] = tmp2[i + 1] * a[i + 1];
        }
        for (int i = 0; i < a.length; i++) {
            res[i] = tmp[i] * tmp2[i];
        }
        return res;
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                subResult.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            if (!leftToRight) {
                int start = 0;
                int end = subResult.size() - 1;
                while (start < end) {
                    int tmp = subResult.get(start);
                    subResult.set(start, subResult.get(end));
                    subResult.set(end, tmp);
                    start++;
                    end--;
                }
            }
            result.add(subResult);
            leftToRight = !leftToRight;
        }
        return result;
    }

    /**
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        helperPathSum(root, sum, result, 0, new ArrayList<>());
        return result;
    }

    private void helperPathSum(TreeNode root, int sum, List<List<Integer>> result, int cur, List<Integer> subRes) {
        if (root == null) {
            return;
        }
        subRes.add(root.val);
        cur += root.val;
        if (cur == sum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(subRes));
        }
        helperPathSum(root.left, sum, result, cur, subRes);
        helperPathSum(root.right, sum, result, cur, subRes);
        subRes.remove(subRes.size() - 1);
    }

    /**
     * 剑指 Offer 45. 把数组排成最小的数
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        List<String> numStr = new ArrayList<>();
        for (int num : nums) {
            numStr.add(String.valueOf(num));
        }
        numStr.sort((a, b) -> (a + b).compareTo(b + a));
        return String.join("", numStr);

    }
    public int[] singleNumbers(int[] nums) {

        return null;
    }

    //338. 比特位计数
    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        int[] f = new int[num + 1];
        f[0] = 0;
        int heightBit = 0;
        for (int i = 1; i <= num; i++) {
            //代表2的n次方,它的位数永远为1
            if((i & (i-1)) == 0){
                f[i] = 1;
                heightBit = i;
                continue;
            }
            f[i] = f[i - heightBit] + 1;
        }

        return f;
    }

    /**
     * 剑指 Offer 60. n个骰子的点数
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        if (n == 0) {
            return new double[0];
        }
        int m = 6 * n;
        //表示n个筛子赛中j数字的方式
        //例如3个骰子 摇到 4 的方式可以有3种(2,1,1)(1,2,1)(1,1,2)
        int[][] f = new int[n+1][m+1];
        for (int i = 1; i <= 6; i++) {
            f[1][i] = 1;
        }
        f[0][0] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                for (int k = 1; k < j; k++) {
                    if ((j - k) <= 6*(i-1) && k <= 6) {
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        double[] res = new double[n * 6 - (n - 1)];
        int base = 0;
        for (int i = 0; i < f[n].length; i++) {
            base += f[n][i];
        }
        int index = 0;
        for (int i = n; i < f[n].length; i++) {
            res[index++] =(double)f[n][i] / (double)base;
        }
        return res;
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        //按照 中 ↗ 右 ↘ 左的顺序 先递增再递减
        //所以如果在递减的那里有元素大于root的值 那么马上返回false
        for(int i = postorder.length - 1; i >= 0; i--) {
            if(postorder[i] > root){
                return false;
            }
            //找到子树的root
            while(!stack.isEmpty() && stack.peek() > postorder[i]){
                root = stack.pop();
            }
            stack.push(postorder[i]);
        }
        return true;
    }


    /**
     * 剑指 Offer 16. 数值的整数次方
     * x的n次方
     * 先把n转化为二进制b1b2b3b4
     * 二进制转化十进制 b4*2^0+b3*2^1+b2*2^2+b1*2^3
     * x^b4*2^0+b3*2^1+b2*2^2+b1*2^3 = x^(b4*2^0) * x^(b3*2^1) * x^(b2*2^2) * x^(b1*2^3)
     * 只要bi是0 那么 x^(bi*2^j) = 1这样方便计算减少时间复杂度
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1){
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
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

