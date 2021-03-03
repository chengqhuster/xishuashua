package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 *
 * 思路简述：位运算
 */
public class NumberOfStepsToReduceANumberToZero {

    public int numberOfSteps (int num) {
        int time = 0;
        while (num != 0) {
            if ((num & 1) == 0) {
                num = num >> 1;
            } else {
                num = num ^ 1;
            }
            time++;
        }
        return time;
    }
}
