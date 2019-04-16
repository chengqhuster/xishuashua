package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/pacific-atlantic-water-flow/
 *
 * 思路简述：1. dfs + memory 参考 LongestIncreasingPathInAMatrix，本题中的位置的传递关系为小于等于，需要把高等相等的邻接点作为一个
 *          整体一起处理（否则会导致循环处理）
 *          2. 从边界出发，Pacific 与 Atlantic 的边界分别处理，DFS 或 BFS 均可，使用队列（代码略）
 *
 */

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
    int[][] moves = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][][] mem = new boolean[m][n][2];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    List<Position> posList = new ArrayList<>();
                    posList.add(new Position(i, j));
                    dfs(matrix, visited, mem, res, posList);
                }
            }
        }
        return res;
    }

    private boolean[] dfs(int[][] matrix, boolean[][] visited, boolean[][][] mem, List<int[]>res, List<Position> posList) {
        boolean[] solve = new boolean[2];
        int m = visited.length;
        int n = visited[0].length;
        for (int i = 0; i < posList.size(); i++) {
            int x = posList.get(i).x;
            int y = posList.get(i).y;
            for (int[] move : moves) {
                int xx = x + move[0];
                int yy = y + move[1];
                if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
                    if (matrix[xx][yy] == matrix[x][y]) {
                        Position pos = new Position(xx, yy);
                        if (!posList.contains(pos)) {
                            posList.add(pos);
                        }
                    } else if (matrix[xx][yy] < matrix[x][y]) {
                        boolean[] temp;
                        if (visited[xx][yy]) {
                            temp = mem[xx][yy];
                        } else {
                            List<Position> nextPosSet = new ArrayList<>();
                            nextPosSet.add(new Position(xx, yy));
                            temp = dfs(matrix, visited, mem, res, nextPosSet);
                        }
                        solve[0] = solve[0] || temp[0];
                        solve[1] = solve[1] || temp[1];
                    }
                } else {
                    if (xx < 0 || yy < 0) {
                        solve[0] = true;
                    }
                    if (xx >= m || yy >= n) {
                        solve[1] = true;
                    }
                }
            }
        }
        if (solve[0] && solve[1]) {
            for (Position pos : posList) {
                res.add(new int[]{pos.x, pos.y});
            }
        }
        for (Position pos : posList) {
            visited[pos.x][pos.y] = true;
            mem[pos.x][pos.y] = solve;
        }
        return solve;
    }

    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Position) {
                Position anotherPos = (Position) obj;
                return anotherPos.x == x && anotherPos.y == y;
            }
            return false;
        }
    }
}
