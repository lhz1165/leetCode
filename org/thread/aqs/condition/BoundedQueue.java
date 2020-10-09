package org.thread.aqs.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lhzlhz
 * @create 2020/10/9
 * 有界等待队列实例
 */
public class BoundedQueue<T> {
	private Object[] items;
	private int addIndex,removeIndex,count;
	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();

	public BoundedQueue(int size) {
		this.items = new Object[size];
	}

	/**
	 *添加实例 如果满了就进入等待队列等待
	 * @param t
	 * @throws InterruptedException
	 */
	public void add(T t)throws InterruptedException {
		lock.lock();
		try {
			//如果满了就阻塞
			while (count == items.length) {
				notFull.await();
			}
			items[addIndex] = t;
			if (++addIndex == items.length) {
				addIndex = 0;
			}
			count++;
			notEmpty.signal();
		}finally {
			lock.unlock();
		}
	}

	public void remove() throws InterruptedException{
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();
			}
			items[removeIndex] = null;
			if (++removeIndex == items.length) {
				removeIndex = 0;
			}
			count--;

			notFull.signal();
		}finally {
			lock.unlock();
		}

	}
}
