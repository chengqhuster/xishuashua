package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/design-tic-tac-toe/
 *
 * 思路简述：每次落子的时候检测
 */
public class TicTacToe {

    private static final int[][] DIRECT_A = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}};

    private static final int[][] DIRECT_B = {{1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    private int[][] board;

    /**
     * 游戏状态 0 准备，1 进行，2 结束
     */
    private int state;

    /**
     * 胜者，游戏结束时确定
     */
    private int winner;

    public TicTacToe(int n) {
        board = new int[n][n];
        state = 1;
    }

    /**
     * 获胜返回 1，否则返回 0
     */
    public int move(int row, int col, int player) {
        if (state != 1) {
            throw new IllegalStateException("Game is not running!");
        }
        if (row < 0 || col < 0 || row >= board.length || col >= board.length || board[row][col] > 0) {
            throw new IllegalArgumentException("Param error");
        }
        board[row][col] = player;
        if (winCheck(row, col, player)) {
            state = 2;
            winner = player;
            return player;
        }
        return 0;
    }

    private boolean winCheck(int row, int col, int player) {
        int n = board.length;
        for (int i = 0; i < DIRECT_A.length; i++) {
            int countA = 0, countB = 0;
            countA = getCount(row, col, player, n, i, countA, DIRECT_A);
            countB = getCount(row, col, player, n, i, countB, DIRECT_B);
            if (countA + countB - 1 >= n) {
                return true;
            }
        }
        return false;
    }

    private int getCount(int row, int col, int player, int n, int i, int count, int[][] direct) {
        int r;
        int c;
        do {
            count++;
            r = row + direct[i][0] * count;
            c = col + direct[i][1] * count;
        } while (r >= 0 && r < n && c >= 0 && c < n && board[r][c] == player);
        return count;
    }
}
