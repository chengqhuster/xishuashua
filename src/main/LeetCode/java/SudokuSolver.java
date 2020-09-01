package LeetCode.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目描述：https://leetcode.com/problems/sudoku-solver/
 *
 * 思路简述：回溯法
 */
public class SudokuSolver {

    private final int len = 9;

    public void solveSudoku(char[][] board) {
        dfs(0, board);
    }

    private boolean dfs(int pos, char[][] board) {
        pos = getNextPos(pos, board);
        if (pos == len * len) {
            return true;
        }
        int i = pos / len;
        int j = pos % len;
        Set<Integer> possibleNums = getPossibleNums(i, j, board);
        for (int num : possibleNums) {
            board[i][j] = (char) ('0' + num);
            if (dfs(pos, board)) {
                return true;
            }
        }
        board[i][j] = '.';
        return false;
    }

    private int getNextPos(int pos, char[][] board) {
        while (pos < len * len) {
            int i = pos / len;
            int j = pos % len;
            if (board[i][j] == '.') {
                return pos;
            }
            pos++;
        }
        return pos;
    }

    private Set<Integer> getPossibleNums(int i, int j, char[][] board) {
        Set<Integer> init = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        // check horizontal and vertical direction
        for (int k = 0; k < len; k++) {
            if (k != j && board[i][k] != '.') {
                init.remove(board[i][k] - '0');
            }
            if (k != i && board[k][j] != '.') {
                init.remove(board[k][j] - '0');
            }
        }
        // check zone
        int zoneSize = 3;
        int xZone = i / zoneSize;
        int yZone = j / zoneSize;
        for (int p = xZone * zoneSize; p < (xZone + 1) * zoneSize; p++) {
            for (int q = yZone * zoneSize; q < (yZone + 1) * zoneSize; q++) {
                if (p != i && q != j && board[p][q] != '.') {
                    init.remove(board[p][q] - '0');
                }
            }
        }
        return init;
    }
}
