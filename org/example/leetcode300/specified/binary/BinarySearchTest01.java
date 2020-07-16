package org.example.leetcode300.specified.binary;

/**
 * @author lhzlhz
 * @create 2020/7/16
 */
public class BinarySearchTest01 {
	public static void main(String[] args) {
		int[] a = {2, 2, 2, 2, 2, 2};
	}

	/**
	 * 峰值 随便找一个
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
}
