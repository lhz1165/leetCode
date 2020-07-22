package org.example.leetcode300.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/7/22
 **/
public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}
