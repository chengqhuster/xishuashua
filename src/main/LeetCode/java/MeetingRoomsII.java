package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/meeting-rooms-ii/
 *
 * 思路简述：区间重叠数量问题，对端点进行排序即可，标识出起始端点
 *
 */
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        // 记录区间起始节点，第二个维度作为区间左右端点标识 -1表示左 1表示右
        int[][] points = new int[n * 2][2];
        for (int i = 0; i < n; i++) {
            points[2 * i][0] = intervals[i][0];
            points[2 * i][1] = -1;
            points[2 * i + 1][0] = intervals[i][1];
            points[2 * i + 1][1] = 1;
        }
        // 端点排序，位置相同时，右端点排在前面
        Arrays.sort(points, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        int res = 0, count = 0;
        for (int[] point : points) {
            if (point[1] > 0) {
                count--;
            } else {
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
