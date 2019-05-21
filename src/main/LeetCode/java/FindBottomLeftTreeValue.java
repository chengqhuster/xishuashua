package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/find-bottom-left-tree-value/
 *
 * 思路简述：二叉数的分层遍历，用队列实现
 *
 */

import LeetCode.java.DataStruct.TreeNode;

import java.util.LinkedList;

public class FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode targetNode = root;
        int res = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (targetNode == node) {
                res = targetNode.val;
                targetNode = null;
            }
            if (node.left != null) {
                queue.add(node.left);
                if (targetNode == null) {
                    targetNode = node.left;
                }
            }
            if (node.right != null) {
                queue.add(node.right);
                if (targetNode == null) {
                    targetNode = node.right;
                }
            }
        }
        return res;
    }
}
