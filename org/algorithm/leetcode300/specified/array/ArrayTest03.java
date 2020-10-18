package org.algorithm.leetcode300.specified.array;

import com.sun.glass.ui.Size;
import sun.security.provider.Sun;

import java.util.*;

/**
 * @author lhzlhz
 * @create 2020/10/17
 */
public class ArrayTest03 {


	/**
	 * 641. 丢失的间隔
	 * 输入：
	 * nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
	 * 输出：
	 * ["2", "4->49", "51->74", "76->99"]
	 */
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		// write your code here
		List<String> results = new ArrayList<>();
		if (nums.length == 0) {
			getString(results, lower, upper);
			return results;
		}

		getString(results, lower + 1, nums[0]);
		for (int i = 1; i < nums.length; i++) {
			getString(results, (long) nums[i - 1] + 1, (long) nums[i] - 1);
		}
		getString(results, nums[nums.length - 1] + 1, upper);
		return results;
	}


	private void getString(List<String> results, long st, long ed) {
		if (st > ed) {
			return;
		}
		if (st == ed) {
			results.add(st + "");
			return;
		}
		results.add(st + "->" + ed);
	}



	public static void main(String[] args) {
		ArrayTest03 a = new ArrayTest03();
		Interval i1 = new Interval(1, 3);
		Interval i2 = new Interval(2, 2);
		Interval i3 = new Interval(4, 6);
		Interval i4 = new Interval(5, 7);
		System.out.println(a.merge(new ArrayList<>(Arrays.asList(i1, i2, i3, i4))));
	}
	/**
	 *156. 合并区间
	 * 	 * 思路二类似于桶排序
	 * 	 * (1,3),(2,6),(8,10),(15,18)
	 * 	 * 1 -18 的boolean数组
	 * 	 * 1到3标记 true
	 * 	 * 2到6标记 true
	 * 	 * 最后查看结果
	 *
	 * 	 * 思路二 直接合并（本题采用这个）
	 * 输入:  [(1,3),(2,6),(8,10),(15,18)]
	 * 输出: [(1,6),(8,10),(15,18)]
	 */
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> results = new ArrayList<>();
		intervals.sort(Comparator.comparingInt(o -> o.start));
		// write your code here
		if (intervals.isEmpty()) {
			return results;
		}
		Interval prev = intervals.get(0);
		results.add(prev);
		int prevTail = intervals.get(0).end;
		for (int i = 1; i < intervals.size(); i++) {
			if (prevTail >= intervals.get(i).start && intervals.get(i).end > prevTail) {
				prev.end = intervals.get(i).end;
				prevTail = prev.end;
			} else if (intervals.get(i).end > prevTail) {
				prev = intervals.get(i);
				prevTail = intervals.get(i).end;
				results.add(prev);
			}
		}
		return results;
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
		return
				"[" + start +
						", " + end +
						']';
	}
}

/**
 * 642. 数据流滑动窗口平均值
 */
class MovingAverage {
	public static void main(String[] args) {
		MovingAverage m = new MovingAverage(3);
		m.next(1);
		m.next(10);
		m.next(3);
		System.out.println(m.next(5));

	}

	private Queue<Integer> queue;
	private double sum = 0;
	private int size;

	/**
	 * Initialize your data structure here.
	 */
	public MovingAverage(int size) {
		queue = new LinkedList<Integer>();
		this.size = size;
	}

	public double next(int val) {
		sum += val;
		if (queue.size() == size) {
			sum = sum - queue.poll();
		}
		queue.offer(val);
		return sum / queue.size();
	}
}
