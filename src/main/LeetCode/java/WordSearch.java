package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/sort-colors/
 *
 * 思路简述：回溯 + memory（集合里面存放失败的情形，key 由坐标和 word 的索引位置决定）
 *
 */

public class WordSearch {
    private int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] state = new boolean[m][n];
        if (word.length() == 0) {
            return true;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    state[i][j] = true;
                    if (helper(board, word, new int[]{i, j}, 1, state)) {
                        return true;
                    }
                    state[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean helper(char[][] board, String word, int[] pos, int index, boolean[][] state) {
        int m = board.length;
        int n = board[0].length;
        if (index == word.length()) {
            return true;
        }
        for (int[] move : moves) {
            int xx = pos[0] + move[0];
            int yy = pos[1] + move[1];
            if (xx >= 0 && xx < m && yy >= 0 && yy < n && !state[xx][yy] && board[xx][yy] == word.charAt(index)) {
                state[xx][yy] = true;
                if (helper(board, word, new int[]{xx, yy}, index + 1, state)) {
                    return true;
                }
                state[xx][yy] = false;
            }
        }
        return false;
    }
}
