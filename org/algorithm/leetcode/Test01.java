package org.algorithm.leetcode;

import org.algorithm.leetcode.specified.OperationLog;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: lhz
 * @date: 2020/8/19
 **/
public class Test01 {
    public static void main(String[] args)  {
        int[] ints = {4,1};
        quickSort(ints, 0, ints.length-1);
        mergeSort(ints,0,ints.length-1,new int[ints.length]);
        System.out.println(Arrays.toString(ints));
    }


    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;

        int pivot = array[(left+right)/2];
        while (left <= right) {
            while (pivot > array[left] && left <= right) {
                left++;
            }
            while (pivot < array[right] && left <= right) {
                right--;
            }
            if (left <= right) {
                int tmp =array[left];
                array[left]=array[right];
                array[right]=tmp;
                left++;
                right--;
            }

        }
        quickSort(array,start,right);
        quickSort(array, left, end);
    }
    public static void mergeSort(int[] arr, int start, int end, int[] tmp) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid, tmp);
        mergeSort(arr, mid + 1, end, tmp);
        merge(arr, start, end, tmp);
    }

    private static void merge(int[] arr, int start, int end, int[] tmp) {
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
