package org.algorithm.leetcode.specified.dynamic.lintcode;

/**
 * @author: lhz
 * @date: 2020/6/23
 * 划分型动态规划
 **/
public class DecodeWithDP {
    public static void main(String[] args) {

        System.out.println(decodeString("19261001"));
    }
    /**
     * 字符串有多少种解密方式
     * @param s
     * @return
     */
    public static int decodeString(String s) {
        char[] ss = s.toCharArray();
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int f[] = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = 0;
            if (ss[i-1] >= '1' && ss[i-1] <= '9') {
                f[i] = f[i - 1];

            }
            if (i > 1) {
                int j = 10 * (ss[i-2]-'0') + (ss[i-1]-'0');
                if (j >= 10 && j <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[n];
    }
}
