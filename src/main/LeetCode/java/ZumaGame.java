package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/zuma-game/
 *
 * 思路简述：DFS，注意字符的消除与拼接，board加上一个结束标志会使得代码整体简洁很多
 *
 */

public class ZumaGame {
    String endFlag = "#";
    int unreachedRes = 10;

    public int findMinStep(String board, String hand) {
        if (board == null || board.length() == 0) {
            return 0;
        }
        if (hand == null || hand.length() >= 6) {
            return -1;
        }
        int[] count = new int[26];
        for (int i = 0; i < hand.length(); i++) {
            count[hand.charAt(i) - 'A']++;
        }
        // 减少了对空字符的判断
        board = board + endFlag;
        int res = dfs(board, count);
        return res == unreachedRes ? -1 : res;
    }

    private int dfs(String board, int[] count) {
        board = removeConsecutive(board);
        int need = 0;
        int res = unreachedRes;
        if (endFlag.equals(board)) {
            return 0;
        }
        for (int i = 0, j = 0; j < board.length(); j++) {
            if (board.charAt(j) == board.charAt(i)) {
                continue;
            }
            need = 3 - (j - i);
            if (count[board.charAt(i) - 'A'] >= need) {
                count[board.charAt(i) - 'A'] -= need;
                res = Math.min(res, dfs(board.substring(0, i) + board.substring(j), count) + need);
                count[board.charAt(i) - 'A'] += need;
            }
            i = j;
        }
        return res;
    }

    private String removeConsecutive(String input) {
        for (int i = 0, j = 0; j < input.length(); j++) {
            if (input.charAt(j) == input.charAt(i)) {
                continue;
            }
            if (j - i >= 3) {
                return removeConsecutive(input.substring(0, i) + input.substring(j));
            }
            i = j;
        }
        return input;
    }
}
