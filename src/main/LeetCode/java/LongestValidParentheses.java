package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-valid-parentheses/description/
 *
 * 思路简述：动态规划
 *          由内向外聚合的方式，state数组保存该位置是否为有效括号对串的一位的信息
 *          1.从()开始聚合，()xx, xx(), (xx) 有三种方式
 *          2.将状态数组连续的1组成的块作为内核，继续聚合，直到没有新的聚合结束
 *
 *          步骤1可能形成 (#######$$$$$$$$)的形式，#与$为两个()内核聚集而成
 *          步骤2将#$组成块继续向外聚合
 *
 */

import java.util.Stack;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int N = s.length();
        char[] cc = s.toCharArray();
        int[] state = new int[N];
        for(int i=0; i<N-1; i++) {
            if(cc[i] == '(' && cc[i+1] == ')' && state[i] == 0 && state[i+1] == 0) {
                buildValidParentheses(cc, state, i, i+1);
            }
        }
        combineValidParentheses(cc, state);
        int res = 0;
        int count = 0;
        for( int i : state) {
            if(i == 0) {
                res = Math.max(res, count);
                count = 0;
            }
            else
                count++;
        }
        return Math.max(res, count);
    }

    private boolean buildValidParentheses(char[] cc, int[] state, int left, int right) {
        boolean res = false;
        state[left] = 1;
        state[right] = 1;
        int N = cc.length;
        boolean hasNext = true;
        while(hasNext) {
            hasNext = false;
            if(right + 2 < N && cc[right + 1] == '(' && cc[right + 2] == ')') {
                res = true;
                hasNext = true;
                state[right + 1] = 1;
                state[right + 2] = 1;
                right = right + 2;
            }
            if(left - 2 >= 0 && cc[left - 2] == '(' && cc[left - 1] == ')') {
                res = true;
                hasNext = true;
                state[left - 2] = 1;
                state[left - 1] = 1;
                left = left - 2;
            }
            if(right + 1 < N && left - 1 >=0 && cc[left - 1] == '(' && cc[right + 1] == ')') {
                res = true;
                hasNext = true;
                state[left - 1] = 1;
                state[right + 1] = 1;
                left = left - 1;
                right = right + 1;
            }
        }
        return res;
    }

    private void combineValidParentheses(char[] cc, int[] state) {
        boolean hasNext = true;
        while(hasNext) {
            int left = -1;
            int right = -1;
            hasNext = false;
            for(int i=0; i<cc.length; i++) {
                if(state[i] == 0) {
                    if(left != -1) {
                        right = i - 1;
                        if(buildValidParentheses(cc, state, left, right))
                            hasNext = true;
                        left = -1;
                    }
                } else {
                    if(left == -1) {
                        left = i;
                    }
                }
            }
        }
    }

    // 用栈计算符号匹配情况，匹配的符号弹出栈，最终剩下就是将字符串分割为一个个匹配子串的符号位置
    public int longestValidParenthesesSec(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                if (!st.isEmpty() && s.charAt(st.peek()) == '(') {
                    st.pop();
                } else {
                    st.push(i);
                }
            }
        }
        int res = 0;
        int a = len, b = 0;
        while (!st.isEmpty()) {
            b = st.pop();
            res = Math.max(res, a - b - 1);
            a = b;
        }
        res = Math.max(res, a);
        return res;
    }
}
