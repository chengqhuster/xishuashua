package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/min-stack/
 *
 * 思路简述：用辅助栈保存当前栈元素的最小值，也即数据栈压入的值小于等于辅助栈顶（已经入数据栈的最小值）时同时
 *          也压入辅助栈，注意等值时也需压入
 *
 */

import java.util.Stack;

public class MinStack {
    private Stack<Integer> value;
    private Stack<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        value = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x) {
        value.push(x);
        if (min.isEmpty() || x <= min.peek()) {
            min.push(x);
        }
    }

    public void pop() {
        if (value.pop().equals(min.peek())) {
            min.pop();
        }
    }

    public int top() {
        return value.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
