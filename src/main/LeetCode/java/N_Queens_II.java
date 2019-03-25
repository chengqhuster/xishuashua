package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/n-queens-ii/
 *
 * 思路简述：参考N_Queens
 *
 */

public class N_Queens_II {
    long cmp;
    int res;

    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        cmp = (1 << n) - 1;
        long k = 0;
        long l = 0;
        long r = 0;
        solveNQueensCore(n, k, l, r);
        return res;
    }
    private void solveNQueensCore(int n, long k, long l, long r) {
        if (k == cmp) {
            res++;
        }
        long validPos = k | l | r;
        for (int i = 0; i < n; i++) {
            long t = (1 << (n - 1 - i));
            if ((validPos & t) == 0) {
                solveNQueensCore(n, k | t, (l | t) >> 1, ((r | t) << 1) & cmp);
            }
        }
    }
}
