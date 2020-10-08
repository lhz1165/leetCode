package org.thread.aqs.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lhzlhz
 * @create 2020/10/8
 */
public class Test03 {

	public int i = 0;

	public void increase() {

		i++;
	}

	/**
	 * 试用线程池
	 * 所有线程 同时执行++操作
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		MyLock lock = new MyLock();

		Test03 t = new Test03();
		final ExecutorService TASK_POOL = Executors.newFixedThreadPool(200);
		final CountDownLatch countDownLatch = new CountDownLatch(20000);
		final CountDownLatch countDownLatch2 = new CountDownLatch(20000);

		for (int i = 0; i < 20000; i++) {
			TASK_POOL.execute(() -> {
				try {
					//System.out.println("wait--------");
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//不使用lock经常会出现并发问题
				//lock.lock();
				t.increase();


				//lock.unlock();
				countDownLatch2.countDown();

			});
			countDownLatch.countDown();//每个任务提交完毕后执行
		}
		TASK_POOL.shutdown();
		TASK_POOL.awaitTermination(5, TimeUnit.SECONDS);
		countDownLatch2.await();
		System.out.println("i: " + t.i);


	}
}
