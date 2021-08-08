package org.algorithm.leetcode.nomal.test.everyday.aug2021;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.*;

/**
 * Date: 2021/8/4
 * Description:
 *
 * @author hz.lai
 */
public class Week01 {


    /**
     * 611 有效三角形个数
     *
     * (固定一条最长边)
     * @param nums
     * @return
     */
    public int triangleNumber3(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = n - 1; i >= 2; --i) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    --r;
                } else {
                    ++l;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Week01 w = new Week01();
        System.out.println(w.eventualSafeNodes(new int[][]{{1,3,4,5},{},{},{},{},{2,4}}));
    }
    //[[1,2],[2,3],[5],[0],[5],[],[]]
    //[1,2,3,4],[1,2],[3,4],[0,4],[]

    /**
     * [[1,3,4,5],[],[],[],[],[2,4]]
     * 输出：
     * [0,0,0,0,1,2,3,4,5,5]
     * 预期结果：
     * [0,1,2,3,4,5]
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> rg = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i) {
            rg.add(new ArrayList<Integer>());
        }
        //统计所有点的度
        int[] inDeg = new int[n];
        for (int x = 0; x < n; ++x) {
            for (int y : graph[x]) {
                //入度
                rg.get(y).add(x);
            }
            //出度
            inDeg[x] = graph[x].length;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int y = queue.poll();
            //
            List<Integer> indrgee = rg.get(y);
            for (int x : indrgee) {
                if (--inDeg[x] == 0) {
                    queue.offer(x);
                }
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;

    }





}
