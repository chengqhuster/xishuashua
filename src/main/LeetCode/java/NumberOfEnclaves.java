package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-enclaves/
 *
 * 思路简述：bfs，从边界出发
 *
 */

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves {
    int[] directs = new int[]{1, 0, -1, 0 ,1};
    int m, n;

    public int numEnclaves(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        m = A.length;
        n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        int sum = 0, count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    sum++;
                    if (isOnBoard(i, j)) {
                        count++;
                        visited[i][j] = true;
                        queue.add(i * n + j);
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            for (int i = 0; i < directs.length - 1; i++) {
                int x = pos / n + directs[i];
                int y = pos % n + directs[i + 1];
                if (isValidPos(x, y) && !visited[x][y] && A[x][y] == 1) {
                    count++;
                    visited[x][y] = true;
                    queue.add(x * n + y);
                }
            }
        }
        return sum - count;
    }

    private boolean isOnBoard(int x, int y) {
        return x == 0 || x == m -1 || y == 0 || y == n - 1;
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
