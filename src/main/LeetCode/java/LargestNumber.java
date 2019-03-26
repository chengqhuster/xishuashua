package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/largest-number/
 *
 * 思路简述：先将数字全部转化为字符串，然后按照给定排序规则进行排序，注意 12120 小于 12
 *          具体排序方式查看 compareTo 方法
 *          更加优雅的写法可以通过java的stream函数式编程来实现
 *
 */

import java.util.Arrays;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        NumberString[] ns = new NumberString[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ns[i] = new NumberString(nums[i]);
        }
        Arrays.sort(ns);
        StringBuffer sb = new StringBuffer();
        for (NumberString n : ns) {
            sb.append(n.s);
        }
        if (sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }

    public class NumberString implements Comparable<NumberString> {
        String s;

        public NumberString(int i) {
            s = Integer.toString(i);
        }

        @Override
        public int compareTo(NumberString o) {
            int a = s.length();
            int b = o.s.length();
            int i = 0, j = 0;
            while (true) {
                if (s.charAt(i) > o.s.charAt(j)) {
                    return -1;
                } else if (s.charAt(i) < o.s.charAt(j)){
                    return 1;
                }
                i++;
                j++;
                if (i == a && j == b) {
                    return 0;
                } else if (i == a) {
                    i = 0;
                } else if (j == b) {
                    j = 0;
                }
            }
        }
    }
}
