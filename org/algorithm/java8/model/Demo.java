package org.algorithm.java8.model;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        List<Demo> d = new ArrayList<>();
        int count = (int)d.stream().filter(e-> "aa".equals(e.getName())).count();
        System.out.println(count);

    }
}
