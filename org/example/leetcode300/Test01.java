package org.example.leetcode300;

/**
 * @author: lhz
 * @date: 2020/8/19
 **/
public class Test01 {
    public static void main(String[] args) {
        String str ="setAdAmount";
        String amount = str.substring(0, str.indexOf("Amount"));
        System.out.println(amount);

        System.out.println(amount.substring(3)
                .toLowerCase());
        System.out.println(str.startsWith("get"));
    }
}
