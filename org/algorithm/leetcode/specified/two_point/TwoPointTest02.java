package org.algorithm.leetcode.specified.two_point;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2020/7/29
 **/
public class TwoPointTest02 {

    public static void main(String[] args) {
        TwoPointTest02 t = new TwoPointTest02();
        int[] nums ={0,2,2,2,2,1,0,1,0,0,0,1,0,2,0,1};
        System.out.println(Arrays.toString(nums));
        t.sortColors2(nums);
        System.out.println(Arrays.toString(nums));
        int i = t.findKthLargest(new int[]{3,2,1}, 0, 2, 2);
        System.out.println(i);
//        int[] arr = {3,5,4,6,1,2,4};
        //t.quickSort2(arr, 4);
    }

    /**
     * 划分数组，以k为界限
     * @param nums
     * @param k
     * @return
     */
    public int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            //end 如果等于k 那么会把所有比k小得找出来,(start左边的一段)，然后大于或等于k的另一段无序（start另一段，包含start）
            //0---end小于， start----length大于等于
            while (start <= end && nums[end] >= k) {
                end--;
            }
            //start 如果等于k 那么会把所有比k大的找出来,(start右边的一段，包含start)
            // 0---end小于等于， start----length大于
            while (start <= end && nums[start] < k) {
                start++;
            }
            //如果都等于k，那么狠混乱，start左边右边都有等于k的，0---end小于等于 start----length大于等于
            if (start <= end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                start++;
                end--;
            }
        }
        return start;
    }

    /**
     * 寻找倒数k个最大值 k从1开始
     * 第k大元素
     * @param n
     * @param nums
     * @return
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return findKthLargest(nums, 0, nums.length - 1, n);
    }

    /**
     * 从大到小快速排序
     */
    private int findKthLargest(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int i = start;
        int j = end;
        int pivot = nums[(i + j) / 2];
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            if (i <= j) {
                int tmp = nums[i];
                nums[i] = nums[ j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }
        // 如果取第一个  那么就是satrt(0) +k(1) - 1 = 下标为0
        if (start + k - 1 <= j) {
            return findKthLargest(nums, start, j, k);
        }
        //去掉了左边  那么就不看了 左边有 left -satr 个数
        //假设 start = 1  left = 2，就是start -left =1  ，1个不要了
        if (start + k - 1 >= i) {
            return findKthLargest(nums, i, end, k - (i - start));
        }
        return nums[j + 1];
    }


    /**
     * 按照红白蓝排序
     * 我们可以使用整数 0，1 和 2 分别代表红，白，蓝。
     *
     * 输入 : [1, 0, 1, 2]
     * 输出 : [0, 1, 1, 2]
     *
     * 思路1：先把小于1的挪到最左边，再把小于2的挪动一次 就好
     * 这里如果 nums[start] <= 1 ，nums[end] > 1 那么会让所有比1大的2先放在一起 start作为2的头
     * 这里如果 nums[start] < 1 ，nums[end] >= 1 那么会让所有所有比1小的0先放在一起 start作为1的头
     * 这里如果 nums[start] <= 1 ，nums[end] >= 1 那么会让所有的0先放在一起 end作为1的头
     *
     * 思路2：如果不用挪两次
     * @param nums
     */
    public void sortColors(int[] nums) {
        // write your code here
        int start = 0;
        int end = nums.length - 1;
        //因为这里取start==1 ，所以就是找到右边所有【只大于】
        while (start <= end) {
            while (start <= end && nums[start] <= 1) {
                start++;
            }
            while (start <= end && nums[end] > 1) {
                end--;
            }
            if (start <= end) {
                int tmp = nums[start];
                nums[start] = nums[ end];
                nums[end] = tmp;
                start++;
                end--;
            }
        }

        int start2 = 0;
        int end2 = start-1;
        while (start2 <= end2) {
            while (start2 <= end2 && nums[start2] < 1) {
                start2++;
            }
            while (start2 <= end2 && nums[end2] >= 1) {
                end2--;
            }
            if (start2 <= end2) {
                int tmp = nums[start2];
                nums[start2] = nums[ end2];
                nums[end2] = tmp;
                start2++;
                end2--;
            }
        }
    }


    /**
     *思路2：两个指针 l左边包括全是0 ，r右边全是2，中间就全是1
     * 三指针维护
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int index = 0;
        int l = 0;
        int r = nums.length - 1;
        while (index <= r) {
            if (nums[index] == 0) {
                swap(nums, index, l);
                l++;
                index++;
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, index, r);
                r--;
            }
        }
    }

    private void swap(int[] nums, int l, int i) {
        int tmp = nums[l];
        nums[l] = nums[i];
        nums[i] = tmp;
    }

    private void quickSort2(int[] nodes, int pivot) {
        int start = 0;
        int end = nodes.length - 1;
        int index = 0;
        while (index  != end) {
            if (nodes[index] < pivot) {
                swap(nodes, start, index);
                start++;
                index++;
            } else if (nodes[index] > pivot) {
                swap(nodes, end, index);
                end--;
            }else {
                index++;
            }

        }
    }


}
