package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-squareful-arrays/
 *
 * 思路简述：参考 PermutationsII
 *
 */

import java.util.Arrays;

public class NumberOfSquarefulArrays {
    private int count = 0;

    public int numSquarefulPerms(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Arrays.sort(A);
        helper(A, new boolean[A.length], -1, 0);
        return count;
    }

    private void helper(int[] A, boolean[] visited, int last, int len) {
        if (len == A.length) {
            count++;
        } else {
            for (int i = 0; i < A.length; i++) {
                // 理解这里的判断条件
                if (visited[i] || (i > 0 && A[i] == A[i - 1] && !visited[i - 1])) {
                    continue;
                }
                if (last != -1) {
                    if (!isSquare(A[i], last)) {
                        continue;
                    }
                }
                visited[i] = true;
                helper(A, visited, A[i], len + 1);
                visited[i] = false;
            }
        }
    }

    private boolean isSquare(int a, int b){
        double sqr = Math.sqrt(a+b);
        return (sqr - Math.floor(sqr)) == 0;
    }
}
