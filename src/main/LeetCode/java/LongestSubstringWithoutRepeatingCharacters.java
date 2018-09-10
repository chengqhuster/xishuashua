package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * 思路简述：两个指针指向子字符串的前后位置，遍历输入字符串时，移动末指针，遇到重复的字符时，移动首指针至不重复
 *          的位置为止，统计最长的子字符串
 *
 */

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0)
            return 0;
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int res = 0;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(set.contains(c)) {
                while(s.charAt(left) != c) {
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;
            } else {
                set.add(c);
                int length = i - left + 1;
                res = res > length ? res : length;
            }
        }
        return res;
    }
}
