package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/palindrome-pairs/
 *
 * 思路简述：分析，两个字符串组成回文串的情况 A与B
 *          A就是B的回文串 则 AB BA 均为回文串
 *          假设 A = C + D
 *          C 为回文串 且B与D回文 则 BCD 即 BA 为回文串
 *          D 为回文串 且B与C回文 则 CDB 即 AB 为回文串
 *          为了方便找到字符串与其索引位置的对应关系，可以将字符串放到字典中
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i=0; i<words.length; i++) {
            map.put(words[i], i);
        }
        for (int i=0; i<words.length; i++) {
            String re = new StringBuffer(words[i]).reverse().toString();
            // 第一种情形
            if(map.containsKey(re) && map.get(re) != i) {
                res.add(Arrays.asList(i, map.get(re)));
            }
            // 第二种情形
            int len = words[i].length();
            for(int j=0; j<len; j++) {
                // 2.1
                if (words[i].charAt(j) == words[i].charAt(0)) {
                    if (isPalidorme(words[i], 0, j)) {
                        String pre = new StringBuffer(words[i].substring(j + 1)).reverse().toString();
                        if(map.containsKey(pre) && map.get(pre) != i) {
                            res.add(Arrays.asList(map.get(pre), i));
                        }
                    }
                }
                // 2.2
                if (words[i].charAt(j) == words[i].charAt(len - 1)) {
                    if (isPalidorme(words[i], j, len - 1)) {
                        String pre = new StringBuffer(words[i].substring(0, j)).reverse().toString();
                        if(map.containsKey(pre) && map.get(pre) != i) {
                            res.add(Arrays.asList(i, map.get(pre)));
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalidorme(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
