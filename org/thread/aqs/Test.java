package org.thread.aqs;

import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

/**
 * @author lhzlhz
 * @create 2020/8/22
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
		Lock lock = new TwinsLock();
		IntStream.range(1, 11).forEach(i -> {
			Thread thread = new Thread(() -> {
				while (true) {
					try {
						lock.lock();
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + ":  " + i);
						Thread.sleep(1000);
					} catch (Exception e) {

					} finally {
						lock.unlock();
					}
				}
			});
			thread.setDaemon(true);
			thread.start();
		});

		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			System.out.println();
		}

	}


}
