package org.algorithm.java8.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lhz
 * @date: 2020/8/19
 **/
public class Demo {
    int id;
    String name;

    public Demo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<Demo> ds = new ArrayList<>(Arrays.asList(new Demo(1,"2"),new Demo(2,"22"),new Demo(3,"33"),new Demo(4,"44")));
        ds.stream()
                .peek(d -> d.setId(5)).collect(Collectors.toList());
        System.out.println(ds);



    }
}
