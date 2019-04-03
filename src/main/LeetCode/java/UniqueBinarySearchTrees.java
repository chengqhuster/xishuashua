package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/unique-binary-search-trees/
 *
 * 思路简述：从序列中任意取出一个枢纽，剩余的左右两部分即为左右子树
 *          DP = Recursive + Memory
 *
 */

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTrees {
    Map<Integer, Integer> map = new HashMap<>();
    {
        map.put(1, 1);
    }

    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (!map.containsKey(i - 1)) {
                map.put((i - 1), numTrees(i -1));
            }
            if (!map.containsKey(n - i)) {
                map.put((n - i), numTrees(n -i));
            }
            res += map.get(i - 1) + map.get(n - i);
        }
        return res;
    }
}
