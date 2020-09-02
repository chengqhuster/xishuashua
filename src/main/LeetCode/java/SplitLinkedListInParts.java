package LeetCode.java;

import LeetCode.java.DataStruct.ListNode;

/**
 * 题目描述：https://leetcode.com/problems/split-linked-list-in-parts/
 *
 * 思路简述：aSize * aLen + bSize * bLen = root.len, aSize + bSize = k,
 * aLen - bLen <= 1, aLen >= bLen
 */
public class SplitLinkedListInParts {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int len = getListLength(root);
        if (len == 0) {
            return res;
        }
        int aLen = 0, aSize = 0, bLen = 0, bSize = 0;
        if (len % k == 0) {
            aLen = len / k;
            aSize = k;
        } else {
            aLen = len / k + 1;
            bLen = aLen - 1;
            bSize = aLen * k - len;
            aSize = k - bSize;
        }
        int index = 1;
        int count = 1;
        ListNode last = root;
        ListNode temp = root.next;
        res[0] = root;
        while (index < len) {
            if (index <= aLen * aSize) {
                if (index % aLen == 0) {
                    res[count] = temp;
                    count++;
                    last.next = null;
                }
            } else {
                if ((index - aLen * aSize) % bLen == 0) {
                    res[count] = temp;
                    count++;
                    last.next = null;
                }
            }
            last = temp;
            temp = temp.next;
            index++;
        }
        return res;
    }

    private int getListLength(ListNode root) {
        int res = 0;
        while (root != null) {
            root = root.next;
            res++;
        }
        return res;
    }
}
