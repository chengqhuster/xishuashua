package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/permutation-in-string/
 *
 * 思路简述：参考 find all anagram in a string
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] target = getLetterCount(s1, 0, s1.length());
        int[] temp = getLetterCount(s2, 0, s1.length());
        int dis = 0;
        for (int i = 0; i < target.length; i++) {
            dis += Math.abs(target[i] - temp[i]);
        }
        if (dis == 0) {
            return true;
        }
        for (int i = 1; i <= s2.length() - s1.length(); i++) {
            int headCharNum = s2.charAt(i - 1) - 'a';
            int tailCharNum = s2.charAt(i + s1.length() - 1) - 'a';
            if (headCharNum != tailCharNum) {
                temp[headCharNum]--;
                temp[tailCharNum]++;
                dis += Math.abs(target[headCharNum] - temp[headCharNum]) -
                        Math.abs(target[headCharNum] - temp[headCharNum] - 1) +
                        Math.abs(target[tailCharNum] - temp[tailCharNum]) -
                        Math.abs(target[tailCharNum] - temp[tailCharNum] + 1);
            }
            if (dis == 0) {
                return true;
            }
        }
        return false;
    }

    private int[] getLetterCount(String s, int start, int end) {
        int[] res = new int[26];
        for (int i = start; i < end; i++) {
            res[s.charAt(i) - 'a']++;
        }
        return res;
    }
}
