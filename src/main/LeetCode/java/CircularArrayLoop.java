package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/circular-array-loop/
 *
 * 思路简述：记录每个位置顺时针和逆时针的状态
 */
public class CircularArrayLoop {
    private final int STATE_PASS = 1;
    private final int STATE_STOP = -1;

    public boolean circularArrayLoop(int[] nums) {
        int size = nums.length;
        int[] Clockwise = new int[size];
        int[] Counterclockwise = new int[size];
        List<Integer> store = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            store.clear();
            int j = i;
            if (nums[i] > 0) {
                while (nums[j] > 0 && Clockwise[j] != STATE_STOP) {
                    if (Clockwise[j] == STATE_PASS) {
                        return true;
                    }
                    Clockwise[j] = STATE_PASS;
                    store.add(j);
                    // 判断是否有长度为1的圈
                    if (j == getNextIndex(nums, j)) {
                        break;
                    }
                    j = getNextIndex(nums, j);
                }
                store.forEach(index -> Clockwise[index] = STATE_STOP);
            } else {
                while (nums[j] < 0 && Counterclockwise[j] != STATE_STOP) {
                    if (Counterclockwise[j] == STATE_PASS) {
                        return true;
                    }
                    Counterclockwise[j] = STATE_PASS;
                    store.add(j);
                    // 判断是否有长度为1的圈
                    if (j == getNextIndex(nums, j)) {
                        break;
                    }
                    j = getNextIndex(nums, j);
                }
                store.forEach(index -> Counterclockwise[index] = STATE_STOP);
            }
        }
        return false;
    }

    private int getNextIndex(int[] nums, int i) {
        int temp = (i + nums[i]) % nums.length;
        if (temp < 0) {
            return temp + nums.length;
        }
        return temp;
    }
}
