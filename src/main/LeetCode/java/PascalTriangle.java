package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/pascals-triangle/
 *
 * 思路简述：easy job
 *
 */

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0) {
            return res;
        }
        List<Integer> head = new ArrayList<>();
        head.add(1);
        res.add(head);
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 0; j < i - 1; j++) {
                row.add(res.get(i - 1).get(j) + res.get(i - 1).get(j + 1));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
}
