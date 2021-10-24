package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/count-binary-substrings/
 *
 * 思路简述：统计连续的0和1的数量即可
 *
 */
public class CountBinarySubstrings {

    public int countBinarySubstrings(String s) {
        int prev = 0, curr = 1, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curr++;
            } else {
                res += Math.min(prev, curr);
                prev = curr;
                curr = 1;
            }
        }
        res += Math.min(prev, curr);
        return res;
    }
}
