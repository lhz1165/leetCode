package org.algorithm.leetcode300.nomal.test.easy;

import org.algorithm.leetcode300.basic.TreeNode;

/**
 * @author: lhz
 * @date: 2020/12/7
 **/
public class EasyTest02 {

    public static void main(String[] args) {
        EasyTest02 e = new EasyTest02();
        //111
        //899
        e.duplicateZeros(new int[]{1, 0, 2, 3, 0, 4, 5, 0});
    }

    /**
     * 面试题 01.03. URL化
     * 输入："Mr John Smith    ", 13
     * 输出："Mr%20John%20Smith"
     */
    public String replaceSpaces(String S, int length) {
        String candidate = "%20";
        return S.substring(0, length)
                .replace(" ", candidate);
    }

    /**
     * 1446. 连续字符
     */
    public int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        int n = s.length();
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                f[i] = 1;
                result = Math.max(result, f[i]);
                continue;
            }
            if (s.charAt(i) == s.charAt(i - 1)) {
                f[i] = f[i - 1] + 1;
            } else {
                f[i] = 1;
            }
            result = Math.max(result, f[i]);

        }
        return result;

    }

    /**
     * 面试题 17.01. 不用加号的加法
     * ***********不会
     * 1001 = 9
     * 1011 = 11
     */
    public int add(int a, int b) {

        while (b != 0) {
            //1001^1011 = 0010  不是因为进位加起来等于1的值 0+1 = 1 ，0+0=0，1+1=0
            int sum = (a ^ b);
            //找到进位在哪 例如求 1001+1011进位
            //1001 & 1011 =1001 ==> 进位 10010
            int carry = (a & b) << 1;// (a&b) * 2

            //直到没有这个进位了
            a = sum;
            b = carry;
        }

        return a;
    }

    /**
     *
     */
    public void duplicateZeros(int[] arr) {
        int count = 0;
        int len = arr.length;
        int i = 0;
        // 统计需要复制的0的个数，复制count个0，则会挤出原数组count个值
        // i进行扫描，遇到0则count自增
        // 若i+count大于原数组长度，则停止扫描，后面的直接舍弃
        while (i + count < len) {
            if (arr[i++] == 0) count++;
        }
        // 因为循环中i自增到了下一个值，此处i--返回到上一个结束值
        i--;
        int j = len - 1;
        // i从结束值开始，j从数组末尾开始，从后往前扫描，开始复制
        // 特别需要注意的是：若最后一个数字是0，统计需要复制的0时统计了该数，但若复制一次，则数组可能越界
        // 此处+1判断，如果越界，则只复制本身
        if (count + i + 1 > len) {
            arr[j--] = arr[i--];
            count--;
        }
        // 遇0则复制两次，非0则复制本身
        // count<=0时，说明前面没有0了，保持不变就行
        while (count > 0) {
            arr[j--] = arr[i];
            if (arr[i] == 0) {
                arr[j--] = arr[i];
                count--;
            }
            i--;
        }
    }

    /**
     * 563. 二叉树的坡度
     */
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        return findTilt(root.left) + findTilt(root.right) + Math.abs(leftSum - rightSum);

    }

    public int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sum(root.left) + sum(root.right) + root.val;

    }

}
