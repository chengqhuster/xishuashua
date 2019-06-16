package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/numbers-with-same-consecutive-differences/
 *
 * 思路简述：easy job
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersWithSameConsecutiveDifferences {
    public int[] numsSameConsecDiff(int N, int K) {
        if (N <= 0 || K < 0 || K > 10) {
            return new int[0];
        }
        int[] temp = new int[N];
        List<Integer> res = new ArrayList<>();
        if (N == 1) {
            res.add(0);
        }
        for (int i = 1; i < 10; i++) {
            temp[0] = i;
            dfs(temp, 1, K, res);
        }
        Integer[] integers = res.toArray(new Integer[0]);
        return Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();
    }

    private void dfs(int[] temp, int pos, int K, List<Integer> res) {
        int n = temp.length;
        if (pos == n) {
            res.add(getNum(temp));
            return;
        }
        if (temp[pos - 1] >= K) {
            temp[pos] = temp[pos - 1] - K;
            dfs(temp, pos + 1, K, res);
        }
        if (K != 0 && temp[pos - 1] + K < 10) {
            temp[pos] = temp[pos - 1] + K;
            dfs(temp, pos + 1, K, res);
        }
    }

    private int getNum(int[] temp) {
        int res = 0;
        for (int i = 0; i < temp.length; i++) {
            res = res * 10 + temp[i];
        }
        return res;
    }
}
