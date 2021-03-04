package org.algorithm.leetcode.nomal.test.everyday;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2021/3/4
 **/
public class EveryDay07 {
    public static void main(String[] args) {
        EveryDay07 e = new EveryDay07();
        e.maxEnvelopes(new int[][]{{10,8}, {1,12}, {6,15}, {2,18}});
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


}
