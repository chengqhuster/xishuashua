package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/different-ways-to-add-parentheses/
 *
 * 思路简述：1. 每次组合其中相邻的两个运算数，dfs遍历的方式（错误，存在重复 如：1+2+3+4 先算 1+2 再算 3+4 与
 *             先算3+4再算1+2是同一种情形）
 *          2. 运算符将 input 分成两个部分（思路 1 是每次 n 个运算符将 input 分为 n+1 部分，导致了重复），map 记忆一些中间解
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DifferentWaysToAddParentheses {
    private Map<Character, Integer> map = new HashMap<>();
    {
        map.put('+', 1);
        map.put('-', 2);
        map.put('*', 3);
    }

    Map<String, List<Integer>> storeMap = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if (storeMap.containsKey(input)) {
            return storeMap.get(input);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> a = diffWaysToCompute(input.substring(0, i));
                List<Integer> b = diffWaysToCompute(input.substring(i + 1));
                for (int p1 : a) {
                    for (int p2 : b) {
                        res.add(getOpRes(p1, p2, map.get(c)));
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        storeMap.put(input, res);
        return res;
    }

    public List<Integer> diffWaysToComputeWrong(String input) {
        List<Integer> res = new ArrayList<>();
        if (input == null) {
            return res;
        }
        List<Integer> nums = new LinkedList<>();
        List<Integer> op = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                op.add(map.get(c));
                nums.add(Integer.parseInt(input.substring(index, i)));
                index = i + 1;
            }
        }
        nums.add(Integer.parseInt(input.substring(index)));
        boolean[] state = new boolean[op.size()];
        dfs(nums, op, state, res);
        return res;
    }

    private void dfs(List<Integer> nums, List<Integer> op, boolean[] state, List<Integer> res) {
        int index = lastStateCount(state);
        if (index >= 0) {
            res.add(getOpRes(nums.get(0), nums.get(1), op.get(index)));
            return;
        }
        index = 0;
        for (int i = 0; i < state.length; i++) {
            if (!state[i]) {
                int a = nums.get(index);
                int b = nums.get(index + 1);
                nums.set(index, getOpRes(a, b, op.get(i)));
                index++;
                nums.remove(index);
                state[i] = true;
                dfs(nums, op, state, res);
                state[i] = false;
                nums.set(index - 1, a);
                nums.add(index, b);
            }
        }
    }

    private int lastStateCount(boolean[] state) {
        int index = -1;
        for (int i = 0; i < state.length; i++) {
            if (!state[i]) {
                if (index == -1) {
                    index = i;
                } else {
                    return -1;
                }
            }
        }
        return index;
    }

    private int getOpRes(int a, int b, int c) {
        switch (c) {
            case 1:
                return a + b;
            case 2:
                return a - b;
            case 3:
                return a * b;
            default :
                    return 0;
        }
    }
}

