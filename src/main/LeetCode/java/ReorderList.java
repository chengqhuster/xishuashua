package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/reorder-list/
 *
 * 将链表分为两个部分，前半部分通过stack保存，再将后半部分节点插入。
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        // 先获取长度
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        if (len <= 2) {
            return;
        }
        // 栈结构
        LinkedList<ListNode> stack = new LinkedList<>();
        int count = 1;
        node = head;
        while (true) {
            stack.push(node);
            count++;
            if (count > len >> 1) {
                break;
            }
            node = node.next;
        }
        ListNode left;
        if ((len & 1) == 1) {
            // 奇数个
            left = node.next.next;
            node.next.next = null;
        } else {
            // 偶数个
            left = node.next;
            node.next = null;
        }
        // 插入
        while (left != null) {
            ListNode prev = stack.pop();
            ListNode next = left.next;
            left.next = prev.next;
            prev.next = left;
            left = next;
        }
    }
}
