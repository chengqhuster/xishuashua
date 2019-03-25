package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/gas-station/
 *
 * 思路简述：参考https://leetcode.com/problems/gas-station/discuss/246879/Simple-%22graphic%22-proof-and-easy-Python-Code
 *          通过折线图来理解
 *
 */

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int min = Integer.MAX_VALUE;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (total < min) {
                min = total;
                start = i;
            }
        }
        if (total < 0) {
            return -1;
        } else {
            // 理解为什么加1，index在折线图中代表的是一段段的变化趋势，而不能理解为一个点的状态，min最小的点对应的index趋势
            // 仍旧是下降的，应该把下一个点（开始上升）作为起始点
            return (start + 1)%gas.length;
        }
    }
}
