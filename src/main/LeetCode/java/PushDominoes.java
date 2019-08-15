package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/push-dominoes/
 *
 * 思路简述：以 R 为分割，将字符串拆成字串，同时记录关键信息（每段字串 L 的最左和最右位置）
 *
 */

public class PushDominoes {
    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() == 0) {
            return dominoes;
        }
        int len = dominoes.length();
        StringBuilder sb = new StringBuilder(dominoes);
        // 以R为分割线
        int head = 0, minL = -1, maxL = -1, tail = 0;
        while (tail < len) {
            char c = dominoes.charAt(tail);
            if (c == 'L') {
                maxL = tail;
                if (minL == -1) {
                    minL = tail;
                }
            } else if (c == 'R') {
                helper(sb, head, tail, minL, maxL);
                head = tail;
                minL = -1;
                maxL = -1;
            }
            tail++;
        }
        helper(sb, head, tail, minL, maxL);
        return sb.toString();
    }

    private void helper(StringBuilder s, int head, int tail, int minL, int maxL) {
        if (tail - head <= 1) {
            return;
        }
        if (s.charAt(head) == 'R') {
            if (minL == -1) {
                for (int i = head; i < tail; i++) {
                    s.setCharAt(i, 'R');
                }
            } else {
                for (int i = head; i <= minL; i++) {
                    if (i * 2 < head + minL) {
                        s.setCharAt(i, 'R');
                    }
                    if (i * 2 > head + minL) {
                        s.setCharAt(i, 'L');
                    }
                }
                for (int i = minL; i <= maxL; i++) {
                    s.setCharAt(i, 'L');
                }
            }
        } else {
            for (int i = head; i < maxL; i++) {
                s.setCharAt(i, 'L');
            }
        }
    }
}
