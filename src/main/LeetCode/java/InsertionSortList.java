package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/insertion-sort-list/
 *
 * 思路简述：插入排序
 *
 */

import LeetCode.java.DataStruct.ListNode;

public class InsertionSortList {
    ListNode head;

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        this.head = head;
        ListNode last = head;
        ListNode next = head.next;
        while (next != null) {
            insert(last, next);
            if (next == last.next) {
                last = next;
                next = next.next;
            } else {
                next = last.next;
            }
        }
        return this.head;
    }

    private void insert(ListNode last, ListNode next) {
        ListNode node = head;
        ListNode nodeLast = null;
        while (node != next) {
            if (next.val <= node.val) {
                last.next = last.next.next;
                next.next = node;
                if (node == head) {
                    head = next;
                } else {
                    nodeLast.next = next;
                }
                break;
            }
            nodeLast = node;
            node = node.next;
        }
    }
}
