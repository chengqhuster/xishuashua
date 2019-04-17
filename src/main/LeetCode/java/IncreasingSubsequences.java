package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/increasing-subsequences/
 *
 * 思路简述：DFS
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }
        List<Integer> curList = new ArrayList<>();
        dfs(nums, -1, res, curList);
        return res;
    }

    private void dfs(int[] nums, int prePos, List<List<Integer>> res, List<Integer> curList) {
        Set<Integer> used = new HashSet<>();
        for (int i = prePos + 1; i < nums.length; i++) {
            // 重复的数
            if (used.contains(nums[i])) {
                continue;
            }
            if (prePos == -1 || nums[i] >= nums[prePos]) {
                used.add(nums[i]);
                curList.add(nums[i]);
                int size = curList.size();
                if (size > 1) {
                    res.add(new ArrayList<>(curList));
                }
                dfs(nums, i, res, curList);
                curList.remove(size - 1);
            }
        }
    }
}
