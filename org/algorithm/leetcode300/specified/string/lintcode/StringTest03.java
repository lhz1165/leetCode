package org.algorithm.leetcode300.specified.string.lintcode;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/10/22
 **/
public class StringTest03 {
    public static void main(String[] args) {
        StringTest03 s = new StringTest03();
        System.out.println(s.firstUniqChar("lovelintcode"));

    }


    /**
     * 合并区间
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        intervals.sort(Comparator.comparingInt(o -> o.start));
        List<Interval> results = new ArrayList<>();

        // write your code here
        if (intervals.isEmpty()) {
            return results;
        }

        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if(isContain(prev,current)){
                prev = mergeTwoIntervals(prev, current);
            }else {
                results.add(prev);
                prev = current;
            }
        }
        results.add(prev);
        return results;
    }

    /**
     * 是否有交集
     * @param left
     * @param right
     * @return
     */
    public boolean isContain(Interval left, Interval right) {
        int rightMin = Math.min(left.end, right.end);
        int leftMax = Math.max(left.start, right.start);
        return rightMin >= leftMax;
    }

    /**
     * 合并区间
     * @param left
     * @param right
     * @return
     */
    public Interval mergeTwoIntervals(Interval left, Interval right){
        return new Interval(Math.min(left.start, right.start), Math.max(right.end, left.end));
    }

    /**
     * 30. 插入区间
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // write your code here
        intervals.add(newInterval);
        return merge(intervals);
    }

    /**
     * 646. 第一个独特字符位置
     *
     */
    public int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            Integer count = map.get(aChar);
            if (count == null) {
                map.put(aChar, 1);
            }else {
                map.put(aChar, ++count);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }


}

class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" +
                 start +
                ", " + end +
                ']';
    }
}
