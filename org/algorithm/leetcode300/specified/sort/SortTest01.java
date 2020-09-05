package org.algorithm.leetcode300.specified.sort;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/7/29
 **/
public class SortTest01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 8, 9, 5, 3, 4, 0, 6, 7};
        //quickSort(arr, 0, arr.length - 1);

//        SortTest01 s = new SortTest01();
//        s.mergeSort(arr, 0, arr.length - 1, new int[arr.length]);


        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 快速排序
     * 理想情况下
     *
     * @param nums
     * @param start
     * @param end
     */
    public static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        //1 注意pivot选取最好中间
        int pivot = nums[(left + end) / 2];
        //2 left <= right 而不是 lef t< right
        while (left-1 < right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        //left 交错了了
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }

    public void mergeSort(int[] arr, int start, int end, int[] tmp) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid, tmp);
        mergeSort(arr, mid + 1, end, tmp);
        merge(arr, start, end, tmp);
    }

    private void merge(int[] arr, int start, int end, int[] tmp) {
        int i = start;
        int mid = (start + end) / 2;
        int j = mid + 1;
        int index = start;
        while (j <= end && i <= mid) {
            if (arr[i] >= arr[j]) {
                tmp[index++] = arr[j++];
            } else {
                tmp[index++] = arr[i++];
            }
        }
        while (i <= mid) {
            tmp[index++] = arr[i++];
        }
        while (j <= end) {
            tmp[index++] = arr[j++];
        }
        for (int i1 = start; i1 <= end; i1++) {
            arr[i1] = tmp[i1];
        }


    }


}
