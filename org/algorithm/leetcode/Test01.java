package org.algorithm.leetcode;

import org.algorithm.leetcode.specified.OperationLog;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: lhz
 * @date: 2020/8/19
 **/
public class Test01 {
    public static void main(String[] args)  {
        new Test01().powIteration(2, 5);
    }





    public double powIteration(double x, int n) {
        double ans = 1;
        //遍历每一位
        while (n > 0) {
            //最后一位是 1，加到累乘结果里
            if ((n & 1) == 1) {
                ans = ans * x;
            }
            //更新 x
            x = x * x;
            //n 右移一位
            n = n >> 1;
        }
        return ans;
    }
}
