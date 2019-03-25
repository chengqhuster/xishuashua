package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/candy
 *
 * 思路简述：
 *
 */

import java.util.Arrays;

public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int[] num = new int[ratings.length];
        Arrays.fill(num, 1);
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                num[i + 1] = num[i] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                num[i] = Math.max(num[i], num[i + 1] + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < ratings.length; i++) {
            res += num[i];
        }
        return res;
    }
}
