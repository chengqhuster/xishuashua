package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

/**
 * 题目描述：https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * 思路简述：参考 LinkedListCycle
 */
public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        ListNode node = getCycleNode(head);
        if (node == null) {
            return null;
        }
        int cycleLen = getCycleLen(node);
        ListNode a = head, b = head;
        while (cycleLen > 0) {
            b = b.next;
            cycleLen--;
        }
        while (true) {
            if (a == b) {
                return a;
            }
            a = a.next;
            b = b.next;
        }
    }

    private ListNode getCycleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode a = head, b = head;
        while (a.next != null && b.next != null && b.next.next != null) {
            a = a.next;
            b = b.next.next;
            if (a == b) {
                return a;
            }
        }
        return null;
    }

    private int getCycleLen(ListNode node) {
        ListNode a = node, b = node;
        int count = 0;
        while (true) {
            a = a.next;
            b = b.next.next;
            count++;
            if (a == b) {
                return count;
            }
        }
    }
}
