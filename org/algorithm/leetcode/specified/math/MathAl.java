package org.algorithm.leetcode.specified.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MathAl {
    public static void main(String[] args) {
        List<String> strings = new MathAl().midToEnd("2*(9+6/3-5)+4");
        System.out.println(strings);
    }

    //中缀转后缀
    //1准备stack （用来放符号） list（放结果）
    //2 依次使用，
    //         如果【数字】放入list】，
    //         如果【（】直接放入stack，
    //         如果【）】弹出对称括号里的符号放入list（例如【（+）弹出来放入list】）
    //         如果是符号
    //                  优先级比【stack顶的符号】高的话直接放入stack
    //                  优先级小于等于【stack顶的符号】的话弹出所有符号放入list，直到遇到【优先级比他低的或者 ) 】才放入stack
    public List<String> midToEnd(String str) {
        char[] chars = str.toCharArray();
        String[] strings = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            strings[i] = chars[i]+"";
        }
        Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            if (isNumber(s)) {
                result.add(s);
            }else {
                if (s.equals("(")) {
                    stack.push(s);
                }else if (s.equals(")")){

                    //弹出( )中间的
                    while (!stack.peek().equals("(")){
                        result.add(stack.pop());
                    }
                    //弹出)
                    stack.pop();
                }else{
                    if (!stack.isEmpty()) {
                        String topOp = stack.peek();
                        //如果优先级高 直接放入stack
                        if (isHigh(s, topOp)||topOp.equals("(")) {
                            stack.push(s);
                        }else {
                            //优先级低 弹出来直到遇到低的
                            while (!stack.isEmpty()&&!isHigh(s, stack.peek())){
                                String str1 = stack.pop();
                                result.add(str1);
                            }
                            stack.push(s);
                        }
                    }else{
                        stack.push(s);
                    }

                }
            }
        }
        result.add(stack.pop());
        return result;
    }

    /**
     * 判断字符串是否为操作符
     * @param op
     * @return
     */
    public static boolean isOperator(String op){
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

    /**
     * 判断是否为数字
     * @param num
     * @return
     */
    public static boolean isNumber(String num){
        return num.matches("\\d+");
    }

    /**
     * 获取操作符的优先级
     * @param op
     * @return
     */
    public static int priority(String op){
        if(op.equals("*") || op.equals("/")){
            return 1;
        }else if(op.equals("+") || op.equals("-")){
            return 0;
        }
        return -1;
    }

    public static boolean isHigh(String op1, String op2) {
        if (op2.equals("(")) {
            return true;
        }
        return priority(op1) - priority(op2) > 0;
    }
}
