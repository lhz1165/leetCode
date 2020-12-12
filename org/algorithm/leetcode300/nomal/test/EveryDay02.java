package org.algorithm.leetcode300.nomal.test;

import java.time.temporal.TemporalUnit;
import java.util.*;

/**
 * @author: lhz
 * @date: 2020/12/1
 **/
public class EveryDay02 {


    public static void main(String[] args) {
        EveryDay02 e = new EveryDay02();
        //[5,5,10,20,5,5,5,5,5,5,5,5,5,10,5,5,20,5,20,5]
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * <p>
     * <p>
     * 思路两次二分，求最左边和最右边的值
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        //向左压缩
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            res[0] = start;
        } else if (nums[end] == target) {
            res[0] = end;
        } else {
            res[0] = -1;
        }

        start = 0;
        end = nums.length - 1;
        //向右压缩
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) {
            res[1] = end;
        } else if (nums[start] == target) {
            res[1] = start;
        } else {
            res[1] = -1;
        }
        return res;
    }

    /**
     * 204. 计数质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        boolean[] results = new boolean[n];
        //假设所有数字都是素数
        Arrays.fill(results, true);
        for (int i = 2; i < n; i++) {
            //素数的倍数不是素数
            if (results[i]) {
                for (int j = 2 * i; j < n; j = i + j) {
                    results[j] = false;
                }
            }
        }
        int count = 0;
        for (boolean result : results) {
            if (result) {
                count++;
            }
        }
        return count - 2;
    }

    /**
     * 659. 分割数组为连续子序列
     * 哈希表的键为子序列的 key = [最后一个数字]，值为最小堆，用于存储所有的子序列 value = [长度]，
     * 最小堆满足堆顶的元素是最小的，因此堆顶的元素即为最小的子序列长度。
     * 12334455
     * {key = 5,value = {3,5}}
     */
    public boolean isPossibleII(int[] nums) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                LinkedList<Integer> list = new LinkedList<>();
                map.put(num, list);
            }
            //如果前面有序列接着长度+1
            if (map.containsKey(num - 1)) {
                //把最短的取出来
                Integer minLen = map.get(num - 1)
                        .pollFirst();
                LinkedList<Integer> newList = map.get(num);
                newList.add(minLen + 1);

                //3->{1，3}  来了个 4
                if (map.get(num - 1)
                        .isEmpty()) {
                    map.remove(num - 1);
                }
            } else {
                //如果前面没有序列接着，新开一个序列，长度为1
                map.get(num)
                        .addFirst(1);
            }
        }

        for (Map.Entry<Integer, LinkedList<Integer>> entry : map.entrySet()) {
            for (Integer len : entry.getValue()) {
                if (len < 3) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 861. 翻转矩阵后的得分
     * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
     * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
     */
    public int matrixScore(int[][] A) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < A[i].length; j++) {
                    A[i][j] = A[i][j] ^ 1;
                }
            }

        }


        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (i == 0) {
                    if (A[i][j] == 1) {
                        map.put(j, 1);
                    } else {
                        map.put(j, 0);
                    }
                    continue;
                }
                if (A[i][j] == 1) {
                    map.put(j, map.get(j) + 1);
                }

            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer columnNum = entry.getKey();
            Integer numOfOne = entry.getValue();
            if (numOfOne * 2 < (A.length * 2) / 2) {
                for (int i = 0; i < A.length; i++) {
                    A[i][columnNum] = A[i][columnNum] ^ 1;
                }
            }
        }


        for (int i = 0; i < A.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < A[i].length; j++) {
                sb.append(A[i][j]);
            }
            res += Integer.parseInt(sb.toString(), 2);
        }
        return res;
    }

    /**
     842. 将数组拆分成斐波那契序列     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<Integer>();
        backtrack(list, S, S.length(), 0, 0, 0);
        return list;
    }

    public boolean backtrack(List<Integer> list, String S, int length, int index, int sum, int prev) {
        if (index == length) {
            return list.size() >= 3;
        }
        long currLong = 0;
        for (int i = index; i < length; i++) {
            if (i > index && S.charAt(index) == '0') {
                break;
            }
            currLong = currLong * 10 + S.charAt(i) - '0';
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            int curr = (int) currLong;
            if (list.size() >= 2) {
                if (curr < sum) {
                    continue;
                } else if (curr > sum) {
                    break;
                }
            }
            list.add(curr);
            if (backtrack(list, S, length, i + 1, prev + curr, curr)) {
                return true;
            } else {
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
    /**
     * 376. 摆动序列
     * *****
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n <2 ){
            return n;
        }
        //以n结尾的最长的上升和下降序列
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            int curNum = nums[i];
            int prevNum = nums[i - 1];
            //如果当前小，那处于下降，可以试着更新下降序列的值
            if(curNum < prevNum){
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
                up[i] = up[i - 1];
            }else if(curNum > prevNum){
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            }else{
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }





}
