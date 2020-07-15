package org.example.leetcode300.specified.dynamic.leetcode.easy;

/**
 * @author: lhz
 * @date: 2020/7/3
 **/
public class DPTest01 {
    public static void main(String[] args) {
        int[] a = {-2,2,-3,4,-1,2,1,-5,3};
        maxSubArray(a);
    }


    /**
     * 392. 判断子序列 AC
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列
     * <p>
     * f[i]={f[i-1] && t[i]=s[j]}
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        //big
        char[] tt = t.toCharArray();
        //little
        char[] ss = s.toCharArray();
        int n = tt.length;
        int m = ss.length;
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            f[i][0] = true;
        }
        for (int i = 1; i <= m; i++) {
            f[0][i] = false;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (ss[j - 1] == tt[i - 1] && i >= j) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        return f[n][m];
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和 AC
     * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
     * <p>
     * <p>
     * 如果最后一步的前一种情况为负数，那么直接跳到这里重新开始数
     * 如果是正数那么直接相加
     * f[i]代表数组从nums[0]---->nums[i]最大连续值
     * f[i]=f[i-1]+nums[i] || f[i]=nums[i]
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int f[] = new int[n];
        f[0] = nums[0];
        int max = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
            max = Math.max(max, f[i]);
        }
        return max;
    }

    /**
     * 70. 爬楼梯 AC
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        if (n == 1) {
            return 1;
        }

        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];

    }

    /**
     * 746. 使用最小花费爬楼梯 AC
     * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] cost2 = new int[n - 1];
        System.arraycopy(cost, 1, cost2, 0, n - 1);
        int[] f = new int[n + 1];
        int[] f2 = new int[n];
        f[0] = 0;
        f[1] = 0;
        f2[0] = 0;
        //从 0起步
        for (int i = 2; i <= n; i++) {
            //从起步到终点花费的体力计算
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= 2; j++) {
                f[i] = Math.min(f[i], f[i - j] + cost[i - j]);
            }
        }
        //从 1 开始起步 楼梯长度少1
        for (int i = 1; i <= n - 1; i++) {
            //从起步到终点花费的体力计算
            f2[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= 2; j++) {
                if (i - j >= 0) {
                    f2[i] = Math.min(f2[i], f2[i - j] + cost2[i - j]);

                }
            }
        }
        return Math.min(f[n], f2[n - 1]);
    }

    /**
     *
     *
     * 1025. 除数博弈
     *
     * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     *
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
     * @param N
     * @return
     */
    public static boolean divisorGame(int N) {
        boolean[] f = new boolean[N+1];
        f[0] = false;
        f[1] = false ;
        if(N == 1){
            return false;
        }
        //要判断当前这数有没有机会赢
        //那么必须判断假设取了某个值i 对手(N-i)赢不赢 对手一定赢 我们输 对手一定输 那么我们一定赢
        for(int i = 2; i <= N; i++){
            for(int x =1; x < i; x++){
                if(i % x == 0){
                    //求当前是否能有一种取胜结果 取这个结果
                    //要是对手取胜 那么自己肯定就输了
                    f[i] |= !f[i-x];
                    if(f[i]){
                        break;
                    }
                }

            }
        }
        return f[N];

    }






}

/**
 * 303. 区域和检索 - 数组不可变
 * <p>
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点
 * 使用动态规划 内存超了 不知道如何解决
 */
class NumArray {

    public static void main(String[] args) {
        int[] a = {-2, 0, 3, -5, 2, -1};
        NumArray array = new NumArray(a);
    }

    int[] nums;
    int[][] f;

    public NumArray(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        f = new int[n][n];
        for (int i = 0; i < n; i++) {
            f[i][i] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i != j) {
                    f[i][j] = f[i][j - 1] + nums[j];
                }

            }
        }
        System.out.println();
    }

    public int sumRange2(int i, int j) {
        return f[i][j];
    }
    //使用递归可以完成
//    public NumArray(int[] nums) {
//        this.nums = nums;
//    }
//    public int sumRange(int i, int j) {
//        if(i==j){
//            return nums[i];
//        }else{
//            return nums[i]+sumRange(i+1,j);
//        }
//
//    }
}
