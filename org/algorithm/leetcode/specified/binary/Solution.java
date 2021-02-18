package org.algorithm.leetcode.specified.binary;

/**
 * @author lhzlhz
 * @create 2020/7/7
 */
public class Solution extends VersionControl {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.firstBadVersion(5));
	}
	public  int firstBadVersion(int n) {
		int start = 1;
		int end = n;
		while(start + 1 < end){
			int mid = start +(end - start)/2;
			if(isBadVersion(mid)){
				end = mid;
			}else if (!isBadVersion(mid) ){
				start = mid;
			}
		}
		if(isBadVersion(start)){
			return start;
		}
		if(isBadVersion(end)){
			return end;
		}
		return -1;

	}
}
