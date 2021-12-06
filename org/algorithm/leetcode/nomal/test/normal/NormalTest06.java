package org.algorithm.leetcode.nomal.test.normal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * Date: 2021/8/9
 * Description:
 *
 * @author hz.lai
 */
public class NormalTest06 {

    public static void main(String[] args) {
        NormalTest06 n = new NormalTest06();
        n.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
    }

    /**
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 128. 最长连续序列
     *
     * @param nums
     * @return
     */

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int longestStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }


    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<Point> queue = new LinkedList<>();
        HashSet<Point> isVisited = new HashSet<>();
        Point point = new Point(click[0], click[1]);
        queue.offer(point);
        isVisited.add(point);
        while (!queue.isEmpty()) {
            Point nowPoint = queue.poll();
            List<Point> neighbors = findNeighbors(nowPoint, board, isVisited);
            List<Point> booms = new ArrayList<>();
            //找出炸弹
            for (Point neighbor : neighbors) {
                if (board[neighbor.x][neighbor.y] == 'M') {
                    booms.add(neighbor);
                }
            }
            //根据炸弹来设置值
            for (Point boom : booms) {
                //炸弹附近的点
                //和中心点再水平线
                if (nowPoint.x == boom.x) {
                    for (Point neighbor : neighbors) {
                        if (neighbor.x == boom.x + 1 && neighbor.y == boom.y) {
                            neighbor.v++;
                        }
                        if (neighbor.x == boom.x - 1 && neighbor.y == boom.y) {
                            neighbor.v++;
                        }
                    }
                } else if (nowPoint.y == boom.y) {
                    for (Point neighbor : neighbors) {
                        if (neighbor.x == boom.x && neighbor.y == boom.y + 1) {
                            neighbor.v++;
                        }
                        if (neighbor.x == boom.x && neighbor.y == boom.y - 1) {
                            neighbor.v++;
                        }
                    }
                } else {
                    for (Point neighbor : neighbors) {
                        if (neighbor.x == boom.x  && neighbor.y == boom.y+1) {
                            neighbor.v++;
                        }
                        if (neighbor.x == boom.x - 1 && neighbor.y == boom.y) {
                            neighbor.v++;
                        }
                    }
                }
            }

        }
        return board;
    }

    public List<Point> findNeighbors(Point point, char[][] board, Set<Point> points) {
        int[] xOffset = {0, 0, 1, 1, 1, -1, -1, -1};
        int[] yOffset = {1, -1, -1, 0, 1, -1, 0, 1};
        List<Point> neighbors = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int x = point.x + xOffset[i];
            int y = point.y + yOffset[i];
            Point neighbor = new Point(x, y);
            if (isValid(neighbor, board, points)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public boolean isValid(Point point, char[][] board, Set<Point> points) {
        if (points.contains(point)) {
            return false;
        }
        if (point.x < 0 || point.x >= board.length) {
            return false;
        }
        if (point.y < 0 || point.y >= board[0].length) {
            return false;
        }
        return true;

    }

    static class Point {
        int x;
        int y;
        int v;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
