package org.algorithm.leetcode.specified.array;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lhzlhz
 * @create 2021/3/24
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Main m =new Main();
		m.printABC();
	}

	Lock lock = new ReentrantLock();
	Condition ca = lock.newCondition();
	Condition cb = lock.newCondition();
	Condition cc = lock.newCondition();
	boolean fa = false;
	boolean fb = false;
	boolean fc = false;

	public void printA() throws InterruptedException {
		lock.lock();
		while (fa) {
			ca.await();
		}
		System.out.println("A");
		fa = true;
		fb = false;
		cb.signal();
	}
	public void printB() throws InterruptedException {
		lock.lock();
		while (fb) {
			cb.await();
		}
		System.out.println("B");
		fb = true;
		fc = false;
		cc.signal();
	}
	public void printC() throws InterruptedException {
		lock.lock();
		while (fc) {
			cc.await();
		}
		System.out.println("C");
		fc = true;
		fa = false;
		ca.signal();
	}

	private void printABC() throws InterruptedException {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					printA();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					printB();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					printC();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();
	}
}
