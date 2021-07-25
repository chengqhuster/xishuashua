package LeetCode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/tuple-with-same-product/
 *
 * 思路简述：把所有乘积的结果统计起来，存在重复的结果表示有符合要求的组合
 */
public class TupleWithSameProduct {

    public int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> productCountMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                productCountMap.compute(product, (k , v) -> v == null ? 1 : v + 1);
            }
        }
        int countSum = 0;
        for (Map.Entry<Integer, Integer> entry : productCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                // compute combination (take 2 from n)
                countSum += entry.getValue() * (entry.getValue() - 1) / 2;
            }
        }
        return countSum * 8;
    }
}
