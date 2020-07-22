package org.example.leetcode300.specified.bfs;

import org.example.leetcode300.basic.DirectedGraphNode;
import org.example.leetcode300.basic.UndirectedGraphNode;

import java.util.*;

/**
 *
 * 图的BFS
 * 使用Hash来记录某个点是否已经被放进queue来了
 * @author: lhz
 * @date: 2020/7/22
 **/
public class BFSGraphTest01 {
    public static void main(String[] args) {

    }
    /**
     * 图是否树
     *
     * 宽度优先搜索
     * 是否都连通 ，n个点，n-1条边
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        // write your code here

        return false;
    }



    /**
     * 克隆图
     * deep copy
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        return null;
    }

    /**
     * 拓扑排序 [重要]
     * 宽度优先搜索
     * @param graph`
     * @return
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> order = new ArrayList<>();
        if (graph == null) {
            return order;
        }
        //1 统计图每个点的入度
        Map<DirectedGraphNode, Integer> inDegree = getInDegree(graph);
        //2 拿到入度为0的点，作为起点
        ArrayList<DirectedGraphNode> startNodes = getStartNodes(inDegree, graph);

        //3 bfs
        order = bfs(inDegree,startNodes);
        if (order.size() == graph.size()) {
            return order;
        }
        return null;

    }

    private ArrayList<DirectedGraphNode> bfs(Map<DirectedGraphNode, Integer> inDegree,ArrayList<DirectedGraphNode> startNodes) {
        ArrayList<DirectedGraphNode> order = new ArrayList<>();
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : startNodes) {
            order.add(node);
            queue.offer(node);
        }

        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            for (DirectedGraphNode neighbor : node.neighbors) {
                inDegree.put(neighbor,inDegree.get(neighbor)-1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                    order.add(neighbor);
                }

            }
        }
        return order;
    }

    /**
     * 找出入度为0的点作为起点q
     * @param inDegree
     * @param graph
     * @return
     */
    private ArrayList<DirectedGraphNode> getStartNodes(Map<DirectedGraphNode, Integer> inDegree, ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> startNodes = new ArrayList<>();
        for (DirectedGraphNode directedGraphNode : graph) {
            if (inDegree.get(directedGraphNode) == 0) {
                startNodes.add(directedGraphNode);
            }
        }
        return startNodes;
    }

    /**
     * 每个点的入度是多少
     * @param graph
     * @return
     */
    private Map<DirectedGraphNode, Integer> getInDegree(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> in = new HashMap<>();
        int n = graph.size();
        for (int i = 0; i < n; i++) {
            //点的邻居们
            ArrayList<DirectedGraphNode> point = graph.get(i).neighbors;
            int m = point.size();
            // 记录每个点的入度
            for (int j = 0; j < m; j++) {
                //下一级的点
                DirectedGraphNode tmp = point.get(j);
                if (in.containsKey(tmp)) {
                    in.put(tmp, in.get(tmp) + 1);
                }
                else {
                    in.put(tmp, 1);
                }
            }
        }
        return in;

    }


}
