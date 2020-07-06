package org.example.leetcode300.specified.dynamic.leetcode.easy;

/**
 * @author: lhz
 * @date: 2020/7/6
 **/
public class DPTest02 {
    public static void main(String[] args) {
        System.out.println(waysToStep(5));
    }

    /**
     * 面试题 08.01. 三步问题
     * @param n
     * @return
     */
    public static int waysToStep(int n) {
        int[] f = new int[n+1];
        f[0] = 1;
        //f[1] = 1;

        //取模，对两个较大的数之和取模再对整体取模，防止越界（这里也是有讲究的）
        //假如对三个dp[i-n]都 % 1000000007，那么也是会出现越界情况（导致溢出变为负数的问题）
        //因为如果本来三个dp[i-n]都接近 1000000007 那么取模后仍然不变，但三个相加则溢出
        //但对两个较大的dp[i-n]:dp[i-2],dp[i-3]之和mod 1000000007，那么这两个较大的数相加大于 1000000007但又不溢出
        //取模后变成一个很小的数，与dp[i-1]相加也不溢出
        //所以取模操作也需要仔细分析
        for(int i = 1; i <= n; i++){
            if(i-3 >= 0){
                f[i] += f[i-3];
            }
            if(i-2 >= 0){
                f[i] += f[i-2];
                f[i] %= 1000000007;
            }
            f[i] += f[i-1];
            f[i] %= 1000000007;
        }
        return f[n];
    }
}
