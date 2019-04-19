package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-arithmetic-sequence/
 *
 * 思路简述：dp 记录到 i 为止 存在的所有的等差序列差值 以及 对应的最长序列长度（Map形式）
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestArithmeticSequence {
    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length < 2) {
            return 0;
        }
        int res = 0;
        List<Map<Integer, Integer>> maps = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            maps.add(new HashMap<>());
        }
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j];
                int count = 2;
                if (maps.get(j).containsKey(diff)) {
                    count = maps.get(j).get(diff) + 1;
                }
                if (maps.get(i).containsKey(diff)) {
                    count = Math.max(count, maps.get(i).get(diff));
                }
                res = Math.max(res, count);
                maps.get(i).put(diff, count);
            }
        }
        return res;
    }
}
