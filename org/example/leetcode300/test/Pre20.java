package org.example.leetcode300.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/6/19
 **/
public class Pre20 {
    public static void main(String[] args) {
        int[] a = {0,1,2};
        System.out.println(threeSumClosest(a,3));

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


}
