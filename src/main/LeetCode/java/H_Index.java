package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/h-index/
 *
 * 思路简述：最终的结果为 0-n 之间，因此大于等于 n 的数量可以合在一起统计，用桶排序的思想构建大小
 *          为 n+1 的桶
 *
 */

public class H_Index {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int c : citations) {
            if (c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        int res = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];
            // 理解这里的大于等于，h 划分的两部分都可以包含 h
            if (count >= i) {
                res =  i;
                break;
            }
        }
        return res;
    }
}
