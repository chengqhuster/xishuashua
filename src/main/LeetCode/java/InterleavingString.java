package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/interleaving-string/description/
 *
 * 思路简述：维持三个指针p1，p2，p3，判断当前s3(p3)子字符串是否为s1(p1)子字符串和s2(p2)子字符串的
 *          interleaving满足的话，继续向下寻找。
 *          方式一(注释部分)即为该思想的递归方式，但是在分支很多的时候耗时较长
 *          方式二：将解的状态空间用数组保存起来，通过迭代的方式节省了cpu时间(p3=p1+p2，p1与p2即构成了解空间)
 *
 */

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1 == null || s2 == null || s3 == null || s1.length() + s2.length() != s3.length())
            return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
//        return judge(c1, c2, c3, 0, 0, 0);
        boolean[][] solve = new boolean[c1.length+1][c2.length+1];
        //初始状态
        solve[0][0] = true;
        for(int i=1; i<=c1.length; i++) {
            solve[i][0] = solve[i-1][0] && c1[i-1] == c3[i-1];
        }
        for(int i=1; i<=c2.length; i++) {
            solve[0][i] = solve[0][i-1] && c2[i-1] == c3[i-1];
        }
        // 迭代
        for(int i=1; i<=c1.length; i++) {
            for(int j=1; j<=c2.length; j++) {
                solve[i][j] = (solve[i-1][j] && c1[i-1] == c3[i+j-1]) ||
                        (solve[i][j-1] && c2[j-1] == c3[i+j-1]);
            }
        }
        return solve[c1.length][c2.length];
    }

//    private boolean judge(char[] c1, char[] c2, char[] c3, int p1, int p2, int p3) {
//        if(p3 == c3.length) {
//            return true;
//        }
//        if(p1 < c1.length && c3[p3] == c1[p1]) {
//            if(judge(c1, c2, c3, p1 + 1, p2, p3 + 1)) {
//                return true;
//            }
//        }
//        if(p2 < c2.length && c3[p3] == c2[p2]) {
//            if(judge(c1, c2, c3, p1, p2 + 1, p3 + 1)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
