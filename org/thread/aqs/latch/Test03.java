package org.thread.aqs.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lhzlhz
 * @create 2020/10/8
 */
public class Test03 {
	public static void main(String[] args) throws InterruptedException {
		new Test03().doTestWithCountDown();
	}


	public void doTestWithCountDown() throws InterruptedException {
		final List<Integer> datas = new ArrayList<>();
		final ExecutorService TASK_POOL = Executors.newFixedThreadPool(20);
		final CountDownLatch countDownLatch = new CountDownLatch(20);
		Runnable task = () -> {
			try {
				countDownLatch.await();//这里等待其他线程就绪后开始放行
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 100; i++) {
				datas.add(i);
			}
		};
		for (int i = 0; i < 20; i++) {
			TASK_POOL.execute(task);
			countDownLatch.countDown();//每个任务提交完毕后执行
		}
		TASK_POOL.shutdown();
		TASK_POOL.awaitTermination(5, TimeUnit.SECONDS);
		System.out.println(datas.size());
	}
}
