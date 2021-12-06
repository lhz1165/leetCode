package org.codetop.douyindianshang;

/**
 * @author lhzlhz
 * @create 22/11/2021
 */
public class Test03 {
	public static void main(String[] args) {
		Test03 t = new Test03();
		t.checkInclusion("adc","dcda");

	}

	public boolean checkInclusion(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();

		int[] s1Freq = new int[26];
		char[] s1Chars = s1.toCharArray();
		char[] s2Chars = s2.toCharArray();

		for (char s1Char : s1Chars) {
			s1Freq[s1Char - 'a']++;
		}

		int left = 0;
		int right = 0;
		while (left <= len2 - len1) {
			while (right - left < len1 && s1Freq[s2Chars[right] - 'a'] > 0) {
				s1Freq[s2Chars[right] - 'a']--;
				right++;
			}
			//循环结束表示s1走完了，或者中间遇到不同的重来
			//s1走完了
			if (right - left == len1) {
				return true;
			}
			//中间遇到不同的 left向前移动一位
			s1Freq[s2Chars[left] - 'a']++;
			left++;
		}
		return false;
	}
}
