package org.example.java8.collector;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author: lhz
 * @date: 2020/8/19
 **/
public class PrimCollector implements Collector<Integer, Map<Boolean, List<Integer>>,Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return ()->{
            Map<Boolean, List<Integer>> res = new HashMap<>();
            res.put(true, new ArrayList<>());
            res.put(false, new ArrayList<>());
            return res;
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {

        return (res,i)->{
            res.get(isPrime(res.get(true),i)).add(i);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return t -> t;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return  Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
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

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, 20)
                .boxed()
                .collect(new PrimCollector());
        System.out.println(collect);

    }
}
