package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * 思路简述：表头数组记录每个列表的表头，每次取最小值的表头节点，前后链接，注意终止条件的判断。
 *
 */

import LeetCode.java.DataStruct.ListNode;

import java.util.Arrays;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        int N = lists.length;
        ListNode[] pos = Arrays.copyOf(lists, N);
        for(ListNode node : pos) {
            if(node == null) {
                N--;
            }
        }
        ListNode last = null;
        ListNode res = null;
        while(N > 0) {
            int temp = getMinNode(pos);
            if(last == null) {
                res = pos[temp];
            } else {
                last.next = pos[temp];
            }
            last = pos[temp];
            pos[temp] = pos[temp].next;
            if(pos[temp] == null) {
                N--;
            }
        }
        return res;
    }

    private int getMinNode(ListNode[] pos) {
        int  index = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<pos.length; i++) {
            if(pos[i] != null && pos[i].val < min) {
                min = pos[i].val;
                index = i;
            }
        }
        return index;
    }
}
