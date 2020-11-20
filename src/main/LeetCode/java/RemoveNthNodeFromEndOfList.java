package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

/**
 * 题目描述：https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * 思路简述：经典题型, 增加伪头节点处理起来更加的简便
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fakeNode = new ListNode(0);
        fakeNode.next = head;

        ListNode mark = head;
        while (n > 1) {
            mark = mark.next;
            n--;
        }
        ListNode pre = fakeNode;
        ListNode remove = head;
        while (mark.next != null) {
            pre = pre.next;
            remove = remove.next;
            mark = mark.next;
        }
        pre.next = remove.next;
        return fakeNode.next;
    }
}
