package org.algorithm.leetcode300.nomal.test;

import org.algorithm.leetcode300.basic.ListNode;

import java.util.*;

/**
 * @author: lhz
 * @date: 2020/11/11
 **/
public class EasyTest01 {
    public static void main(String[] args) {
        EasyTest01 e = new EasyTest01();
        ListNode n1 = new ListNode(-1);
        ListNode n2 = new ListNode(5);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(0);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        e.sumEvenAfterQueries(new int[]{1, 2, 3, 4}, new int[][]{{1, 0}, {-3, 1}, {-4, 0}, {2, 3}});
        //"1s3 PSt"
        //["step","steps","stripe","stepple"]
        e.shortestCompletingWord("1s3 PSt", new String[]{"step","steps","stripe","stepple"});

    }


    /**
     * 1470. 重新排列数组
     * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     * 输入：nums = [2,5,1,3,4,7], n = 3
     * 输出：[2,3,5,4,1,7]
     * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
     */
    public int[] shuffle(int[] nums, int n) {
        if (nums.length == 0) {
            return nums;
        }
        int back = 0;
        int front = n;
        int[] result = new int[nums.length];
        int index = 0;
        for (int i = 0; i < n; i++) {
            result[index++] = nums[back++];
            result[index++] = nums[front++];
        }
        return result;
    }

    /**
     * 1030. 距离顺序排列矩阵单元格
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, Comparator.comparingInt(a -> (Math.abs(a[0] - r0) + Math.abs(a[1] - c0))));
        return ret;
    }

    /**
     * 283. 移动零
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /* ***********************
     * 147. 对链表进行插入排序  *
     * **********************
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode tail = head;//最大的尾巴
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val >= tail.val) {

                tail = tail.next;
            } else {
                ListNode start = dummyHead;

                while (start.next.val <= curr.val) {
                    start = start.next;
                }
                tail.next = curr.next;
                curr.next = start.next;
                start.next = curr;
            }
            curr = tail.next;
        }
        return dummyHead.next;
    }

    /**
     * 杨辉三角
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> sanjiao = new ArrayList<>();
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> result = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    result.add(1);
                    continue;
                }
                if (j == i - 1) {
                    result.add(1);
                    continue;
                }
                List<Integer> last = sanjiao.get(sanjiao.size() - 1);
                int num = last.get(j - 1) + last.get(j);
                result.add(num);
            }
            sanjiao.add(result);

        }
        return sanjiao.get(sanjiao.size() - 1);
    }

    /**
     * 三角形最大周长
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 1] + A[i - 2] > A[i]) {
                return A[i - 1] + A[i - 2] + A[i];
            }
        }
        return 0;
    }

    /**
     * 1009. 十进制整数的反码
     * 输入：5
     * 输出：2
     * 解释：5 的二进制表示为 "101"，其二进制反码为 "010"，也就是十进制中的 2 。
     */
    public int bitwiseComplement(int N) {
        String binary = getBinary(N, new StringBuilder(), true);
        String revsrse = getReverse(binary);
        return binaryToInt(revsrse);
    }

    /**
     * 反码 = 数字 异或 11111.。。
     *
     * @param N
     * @return
     */
    public int bitwiseComplement2(int N) {
        int criteria = 1;
        int sum = 0;
        while (sum < N) {
            sum = (sum + criteria);
            criteria = criteria << 1;
        }
        return N ^ sum;
    }

    public String getBinary(int N, StringBuilder sb, boolean first) {
        if (N == 0) {
            return "0";
        }
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (N - (1 << i) > 0) {
                continue;
            }
            if (N - (1 << i) == 0 && first) {

                sb.append(1);
                for (int j = 0; j < i; j++) {
                    sb.append(0);
                }
                break;
            }
            if (N - (1 << i) == 0) {
                continue;
            }
            N -= 1 << (i - 1);
            if (first) {
                sb.append(1);
                for (int j = 0; j < i - 1; j++) {
                    sb.append(0);
                }
            } else {
                sb.replace(sb.length() - i, sb.length() - i + 1, "1");
            }
            getBinary(N, sb, false);
            break;
        }
        return sb.toString();
    }

    public String getReverse(String binary) {
        char[] chars = binary.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                chars[i] = '0';
            } else {
                chars[i] = '1';
            }
        }
        return new String(chars);
    }


    public int binaryToInt(String binary) {
        int result = 0;
        char[] chars = binary.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[index] == '1') {
                result += (1 << i);
            }
            index++;

        }
        return result;
    }

    /**
     * 1071. 字符串的最大公因子
     * m*x  n*x
     * x为相同部分的数量
     * 求 n 和 m 的最大公约数
     */
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        int maxYue = getMaxYue(Math.max(str1.length(), str2.length()), Math.min(str1.length(), str2.length()));
        return str2.substring(0, maxYue);
    }

    public int getMaxYue(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return getMaxYue(b, a % b);
    }

    /**
     * 985. 查询后的偶数和
     *  先求出偶数的和 ，然后先减再加
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int S = 0;
        for (int x : A) {
            if (x % 2 == 0) {
                S += x;
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int val = queries[i][0], index = queries[i][1];
            if (A[index] % 2 == 0) {
                S -= A[index];
            }
            A[index] += val;
            if (A[index] % 2 == 0) {
                S += A[index];
            }
            ans[i] = S;
        }
        return ans;
    }

    /**
     *
     * 268. 丢失的数字
     * n ^ n = 0
     * 0 ^ n = n
     * ()
     */
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ i;
            res = res ^ nums[i];
        }
        return res;
    }
    /**
     *748. 最短补全词
     *
     * 更好的思路：
     * 遇到char的字母表类型的比较  可以使用26为大小数组，下标为char的值，值为个数
     * 输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
     * 输出："steps"
     * 1s3 PSt===>   arr[s-'a']= 2;arr[p-'a']= 2;arr[t-'a']= 2;
     * step   ====>  arr[s-'a']= 1;arr[p-'a']= 2;arr[t-'a']= 2;
     * 不一样不是齐全的
     *
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        List<String> license = new ArrayList<>();
        licensePlate = licensePlate.replaceAll(" ","");
        char[] chars = licensePlate.toCharArray();
        for (char c : chars) {
            if (c >= '0' & c <= '9') {
                continue;
            }
            license.add((c+"").toLowerCase());
        }
        String result = null;
        int len = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() < len && isContains(word.toLowerCase(), license)) {
                len = word.length();
                result = word;
            }
        }
        return result;
    }

    public boolean isContains(String s, List<String> license) {
        if (s.length() < license.size()) {
            return false;
        }
        for (String c : license) {
            if (!s.contains(c)) {
                return false;
            }
            s = s.replaceFirst(c, "");

        }
        return true;
    }

}