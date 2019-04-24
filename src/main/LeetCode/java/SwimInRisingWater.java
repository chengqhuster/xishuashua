package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/swim-in-rising-water/
 *
 * 思路简述：queueA 为最大堆，用于宽度优先遍历当前时刻能够到达的位置
 *          queueB 为最小堆，用于保存当前时刻无法到达的区域边界，即上述遍历过程无法进入的位置
 *          每个时刻遍历结束，若还未到达最右下位置，从queueB取出堆顶，该高度为下一次计算的时刻（queueB中该高度位置可能有多个，
 *          一次取出，减少计算过程）
 *
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRisingWater {
    int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<PosInfo> queueA = new PriorityQueue<>((o1, o2) -> o2.elevation - o1.elevation);
        Queue<PosInfo> queueB = new PriorityQueue<>((o1, o2) -> o1.elevation - o2.elevation);
        boolean[][] visited = new boolean[m][n];
        if (grid[0][0] <= res) {
            visited[0][0] = true;
            queueA.add(new PosInfo(0, 0, grid[0][0]));
        } else {
            queueB.add(new PosInfo(0, 0, grid[0][0]));
        }
        while (true) {
            while (!queueA.isEmpty()) {
                PosInfo info = queueA.poll();
                for (int[] move : moves) {
                    int xx = info.x + move[0];
                    int yy = info.y + move[1];
                    if (xx >= 0 && xx < m && yy >= 0 && yy < n && !visited[xx][yy]) {
                        if (info.elevation >= grid[xx][yy]) {
                            visited[xx][yy] = true;
                            queueA.add(new PosInfo(xx, yy, Math.max(grid[xx][yy], res)));
                        } else {
                            queueB.add(new PosInfo(xx, yy, grid[xx][yy]));
                        }
                    }
                }
            }
            if (visited[m - 1][n - 1]) {
                return res;
            } else {
                PosInfo info = queueB.poll();
                res = info.elevation;
                queueA.add(info);
                visited[info.x][info.y] = true;
                while (!queueB.isEmpty() && queueB.peek().elevation <= res) {
                    info = queueB.poll();
                    queueA.add(info);
                    visited[info.x][info.y] = true;
                }
            }
        }
    }

    class PosInfo {
        int x;
        int y;
        int elevation;

        public PosInfo(int x, int y, int elevation) {
            this.x = x;
            this.y = y;
            this.elevation = elevation;
        }
    }
}
