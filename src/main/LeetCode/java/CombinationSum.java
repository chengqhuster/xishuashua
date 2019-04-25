package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/combination-sum/
 *
 * 思路简述：回溯法
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        if (candidates[0] <= 0) {
            return res;
        }
        backtrackingCore(new ArrayList<>(), res, candidates, 0, 0, target);
        return res;
    }

    private void backtrackingCore(List<Integer> store, List<List<Integer>> res, int[] nums, int start, int sum, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(store));
        }
        for (int i = start; i < nums.length; i++) {
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
