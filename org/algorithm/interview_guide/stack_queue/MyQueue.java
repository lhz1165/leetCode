package org.algorithm.interview_guide.stack_queue;

import java.util.Stack;

/**
 * @author: lhz
 * @date: 2021/2/2
 **/
public class MyQueue {
    Stack<Integer> stack ;
    Stack<Integer> stack2 ;


    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack.isEmpty()) {
                stack2.push(stack.pop());
            }
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack.isEmpty()) {
                stack2.push(stack.pop());
            }
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack2.isEmpty() && stack.isEmpty();
    }

}
