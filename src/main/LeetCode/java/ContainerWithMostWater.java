package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/trapping-rain-water/
 *
 * 思路简述：两个指针从高度数组的两端向中间移动
 *
 */

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
