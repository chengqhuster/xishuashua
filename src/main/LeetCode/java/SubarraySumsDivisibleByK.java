package LeetCode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * 思路简述：参考 MakeSumDivisibleByP, 本题更加简单一些
 */
public class SubarraySumsDivisibleByK {

    public int subArraysDivByK(int[] A, int K) {
        int len = A.length, sumMod = 0, res = 0;
        Map<Integer, Integer> prefixSumModCountMap = new HashMap<>();
        prefixSumModCountMap.put(0, 1);
        for (int i = 0; i < len; i++) {
            sumMod += A[i];
            sumMod %= K;
            sumMod = sumMod < 0 ? sumMod + K : sumMod;
            res += prefixSumModCountMap.getOrDefault(sumMod, 0);
            prefixSumModCountMap.compute(sumMod, (k, v) -> {
                if (v == null) {
                    v = 1;
                } else {
                    v += 1;
                }
                return v;
            });
        }
        return res;
    }
}
