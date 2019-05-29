package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/strange-printer/
 *
 * 思路简述：dp[i][j] 为 s 的 i 到 j 子串的子问题解，拆分为子问题很简单
 *          重点理解怎么合并，也就是 for 的第三层循环部分
 *
 *          对于一个字符串 s，它的最优方法第一步一定是先涂左右两个端点（正确？）
 *          这给子问题的合并提供了思路
 *
 *          事实上，canCombine 函数字需要检测四种情况的任意一种即可？
 *          a c，b c，b d 的情形在中断点的移动时候都会被子问题包括
 *          a d 才是唯一需要判断的
 *
 */

public class StrangePainter {
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = getMinimalEqualStr(s);
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int ll = 2; ll <= len; ll++) {
            for (int i = 0; i < len - ll + 1; i++) {
                int j = i + ll - 1;
                dp[i][j] = ll;
                for (int k = i; k < j; k++) {
                    int temp = dp[i][k] + dp[k + 1][j];
//                    boolean v = canCombine(s.charAt(i), s.charAt(k), s.charAt(k + 1), s.charAt(j));
                    dp[i][j] = Math.min(dp[i][j], temp);
                }
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j]--;
                }
            }
        }
        return dp[0][len -1];
    }

//    private boolean canCombine(char a, char b, char c, char d) {
//        return a == c || a == d || b == c || b == d;
//    }

    private String getMinimalEqualStr(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i  -1)) {
                continue;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
