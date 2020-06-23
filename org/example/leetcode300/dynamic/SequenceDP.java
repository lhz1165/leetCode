package org.example.leetcode300.dynamic;

/**
 * @author: lhz
 *
 * @date: 2020/6/23
 * 序列性动态规划问题
 **/
public class SequenceDP {
    public static void main(String[] args) {
        int c[][] = {{14, 2, 11}, {11, 14, 5}, {14, 3, 10}};
        System.out.println(minCost(c));
    }
    /**
     * 房子染色问题，相邻房子颜色不同
     * @param c 假设三栋房子，不同房子，不同颜色的价格{ {14,2,11},{11,14,5},{14,3,10}  }
     * @return
     */
    public static int minCost(int[][] c) {
        int houseNum = c.length;
        int colorCost = c[0].length;
        //序列型多开以为从0开始
        int f[][] = new int[houseNum + 1][colorCost];
        for (int i = 0; i < houseNum+1; i++) {
            // 当前这一栋枚举一种颜色
            for (int j = 0; j < colorCost; j++) {
                if (i == 0) {
                    //f[0][0]， f[0][1] ， f[0][2]=0
                    f[0][j] = 0;
                }else {
                    // 当前这一栋的前一栋应该取染成什么色
                    f[i][j] = Integer.MAX_VALUE;
                    for (int k = 0; k < 3; k++) {
                        //不撞色
                        if (k != j) {
                            //i-1染成k色 花费
                            f[i][j] = Math.min(f[i][j], f[i - 1][k] + c[i - 1][j]);
                        }
                    }

                }
            }
        }
        return Math.min(f[houseNum][0],Math.min(f[houseNum][1],f[houseNum][2]));
    }

}
