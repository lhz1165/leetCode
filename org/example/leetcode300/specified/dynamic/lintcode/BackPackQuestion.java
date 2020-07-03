package org.example.leetcode300.specified.dynamic.lintcode;

/**
 * @author: lhz
 * @date: 2020/6/30
 * 背包问题
 **/
public class BackPackQuestion {
    public static void main(String[] args) {
        int[] A = {3,4,5,8};
        int[] nums = {1,2,3,3,7};
        System.out.println(backPack2(10, A));
        System.out.println(backPackV(nums,7));
    }


    /**
     * 背包问题数组大小就是总承重有关，
     * 画图
     * 行表示 0-重量的数组
     * 列表示 0-每个物品的重量
     *
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public static int backPack(int m, int[] A) {
        // write your code here
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        boolean[][] f = new boolean[n+1][m+1];
        //最后一个物品是否进入背包
        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            f[n][0] = false;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //这个前一个填满，或者这个的前一个剩下这个才填满
                //f[i-1][w]orf[i-1][W-A（i-1）]
//                if (j > A[i - 1]) {
//                    f[i][j] |= f[i - 1][j - A[i - 1]];
//                }
                f[i][j] |= f[i - 1][j];
                if (f[i-1][j]&&A[i-1]+j<=m) {
                    f[i][A[i-1] + j] = true;
                }
            }
        }
        int res = 0;
        for (int i = m; i >= 0; i--) {
            if (f[n][i]) {
                res = i;
                break;
            }
        }
        return res;



    }

    /**
     * 背包最大承重一维数组来解决
     * @param m
     * @param A
     * @return
     */
    public static int backPack2(int m, int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        boolean f[] = new boolean[m+1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            //因为更新这f[j]需要老的f[i]和老f[j - A[i - 1]]
            //但是从小到大更新 会直接把老的f[j - A[i - 1]]更新成新的导致数据出错
            for (int j = m; j >= 1; j--) {
            //for (int j = 0; j<=m; j++) {
                //等于代表前i个物品 加上第i个物品刚好能装满j重量
                if (j >= A[i - 1]) {
                    //查看是否能刚好装下
                    f[j] = f[j] | f[j - A[i - 1]];
                }
            }
        }
        int res = 0;
        for (int i = m; i >= 0; i--) {
            if (f[i]) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
     * 每一个物品只能使用一次
     *
     * @param nums:   an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public static int backPackV(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] f = new int[target + 1];
        f[0] = 0;
        for (int i = 1; i <=target ; i++) {
            f[i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    f[j] += f[j - nums[i - 1]];
                }
            }
        }
        System.out.println();
        return f[target];
    }


}
