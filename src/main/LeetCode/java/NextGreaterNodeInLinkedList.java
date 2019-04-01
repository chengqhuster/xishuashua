package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/next-greater-node-in-linked-list/
 *
 * 思路简述：遍历链表时把节点的 value 值压入栈中，压入之前判断该 value 值是否比栈顶的值大，若是则该
 *          value 是栈顶对应元素的 NextGreaterNode，因此我们还需要一个辅助栈来保存节点的索引位置
 *
 */

import LeetCode.java.DataStruct.ListNode;

import java.util.Stack;

public class NextGreaterNodeInLinkedList {
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        int[] res = new int[size];
        temp = head;
        Stack<Integer> st_val = new Stack<>();
        Stack<Integer> st_index = new Stack<>();
        int index = 0;
        while (temp != null) {
            while (!st_val.isEmpty() && st_val.peek() < temp.val) {
                res[st_index.pop()] = temp.val;
                st_val.pop();
            }
            st_val.push(temp.val);
            st_index.push(index);
            temp = temp.next;
            index++;
        }
        return res;
    }
}
