package org.example.leetcode300.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/5/22
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static void main(String[] args) {

        List<Integer> a = Arrays.asList(1, 2, 3);
        System.out.println(a);
    }

    public static void ad(List<Integer> list) {
        list.add(4);
    }
}
