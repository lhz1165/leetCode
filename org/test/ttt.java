package org.test;

/**
 * Created by: hz.lai
 * Date: 2021/5/11
 * Description:
 */
public class ttt {
 public static void main(String[] args) {

  try {
   int i = 1 / 0;
  } catch (Exception e) {
   //e.printStackTrace();
  } finally {
   System.out.println(111);
  }
 }
}
