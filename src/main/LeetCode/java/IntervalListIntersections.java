package LeetCode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/interval-list-intersections/
 *
 * 思路简述：先提取，再合并
 */
public class IntervalListIntersections {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[][]{};
        }
        List<int[]> intervals = new ArrayList<>();
        int aIndex = 0, bIndex = 0;
        while (aIndex < A.length && bIndex < B.length) {
            intersection(A[aIndex], B[bIndex], intervals);
            if (A[aIndex][1] > B[bIndex][1]) {
                bIndex++;
            } else {
                aIndex++;
            }
        }
        // 合并
        List<int[]> res = new ArrayList<>();
        int first = 0, second = 1;
        while (second < intervals.size()) {
            if (intervals.get(second - 1)[1] != intervals.get(second)[0]) {
                res.add(new int[]{intervals.get(first)[0], intervals.get(second - 1)[1]});
                first = second;
            }
            second++;
        }
        res.add(new int[]{intervals.get(first)[0], intervals.get(second - 1)[1]});
        return res.toArray(new int[][]{});
    }

    //提取重合部分
    private void intersection(int[] a, int[] b, List<int[]> intervals) {
        int[] total = new int[]{a[0], a[1], b[0], b[1]};
        Arrays.sort(total);
        boolean aFlag = total[0] == a[0] && total[1] == a[1];
        boolean bFlag = total[0] == b[0] && total[1] == b[1];
        if (!(aFlag || bFlag) || total[1] == total[2]) {
            intervals.add(new int[]{total[1], total[2]});
        }
    }
}
