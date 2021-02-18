package org.algorithm.leetcode.specified.sweep_line;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/11/8
 * 线性扫描
 */
public class SweepLineTest {
	public static void main(String[] args) {
		SweepLineTest s = new SweepLineTest();
		System.out.println(s.countOfAirplanes(new ArrayList<>(Arrays
				.asList(new Interval(1, 10), new Interval(2, 3), new Interval(3, 4)))));
	}
	/**
	 *给出飞机的起飞和降落时间的列表，用序列 interval 表示. 请计算出天上同时最多有多少架飞机？
	 * 输入: [(1, 10), (2, 3), (5, 8), (4, 7)]
	 * 输出: 3
	 * 解释:
	 * 第一架飞机在1时刻起飞, 10时刻降落.
	 * 第二架飞机在2时刻起飞, 3时刻降落.
	 * 第三架飞机在5时刻起飞, 8时刻降落.
	 * 第四架飞机在4时刻起飞, 7时刻降落.
	 * 在5时刻到6时刻之间, 天空中有三架飞机.
	 * @param airplanes
	 * @return
	 */
	public int countOfAirplanes(List<Interval> airplanes) {
		// write your code here
		List<Point> points = new ArrayList<>();
		for (Interval interval : airplanes) {
			points.add(new Point(interval.start, 1));
			points.add(new Point(interval.end, 0));
		}
		points.sort((p1,p2)->{
			if (p1.getTakeOffTime() == p2.getTakeOffTime()) {
				return p1.isTakeOff - p2.isTakeOff;

			}else {
				return p1.getTakeOffTime() - p2.getTakeOffTime();
			}
		});
		int count = 0;
		int res = 1;
		for (Point point : points) {
			if (point.isTakeOff==1) {
				count++;
			}else {
				count--;
			}
			res = Math.max(res, count);
		}
		return res;
	}
}
class Point{
	int takeOffTime;
	int isTakeOff;

	public Point(int takeOffTime, int isTakeOff) {
		this.takeOffTime = takeOffTime;
		this.isTakeOff = isTakeOff;
	}

	public int getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(int takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public  int isTakeOff() {
		return isTakeOff;
	}

	public void setTakeOff(int isTakeOff ) {
		isTakeOff = isTakeOff;
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
