package org.algorithm.leetcode.nomal.test;

import org.algorithm.leetcode.basic.ListNode;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/5/22
 **/
public class Pre10 {
    public static void main(String[] args) {
//        ListNode l1 = new ListNode(9);
//        ListNode l2= new ListNode(9);
//        ListNode l3 = new ListNode(9);
//        l1.next = l2;
//        l2.next = l3;
//        ListNode n1 = new ListNode(9);
//        ListNode n2= new ListNode(9);
//        ListNode n3 = new ListNode(9);
//        n1.next = n2;
//        n2.next = n3;
//        ListNode node = addTowNumber(l1, n1);
//        System.out.println();


//        int[] num2 = {1,3};
//        int[] num1 = {4,5,6,7};
//        System.out.println(MedianofTwoSortedArrays(num1, num2));
//        String s = "abbdswq";
//        System.out.println(lengthOfLongestSubstring(s));
//
//        System.out.println(longestPalindrome("abcdbbfcba"));
//
//        System.out.println(convert("ABC", 1));
//        System.out.println(reverse(5321));
//        isPalindrome(121);
//        int i = maxArea(new int[]{2, 3, 4, 5, 18, 17, 6});
//        System.out.println(i);
//        System.out.println(intToRoman(40));
        int[] a = {-4, -1, -1, 0, 1, 2};
        System.out.println(threeSum(a));
        System.out.println(longestPalindrome2("aaaa"));
        int[] aa = {5,-3, 1, 2, -3, 4};
        System.out.println(subarraySum(aa));
    }

    /**
     * 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTowNumber(ListNode l1, ListNode l2) {
        ListNode curr;
        ListNode dummy = new ListNode(0);
        curr = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;

        while (p1 != null || p2 != null || carry > 0) {
            int x = p1 == null ? 0 : p1.val;
            int y = p2 == null ? 0 : p2.val;
            if (p1 != null) {
                p1 = p1.next;
            }

            if (p2 != null) {
                p2 = p2.next;
            }
            int sum = x + y + carry;
            ListNode node;
            if (sum >= 10) {
                carry = sum / 10;
                sum = sum % 10;
                node = new ListNode(sum);
            } else {
                node = new ListNode(sum);
                carry = 0;
            }
            curr.next = node;
            curr = node;
        }
        return dummy.next;
    }

    /**
     * 最长公共子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int result = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            HashSet<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            int len = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                } else {
                    set.add(s.charAt(j));
                    len++;
                }
            }
            result = Math.max(result, len);
        }
        return result;
    }

    /**
     * 两个数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double MedianofTwoSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //保存上一个值
        int left = 0;
        //保存当前值
        int right = 0;
        //num1的指针
        int p1 = 0;
        //num2的指针
        int p2 = 0;
        for (int i = 0; i <= (m + n) / 2; i++) {
            left = right;
            //不可能出现p1>m和p2>n的情况 所以这样来做
//            if (p1 < m && (p2 >= n || nums1[p1] < nums2[p2])) {
//                right = nums1[p1];
//                p1++;
//            }else {
//                right = nums2[p2];
//                p2++;
//            }
            if (p1 < m || p2 < n) {
                if (p1 >= m) {
                    right = nums2[p2++];
                } else if (p2 >= n) {
                    right = nums1[p1++];
                } else {
                    if (nums1[p1] < nums2[p2]) {
                        right = nums1[p1++];
                    } else {
                        right = nums2[p2++];
                    }
                }
            }
        }
        if (((m + n) & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    /**
     * 最长公共子串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String mgetLCSLength(String str1, String str2) {

        int[][] matrix = new int[str1.length()][str2.length()];
        int indexI = 0;
        int len = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        matrix[i][j] = 1;
                    } else {
                        matrix[i][j] = 1 + matrix[i - 1][j - 1];

                    }
                }
                if (matrix[i][j] > len) {
                    //判断是否倒装，导致相同的
                    int beforeRev = str1.length() - 1 - j;
                    if (beforeRev + matrix[i][j] - 1 == i) { //判断下标是否对应
                        len = matrix[i][j];
                        indexI = i;
                    }
                }
            }
        }
        return str1.substring(indexI - len + 1, indexI + 1);

    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        return mgetLCSLength(s, reverse(s));
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int n = s.length();
        String result = s.substring(0, 1);
        //代表第 i到j位是否回文串
        boolean[][] f = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            f[i][i] = true;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (j > i) {
                    //两位
                    if (j - i == 1) {
                        boolean b = s.charAt(i) == s.charAt(j);
                        if (b) {
                            f[i][j] = true;
                            if (j - i + 1 > result.length()) {
                                result = s.substring(i, j + 1);
                            }

                            continue;
                        }
                    }
                    //两位以上
                    if (j - i > 1) {
                        boolean b = s.charAt(i) == s.charAt(j);
                        f[i][j] = f[i + 1][j - 1] && b;
                        if (f[i][j]) {
                            if (j - i + 1 > result.length()) {
                                result = s.substring(i, j + 1);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 反正字符串
     *
     * @param str
     * @return
     */
    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        char[] reverseChar = new char[str.length()];
        int j = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            reverseChar[j++] = chars[i];
        }
        return String.valueOf(reverseChar);
    }

    /**
     * 就是给定一个字符串，然后按写竖着的 「z」的方式排列字符。
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        //判断有几行
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            rows.add(new StringBuilder());
        }
        //遍历字符串,从第0行开始,true为向下，fals为向上
        boolean direction = true;
        int getRow = 0;
        for (char c : s.toCharArray()) {
            //从下往上转向
            if (direction) {
                rows.get(getRow).append(c);
                getRow++;
                if (getRow >= rows.size()) {
                    direction = false;
                    getRow = Math.max(rows.size() - 2, 0);
                }
            } else {
                rows.get(getRow).append(c);
                getRow--;
                if (getRow < 0) {
                    direction = true;
                    getRow = getRow + 2 >= rows.size() ? 0 : getRow + 2;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    /**
     * Reverse Integer
     * 很简单，就是输入整数，输出它的倒置。
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int tmp = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE / 10) {
                return 0;
            }

            if (result < Integer.MIN_VALUE / 10) {
                return 0;
            }

            result = result * 10 + tmp;
        }
        return result;
    }

    /**
     * 字符串转换整数 (atoi)
     * @param str
     * @return
     */
//    public int myAtoi(String str) {
//        str.replace(" ", "");
//    }

    /**
     * 回文数
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int result = 0;
        while (x > 0) {
            int tmp = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE / 10) {
                break;
            }

            if (result < Integer.MIN_VALUE / 10) {
                break;
            }
            result = result * 10 + tmp;
        }
        if (result != x) {
            return false;
        }
        return true;
    }

    /**
     * 盛最多水的容器
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        //左指针
        int l = 0;
        //右指针
        int r = height.length - 1;
        //最大面积
        int max = 0;
        do {
            //左边的高度
            int lhigh = height[l];
            //右边高度
            int rhigh = height[r];
            //木桶效应取最小高度
            int mhigh = Math.min(lhigh, rhigh);
            max = Math.max(max, (r - l) * mhigh);
            //移动zhiz
            if (lhigh < rhigh) {
                l++;
            } else {
                r--;
            }
        } while (r - l > 0);
        return max;
    }

    /**
     * 整数转罗马数字
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        //1000
        int M = num / 1000;
        for (int i = 0; i < M; i++) {
            sb.append("M");
        }
        num = num % 1000;
        //900
        int CM = num / 900;
        for (int i = 0; i < CM; i++) {
            sb.append("CM");
        }
        num %= 900;

        //500
        int D = num / 500;
        for (int i = 0; i < D; i++) {
            sb.append("D");
        }
        num = num % 500;

        //400
        int CD = num / 400;
        for (int i = 0; i < CD; i++) {
            sb.append("CD");
        }
        num = num % 400;

        //100
        int C = num / 100;

        for (int i = 0; i < C; i++) {
            sb.append("C");
        }
        num = num % 100;
        //90
        int XC = num / 90;
        for (int i = 0; i < XC; i++) {
            sb.append("XC");
        }
        num %= 90;
        //50
        int L = num / 50;
        for (int i = 0; i < L; i++) {
            sb.append("L");
        }
        num = num % 50;
        //40
        int XL = num / 40;
        for (int i = 0; i < XL; i++) {
            sb.append("XL");

        }
        num %= 40;

        //10
        int X = num / 10;
        for (int i = 0; i < X; i++) {
            sb.append("X");
        }
        num %= 10;

        //9
        int IX = num / 9;
        for (int i = 0; i < IX; i++) {
            sb.append("IX");
        }
        num %= 9;

        //5
        int V = num / 5;
        for (int i = 0; i < V; i++) {
            sb.append("V");
        }

        num %= 5;
        //4
        int IV = num / 4;
        for (int i = 0; i < IV; i++) {
            sb.append("IV");
        }
        num %= 4;
        //1
        int I = num;
        for (int i = 0; i < I; i++) {
            sb.append("I");
        }
        return sb.toString();

    }

    /**
     * 动态规划
     * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，
     * 编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
     *
     * @param n
     * @return
     */
    public static int waysToChange(int n) {

        int[] dp = new int[n + 1];
        int[] coins = {1, 5, 10, 25};
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }

        return dp[n];
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); //排序
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int target = -nums[i];
                int LO = i + 1;
                int HI = nums.length - 1;
                while (LO < HI) {
                    if (nums[LO] + nums[HI] == target) {
                        result.add(Arrays.asList(nums[i], nums[LO], nums[HI]));
                        //元素相同要后移，防止加入重复的 list
                        while (LO < HI && nums[LO] == nums[LO + 1]) LO++;
                        while (LO < HI && nums[HI] == nums[HI - 1]) HI--;
                        LO++;
                        HI--;
                    } else if (nums[LO] + nums[HI] < target) {
                        LO++;
                    } else {
                        HI--;
                    }
                }


            }
        }
        return result;
    }

    public static ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        // 初始化
        hash.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 累加前缀和
            sum += nums[i];
            // 前缀和曾经出现，即这个区间的和为0
            if (hash.containsKey(sum)) {
                result.add(hash.get(sum) + 1);
                result.add(i);
                break;
            }
            //前缀和第一次出现，存入hash
            hash.put(sum, i);
        }
        return result;

    }






}
