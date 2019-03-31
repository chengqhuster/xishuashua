package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/valid-parentheses/
 *
 * 思路简述：用栈解决符号匹配问题
 *
 */

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c= s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else if (c == ')') {
                if (st.isEmpty() || st.pop() != '(') {
                    return false;
                }
            } else if (c == ']') {
                if (st.isEmpty() || st.pop() != '[') {
                    return false;
                }
            } else if (c == '}') {
                if (st.isEmpty() || st.pop() != '{') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return st.isEmpty();
    }
}
