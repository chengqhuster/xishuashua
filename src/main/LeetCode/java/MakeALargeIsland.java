package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/making-a-large-island/
 *
 * 思路简述：dfs，先把各区域单独标记出来，在统计 0 与哪些孤岛相连，并计算总面积
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MakeALargeIsland {
    private int[] directs = new int[]{1, 0, -1, 0 ,1};
    int m, n;
    Map<Integer, Integer> map = new HashMap<>();

    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int startIndex = 2;
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Stack<int[]> stack = new Stack<>();
                    dfs(grid, visited, stack, i, j);
                    int count = 0;
                    while (!stack.isEmpty()) {
                        int[] pos = stack.pop();
                        grid[pos[0]][pos[1]] = startIndex;
                        count++;
                    }
                    map.put(startIndex, count);
                    startIndex++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int sum = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < directs.length - 1; k++) {
                        int p = i + directs[k];
                        int q = j + directs[k  + 1];
                        if (isValidPos(p, q) && grid[p][q] > 0) {
                            int index = grid[p][q];
                            if (!set.contains(index)) {
                                set.add(index);
                                sum += map.get(index);
                            }
                        }
                    }
                    res = Math.max(res, sum);
                }
            }
        }
        if (res > 0) {
            return res;
        } else {
            return m * n;
        }
    }

    private void dfs(int[][] grid, boolean[][] visited, Stack<int[]> stack, int x, int y) {
        stack.add(new int[]{x, y});
        visited[x][y] = true;
        for (int i = 0; i < directs.length -1; i++) {
            int p = x + directs[i];
            int q = y + directs[i + 1];
            if (isValidPos(p, q) && !visited[p][q] && grid[p][q] == 1) {
                dfs(grid, visited, stack, p, q);
            }
        }
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
