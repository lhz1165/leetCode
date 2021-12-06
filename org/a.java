package org;



/**
 * @author lhzlhz
 * @create 2021/8/22
 */
public class a {
	public static void main(String[] args) {
		System.out.println(new a().find(new int[]{90, 3, 4, 5, 7, 8, 10}));
	}


	public int find(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		int p = arr[arr.length - 1];
		while (left + 1 < right) {
			int mid = (right + left) / 2;
			if (arr[mid] < p) {
				right = mid;
			} else if (arr[mid] > p) {
				left = mid;
			}
		}
		return  arr[right];

	}




}
