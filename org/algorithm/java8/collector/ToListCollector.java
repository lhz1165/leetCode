package org.algorithm.java8.collector;

import org.algorithm.java8.model.Demo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author: lhz
 * @date: 2020/8/19
 * 收集的元素类型
 * 累加器类型
 * 收集得到的对象类型
 **/
public class ToListCollector<T> implements Collector<T, List<T>,List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2)->{
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return t->t;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        ArrayList<Demo> demos = new ArrayList<>(Arrays.asList(new Demo(1, "1"), new Demo(2, "22"), new Demo(3, "33")));
        List<Integer> collect = demos.stream()
                .map(Demo::getId)
                .   collect(new ToListCollector<Integer>());
        System.out.println(collect);


    }
}
