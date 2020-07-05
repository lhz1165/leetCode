package org.example.leetcode300.specified.dynamic.lintcode;

/**
 * @author lhzlhz
 * @create 2020/7/5
 * 双序列型动态规划
 */
public class DoubleSequence {
	public static void main(String[] args) {
		longestCommonSubsequence("jiuzhangsuanfa", "lijiang");
		System.out.println(longestCommonSubstring("ABCDE", "CBCE"));

	}

	/**
	 * 最长公共子串
	 *
	 * @param A: A string
	 * @param B: A string
	 * @return: the length of the longest common substring.
	 */
	public static int longestCommonSubstring(String A, String B) {
		// write your code here
		int m = A.length();
		int n = B.length();
		int[][] f = new int[m + 1][n + 1];
		//重点！！！！！！！！！！
		//由于不是最长公共子序列 所以f[i][j]不能存储之前所有的结果
		//就是说f[i][j]不能表示 前i个 和前j个最长公共子串只能通过result来记录
		//但是如果是最长公共子序列 那么则不需要result来记录只需要，用f[i][j]记录即可
		int result = 0;
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					f[i][j] = 0;
					continue;
				}
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					//相等去掉两个尾巴比较
					f[i][j] = f[i - 1][j - 1] + 1;
					result = Math.max(f[i][j], result);
				}
			}
		}
		return result;
	}
	/**
	 * 最长公共子序列

	 * @param A: A string
	 * @param B: A string
	 * @return: The length of longest common subsequence of A and B
	 */
	public static int longestCommonSubsequence(String A, String B) {
		// write your code here
		int m = A.length();
		int n = B.length();
		int[][] f = new int[m+1][n+1];

		//通过pi来记录选取的那种情况，以此来打印数据
		// 0:情况1
		// 1:情况2
		// 2:情况3
		int[][] pi =new int[m+1][n+1];

		for (int i = 0; i <= m; i++ ){
			for (int j = 0; j <= n ;j++ ){
				if(i == 0 || j == 0){
					f[i][j] = 0 ;
					continue;
				}
				//情况1 和情况2 比较
				f[i][j] = Math.max(f[i][j-1], f[i-1][j]);
				if(f[i][j] == f[i][j-1]){
					pi[i][j] = 0;
				}else{
					pi[i][j] = 1;
				}
				//情况3
				if(A.charAt(i-1) == B.charAt(j-1)){
					f[i][j] = Math.max(f[i][j], f[i-1][j-1]+1);
					if(f[i][j] == f[i-1][j-1]+1){
						pi[i][j] = 2;
					}
				}

			}
		}
		//收集结果
		int k = f[m][n];
		char[] result = new char[k];
		int i = m;
		int j = n;
		int q = k-1;
		while(i >= 0 && j >= 0){
			if(pi[i][j] == 0){
				j--;
			}else if(pi[i][j] == 1){
				i--;
			}else {
				result[q--] = A.charAt(i-1);
				i--;
				j--;
			}
		}
		return k;
	}

	/**
	 * @param s1: A string
	 * @param s2: A string
	 * @param s3: A string
	 * @return: Determine whether s3 is formed by interleaving of s1 and s2
	 */
	public boolean isInterleave(String s1, String s2, String s3) {
		// write your code here
		return false;
	}

	/**
	 * @param word1: A string
	 * @param word2: A string
	 * @return: The minimum number of steps.
	 */
	public int minDistance(String word1, String word2) {
		// write your code here
		int m = word1.length();
		int n = word2.length();
		int[][] f = new int[m+1][n+1];
		f[0][0] = 0;
		for(int i = 0; i <= m; i++){
			for(int j = 0; j <= n; j++){
				if(i == 0){
					f[i][j] = j;
					continue;
				}

				if(j == 0){
					f[i][j] = i;
					continue;
				}
				//f[i][j] = Math.min(Math.min(f[i-1][j],f[i][j-1]),f[i-1][j-1])+1;
				f[i][j] = Integer.MAX_VALUE;
				//插入一个
				f[i][j] = Math.min(f[i][j],f[i-1][j]+1);
				//删掉一个
				f[i][j] = Math.min(f[i][j],f[i][j-1]+1);
				//替换一个
				f[i][j] = Math.min(f[i][j],f[i-1][j-1]+1);
				//什么也不干
				if (word1.charAt(i-1) == word2.charAt(j-1)){
					f[i][j] = Math.min(f[i][j],f[i-1][j-1]);
				}

			}
		}
		return f[m][n];
	}
	/**
	 * @param S: A string
	 * @param T: A string
	 * @return: Count the number of distinct subsequences
	 */
	public int numDistinct(String S, String T) {
		// write your code here
		int m = S.length();
		int n = T.length();
		int[][] f = new int[m+1][n+1];
		f[0][0] = 1;
		for (int i = 1;i <= m ;i++ ){
			f[i][0] = 1;
		}

		for (int i = 1;i <= n ;i++ ){
			f[0][i] = 0;
		}

		for(int i = 1;i <= m; i++){
			for(int j = 1;j <= n; j++){
				f[i][j] = f[i-1][j];
				if(S.charAt(i-1) == T.charAt(j-1)){
					f[i][j] += f[i-1][j-1];
				}
			}
		}
		return f[m][n];
	}

	/**
	 * @param s: A string
	 * @param p: A string includes "." and "*"
	 * @return: A boolean
	 */
	public boolean isMatch(String s, String p) {
		// write your code here

	}
}
