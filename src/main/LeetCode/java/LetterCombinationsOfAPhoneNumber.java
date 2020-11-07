package LeetCode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * 思路简述：编码
 */
public class LetterCombinationsOfAPhoneNumber {

    private static Map<Character, List<Character>> NUMBER_TO_CHAR_LIST = new HashMap<>();

    static {
        NUMBER_TO_CHAR_LIST.put('2', Arrays.asList('a', 'b', 'c'));
        NUMBER_TO_CHAR_LIST.put('3', Arrays.asList('d', 'e', 'f'));
        NUMBER_TO_CHAR_LIST.put('4', Arrays.asList('g', 'h', 'i'));
        NUMBER_TO_CHAR_LIST.put('5', Arrays.asList('j', 'k', 'l'));
        NUMBER_TO_CHAR_LIST.put('6', Arrays.asList('m', 'n', 'o'));
        NUMBER_TO_CHAR_LIST.put('7', Arrays.asList('p', 'q', 'r', 's'));
        NUMBER_TO_CHAR_LIST.put('8', Arrays.asList('t', 'u', 'v'));
        NUMBER_TO_CHAR_LIST.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder(digits);
        if (digits.length() > 0) {
            core(digits, 0, sb, res);
        }
        return res;
    }

    private void core(String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (Character c : NUMBER_TO_CHAR_LIST.get(digits.charAt(index))) {
            sb.setCharAt(index, c);
            core(digits, index + 1, sb, res);
        }
    }
}
