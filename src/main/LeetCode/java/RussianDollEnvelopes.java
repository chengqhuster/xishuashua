package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/russian-doll-envelopes/
 *
 * 思路简述：对 envelopes 按照第一个元素升序 第二个元素降序排列
 *          第二个元素形成的序列的 最长递增序列的长度 即为结果
 *          参考 LongestIncreasingSubsequence 解 2
 *
 */

import java.util.Arrays;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        int[] minLTS = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(minLTS, 0, len, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            minLTS[index] = envelope[1];
            if (index == len) {
                len++;
            }
        }
        return len;
    }
}
