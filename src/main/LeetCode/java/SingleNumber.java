package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/single-number/
 *
 * 思路简述：a ^ a = 0;
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums).reduce(0, (acc, item) -> acc ^ item);
    }
}
