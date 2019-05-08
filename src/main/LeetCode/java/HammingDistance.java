package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/hamming-distance/
 *
 * 思路简述：easy job
 *
 */

public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count  = 0;
        while (z != 0) {
            count++;
            z = z & (z - 1);
        }
        return count;
    }
}
