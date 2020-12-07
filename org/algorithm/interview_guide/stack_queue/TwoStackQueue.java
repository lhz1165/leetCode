package org.algorithm.interview_guide.stack_queue;

import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/11/9
 */
public class TwoStackQueue {
	public static void main(String[] args) {
		TwoStackQueue s = new TwoStackQueue();
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		System.out.println(s.poll());//1
		System.out.println(s.peek());//2
		System.out.println(s.poll());//2
		System.out.println(s.peek());//3
		s.add(5);

		System.out.println(s.poll());
		System.out.println(s.poll());
		System.out.println(s.poll());
		s.add(6);
		System.out.println(s.poll());
	}



	Stack<Integer> s1;
	Stack<Integer> s2;

	public TwoStackQueue() {
		this.s1 = new Stack<>();
		this.s2 = new Stack<>();
	}
	public void add(int num) {
		s1.push(num);
	}

	public int poll() {
		if (s2.isEmpty()) {
			//s1全部拿出来再放进去
			s1ToS2();
		}
		return s2.pop();
	}

	public int peek() {
		if (s2.isEmpty()) {
			//s1全部拿出来再放进去
			s1ToS2();
		}
		return s2.peek();
	}


	public void s1ToS2() {
		if (!s1.isEmpty()) {
			int size = s1.size();
			for (int i = 0; i < size; i++) {
				Integer pop = s1.pop();
				s2.push(pop);
			}
		}
	}





//	public static void main(String[] args) {
//		TwoStackQueue q = new TwoStackQueue();
//		q.add(1);
//		q.add(2);
//		q.add(3);
//		q.add(4);
//		System.out.println(q.poll());
//		q.add(5);
//		q.add(6);
//		System.out.println(q.poll());
//		System.out.println(q.poll());
//		q.add(7);
//	}
}
