package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/knight-dialer/
 *
 * 思路简述：dp 可降至一维
 *
 */

import java.util.Arrays;

public class KnightDialer {
    private int[][] dic = new int[10][];
    private int mod = 1000000007;

    {
        dic[0] = new int[]{4, 6};
        dic[1] = new int[]{6, 8};
        dic[2] = new int[]{7, 9};
        dic[3] = new int[]{4, 8};
        dic[4] = new int[]{0, 3, 9};
        dic[5] = new int[]{};
        dic[6] = new int[]{0, 1, 7};
        dic[7] = new int[]{2, 6};
        dic[8] = new int[]{1, 3};
        dic[9] = new int[]{2, 4};
    }

    public int knightDialer(int N) {
        if (N == 1) {
            return 10;
        }
        int[][] dp = new int[N][10];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                int temp = i;
                dp[i][j] = Arrays.stream(dic[j]).map(o -> dp[temp - 1][o]).reduce(0, (acc , element) -> (acc + element) % mod);
            }
        }
        return Arrays.stream(dp[N - 1]).reduce(0, (acc , element) -> (acc + element) % mod);
    }
}
