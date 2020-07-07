package org.example.leetcode300.specified.binary;

/**
 * @author lhzlhz
 * @create 2020/7/7
 */
public class BinaryModel {


	/**
	 * 二分法模板
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int binarySearch(int[] nums,int target){
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int start = 0, end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target){
				end = mid; //左边界
				//or start = mid //右边界
			} else if (nums[mid] < target) {
				start = mid;
				//or  = mid + 1;
			} else {
				end = mid;
				//or =mid -1 ;
			}
		}
		//找左边界 和 end = mid 配合使用
		if (nums[start] == target) {
			return start;
		}
		//找有边界 和 start = mid 配合使用
		if (nums[end] == target) {
			return end;
		}
		return -1;

	}
}
