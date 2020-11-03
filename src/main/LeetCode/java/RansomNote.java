package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/ransom-note/
 *
 * 思路简述：直接比较
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] countA = toCountArray(ransomNote);
        int[] countB = toCountArray(magazine);
        for (int i = 0; i < 26; i++) {
            if (countA[i] > countB[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] toCountArray(String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }
        return count;
    }
}
