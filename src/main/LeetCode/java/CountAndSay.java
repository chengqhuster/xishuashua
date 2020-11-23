package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/count-and-say/
 *
 * 思路简述：递归
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i <= j) {
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (i == j) {
                break;
            }
            int count = j - i;
            sb.append(count).append(s.charAt(i));
            i = j;
        }
        return sb.toString();
    }
}
