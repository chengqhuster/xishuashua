package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * 思路简述：递归与非递归方式
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inorderTraversalCore(res, root);
        return res;
    }

    private void inorderTraversalCore(List<Integer> res, TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversalCore(res, node.left);
        res.add(node.val);
        inorderTraversalCore(res, node.right);
    }

    private void inOrderIter(List<Integer> res, TreeNode node) {
        Stack<TreeNode> st = new Stack<>();
        while (node != null || !st.isEmpty()) {
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                TreeNode n = st.pop();
                res.add(n.val);
                node = n.right;
            }
        }
    }
}
