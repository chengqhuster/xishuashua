package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/decode-string/
 *
 * 思路简述：类似符号匹配的问题，遇到匹配的字符时进行解码
 *
 */

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ']') {
                st.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                while (!st.isEmpty()) {
                    c = st.pop();
                    if (c != '[') {
                        sb.append(c);
                    } else {
                        break;
                    }
                }
                int times = 0;
                int digitsNum = 1;
                while (!st.isEmpty()) {
                    c = st.peek();
                    if (c >= '0' && c <= '9') {
                        st.pop();
                        times += (c - '0') * digitsNum;
                        digitsNum *= 10;
                    } else {
                        break;
                    }
                }
                for (int k = 0; k < times; k++) {
                    for (int j = sb.length() - 1; j >= 0; j--) {
                        st.push(sb.charAt(j));
                    }
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!st.isEmpty()) {
            res.append(st.pop());
        }
        return res.reverse().toString();
    }
}
