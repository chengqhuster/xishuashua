package LeetCode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/contains-duplicate-iii/
 *
 * 思路简述：使用桶排序的方式，注意桶不一定要用连续的数组表示
 */
public class ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1) {
            return false;
        }
        // 桶内只存放一个元素即可
        Map<Long, Integer> bucketMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 桶的大小设置为t + 1，则内部任意两个元素a，b，满足 abs(a - b) <= t
            long idx = ((long) nums[i] - Integer.MIN_VALUE) / ((long) t + 1);
            if (bucketMap.containsKey(idx)
                    || (bucketMap.containsKey(idx - 1) && (long) nums[i] - bucketMap.get(idx - 1) <= t)
                    || (bucketMap.containsKey(idx + 1) && (long) bucketMap.get(idx + 1) - nums[i] <= t)) {
                return true;
            }
            bucketMap.put(idx, nums[i]);
            if (i >= k) {
                bucketMap.remove(((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1));
            }
        }
        return false;
    }
}
