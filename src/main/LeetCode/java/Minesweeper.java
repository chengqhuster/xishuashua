package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/minesweeper/
 *
 * 思路简述：扫雷
 *
 */

public class Minesweeper {
    int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int hei, wid;

    public char[][] updateBoard(char[][] board, int[] click) {
        hei = board.length;
        wid = board[0].length;
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        }
        if (board[x][y] == 'E') {
            dfs(board, x, y);
        }
        boardPrint(board);
        return board;
    }

    private void boardPrint(char[][] board) {
        for (char[] chars : board) {
            for (char c: chars) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (board[x][y] != 'E') {
            return;
        }
        int count = getSurroundMines(board, x, y);
        if (count > 0) {
            board[x][y] = (char)('0' + count);
        } else {
            board[x][y] = 'B';
            for (int[] move : moves) {
                if (isValidPos(x + move[0], y + move[1])) {
                    dfs(board, x + move[0], y + move[1]);
                }
            }
        }
    }

    private int getSurroundMines(char[][] board, int x, int y) {
        int a, b, count = 0;
        for (int[] move : moves) {
            a = x + move[0];
            b = y + move[1];
            if (isValidPos(a, b) && board[a][b] == 'M') {
                count++;
            }
        }
        return count;
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < hei && y >= 0 && y < wid;
    }
}
