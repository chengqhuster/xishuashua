package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/restore-ip-addresses/
 *
 * 思路简述：贪心，dfs
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        int[] tailIndex = new int[3];
        List<String> res = new ArrayList<>();
        core(s, 0, tailIndex, res);
        return res;
    }

    private void core(String s, int count, int[] tailIndex, List<String> res) {
        if (count == 3) {
            int leftLen = s.length() - tailIndex[2] - 1;
            if (leftLen <= 0 || leftLen > 3 || (s.charAt(tailIndex[2] + 1) == '0' && leftLen > 1)) {
                return;
            }
            int num = Integer.parseInt(s.substring(tailIndex[2] + 1));
            if (num <= 255) {
                res.add(s.substring(0, tailIndex[0] + 1) + "."
                        + s.substring(tailIndex[0] + 1, tailIndex[1] + 1) + "."
                        + s.substring(tailIndex[1] + 1, tailIndex[2] + 1) + "."
                        + s.substring(tailIndex[2] + 1));
            }
        } else {
            int start = count == 0 ? 0 : tailIndex[count - 1] + 1;
            if (start >= s.length()) {
                return;
            }
            tailIndex[count] = start;
            core(s, count + 1, tailIndex, res);
            if (s.charAt(start) != '0') {
                if (start + 1 < s.length()) {
                    tailIndex[count] = start + 1;
                    core(s, count + 1, tailIndex, res);
                }
                if (start + 2 < s.length()) {
                    int num = Integer.parseInt(s.substring(start, start + 3));
                    if (num <= 255) {
                        tailIndex[count] = start + 2;
                        core(s, count + 1, tailIndex, res);
                    }
                }
            }
        }
    }
}
