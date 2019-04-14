package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/generate-parentheses/
 *
 * 思路简述：dfs，store数组保存字符信息，m n为剩余可用的左右括号数，m要保证不大于n，m等于n时只能添加左括号
 *          m小于n时可添加剩余的左右括号
 *
 */

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        char[] store = new char[n * 2];
        generateParenthesisCore(res, store, n, n);
        return res;
    }

    private void generateParenthesisCore(List<String> res, char[] store, int m, int n) {
        if (m == 0 && n == 0) {
            res.add(new String(store));
            return;
        }
        int index = store.length - m - n;
        if (m == n) {
            store[index] = '(';
            generateParenthesisCore(res, store, m - 1, n);
        } else {
            if (m > 0) {
                store[index] = '(';
                generateParenthesisCore(res, store, m - 1, n);
            }
            store[index] = ')';
            generateParenthesisCore(res, store, m, n - 1);
        }
    }
}
