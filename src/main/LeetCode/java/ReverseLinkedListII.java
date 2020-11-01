package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

/**
 * 题目描述：https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * 思路简述：straight forward
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if (m == n) {
            return head;
        }
        ListNode fakeNode = new ListNode(0, head);
        ListNode preNode = fakeNode;
        while (m > 1) {
            preNode = preNode.next;
            m--;
            n--;
        }
        ListNode node = preNode;
        ListNode nextNode = node.next;
        while (n > 0) {
            ListNode temp = nextNode.next;
            nextNode.next = node;
            node = nextNode;
            nextNode = temp;
            n--;
        }
        preNode.next.next = nextNode;
        preNode.next = node;
        return fakeNode.next;
    }
}
