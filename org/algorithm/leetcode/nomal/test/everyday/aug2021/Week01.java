package org.algorithm.leetcode.nomal.test.everyday.aug2021;

import java.util.Arrays;

/**
 * Date: 2021/8/4
 * Description:
 *
 * @author hz.lai
 */
public class Week01 {
    public static void main(String[] args) {
        Week01 w = new Week01();
        System.out.println(w.triangleNumber2(new int[]{2, 2, 3, 4}));
    }

    /**
     * 611 有效三角形个数
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i <= n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int k = j + 1;
                while (k < n) {
                    if (nums[k] < nums[i] + nums[j]) {
                        count++;
                    }
                    k++;
                }

            }
        }
        return count;

    }


    public int triangleNumber2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int k = i;
            for (int j = i + 1; j < n; ++j) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    System.out.println("i j k+1 "+i+" "+j+" "+(k+1));
                    System.out.println(nums[i]+" "+nums[j]+" "+nums[k + 1]);
                    ++k;
                }
                System.out.println("gg");
                ans += Math.max(k - j, 0);
            }
        }
        return ans;
    }


}
