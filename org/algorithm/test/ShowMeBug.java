package org.algorithm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: lhz
 * @date: 2020/9/11
 **/
public class ShowMeBug {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入选项");
            String option = sc.next();
            if (option .equals("1") ) {
                System.out.println("请输入姓名");
                String name = sc.next();
                System.out.println(name);
            }



        }

    }


}

