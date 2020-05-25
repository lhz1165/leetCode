package org.example.leetcode300.test;

import org.example.leetcode300.demo.ListNode;

import java.util.HashSet;
import java.util.Set;

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


        int[] num2 = {1,3};
        int[] num1 = {4,5,6,7};
        System.out.println(MedianofTwoSortedArrays(num1, num2));
        String s = "abbdswq";
        System.out.println(lengthOfLongestSubstring(s));

        System.out.println(longestPalindrome("abcdbbfcba"));
        System.out.println(longestPalindrome2("abcdbbfcba"));
    }

    /**
     * 两数相加
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

        while (p1 != null || p2 != null||carry>0) {
            int x = p1 == null ?  0 :  p1.val;
            int y = p2 == null ?  0 :  p2.val;
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
            int sum = x + y+carry;
            ListNode node;
            if (sum >=10) {
                carry = sum / 10;
                sum=sum%10;
                node = new ListNode(sum);
            }else {
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
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int result = 1;
        for (int i = 0; i <s.length()-1 ; i++) {
            HashSet<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            int len=1;
            for (int j = i+1; j <s.length() ; j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }else {
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
     * @param nums1
     * @param nums2
     * @return
     */
    public static double MedianofTwoSortedArrays(int[] nums1, int[] nums2){
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
        for (int i = 0; i <=(m+n)/2 ; i++) {
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
                }else {
                    if (nums1[p1] < nums2[p2]) {
                        right = nums1[p1++];
                    }else {
                        right = nums2[p2++];
                    }
                }
            }
        }
        if (((m + n) & 1 )== 0) {
            return (left+right)/2.0;
        }else {
            return right;
        }
    }

    /**
     * 最长公共子串
     * @param str1
     * @param str2
     * @return
     */
    public static String mgetLCSLength(String str1, String str2) {

        int[][] matrix = new int[str1.length()][str2.length()];
        int indexI=0;
        int len = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        matrix[i][j] = 1;
                    }else {
                        matrix[i][j] = 1 + matrix[i - 1][j - 1];

                    }
                }
                if (matrix[i][j]>len) {
                    //判断是否倒装，导致相同的
                    int beforeRev = str1.length() - 1 - j;
                    if (beforeRev + matrix[i][j] - 1 == i) { //判断下标是否对应
                        len = matrix[i][j];
                        indexI = i;
                    }
                }
            }
        }
        return str1.substring(indexI - len + 1, indexI+1);

    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        return mgetLCSLength(s,reverse(s));
    }

    /**
     * 反正字符串
     * @param str
     * @return
     */
    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        char[] reverseChar = new char[str.length()];
        int j = 0;
        for (int i = chars.length-1; i >= 0; i--) {
            reverseChar[j++] = chars[i];
        }
        return String.valueOf(reverseChar);
    }








}
