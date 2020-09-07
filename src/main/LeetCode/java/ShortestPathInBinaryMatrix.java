package LeetCode.java;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 题目描述：https://leetcode.com/problems/shortest-path-in-binary-matrix/
 *
 * 思路简述：BSF，宽度优先遍历，使用队列实现
 */
public class ShortestPathInBinaryMatrix {

    private final int[][] directs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        boolean[][] visited = new boolean[n][n];
        int[][] dis = new int[n][n];
        for (int[] disLine : dis) {
            Arrays.fill(disLine, Integer.MAX_VALUE);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0][0] = true;
        dis[0][0] = 1;
        bfs(visited, dis, grid, queue);
        if (dis[n - 1][n - 1] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dis[n - 1][n - 1];
        }
    }

    private void bfs(boolean[][] visited, int[][] dis, int[][] grid, LinkedList<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }
        int n = grid.length;
        int size = queue.size();
        while (size > 0) {
            int index = queue.poll();
            int x = index / n;
            int y = index % n;
            for (int[] direct : directs) {
                int xx = x + direct[0];
                int yy = y + direct[1];
                if (xx >= 0 && xx < n && yy >= 0 && yy < n && grid[xx][yy] == 0) {
                    if (!visited[xx][yy]) {
                        visited[xx][yy] = true;
                        queue.offer(xx * n + yy);
                    }
                    dis[xx][yy] = Math.min(dis[xx][yy], dis[x][y] + 1);
                }
            }
            size--;
        }
        bfs(visited, dis, grid, queue);
    }
}