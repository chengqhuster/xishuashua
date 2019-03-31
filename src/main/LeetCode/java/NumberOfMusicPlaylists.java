package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-music-playlists/
 *
 * 思路简述：DP[i][j] 代表 j 种不同歌曲，歌单长度为 i 时的解的个数 ( N 为总的歌曲数量)
 *          a) 没有K的限制
 *            1. 长度 i 的歌单最后一首是新增的歌曲 有 DP[i][j] = DP[i - 1][j - 1] * (N - j + 1)
 *            2. 长度 i 的歌单最后一首是重复的歌曲 有 DP[i][j] = DP[i - 1][j] * j
 *          b) 加上K的限制， K 的作用是歌单中任何连续的 K 首歌曲都是不重复的
 *            1. 长度 i 的歌单最后一首是新增的歌曲 与 a.1 相同 有 DP[i][j] = DP[i - 1][j - 1] * (N - j + 1)
 *            2. 长度 i 的歌单最后一首是重复的歌曲 有 DP[i][j] = DP[i - 1][j] * (j - K)   when j > K
 */

public class NumberOfMusicPlaylists {
    public int numMusicPlaylists(int N, int L, int K) {
        if ( N <= 0 || L <= 0 || K < 0) {
            return 0;
        }
        int mod = 1000000007;
        long[][] dp = new long[L + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i - 1][j - 1] * (N - j + 1)) % mod;
                if (j > K) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - K)) % mod;
                }
            }
        }
        return (int)dp[L][N];
    }
}
