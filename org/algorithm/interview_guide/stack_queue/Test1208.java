package org.algorithm.interview_guide.stack_queue;

import java.util.Stack;

/**
 * @author lhzlhz
 * @create 2020/12/8
 */
public class Test1208 {
	public static void main(String[] args) {
		int[] arr = new int[]{3, 4, 1, 5, 6, 2, 7};
		Test1208 t = new Test1208();
		//t.printArr(arr);
		t.printTwoArr(t.rightWay(arr));

	}

	public void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}

	public void printTwoArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j =0; j < arr[i].length ; j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();

		}
	}


	/**
	 * 单调栈
	 */
	// [X,X,X,X,X] x= int
	// [[x,x],[x,x,x],[x,x,x],[x,x] ]  x =int[]
	public int[][] rightWay(int[] arr) {
		int[][] result =new  int[arr.length][2];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
				//得出结果
				Integer popIndex = stack.pop();
				result[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
				result[popIndex][1] = i;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			Integer popIndex = stack.pop();
			result[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
			result[popIndex][1] = -1;
		}
		return result;
	}
	/**
	 *
	 */


}
