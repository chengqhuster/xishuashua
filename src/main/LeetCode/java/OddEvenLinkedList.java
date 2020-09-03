package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

/**
 * 题目描述：https://leetcode.com/problems/odd-even-linked-list/
 *
 * 思路简述：先拆再合
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head, even = head.next, evenHead = head.next;
        boolean oddIndex = true;
        ListNode node = even.next;
        while (node != null) {
            if (oddIndex) {
                oddIndex = false;
                odd.next = node;
                odd = node;
            } else {
                oddIndex = true;
                even.next = node;
                even = node;
            }
            node = node.next;
        }
        odd.next = evenHead;
        even.next = null;
        return head;
    }
}
