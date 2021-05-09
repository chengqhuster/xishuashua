package LeetCode.java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 题目描述：https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/
 *
 * 思路简述：移动方向上有限制 相同目标位置不同路径的移动距离是相同的 宽度优先遍历
 */
public class MinimumMovesToReachTargetWithRotations {

    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        int[][] start = new int[][]{{0, 0}, {0, 1}};
        int[][] end = new int[][]{{n - 1, n - 2}, {n - 1, n - 1}};
        int target = getSnakePosNum(end, n);
        Queue<int[][]> queue = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        queue.offer(start);
        visited.put(getSnakePosNum(start, n), 0);
        while (!queue.isEmpty()) {
            int[][] pos = queue.poll();
            int count = visited.get(getSnakePosNum(pos, n));
            // move right
            int[][] rightPos = new int[][]{{pos[0][0], pos[0][1] + 1}, {pos[1][0], pos[1][1] + 1}};
            if (checkBlockGrid(rightPos, grid) && !visited.containsKey(getSnakePosNum(rightPos, n))) {
                queue.offer(rightPos);
                visited.put(getSnakePosNum(rightPos, n), count + 1);
            }
            // move down
            int[][] downPos = new int[][]{{pos[0][0] + 1, pos[0][1]}, {pos[1][0] + 1, pos[1][1]}};
            if (checkBlockGrid(downPos, grid) && !visited.containsKey(getSnakePosNum(downPos, n))) {
                queue.offer(downPos);
                visited.put(getSnakePosNum(downPos, n), count + 1);
            }
            if (pos[0][0] + 1 < n && pos[0][1] + 1 < n && grid[pos[0][0] + 1][pos[0][1] + 1] == 0) {
                if (pos[0][0] == pos[1][0]) {
                    // rotate clockwise
                    int[][] rotateWise = new int[][]{{pos[0][0], pos[0][1]}, {pos[1][0] + 1, pos[1][1] - 1}};
                    if (checkBlockGrid(rotateWise, grid) && !visited.containsKey(getSnakePosNum(rotateWise, n))) {
                        queue.offer(rotateWise);
                        visited.put(getSnakePosNum(rotateWise, n), count + 1);
                    }
                } else {
                    // rotate counterclockwise
                    int[][] rotateCounter = new int[][]{{pos[0][0], pos[0][1]}, {pos[1][0] - 1, pos[1][1] + 1}};
                    if (checkBlockGrid(rotateCounter, grid) && !visited.containsKey(getSnakePosNum(rotateCounter, n))) {
                        queue.offer(rotateCounter);
                        visited.put(getSnakePosNum(rotateCounter, n), count + 1);
                    }
                }
            }
            if (visited.containsKey(target)) {
                return visited.get(target);
            }
        }
        return -1;
    }

    private boolean checkBlockGrid(int[][] pos, int[][] grid) {
        return pos[1][0] < grid.length
                && pos[1][1] < grid.length
                && grid[pos[0][0]][pos[0][1]] == 0
                && grid[pos[1][0]][pos[1][1]] == 0;
    }

    private int getSnakePosNum(int[][] snake, int n) {
        return getNodePosNum(snake[0], n) + getNodePosNum(snake[1], n) * n * n;
    }

    private int getNodePosNum(int[] pos, int n) {
        return pos[0] * n + pos[1];
    }
}
