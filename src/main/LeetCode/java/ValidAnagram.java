package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/valid-anagram/
 *
 * 思路简述：参考group anagrams
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        return getDictSortStr(s).equals(getDictSortStr(t));
    }

    private String getDictSortStr(String str) {
        int[] nums = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            nums[i] = str.charAt(i);
        }
        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }
}
