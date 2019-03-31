package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * 思路简述：递归与非递归方式
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        postorderTraversalCore(res, root);
        return res;
    }

    private void postorderTraversalCore(List<Integer> res, TreeNode node) {
        if (node == null) {
            return;
        }
        postorderTraversalCore(res, node.left);
        postorderTraversalCore(res, node.right);
        res.add(node.val);
    }

    private void postOrderIter(List<Integer> res, TreeNode node) {
        Stack<TreeNode> st = new Stack<>();
        while (node != null || !st.isEmpty()) {
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                TreeNode t = st.peek();
                while (t.right == null || node == t.right) {
                    node = st.pop();
                    res.add(node.val);
                    if (st.isEmpty()) {
                        node = null;
                        break;
                    }
                    t = st.peek();
                }
                if (!st.isEmpty()) {
                    node = t.right;
                }
            }
        }
    }
}
