package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/decode-ways/
 *
 * 思路简述：1.深度优先遍历，递归调用，超时
 *          2.dp[n] 代表s的n长子串的解，探索dp[n]与dp[n-1]，dp[n-2]的关系
 *
 */

public class DecodeWays {

//    超时的方法
//    public int numDecodingsTLE(String s) {
//        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
//            return 0;
//        }
//        int n = s.length();
//        if (n == 1) {
//            return 1;
//        }
//        else if (n == 2) {
//            int num = Integer.parseInt(s);
//            if (num == 10 || num == 20) {
//                return 1;
//            } else if (num > 26) {
//                if (s.charAt(1) == '0') {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            } else {
//                return 2;
//            }
//        } else {
//            int num = Integer.parseInt(s.substring(0, 2));
//            if (num > 26) {
//                return numDecodingsTLE(s.substring(1));
//            } else {
//                return numDecodingsTLE(s.substring(1)) + numDecodingsTLE(s.substring(2));
//            }
//        }
//    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n];
        if (isValidCode(s.substring(0, 1))) {
            dp[0] = 1;
        } else {
            return 0;
        }
        if (n > 1) {
            int res = 0;
            if (isValidCode(s.substring(1, 2))) {
                res++;
            }
            if (isValidCode(s.substring(0, 2))) {
                res++;
            }
            if (res == 0) {
                return 0;
            } else {
                dp[1] = res;
            }
            for (int i = 2; i < n; i++) {
                boolean resA = isValidCode(s.substring(i, i +1));
                boolean resB = isValidCode(s.substring(i - 1, i +1));
                if (resA && resB) {
                    dp[i] = dp[i - 2] + dp[i - 1];
                } else if (resA) {
                    dp[i] = dp[i - 1];
                } else if (resB) {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            }
        }
        return dp[n - 1];
    }

    private boolean isValidCode(String s) {
        int n = s.length();
        if (n == 1) {
            return s.charAt(0) != '0';
        } else if (n == 2) {
            int num = Integer.parseInt(s);
            if (s.charAt(0) == '0' || num > 26) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
