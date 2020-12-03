package org.algorithm.leetcode300.nomal.test;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/12/1
 **/
public class EveryDay02 {




    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * <p>
     * <p>
     * 思路两次二分，求最左边和最右边的值
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        //向左压缩
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            res[0] = start;
        } else if (nums[end] == target) {
            res[0] = end;
        } else {
            res[0] = -1;
        }

        start = 0;
        end = nums.length - 1;
        //向右压缩
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) {
            res[1] = end;
        } else if (nums[start] == target) {
            res[1] = start;
        } else {
            res[1] = -1;
        }
        return res;
    }

    /**
     * 204. 计数质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        boolean[] results = new boolean[n];
        //假设所有数字都是素数
        Arrays.fill(results, true);
        for (int i = 2; i < n; i++) {
            //素数的倍数不是素数
            if (results[i]) {
                for (int j = 2 * i; j < n; j = i + j) {
                    results[j] = false;
                }
            }
        }
        int count = 0;
        for (boolean result : results) {
            if (result) {
                count++;
            }
        }
        return count - 2;
    }

    public static void main(String[] args) {
        EveryDay02 e = new EveryDay02();
        System.out.println(e.gcdOfStrings("ABCDABCDABCD", "ABCD"));
    }

    /**
     * 1071. 字符串的最大公因子
     */
    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() < str2.length()) {
            String tmp = str1;
            str1 = str2;
            str2 = tmp;
        }

        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int p = 0;
        int q = 0;
        while (p < str1.length() && q < str2.length()) {
            if (c1[p] != c2[q]) {
                return "";
            }
            p++;
            q++;
        }
        String remain = str1.substring(p);

        for (int i = 0; i < remain.length(); i++) {
            String sub = remain.substring(0, i + 1);
            boolean check = check(str2, sub);
            if (!check) {
                continue;
            }
            return sub;
        }
        return "";
    }

    public boolean check(String s, String sub) {
        int step = sub.length();
        for (int i = 0; i < s.length(); i += step) {
            int start = i;
            int end = Math.min(step + i, s.length());
            String str2Sub = s.substring(start, end);
            if (!str2Sub.equals(sub)) {
                return false;
            }
        }
        return true;

    }


}
