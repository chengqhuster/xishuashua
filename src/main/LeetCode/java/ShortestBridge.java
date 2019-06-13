package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/shortest-bridge/
 *
 * 思路简述：宽度优先遍历
 *
 */

import java.util.LinkedList;

public class ShortestBridge {
    int m, n;
    int[] moves = new int[]{1, 0, -1, 0, 1};

    public int shortestBridge(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        m = A.length;
        n = A[0].length;
        // find a 1 pos
        int startX = 0, startY = 0;
        outer:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                if (A[i][j] == 1) {
                    startX = i;
                    startY = j;
                    break outer;
                }
            }
        }
        if (A[startX][startY] == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[m][n];
        LinkedList<Position> queue = new LinkedList<>();
        getBorder(startX, startY, A, visited, queue);
        int count  = 0;
        Position target = queue.peekLast();
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            for (int i = 0; i < moves.length - 1; i++) {
                int xx = pos.x + moves[i];
                int yy = pos.y + moves[i + 1];
                if (isValidPos(xx, yy) && !visited[xx][yy]) {
                    if (A[xx][yy] == 1) {
                        return count;
                    } else {
                        visited[xx][yy] = true;
                        queue.add(new Position(xx, yy));
                    }
                }
            }
            if (pos.equals(target)) {
                count++;
                if (queue.isEmpty()) {
                    return 0;
                } else {
                    target = queue.peekLast();
                }
            }
        }
        return 0;
    }

    private void getBorder(int x, int y, int[][] A, boolean[][] visited, LinkedList<Position> queue) {
        if (isBorderPos(x, y, A)) {
            queue.add(new Position(x, y));
        }
        visited[x][y] = true;
        for (int i = 0; i < moves.length - 1; i++) {
            int xx = x + moves[i];
            int yy = y + moves[i + 1];
            if (isValidPos(xx, yy) && !visited[xx][yy] && A[xx][yy] == 1) {
                getBorder(xx, yy, A, visited, queue);
            }
        }
    }

    private boolean isBorderPos(int x, int y, int[][] A) {
        for (int i = 0; i < moves.length - 1; i++) {
            int xx = x + moves[i];
            int yy = y + moves[i + 1];
            if (isValidPos(xx, yy) && A[xx][yy] == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else {
                if (obj instanceof Position) {
                    Position cmp = (Position)obj;
                    return cmp.x == x && cmp.y == y;
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return ("" + x + y).hashCode();
        }
    }
}
