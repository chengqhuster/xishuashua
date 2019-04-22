package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * 思路简述：第 K 大问题
 *
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInAStream {
    private Queue<Integer> queue = new PriorityQueue<>();

    public KthLargestElementInAStream(int k, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                queue.add(nums[i]);
            } else {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.add(nums[i]);
                }
            }
        }
        // fill the min heap to size k
        while (queue.size() < k) {
            queue.add(Integer.MIN_VALUE);
        }
    }

    public int add(int val) {
        if (val > queue.peek()) {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }
}
