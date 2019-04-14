package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/add-two-numbers-ii/
 *
 * 思路简述：翻转链表 然后参考 AddTwoNumbers，或者使用栈
 *
 */

import LeetCode.java.DataStruct.ListNode;

import java.util.Stack;

public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> t1 = new Stack<>();
        Stack<ListNode> t2 = new Stack<>();
        while (l1 != null) {
            t1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            t2.push(l2);
            l2 = l2.next;
        }
        int sum = 0;
        ListNode res = new ListNode(0);
        while (!t1.isEmpty() || !t2.isEmpty()) {
            if (!t1.isEmpty()) {
                sum += t1.pop().val;
            }
            if (!t2.isEmpty()) {
                sum += t2.pop().val;
            }
            ListNode node = new ListNode(sum % 10);
            sum = sum / 10;
            node.next = res.next;
            res.next = node;
        }
        if (sum != 0) {
            ListNode node = new ListNode(1);
            node.next = res.next;
            res.next = node;
        }
        return res.next;
    }
}
