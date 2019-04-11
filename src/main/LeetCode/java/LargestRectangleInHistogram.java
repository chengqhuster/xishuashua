package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * 思路简述：用栈保存前置信息（一定是非增序的顺序），栈的元素为int数组，第一个元素代表高度，第三个元素为初始位置，
 *          第二个元素为移位后的位置（默认等于初始位置）。遍历时，如果当前位置高度不大于栈顶高度，则由栈顶元素与
 *          当前位置构成一个长方形；如果当前位置高度小于栈顶，则栈弹出一直到空栈或者栈顶元素高度大于等于当前位置
 *          的高度，注意此时当前位置入栈时的移位位置为0（空栈）或栈顶元素初始位置加 1（非移位位置，这就是为什么要
 *          保存初始位置的原因）。高度数组遍历结束后，还要将栈清空，计算面积时一律使用移位位置。
 *
 */

import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int res = heights[0];
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{heights[0], 0, 0});
        for (int i = 1; i < heights.length; i++) {
            int[] pk = st.peek();
            if (pk[0] <= heights[i]) {
                res = Math.max(res, pk[0] * (i - pk[1] + 1));
                st.push(new int[]{heights[i], i, i});
            } else {
                while (pk[0] > heights[i]) {
                    res = Math.max(res, pk[0] * (i - pk[1]));
                    st.pop();
                    if (st.isEmpty()) {
                        break;
                    }
                    pk = st.peek();
                }
                if (st.isEmpty()) {
                    st.push(new int[]{heights[i], 0, i});
                } else {
                    st.push(new int[]{heights[i], pk[2] + 1, i});
                }
            }
        }
        while (!st.isEmpty()) {
            int[] left = st.pop();
            res = Math.max(res, left[0] * (heights.length - left[1]));
        }
        return res;
    }
}
