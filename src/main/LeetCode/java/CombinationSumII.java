package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/combination-sum/
 *
 * 思路简述：参考 CombinationSum
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        backtrackingCore(new ArrayList<>(), res, candidates, -1, 0, target);
        return res;
    }

    private void backtrackingCore(List<Integer> store, List<List<Integer>> res, int[] nums, int start, int sum, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(store));
        }
        for (int i = start + 1; i < nums.length; i++) {
            if (i > start + 1 && nums[i] == nums[i  - 1]) {
                continue;
            }
            if (sum + nums[i] <= target) {
                store.add(nums[i]);
                backtrackingCore(store, res, nums, i, sum + nums[i], target);
                store.remove(store.size() - 1);
            } else {
                break;
            }
        }
    }
}
