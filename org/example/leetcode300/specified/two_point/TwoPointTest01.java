package org.example.leetcode300.specified.two_point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

/**
 * @author: lhz
 * @date: 2020/7/27
 **/
public class TwoPointTest01 {

    public static void main(String[] args) {
        TwoPointTest01 t = new TwoPointTest01();
        int[] arr = {-5,-3,-2,1,2,2,3,4,9};
        System.out.println(t.threeSumClosest(arr, 1));

    }

    /**
     * easy 双指针
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int head = 0;
        int tail = k - 1;
        int[] results = new int[nums.length - k + 1];
        int index = 0;
        while (tail < nums.length) {
            int sum = 0;
            for (int i = head; i <= tail; i++) {
                sum += nums[i];
            }
            results[index] = sum;
            index++;
            head++;
            tail++;
        }
        return results;
    }

    /**
     * easy 双指针
     * 给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        // write your code here
        int[] result = new int[nums.length];
        int head = 0;
        int tail = nums.length - 1;

        int index = 0;
        while (index < nums.length) {
            if (nums[index] != 0) {
                result[head] = nums[index];
                index++;
                head++;
                continue;
            }
            index++;
            tail--;
        }
        for (int i = 0; i < result.length; i++) {
            nums[i] = result[i];
        }
    }

    /**
     * 两数之和
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        // write your code here
        Pair[] pairs = getPairs(numbers);
        int[] result = {-1, -1};
        int head = 0;
        int tail = numbers.length - 1;

        while (head < tail) {
            if (pairs[head].number + pairs[tail].number < target) {
                head++;
            } else if (pairs[head].number + pairs[tail].number > target) {
                tail--;
            } else {
                result[0] = Math.min(pairs[head].index, pairs[tail].index);
                result[1] = Math.max(pairs[head].index, pairs[tail].index);
                return result;
            }
        }
        return result;


    }

    public static Pair[] getPairs(int[] nums) {
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        Arrays.sort(pairs, Comparator.comparingInt(o -> o.number));
        return pairs;
    }

    static class Pair {
        int number;
        int index;

        public Pair(int number, int index) {
            this.number = number;
            this.index = index;
        }
    }

    public static int[] twoSum2(int[] nums, int target) {
        // write your code here
        int[] result = {-1, -1};
        int head = 0;
        int tail = nums.length - 1;
        while (head < tail) {
            if (nums[head] + nums[tail] < target) {
                head++;
            } else if (nums[head] + nums[tail] > target) {
                tail--;
            } else {
                result[0] = head;
                result[1] = tail;
                return result;
            }

        }
        return result;

    }

    /**
     * 给定一个旋转排序数组，在原地恢复其排序。（升序）
     * [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
     *
     * @param nums
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) {
            return;
        }
        int tmp = nums.get(0);
        int head = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (tmp > nums.get(i)) {
                head = i;
                break;
            }
        }
        if (head != 0) {
            reverseArr(0, head - 1, nums);
            reverseArr(head, nums.size() - 1, nums);
            reverseArr(0, nums.size() - 1, nums);
        }
    }

    public void reverseArr(int start, int end, List<Integer> nums) {
        int l = start;
        int r = end;
        while (l < r) {
            int tmp = nums.get(l);
            nums.set(l, nums.get(r));
            nums.set(r, tmp);
            l++;
            r--;
        }
    }

    /**
     * 587. 两数之和 - 不同组成
     * 给一整数数组, 找到数组中有多少组 不同的元素对 有相同的和, 且和为给出的 target 值, 返回对数
     * <p>
     * 输入: nums = [1,1,2,45,46,46], target = 47
     * 输出: 2
     * 解释:
     * <p>
     * 1 + 46 = 47
     * 2 + 45 = 47
     *
     * @param nums
     * @param target
     * @return
     */
    public int twoSum6(int[] nums, int target) {
        // write your code here
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int result = 0;

        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                if (left != 0 && right != nums.length - 1 && nums[left] == nums[left - 1] && nums[right] == nums[right + 1]) {

                    left++;
                    right--;
                    continue;
                }
                left++;
                right--;
                result++;
            }
        }
        return result;
    }

    /**
     * 57. 三数之和
     * 中文English
     * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
     * <p>
     * 样例
     * 例1:
     * <p>
     * 输入:[2,7,11,15]
     * 输出:[]
     * 例2:
     * 输入:[-1,0,1,2,-1,-4]
     * 输出:[[-1, 0, 1],[-1, -1, 2]]
     *
     * @param numbers
     * @return
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (numbers == null || numbers.length == 0) {
            return results;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {

            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            //变成了两数之和
            int target = -numbers[i];

            int left = i + 1;
            int right = numbers.length - 1;

            while (left < right) {
                List<Integer> result = new ArrayList<>();
                if (numbers[left] + numbers[right] < target) {
                    left++;
                } else if (numbers[left] + numbers[right] > target) {
                    right--;
                } else {
                    result.add(numbers[i]);
                    result.add(numbers[left]);
                    result.add(numbers[right]);
                    results.add(result);
                    left++;
                    right--;
                    // skip duplicate pairs with the same left
                    while (left < right && numbers[left] == numbers[left - 1]) {
                        left++;
                    }

                    // skip duplicate pairs with the same right
                    while (left < right && numbers[right] == numbers[right + 1]) {
                        right--;
                    }

                }
            }
        }
        return results;
    }

    /**
     * 给定一个整数数组，在该数组中，寻找三个数，分别代表三角形三条边的长度，问，可以寻找到多少组这样的三个数来组成三角形？
     * [3, 4, 6, 7]
     * @param S
     * @return
     */
    public int triangleCount(int[] S) {
        // write your code here
       int result = 0;
        for (int i = 0; i < S.length; i++) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (S[left] + S[left] > S[i]) {
                    result += (right - left );
                    right--;
                }else {
                    left++;
                }
          }
        }
        return result;
    }

    /**
     * 两数和-小于或等于目标值
     * 给定一个整数数组，找出这个数组中有多少对的和是小于或等于目标值。返回对数
     * nums = [2, 7, 11, 15]
     * @param nums
     * @param target
     * @return
     */
    public int twoSum5(int[] nums, int target) {
        int ans = 0;
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            }else{
                ans+=(right-left);
                left++;
            }
        }
        return ans;
    }

    /**
     * 给一组整数，问能找出多少对整数，他们的和大于一个给定的目标值
     * @param nums
     * @param target
     * @return
     */
    public int twoSum21(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int ans = 0;
        int left = 0;
        int right = nums.length-1;
        Arrays.sort(nums);
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                right--;
            }else{
                ans+=(right-left);
                left++;
            }
        }
        return ans;
    }

    /**
     * 59. 最接近的三数之和
     * @param numbers
     * @param target
     * @return
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        int ans = Integer.MAX_VALUE;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                ans = Math.min(ans, Math.abs(target-sum));
                if (numbers[i] + numbers[left] + numbers[right] - target > 0) {
                    right--;
                }else{
                    left++;
                }
            }
        }
        return ans+target;
    }


}
