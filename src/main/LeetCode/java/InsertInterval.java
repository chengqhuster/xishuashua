package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/insert-interval/
 *
 * 思路简述：通过interval将整个区间分为 2*len + 1 个小区间，计算出 newInterval 的起始点落在哪个区间
 *         如 [[1,3],[6,9]] 分解为 （min,1) [1,3] (3,6) [6,9] (9,max)
 *         注意区间的闭合
 *         最后通过区间的位置判断是否需要增删区间以及修改区间起始值
 *
 */

import java.util.List;

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int leftIndex = -1, rightIndex = -1;
        int left, right;
        int len = intervals.size();
        // separate the whole interval into len*2+1 parts
        for (int i = 0; i < len * 2 + 1; i++) {
            if (i == 0) {
                left = Integer.MIN_VALUE;
            } else if (i%2 == 0){
                left = intervals.get(i/2 -1).end;
            } else {
                left = intervals.get(i/2).start;
            }
            if (i == len * 2) {
                right = Integer.MAX_VALUE;
            } else if (i%2 == 0) {
                right = intervals.get(i/2).start;
            } else {
                right = intervals.get(i/2).end;
            }
            if (i%2  == 0) {
                // opened interval
                if (left < newInterval.start && right > newInterval.start) {
                    leftIndex = i;
                }
                if (left < newInterval.end && right > newInterval.end) {
                    rightIndex = i;
                }
            } else {
                // closed interval
                if (left <= newInterval.start && right >= newInterval.start) {
                    leftIndex = i;
                }
                if (left <= newInterval.end && right >= newInterval.end) {
                    rightIndex = i;
                }
            }
            if (leftIndex != -1 && rightIndex != -1) {
                break;
            }
        }
        if (leftIndex == rightIndex) {
            // add a new interval
            if (leftIndex%2 == 0) {
                intervals.add(leftIndex/2, newInterval);
            }
        } else {
            int targetIndex = leftIndex/2;
            // fix start value
            if (leftIndex%2 == 0) {
                intervals.get(targetIndex).start = newInterval.start;
            }
            // fix end value
            if (rightIndex%2 == 0) {
                intervals.get(targetIndex).end = newInterval.end;
            } else {
                intervals.get(targetIndex).end = intervals.get(rightIndex/2).end;
            }
            // remove combined interval
            int end = rightIndex%2 == 0 ? rightIndex/2 : rightIndex/2 + 1;
            int count = 0;
            for (int i = leftIndex/2 + 1; i < end; i++) {
                intervals.remove(i - count);
                count++;
            }
        }
        return intervals;
    }


     public class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }
}
