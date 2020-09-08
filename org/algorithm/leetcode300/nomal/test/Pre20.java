package org.algorithm.leetcode300.nomal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/6/19
 **/
public class Pre20 {
    public static void main(String[] args) {
//        int[] a = {0,1,2};
//        System.out.println(threeSumClosest(a,3));
//
//        System.out.println(lengthOfLongestSubstring("pwwkewabccd"));
//        int [] aa={1, -1, 5, -2, 3};
//        System.out.println(maxSubArrayLen(aa, 3));
        Pre20 p = new Pre20();
        int[] a = {1, 1, 2, 2, 3, 3, 4,4,4,4,4,4};
        int i = p.removeDuplicates(a);
        System.out.println();


    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minL = 0;
        int minR = 0;
        int minI = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length-3; i++) {
            int goal = target - nums[i];
            int LO = i + 1;
            int HI = nums.length - 1;
            while (LO < HI) {
                int two = nums[HI] + nums[LO];
                if (Math.abs(two-goal)<min) {
                    min = Math.abs(two-goal);
                    minL = LO;
                    minR = HI;
                    minI = i;
                }
                if (goal >= two) {
                    LO++;
                } else  {
                    HI--;
                }
            }


        }
        return nums[minI] + nums[minL] + nums[minR];

    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")) {
            return new ArrayList<String>();
        }
        List<String> keys = Arrays.asList("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");
        List<String> result = new ArrayList<>();
        recursive(keys, result, digits, 0, "");
        return result;
    }

    /**
     *
     * @param keys
     * @param result
     * @param digits 输入的一串数字
     * @param index 第几个数字
     * @return
     */
    public void recursive(List<String> keys,List<String> result,String digits,int index,String prefix) {
        //offset 代表在加哪个数字
        if (index == digits.length()) {
            result.add(prefix);
            return ;
        }
        // abc  def
        String letters = keys.get(digits.charAt(index) - '0');
        for (int i = 0; i < letters.length(); i++) {
            recursive(keys,result,digits,index+1,prefix+letters.charAt(i));
        }
    }

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {

        if (s.length() < 2) {
            return s.length();
        }
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        int result = 1;
        for (int i = 0; i < n; i++) {
            f[i][i] = true;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (j > i) {
                    f[i][j] = f[i][j - 1] && (s.substring(i, j).indexOf(s.charAt(j))==-1);
                    if (f[i][j]) {
                        result = Math.max(result, j - i + 1);
                    }
                }
            }
        }
        return result;
    }

    //1,-1,5,-2,3,3
    // f[i][j] = f[i][j-1]+nums[j]
    public static int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //数组中i-j的和
        int[][] f = new int[n][n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            f[i][i] = nums[i];
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (j > i) {
                    f[i][j] = f[i][j - 1] + nums[j];
                    if (f[i][j] == 4) {
                        result = Math.max(result, j - i + 1);
                    }
                }
            }
        }
        return result;
    }
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int prev = nums[0];
        int result = 1;
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != prev) {
                result++;
                prev = nums[i];
                nums[index] = nums[i];
                index++;
            }
        }
        return result;

    }



}
