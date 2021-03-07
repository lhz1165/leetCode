package org.algorithm.leetcode.nomal.test.everyday;

import sun.font.FontRunIterator;
import sun.plugin.viewer.context.AxBridgeAppletContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2021/3/4
 **/
public class EveryDay07 {
    public static void main(String[] args) {
        EveryDay07 e = new EveryDay07();
        e.maxEnvelopes(new int[][]{{10,8}, {1,12}, {6,15}, {2,18}});
        e.partition("aab");
    }
    /**
     *354. 俄罗斯套娃信封问题
     * [[10,8],[1,12],[6,15],[2,18]]
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes,(a,b)->{
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int maxVal = 0;
        int n = envelopes.length;
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && (envelopes[i][1] > envelopes[j][1])) {
                    f[i] = Math.max(f[i],f[j] + 1);
                    maxVal = Math.max(f[i], maxVal);
                }
            }
        }
        return maxVal;
    }

    /**
     * 131. 分割回文串
     * *****
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        boolean[][] isPartition = helperPartition(s);
        List<List<String>> results = new ArrayList<>();
        helperDfs(results, new ArrayList<String>(), 0,isPartition,s);
        return results;

    }


    /**
     * 字符串哪些是回文串
     * @param s
     * @return
     */
    public  boolean[][] helperPartition(String s) {
        char[] cs = s.toCharArray();
        int n = s.length();
        boolean[][] isPartition = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPartition[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            isPartition[i][i + 1] = cs[i] == cs[i + 1];
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                    isPartition[i][i + len - 1] = cs[i] == cs[i + len - 1] && isPartition[i+1][i + len - 2];
            }
        }
        return isPartition;
    }



    private void helperDfs(List<List<String>> results, List<String> result, int startIndex, boolean[][] isPartition,String s) {
        if (startIndex == s.length()) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPartition[startIndex][i]) {
                result.add(s.substring(startIndex, i+1));
                helperDfs(results,result,i+1,isPartition,s);
                result.remove(result.size() - 1);
            }
        }

    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxNums = new int[n];
        int[] minNums = new int[n];
        maxNums[0] = nums[0];
        minNums[0] = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            maxNums[i] = Math.max(nums[i], Math.max(nums[i] * maxNums[i - 1], nums[i] * minNums[i - 1]));
            minNums[i] = Math.min(nums[i], Math.min(nums[i] * maxNums[i - 1], nums[i] * minNums[i - 1]));
            max = Math.max(max, maxNums[i]);
        }
        return max;


    }


}
