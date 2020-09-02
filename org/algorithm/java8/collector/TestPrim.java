package org.algorithm.java8.collector;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author: lhz
 * @date: 2020/8/19
 **/
public class TestPrim {

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }
    //循环除以2到小于自己平方根的
    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public boolean isPrime(List<Integer> prims,int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(prims,i->i <= candidateRoot).stream().noneMatch(i -> i % candidate == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }


}
