package org.algorithm.test;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/9/11
 **/
public class Test4 {

    public static void main(String[] args) {
        A a1 = new A(1);
        A a2 = new A(1);
        A a3 = new A(3);
        HashMap<A, Integer> a = new HashMap<>();
        a.put(a1, 1);
        a.put(a3, 3);
        a.put(a2, 2);
        System.out.println(a);
    }


}
class  A{
    int id;

    @Override
    public boolean equals(Object obj) {
        return id == ((A) obj).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public A(int id) {
        this.id = id;
    }
}