package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/3sum/
 *
 * 思路简述：先排序，然后遍历数组 a + b + c = 0 ----> b + c = -a
 *          用首尾指针来寻找符合条件的解（注意去掉重复解）
 *
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i <= n - 3; i++) {
            if (i > 0 && nums[i] == nums[i  -1]) {
                continue;
            }
            int head = i + 1;
            int tail = n - 1;
            int target = -nums[i];
            while (head < tail) {
                int sum = nums[head] + nums[tail];
                if (sum < target) {
                    head++;
                } else if (sum > target) {
                    tail--;
                } else {
                    List<Integer> solution = new LinkedList<>();
                    solution.add(nums[i]);
                    solution.add(nums[head]);
                    solution.add(nums[tail]);
                    res.add(solution);
                    do {
                        head++;
                    } while (head < tail && nums[head - 1] == nums[head]);
                }
            }
        }
        return res;
    }
}
