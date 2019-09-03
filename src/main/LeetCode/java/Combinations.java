package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/combinations/
 *
 * 思路简述：排列组合类问题，带终止条件的 dfs
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0) {
            return res;
        }
        List<Integer> combination = new ArrayList<>();
        dfs(n, 1, k, res, combination);
        return res;
    }

    private void dfs(int n, int index, int left, List<List<Integer>> res, List<Integer> combination) {
        if (index > n) {
            return;
        }
        // not choose
        dfs(n, index + 1, left, res, combination);
        // choose
        combination.add(index);
        left--;
        if (left == 0) {
            res.add(new ArrayList<>(combination));
        } else {
            dfs(n, index + 1, left, res, combination);
        }
        combination.remove(combination.size() - 1);
    }
}
