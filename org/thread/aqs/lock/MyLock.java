package org.thread.aqs.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author lhzlhz
 * @create 2020/10/8
 */
public class MyLock {

	private Sync sync;
	public MyLock() {
		sync = new Sync();
	}

	public void lock() {
		 sync.acquire(1);
	}

	public void unlock() {
		 sync.release(0);
	}



	static class Sync extends AbstractQueuedSynchronizer{
		/**
		 * 独占锁
		 * @param arg
		 * @return
		 */
		@Override
		protected boolean tryAcquire(int arg) {
			if (compareAndSetState(0, arg)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			setExclusiveOwnerThread(null);
			setState(arg);
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState()==1;
		}
	}
}
