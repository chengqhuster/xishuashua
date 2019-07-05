package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/split-array-largest-sum/
 *
 * 思路简述：a.参考 Largest Sum of average
 *           b.二分法
 *
 */

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0 || nums.length < m) {
            return 0;
        }
        int len = nums.length;
        int[] sum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[len][m + 1];
        return helper(sum, dp, 0, m);
    }

    private int helper(int[] sum, int[][] dp, int start, int m) {
        if (dp[start][m] > 0) {
            return dp[start][m];
        }
        int len = sum.length - 1;
        if (m == 1) {
            dp[start][1] = sum[len] - sum[start];
            return dp[start][1];
        }
        int min = Integer.MAX_VALUE;
        for (int next = start; next < len; next++) {
            if (len - next < m) {
                break;
            }
            int temp = Math.max(sum[next + 1] - sum[start], helper(sum, dp, next + 1, m - 1));
            min = Math.min(min, temp);
        }
        dp[start][m] = min;
        return dp[start][m];
    }

    // 二分法的上下界 l r 分别代表结果可能的最小值与最大值“减一”，可能有个疑问是每次取值都取 mid + 1 或者 mid - 1 作为上下界，
    // 如果结果刚好取 mid 怎么办，这种情况最终 l = r = 'mid' 再经过一次判断 l = 'mid' + 1 便取回了刚才的 mid 值
    // 或者判断条件改为

//    while (l < r) {
//        long mid = (l + r) / 2;
//        if (isValid(nums, mid, m)) {
//            r = mid;
//        } else {
//            l = mid + 1;
//        }
//    }

    public int splitArrayII(int[] nums, int m) {
        if (nums == null || nums.length == 0 || nums.length < m) {
            return 0;
        }
        long l = 0, r = 0;
        for (int num : nums) {
            l = Math.max(l, num);
            r += num;
        }
        while (l <= r) {
            long mid = (l + r) / 2;
            if (isValid(nums, mid, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)l;
    }

    private boolean isValid(int[] nums, long group, int m) {
        int count = 0, total = 0;
        for (int num : nums) {
            total += num;
            if (total > group) {
                count++;
                total = num;
                if (count >= m) {
                    return false;
                }
            }
        }
        return true;
    }
}
