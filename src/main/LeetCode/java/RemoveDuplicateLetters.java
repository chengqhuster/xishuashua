package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/remove-duplicate-letters/
 *
 * 思路简述：第一次统计各个字母出现的次数，再从左向右遍历，把栈中还没有出现的
 *          字母压入栈中，压入之前，若当前字母比栈顶字母小，且栈顶字母在后续
 *          还有出现（通过字母统计数量判断），弹出栈顶元素。最终的栈为输出的
 *          最小字典序无重复字母字符串。
 *
 */

import java.util.Stack;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (st.isEmpty() || !st.contains(c)) {
                while (!st.isEmpty() && st.peek() > c) {
                    if (count[st.peek() - 'a'] > 0) {
                        st.pop();
                    } else {
                        break;
                    }
                }
                st.push(c);
            }
            count[c - 'a']--;
        }
        StringBuffer sb = new StringBuffer();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("edebbed"));
    }
}
