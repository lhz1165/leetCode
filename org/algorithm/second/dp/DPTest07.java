package org.algorithm.second.dp;

/**
 * @author: lhz
 * @date: 2020/9/18
 * 双序列性动态规划
 **/
public class DPTest07 {
    public static void main(String[] args) {
        DPTest07 d = new DPTest07();
        d.isInterleave("a", "b", "abc");
    }

    /**
     * 最长公共子序列
     * 多用于文件差异比较
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        int m = c1.length;
        int n = c2.length;
        int [][]f = new int[m+1][n+1];
        int[][] pi = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i ==0 || j== 0){
                    continue;
                }
                f[i][j] = Math.max(f[i][j-1],f[i-1][j]);
                if (f[i][j] == f[i][j - 1]) {
                    pi[i][j] = 0;
                }else{
                    pi[i][j] = 1;
                }

                if(c1[i-1] == c2[j-1]){
                    f[i][j] = Math.max(f[i][j],f[i-1][j-1] + 1);
                    if (f[i][j] == f[i-1][j-1] + 1){
                        pi[i][j] = 2;
                    }
                }

            }
        }
        // 回溯得到子序列
        StringBuilder lcs = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (pi[i][j] == 2) {
                lcs.append(c1[i - 1]);
                i--;
                j--;
            } else if (pi[i][j] == 0) {
                j--;
            } else {
                i--;
            }
        }

        // 返回结果
        System.out.println("Longest Common Subsequence: " + lcs.reverse().toString());

        return f[m][n];
    }

    /**
     * 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
     * <p>
     * 输入:
     * "aabcc"
     * "dbbca"
     * "aadbbcbcac"
     * 输出:
     * true
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        int n = s1.length();
        int m = s2.length();
        //f[i][j]表示s3的0到i+j是否由s1的前i个 s2的前j个组成
        boolean[][] f = new boolean[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 && j == 0) {
                    f[i][j] = true;
                    continue;
                }
                if (i > 0 && s3.charAt(i + j - 1) == s1.charAt(i - 1)) {
                    f[i][j] |= f[i - 1][j];
                }
                if (j > 0 && s3.charAt(i + j - 1) == s2.charAt(j - 1)) {
                    f[i][j] |= f[i][j - 1];
                }
            }
        }
        return f[n][m];
    }

    /**
     * 给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
     * 编辑距离
     * <p>
     * 你总共三种操作方法：
     * <p>
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * 输入:
     * "horse"
     * "ros"
     * 输出: 3
     * 解释:
     * horse -> rorse (替换 'h' 为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        int n = word1.length();
        int m = word2.length();
        //f[i][j]表示world1的前i个变成world2的前j个用的最少步骤
        int[][] f = new int[n + 1][m + 1];
        f[0][0] = 0;
        for (int i = 0; i < n + 1; i++) {
            f[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            f[0][i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                f[i][j] = Integer.MAX_VALUE;

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                }
                //在单词 A 中插入一个字符
                f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                //在单词 B 中插入一个字符
                f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                //替换
                f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + 1);
            }
        }
        return f[n][m];

    }

    public int findMaxForm(String[] strs, int m, int n) {
        //设f[i][j][k]为前i个01串最多能有多少个被j个0和k个1组成
        int t = strs.length;
        int[][][] f = new int[t + 1][m + 1][n + 1];
        for (int i = 0; i <= t; i++) {
            int count0 = getZero(strs[i]);
            int count1 = getOne(strs[i]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (i == 0) {
                        f[i][j][k] = 0;
                        continue;
                    }
                    if (j == 0 && k == 0) {
                        f[i][j][k] = 0;
                        continue;
                    }
                    if (j >= count0 && k >= count1) {
                        f[i][j][k] = Math.max(f[i - 1][j][k], f[i - 1][j - count0][k - count1] + 1);
                    } else {
                        f[i][j][k] = f[i - 1][j][k];
                    }

                }
            }
        }
        return f[strs.length][m][n];
    }

    public int getZero(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }

    public int getOne(String str) {
        return str.length() - getZero(str);
    }


}
