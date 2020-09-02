package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

/**
 * 题目描述：https://leetcode.com/problems/rotate-list/
 *
 * 思路简述：求解头节点的位置
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        int size = getListSize(head);
        if (size == 0) {
            return head;
        }
        int pos = size - k % size;
        if (pos == size) {
            return head;
        }
        return rotateList(head, pos);
    }

    private int getListSize(ListNode head) {
        int res = 0;
        while (head != null) {
            head = head.next;
            res++;
        }
        return res;
    }

    private ListNode rotateList(ListNode head, int pos) {
        ListNode last = head;
        ListNode temp = head.next;
        for (int i = 1; i < pos; i++) {
            last = temp;
            temp = temp.next;
        }
        last.next = null;
        getTailNode(temp).next = head;
        return temp;
    }

    private ListNode getTailNode(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}
