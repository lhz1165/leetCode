package org.algorithm.leetcode300.specified.binary;

import java.util.Arrays;

/**
 * @author lhzlhz
 * @create 2020/7/16
 */
public class BinarySearchTest01 {
	public static void main(String[] args) {
		int[] a = {8,9,1,2,3,4,5};
		System.out.println(findMin(a));
	}

	/**
	 * 众多峰值 随便找一个
	 * @param A
	 * @return
	 */
	public int findPeak(int[] A) {
		// write your code here
		if (A == null || A.length < 3) {
			return -1;
		}
		int start = 0;
		int end = A.length - 1;

		while (start + 1 < end) {
			int mid = (start + end) / 2;
			//上峰
			if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) {
				return mid;
				//谷底
			} else if ((A[mid] < A[mid + 1] && A[mid] < A[mid - 1])) {
				start = mid;
				//下降
			}else if ((A[mid] > A[mid + 1] && A[mid] < A[mid - 1])){
				end = mid;
				//上升
			}else if ((A[mid] < A[mid + 1] && A[mid] > A[mid - 1])){
				start = mid;
			}
		}
		return -1;
	}

	/**
	 * 一个峰值 找出来
	 * @param A
	 * @return
	 */
	public static int peakIndexInMountainArray(int[] A) {
		// Write your code here
		int start = 0;
		int end = A.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			//下坡
			if (A[mid] > A[mid + 1] && A[mid] < A[mid - 1]) {
				end = mid;
				//上坡
			}else if (A[mid] < A[mid + 1] && A[mid] > A[mid - 1]){
				start = mid;
			} else if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]){
				return   mid;
			}
		}
		return A[start] > A[end] ? start : end;



	}


	/**
	 *
	 * 假设一个排好序的数组在其某一未知点发生了旋转（比如0 1 2 4 5 6 7 可能变成4 5 6 7 0 1 2）。你需要找到其中最小的元素。
     * ****
	 * @param numbers: a rotated sorted array
	 * @return: the minimum number in the array
	 */
	public static int findMin(int[] numbers) {
        //Arrays.sort(numbers);
		// write your code here
		int start = 0;
		int end = numbers.length - 1;

		// find the first element <= target
		while (start + 1 < end) {			//用来控制区间大小
			int mid = start + (end - start) / 2;
			if (numbers[mid] < numbers[end]) { 		//如果mid位置上的数字小于等于最右端的数字时，区间向左移动
				end = mid;
			} else if(numbers[mid] > numbers[end]) {
				start = mid;
			}else {
                end--;
            }
		}
		return Math.min(numbers[start],numbers[end]);
	}

	/**
	 * 33. 搜索旋转排序数组[important]***
	 *    /
	 *   /
	 *  /
	 * s -------e----------
	 *         /
	 *        /
	 *       /
	 *
	 *            |-----target在mid左边（缩小右边界）
	 * mid在左边 --|
	 *            |-----target在mid右边（缩小左边界）
	 *
	 *            |-----target在mid左边（缩小右边界）
	 * mid在右边 --|
	 *            |-----target在mid右边（缩小左边界）
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int search(int[] nums, int target) {
		if (null == nums || nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] > nums[start]) {
				if (target <= nums[mid]&&target>=nums[start]) {
					//缩小有边界
					end = mid;
				}else {
					start = mid;
				}
			} else if (nums[mid] < nums[end]) {
				if (target <= nums[end] && target >= nums[mid]) {
					start = mid;
				}else {
					end = mid;
				}
			}

		}
		if (nums[start] == target) {
			return start;
		}
		if (nums[end] == target) {
			return end;
		}
		return -1;


	}


}
