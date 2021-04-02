package org.algorithm.leetcode.specified.hash_heap;

/**
 * @author lhzlhz
 * @create 2020/11/5
 */
public class HashTest {
	public static void main(String[] args) {
		HashTest h = new HashTest();
		h.trapRainWater(new int[]{3,0,1,4,1,2});
	}

	public int trapRainWater(int[] height) {
		// write your code here
		int n = height.length;
		if (n == 0) {
			return 0;
		}
		int res = 0;
		int left = 0;
		int right = n - 1;
		int leftH = height[left];
		int rightH = height[right];
		while (left < right) {
			if (leftH < rightH) {
				left++;
				if (leftH > height[left]) {
					res += (leftH - height[left]);
				}else {
					leftH = height[left];
				}
			}else {
				right--;
				if (rightH > height[right]) {
					res += rightH - height[right];
				}else {
					rightH = height[right];
				}
			}

		}
		return res;
	}
}
