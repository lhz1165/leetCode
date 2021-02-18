package org.algorithm.leetcode.specified.string.lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lhzlhz
 * @create 2020/12/19
 */
public class MovingAverage {
	public static void main(String[] args) {
		MovingAverage m = new MovingAverage(3);
		System.out.println(m.next(1));
		System.out.println(m.next(10));
		System.out.println(m.next(3));
		System.out.println(m.next(5));

	}

	Queue<Integer> queue ;
	int size;
	double sum ;
	public MovingAverage(int size) {
		queue = new LinkedList<>();
		this.size = size;
		sum = 0;
	}

	public double next(int val) {
		queue.offer(val);
		sum += val;
		if (queue.size() <= size) {
			return ( sum) / ((double)queue.size());
		}else {
			sum -= queue.poll();
			return ( sum) / ((double) size);
		}


	}
}
