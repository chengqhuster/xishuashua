package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/balanced-binary-tree/
 *
 * 思路简述：后序遍历判断左右子树深度是否平衡
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class BalancedBinaryTree {
    boolean flag = true;

    public boolean isBalanced(TreeNode root) {
        getTreeDepth(root);
        return flag;
    }

    private int getTreeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(node.left) + 1;
        int rightDepth = getTreeDepth(node.right) + 1;
        if (Math.abs(leftDepth - rightDepth) > 1) {
            flag = false;
        }
        return Math.max(leftDepth, rightDepth);
    }
}
