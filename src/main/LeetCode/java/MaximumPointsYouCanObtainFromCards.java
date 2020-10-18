package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 *
 * 思路简述：双向求累加和，然后合并计算
 */
public class MaximumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] preSum = new int[len + 1], backSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i - 1] + cardPoints[i - 1];
            backSum[i] = backSum[i - 1] + cardPoints[len - i];
        }
        int res = 0;
        for (int i = 0; i <= k; i++) {
            res = Math.max(res, preSum[i] + backSum[k -i]);
        }
        return res;
    }
}
