package LeetCode.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目描述：https://leetcode.com/problems/valid-sudoku/
 *
 * 思路简述：只需要check，不需要求解
 */
public class ValidSudoku {
    private final int len = 9;

    public boolean isValidSudoku(char[][] board) {
        int count = 0;
        int[][] nums = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] == '.') {
                    count++;
                } else {
                    nums[i][j] = board[i][j] - '0';
                }
            }
        }
        // check & recode number miss position
        int[] missPos = new int[count];
        int index = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (nums[i][j] == 0) {
                    missPos[index] = i * len + j;
                    index++;
                } else if (!checkValid(i, j, nums)) {
                    return false;
                }
            }
        }
//        return dfs(missPos, 0, nums);
        return true;
    }

    private boolean dfs(int[] missPos, int index, int[][] nums) {
        if (index >= missPos.length) {
            return true;
        }
        int i = missPos[index] / len;
        int j = missPos[index] % len;
        Set<Integer> possibleNums = getPossibleNums(i, j, nums);
        for (int num : possibleNums) {
            nums[i][j] = num;
            if (dfs(missPos, index + 1, nums)) {
                return true;
            }
        }
        nums[i][j] = 0;
        return false;
    }

    private boolean checkValid(int i, int j, int[][] nums) {
        int target = nums[i][j];
        // check horizontal and vertical direction
        for (int k = 0; k < len; k++) {
            if (k != j && target == nums[i][k]) {
                return false;
            }
            if (k != i && target == nums[k][j]) {
                return false;
            }
        }
        // check zone
        int xZone = i / 3;
        int yZone = j / 3;
        for (int p = xZone * 3; p < (xZone + 1) * 3; p++) {
            for (int q = yZone * 3; q < (yZone + 1) * 3; q++) {
                if (p != i && q != j && target == nums[p][q]) {
                    return false;
                }
            }
        }
        return true;
    }

    private Set<Integer> getPossibleNums(int i, int j, int[][] nums) {
        Set<Integer> init = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        // check horizontal and vertical direction
        for (int k = 0; k < len; k++) {
            if (k != j && nums[i][k] != 0) {
                init.remove(nums[i][k]);
            }
            if (k != i && nums[k][j] != 0) {
                init.remove(nums[k][j]);
            }
        }
        // check zone
        int xZone = i / 3;
        int yZone = j / 3;
        for (int p = xZone * 3; p < (xZone + 1) * 3; p++) {
            for (int q = yZone * 3; q < (yZone + 1) * 3; q++) {
                if (p != i && q != j && nums[p][q] != 0) {
                    init.remove(nums[p][q]);
                }
            }
        }
        return init;
    }
}
