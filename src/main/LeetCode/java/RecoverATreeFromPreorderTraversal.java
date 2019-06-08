package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
 *
 * 思路简述：stack保存构建的树当前节点的路径，deep 维持栈顶节点的深度，从 S 中获取下一个节点的 val 以及深度信息
 *          从 stack 的相应位置插入下一个节点
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.Stack;

public class RecoverATreeFromPreorderTraversal {
    int pos;

    public TreeNode recoverFromPreorder(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }
        pos = 0;
        int[] head = getNextVal(S);
        TreeNode root = new TreeNode(head[1]);
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        int deep = 1;
        for (int[] next = getNextVal(S); next[0] > 0; next = getNextVal(S)) {
            TreeNode node = new TreeNode(next[1]);
            while (deep > next[0]) {
                st.pop();
                deep--;
            }
            TreeNode fa = st.peek();
            if (fa.left == null) {
                fa.left = node;
            } else {
                fa.right = node;
            }
            st.push(node);
            deep++;
        }
        return root;
    }

    private int[] getNextVal(String s) {
        int[] res = new int[2];
        if (pos == s.length()) {
            return res;
        }
        while (pos < s.length()) {
            if (s.charAt(pos) == '-') {
                res[0]++;
            } else {
                break;
            }
            pos++;
        }
        while (pos < s.length()) {
            if (s.charAt(pos) != '-') {
                res[1] = res[1] * 10 + s.charAt(pos) - '0';
            } else {
                break;
            }
            pos++;
        }
        return res;
    }
}
