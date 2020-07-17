package org.example.leetcode300.specified.string.lintcode;

/**
 * @author: lhz
 * @date: 2020/7/6
 **/
public class StringTest01 {
    public static void main(String[] args) {
        //"mississippi"
        //"issi"
        System.out.println(strStr2("abcdef", "def"));
        String s = "abcdefg";
        rotateString(s.toCharArray(),0);
    }
    /**
     * 使用rabin-karp 解决字符串字串问题
     * 使用hash表来记录字符串的值
     * def 的targetCode =d*31^2+e*31^1+f*31^0
     * abcdef开始枚举
     * 1 a hashcode = a*31^0;
     * 2 ab hashcode = a*31^1+b*31^0;
     * 3 abc hashcode = a*31^2+b*31^1+c*31^0
     * 4 abcd hashcode =a*31^3+b*31^2+c*31^1+d*31^0;此时长度超出3个---->hashcode=a*31^3+b*31^2+c*31^1+d*31^0-a*31^3=b*31^2+c*31^1+d*31^0
     * 5 bcde ......
     * .....
     * cdef hashcode=d*31^2+e*31^1+f*31^0
     * 就找到了
     *
     * @param source
     * @param target
     * @return
     */
    public static int strStr2(String source, String target) {
        //防止越界，每次模这个
        final int BASE = 10000000;
        // write your code here
        if (source == null || target == null) {
            return -1;
        }
        int m = target.length();
        if (m == 0) {
            return 0;
        }
        int n = source.length();

        //计算出31的m次方是多少=31^m
        int power = 1;
        for (int i = 0; i < m; i++) {
            power = (power * 31) % BASE;
        }

        //计算目标target的hashCode
        //abc a*31^0+b*31^1+c*31^2;
        int targetCode = 0;
        for (int i = 0; i < m; i++) {
            //31的0次方开始---->31的m-1次方
            targetCode = (targetCode * 31 + target.charAt(i)) % BASE;
        }

        int hashCode = 0;
        //开始是空的 一个一个添加字母算hashCode
        for (int i = 0; i < n; i++) {
            //加上一个字母的hashCode
            hashCode = (hashCode * 31 + source.charAt(i)) % BASE;
            if (i < m - 1) {
                continue;
            }
            //当加上的字母比target多，那么减去一个字母
            if (i > m - 1) {
                //减去一个字母的hashCode
                hashCode = hashCode - (source.charAt(i - m) * power) % BASE;
                //如果减了为一个负数 形成一个闭环
                if (hashCode < 0) {
                    hashCode += BASE;
                }
            }

            if (hashCode == targetCode) {
                //左闭右开
                if (source.substring(i - m + 1, i + 1).equals(target)) {
                    return i - m + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 旋转字符串------->三步反转法
     * @param str
     * @param offset
     */
    public static void rotateString(char[] str, int offset) {
        if(str.length == 0 || offset == 0){
            return;
        }
        // write your code here
        offset  %= str.length;
        //向右offset 等于向左 length -offset
        offset= str.length - offset;
        //从0开始
         offset -= 1;
         //1先反转前面几位
        reverseChar(str, 0, offset);
        //2再反转后面几位
        reverseChar(str, offset+1, str.length-1);
        //3再全部反转
        reverseChar(str,0,str.length-1);
    }

    public static void reverseChar(char[] str, int start,int end) {
        while (start < end) {
            char tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;
            start++;
            end--;
        }

    }


}
