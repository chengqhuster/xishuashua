package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/score-of-parentheses/
 *
 * 思路简述：使用栈完成数据的转换以及合并，栈中只有左括号以及数字信息，碰到S中的右括号就进行一次转换
 *          匹配的字符串的计算结果是栈中只有一个元素，最终的结果值
 *
 */

import java.util.Stack;

public class ScoreOfParentheses {
    String left = "(";
    String right = ")";

    public int scoreOfParentheses(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        Stack<String> st = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                st.push(left);
            } else {
                int num;
                if (left.equals(st.peek())) {
                    st.pop();
                    num = 1;
                } else {
                    num = Integer.parseInt(st.pop()) * 2;
                    st.pop();
                }
                while (!st.isEmpty() && !left.equals(st.peek())) {
                    num += Integer.parseInt(st.pop());
                }
                st.push("" + num);
            }
        }
        return Integer.parseInt(st.pop());
    }
}
