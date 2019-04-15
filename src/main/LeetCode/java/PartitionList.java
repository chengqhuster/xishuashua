package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/partition-list/
 *
 * 思路简述：注意头尾节点，保存前置节点信息，对最后一个节点单独处理，用伪头结点减少头部的处理
 *
 */

import LeetCode.java.DataStruct.ListNode;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode fakeNode = new ListNode(0);
        fakeNode.next = head;
        ListNode start = head, end = tail, last = fakeNode;
        while (start != end) {
            if (start.val >= x) {
                tail.next = start;
                tail = start;
                last.next = start.next;
                start.next = null;
                start = last.next;
            } else {
                last = start;
                start = start.next;
            }
        }
        if (start.val >= x && tail != start) {
            tail.next = start;
            last.next = start.next;
            start.next = null;
        }
        return fakeNode.next;
    }
}
