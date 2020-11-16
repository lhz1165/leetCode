package org.algorithm.interview_guide.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/11/16
 **/
public class Test {
    public static void main(String[] args) {
        S1 s1 = new S1(1);
        S1 s2 = new S1(2);
        S1 s3 = new S1(3);

        List<S1> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        s1 = s3;
        s1.id = 5;

        list.add(s1);

        System.out.println(list);
    }

    static class S1{
        int id = 1;

        public S1(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "S1{" +
                    "id=" + id +
                    '}';
        }
    }
}
