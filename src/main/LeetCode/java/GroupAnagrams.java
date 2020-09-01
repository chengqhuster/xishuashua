package LeetCode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/group-anagrams/
 *
 * 思路简述：计算出每个string的字母序对应的字符串
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String dict = getDictSortStr(str);
            if (!map.containsKey(dict)) {
                map.put(dict, new ArrayList<>());
            }
            map.get(dict).add(str);
        }
        return new ArrayList<>(map.values());
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
