package org.example.leetcode300.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/6/29
 * 划分型动态规划
 * 指定长度为N的序列或者字符串，划分K段
 **/
public class HuaFenXing {
    public static void main(String[] args) {
        System.out.println(minCut2("aaabaa"));
    }

    public static void newL(List<Integer> list) {
        list = new ArrayList<>();

    }

    /**
     * 给定一个n的整数，问可以被几个完全平方数相加
     *
     * @param n
     * @return
     */
    public static int perfectSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {

                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }
        return f[n];
    }

    /**
     * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.
     *
     * @param s
     * @return
     */
    public static int minCut(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        //前n个回文串
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isHuiWen(s.substring(j, i))) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[n] - 1;
    }

    /**
     *
     * 这里先提前算出所有的回文串
     * 优化
     *不会超时了
     * @param s
     * @return
     */
    public static int minCut2(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        //前n个回文串
        boolean[][] c = calHuiWen(s);
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                //------|-------
                //        j    i-1
                //代表最后这一串是否回文串
                if (c[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[n] - 1;
    }

    //是否回文串
    public static boolean isHuiWen(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return String.valueOf(chars).equals(s);
    }

    //求出所有的会问字串
    public static boolean[][] calHuiWen(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean[][] res = new boolean[n][n];
        //偶数
        for (int i = 0; i < n - 1; i++) {
            int l = i;
            int r = i + 1;
            while (l >= 0 && r <= n - 1 && chars[l] == chars[r]) {
                res[l][r] = true;
                l--;
                r++;
            }
        }
        //奇数
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n - 1) {
                res[i][i] = true;
            }
            int l = i;
            int r = i;
            while (l >= 0 && r <= n - 1 && chars[l] == chars[r]) {
                res[l][r] = true;
                l--;
                r++;
            }
        }
        return res;
    }

    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     *
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        int n = pages.length;
        //前k个人抄写第n本书的时间
        int[][] f = new int[k][n];
        f[0][0] = 0;
        //初始化
        for (int i = 1; i < n; i++) {
            //0个人写前i本书无线时间
            f[0][i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <k ; i++) {
            //前k个人写0本书 花费0时间
            f[k][0] = 0;
        }
        //动态规划遍历
        //从1个人开始写
        for (int i = 1; i < k-1; i++) {
            for (int j = 1; j <n-1 ; j++) {
                int sum=0;
                f[i][j] = Integer.MAX_VALUE;
                for(int l = j; l >=0; l--) {
                    sum += pages[l];
                    f[i][j]=Math.max(f[k-1][j],)
                }

            }
        }




    }




}
