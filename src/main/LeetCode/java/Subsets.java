package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/subsets/
 *
 * 思路简述：n位二进制 0 代表不取数组对应位置数，1 代表取该元素
 *
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null) {
            return res;
        } else if (nums.length == 0) {
            res.add(new LinkedList<>());
            return res;
        } else {
            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            int key = 1;
            // 0 ... 1 ... 0(1 is in the last-first i place) ----> nums[i]
            for (int i = 0; i < n; i++) {
                map.put(key << i, nums[i]);
            }
            res.add(new LinkedList<>());
            for (int i = 1; i < 1 << n; i++) {
                int temp = i;
                List<Integer> subset = new LinkedList<>();
                while (temp != 0) {
                    // get the last 1 value like 1101010100 ----> 0000000100
                    int index = temp & (-temp);
                    subset.add(map.get(index));
                    temp -= index;
                }
                res.add(subset);
            }
            return res;
        }
    }
}
