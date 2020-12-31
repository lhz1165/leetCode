package org.algorithm.leetcode300.nomal.test.everyday;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/12/30
 **/
public class EveryDay05 {
    public static void main(String[] args) {
        EveryDay05 e = new EveryDay05();
        //[[0,2],[1,3],[2,4],[3,5],[4,6]]
        int[][] ints = {{0, 2}, {1, 3},{2,4},{3,5},{4,6}};
        System.out.println(e.eraseOverlapIntervals(ints));
    }
    /**
     * 435. 无重叠区间
     * 如果重合，需要删除，删掉右边界最长的那一段
     * 接着遍历
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        List<Pair> pairs = new ArrayList<>();
        for (int[] interval : intervals) {
            pairs.add(new Pair(interval[0], interval[1]));
        }
        pairs.sort((o1, o2) -> {
            if (o1.start - o2.start == 0) {
                return o1.end - o2.end;
            }else {
                return o1.start - o2.start;
            }
        });
        Pair pair = pairs.get(0);
        int res = 0;
        int prevStart = pair.start;
        int prevEnd = pair.end;
        for (int i = 1; i < pairs.size(); i++) {
            Pair current = pairs.get(i);
            int curStart = current.start;
            int curEnd = current.end;
            if (curStart < prevEnd) {
                if (curStart == prevStart && prevEnd == curEnd) {
                    res++;
                }else if (curStart == prevStart && prevEnd < curEnd){
                    res++;
                } else if (curStart > prevStart && prevEnd >= curEnd){
                    res++;
                    prevStart = curStart;
                    prevEnd = curEnd;
                }else if (curStart > prevStart && curEnd > prevEnd){
                    res++;
                }
            }else {
                prevStart = curStart;
                prevEnd = curEnd;
            }
        }
        return res;


    }



}
class Pair {
    int start;
    int end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
