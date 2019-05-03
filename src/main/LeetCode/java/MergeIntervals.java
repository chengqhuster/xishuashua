package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/merge-intervals/
 *
 * 思路简述：easy job
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> store = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int [] merge = new int[2];
        merge[0] = intervals[0][0];
        merge[1] = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= merge[1]) {
                merge[1] = Math.max(merge[1], intervals[i][1]);
            } else {
                store.add(Arrays.copyOf(merge, merge.length));
                merge[0] = intervals[i][0];
                merge[1] = intervals[i][1];
            }
        }
        store.add(Arrays.copyOf(merge, merge.length));
        int[][] res = new int[store.size()][];
        for (int i = 0; i < store.size(); i++) {
            res[i] = store.get(i);
        }
        return res;
    }
}
