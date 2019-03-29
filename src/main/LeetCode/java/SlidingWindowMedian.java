package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/sliding-window-median/
 *
 * 思路简述：参考FindMedianFromDataStream，用最大堆和最小堆保存中位数两侧的数据
 *
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return new double[]{};
        }
        PriorityQueue<Integer> hi = new PriorityQueue<>();
        PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
        double[] res = new double[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            hi.add(nums[i]);
            if (lo.size() > 0 && hi.peek() < lo.peek()) {
                lo.add(hi.poll());
                hi.add(lo.poll());
            }
            if (hi.size() > lo.size() + 1) {
                lo.add(hi.poll());
            }
            if (i >= k - 1) {
                if (k%2 == 1) {
                    res[index++] = hi.peek();
                } else {
                    // in case sum of two peek is out of Integer range
                    res[index++] = hi.peek()/2d + lo.peek()/2d;
                }
                int rv = nums[i - k + 1];
                if (rv < hi.peek()) {
                    lo.remove(rv);
                } else {
                    hi.remove(rv);
                }
            }
        }
        return res;
    }
}
