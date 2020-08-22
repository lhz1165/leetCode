package org.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

/**
 * @author lhzlhz
 * @create 2020/8/19
 * 自定义锁 ，每次允许两个线程进入
 */
public class TwinsLock implements Lock {
	public static void main(String[] args) {
		IntStream.range(1,11).forEach(System.out::println);
	}


	private final Sync sync = new Sync(2);
	@Override
	public void lock() {
		sync.acquireShared(1);

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return sync.tryReleaseShared(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryReleaseShared(1);
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	public static final class Sync extends AbstractQueuedSynchronizer{
		//允许几个线程进入
		public Sync(int count) {
			setState(count);
		}

		@Override
		protected int tryAcquireShared(int reduceCount) {
			while (true) {
				int cur = getState();
				int newCount = cur - reduceCount;
				if (newCount < 0 || compareAndSetState(cur, newCount)) {
					return newCount;
				}

			}

		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			while (true) {
				int cur = getState();
				int newCount = cur + arg;
				if (compareAndSetState(cur, newCount)) {
					return true;
				}
			}
		}
	}
}
