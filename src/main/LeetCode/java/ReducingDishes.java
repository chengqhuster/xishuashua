package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/reducing-dishes/
 *
 * 思路简述：先排序，再统计
 */
public class ReducingDishes {

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int fistNonNegativeIndex = 0;
        while (fistNonNegativeIndex < satisfaction.length && satisfaction[fistNonNegativeIndex] < 0) {
            fistNonNegativeIndex++;
        }
        int nonNegativeBase = 0, nonNegativeSum = 0;
        for (int i = fistNonNegativeIndex; i < satisfaction.length; i++) {
            nonNegativeBase += satisfaction[i] * (i - fistNonNegativeIndex + 1);
            nonNegativeSum += satisfaction[i];
        }
        int res = nonNegativeBase;
        int negativeBase = 0, negativeSum = 0;
        for (int i = fistNonNegativeIndex - 1; i >= 0; i--) {
            negativeBase += satisfaction[i] + negativeSum;
            negativeSum += satisfaction[i];
            nonNegativeBase += nonNegativeSum;
            res = Math.max(res, negativeBase + nonNegativeBase);
        }
        return res;
    }
}
