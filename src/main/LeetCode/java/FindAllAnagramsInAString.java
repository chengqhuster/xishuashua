package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *
 * 思路简述：滑动窗口的思想
 */
public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] target = getLetterCount(p, 0, p.length());
        int[] temp = getLetterCount(s, 0, p.length());
        int dis = 0;
        for (int i = 0; i < target.length; i++) {
            dis += Math.abs(target[i] - temp[i]);
        }
        if (dis == 0) {
            res.add(0);
        }
        for (int i = 1; i <= s.length() - p.length(); i++) {
            int headCharNum = s.charAt(i - 1) - 'a';
            int tailCharNum = s.charAt(i + p.length() - 1) - 'a';
            if (headCharNum != tailCharNum) {
                temp[headCharNum]--;
                temp[tailCharNum]++;
                dis += Math.abs(target[headCharNum] - temp[headCharNum]) -
                        Math.abs(target[headCharNum] - temp[headCharNum] - 1) +
                        Math.abs(target[tailCharNum] - temp[tailCharNum]) -
                        Math.abs(target[tailCharNum] - temp[tailCharNum] + 1);
            }
            if (dis == 0) {
                res.add(i);
            }
        }
        return res;
    }

    private int[] getLetterCount(String s, int start, int end) {
        int[] res = new int[26];
        for (int i = start; i < end; i++) {
            res[s.charAt(i) - 'a']++;
        }
        return res;
    }
}
