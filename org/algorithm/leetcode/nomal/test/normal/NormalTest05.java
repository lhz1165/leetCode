package org.algorithm.leetcode.nomal.test.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NormalTest05 {
    public static void main(String[] args) {
        NormalTest05 n =new NormalTest05();
        System.out.println("n.canJump(new int[]{3,2,1,0,4}) = " + n.canJump(new int[]{2,3,1,1,4}));
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> results = new ArrayList<>();
        int n = nums.length;
        if (n==0){
            return new ArrayList<>();
        }
        if (n == 1){
            return new ArrayList<>(Arrays.asList(nums[0]));
        }
        //f[i]表示下标i结尾的数组的最大整除长度
        int f[] = new int[n];
        f[0] = 1;
        Arrays.sort(nums);
        int maxLen = 1;
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            f[i]=1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            maxLen = Math.max(f[i],maxLen);
            maxIndex = maxLen==f[i]?i:maxIndex;
            System.out.println(f[i]);
        }
        results.add(nums[maxIndex]);
        int prevLen = maxLen;
        int prev = nums[maxIndex];
        for (int i = maxIndex - 1; i >=0; i--) {
            if (f[i] == prevLen - 1 && prev % nums[i] == 0) {
                prevLen--;
                prev = nums[i];
                results.add(nums[i]);
            }
        }
        System.out.println(results);
        return results;
    }

    /**
     * 55. 跳跃游戏
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return true;
        }
        boolean[] f = new boolean[n];
        f[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && i - j <= nums[j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n - 1];
    }
}
