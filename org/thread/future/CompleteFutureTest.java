package org.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author: lhz
 * @date: 2020/8/25
 **/
public class CompleteFutureTest {
    public static void main(String[] args) {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
           System.out.println(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        System.out.println(cf.getNow(null));
        System.out.println("-------------------------------------");

        //线程池
        ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count++);
            }
        });

        CompletableFuture cf2 = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().getName().startsWith("custom-executor-"));
            System.out.println(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executor);
        System.out.println(cf2.getNow(null));
        System.out.println(cf2.join());
    }


}
