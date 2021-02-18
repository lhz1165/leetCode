package org.online;

import java.util.Scanner;

/**
 * @author: lhz
 * @date: 2021/2/1
 **/
public class Test {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(a+b);
        }
    }

}
