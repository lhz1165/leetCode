package org.algorithm.leetcode.specified.sort;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/7/29
 **/
public class SortTest01 {
    public static void main(String[] args) {
        int[] arr = {1,2};
        quickSort(arr, 0, arr.length - 1);

        SortTest01 s = new SortTest01();
//        s.mergeSort(arr, 0, arr.length - 1, new int[arr.length]);


       // quickSort22(arr, 3);
        System.out.println(Arrays.toString(arr));


    }

    /**
     * 快速排序
     * 理想情况下
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        //1 注意pivot选取最好中间
        int pivot = arr[(left + end) / 2];
        //2 left <= right 而不是 lef t< right
        while (left < right) {
            //3  arr[left] < pivot 而不是 arr[left] <= pivot
            while (left <= right && arr[left] < pivot) {
                left++;
            }
            while (left <= right && arr[right] > pivot) {
                right--;
            }
            if (left < right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }

        //left 交错了了
        quickSort(arr, start, right);
         quickSort(arr, left, end);
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

    /**
     * index 必须保证index左边的小与或等于pivot
     * left
     * right
     * @param nums
     * @param pivot
     */
    public static void quickSort22(int[] nums, int pivot) {
        int index = 0;
        int left = 0;
        int right = nums.length - 1;
        while (index-1 < right) {
            if (nums[index] < pivot) {
                swap(nums, left, index);
                left++;
                index++;
            } else if (nums[index] > pivot) {
                swap(nums, index, right);
                right--;
            } else {
                index++;
            }

        }
        System.out.println();

    }



    public static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}
