package org.example.leetcode300.specified.dynamic.lintcode;

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
        System.out.println(coinChange(data,25));
    }

    /**
     *  A =[2, 5, 7]三枚硬币
     *
     *  M=凑成27,最小的硬币数
     *
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



    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if(n == 0){
            return 0;
        }
        //凑 i 元需要的最少硬币
        int [] f=new int [amount+1];
        for(int i = 1; i <= n ;i++){
            f[i] = Integer.MAX_VALUE;
            for(int j = 0;j <coins.length ;j++){
                if(i-coins[j]>=0&&f[i - coins[j]]<Integer.MAX_VALUE){
                    f[i]=Math.min(f[i],f[i-coins[j]]+1);
                }
            }
        }
        System.out.println();

        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }

}
