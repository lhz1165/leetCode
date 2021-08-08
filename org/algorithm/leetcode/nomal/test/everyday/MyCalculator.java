package org.algorithm.leetcode.nomal.test.everyday;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class MyCalculator extends JFrame{
    //用于判断是否重新开始
    private boolean start = true;
    private double result = 0;
    //存放加减乘除等于等
    private String command = "=";
    private JTextField jTextField;
    private JPanel jPanel = new JPanel();
    private JButton[] jButtons;

    //用构造方法进行必要的设置
    public MyCalculator() {

        this.setTitle("科学计算器");
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);

        //添加文本域
        jTextField = new JTextField();
        jTextField.setText("");
        jTextField.setEditable(false);
        this.add(jTextField,"North");

        //添加按钮
        jPanel.setLayout(new GridLayout(5,4,4,4));
//        String name2[] = {
//                "+/-","PI","1/X","C","/","*","Back","X^2","X^3",
//                "X^y","7","8","9","-","X!","√X","3^√X","4","5",
//                "6","+","sin","cos","tan","1","2","3","%",
//                "2进制","10进制","cot","time","0",".","="
//        };
        String name[] = {
                "+/-","C","/","*","Back","7","8","9","-",".","4","5",
                "6","+","time","1","2","3","%","0","=",
        };
        jButtons = new JButton[name.length];
        MyActionListener actionListener= new MyActionListener();

        //利用循环创建按钮对象并添加事件监听器
        for(int i = 0; i < name.length; i++) {

            jButtons[i] = new JButton(name[i]);
            jButtons[i].addActionListener(actionListener);

            //设置按钮背景颜色
            jButtons[i].setBackground(Color.lightGray);
            if(name[i].equals("="))
                jButtons[i].setBackground(Color.RED);
            else if((int)name[i].charAt(0)>=48 && (int)name[i].charAt(0)<=57
                    && name[i].length() == 1)
                jButtons[i].setBackground(Color.WHITE);
            else if(name[i].equals("Back"))
                jButtons[i].setBackground(Color.GRAY);

            jPanel.add(jButtons[i]);
        }

        this.add(jPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    //用内部类实现事件监听器
    class MyActionListener implements ActionListener{
        //按钮被单击
        public void actionPerformed(ActionEvent e) {

            String input = e.getActionCommand();
            //开始
            if(start) {
                if((int)input.charAt(0)>=48 && (int)input.charAt(0)<=57
                        && input.length() == 1 ) {
                    jTextField.setText(""+input);
                }
                if(input.equals("+/-")) {
                    jTextField.setText("-");
                }
                if(input.equals("PI")) {
                    jTextField.setText(""+Math.PI);
                }
                start = false;
                if(input.equals("C"))
                    jTextField.setText("");
            }
            //0~9数字等非运算符
            else if((int)input.charAt(0)>=48 && (int)input.charAt(0)<=57
                    && input.length() == 1 || input.equals(".")){
                jTextField.setText(jTextField.getText()+input);
            }
            //实现所有 数组+运算符 的运算

            //实现清零键
            else if(input.equals("C"))
                jTextField.setText("");
                //实现退格键
            else if(input.equals("Back")) {
                if(jTextField.getText().length() > 0){
                    jTextField.setText(jTextField.getText().substring(0,jTextField.getText().length()-1));
                }
            }
            //实现1/x
            else if(input.equals("1/X")) {
                result = 1 / Double.parseDouble(jTextField.getText());
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }

            //实现百分号计算
            else if(input.equals("%")) {
                result = Double.parseDouble(jTextField.getText())/ 100.0;
                jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                start = true;
            }
            //实现获取当前时间
            else if(input.equals("time")) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jTextField.setText(df.format(System.currentTimeMillis()));
                start = true;
            }

            //实现加减乘除等 数字+运算符+数字 形式的运算
            else {
                if(!start) {
                    if(command.equals("+"))
                        result += Double.parseDouble(jTextField.getText());
                    else if(command.equals("-"))
                        result -= Double.parseDouble(jTextField.getText());
                    else if(command.equals("*"))
                        result *= Double.parseDouble(jTextField.getText());
                    else if(command.equals("/")) {
                        if(Double.parseDouble(jTextField.getText()) != 0) {
                            result /= Double.parseDouble(jTextField.getText());
                        }else {
                            jTextField.setText(""+"对不起，除数不能为零");
                            JOptionPane.showMessageDialog(null, "对不起，除数不能为零", "Error!", JOptionPane.ERROR_MESSAGE);
                            command = "=";
                            start = true;
                            throw new ArithmeticException("除数为零");
                        }

                    }
                    else if(command.equals("="))
                        result = Double.parseDouble(jTextField.getText());

                    else if(command.equals("X^y"))
                        result = Math.pow(result, Double.parseDouble(jTextField.getText()));
                    jTextField.setText(""+getPrettyNumber(Double.toString(result)));
                    command = input;
                    start = true;
                }
            }
        }
    }
    //去掉小数点后没用的0
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }

    public static void main(String[] args) {
        MyCalculator myCalculator = new MyCalculator();

    }

}