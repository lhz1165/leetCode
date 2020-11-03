package org.algorithm.second.binary.model;

import java.util.Scanner;

/**
 * @author: lhz
 * @date: 2020/8/25
 **/
public class Binary2 {
    public static void main(String[] args) {
        Binary2 b = new Binary2();

        System.out.println(b.search(new int[]{1,2,3,4,5,9}, 9));


    }

    /**
     * 给 n 个整数的山脉数组，即先增后减的序列，找到山顶（最大值）
     * <p>
     * nums = [1, 2, 4, 8, 6, 3]
     * 8
     *
     * @param nums
     * @return
     */
    public int mountainSequence(int[] nums) {
        // write your code here
        if (nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else if (nums[mid] > nums[mid - 1]) {
                start = mid;
            } else if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return nums[mid];
            }
        }
        if (nums[start] > nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }

    /**
     * @param A
     * @return
     */
    public int findPeak(int[] A) {
        // write your code here
        if (A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;

        while (start + 2 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
                start = mid;
            } else if (A[mid] < A[mid - 1] && A[mid] > A[mid + 1]) {
                end = mid;
            } else if (A[mid] < A[mid - 1] && A[mid] < A[mid + 1]) {
                end = mid;
            }
        }
        int mid = (start + end) / 2;
        if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
            return mid;
        } else if (A[mid] < A[mid - 1] && A[mid] > A[mid + 1]) {
            return start;
        } else if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
            return end;
        } else {
            return end;
        }
    }

    /**
     * Search in Rotated Sorted Array
     *注意: 为了防止没有旋转 必须target>=headVal
     * @param A
     * @param target
     * @return
     */
    public int search(int[] A, int target) {
        if (A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;

        int headVal = A[start];
        while (start + 1 < end) {

            int mid = start + (end - start) / 2;

            //1 mid在左边
            if (A[mid] > headVal) {
                if (A[mid] == target) {
                    return mid;
                    //1.1 target在左边(target>headVal),并且A[mid] >= target，那么往左边压缩
                } else if (A[mid] > target && target>=headVal) {
                    end = mid;
                } else {
                    start = mid;
                }
                //2 mid在右边部分
            } else {
                if (A[mid] == target) {
                    return mid;
                } else if (A[mid] < target && target <=headVal) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }

    public int mySqrt(int x) {
        int l = 1;
        int r = x;
        int s = 0;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
             s = x / mid;
            if (s == mid) {
                return s;
            } else if (s > mid) {
                l = mid;
            }else {
                r = mid;
            }
        }
        if (l * l < x) {
            return l;
        }
        if (r * r < x) {
            return r;
        }
        return -1;

    }

}
