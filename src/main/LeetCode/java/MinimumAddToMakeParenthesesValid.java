package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 *
 * 思路简述：参考 LongestValidParentheses 解法二 stack最终剩下的即为无法匹配的字符，需要增加相应数量的字符完成匹配
 *
 */

import java.util.Stack;

public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                st.push(c);
            } else {
                if (!st.isEmpty() && st.peek() == '(') {
                    st.pop();
                } else {
                    st.push(c);
                }
            }
        }
        return st.size();
    }
}
