package org.algorithm.leetcode.nomal.test.normal;

import org.algorithm.leetcode.basic.ListNode;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lhz
 * @date: 2021/3/17
 **/
public class NormalTest04 {
    public static void main(String[] args) {
        NormalTest04 n = new NormalTest04();
        n.wordBreak("apleap", new ArrayList<>(Arrays.asList("ap", "le")));
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;

    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, word.toCharArray(), 0, i, j)) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean dfs(char[][] board, char[] words, int k, int i, int j) {
        if (k >= words.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != words[k]) {
            return false;
        }
        return dfs(board,words,k+1,i,j-1)||dfs(board,words,k+1,i-1,j)||dfs(board,words,k+1,i,j+1)||dfs(board,words,k+1,i+1,j);
    }

    public int[] sortedSquares(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int[] res = new int[nums.length];
        int index = nums.length - 1;
        while (i <= j) {
            if (nums[i] * nums[i] < nums[j] * nums[j]) {
                res[index--] = nums[j] * nums[j];
                j--;
            }else {
                res[index--] = nums[i] * nums[i];
                i++;
            }
        }
        return res;
    }

    /**
     * dp
     *
     *279. 完全平方数
     * @param n
     * @return
     */

    public int numSquares(int n) {
        if (n == 0) {
            return 1;
        }
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                if (j * j == i) {
                    f[i] = 1;
                    break;
                }
                if (j * j > i) {
                    break;
                }
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }
        return f[n];
    }


        /**
         * 75. 颜色分类
         * @param nums
         */
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int index = 0;
        int target = 1;
        while (index <= end) {
            if (nums[index] == target) {
                index++;
            } else if (nums[index] > target) {
                int tmp = nums[index];
                nums[index] = nums[end];
                nums[end] = tmp;
                end--;
            }else {
                int tmp = nums[index];
                nums[index] = nums[start];
                nums[start] = tmp;
                start++;
                index++;
            }
        }
    }

    /**
     *82. 删除排序链表中的重复元素 II
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        boolean needDel = false;
        while (cur!=null && cur.next != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                needDel = true;
            }
            if (needDel) {
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = prev.next;
                cur = cur.next;
            }
            needDel = false;

        }
        return dummy.next;
    }

    /**
     * 139. 单词拆分
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        //前i个字符能否拆分
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && wordDict.contains(s.substring(j,i))){
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }










}
