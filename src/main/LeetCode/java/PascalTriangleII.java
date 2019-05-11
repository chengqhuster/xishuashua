package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/pascals-triangle-ii/
 *
 * 思路简述：easy job
 *
 */

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> listA = new ArrayList<>();
        if (rowIndex < 0) {
            return listA;
        }
        listA.add(1);
        List<Integer> listB = new ArrayList<>();
        boolean flag = true;
        while (rowIndex > 0) {
            if (flag) {
                getFromLastRow(listB, listA);
            } else {
                getFromLastRow(listA, listB);
            }
            flag = !flag;
            rowIndex--;
        }
        if (flag) {
            return listA;
        } else {
            return listB;
        }
    }

    private void getFromLastRow(List<Integer> a, List<Integer> b) {
        a.clear();
        a.add(1);
        for (int i = 0; i < b.size() - 1; i++) {
            a.add(b.get(i) + b.get(i + 1));
        }
        a.add(1);
    }
}
