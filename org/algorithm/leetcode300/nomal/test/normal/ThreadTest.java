package org.algorithm.leetcode300.nomal.test.normal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lhz
 * @date: 2021/1/12
 **/
public class ThreadTest {
     ReentrantLock lock = new ReentrantLock();
     Condition b = lock.newCondition();
     Condition a = lock.newCondition();
     boolean flag = true;

    private void printA() throws InterruptedException {
            lock.lock();
            while (!flag) {
                a.await();
            }
            System.out.println("AAAAAAAAA");
            Thread.sleep(2000);
            flag = false;
            b.signal();
            lock.unlock();
    }
    private void printB() throws InterruptedException {
        lock.lock();
            while (flag) {
                b.await();
            }
            System.out.println("BBBBBBBBBB");
        Thread.sleep(2000);

        flag = true;
            a.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();
        new Thread(()->{
            while (true) {
                try {
                    t.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        new Thread(()->{
            while (true) {
                try {
                    t.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }



}
