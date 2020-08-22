package org.thread.aqs.latch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author lhzlhz
 * @create 2020/8/23
 */
public class Driver {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch stattLacth = new CountDownLatch(1);
		CountDownLatch doLacth = new CountDownLatch(5);

		IntStream.range(0,5).forEach(i->{
			new Thread(()->{
				try {
					stattLacth.await();
					System.out.println("thread  begin do work:  "+i);
					doLacth.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		});

		System.out.println("do something ---------");
		stattLacth.countDown();
		System.out.println("wait 5 job");
		doLacth.await();
		System.out.println("all 5 job over");

	}
}
