package org.algorithm.leetcode300.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/22
 **/
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}
