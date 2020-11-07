package LeetCode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/roman-to-integer/
 *
 * 思路简述：直接编码
 */
public class RomanToInteger {

    private static final Map<String, Integer> ROMAN_NUMBER_MAP = new HashMap<>();

    static {
        ROMAN_NUMBER_MAP.put("I", 1);
        ROMAN_NUMBER_MAP.put("II", 2);
        ROMAN_NUMBER_MAP.put("III", 3);
        ROMAN_NUMBER_MAP.put("IV", 4);
        ROMAN_NUMBER_MAP.put("V", 5);
        ROMAN_NUMBER_MAP.put("VI", 6);
        ROMAN_NUMBER_MAP.put("VII", 7);
        ROMAN_NUMBER_MAP.put("VIII", 8);
        ROMAN_NUMBER_MAP.put("IX", 9);
        ROMAN_NUMBER_MAP.put("X", 10);
        ROMAN_NUMBER_MAP.put("XX", 20);
        ROMAN_NUMBER_MAP.put("XXX", 30);
        ROMAN_NUMBER_MAP.put("XL", 40);
        ROMAN_NUMBER_MAP.put("L", 50);
        ROMAN_NUMBER_MAP.put("LX", 60);
        ROMAN_NUMBER_MAP.put("LXX", 70);
        ROMAN_NUMBER_MAP.put("LXXX", 80);
        ROMAN_NUMBER_MAP.put("XC", 90);
        ROMAN_NUMBER_MAP.put("C", 100);
        ROMAN_NUMBER_MAP.put("CC", 200);
        ROMAN_NUMBER_MAP.put("CCC", 300);
        ROMAN_NUMBER_MAP.put("CD", 400);
        ROMAN_NUMBER_MAP.put("D", 500);
        ROMAN_NUMBER_MAP.put("DC", 600);
        ROMAN_NUMBER_MAP.put("DCC", 700);
        ROMAN_NUMBER_MAP.put("DCCC", 800);
        ROMAN_NUMBER_MAP.put("CM", 900);
        ROMAN_NUMBER_MAP.put("M", 1000);
        ROMAN_NUMBER_MAP.put("MM", 2000);
        ROMAN_NUMBER_MAP.put("MMM", 3000);
    }

    private static int MAX_NUMBER_LENGTH = 4;

    public int romanToInt(String s) {
        int res = 0;
        while (s.length() != 0) {
            for (int i = Math.min(MAX_NUMBER_LENGTH, s.length()); i >= 1; i--) {
                String pre = s.substring(0, i);
                if (ROMAN_NUMBER_MAP.containsKey(pre)) {
                    res += ROMAN_NUMBER_MAP.get(pre);
                    s = s.substring(i);
                    break;
                }
            }
        }
        return res;
    }
}
