package org.thread.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author: lhz
 * @date: 2020/9/3
 **/
public class RecursionTest {
    public static void main(String[] args) throws InterruptedException {
        int[]arr={21,12,54,71,3,5,974,1,3,2,4,546,87,13,96,45,26,874,2,36,41,840,877,0,36,974,12,87,954,156,354,786,1263,857,24};
        Action action = new Action(arr,0,arr.length-1,new int[arr.length]);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(action);
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println(Arrays.toString(arr));
    }


    //排序1-100；
    static class Action extends RecursiveAction {
        int start;
        int end;
        int[] arr;
        int[]tmp;
        public Action(int[] arr,int start, int end,int[] tmp) {
            this.tmp = tmp;
            this.start = start;
            this.end = end;
            this.arr = arr;
        }

        @Override
        protected void compute() {
            if (end - start <= 2) {
                mergeSort(arr, tmp, start, end);
                System.out.println("------over-----------");
            }else {
                System.out.println("--------start----------");
                int mid = (start + end) / 2;
                Action a1 = new Action(arr, start, mid, tmp);
                Action a2 = new Action(arr, mid + 1, end, tmp);
                a1.fork();
                a2.fork();
                a1.join();
                a2.join();
                merge(arr,  start, end,tmp);
            }

        }

        public void mergeSort(int[] arr, int[] tmp, int start, int end) {
            if (start == end) {
                return;
            }
            int mid = (start + end) / 2;
            mergeSort(arr,tmp,start,mid);
            mergeSort(arr,tmp,mid+1,end);
            merge(arr, start, end, tmp);
        }

        private void merge(int[] arr, int start, int end, int[] tmp) {
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
}

