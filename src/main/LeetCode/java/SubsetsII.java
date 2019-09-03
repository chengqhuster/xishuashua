package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/subsets-ii/
 *
 * 思路简述：与 subset 属于同一类型问题，但是存在重复元素，解决思路是将输入数组排序，对于连续相同的数字，如果某个位置选择
 *          不将该元素纳入子集中，那么后续相同的数字也不能放入其中。
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(new LinkedList<>());
            return res;
        }
        Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();
        dfs(nums, 0, true, res, subset);
        return res;
    }

    private void dfs(int[] nums, int index, boolean chosen, List<List<Integer>> res, List<Integer> subset) {
        if (index == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        // 不选中该元素
        dfs(nums, index + 1, false, res, subset);
        // 选中该元素，但有前提条件
        if (index > 0 && !chosen && nums[index] == nums[index - 1]) {
            return;
        }
        subset.add(nums[index]);
        dfs(nums, index + 1, true, res, subset);
        subset.remove(subset.size() - 1);
    }
}
