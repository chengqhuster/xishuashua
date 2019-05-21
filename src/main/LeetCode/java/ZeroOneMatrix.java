package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/01-matrix/
 *
 * 思路简述：1. dfs (递归过深 StackOverFlow)
 *          2. 从 0 出发，优先队列（事实上不需要优先队列，普通队列也可以，因为入列的元素一定是从小到大的顺序）
 *
 */

import java.util.LinkedList;

public class ZeroOneMatrix {
    private int len, wid;
    int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        len = matrix.length;
        wid = matrix[0].length;
        boolean[][] state = new boolean[len][wid];
        LinkedList<PointInfo> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < wid; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new PointInfo(i, j, 0));
                    state[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            PointInfo info = queue.poll();
            for (int[] move :  moves) {
                int x = info.x + move[0];
                int y = info.y + move[1];
                if (isValidPos(x, y) && !state[x][y]) {
                    matrix[x][y] = matrix[info.x][info.y] + 1;
                    state[x][y] = true;
                    queue.add(new PointInfo(x, y, matrix[x][y]));
                }
            }
        }
        return matrix;
    }

    private boolean isValidPos(int x, int y) {
        return x >=0 && x < len && y >= 0 && y < wid;
    }

    class PointInfo {
        int x, y, val;

        PointInfo(int x, int y, int val) {
            this.x = x; this.y = y; this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}

class ZeroOneMatrixSVF {
    int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][] state;
    int len, wid;

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        len = matrix.length;
        wid = matrix[0].length;
        int[][] res = new int[len][wid];
        state = new boolean[len][wid];
        initState(matrix, state);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < wid; j++) {
                if (!state[i][j]) {
                    dfs(res, state, i, j);
                }
            }
        }
        return res;
    }

    private void initState(int[][] matrix, boolean[][] state) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < wid; j++) {
                if (matrix[i][j] == 0) {
                    state[i][j] = true;
                }
            }
        }
    }

    private int dfs(int[][] res, boolean[][] state, int x, int y) {
        if (state[x][y]) {
            return res[x][y];
        } else {
            int min = Integer.MAX_VALUE;
            for (int[] move : moves) {
                if (isValidPos(x + move[0], y + move[1])) {
                    min = Math.min(min, dfs(res, state, x + move[0], y + move[1]));
                }
            }
            state[x][y] = true;
            res[x][y] = min;
            return min;
        }
    }

    private boolean isValidPos(int x, int y) {
        return x >=0 && x < len && y >= 0 && y < wid;
    }
}
