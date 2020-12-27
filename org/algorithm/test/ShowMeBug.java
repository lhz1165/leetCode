package org.algorithm.test;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author: lhz
 * @date: 2020/9/11
 **/
public class ShowMeBug {

    public static void main(String[] args) {
        ShowMeBug s = new ShowMeBug();
        Integer[] result = s.getRes(100,10);
        System.out.println(Arrays.toString(result));
    }

    public Integer[] getRes(Integer totalReward,Integer num){
        Integer[] avgs = new Integer[num];
        Integer avg = totalReward / num;
        Arrays.fill(avgs,avg);
        Random r = new Random();
        for(int i = 0; i < avgs.length; i++){
            Integer transferMoney = r.nextInt(avg);
            int j  = r.nextInt(num);
            System.out.println(j);
            avgs[i] -= transferMoney;
            while (avgs[j] + transferMoney  > totalReward * 0.9) {
                 j  = r.nextInt(num);
            }
            avgs[j] += transferMoney;
        }
        return avgs;
    }


}

