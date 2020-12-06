package LeetCode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * 思路简述：参考 MakeSumDivisibleByP
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int sum = 0, res = 0;
        Map<Integer, Integer> preSumCount = new HashMap<>();
        preSumCount.put(0, 1);
        for (int num : nums) {
            sum += num;
            res += preSumCount.getOrDefault(sum - k, 0);
            preSumCount.compute(sum, (key ,v) -> {
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
