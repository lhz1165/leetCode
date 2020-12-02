package org.algorithm.interview_guide.stack_queue;

import java.util.*;

/**
 * @author lhzlhz
 * @create 2020/11/9
 */
public class Test01 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(3);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(3);
        stack.push(6);
        stack.push(7);
        Test01 t = new Test01();
        System.out.println(stack);

        // List<Integer> maxWindow = t.getMaxWindow(new int[]{4, 3, 5, 4, 3, 3, 6, 7}, 3);
        t.sortStackByStack(stack);
        System.out.println(stack);

        System.out.println(t.reverse("abc"));
    }

    /**
     * 递归来逆序一个栈
     */
    /**
     * 通过递归把最后一个数返回回来，其他不变
     *
     * @param stack
     * @return
     */
    public int getAndRemoveLast(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int removeLast = getAndRemoveLast(stack);
        stack.push(result);
        return removeLast;
    }

    /**
     * 调用上面的方法 递归来逆序栈
     *
     * @param stack
     */
    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = getAndRemoveLast(stack);
        reverse(stack);
        stack.push(last);
    }

    /**
     * 利用两个栈来排序
     * <p>
     * helper存放从上到下 由大到小的数字，
     * 每次把stack弹出cur，和helper里面的h1 h2 h3做比较，如果cur小于helper的最上面的h1
     * 那么h1放到stack 再比较 如果还小那么h2放回stack
     * 直到cur>hn
     *
     * @param stack
     */
    public void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> helper = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!helper.isEmpty() && cur < helper.peek()) {
                int h = helper.pop();
                stack.push(h);
            }
            helper.push(cur);
        }
        //再把helper的放回去
        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }
    }


    /**
     * 滑动窗口的最大值
     *
     * @param arr
     * @param w
     * @return
     */
    public List<Integer> getMaxWindow(int[] arr, int w) {
        //存放下标的
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i - w == deque.peekFirst()) {
                deque.pollFirst();
            }
            //
            if (i >= w - 1) {
                result.add(arr[deque.peekFirst()]);
            }
        }
        return result;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        //队列从大到小放数字的下标
        //如果滑动窗口滑过了，那么就移除头部的元素。
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            //头部永远存放最大的
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollFirst();
            }
            //放进去
            deque.offerLast(i);

            //这个最大的已经过时了，滑动窗口划过去了，所以移除
            if (i - k == deque.peekFirst()) {
                deque.pollFirst();
            }

            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public String reverse(String str){
        if(str == null){
            return null;
        }
        char[] chars = str.toCharArray();
        int start = 0;
        int end  = chars.length-1;
        while (start <= end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }

        return new String(chars);


    }


}
