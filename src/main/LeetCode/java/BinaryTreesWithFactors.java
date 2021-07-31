package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/binary-trees-with-factors/
 *
 * 思路简述：先排序，在遍历每个元素能作为另外两个元素乘积的数量，最后统计组合的树的数量
 *
 * 动态规划：dp[] 表示每个元素作为根结点的树的数量，有
 *         dp[k] = sum{dp[i] * dp[j] * factor} where (arr[k] == arr[i] * arr[j], factor = i == j ? 1 : 2)
 *         为了方便找到符合要求的 i j 对，采用排序后首尾指针探测的方式
 */
public class BinaryTreesWithFactors {

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        long[] count = new long[arr.length];
        Arrays.fill(count, 1);
        long res = 1;
        for (int i = 1; i < arr.length; i++) {
            int head = 0, tail = i - 1;
            while (head <= tail) {
                long product = (long) arr[head] * arr[tail];
                if (product > arr[i]) {
                    tail--;
                } else if (product < arr[i]) {
                    head++;
                } else {
                    if (head == tail) {
                        count[i] += count[head] * count[tail];
                    } else {
                        count[i] += 2 * count[head] * count[tail];
                    }
                    head++;
                    tail--;
                }
            }
            res += count[i];
        }
        return (int) (res % 1000000007);
    }
}
