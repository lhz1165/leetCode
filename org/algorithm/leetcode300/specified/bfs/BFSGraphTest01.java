package org.algorithm.leetcode300.specified.bfs;

import org.algorithm.leetcode300.basic.DirectedGraphNode;
import org.algorithm.leetcode300.basic.UndirectedGraphNode;

import java.util.*;

/**
 * 图的BFS
 * 使用Hash来记录某个点是否已经被放进queue来了
 *
 * @author: lhz
 * @date: 2020/7/22
 **/
public class BFSGraphTest01 {
    public static void main(String[] args) {

        DirectedGraphNode n4 = new DirectedGraphNode(4);
        DirectedGraphNode n0 = new DirectedGraphNode(0);
        DirectedGraphNode n1 = new DirectedGraphNode(1);
        DirectedGraphNode n2 = new DirectedGraphNode(2);
        DirectedGraphNode n3 = new DirectedGraphNode(3);
        DirectedGraphNode n5 = new DirectedGraphNode(5);
        ArrayList<DirectedGraphNode> ne0 = new ArrayList<>();
        ne0.add(n1);
        ne0.add(n2);
        ne0.add(n3);
        n0.neighbors = ne0;
        ArrayList<DirectedGraphNode> ne1 = new ArrayList<>();
        ne1.add(n4);
        n1.neighbors = ne1;
        ArrayList<DirectedGraphNode> ne2 = new ArrayList<>();
        ne2.add(n4);
        ne2.add(n5);
        n2.neighbors = ne2;
        ArrayList<DirectedGraphNode> ne3 = new ArrayList<>();
        ne3.add(n4);
        ne3.add(n5);
        n3.neighbors = ne3;
        ArrayList<DirectedGraphNode> ne4 = new ArrayList<>();
        ArrayList<DirectedGraphNode> ne5 = new ArrayList<>();
        ArrayList<DirectedGraphNode> graph = new ArrayList<>();
        graph.add(n5);
        graph.add(n4);
        graph.add(n3);
        graph.add(n2);
        graph.add(n1);
        graph.add(n0);
        topSort(graph);

    }

    /**
     * 图是否树
     * <p>
     * 宽度优先搜索
     * 是否都连通 ，n个点，n-1条边
     *
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
     *
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
     *
     * @param graph`
     * @return
     */
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> order = new ArrayList<>();
        if (graph == null) {
            return order;
        }
        //1 统计图每个点的入度
        Map<DirectedGraphNode, Integer> inDegree = getInDegree(graph);
        //2 拿到入度为0的点，作为起点  0
        ArrayList<DirectedGraphNode> startNodes = getStartNodes(inDegree, graph);

        //3 bfs
        order = bfs(inDegree, startNodes);
        if (order.size() == graph.size()) {
            return order;
        }
        return null;

    }

    private static ArrayList<DirectedGraphNode> bfs(Map<DirectedGraphNode, Integer> inDegree, ArrayList<DirectedGraphNode> startNodes) {
        ArrayList<DirectedGraphNode> order = new ArrayList<>();
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : startNodes) {
            order.add(node);
            queue.offer(node);
        }
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            for (DirectedGraphNode neighbor : node.neighbors) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
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
     *
     * @param inDegree
     * @param graph
     * @return
     */
    private static ArrayList<DirectedGraphNode> getStartNodes(Map<DirectedGraphNode, Integer> inDegree, ArrayList<DirectedGraphNode> graph) {
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
     *
     * @param graph
     * @return
     */
    private static Map<DirectedGraphNode, Integer> getInDegree(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> in = new HashMap<>();
        int n = graph.size();
        for (int i = 0; i < n; i++) {
            DirectedGraphNode currentNode = graph.get(i);
            if (!in.containsKey(currentNode)) {
                in.put(currentNode, 0);
            }
            //点的邻居们
            ArrayList<DirectedGraphNode> point = currentNode.neighbors;
            int m = point.size();
            // 记录每个点的入度
            for (int j = 0; j < m; j++) {
                //下一级的点
                DirectedGraphNode tmp = point.get(j);
                if (in.containsKey(tmp)) {
                    in.put(tmp, in.get(tmp) + 1);
                } else {
                    in.put(tmp, 1);
                }
            }
        }
        return in;

    }


}
