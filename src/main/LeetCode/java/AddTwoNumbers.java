package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/add-two-numbers/
 *
 * 思路简述：加法过程 注意链表的长短
 *
 */

import LeetCode.java.DataStruct.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            ListNode head1 = l1, head2 = l2, last1 = null;
            int sum , carry = 0;
            while (head1 != null) {
                if (head2 != null) {
                    sum = head1.val + head2.val + carry;
                } else {
                    sum = head1.val + carry;
                }
                head1.val = sum % 10;
                carry = sum / 10;
                last1 = head1;
                head1 = head1.next;
                if (head1 == null && head2 != null) {
                    head1 = head2.next;
                    last1.next = head1;
                    head2.next = null;
                }
                if (head2 != null) {
                    head2 = head2.next;
                }
            }
            if (carry == 1) {
                ListNode node = new ListNode(1);
                last1.next = node;
            }
            return l1;
        }
    }
}
