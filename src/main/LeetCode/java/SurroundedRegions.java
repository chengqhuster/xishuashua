package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/surrounded-regions/description/
 *
 * 思路简述：dfs 遍历时候用stack保存邻接的'O'的位置，满足包围条件时把'O'换成'X'
 *
 */

import java.util.Stack;

public class SurroundedRegions {
    // 上下左右移动
    private int[][] moves = new int[][]{{0, 1}, {0, -1}, {-1 ,0}, {1, 0}};
    private boolean flag;
    private boolean[][] state;
    private int m, n;

    public void solve(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }
        m = board.length;
        n = board[0].length;
        state = new boolean[m][n];
        Stack<int[]> st = new Stack<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!state[i][j]) {
                    state[i][j] = true;
                    if (board[i][j] == 'O') {
                        flag = true;
                        st.removeAllElements();
                        bfs(board, i, j, st);
                        if (flag) {
                            while (!st.isEmpty()) {
                                int[] pos = st.pop();
                                board[pos[0]][pos[1]] = 'X';
                            }
                        }
                    }
                }
            }
        }

    }

    private void bfs(char[][] board, int i, int j, Stack<int[]> stack) {
        state[i][j] = true;
        stack.push(new int[]{i, j});
        for (int[] move : moves) {
            int x = i + move[0];
            int y = j + move[1];
            if (isValidPos(x, y)) {
                if (!state[x][y] && board[x][y] == 'O') {
                    bfs(board, x, y, stack);
                }
            } else {
                flag = false;
            }
        }
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
