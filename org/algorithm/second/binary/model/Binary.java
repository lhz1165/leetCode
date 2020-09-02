package org.algorithm.second.binary.model;

/**
 * @author lhzlhz
 * @create 2020/8/23
 */
public class Binary {
	public static void main(String[] args) {
		Binary binary = new Binary();
		int[] a ={5,5,5,5,5,5,5,5,5,5};
		int[] ints = binary.searchRange(a, 7);
		System.out.println();

	}
	public int binaryLeft(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		while (start + 1 < end) {
			int mid = (end + start) / 2;
			if (target == nums[mid]) {
				//往左边压缩就是早第一个
				end = mid;
				//往右边压缩就是找最后一个
				//start=mid;
			} else if (target > nums[mid]) {
				start = mid;
			} else if (target < nums[mid]) {
				end = mid;
			}
		}
		/**
		 * 寻找最左边的值 那么肯定先从start开始找
		 */
		if (nums[start] == target) {
			return start;
		}
		/**
		 * 寻找最右边的值 那么肯定先从end开始找
		 */
		if (nums[end] == target) {
			return end;
		}
		return -1;

	}

	/**
	 * 给定一个包含 n 个整数的排序数组，找出给定目标值 target 的起始和结束位置。
	 *
	 * 如果目标值不在数组中，则返回[-1, -1]
	 * @param A
	 * @param target
	 * @return
	 */
	public int[] searchRange(int[] A, int target) {
		// write your code here
		if (A.length == 0) {
			return new int[]{-1,-1};
		}

		int left=-1;
		int start = 0 ;
		int end = A.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (target == A[mid]) {
				end = mid;
			} else if (target > A[mid]) {
				start = mid;
			} else if (target < A[mid]) {
				end = mid;
			}
		}
		if (A[start] == target) {
			left =start;
		}
		/**
		 * 寻找最右边的值 那么肯定先从end开始找
		 */
		if (left==-1&&A[end] == target) {
			left =end;
		}
		int start2 = 0 ;
		int end2 = A.length - 1;
		int right=-1;
		while (start2 + 1 < end2) {
			int mid = start2 + (end2 - start2) / 2;
			if (target == A[mid]) {
				start2 = mid;
			} else if (target > A[mid]) {
				start2 = mid;
			} else if (target < A[mid]) {
				end2 = mid;
			}
		}


		if (A[end2] == target) {
			right =end2;
		}
		if (right==-1&&A[start2] == target) {
			right =start2;
		}
		int[] ints = new int[2];
		ints[0] = left;
		ints[1] =right;
		return ints;
	}


}
