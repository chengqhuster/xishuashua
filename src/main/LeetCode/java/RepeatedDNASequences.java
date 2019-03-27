package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/repeated-dna-sequences/
 *
 * 思路简述：给 A C G T 进行 4 进制编码 00 01 10 11
 *          由此可以计算出长度为10的子串对应的数值，通过该值来判断是否有重复串
 *
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() < 10) {
            return res;
        }
        int[] map = new int[26];
//        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        Set<Integer> value = new HashSet<>();
        Set<Integer> addedValue = new HashSet<>();
        int val = 0;
        for (int i = 0; i < 10; i++) {
            val = (val << 2) | map[s.charAt(i) - 'A'];
        }
        value.add(val);
        for (int i = 10; i < s.length(); i++) {
            val = ((val - (map[s.charAt(i - 10) - 'A'] << 2 * 9)) << 2) + map[s.charAt(i) - 'A'];
            if (!value.add(val) && addedValue.add(val)) {
                res.add(s.substring(i - 9, i + 1));
            }
        }
        return res;
    }
}
