package org.example.leetcode300.basic;

import java.util.ArrayList;

/**
 * @author: lhz
 * @date: 2020/7/22
 **/
public class DirectedGraphNode {
    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
