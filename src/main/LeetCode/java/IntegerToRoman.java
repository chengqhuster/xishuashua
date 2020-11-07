package LeetCode.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/integer-to-roman/
 *
 * 思路简述：直接编码
 */
public class IntegerToRoman {

    private static final Map<Integer, String> ROMAN_NUMBER_MAP = new HashMap<>();

    static {
        ROMAN_NUMBER_MAP.put(1, "I");
        ROMAN_NUMBER_MAP.put(2, "II");
        ROMAN_NUMBER_MAP.put(3, "III");
        ROMAN_NUMBER_MAP.put(4, "IV");
        ROMAN_NUMBER_MAP.put(5, "V");
        ROMAN_NUMBER_MAP.put(6, "VI");
        ROMAN_NUMBER_MAP.put(7, "VII");
        ROMAN_NUMBER_MAP.put(8, "VIII");
        ROMAN_NUMBER_MAP.put(9, "IX");
        ROMAN_NUMBER_MAP.put(10, "X");
        ROMAN_NUMBER_MAP.put(20, "XX");
        ROMAN_NUMBER_MAP.put(30, "XXX");
        ROMAN_NUMBER_MAP.put(40, "XL");
        ROMAN_NUMBER_MAP.put(50, "L");
        ROMAN_NUMBER_MAP.put(60, "LX");
        ROMAN_NUMBER_MAP.put(70, "LXX");
        ROMAN_NUMBER_MAP.put(80, "LXXX");
        ROMAN_NUMBER_MAP.put(90, "XC");
        ROMAN_NUMBER_MAP.put(100, "C");
        ROMAN_NUMBER_MAP.put(200, "CC");
        ROMAN_NUMBER_MAP.put(300, "CCC");
        ROMAN_NUMBER_MAP.put(400, "CD");
        ROMAN_NUMBER_MAP.put(500, "D");
        ROMAN_NUMBER_MAP.put(600, "DC");
        ROMAN_NUMBER_MAP.put(700, "DCC");
        ROMAN_NUMBER_MAP.put(800, "DCCC");
        ROMAN_NUMBER_MAP.put(900, "CM");
        ROMAN_NUMBER_MAP.put(1000, "M");
        ROMAN_NUMBER_MAP.put(2000, "MM");
        ROMAN_NUMBER_MAP.put(3000, "MMM");
    }

    public String intToRoman(int num) {
        List<Integer> numbers = new ArrayList<>();
        int d = 10;
        while (num != 0) {
            int mod = num % d;
            if (mod != 0) {
                numbers.add(mod);
            }
            num -= mod;
            d *= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = numbers.size() - 1; i >= 0; i--) {
            sb.append(ROMAN_NUMBER_MAP.get(numbers.get(i)));
        }
        return sb.toString();
    }
}
