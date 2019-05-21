package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-matching-subsequences/
 *
 * 思路简述：继承 IsSubsequence 思路 TLE
 *
 */

public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String S, String[] words) {
        //todo
        return 0;
    }

    public int numMatchingSubseqTLE(String S, String[] words) {
        int[] index = new int[words.length];
        int[] target = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            target[i] = words[i].length();
        }
        int res = 0;
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < words.length; j++) {
                if (index[j] < target[j]) {
                    if (S.charAt(i) == words[j].charAt(index[j])) {
                        index[j]++;
                        if (index[j] == target[j]) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
