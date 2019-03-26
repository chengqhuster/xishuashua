package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/missing-number/
 *
 * 思路简述：1.通过位图来记录是否存在相应数据
 *          2.数据归位（5放到索引为5的地方）
 *
 */

public class MissingNumber {
    byte[] bit;

    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        bit = new byte[n/8  +1];
        for (int i = 0; i < n; i++) {
            setB(nums[i]);
        }
        return getMissNum();
    }

    private void setB(int n) {
        bit[n/8] = (byte)(bit[n/8] | (1 << n%8));
    }

    private int getMissNum() {
        for (int i = 0; i < bit.length; i++) {
            if ((bit[i] & 255) == 255) {
                continue;
            }
            int j = 0;
            for (; j < 8; j++) {
                if ((bit[i] & (1 << j)) == 0) {
                    return i * 8 + j;
                }
            }

        }
        return -1;
    }

    // another method
    public int missingNumberUsingSwap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == i || nums[i] == n) {
                continue;
            }
            while (nums[i] != i && nums[i] != n) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == n) {
                return i;
            }
        }
        return n;
    }
}
