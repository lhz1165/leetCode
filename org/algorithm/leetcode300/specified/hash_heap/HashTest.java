package org.algorithm.leetcode300.specified.hash_heap;

/**
 * @author lhzlhz
 * @create 2020/11/5
 */
public class HashTest {
	public static void main(String[] args) {
		HashTest h = new HashTest();
		h.trapRainWater(new int[]{3,0,1,4,1,2});
	}

	public int trapRainWater(int[] heights) {
		// write your code here
		int n = heights.length;
		if (n == 0) {
			return 0;
		}
		int res = 0;
		int left = 0;
		int right = n - 1;
		int leftH = heights[left];
		int rightH = heights[right];
		while (left < right) {
			if (leftH < rightH) {
				left++;
				if (leftH > heights[left]) {
					res += (leftH - heights[left]);
				}else {
					leftH = heights[left];
				}
			}else {
				right--;
				if (rightH > heights[right]) {
					res += rightH - heights[right];
				}else {
					rightH = heights[right];
				}
			}

		}
		return res;
	}
}
