package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/counting-bits/
 *
 * 思路简述：n的1的个数与n>>1的1个数相关，n最后一位是否为1与n的奇偶性相关
 *
 * 相关笔记：理解 n&(n-1) 将 n 的二进制表示中的最低位为1的改为0 也即 n 的二进制数比 n&(n-1) 多一个1
 *           理解 n&-n 取 n 的最右边出现的1的值
 *
 */

public class CountingBits {
    public int[] countBits(int num) {
        if (num < 0) {
            return null;
        }
        int[] res = new int[num  + 1];
        for (int i = 1 ; i <= num; i++) {
            res[i] = res[i >> 1] + i%2;
        }
        return res;
    }

    public int[] countBitsSec(int num) {
        if (num < 0) {
            return null;
        }
        int[] res = new int[num  + 1];
        for (int i = 1 ; i <= num; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }
}
