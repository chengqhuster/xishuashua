package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/unique-letter-string/description/
 *
 * 思路简述：动态规划 dp[i,j]表示从第i位开始，长度为j+1的子串的 UNIQ(S)
 *          dp[i,j] = dp[i][j-1] + (-1 or 0 or 1)
 *          长度为N的字符串末尾添加一个字符，该字符在原字符串没有出现过则UNIQ(S)比原字符串加1，出现过1次时候减1，否则不变
 *          leetcode 超时
 *
 *          复杂度可以降到O(N) dp[i]表示在i处结尾的子串的sum(UNIQ(s))
 *          例如 ABCDEFGCHIJKC 考虑ABCDEFGCHIJKL与增加C字符后的ABCDEFGCHIJKLC关系
 *          以L结尾的子串都能增加C字符成为以C结尾的子串
 *          对于以H-L开头的子串 UNIQ(S) 增加 1
 *          对于以D-C开头的子串 UNIQ(S) 减少 1
 *          对于以A-C开头的子串 UNIQ(S) 不变
 *          因此，考虑新增字符在原字符串从后往前遍历时该字符前两次出现的位置即为关键
 */

//import java.util.HashMap;
//import java.util.HashSet;

import java.util.Arrays;

public class UniqueLetterString {
    public int uniqueLetterString(String S) {
        if(S == null || S.length() == 0)
            return 0;
        int[] pre = new int[126];    // 记录字符C(用数字表示，大写字母最大为126)前一次出现的位置
        int[] prep = new int[126];  // 记录字符C前前一次出现的位置
        Arrays.fill(pre, -1);  // 初始为-1
        Arrays.fill(prep, -1);
        int res = 0;
        int sum = 0;
        for(int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            res += (i - pre[c]) - (pre[c] - prep[c]);
            prep[c] = pre[c];
            pre[c] = i;
            sum += res;
            sum = sum%1000000007;
        }
        return sum;
    }
//    public int uniqueLetterString(String S) {
//        if(S == null || S.length() == 0)
//            return 0;
//        int N = S.length();
//        HashMap<Character, HashSet<Integer>> map = new HashMap<>();
//        for(int i=0; i<N; i++) {
//            char c = S.charAt(i);
//            if(!map.containsKey(c))
//                map.put(c, new HashSet<>());
//            map.get(c).add(i);
//        }
//        int[][] dp = new int[N][N+1];
//        for(int i=0; i<N; i++)
//            dp[i][1] = 1;
//        for(int i=0; i<N; i++) {
//            for(int j=2; j<=N-i; j++) {
//                int gain = getGainNum(S, map, i, j-1);
//                dp[i][j] = dp[i][j-1] + gain;
//            }
//        }
//        int res = 0;
//        for(int i=0; i<N; i++) {
//            for(int j=1; j<=N-i; j++) {
//                System.out.print(dp[i][j] + " ");
//                res += dp[i][j];
//            }
//            System.out.print("\n");
//        }
//        return res;
//    }
//
//    private int getGainNum(String s, HashMap<Character, HashSet<Integer>> map, int start, int length) {
//        char c =   s.charAt(start+length);
//        int count = 0;
//        for(Integer i : map.get(c)) {
//            if(i>=start && i<=start+length-1)
//                count++;
//        }
//        if(count == 0)
//            return 1;
//        else if(count == 1)
//            return -1;
//        else
//            return 0;
//    }
}
