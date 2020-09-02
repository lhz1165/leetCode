package org.thread.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author:lhz
 * @date:2020/4/7
 **/
public class FireWaterUpgrade {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //任务1准备水壶烧水
        CompletableFuture<Void>f1=CompletableFuture.runAsync(()->{
            System.out.println("T1:洗水壶");
            sleep(1,TimeUnit.SECONDS);
            System.out.println("T1: 烧开水");
        });
        //任务2 准备茶壶接茶
        CompletableFuture<String> f2=CompletableFuture.supplyAsync(()->{
            System.out.println("T2:洗茶壶...");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T2:洗茶杯...");
            sleep(2, TimeUnit.SECONDS);
            System.out.println("T2:拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });
        //任务3 等水烧开泡茶
        CompletableFuture<String> f3=f1.thenCombine(f2,(t,tf)->{
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            System.out.println(t);
            return "上茶:" + tf;
        });
        //等待任务3执行结果
        System.out.println(f3.join());
    }


    static void sleep(int t, TimeUnit u) {
        try { u.sleep(t);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
