package org.algorithm.leetcode.nomal.test.normal;

import org.algorithm.leetcode.basic.DirectedGraphNode;

import java.util.*;

public class NormalTest05 {
    public static void main(String[] args) {
        NormalTest05 n = new NormalTest05();
        //[[1,4],[2,4],[3,1],[3,2]]
        System.out.println(n.canFinish(5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}}));

    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> results = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            return new ArrayList<>(Arrays.asList(nums[0]));
        }
        //f[i]表示下标i结尾的数组的最大整除长度
        int[] f = new int[n];
        f[0] = 1;
        Arrays.sort(nums);
        int maxLen = 1;
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            maxLen = Math.max(f[i], maxLen);
            maxIndex = maxLen == f[i] ? i : maxIndex;
            System.out.println(f[i]);
        }
        results.add(nums[maxIndex]);
        int prevLen = maxLen;
        int prev = nums[maxIndex];
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (f[i] == prevLen - 1 && prev % nums[i] == 0) {
                prevLen--;
                prev = nums[i];
                results.add(nums[i]);
            }
        }
        System.out.println(results);
        return results;
    }

    /**
     * 55. 跳跃游戏
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return true;
        }
        boolean[] f = new boolean[n];
        f[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && i - j <= nums[j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n - 1];
    }
    /**
     * 416 分割等子集
     * //输入：nums = [1,5,11,5]
     * //输出：true
     * //解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     */
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] f = new boolean[n];

    }

    /**
     * 课程表
     *5
     * [[1,4],[2,4],[3,1],[3,2]]
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Course> courseList = convertArrayToList(prerequisites);
        //先找到入度为0的点
        //1. 统计图每个点的入度
        Map<Course, Integer> inDegree = getInDegree(courseList);
        int origin = numCourses - inDegree.size();
        //2. 找出入度为0的点
        List<Course> startNodes = getStartNodes(inDegree, courseList);
        if (startNodes.isEmpty()) {
            return false;
        }
        //3. bfs
        List<Course> order = bfs(startNodes,inDegree);

        return order.size()+origin == numCourses;

    }

    private List<Course> bfs(List<Course> startNodes, Map<Course, Integer> inDegree) {
        List<Course> courses = new ArrayList<>();
        Queue<Course> queue = new LinkedList<>();
        for (Course startNode : startNodes) {
            queue.add(startNode);
            courses.add(startNode);
        }
        while (!queue.isEmpty()) {
            Course currentCourse = queue.poll();
            for (Course nextCourse : currentCourse.nextCourse) {
                inDegree.put(nextCourse, inDegree.get(nextCourse) - 1);
                if (inDegree.get(nextCourse) == 0) {
                    queue.offer(nextCourse);
                    courses.add(nextCourse);
                }
            }
        }

        return courses;
    }

    private static List<Course> getStartNodes(Map<Course, Integer> inDegree, List<Course> graph) {
        List<Course> startNodes = new ArrayList<>();
        for (Course directedGraphNode : graph) {
            if (inDegree.get(directedGraphNode) == 0) {
                startNodes.add(directedGraphNode);
            }
        }
        return startNodes;
    }

    private Map<Course, Integer> getInDegree(List<Course> courseList) {
        Map<Course, Integer> in = new HashMap<>();
        for (Course currentNode : courseList) {
            if (!in.containsKey(currentNode)) {
                in.put(currentNode, 0);
            }
            //点的邻居们
            List<Course> point = currentNode.nextCourse;
            int m = point.size();
            // 记录每个点的入度
            for (Course currentNeighbors : point) {
                if (in.containsKey(currentNeighbors)) {
                    in.put(currentNeighbors, in.get(currentNeighbors) + 1);
                } else {
                    in.put(currentNeighbors, 1);
                }
            }
        }
        return in;
    }

    private List<Course> convertArrayToList(int[][] prerequisites) {
        Map<Integer, Course> id2Course = new HashMap<>();
        List<Course> courses = new ArrayList<>();
        for (int[] coursePair : prerequisites) {

            int courseId = coursePair[0];
            //先修课程 表示入都
            int prepareCourseId = coursePair[1];
            Course course;
            Course prepareCourse;
            if (!id2Course.containsKey(courseId)) {
                course = new Course(courseId);
                courses.add(course);
                id2Course.put(courseId, course);
            } else {
                course = id2Course.get(courseId);
            }
            if (!id2Course.containsKey(prepareCourseId)) {
                prepareCourse = new Course(prepareCourseId);
                courses.add(prepareCourse);
                id2Course.put(prepareCourseId, prepareCourse);
            } else {
                prepareCourse = id2Course.get(prepareCourseId);
            }
            prepareCourse.nextCourse.add(course);
        }
        return courses;
    }

    static class Course {
        int courseId;
        List<Course> nextCourse;

        public Course(int courseId) {
            this.courseId = courseId;
            nextCourse = new ArrayList<>();
        }
    }
}
