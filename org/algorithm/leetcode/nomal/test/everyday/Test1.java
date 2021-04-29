package org.algorithm.leetcode.nomal.test.everyday;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        getPi(10);
        f1(5, true);
         f1(5, false);
        int[] arr = new int[]{9, 5, 1, 3, 4, 6};
        System.out.print(Arrays.toString(arr)+" ");
        sort(arr, 0, arr.length - 1);
        System.out.println("排序后 " + Arrays.toString(arr));
        findStr("abcdefg", "de");
    }


    public static void f1(int n, boolean flag) {
        int sum = 1;
        for (int i = 2; i <= n; i++) {
            if (flag) {
                sum *= i;
            } else {
                sum += i;
            }
        }
        if (flag) {
            System.out.println(n+"! = " + sum);
        } else {
            System.out.println("1+2+..+ "+n +" = " + sum);

        }
    }

    /**
     * 圆周率π
     */
    public static void getPi(int n) {
        double y = 1.0;
        double π=0;
        for (int i = 0; i <= n; i++) {
            π= 3 * Math.pow(2, i) * y;

            y = Math.sqrt(2 - Math.sqrt(4 - y * y));
        }
        System.out.println(n+"次切割,为正圆周率π≈" + π);
    }

        /**
         * 查找字符串
         * @param str1
         * @param str2
         */
        public static void findStr (String str1, String str2){
            System.out.println("子字符串首次出现的位置 "+str1.indexOf(str2));
        }

        /**
         * 排序
         * @param arr
         * @param start
         * @param end
         */
        public static void sort ( int[] arr, int start, int end){
            if (start >= end) {
                return;
            }
            int l = start;
            int r = end;
            int pivot = arr[(l + r) / 2];
            while (l <= r) {
                while (l <= r && pivot > arr[l]) {
                    l++;
                }
                while (l <= r && pivot < arr[r]) {
                    r--;
                }
                if (l <= r) {
                    int tmp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = tmp;
                    l++;
                    r--;
                }
            }
            sort(arr, start, r);
            sort(arr, l, end);

        }


    }
