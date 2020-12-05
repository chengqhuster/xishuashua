package LeetCode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/make-sum-divisible-by-p/
 *
 * 思路简述：记 sum(nums) mod p = a, 问题即转化为求最短的连续子数字串,
 * 其数字和为 part_sum = sum(part(nums)), 满足 part_sum mod p = a
 * 即该子串(m,n) 满足
 * sum(nums[0, n]) mod p - sum(nums[0, m]) mod p = a
 * 记 MODk = sum(nums[0, k]) mod p
 * 有 MODm = (MODn - a + p) mod p
 * 利用 map 记录遍历过程中 MOD(prefix(nums)) == k 的最大索引位置
 */
public class MakeSumDivisibleByP {

    public int minSubarray(int[] nums, int p) {
        int sumMod = 0;
        for (int i = 0; i < nums.length; i++) {
            sumMod += nums[i];
            sumMod %= p;
        }
        if (sumMod == 0) {
            return 0;
        }
        Map<Integer, Integer> modValueIndexMap = new HashMap<>();
        modValueIndexMap.put(0, -1);
        int res = nums.length, modValue = 0;
        for (int i = 0; i < nums.length; i++) {
            modValue += nums[i];
            modValue %= p;
            int targetMod = (p + modValue - sumMod) % p;
            if (modValueIndexMap.containsKey(targetMod)) {
                res = Math.min(res, i - modValueIndexMap.get(targetMod));
            }
            modValueIndexMap.put(modValue, i);
        }
        return res == nums.length ? -1 : res;
    }
}
