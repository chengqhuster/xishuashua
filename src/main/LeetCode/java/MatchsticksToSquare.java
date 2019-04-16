package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/matchsticks-to-square/
 *
 * 思路简述：dfs 注意 nums 需要先降序排列处理，减少 dfs 的分支数量（处理时间）
 *
 */

import java.util.Arrays;

public class MatchsticksToSquare {
    boolean res = false;

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        sum = sum / 4;
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            nums[left] = nums[left] ^ nums[right];
            nums[right] = nums[left] ^ nums[right];
            nums[left] = nums[left] ^ nums[right];
            left++;
            right--;
        }
        if (sum < nums[0]) {
            return false;
        }
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited, sum, 0, 4);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, int target, int curSum, int left) {
        if (res || left == 1) {
            res = true;
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                int temp = curSum + nums[i];
                if (temp > target) {
                    break;
                }
                visited[i] = true;
                if (temp == target) {
                    dfs(nums, visited, target, 0, left - 1);
                } else {
                    dfs(nums, visited, target, curSum + nums[i], left);
                }
                visited[i] = false;
            }
        }
    }
}
