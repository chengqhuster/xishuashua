package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * 思路简述：先序遍历，Core函数返回的是子树flatten之后的尾节点
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        if (root != null) {
            flattenCore(root);
        }
    }

    private TreeNode flattenCore(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node;
        } else if (node.left == null) {
            return flattenCore(node.right);
        } else if (node.right == null) {
            node.right = node.left;
            node.left = null;
            return flattenCore(node.right);
        } else {
            TreeNode left = flattenCore(node.left);
            TreeNode right = flattenCore(node.right);
            left.right = node.right;
            node.right = node.left;
            node.left = null;
            return right;
        }
    }
}
