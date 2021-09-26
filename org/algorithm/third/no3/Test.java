package org.algorithm.third.no3;

/**
 * @author lhzlhz
 * @create 26/9/2021
 */
public class Test {
	public static void main(String[] args) {
		Test t = new Test();
		t.maxProfit(new int[]{1, 2});
	}
	/**
	 * 151 · 买卖股票的最佳时机 III
	 */
	public int maxProfit(int[] prices) {
		// write your code here
		int n = prices.length;
		//外层循环表示隔板 分别表示第一次和第二次买卖的隔板
		//[0,i)[i,n)
		int result = 0;
		for(int i = 0; i < n; i ++){
			int rightCost = getCost(0, i - 1, prices);
			int leftCost = getCost(i, n - 1, prices);
			result = Integer.max(result, rightCost + leftCost);
		}
		return result;
	}
	private int getCost(int start, int end, int[] prices){
		int prevMin = Integer.MAX_VALUE;
		int result = 0;
		for(int i = start; i <= end; i++){
			prevMin = Integer.min(prevMin, prices[i]);
			result = Integer.max(result, prices[i] - prevMin);
		}
		return result;
	}
}
