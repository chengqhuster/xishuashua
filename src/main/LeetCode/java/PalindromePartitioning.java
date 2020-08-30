package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/palindrome-partitioning/
 *
 * 思路简述：DP[i][j] 代表从 i（包含） 开始到 j（包含） 的子字符串是否为回文串
 *          先用 dp 计算出子字符串是否为回文串，减少直接对 s 进行回溯（DFS）时的回文判断
 *          *** 理解回溯与深度优先遍历的关系 ***
 *
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new LinkedList<>();
        }
        int size = s.length();
        boolean[][] dp = new boolean[size][size];
        for (int len = 0; len < size; len++) {
            for (int start = 0; start < size - len; start++) {
                int end = start + len;
                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = start + 1 >= end - 1 || dp[start + 1][end - 1];
                }
            }
        }
        //检查是否有 (0, k1) (k1+1, k2) ... (kn, len-1) 使得该序列在 dp 中都为 true，采用回溯的方式
        List<List<String>> res = new LinkedList<>();
        Stack<Integer> st = new Stack<>();
        backtrackingCore(0, dp, s, st, res);
        return res;
    }

    private void backtrackingCore(int start, boolean[][] dp, String s, Stack<Integer> st, List<List<String>> res) {
        for (int i = start; i < dp.length; i++) {
            if (dp[start][i]) {
                st.push(i);
                if (i == dp.length - 1) {
                    List<String> subSeq = new LinkedList<>();
                    Iterator<Integer> it = st.iterator();
                    int index = 0;
                    while (it.hasNext()) {
                        int end = it.next();
                        subSeq.add(s.substring(index, end + 1));
                        index = end + 1;
                    }
                    res.add(subSeq);
                } else {
                    backtrackingCore(i + 1, dp, s, st, res);
                }
                st.pop();
            }
        }
    }
}
