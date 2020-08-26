package org.algorithm.second.binary.model;

/**
 * @author lhzlhz
 * @create 2020/8/23
 */
public class Binary {
    public static void main(String[] args) {
        Binary binary = new Binary();
        int[] arr1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2};
        int[] arr2 = {1, 2, 2, 2, 2, 2, 2};
        System.out.println(binary.binaryLeft(arr1, 1));
        System.out.println(binary.binaryLeft(arr2, 2));

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


}
