package org.algorithm.leetcode.nomal.test;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: lhz
 * @date: 2020/12/7
 * 面试题 03.06. 动物收容所
 **/
public class AnimalSelf {
    public static void main(String[] args) {
        AnimalSelf a = new AnimalSelf();
        a.enqueue(new int[]{0,0});
        a.enqueue(new int[]{1,0});
        int[] ints2 = a.dequeueCat();
        int[] ints1 = a.dequeueDog();
        int[] ints = a.dequeueAny();
        System.out.println();
    }

    LinkedList<int[]> house;

    Stack<int[]> helper;

    public AnimalSelf() {
        house = new LinkedList<>();
        helper = new Stack<>();
    }

    public void enqueue(int[] animal) {
        house.addLast(animal);
    }

    public int[] dequeueAny() {
        if (house.isEmpty()) {
            return new int[]{-1, -1};
        }
        return house.pollFirst();
    }

    public int[] dequeueDog() {
        while (!house.isEmpty() && house.peekFirst()[1] != 1) {
            helper.push(house.pollFirst());
        }
        int[] res;
        if (house.isEmpty()) {
            res= new int[]{-1,-1};
        }else {
            res = house.pollFirst();
        }
        while (!helper.isEmpty()) {
            house.addFirst(helper.pop());
        }
        return res;
    }

    public int[] dequeueCat() {
        while (!house.isEmpty() && house.peekFirst()[1] != 0) {
            helper.push(house.pollFirst());
        }
        int[] res;
        if (house.isEmpty()) {
            res= new int[]{-1,-1};
        }else {
            res = house.pollFirst();
        }
        while (!helper.isEmpty()) {
            house.addFirst(helper.pop());
        }
        return res;
    }

}