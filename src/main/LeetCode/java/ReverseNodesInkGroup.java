package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 *
 * 思路简述：链表的倒转，处理好每段内的倒转以及段之间的节点连接关系(需要记忆一些节点)
 *
 */

import LeetCode.java.DataStruct.ListNode;

public class ReverseNodesInkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k <= 1) {
            return head;
        }
        int N = 0;
        ListNode node = head;
        while(node != null) {
            N++;
            node = node.next;
        }
        int count = 0;
        // 上一段的尾节点
        ListNode KLastTail = null;
        // 本段的尾节点
        ListNode KTail = null;
        // 输出的头结点(有倒转时，为倒转后第一段的头结点，也即倒转前第一个节点)
        ListNode resHead = head;
        // 保存三个节点，当前节点以及前后节点
        ListNode last = null;
        ListNode next = head.next;
        node = head;
        while (node != null) {
            if(count%k == 0) {
                if(N - count < k) {
                    break;
                }
                KLastTail = last;
                KTail = node;
                ListNode temp = next;
                next = next.next;
                temp.next = node;
                node = temp;
                last = temp.next;
            } else if((count+1)%k == 0) {
                if(KLastTail == null) {
                    resHead = node;
                } else {
                    KLastTail.next = node;
                }
                KTail.next = next;
                if(next != null) {
                    next = next.next;
                    node = KTail.next;
                    last = KTail;
                }
            } else {
                ListNode temp = next;
                next = next.next;
                temp.next = node;
                node = temp;
                last = temp.next;
            }
            count++;
        }
        return resHead;
    }
}
