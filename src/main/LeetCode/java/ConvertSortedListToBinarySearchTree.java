package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * 思路简述：参考 ConvertSortedArraytoBinarySearchTree 也可先转化为数组，通过下标定位目的节点
 *
 */

import LeetCode.java.DataStruct.ListNode;
import LeetCode.java.DataStruct.TreeNode;

public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return sortedListToBSTCore(head, getListSize(head));
    }

    private TreeNode sortedListToBSTCore(ListNode node, int size) {
        if (size == 0) {
            return null;
        }
        int middle = size/2;
        ListNode midNode = getNextNNode(node, middle);
        TreeNode newNode = new TreeNode(midNode.val);
        newNode.left = sortedListToBSTCore(node, middle);
        newNode.right = sortedListToBSTCore(midNode.next, size - size/2 - 1);
        return newNode;
    }

    private int getListSize(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }

    private ListNode getNextNNode(ListNode node, int N) {
        while (N > 0) {
            node = node.next;
            N--;
        }
        return node;
    }
}
