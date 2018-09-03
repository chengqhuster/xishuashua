package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/surrounded-regions/description/
 *
 * 思路简述：宽度优先遍历 BSF 从边界 o 节点出发将所有与之存在邻接关系的 o 节点标记
 *         为n(不需要被覆盖)
 */

public class SurroundedRegions {
    // 上下左右移动
    private int[][] moves = new int[][]{{0, 1}, {0, -1}, {-1 ,0}, {1, 0}};

    public void solve(char[][] board) {
        if(board == null || board.length == 0)
            return;
        int X = board.length;
        int Y = board[0].length;
        // 将不需要被覆盖的 o 替换为 n
        for(int i=0; i<X; i++) {
            if(board[i][0] == 'o') {
                bfs(board, i, 0);
            }
            if(board[i][Y-1] == 'o') {
                bfs(board, i, Y-1);
            }
        }
        for(int j=0; j<Y; j++) {
            if(board[0][j] == 'o') {
                bfs(board, 0, j);
            }
            if(board[X-1][j] == 'o') {
                bfs(board, X-1, j);
            }
        }
        // o -> x
        for(int i=0; i<X; i++) {
            for(int j=0; j<Y; j++) {
                if(board[i][j] == 'o')
                    board[i][j] = 'x';
            }
        }
        // n -> o
        for(int i=0; i<X; i++) {
            for(int j=0; j<Y; j++) {
                if(board[i][j] == 'n')
                    board[i][j] = 'o';
            }
        }
    }

    private void bfs(char[][] board, int i, int j) {
        board[i][j] = 'n';
        int X = board.length;
        int Y = board[0].length;
        for(int[] move : moves) {
            int[] pos = new int[]{i + move[0], j + move[1]};
            if(isValidPos(pos, X, Y) && board[pos[0]][pos[1]] == 'o')
                bfs(board, pos[0], pos[1]);
        }
    }

    private boolean isValidPos(int[] pos, int X, int Y) {
        return (pos[0] >= 0 && pos[0] < X && pos[1] >= 0 && pos[1] < Y);
    }
}
