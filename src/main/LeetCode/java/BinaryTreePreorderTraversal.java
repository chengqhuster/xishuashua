package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 * 思路简述：递归与非递归方式
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preorderTraversalCore(res, root);
        return res;
    }

    private void preorderTraversalCore(List<Integer> res, TreeNode node) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        preorderTraversalCore(res, node.left);
        preorderTraversalCore(res, node.right);
    }

    private void preOrderIter(List<Integer> res, TreeNode node) {
        Stack<TreeNode> st = new Stack<>();
        if (node == null) {
            return;
        }
        st.push(node);
        while (!st.isEmpty()) {
            TreeNode n = st.pop();
            res.add(n.val);
            if (n.right != null) {
                st.push(n.right);
            }
            if (n.left != null) {
                st.push(n.left);
            }
        }
    }

    private void preOrderIter2(List<Integer> res, TreeNode node) {
        Stack<TreeNode> st = new Stack<>();
        while (node != null || !st.isEmpty()) {
            if (node != null) {
                res.add(node.val);
                st.push(node);
                node = node.left;
            } else {
                node = st.pop();
                node = node.right;
            }
        }
    }
}
