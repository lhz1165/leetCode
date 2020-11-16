package org.algorithm.leetcode300.nomal.test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: lhz
 * @date: 2020/11/16
 **/
public class EveryDay {
    public static void main(String[] args) {
        EveryDay e = new EveryDay();
        //[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]
        int[][] p = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2},{3,0}};
        e.reconstructQueue(p);

    }

    /**
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person1[0] - person2[0];
            } else {
                return person2[1] - person1[1];
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] p : people) {
            int number = p[1]+1;
            for (int i = 0; i < n; i++) {
                if (ans[i] == null) {
                    number--;
                    if (number == 0) {
                        ans[i] = p;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
