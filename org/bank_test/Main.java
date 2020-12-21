package org.bank_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author: lhz
 * @date: 2020/12/20
 **/
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Main m = new Main();
        int len = s.nextInt();
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(m.subSet(arr));
    }
    public int subSet(int[] arr){
        Arrays.sort(arr);
        List<List<Integer>> results = new ArrayList<>();
        helpSubSet(results, new ArrayList<>(), 0,0,arr);
        return results.size();
    }

    public void helpSubSet(List<List<Integer>> results,List<Integer> result,int index,int sum,int[] arr) {
        if (sum == 24) {
            results.add(new ArrayList<>(result));
            return;
        }
        if (sum > 24) {
            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (sum + arr[i] > 24) {
                break;
            }
            if (i != 0 && arr[i - 1] == arr[i] && i != index) {
                continue;

            }
            result.add(arr[i]);
            helpSubSet(results, result, i + 1, sum + arr[i], arr);
            result.remove(result.size() - 1);
        }

    }

}
