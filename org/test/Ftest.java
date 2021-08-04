package org.test;

import java.lang.reflect.Field;

/**
 * Created by: hz.lai
 * Date: 2021/6/4
 * Description:
 */
public class Ftest {
 public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
  O1 target = new O1();
  target.name = "aaa";

  Field h = target.getClass().getDeclaredField("name");
  h.setAccessible(true);
  Object o = h.get(target);
  System.out.println(o);
 }

 static class O1{
   String name;
 }
}
