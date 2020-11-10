package org.algorithm.leetcode300.specified.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author lhzlhz
 * @create 2020/11/8
 */
public class ArrayTest04 {
    public static void main(String[] args) {
        ArrayTest04 a = new ArrayTest04();
        int[] aa = {5,4,7,5,3,2};
        a.nextPermutation(aa);
        System.out.println(Arrays.toString(aa));
    }

    /**
     * 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
     * 输入:
     * [1,2,7,7,8]
     * 3
     * 输出:
     * [7,7,8]
     * 方法一：两个for 遍历k个数组
     * 方法二：map 每次加一个 删一个
     */
    void inQueue(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offer(num);
    }

    void outQueue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }

    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>();
        if (nums.length == 0) {
            return ans;
        }
        for (int i = 0; i < k; i++) {
            inQueue(deque, nums[i]);
        }

        for (int i = k - 1; i < nums.length; i++) {
            //如果这个最大的那么移除
            inQueue(deque, nums[i]);
            //第一位永远存最大的
            ans.add(deque.peekFirst());

            //第一个是否不在窗口里面了
            outQueue(deque, nums[i - k + 1]);
        }
        return ans;
    }

    /**
     * 出这个数组排序出的所有数中，刚好比当前数大的那个数
     * 比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132
     *
     * 158476531
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


}
