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
        System.out.println(w.eventualSafeNodes(new int[][]{{1,3,4,5},{},{},{},{}}));
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
        //各个点的出度
        Map<Integer, List<Integer>> indexTo = getTO(graph);
        Map<Integer, List<Integer>> indexToIn = goIn(graph);
        //找到初度为0的点
        List<Integer> noway = noway(graph);
        if (noway.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> list = bfs(noway, indexTo, indexToIn);
        Collections.sort(list);
        return list;

    }

    public Map<Integer, List<Integer>> getTO(int[][] graph) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            List<Integer> outList = new ArrayList<>();
            for (int g : graph[i]) {
                outList.add(g);
            }
            map.put(i, outList);
        }
        return map;
    }

    public Map<Integer, List<Integer>> goIn(int[][] graph) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            //入度的 key
            int[] inNodes = graph[i];
            for (int inNode : inNodes) {
                List<Integer> inList = map.get(inNode);
                if (inList != null) {
                    inList.add(i);
                }else {
                    map.put(inNode, new ArrayList<>(Arrays.asList(i)));
                }
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

    public List<Integer> bfs( List<Integer> noway, Map<Integer, List<Integer>> indexTo,Map<Integer, List<Integer>> indexToIn) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (Integer node : noway) {
            result.add(node);
            queue.offer(node);
        }
        while (!queue.isEmpty()) {
            //取一个结果
            Integer curNode = queue.poll();
            //找出所有的入度点
            List<Integer> InNodes = indexToIn.get(curNode);
            if (InNodes == null) {
                continue;
            }
            //判断 结果集 是否全部包含 入度点的所有出度
            for (Integer inNode : InNodes) {
                if (result.containsAll(indexTo.get(inNode))) {
                    result.add(inNode);
                    queue.offer(inNode);
                }
            }
        }
        return result;

    }



}
