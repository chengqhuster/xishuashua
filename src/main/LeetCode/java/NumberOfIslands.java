package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-islands/description/
 *
 * 思路简述：宽度优先遍历 BSF 参考 Surrounded Regions
 *
 */

public class NumberOfIslands {
    // 上下左右移动
    private int[][] moves = new int[][]{{0, 1}, {0, -1}, {-1 ,0}, {1, 0}};

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int res = 0;
        char mark = 'a';
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    bfs(grid, i, j, mark);
                    res = res + 1;
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int x, int y, char mark) {
        grid[x][y] = mark;
        for(int[] move : moves) {
            int[] pos = new int[]{x + move[0], y + move[1]};
            if(isValidPos(pos, grid.length, grid[0].length) && grid[pos[0]][pos[1]] == '1') {
                bfs(grid, pos[0], pos[1], mark);
            }
        }
    }

    private boolean isValidPos(int[] pos, int x, int y) {
        return (pos[0] >= 0 && pos[0] < x && pos[1] >= 0 && pos[1] < y);
    }
}
