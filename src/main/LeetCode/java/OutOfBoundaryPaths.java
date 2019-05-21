package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/out-of-boundary-paths/
 *
 * 思路简述：1. dfs + memory
 *
 */

import java.util.HashMap;

public class OutOfBoundaryPaths {
    static int MOD_NUM = 1000000007;
    int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    HashMap<PosInfo, Integer> map = new HashMap<>();

    public int findPaths(int m, int n, int N, int i, int j) {
        if (m <= 0 || n <= 0 || N <= 0) {
            return 0;
        }
        return dfs(m, n, N, i, j);
    }

    private int dfs(int m, int n, int N, int i, int j) {
        PosInfo info = new PosInfo(i, j, N);
        if (map.containsKey(info)) {
            return map.get(info);
        }
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (N <= 0) {
            return 0;
        }
        int count = 0;
        for (int[] move : moves) {
            int x = i + move[0];
            int y = j + move[1];
            count += dfs(m, n, N - 1, x, y);
            count = count % MOD_NUM;
        }
        map.put(info, count);
        return count;
    }

    class PosInfo {
        int x, y, t;

        PosInfo(int x, int y, int t) {
            this.x = x; this.y = y; this.t = t;
        }

        @Override
        public int hashCode() {
            String hashStr = "" + x + y + t;
            return hashStr.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof PosInfo) {
                PosInfo info = (PosInfo)obj;
                return info.x == x && info.y == y && info.t == t;
            } else {
                return false;
            }
        }
    }
}
