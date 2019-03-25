package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/n-queens/
 *
 * 思路简述：回溯法，通过位运算减少计算
 *
 */

import java.util.LinkedList;
import java.util.List;

public class N_Queens {
    long cmp;
    int[] pos;
    List<String> cache;

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
        if (n <= 0) {
            return res;
        }
        cmp = (1 << n) - 1;
        pos = new int[n];
        cache = new LinkedList<>();
        StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        for (int i = 0; i < n; i++) {
            sb.setCharAt(i, 'Q');
            cache.add(sb.toString());
            sb.setCharAt(i, '.');
        }
        long k = 0;
        long l = 0;
        long r = 0;
        solveNQueensCore(0, n, k, l, r, res);
        return res;
    }

    private void solveNQueensCore(int level, int n, long k, long l, long r, List<List<String>> res) {
        if (k == cmp) {
            res.add(getList());
        }
        long validPos = k | l | r;
        for (int i = 0; i < n; i++) {
            long t = (1 << (n - 1 - i));
            if ((validPos & t) == 0) {
                pos[level] = i;
                solveNQueensCore(level + 1, n, k | t, (l | t) >> 1, ((r | t) << 1) & cmp, res);
            }
        }
    }

    private List<String> getList() {
        List<String> sList = new LinkedList<>();
        for (int i = 0; i < pos.length; i++) {
            sList.add(cache.get(pos[i]));
        }
        return sList;
    }
}
