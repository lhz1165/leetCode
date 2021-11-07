package org.prepare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lhzlhz
 * @create 5/11/2021
 */
public class TwoPoint {
	public static void main(String[] args) {
		TwoPoint twoPoint = new TwoPoint();
		int[] arr = new int[]{0, 0, 1, 0, 0, 1, 2, 2, 1, 1, 2, 0, 2, 1};
		twoPoint.sortColors(arr);
		System.out.println(Arrays.toString(arr));
	}


	public int deduplication(int[] nums) {
		// write your code here
		Arrays.sort(nums);
		int index = 0;
		for (int num : nums) {
			if (num == nums[index]) {
				continue;
			}
			index++;
			nums[index] = num;
		}
		return index + 1;
	}

	public void sortColors(int[] nums) {
		// write your code here
		if(null == nums || nums.length == 0){
			return;
		}
		//线把小于1 的移动到左边 大于等于1的移动到右边
		//然后把小于2 的移动到左边 ，大于2的移动到右边
		int l1 = 0;
		int r1 = nums.length - 1;
		while(l1 <= r1){
			while(l1 <= r1 && nums[l1] < 1){
				l1++;
			}
			while(l1 <= r1 && nums[r1] >= 1){
				r1--;
			}
			if(l1 <= r1){
				swap(nums,l1,r1) ;
				l1++;
				r1--;
			}
		}

		int l2 = 0;
		int r2 = nums.length - 1;
		while(l2 <= r2){
			while(l2 <= r2 && nums[l2] <= 1){
				l2++;
			}
			while(l2 <= r2 && nums[r2] > 1){
				r2--;
			}
			if(l2 <= r2){
				swap(nums,l2,r2) ;
				l2++;
				r2--;
			}
		}

	}
	private void swap(int[] nums, int l, int i) {
		int tmp = nums[l];
		nums[l] = nums[i];
		nums[i] = tmp;
	}


}
