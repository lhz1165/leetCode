package org.algorithm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author: lhz
 * @date: 2020/9/11
 **/
public class Test4 {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Integer[] integers = a.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integers));



    }

}
