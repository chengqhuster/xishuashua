package LeetCode.java;

import java.util.Arrays;
import java.util.Random;

/**
 * 题目描述：https://leetcode.com/problems/random-pick-with-weight/
 *
 * 思路简述：加权概率
 */
public class RandomPickWithWeight {
    private int[] weights;
    private int[] prefixSumWeights;
    private int sum;
    private Random random;

    public RandomPickWithWeight(int[] w) {
        weights = w;
        sum = w[0];
        prefixSumWeights = new int[w.length];
        for (int i = 1; i < w.length; i++) {
            prefixSumWeights[i] = sum;
            sum += w[i];
        }
        random = new Random();
    }

    public int pickIndex() {
        int next = random.nextInt(sum);
        int index = Arrays.binarySearch(prefixSumWeights, next);
        return index >= 0 ? index : - index - 2;
    }
}
