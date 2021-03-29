package org.algorithm.leetcode.specified.array;

import java.util.*;
import java.util.concurrent.locks.Lock;

/**
 * @author lhzlhz
 * @create 2020/11/8
 */
public class ArrayTest04 {
    public static void main(String[] args) {
        ArrayTest04 a = new ArrayTest04();
//        int[] aa = {5,4,7,5,3,2};
//        a.nextPermutation(aa);
//        System.out.println(Arrays.toString(aa));
        //[1,3,1,2,0,5]
        //3
        //a.maxSlidingWindow(new int[]{1,3,1,2,0,5},3);
        a.findMedianSortedArrays2(new int[]{1,4,5,6,7}, new int[]{2, 3,8,9});
        System.out.println();
    }

    /**
     * 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
     * 输入:
     * [1,3,-1,-3,5,3,6,7]
     * 3
     * 输出:
     * [3,3,5,5,6,7]
     * 方法一：两个for 遍历k个数组
     * 方法二：双向队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        int[] results = new int[nums.length-k+1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {

            while (!deque.isEmpty() &&nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);

            if (i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }

            if (i >= k-1 ) {
                results[index++] = nums[deque.peekFirst()];
            }


        }
        return results;



    }

    /**
     * 出这个数组排序出的所有数中，刚好比当前数大的那个数
     * 比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132
     *
     * 1 5 8 4 7 6 5 3 1
     *先从后往前找一个不不上升序列的 1 3 5 6 7【4】
     * 在从后往前 找一个比4大的最小数 1 3 【5】 6 7
     * 交换 1 5 8 【5】 7 6 4 3 1
     * 再把【5】后面的反转
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int n = nums.length;
        int start = -1;
        for (int i = n-1; i > 0 ; --i) {
            if (nums[i] > nums[i - 1]) {
                start = i-1;
                break;
            }
        }
        if (start != -1)  {
            int end = n;
            int pivot = nums[start];
            int gap = Integer.MAX_VALUE;
            for (int i = start+1; i < n; i++) {
                if (nums[i] > pivot&&nums[i] - pivot <= gap) {
                    end = i;
                    gap = nums[i] - pivot;
                }
            }
            swap(start, end, nums);
        }
        int left = start+1;
        int right = n - 1;
        while (left < right) {
            swap(left++,right--,nums);

        }

    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int prevVal = -1;
        int curVal = -1;
        int p1 = -1;
        int p2 = -1;
        int step = (nums1.length + nums2.length + 1)/2;
        for(int i = 0; i < step; i++){
            if(p1 + 1 >= nums1.length  &&   p1 < nums1.length && p2 < nums2.length){
                if(nums2[p2] > nums1[p1]){
                    curVal = nums1[p1++];
                }else{
                    curVal = nums2[p2++];
                }
            }
        }
        return (step - 1) / 2 == 0 ? (double) (prevVal + curVal) / 2 : curVal;
    }


    public double findMedianSortedArrays(int A[], int B[]) {
        int n = A.length + B.length;

        if (n % 2 == 0) {
            return (findKth(A, 0, B, 0, n / 2) + findKth(A, 0, B, 0, n / 2 + 1)) / 2.0;
        }

        return findKth(A, 0, B, 0, n / 2 + 1);
    }

    // 找到两个数组中的第k个数
    public  int findKth(int[] A, int startOfA, int[] B, int startOfB, int k){
        if (startOfA >= A.length) {
            return B[startOfB + k - 1];
        }
        if (startOfB >= B.length) {
            return A[startOfA + k - 1];
        }

        if (k == 1) {
            return Math.min(A[startOfA], B[startOfB]);
        }

        int halfKthOfA = startOfA + (k / 2) - 1 < A.length ? A[startOfA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfKthOfB = startOfB + (k / 2) - 1 < B.length ? B[startOfB + k / 2 - 1] : Integer.MAX_VALUE;

        if (halfKthOfA < halfKthOfB) {
            return findKth(A, startOfA + k / 2, B, startOfB, k - k / 2);
        } else {
            return findKth(A, startOfA, B, startOfB + k / 2, k - k / 2);
        }
    }


}
