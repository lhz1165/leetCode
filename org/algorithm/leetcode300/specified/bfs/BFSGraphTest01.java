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
//    public static void main(String[] args) {
//
//        DirectedGraphNode n4 = new DirectedGraphNode(4);
//        DirectedGraphNode n0 = new DirectedGraphNode(0);
//        DirectedGraphNode n1 = new DirectedGraphNode(1);
//        DirectedGraphNode n2 = new DirectedGraphNode(2);
//        DirectedGraphNode n3 = new DirectedGraphNode(3);
//        DirectedGraphNode n5 = new DirectedGraphNode(5);
//        DirectedGraphNode n6 = new DirectedGraphNode(6);
//        ArrayList<DirectedGraphNode> ne0 = new ArrayList<>();
//        ne0.add(n1);
//        ne0.add(n2);
//        ne0.add(n3);
//        n0.neighbors = ne0;
//        ArrayList<DirectedGraphNode> ne1 = new ArrayList<>();
//        ne1.add(n4);
//        n1.neighbors = ne1;
//        ArrayList<DirectedGraphNode> ne2 = new ArrayList<>();
//        ne2.add(n4);
//        ne2.add(n5);
//        n2.neighbors = ne2;
//        ArrayList<DirectedGraphNode> ne3 = new ArrayList<>();
//        ne3.add(n4);
//        ne3.add(n5);
//        n3.neighbors = ne3;
//        ArrayList<DirectedGraphNode> ne6 = new ArrayList<>();
//        ne6.add(n3);
//        n6.neighbors = ne6;
//        ArrayList<DirectedGraphNode> graph = new ArrayList<>();
//        graph.add(n5);
//        graph.add(n4);
//        graph.add(n3);
//        graph.add(n2);
//        graph.add(n1);
//        graph.add(n0);
//        graph.add(n6);
//        topSort(graph);
//
//    }

    /**
     * 图是否树
     * <p>
     * 宽度优先搜索
     * 1.是否都连通 ，
     * 2.n个点，n-1条边
     *
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        //n个点，n-1条边
        if (n != edges.length + 1) {
            return false;
        }
       //点和他的邻居
        Map<Integer, Set<Integer>> map = initializeGraph(n, edges);
        //bfs验证是否联通
        Set<Integer> set = bfsValidTree(map);
        //是否所有的点都连通了
        return n == set.size();
    }

//    public static void main(String[] args) {
//        BFSGraphTest01 b = new BFSGraphTest01();
//        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
//        UndirectedGraphNode n2 = new UndirectedGraphNode(2);
//        UndirectedGraphNode n4 = new UndirectedGraphNode(4);
//        n1.neighbors = new ArrayList<>(Arrays.asList(n2, n4));
//        n2.neighbors = new ArrayList<>(Arrays.asList(n1, n4));
//        n4.neighbors = new ArrayList<>(Arrays.asList(n2, n1));
//        b.cloneGraph(n1);
//    }

    public Set<Integer> bfsValidTree(Map<Integer, Set<Integer>> map) {
        Queue<Integer> nodes = new LinkedList<>();
        Set<Integer> hasReach = new HashSet<>();
        nodes.add(0);
        while (!nodes.isEmpty() && !map.isEmpty()) {
            Integer node = nodes.poll();
            hasReach.add(node);
            Set<Integer> nebs = map.get(node);
            for (Integer neb : nebs) {
                if (!hasReach.contains(neb)) {
                    nodes.add(neb);
                }
            }

        }
        return hasReach;


    }

    //点和他的邻居点
    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
            Set<Integer> set = map.get(edges[i][0]);
            set.add(edges[i][1]);
            Set<Integer> set2 = map.get(edges[i][1]);
            set2.add(edges[i][0]);
        }
        return map;
    }


    /**
     * 克隆图
     * deep copy
     *
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (null == node) {
            return node;
        }
        // write your code here
        List<UndirectedGraphNode> allNodes = getNodes(node);
        Map<Integer, UndirectedGraphNode> newMap = new HashMap<>();
        for (UndirectedGraphNode curNode : allNodes) {
            UndirectedGraphNode newNode = getCloenNode(curNode, newMap);
            List<UndirectedGraphNode> neighbors = curNode.neighbors;
            for (UndirectedGraphNode neighbor : neighbors) {
                UndirectedGraphNode undirectedGraphNode = getCloenNode(neighbor, newMap);
                newNode.neighbors.add(undirectedGraphNode);
            }
        }
        return newMap.get(node.label);
    }

    public UndirectedGraphNode getCloenNode(UndirectedGraphNode curNode, Map<Integer, UndirectedGraphNode> newMap) {
        UndirectedGraphNode newNode = null;

        if (newMap.containsKey(curNode.label)) {
            newNode = newMap.get(curNode.label);
        } else {
            newNode = new UndirectedGraphNode(curNode.label);
            newMap.put(curNode.label, newNode);
        }
        return newNode;
    }


    //获取所有的点
    private List<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();

        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor : head.neighbors) {
                if (!set.contains(neighbor)) {
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return new ArrayList<>(set);
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
        for (DirectedGraphNode currentNode : graph) {
            if (!in.containsKey(currentNode)) {
                in.put(currentNode, 0);
            }
            //点的邻居们
            ArrayList<DirectedGraphNode> point = currentNode.neighbors;
            int m = point.size();
            // 记录每个点的入度
            for (DirectedGraphNode currentNeighbors : point) {
                if (in.containsKey(currentNeighbors)) {
                    in.put(currentNeighbors, in.get(currentNeighbors) + 1);
                } else {
                    in.put(currentNeighbors, 1);
                }

            }

        }
        return in;
    }

    /**
     * 搜索图中节点 · Search Graph Nodes
     * 给定一张 无向图，一个 节点 以及一个 目标值，返回距离这个节点最近 且 值为目标值的节点，如果不能找到则返回 NULL。
     * 在给出的参数中, 我们用 map 来存节点的值
     * 输入:
     * {1,2,3,4#2,1,3#3,1,2#4,1,5#5,4}
     * [3,4,5,50,50]
     * 1
     * 50
     * 输出:
     * 4
     * 解释:
     * 2------3  5
     * \     |  |
     * \    |  |
     * \   |  |
     * \  |  |
     * 1 --4
     * Give a node 1, target is 50
     * <p>
     * there a hash named values which is [3,4,10,50,50], represent:
     * Value of node 1 is 3
     * Value of node 2 is 4
     * Value of node 3 is 10
     * Value of node 4 is 50
     * Value of node 5 is 50
     * <p>
     * Return node 4
     * 太简单..
     */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {
        // Write your code here
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> hash = new HashSet<>();
        queue.offer(node);
        hash.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            if (values.get(head) == target) {
                return head;
            }
            for (UndirectedGraphNode nei : head.neighbors) {
                if (!hash.contains(nei)) {
                    queue.offer(nei);
                    hash.add(nei);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        boolean[][] ints = new boolean[][]{{true,true}};
        BFSGraphTest01 b = new BFSGraphTest01();
        b.numIslands(ints);

    }


    /**
     * [1,1,0,0,0],
     * [0,1,0,0,1],
     * [0,0,0,1,1],
     * [0,0,0,0,0],
     * [0,0,0,0,1]
     * <p>
     * 岛屿数量
     * 思路 bfs遍历，遇到1改成0 coutn++
     *
     * @param grid
     * @return
     */
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //把小岛变成
                if (grid[i][j]) {
                    bfsFill(i, j, grid);
                    count++;
                }
            }
        }

        return count;
    }













    /**
     * 给一个二维网格，每一个格子都有一个值，2 代表墙，1 代表僵尸，0 代表人类(数字 0, 1, 2)。
     * 僵尸每天可以将上下左右最接近的人类感染成僵尸，但不能穿墙。将所有人类感染为僵尸需要多久，如果不能感染所有人则返回 -1
     *
     * @param grid
     * @return
     */
    public int zombie(int[][] grid) {
        // write your code here
        int PEOPLE = 0;
        int ZOMBIE = 1;
        int WALL = 2;
        int[] deltaX = new int[]{1, 0, 0, -1};
        int[] deltaY = new int[]{0, 1, -1, 0};
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int people = 0;
        Queue<NodeZ> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == PEOPLE) {
                    people++;
                } else if (grid[i][j] == ZOMBIE) {
                    queue.offer(new NodeZ(i, j));
                }
            }
        }
        int days = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            days++;
            for (int i = 0; i < size; i++) {
                NodeZ zb = queue.poll();
                //上下左右感染
                for (int direction = 0; direction < 4; direction++) {
                    int x = zb.i + deltaX[direction];
                    int y = zb.j + deltaY[direction];
                    NodeZ adj = new NodeZ(x,y);
                    //不是人不感染
                    if (!isPeople(adj, grid)) {
                        continue;
                    }
                    grid[x][y] = ZOMBIE;
                    people--;
                    if (people == 0) {
                        return days;
                    }
                    queue.offer(adj);
                }
            }
        }
        return -1;
    }

    private boolean isPeople(NodeZ adj, int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (adj.i < 0 || adj.i >= n) {
            return false;
        }
        if (adj.j < 0 || adj.j >= m) {
            return false;
        }
        return (grid[adj.i][adj.j] == 0);
    }




    public void bfsFill(int i,int j,boolean[][] grid) {
        int n = grid.length;//有几行
        int m = grid[0].length; //有几列
        int[] directionX = new int[]{0, 0, 1, -1};
        int[] directionY = new int[]{1, -1, 0, 0};
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(i, j));
        grid[i][j] = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int k = 0; k < 4; k++) {
                int x = node.i + directionX[k];
                int y = node.j + directionY[k];
                if (x < 0 || x >= n) {
                    continue;
                }
                if (y < 0 || y >= m) {
                    continue;
                }
                if (grid[x][y]) {
                    grid[node.i][node.j] = false;

                    queue.offer(new Node(x, y));
                }
            }
        }

    }

    public Node getNode(int i, int j, List<List<Node>> grids) {
        for (int i1 = 0; i1 < grids.size(); i1++) {
            for (int i2 = 0; i2 < grids.get(i1).size(); i2++) {
                if (i == i1 && j == i2) {
                    return grids.get(i1)
                            .get(i2);
                }
            }
        }
        return null;

    }




}

class Node {
    int i;
    int j;


    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
class NodeZ {
    int i;
    int j;

    public NodeZ(int i, int j) {
        this.i = i;
        this.j = j;
    }
}







