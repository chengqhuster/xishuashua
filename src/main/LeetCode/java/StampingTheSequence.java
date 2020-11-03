package LeetCode.java;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目描述：https://leetcode.com/problems/stamping-the-sequence/
 *
 * 思路简述：贪心算法，每次寻找最大匹配
 */
public class StampingTheSequence {

    public int[] movesToStamp(String stamp, String target) {
        Deque<Integer> deque = new LinkedList<>();
        if (dfs(stamp, target, deque)) {
            int[] res = new int[deque.size()];
            int index = 0;
            while (!deque.isEmpty()) {
                res[index] = deque.pollLast();
                index++;
            }
            return res;
        } else {
            return new int[0];
        }

    }

    private boolean dfs(String stamp, String target, Deque<Integer> deque) {
        if (checkTarget(target)) {
            return true;
        }
        int index = findMaxPossibleIndex(stamp, target);
        if (index == -1) {
            return false;
        }
        deque.offerLast(index);
        return dfs(stamp, removeStampFromTarget(stamp, target, index), deque);
    }

    private boolean checkTarget(String target) {
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }

    // TODO index的匹配还有优化空间
    private int findMaxPossibleIndex(String stamp, String target) {
        int res = -1, maxCount = 0;
        for (int i = 0; i <= target.length() - stamp.length(); i++) {
            int count = 0;
            for (int j = 0; j < stamp.length(); j++) {
                if (stamp.charAt(j) == target.charAt(i + j)) {
                    count++;
                } else if (target.charAt(i + j) != '?') {
                    count = 0;
                    break;
                }
            }
            if (count > maxCount) {
                res = i;
                maxCount = count;
            }
        }
        return res;
    }

    private String removeStampFromTarget(String stamp, String target, int index) {
        StringBuilder sb = new StringBuilder(target);
        for (int i = index; i < index + stamp.length(); i++) {
            sb.setCharAt(i, '?');
        }
        return sb.toString();
    }
}
