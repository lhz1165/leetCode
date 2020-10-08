package org.thread.aqs.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author lhzlhz
 * @create 2020/10/8
 */
public class VolatileTest {
	public volatile int i = 0;

	public void increase() {
		i++;
	}

	public static void main(String args[]) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(2000);
		List<Thread> threadList = new ArrayList<>();
		VolatileTest test = new VolatileTest();
		for (int j = 0; j < 2000; j++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						countDownLatch.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					test.increase();
				}
			});
			thread.start();
			countDownLatch.countDown();
			threadList.add(thread);
		}

		//等待所有线程执行完毕
		for (Thread thread : threadList) {
			thread.join();
		}
		System.out.print(test.i);
	}
}
