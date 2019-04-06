package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/container-with-most-water-ii/
 *
 * 思路简述：参考 https://leetcode.com/problems/trapping-rain-water-ii/discuss/89461/Java-solution-using-PriorityQueue
 *          参考 TrappingRainWater 由外向内 边界的思想
 *
 */

import java.util.PriorityQueue;

public class TrappingRainWaterII {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
            return 0;
        }
        int res = 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Cell> queue = new PriorityQueue<>((o1, o2) -> o1.height - o2.height);
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heightMap[i][0]));
            queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
        }
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heightMap[0][i]));
            queue.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
        }
        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] move : moves) {
                int row = cell.row + move[0];
                int col = cell.col + move[1];
                if (row >= 0 && col >= 0 && row < m && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    // key point, the propagation of border height
                    queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height)));
                    if (cell.height > heightMap[row][col]) {
                        res += cell.height - heightMap[row][col];
                    }
                }
            }
        }
        return res;
    }

    public class Cell {
        int row;
        int col;
        int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
}
