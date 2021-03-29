package org.algorithm.leetcode.nomal.test.everyday;

/**
 * @author: lhz
 * @date: 2021/3/29
 **/
public class EveryDay09 {
    public static void main(String[] args) {
        EveryDay09 e = new EveryDay09();
        System.out.println(e.reverseBits(1));
    }

    public int reverseBits(int n) {
        int res = 0;
        int flag = 1;
        for (int i = 0; i < 32; i++) {
            res += n & flag;
            if (i != 31) {
                res <<= 1;
                n >>= 1;
            }
        }
        return res;
    }
}
