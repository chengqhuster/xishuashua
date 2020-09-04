package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/find-the-duplicate-number/
 *
 * 思路简述：按索引位置放置元素（o(1) space, o(n) time, source input changed）
 */
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int index = -1;
        while (index < nums.length) {
            index++;
            if (nums[index] == index + 1) {
                continue;
            }
            while (nums[index] != index + 1) {
                int pos = nums[index] - 1;
                if (nums[pos] == nums[index]) {
                    return pos;
                } else {
                    int temp = nums[pos];
                    nums[pos] = nums[index];
                    nums[index] = temp;
                }
            }
        }
        return -1;
    }
}
