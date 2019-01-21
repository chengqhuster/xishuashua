package LeetCode.java;

import java.util.ArrayList;
import java.util.List;


/*
 * 题目描述：https://leetcode.com/problems/gray-code/
 *
 * 思路简述：动态规划 dp(n) = dp(n-1) + 1 << (n-1)，要保证满足题目连续数只有一个比特位差异的性质
 *           dp(n-1)的集合逆序输出再加上最高比特为1的n位数，放入原集合的后续
 *
 */

public class GrayCode {
    public List<Integer> grayCode(int n) {
        if (n < 0) {
            return null;
        } else if (n == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        } else {
            List<Integer> temp = grayCode(n - 1);
            int addNum = 1 << (n - 1);
            ArrayList<Integer> result = new ArrayList<>(temp);
            for (int i = temp.size() - 1; i >= 0 ; i--) {
                result.add(temp.get(i) + addNum);
            }
            return result;
        }
    }
}
