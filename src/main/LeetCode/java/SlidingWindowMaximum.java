package LeetCode.java;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目描述：https://leetcode.com/problems/sliding-window-maximum/
 *
 * 思路简述：维持一个数组元素降序的队列，队列的元素是数组的索引
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 注释部分也是可行的，因为deque的元素(nums的索引)也一定是非严格降序排列的
//            if(i - deque.peekFirst() == k){
//                deque.pollFirst();
//            }
            while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
