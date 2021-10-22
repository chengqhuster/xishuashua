package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/range-addition/
 *
 * 思路简述：1. 树状数组，使用于随机的单点查询（包括更新过程中）
 *         2. 只需要在起始位置记录即可（右change的位置需要右移一位），使用于最终输出整个数组
 *
 */
public class RangeAddition {

    public int[] getModifiedArray(int length, int[][] updates) {

        int[] change = new int[length];
        for (int[] update : updates) {
            change[update[0]] += update[2];
            if (update[1] + 1 < length) {
                change[update[1] + 1] -= update[2];
            }
        }
        int[] res = new int[length];
        res[0] = change[0];
        for (int i = 1; i < length; i++) {
            res[i] = res[i - 1] + change[i];
        }
        return res;
    }
}
