package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/permutations/
 *
 * 思路简述：1. 采用回溯的方式遍历
 *         2. 采用交换的方式，递归实现
 *         3. 采用交换的方式，非递归实现（模拟栈上的元素）
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        permuteCore(nums, res, new ArrayList<>());
        return res;
    }

    private void permuteCore(int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
        } else {
            for (int num : nums) {
                if (!temp.contains(num)) {
                    temp.add(num);
                    permuteCore(nums, res, temp);
                    // pay attention here
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    public List<List<Integer>> permuteRecursion(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }
        permuteRecursionCore(nums, res, 0);
        return res;
    }

    private void permuteRecursionCore(int[] nums, List<List<Integer>> res, int pos) {
        if (pos == nums.length) {
            res.add(toList(nums));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            swap(nums, pos, i);
            permuteRecursionCore(nums, res, pos + 1);
            swap(nums, pos, i);
        }
    }

    public List<List<Integer>> permuteNoRecursion(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            if (stack.size() == nums.length) {
                res.add(toList(nums));
                while (!stack.isEmpty() && stack.peek() == nums.length - 1) {
                    stack.pop();
                    swap(nums, stack.size(), nums.length - 1);
                }
                if (!stack.isEmpty()) {
                    int a = stack.pop();
                    swap(nums, a, stack.size());
                    stack.push(a + 1);
                    swap(nums, a + 1, stack.size() - 1);
                }
            } else {
                stack.push(stack.size());
            }
        }
        return res;
    }

    private List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        // 位运算实现交换
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }
}
