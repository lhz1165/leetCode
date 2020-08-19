package org.example.java8.model;

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
}
