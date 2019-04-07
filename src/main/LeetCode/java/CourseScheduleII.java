package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/course-schedule-ii/
 *
 * 思路简述：参考 CourseSchedule 用数组记录课程顺序
 *
 */

import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] adjacencyMatrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            // in case of duplication
            if (adjacencyMatrix[pre][course] == 0) {
                indegree[course]++;
            }
            adjacencyMatrix[pre][course] = 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res[count] = node;
            for (int i = 0; i < numCourses; i++) {
                if (adjacencyMatrix[node][i] == 1) {
                    if (indegree[i]-- == 0) {
                        queue.offer(i);
                    }
                }
            }
            count++;
        }
        if (count == numCourses) {
            return res;
        } else {
            return new int[]{};
        }
    }
}
