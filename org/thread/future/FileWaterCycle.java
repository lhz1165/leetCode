package org.thread.future;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: lhz
 * @date: 2020/8/25
 **/
public class FileWaterCycle {
    public static void main(String[] args) {
        CountDownLatch c = new CountDownLatch(1);

        new Thread(new T1Task(c)).start();
        new Thread(new T2Task(c)).start();

    }





    static class T1Task implements Runnable {
        CountDownLatch c ;

        public T1Task(CountDownLatch c) {
            this.c = c;
        }
        @Override
        public void run()  {
            try {
                System.out.println("T1:洗水壶。。。。");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T1: 烧开水。。。");
                TimeUnit.SECONDS.sleep(8);
                c.countDown();
                System.out.println("--------------------wait over---------------------");
            } catch (Exception e) {

            }


        }
    }
    static class T2Task implements Runnable {
        CountDownLatch c ;

        public T2Task(CountDownLatch c) {
            this.c = c;
        }
        @Override
        public void run()  {
            System.out.println("T2: 洗茶壶。。。。");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T2: 洗茶杯。。。");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("T2： 拿茶叶。。。");
                TimeUnit.SECONDS.sleep(1);
                c.await();
                System.out.println("T1： 泡茶。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
