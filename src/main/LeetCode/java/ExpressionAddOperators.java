package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/expression-add-operators/
 *
 * 思路简述：DFS
 *
 */

import java.util.LinkedList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new LinkedList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        dfs(num, res, "", target, 0, 0, 0);
        return res;
    }

    private void dfs(String num, List<String> res, String sol, int target, int pos, long cur, long last) {
        if (pos == num.length()) {
            if (cur == target) {
                res.add(sol);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            // pos 为 0 时候，只能取一位（007不是合法的数字）
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long n = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                // 起点处没有与上一个数的符号连接
                dfs(num, res, sol + n, target, i + 1, n, n);
            } else {
                // last 变量保存上一次计算 cur 的变化量，这次出现乘法时，优先级最高，解除上次计算结果并计算 last 与当前值
                // 的乘积作为此次的变化量
                dfs(num, res, sol + "+" + n, target, i + 1, cur + n, n);
                dfs(num, res, sol + "-" + n, target, i + 1, cur - n, -n);
                dfs(num, res, sol + "*" + n, target, i + 1, cur - last + last * n, last * n);
            }
        }
    }
}
