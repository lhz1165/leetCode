package org.example.leetcode300.dynamic;

/**
 * @author: lhz
 * @date: 2020/6/12
 * 解决动态规划的方法
 * 1 确定状态
 * 最后一步（最后一个决策）  子问题
 *  
 * 2 转移方程
 *   f(x)=min{f(x-2)+1,f(x-5)+1,f(x-7)+1}
 * 3 两个关键点(边界情况，初始条件)
 *
 * 4 计算顺序
 **/
public class DymicTest {
    public static void main(String[] args) {
        int[] data = {2, 5, 7};
        System.out.println(test01(data,1000));
    }
    /**
     *  A =[2, 5, 7]三枚硬币
     *  M=凑成27最小的硬币数
     *  f(x)=min{f(x-2)+1,f(x-5)+1,f(x-7)+1}
     *
     * @return
     */
    public static int test01(int[]A,int M) {
        int[] f = new int[M + 1];
        f[0] = 0;
        int result = 0;
        //计算顺序
        for (int i = 1; i <= M; i++) {
            f[i] = Integer.MAX_VALUE;
            //内层比较  min{f(x-2)+1,f(x-5)+1,f(x-7)+1}
            for (int j = 0; j <A.length ; j++) {
                if (i - A[j] >= 0&&f[i - A[j]]<Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - A[j]]+1);
                }
            }
        }
        result = f[M];
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }

}
