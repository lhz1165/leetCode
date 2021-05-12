package org.thread.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * Created by: hz.lai
 * Date: 2021/5/12
 * Description:信号量打印ABC
 */
public class PrintABCBySemphore {
    Semaphore sa = new Semaphore(1);
    Semaphore sb = new Semaphore(0);
    Semaphore sc = new Semaphore(0);
    public static void main(String[] args) throws InterruptedException {
        PrintABCBySemphore p = new PrintABCBySemphore();
        CyclicBarrier c = new CyclicBarrier(3);
        CountDownLatch cd = new CountDownLatch(3);
        IntStream.range(1,4).forEach(i ->{
            new Thread(()->{
                    try {
                        if (i==1){
                            c.await();
                            p.printA();
                            cd.countDown();
                        } else if (i == 2) {
                            c.await();
                            p.printB();
                            cd.countDown();
                        }else {
                            c.await();
                            p.printC();
                            cd.countDown();
                        }
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
            }).start();
        });
        cd.await();
    }

    public void printA() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            sa.acquire(1);
            System.out.println("A");
            sb.release(1);
        }
    }

    public void printB() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            sb.acquire(1);
            System.out.println("B");
            sc.release(1);
        }
    }
    public void printC() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            sc.acquire(1);
            System.out.println("C");
            sa.release(1);
        }
    }


}
