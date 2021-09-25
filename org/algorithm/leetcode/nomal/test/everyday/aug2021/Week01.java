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

    /**
     *802. 找到最终的安全状态
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //出度数量
        List<Integer>  indexToNum = getTO(graph);
        //入度有哪些点
        Map<Integer, List<Integer>> indexToIn = goIn(graph);


        //找到初度为0的点
        List<Integer> noway = noway(graph);
        if (noway.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> list = bfs(noway, indexToNum, indexToIn);
        Collections.sort(list);
        return list;

    }

    public List<Integer> getTO(int[][] graph) {
        List<Integer> toNum = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            toNum.add(graph[i].length);
        }
        return toNum;
    }

    public Map<Integer, List<Integer>> goIn(int[][] graph) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < graph.length; i++) {
            //入度的 key
            int[] inNodes = graph[i];
            for (int inNode : inNodes) {
                List<Integer> inList = map.get(inNode);
                inList.add(i);
            }
        }
        return map;
    }

    public List<Integer> noway(int[][] graph) {
        List<Integer> noway = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0) {
                noway.add(i);
            }
        }
        return noway;
    }

    public List<Integer> bfs(List<Integer> noway, List<Integer> indexTo, Map<Integer, List<Integer>> indexToIn) {
        Queue<Integer> queue = new LinkedList<>(noway);
        while (!queue.isEmpty()) {
            //取一个结果
            Integer curNode = queue.poll();
            //找出该点的所有的入度点
            List<Integer> InNodes = indexToIn.get(curNode);
            for (Integer inNode : InNodes) {
                indexTo.set(inNode, indexTo.get(inNode) - 1);
                if (indexTo.get(inNode) == 0) {
                    queue.offer(inNode);
                }
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < indexTo.size(); i++) {
            if (indexTo.get(i) == 0) {
                ans.add(i);
            }
        }
        return ans;
    }





}
