package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/average-waiting-time/
 *
 * 思路简述：straight forward
 */
public class AverageWaitingTime {

    public double averageWaitingTime(int[][] customers) {
        long chefIdleTime = 0;
        long customerTimeSpent = 0;
        for (int[] customer : customers) {
            if (customer[0] >= chefIdleTime) {
                chefIdleTime = customer[0] + customer[1];
                customerTimeSpent += customer[1];
            } else {
                chefIdleTime += customer[1];
                customerTimeSpent += chefIdleTime - customer[0];
            }
        }
        return customerTimeSpent / (double) customers.length;
    }
}
