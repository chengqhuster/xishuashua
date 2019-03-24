package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * 思路简述：先序遍历，Core函数返回的是从当前节点出发到子孙节点的最大路径和
 *          题目要求的路径不一定要经过根节点
 *
 */

import LeetCode.java.DataStruct.TreeNode;

public class BinaryTreeMaximumPathSum {
    int sum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSumCore(root);
        return sum;
    }

    private int maxPathSumCore(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxPathSumCore(node.left);
        int right = maxPathSumCore(node.right);
        int temp = node.val;
        if (left > 0) {
            temp += left;
        }
        if (right > 0) {
            temp += right;
        }
        sum = Math.max(sum, temp);
        return Math.max(Math.max(left, right), 0) + node.val;
    }
}
