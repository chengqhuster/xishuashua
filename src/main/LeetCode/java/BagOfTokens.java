package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/bag-of-tokens/
 *
 * 思路简述：先排序，从小到大消费token，power不足时，从后往前兑换token（需保证能够继续消费）
 */
public class BagOfTokens {

    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        if (tokens.length == 0 || P < tokens[0]) {
            return 0;
        }
        int start = 0, end = tokens.length - 1, point = 0;
        while (start <= end) {
            if (P >= tokens[start]) {
                P -= tokens[start];
                point++;
                start++;
            } else {
                if (end - start <= 1) {
                    return point;
                } else {
                    P += tokens[end];
                    end--;
                    point--;
                }
            }
        }
        return point;
    }
}
