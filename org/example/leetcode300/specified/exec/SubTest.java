package org.example.leetcode300.specified.exec;

import java.util.Arrays;

/**
 * @author lhzlhz
 * @create 2020/8/3
 * 关键算法小练习
 */
public class SubTest {

	/**
	 * 归并排序
	 * @param arr
	 */
	public void mergeSort(int[] arr) {
		//用来存放临时数值的
		int[] temp = new int[arr.length];
		subMergeSort(arr, 0, arr.length - 1,temp);
	}

	/**
	 * 归
	 * divided
	 * @param arr
	 * @param start
	 * @param end
	 */
	public void subMergeSort(int[] arr, int start, int end,int[] temp) {
		if (start >= end) {
			return;
		}
		int middle = (start + end) / 2;
		subMergeSort(arr, start, middle, temp);
		subMergeSort(arr, middle+1,end , temp);
		merge(arr,start,end,temp);

	}
	/**
	 * 并
	 * concord
	 * @param A
	 * @param start
	 * @param end
	 */
	public void merge(int[] A, int start, int end,int[] temp) {
		int middle = (start + end) / 2;
		int leftIndex = start;
		int rightIndex = middle + 1;
		int tmpIndex = start;
		while (leftIndex <= middle && rightIndex <= end) {
			if (A[leftIndex] < A[rightIndex]) {
				temp[tmpIndex] = A[leftIndex];
				tmpIndex++;
				leftIndex++;
			}else {
				temp[tmpIndex] = A[rightIndex];
				tmpIndex++;
				rightIndex++;
			}
		}
		while (leftIndex <= middle) {
			temp[tmpIndex] = A[leftIndex];
			tmpIndex++;
			leftIndex++;
		}
		while (rightIndex <= end) {
			temp[tmpIndex] = A[rightIndex];
			tmpIndex++;
			rightIndex++;
		}
		for (int i = start; i <= end; i++) {
			A[i] = temp[i];
		}

	}
	//----------------------------------------------------


	/**
	 * 快速排序
	 * @param arr
	 */
	public void quickSort(int[] arr) {

		subQuick(arr, 0, arr.length-1);

	}


	private void subQuick(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}

		int l = start;
		int r = end;
		int piv = arr[(start + end) / 2];
		while (l <= r) {
			while (l <= r && piv < arr[r]) {
				r--;
			}
			while (l <= r && piv > arr[l]) {
				l++;
			}
			if (l <= r) {
				int tmp = arr[l];
				arr[l] = arr[r];
				arr[r] = tmp;
				l++;
				r--;
			}
		}
		subQuick(arr, start, r);
		subQuick(arr, l, end);
	}

	public int[] runningSum(int[] nums) {
		int[] result = new int[nums.length];
		result[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			result[i] = nums[i] + result[i - 1];
		}
		return result;
	}


	public static void main(String[] args) {
		SubTest sb = new SubTest();
		int[] nums = {1, 2, 3, 4};
		sb.runningSum(nums);

	}


}
