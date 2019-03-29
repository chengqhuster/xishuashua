package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/find-median-from-data-stream/
 *
 * 思路简述：用最大堆和最小堆来保存数据，堆的大小差值不超过1，最大堆的堆顶要小于最小堆的堆顶
 *
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    class MedianFinder {
        PriorityQueue<Integer> hi, lo;

        /** initialize your data structure here. */
        public MedianFinder() {
            lo = new PriorityQueue<>(Collections.reverseOrder());
            hi = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (lo.size() > hi.size()) {
                hi.add(num);
            } else if (lo.size() < hi.size()) {
                lo.add(num);
            } else {
                hi.add(num);
            }
            if (lo.size() > 0 && lo.peek() > hi.peek()) {
                hi.add(lo.poll());
                lo.add(hi.poll());
            }
        }

        public double findMedian() {
            if (hi.size() > lo.size()) {
                return hi.peek();
            } else if (hi.size() < lo.size()) {
                return lo.peek();
            } else {
                return (hi.peek() + lo.peek())/2d;
            }
        }
    }
}
