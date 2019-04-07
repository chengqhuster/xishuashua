package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/course-schedule/
 *
 * 思路简述：bfs 一个课程需要依赖多少门其他的课程 用图上的入度来表示 宽度优先遍历
 *          用队列实现 入度为 0 的节点可入列
 *
 */

import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        int[][] adjacencyMatrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int [] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            // in case of duplication
            if (adjacencyMatrix[pre][course] == 0) {
                indegree[course]++;
            }
            adjacencyMatrix[pre][course] = 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < numCourses; i++) {
                if (adjacencyMatrix[node][i] == 1) {
                    indegree[i]--;
                    if (indegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
            count++;
        }
        return count == numCourses;
    }
}
