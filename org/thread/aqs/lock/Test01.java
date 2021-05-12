package org.thread.aqs.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lhz
 * @date: 2020/10/12
 * 交替打印ab
 **/
public class Test01 {
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    boolean flag = false;
    public static void main(String[] args) {
        Test01 t = new Test01();
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            new Thread(()->{
                    try {
                        if (finalI == 0) {
                            t.printA();
                        }else {
                            t.printB();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }).start();
        }
    }
    public void printA() throws InterruptedException {
        try {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                while (flag) {
                    conditionA.await();
                }
                System.out.println("AAAAAAAAAA");
                flag = true;
                conditionB.signal();
            }
        } finally {
            lock.unlock();
        }


    }

    public void printB() throws InterruptedException {
        try {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                while (!flag) {
                    conditionB.await();
                }
                System.out.println("BBBBBBBBBBBB");
                flag = false;
                conditionA.signal();
            }
        }finally {
            lock.unlock();
        }



    }

}
