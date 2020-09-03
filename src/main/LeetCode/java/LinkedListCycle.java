package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

/**
 * 题目描述：https://leetcode.com/problems/linked-list-cycle/
 *
 * 思路简述：链表圈问题
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode a = head, b = head;
        while (a.next != null && b.next != null && b.next.next != null) {
            a = a.next;
            b = b.next.next;
            if (a == b) {
                return true;
            }
        }
        return false;
    }
}
