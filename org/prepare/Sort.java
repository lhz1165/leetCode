package org.prepare;

import java.util.Arrays;

/**
 * @author lhzlhz
 * @create 4/11/2021
 */
public class Sort {
	public static void main(String[] args) {
		Sort s = new Sort();
		int[] arr = {10, 5, 8, 1, 3, 6, 4, 7, 12, 50, 90, 44};
		s.quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));


	}

	public void quickSort(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}

		int left = start;
		int right = end;
		int pivot = arr[(start + end) / 2];

		while (left <= right) {
			while (left <= right && arr[left] < pivot) {
				left++;
			}
			while (left <= right && arr[right] > pivot) {
				right--;
			}
			if (left <= right) {
				int tmp = arr[left];
				arr[left] = arr[right];
				arr[right] = tmp;
				right--;
				left++;
			}
			quickSort(arr, start, right);
			quickSort(arr, left, end);
		}







	}

}
